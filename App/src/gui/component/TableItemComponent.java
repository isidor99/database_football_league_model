package gui.component;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import gui.window.TableWindow;
import model.Table;

import javax.swing.*;
import java.awt.*;

public class TableItemComponent extends JComponent {

    private final Table table;

    private JButton tableInfoButton;

    public TableItemComponent(Table table) {
        this.table = table;

        initComponents();
        setListeners();
    }

    /**
     *
     */
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel seasonLabel = new JLabel(table.getSeason());
        seasonLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD | Font.ITALIC, 16));

        tableInfoButton = new JButton(TextConstants.BUTTON_TABLE_INFO);
        tableInfoButton.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 12));

        //
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(seasonLabel)
                        .addComponent(tableInfoButton, Dimensions.SMALL_BUTTON_WIDTH, Dimensions.SMALL_BUTTON_WIDTH, Dimensions.SMALL_BUTTON_WIDTH)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(Dimensions.MARGIN)
                        .addComponent(seasonLabel)
                        .addComponent(tableInfoButton, Dimensions.SMALL_BUTTON_HEIGHT, Dimensions.SMALL_BUTTON_HEIGHT, Dimensions.SMALL_BUTTON_HEIGHT)
                        .addGap(Dimensions.MARGIN)
        );
    }

    /**
     *
     */
    private void setListeners() {
        tableInfoButton.addActionListener(l -> {
            new TableWindow(table);
        });
    }
}
