package gui.window;

import constants.TextConstants;
import gui.tab.AddMatchTab;
import gui.tab.LeagueOptionsTab;
import gui.tab.MatchTab;
import gui.tab.TableTab;
import model.Table;

import javax.swing.*;

public class TableWindow extends JFrame {

    private final Table table;

    public TableWindow(Table table) {
        this.table = table;

        initComponents();
    }

    /**
     *
     */
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        TableTab tableTab = new TableTab(table.getLeagueId(), table.getSeasonId());
        MatchTab matchTab = new MatchTab(table.getLeagueId(), table.getSeasonId());
        LeagueOptionsTab leagueOptionsTab = new LeagueOptionsTab(table.getLeagueId(), table.getSeasonId());
        AddMatchTab addMatchTab = new AddMatchTab(table.getLeagueId(), table.getSeasonId());

        leagueOptionsTab.setTableListener(tableTab);
        leagueOptionsTab.setMatchListener(matchTab);
        leagueOptionsTab.setAddMatchListener(addMatchTab);
        addMatchTab.setMatchListener(matchTab);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab(TextConstants.TABLE, tableTab);
        tabbedPane.addTab(TextConstants.MATCHES, matchTab);
        tabbedPane.addTab(TextConstants.OPTIONS, leagueOptionsTab);
        tabbedPane.addTab(TextConstants.ADD_MATCH, addMatchTab);

        //
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(tabbedPane)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(tabbedPane)
        );

        setTitle(TextConstants.TITLE_TABLE);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
