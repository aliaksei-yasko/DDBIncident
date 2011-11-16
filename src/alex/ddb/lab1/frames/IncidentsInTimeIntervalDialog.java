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

/**
 *
 * @author Admin
 */
public class IncidentsInTimeIntervalDialog extends JDialog{
    private JFormattedTextField afterDateField;
    private JFormattedTextField beforeDateField;
    private boolean ok = false;

    public IncidentsInTimeIntervalDialog(JFrame owner){
        super(owner, "Add article", true);

        this.setSize(200, 200);
        this.setLocation(200, 200);

        afterDateField = new JFormattedTextField(DateFormat.getDateInstance());
        afterDateField.setInputVerifier(new FormattesTextFieldVerifier());
        beforeDateField = new JFormattedTextField(DateFormat.getDateInstance());
        beforeDateField.setInputVerifier(new FormattesTextFieldVerifier());

        Box vBox = Box.createVerticalBox();
        vBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelL1 = new JPanel();
        panelL1.add(new JLabel("After date: "));
        vBox.add(panelL1);
        vBox.add(afterDateField);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL2 = new JPanel();
        panelL2.add(new JLabel("Before date: "));
        vBox.add(panelL2);
        vBox.add(beforeDateField);
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

    public Date getAfterDate() throws ParseException{
        String date = afterDateField.getText();
        DateFormat format = DateFormat.getDateInstance();
        java.util.Date inputDate = format.parse(date);
        return new Date(inputDate.getTime());
    }

    public Date getBeforeDate() throws ParseException{
        String date = beforeDateField.getText();
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
