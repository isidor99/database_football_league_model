package gui.tab;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import database.worker.MatchController;
import database.worker.TableController;
import listener.AddMatchListener;
import listener.MatchListener;
import listener.TableListener;
import model.Match;
import model.PositionOnTable;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddMatchTab extends JComponent implements AddMatchListener {

    private JComboBox homeTeamComboBox;
    private JComboBox awayTeamComboBox;
    private JTextField dateTextField;
    private JTextField matchDayTextField;
    private JLabel messageLabel;
    private JButton addMatchButton;

    private ArrayList<PositionOnTable> positions;
    private MatchListener matchListener;
    private final int leagueId;
    private final int seasonId;

    public AddMatchTab(int leagueId, int seasonId) {

        this.leagueId = leagueId;
        this.seasonId = seasonId;

        positions = TableController.getPositionsOnTable(leagueId, seasonId);

        initComponents();
        setListeners();
    }

    @Override
    public void onTableChange() {
        positions = TableController.getPositionsOnTable(leagueId, seasonId);
        homeTeamComboBox.setModel(new DefaultComboBoxModel(positions.toArray()));
        awayTeamComboBox.setModel(new DefaultComboBoxModel(positions.toArray()));
    }

    public void setMatchListener(MatchListener matchListener) {
        this.matchListener = matchListener;
    }

    //
    //
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel homeTeamLabel = new JLabel(TextConstants.HOME_TEAM);
        JLabel awayTeamLabel = new JLabel(TextConstants.AWAY_TEAM);
        JLabel datetimeLabel = new JLabel(TextConstants.DATE_TIME);
        JLabel matchDayLabel = new JLabel(TextConstants.MATCH_DAY);

        messageLabel = new JLabel();
        messageLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 12));

        homeTeamComboBox = new JComboBox(positions.toArray());
        awayTeamComboBox = new JComboBox(positions.toArray());
        dateTextField = new JTextField(TextConstants.DATE_TIME_FORMAT);
        matchDayTextField = new JTextField();

        addMatchButton = new JButton(TextConstants.BUTTON_ADD_MATCH);

        //
        //
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addComponent(homeTeamLabel, Dimensions.ADD_MATCH_LABEL_WIDTH, Dimensions.ADD_MATCH_LABEL_WIDTH, Dimensions.ADD_MATCH_LABEL_WIDTH)
                                        .addComponent(homeTeamComboBox, Dimensions.GOAL_COMBO_BOX_WIDTH, Dimensions.GOAL_COMBO_BOX_WIDTH, Dimensions.GOAL_COMBO_BOX_WIDTH)
                        )
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addComponent(awayTeamLabel, Dimensions.ADD_MATCH_LABEL_WIDTH, Dimensions.ADD_MATCH_LABEL_WIDTH, Dimensions.ADD_MATCH_LABEL_WIDTH)
                                        .addComponent(awayTeamComboBox, Dimensions.GOAL_COMBO_BOX_WIDTH, Dimensions.GOAL_COMBO_BOX_WIDTH, Dimensions.GOAL_COMBO_BOX_WIDTH)
                        )
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addComponent(datetimeLabel, Dimensions.ADD_MATCH_LABEL_WIDTH, Dimensions.ADD_MATCH_LABEL_WIDTH, Dimensions.ADD_MATCH_LABEL_WIDTH)
                                        .addComponent(dateTextField, Dimensions.GOAL_COMBO_BOX_WIDTH, Dimensions.GOAL_COMBO_BOX_WIDTH, Dimensions.GOAL_COMBO_BOX_WIDTH)
                        )
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addComponent(matchDayLabel, Dimensions.ADD_MATCH_LABEL_WIDTH, Dimensions.ADD_MATCH_LABEL_WIDTH, Dimensions.ADD_MATCH_LABEL_WIDTH)
                                        .addComponent(matchDayTextField, Dimensions.GOAL_COMBO_BOX_WIDTH, Dimensions.GOAL_COMBO_BOX_WIDTH, Dimensions.GOAL_COMBO_BOX_WIDTH)
                        )
                        .addComponent(messageLabel)
                        .addComponent(addMatchButton, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(homeTeamLabel)
                                        .addComponent(homeTeamComboBox, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT)
                        )
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(awayTeamLabel)
                                        .addComponent(awayTeamComboBox, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT)
                        )
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(datetimeLabel)
                                        .addComponent(dateTextField, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT)
                        )
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(matchDayLabel)
                                        .addComponent(matchDayTextField, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT)
                        )
                        .addComponent(messageLabel)
                        .addComponent(addMatchButton)
        );
    }

    private void setListeners() {
        addMatchButton.addActionListener(l -> {

            int homeIndex = homeTeamComboBox.getSelectedIndex();
            int awayIndex = awayTeamComboBox.getSelectedIndex();

            if (homeIndex == awayIndex) {
                messageLabel.setText(TextConstants.MESSAGE_SAME_CLUBS);
                return;
            }

            if (dateTextField.getText().isEmpty() || !isValidDate(dateTextField.getText())) {
                messageLabel.setText(TextConstants.MESSAGE_INVALID_DATE_FORMAT);
                return;
            }

            if (isDateOld(dateTextField.getText())) {
                messageLabel.setText(TextConstants.MESSAGE_OLD_DATE);
                return;
            }

            if (!isNumber(matchDayTextField.getText())) {
                messageLabel.setText(TextConstants.MESSAGE_INVALID_MATCH_DAY_DATA);
                return;
            }

            Match match = new Match();

            match.setLeagueId(leagueId);
            match.setSeasonId(seasonId);
            match.setHomeClubId(positions.get(homeIndex).getClubId());
            match.setAwayClubId(positions.get(awayIndex).getClubId());
            match.setDatetime(Timestamp.valueOf(dateTextField.getText()));
            match.setMatchDay(Integer.parseInt(matchDayTextField.getText()));

            int error = MatchController.addMatch(match);

            if (error == 0) {
                messageLabel.setText(TextConstants.MESSAGE_MATCH_ADDED_SUCCESSFULLY);
                matchListener.onMatchChanged();
            }
        });
    }

    private boolean isValidDate(String date) {

        SimpleDateFormat format = new SimpleDateFormat(TextConstants.DATE_TIME_FORMAT);

        try {
            format.parse(date);
        } catch (ParseException ex) {
            return false;
        }

        return true;
    }

    private boolean isDateOld(String dateString) {

        SimpleDateFormat format = new SimpleDateFormat(TextConstants.DATE_TIME_FORMAT);

        try {
            Date date = format.parse(dateString);
            if (new Timestamp(date.getTime()).before(new Timestamp(System.currentTimeMillis())))
                return true;
        } catch (ParseException ex) {
            return true;
        }

        return false;
    }

    private boolean isNumber(String number) {

        try {
            Integer.parseInt(number);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
