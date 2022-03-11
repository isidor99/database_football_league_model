package gui.window;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import database.worker.TableController;
import gui.component.TableItemComponent;
import model.League;
import model.Table;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeagueInfoWindow extends JFrame {

    private final League league;

    private final ArrayList<Table> tables;

    public LeagueInfoWindow(League league) {
        this.league = league;

        this.tables = TableController.getTablesForLeague(league.getId());

        initComponents();
    }

    /**
     *
     */
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        setLayout(groupLayout);

        GridLayout gridLayout = new GridLayout(tables.size(), 1);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel leagueLabel = new JLabel(league.getLeagueName());
        leagueLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 18));

        JLabel tableLabel = new JLabel(TextConstants.TABLE_INFO);

        JPanel tablePanel = new JPanel(gridLayout);
        JScrollPane tableScrollPane = new JScrollPane(tablePanel);

        for (Table table : tables) {
            TableItemComponent tableItemComponent = new TableItemComponent(table);
            tablePanel.add(tableItemComponent);
        }

        //
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(leagueLabel)
                        .addComponent(tableLabel)
                        .addComponent(tableScrollPane, Dimensions.PANE_WIDTH, Dimensions.PANE_WIDTH, Dimensions.PANE_WIDTH)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(leagueLabel)
                        .addGap(Dimensions.MARGIN)
                        .addComponent(tableLabel)
                        .addComponent(tableScrollPane)
        );

        setTitle(TextConstants.TITLE_LEAGUE_INFO);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
