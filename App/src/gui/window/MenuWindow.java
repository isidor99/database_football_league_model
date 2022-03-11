package gui.window;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;

import javax.swing.*;
import java.awt.*;

public class MenuWindow extends JFrame {

    private JButton leagueButton;
    private JButton addClubButton;

    public MenuWindow() {
        initComponents();
        setListeners();
    }

    /**
     *
     */
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        GridLayout gridLayout = new GridLayout(4, 1);

        gridLayout.setVgap(Dimensions.MARGIN);
        gridLayout.setHgap(Dimensions.MARGIN);

        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel menuLabel = new JLabel(TextConstants.MENU);
        menuLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 18));

        JPanel gridPanel = new JPanel(gridLayout);

        // init button
        leagueButton = new JButton(TextConstants.BUTTON_LEAGUES);
        addClubButton = new JButton(TextConstants.BUTTON_ADD_CLUB);

        // add buttons on panel
        gridPanel.add(leagueButton);
        gridPanel.add(addClubButton);

        // layout
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(menuLabel)
                        .addComponent(gridPanel, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(menuLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gridPanel)
        );

        setTitle(TextConstants.TITLE_MENU);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     *
     */
    private void setListeners() {

        leagueButton.addActionListener(l -> {
            new LeagueListWindow();
        });

        addClubButton.addActionListener(l -> {
            new AddClubWindow();
        });
    }
}
