package gui.window;

import constants.Constants;
import constants.Dimensions;
import constants.TextConstants;
import database.worker.CityController;
import database.worker.ClubController;
import model.City;
import model.Club;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddClubWindow extends JFrame {

    private JTextField clubNameField;
    private JTextField stadiumNameField;
    private JTextField stadiumCapacityField;
    private JComboBox cityComboBox;
    private JButton submitButton;

    private final ArrayList<City> cities;

    public AddClubWindow() {

        this.cities = CityController.getCities();

        initComponents();
        setListeners();
    }

    /**
     *
     */
    private void initComponents() {

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel addClubLabel = new JLabel(TextConstants.ADD_CLUB);
        addClubLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 18));

        JLabel clubName = new JLabel(TextConstants.CLUB_NAME);
        JLabel stadiumName = new JLabel(TextConstants.STADIUM_NAME);
        JLabel stadiumCapacity = new JLabel(TextConstants.STADIUM_CAPACITY);
        JLabel clubCity = new JLabel(TextConstants.CITY);

        //
        clubNameField = new JTextField();
        stadiumNameField = new JTextField();
        stadiumCapacityField = new JTextField();
        cityComboBox = new JComboBox(cities.toArray());

        //
        submitButton = new JButton(TextConstants.BUTTON_SUBMIT);

        //
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(addClubLabel)
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(clubName)
                                                        .addGap(Dimensions.MARGIN)
                                                        .addComponent(stadiumName)
                                                        .addGap(Dimensions.MARGIN)
                                                        .addComponent(stadiumCapacity)
                                                        .addGap(Dimensions.MARGIN)
                                                        .addComponent(clubCity)
                                        )
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(clubNameField, Dimensions.TEXT_FIELD_WIDTH, Dimensions.TEXT_FIELD_WIDTH, Dimensions.TEXT_FIELD_WIDTH)
                                                        .addGap(Dimensions.MARGIN)
                                                        .addComponent(stadiumNameField, Dimensions.TEXT_FIELD_WIDTH, Dimensions.TEXT_FIELD_WIDTH, Dimensions.TEXT_FIELD_WIDTH)
                                                        .addGap(Dimensions.MARGIN)
                                                        .addComponent(stadiumCapacityField, Dimensions.TEXT_FIELD_WIDTH, Dimensions.TEXT_FIELD_WIDTH, Dimensions.TEXT_FIELD_WIDTH)
                                                        .addGap(Dimensions.MARGIN)
                                                        .addComponent(cityComboBox, Dimensions.TEXT_FIELD_WIDTH, Dimensions.TEXT_FIELD_WIDTH, Dimensions.TEXT_FIELD_WIDTH)
                                        )
                        )
                        .addComponent(submitButton, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH, Dimensions.BUTTON_WIDTH)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(addClubLabel)
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(clubName)
                                        .addComponent(clubNameField)
                        )
                        .addGap(Dimensions.MARGIN)
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(stadiumName)
                                        .addComponent(stadiumNameField)
                        )
                        .addGap(Dimensions.MARGIN)
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(stadiumCapacity)
                                        .addComponent(stadiumCapacityField)
                        )
                        .addGap(Dimensions.MARGIN)
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(clubCity)
                                        .addComponent(cityComboBox)
                        )
                        .addGap(Dimensions.MARGIN * 3)
                        .addComponent(submitButton)
        );

        setTitle(TextConstants.TITLE_ADD_CLUB);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     *
     */
    private void setListeners() {
        submitButton.addActionListener(l -> {

            if (!textFieldsEmpty() && cityComboBox.getSelectedItem() != null) {

                String selectedItem = cityComboBox.getSelectedItem().toString();
                String[] cityCountry = selectedItem.split(", ");

                City city =
                        cities.stream()
                                .filter(c -> c.getCityName().equals(cityCountry[0]) &&
                                        c.getCountryName().equals(cityCountry[1]))
                                .findFirst()
                                .orElse(null);

                if (city == null)
                    return;

                Club club = new Club();

                club.setName(clubNameField.getText());
                club.setStadiumName(stadiumNameField.getText());
                club.setStadiumCapacity(Integer.parseInt(stadiumCapacityField.getText()));
                club.setCity(cityCountry[0]);
                club.setCountry(cityCountry[1]);
                club.setCityId(city.getCityId());

                ClubController.addNewClub(club);

                dispose();
            }
        });
    }

    /**
     *
     *
     */
    private boolean textFieldsEmpty() {
        return clubNameField.getText().isEmpty() || stadiumNameField.getText().isEmpty()
                || stadiumCapacityField.getText().isEmpty();
    }
}
