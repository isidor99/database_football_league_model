package gui.component;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import model.Goal;

import javax.swing.*;
import java.awt.*;

public class GoalItemComponent extends JComponent {

    private final Goal goal;

    public GoalItemComponent(Goal goal) {
        this.goal = goal;

        initComponents();
    }

    //
    //

    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        String minuteText = goal.getMinute() + "'";
        String footballerName = goal.getFootballerName() + " " + goal.getFootballerSurname();
        String ownGoalText = goal.isOwnGoal() ? TextConstants.OWN_GOAL : "";

        JLabel minuteLabel = new JLabel(minuteText);
        JLabel clubLabel = new JLabel(goal.getClub());
        JLabel footballerNameLabel = new JLabel(footballerName);
        JLabel ownGoalLabel = new JLabel(ownGoalText);

        clubLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 12));
        footballerNameLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 12));

        //
        //
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(Dimensions.MARGIN)
                        .addComponent(minuteLabel)
                        .addGap(Dimensions.MARGIN * 3)
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(clubLabel)
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(footballerNameLabel)
                                                        .addGap(Dimensions.MARGIN)
                                                        .addComponent(ownGoalLabel)
                                        )
                        )
        );

        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(minuteLabel)
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addComponent(clubLabel)
                                        .addGap(Dimensions.MARGIN)
                                        .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(footballerNameLabel)
                                                        .addComponent(ownGoalLabel)
                                        )
                        )
        );
    }
}
