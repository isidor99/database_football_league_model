package gui.tab;

import constants.Dimensions;
import gui.component.MatchItemComponent;
import database.worker.MatchController;
import listener.MatchListener;
import model.Match;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MatchTab extends JComponent implements MatchListener {

    private JPanel matchesPanel;

    private final int leagueId;
    private final int seasonId;
    private ArrayList<Match> matches;

    public MatchTab(int leagueId, int seasonId) {

        this.leagueId = leagueId;
        this.seasonId = seasonId;

        matches = MatchController.getMatchesForLeagueInSeason(leagueId, seasonId);

        initComponents();
    }

    @Override
    public void onMatchChanged() {
        matches = MatchController.getMatchesForLeagueInSeason(leagueId, seasonId);
        setData();
    }

    //
    //
    //
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(this);

        setLayout(groupLayout);

        GridLayout gridLayout = new GridLayout(matches.size(), 1);
        matchesPanel = new JPanel(gridLayout);

        setData();

        JScrollPane scrollPane = new JScrollPane(matchesPanel);

        //
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(scrollPane, Dimensions.TABLE_PANE_WIDTH, Dimensions.TABLE_PANE_WIDTH, Dimensions.TABLE_PANE_WIDTH)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(scrollPane, Dimensions.TABLE_PANE_WIDTH, Dimensions.TABLE_PANE_WIDTH, Dimensions.TABLE_PANE_WIDTH)
        );
    }

    private void setData() {

        matchesPanel.removeAll();
        matchesPanel.revalidate();
        matchesPanel.repaint();

        GridLayout gridLayout = new GridLayout(matches.size(), 1);
        matchesPanel.setLayout(gridLayout);

        for (Match match : matches) {
            MatchItemComponent matchItemComponent = new MatchItemComponent(match, this);
            matchesPanel.add(matchItemComponent);
        }
    }
}
