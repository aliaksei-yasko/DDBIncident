/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alex.ddb.lab1.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class AddPersonDialog extends JDialog{

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JFormattedTextField burnDateField;
    private JFormattedTextField passportNumberField;
    private JTextField addressField;
    private JFormattedTextField courtRateField;
    private boolean ok;

    public AddPersonDialog(JFrame owner){
        super(owner, "Add person", true);

        this.setSize(250, 400);
        this.setLocation(200, 200);

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        addressField = new JTextField();

        burnDateField = new JFormattedTextField(DateFormat.getDateInstance());
        burnDateField.setInputVerifier(new FormattesTextFieldVerifier());
        passportNumberField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        passportNumberField.setInputVerifier(new FormattesTextFieldVerifier());
        courtRateField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        courtRateField.setInputVerifier(new FormattesTextFieldVerifier());

        Box vBox = Box.createVerticalBox();
        vBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelL1 = new JPanel();
        panelL1.add(new JLabel("First name: "));
        vBox.add(panelL1);
        vBox.add(firstNameField);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL2 = new JPanel();
        panelL2.add(new JLabel("Last name: "));
        vBox.add(panelL2);
        vBox.add(lastNameField);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL3 = new JPanel();
        panelL3.add(new JLabel("Burn date: "));
        vBox.add(panelL3);
        vBox.add(burnDateField);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL4 = new JPanel();
        panelL4.add(new JLabel("Passport number: "));
        vBox.add(panelL4);
        vBox.add(passportNumberField);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL5 = new JPanel();
        panelL5.add(new JLabel("Address: "));
        vBox.add(panelL5);
        vBox.add(addressField);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL6 = new JPanel();
        panelL6.add(new JLabel("Court rate: "));
        vBox.add(panelL6);
        vBox.add(courtRateField);
        vBox.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");
        okButton.addActionListener(new ButtonHandler());
        cancelButton.addActionListener(new ButtonHandler());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        this.add(vBox, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public boolean getOk(){
        return ok;
    }

    public String getFirstName(){
        return firstNameField.getText();
    }

    public String getLastName(){
        return lastNameField.getText();
    }

    public String getAddress(){
        return addressField.getText();
    }

    public int getPassportNumber(){
        return Integer.parseInt(passportNumberField.getValue().toString());
    }

    public int getCourtRate(){
         return Integer.parseInt(courtRateField.getValue().toString());
    }

    public Date getBurnDate() throws ParseException{
        String date = burnDateField.getText();
        DateFormat format = DateFormat.getDateInstance();
        java.util.Date inputDate = format.parse(date);
        return new Date(inputDate.getTime());
    }

    private class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton)e.getSource();

            if (clickedButton.getText().equals("Cancel")){
                setVisible(false);
                return;
            } else {
                ok = true;
                setVisible(false);
                return;
            }
        }
    }

    /**
     * Handler for verifying input formated data
    */
    private class FormattesTextFieldVerifier extends InputVerifier{

        /**
         * Function that verify input data in text field
         * @param input - verifying component
         * @return - result verifying value
         */
        @Override
        public boolean verify(JComponent input) {
            JFormattedTextField textF = (JFormattedTextField)input;
            if(textF.isEditValid()){
                textF.setBackground(Color.WHITE);
                return true;
            }else{
                textF.setBackground(Color.pink);
                return false;
            }
        }

    }
}
