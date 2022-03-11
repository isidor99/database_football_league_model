package gui.component;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import gui.window.MatchInfoWindow;
import listener.MatchListener;
import model.Match;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MatchItemComponent extends JComponent {

    private JButton moreInfoButton;

    private final Match match;
    private MatchListener matchListener;

    public MatchItemComponent(Match match, MatchListener matchListener) {
        this.match = match;
        this.matchListener = matchListener;

        initComponents();
        setListeners();
    }

    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");

        JLabel timeLabel = new JLabel(timeFormat.format(match.getDatetime()));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.ITALIC, 10));

        JLabel dateLabel = new JLabel(dateFormat.format(match.getDatetime()));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.ITALIC, 10));

        JLabel homeClubLabel = new JLabel(match.getHomeClub());
        homeClubLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));

        JLabel awayClubLabel = new JLabel(match.getAwayClub());
        awayClubLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));

        JLabel homeClubGoalsLabel = new JLabel();
        homeClubGoalsLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));

        JLabel awayClubGoalsLabel = new JLabel();
        awayClubGoalsLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));

        moreInfoButton = new JButton(TextConstants.BUTTON_MORE_INFO);

        if (match.getDatetime().before(new Timestamp(System.currentTimeMillis()))) {
            homeClubGoalsLabel.setText(String.valueOf(match.getHomeClubGoals()));
            awayClubGoalsLabel.setText(String.valueOf(match.getAwayClubGoals()));
        }

        JPanel linePanel = new JPanel();
        linePanel.setBackground(new Color(0, 0, 0));

        //
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGroup(
                                            groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                    .addComponent(timeLabel)
                                                    .addComponent(dateLabel)
                                        )
                                        .addGap(Dimensions.MARGIN * 3)
                                        .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(homeClubLabel, Dimensions.CLUB_NAME_LABEL_WIDTH, Dimensions.CLUB_NAME_LABEL_WIDTH, Dimensions.CLUB_NAME_LABEL_WIDTH)
                                                        .addComponent(awayClubLabel, Dimensions.CLUB_NAME_LABEL_WIDTH, Dimensions.CLUB_NAME_LABEL_WIDTH, Dimensions.CLUB_NAME_LABEL_WIDTH)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(homeClubGoalsLabel)
                                                        .addComponent(awayClubGoalsLabel)
                                        )
                        )
                        .addComponent(moreInfoButton)
                        .addComponent(linePanel)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(timeLabel)
                                                        .addComponent(dateLabel)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addGap(Dimensions.MARGIN)
                                                        .addComponent(homeClubLabel)
                                                        .addComponent(awayClubLabel)
                                                        .addGap(Dimensions.MARGIN)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addGap(Dimensions.MARGIN)
                                                        .addComponent(homeClubGoalsLabel)
                                                        .addComponent(awayClubGoalsLabel)
                                                        .addGap(Dimensions.MARGIN)
                                        )
                        )
                        .addGap(Dimensions.MARGIN)
                        .addComponent(moreInfoButton, Dimensions.SMALL_BUTTON_HEIGHT, Dimensions.SMALL_BUTTON_HEIGHT, Dimensions.SMALL_BUTTON_HEIGHT)
                        .addGap(Dimensions.MARGIN)
                        .addComponent(linePanel, Dimensions.THICK_LINE_HEIGHT, Dimensions.THICK_LINE_HEIGHT, Dimensions.THICK_LINE_HEIGHT)
        );
    }

    private void setListeners() {
        moreInfoButton.addActionListener(l -> {
            new MatchInfoWindow(match, matchListener);
        });
    }
}
