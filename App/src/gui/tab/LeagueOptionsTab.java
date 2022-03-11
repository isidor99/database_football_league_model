package gui.tab;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import database.worker.ClubController;
import database.worker.MatchController;
import database.worker.TableController;
import listener.AddMatchListener;
import listener.MatchListener;
import listener.TableListener;
import model.Club;
import model.Match;
import model.PositionOnTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeagueOptionsTab extends JComponent {

    private JLabel addMessageLabel;
    private JLabel removeMessageLabel;
    private JComboBox addClubComboBox;
    private JComboBox removeClubComboBox;
    private JButton addClubButton;
    private JButton removeClubButton;

    private final int leagueId;
    private final int seasonId;

    private final ArrayList<Club> allClubs;
    private final ArrayList<PositionOnTable> positions;

    private TableListener tableListener;
    private MatchListener matchListener;
    private AddMatchListener addMatchListener;

    public LeagueOptionsTab(int leagueId, int seasonId) {

        this.leagueId = leagueId;
        this.seasonId = seasonId;

        allClubs = ClubController.getClubs();
        positions = TableController.getPositionsOnTable(leagueId, seasonId);

        initComponents();
        setListeners();
    }

    public void setTableListener(TableListener tableListener) { this.tableListener = tableListener; }

    public void setMatchListener(MatchListener matchListener) { this.matchListener = matchListener; }

    public void setAddMatchListener(AddMatchListener addMatchListener) { this.addMatchListener = addMatchListener; }

    //
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        //
        // add club
        JLabel addClubInLeagueLabel = new JLabel(TextConstants.ADD_CLUB_IN_LEAGUE);
        addClubInLeagueLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));

        JLabel clubLabel1 = new JLabel(TextConstants.SELECT_CLUB);

        addMessageLabel = new JLabel();
        addMessageLabel.setHorizontalAlignment(JLabel.CENTER);

        addClubButton = new JButton(TextConstants.BUTTON_ADD_CLUB);

        addClubComboBox = new JComboBox(allClubs.toArray());

        JPanel linePanel1 = new JPanel();
        linePanel1.setBackground(new Color(0, 0, 0));

        //
        // remove club
        JLabel removeClubFromLeagueLabel = new JLabel(TextConstants.REMOVE_CLUB_FROM_LEAGUE);
        removeClubFromLeagueLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));

        JLabel clubLabel2 = new JLabel(TextConstants.SELECT_CLUB);

        removeMessageLabel = new JLabel();
        removeMessageLabel.setHorizontalAlignment(JLabel.CENTER);

        removeClubButton = new JButton(TextConstants.BUTTON_REMOVE_CLUB);

        removeClubComboBox = new JComboBox(positions.toArray());

        JPanel linePanel2 = new JPanel();
        linePanel2.setBackground(new Color(0, 0, 0));

        //
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(addClubInLeagueLabel)
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGap(Dimensions.MARGIN)
                                        .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(clubLabel1)
                                        )
                                        .addGap(Dimensions.MARGIN * 3)
                                        .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(addClubComboBox)
                                        )
                        )
                        .addComponent(addMessageLabel)
                        .addComponent(addClubButton)
                        .addComponent(linePanel1)
                        .addComponent(removeClubFromLeagueLabel) // new
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGap(Dimensions.MARGIN)
                                        .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(clubLabel2)
                                        )
                                        .addGap(Dimensions.MARGIN * 3)
                                        .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(removeClubComboBox)
                                        )
                        )
                        .addComponent(removeMessageLabel)
                        .addComponent(removeClubButton)
                        .addComponent(linePanel2)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(addClubInLeagueLabel)
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(clubLabel1)
                                        .addComponent(addClubComboBox, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT)
                        )
                        .addComponent(addMessageLabel)
                        .addGap(Dimensions.MARGIN)
                        .addComponent(addClubButton)
                        .addGap(Dimensions.MARGIN)
                        .addComponent(linePanel1, Dimensions.THICK_LINE_HEIGHT, Dimensions.THICK_LINE_HEIGHT, Dimensions.THICK_LINE_HEIGHT)
                        .addComponent(removeClubFromLeagueLabel) // new
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(clubLabel2)
                                        .addComponent(removeClubComboBox, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT, Dimensions.COMBO_BOX_HEIGHT)
                        )
                        .addComponent(removeMessageLabel)
                        .addGap(Dimensions.MARGIN)
                        .addComponent(removeClubButton)
                        .addGap(Dimensions.MARGIN)
                        .addComponent(linePanel2, Dimensions.THICK_LINE_HEIGHT, Dimensions.THICK_LINE_HEIGHT, Dimensions.THICK_LINE_HEIGHT)
        );
    }

    private void setListeners() {

        addClubButton.addActionListener(l -> {

            int index = addClubComboBox.getSelectedIndex();

            int error = TableController.addClubOnTable(leagueId, seasonId, allClubs.get(index).getId());

            if (error == 0) {
                addMessageLabel.setText(TextConstants.MESSAGE_CLUB_ADDED_SUCCESSFULLY);

                positions.add(
                        new PositionOnTable(
                                leagueId,
                                seasonId,
                                allClubs.get(index).getId(),
                                allClubs.get(index).getName()
                        )
                );
                removeClubComboBox.setModel(new DefaultComboBoxModel(positions.toArray()));

                tableListener.onTableChange();
                addMatchListener.onTableChange();
            } else
                addMessageLabel.setText(TextConstants.MESSAGE_CLUB_ADDING_ERROR);
        });

        removeClubButton.addActionListener(l -> {

            int index = removeClubComboBox.getSelectedIndex();

            int numberOfPlayedMatches = MatchController.getNumberOfPlayedMatches(positions.get(index).getClubId(), leagueId, seasonId);

            if (numberOfPlayedMatches > 0) {
                removeMessageLabel.setText(TextConstants.MESSAGE_CLUB_PLAYED);
                return;
            }

            int error = TableController.removeClubFromTable(leagueId, seasonId, positions.get(index).getClubId());

            if (error == 0) {
                removeMessageLabel.setText(TextConstants.MESSAGE_CLUB_REMOVE_SUCCESSFULLY);

                positions.remove(index);
                removeClubComboBox.setModel(new DefaultComboBoxModel(positions.toArray()));

                tableListener.onTableChange();
                matchListener.onMatchChanged();
                addMatchListener.onTableChange();
            } else
                removeMessageLabel.setText(TextConstants.MESSAGE_CLUB_REMOVING_ERROR);
        });
    }
}
