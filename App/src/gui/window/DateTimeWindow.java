package gui.window;

import constants.Dimensions;
import constants.TextConstants;
import database.worker.MatchController;
import listener.DateListener;

import javax.swing.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeWindow extends JFrame {

    private JButton submitButton;
    private JLabel messageLabel;
    private JTextField dateTextField;

    private final int matchId;
    private DateListener dateListener;

    public DateTimeWindow(int matchId, DateListener dateListener) {
        this.matchId = matchId;
        this.dateListener = dateListener;

        initComponents();
        setListeners();
    }

    //
    //
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        dateTextField = new JTextField(TextConstants.DATE_TIME_FORMAT);
        messageLabel = new JLabel();
        submitButton = new JButton(TextConstants.BUTTON_SUBMIT);

        //
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(dateTextField, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH)
                        .addComponent(messageLabel)
                        .addComponent(submitButton, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(dateTextField)
                        .addGap(Dimensions.MARGIN * 3)
                        .addComponent(messageLabel)
                        .addGap(Dimensions.MARGIN * 3)
                        .addComponent(submitButton)
        );

        setTitle(TextConstants.TITLE_EDIT_DATE_TIME);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    //
    private void setListeners() {
        submitButton.addActionListener(l -> {

            if (dateTextField.getText().isEmpty() || !isValidDate(dateTextField.getText())) {
                messageLabel.setText(TextConstants.MESSAGE_INVALID_DATE_FORMAT);
                return;
            }

            int error = MatchController.updateDateTime(matchId, Timestamp.valueOf(dateTextField.getText()));

            if (error == 0) {
                dateListener.onDateChanged(Timestamp.valueOf(dateTextField.getText()));
                dispose();
            }
        });
    }

    private boolean isValidDate(String date) {

        SimpleDateFormat format = new SimpleDateFormat(TextConstants.DATE_TIME_FORMAT);

        try {
            format.parse(date);
        } catch (ParseException ex) {
            return false;
        }

        return true;
    }
}
