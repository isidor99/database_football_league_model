package gui.component;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import gui.window.LeagueInfoWindow;
import model.League;

import javax.swing.*;
import java.awt.*;

public class LeagueItemComponent extends JComponent {

    private final League league;

    private JButton moreInfoButton;

    public LeagueItemComponent(League league) {
        this.league = league;

        initComponents();
        setListeners();
    }

    /**
     *
     */
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);

        JLabel leagueLabel = new JLabel(league.getLeagueName());
        leagueLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 14));

        JLabel countryLabel = new JLabel(league.getCountry());
        countryLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.ITALIC, 10));

        //
        moreInfoButton = new JButton(TextConstants.BUTTON_MORE_INFO);
        moreInfoButton.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 12));

        // layout
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(Dimensions.MARGIN)
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(leagueLabel)
                                        .addComponent(countryLabel)
                                        .addComponent(moreInfoButton, GroupLayout.Alignment.CENTER, Dimensions.SMALL_BUTTON_WIDTH, Dimensions.SMALL_BUTTON_WIDTH, Dimensions.SMALL_BUTTON_WIDTH)
                        )
                        .addGap(Dimensions.MARGIN)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(Dimensions.MARGIN)
                        .addComponent(leagueLabel)
                        .addComponent(countryLabel)
                        .addGap(Dimensions.MARGIN)
                        .addComponent(moreInfoButton, Dimensions.SMALL_BUTTON_HEIGHT, Dimensions.SMALL_BUTTON_HEIGHT, Dimensions.SMALL_BUTTON_HEIGHT)
                        .addGap(Dimensions.MARGIN)
        );
    }

    /**
     *
     */
    private void setListeners() {
        moreInfoButton.addActionListener(l -> {
            new LeagueInfoWindow(league);
        });
    }
}
