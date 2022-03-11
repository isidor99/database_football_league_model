package gui.tab;

import constants.Dimensions;
import database.worker.TableController;
import listener.TableListener;
import model.PositionOnTable;

import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TableTab extends JComponent implements TableListener {

    private JTable table;

    private Object[][] data;
    private final String[] columnNames =
            {
                    "#",
                    "Club Name",
                    "GF",
                    "GA",
                    "GD",
                    "W",
                    "L",
                    "D",
                    "Points"
            };

    private final int leagueId;
    private final int seasonId;

    public TableTab(int leagueId, int seasonId) {

        this.leagueId = leagueId;
        this.seasonId = seasonId;

        loadData();
        initComponents();
    }

    @Override
    public void onTableChange() {
        loadData();
        refreshTable();
    }

    //
    //

    //
    private void loadData() {
        ArrayList<PositionOnTable> positions = TableController.getPositionsOnTable(leagueId, seasonId);

        data = new Object[positions.size()][columnNames.length];

        for (int i = 0; i < positions.size(); ++i) {

            PositionOnTable p = positions.get(i);

            data[i][0] = String.valueOf(i + 1);
            data[i][1] = p.getClubName();
            data[i][2] = String.valueOf(p.getGoalsFor());
            data[i][3] = String.valueOf(p.getGoalsAgainst());
            data[i][4] = String.valueOf(p.getGoalDifference());
            data[i][5] = String.valueOf(p.getNumberOfWins());
            data[i][6] = String.valueOf(p.getNumberOfLosses());
            data[i][7] = String.valueOf(p.getNumberOfDraws());
            data[i][8] = String.valueOf(p.getPoints());
        }
    }

    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        table = new JTable(data, columnNames);
        setTableWidth();

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        Iterator<TableColumn> iterator = table.getColumnModel().getColumns().asIterator();
        while (iterator.hasNext())
            iterator.next().setCellRenderer(centerRenderer);

        //
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(scrollPane, Dimensions.TABLE_PANE_WIDTH, Dimensions.TABLE_PANE_WIDTH, Dimensions.TABLE_PANE_WIDTH)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(scrollPane, Dimensions.TABLE_PANE_HEIGHT, Dimensions.TABLE_PANE_HEIGHT, Dimensions.TABLE_PANE_HEIGHT)
        );
    }

    private void refreshTable() {
        TableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
        setTableWidth();
    }

    private void setTableWidth() {
        table.getColumnModel().getColumn(0).setPreferredWidth(Dimensions.SMALL_COLUMN);
        table.getColumnModel().getColumn(1).setPreferredWidth(Dimensions.LARGE_COLUMN);
        table.getColumnModel().getColumn(2).setPreferredWidth(Dimensions.SMALL_COLUMN);
        table.getColumnModel().getColumn(3).setPreferredWidth(Dimensions.SMALL_COLUMN);
        table.getColumnModel().getColumn(4).setPreferredWidth(Dimensions.SMALL_COLUMN);
        table.getColumnModel().getColumn(5).setPreferredWidth(Dimensions.SMALL_COLUMN);
        table.getColumnModel().getColumn(6).setPreferredWidth(Dimensions.SMALL_COLUMN);
        table.getColumnModel().getColumn(7).setPreferredWidth(Dimensions.SMALL_COLUMN);
        table.getColumnModel().getColumn(8).setPreferredWidth(Dimensions.MEDIUM_COLUMN);
    }
}
