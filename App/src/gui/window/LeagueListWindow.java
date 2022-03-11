package gui.window;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import database.worker.LeagueController;
import gui.component.LeagueItemComponent;
import model.League;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeagueListWindow extends JFrame {

    private final ArrayList<League> leagues;

    public LeagueListWindow() {

        leagues = LeagueController.getLeagues();

        initComponents();
    }

    /**
     *
     */
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        setLayout(groupLayout);

        GridLayout gridLayout = new GridLayout(leagues.size(), 1);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel leagueLabel = new JLabel(TextConstants.LEAGUE);
        leagueLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 14));

        JPanel leaguePanel = new JPanel(gridLayout);
        JScrollPane leaguePane = new JScrollPane(leaguePanel);

        // add league components
        for (League league : leagues) {
            LeagueItemComponent component = new LeagueItemComponent(league);
            leaguePanel.add(component);
        }

        // horizontal group
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(leagueLabel)
                        .addComponent(leaguePane, Dimensions.PANE_WIDTH, Dimensions.PANE_WIDTH, Dimensions.PANE_WIDTH)
        );

        // vertical group
        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(leagueLabel)
                        .addComponent(leaguePane)
        );

        setTitle(TextConstants.TITLE_LEAGUE);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
