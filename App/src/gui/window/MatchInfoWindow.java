package gui.window;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import database.worker.MatchController;
import gui.component.GoalItemComponent;
import listener.DateListener;
import listener.MatchListener;
import model.Goal;
import model.Match;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MatchInfoWindow extends JFrame implements DateListener {

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private final Match match;

    private JLabel timeLabel;
    private JLabel dateLabel;
    private JLabel messageLabel;
    private JButton editDateTime;
    private JButton deleteMatch;

    private final MatchListener matchListener;

    public MatchInfoWindow(Match match, MatchListener matchListener) {

        this.match = match;
        this.matchListener = matchListener;

        MatchController.getGoalsForMatch(match);

        initComponents();
        setListeners();
    }

    @Override
    public void onDateChanged(Timestamp timestamp) {
        match.setDatetime(timestamp);

        timeLabel.setText(timeFormat.format(timestamp));
        dateLabel.setText(dateFormat.format(timestamp));

        matchListener.onMatchChanged();
    }

    //
    //
    //
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        String matchDayText = TextConstants.MATCH_DAY + match.getMatchDay();

        JLabel matchDayLabel = new JLabel(matchDayText);
        matchDayLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        matchDayLabel.setHorizontalAlignment(JLabel.CENTER);

        timeLabel = new JLabel(timeFormat.format(match.getDatetime()));
        timeLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        dateLabel = new JLabel(dateFormat.format(match.getDatetime()));
        dateLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel homeClubName = new JLabel(match.getHomeClub());
        homeClubName.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        homeClubName.setHorizontalAlignment(JLabel.CENTER);

        JLabel homeClubGoals = new JLabel();
        homeClubGoals.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        homeClubGoals.setHorizontalAlignment(JLabel.CENTER);

        JLabel awayClubName = new JLabel(match.getAwayClub());
        awayClubName.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        awayClubName.setHorizontalAlignment(JLabel.CENTER);

        JLabel awayClubGoals = new JLabel();
        awayClubGoals.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        awayClubGoals.setHorizontalAlignment(JLabel.CENTER);

        JLabel colonLabel = new JLabel(TextConstants.COLON);
        colonLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        colonLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel goalsLabel = new JLabel(TextConstants.GOALS);
        goalsLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 14));

        JPanel linePanel = new JPanel();
        linePanel.setBackground(new Color(0, 0, 0));

        GridLayout gridLayout = new GridLayout(match.getGoals().size(), 1);
        JPanel goalsPanel = new JPanel(gridLayout);

        for (Goal goal : match.getGoals()) {
            GoalItemComponent goalItemComponent = new GoalItemComponent(goal);
            goalsPanel.add(goalItemComponent);
        }

        JScrollPane scrollPane = new JScrollPane(goalsPanel);

        editDateTime = new JButton(TextConstants.BUTTON_EDIT_DATE_TIME);
        deleteMatch = new JButton(TextConstants.BUTTON_DELETE_MATCH);

        messageLabel = new JLabel();

        if (match.getDatetime().before(new Timestamp(System.currentTimeMillis()))) {
            homeClubGoals.setText(String.valueOf(match.getHomeClubGoals()));
            awayClubGoals.setText(String.valueOf(match.getAwayClubGoals()));
        }

        //
        //
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(matchDayLabel)
                        .addComponent(timeLabel)
                        .addComponent(dateLabel)
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGap(Dimensions.MARGIN)
                                        .addComponent(homeClubName, Dimensions.CLUB_NAME_LABEL_WIDTH, Dimensions.CLUB_NAME_LABEL_WIDTH, Dimensions.CLUB_NAME_LABEL_WIDTH)
                                        .addComponent(homeClubGoals)
                                        .addComponent(colonLabel)
                                        .addComponent(awayClubGoals)
                                        .addComponent(awayClubName, Dimensions.CLUB_NAME_LABEL_WIDTH, Dimensions.CLUB_NAME_LABEL_WIDTH, Dimensions.CLUB_NAME_LABEL_WIDTH)
                                        .addGap(Dimensions.MARGIN)
                        )
                        .addComponent(linePanel)
                        .addComponent(goalsLabel)
                        .addComponent(scrollPane)
                        .addComponent(messageLabel)
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addComponent(editDateTime, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH)
                                        .addComponent(deleteMatch, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH)
                        )
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(matchDayLabel)
                        .addGap(Dimensions.MARGIN)
                        .addComponent(timeLabel)
                        .addComponent(dateLabel)
                        .addGap(Dimensions.MARGIN * 2)
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(homeClubName)
                                        .addComponent(homeClubGoals)
                                        .addComponent(colonLabel)
                                        .addComponent(awayClubGoals)
                                        .addComponent(awayClubName)
                        )
                        .addGap(Dimensions.MARGIN)
                        .addComponent(linePanel, Dimensions.THICK_LINE_HEIGHT, Dimensions.THICK_LINE_HEIGHT, Dimensions.THICK_LINE_HEIGHT)
                        .addComponent(goalsLabel)
                        .addComponent(scrollPane)
                        .addGap(Dimensions.MARGIN)
                        .addComponent(messageLabel)
                        .addGap(Dimensions.MARGIN * 3)
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(editDateTime)
                                        .addComponent(deleteMatch)
                        )
        );

        setTitle(TextConstants.TITLE_MATCH);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void setListeners() {

        editDateTime.addActionListener(l -> {
            if (match.getDatetime().after(new Timestamp(System.currentTimeMillis()))) {
                new DateTimeWindow(match.getId(), this);
            } else
                messageLabel.setText(TextConstants.MESSAGE_CHANGE_NOT_ALLOWED);
        });

        deleteMatch.addActionListener(l -> {

            if (match.getDatetime().after(new Timestamp(System.currentTimeMillis()))) {

                int error = MatchController.deleteMatch(match);

                if (error == 0) {
                    matchListener.onMatchChanged();
                    dispose();
                }
            } else
                messageLabel.setText(TextConstants.MESSAGE_DELETE_NOT_ALLOWED);
        });
    }
}
