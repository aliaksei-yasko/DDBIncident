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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
public class AddIncidentDialog extends JDialog{

    private JFormattedTextField incidentDate;
    private JComboBox incidentDecision;
    private JTextArea incidentDescription;
    private boolean ok = false;
    private JButton okButton;
    private String decision = "Не возбуждать уголовное дело";

    public AddIncidentDialog(JFrame owner){
        super(owner, "Add incident", true);

        this.setSize(250, 350);
        this.setLocation(200, 200);

        incidentDate = new JFormattedTextField(DateFormat.getDateInstance());
        incidentDate.setInputVerifier(new FormattesTextFieldVerifier());
        incidentDecision = new JComboBox(new String[]
            {"Возбудить уголовное дело", "Не возбуждать уголовное дело"});
        incidentDescription = new JTextArea(10, 50);
        JScrollPane textScroll = new JScrollPane(incidentDescription);

        okButton = new JButton("Ok");
        okButton.addActionListener(new ButtonHandler());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ButtonHandler());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        Box vBox = Box.createVerticalBox();

        JPanel panLabel1= new JPanel();
        panLabel1.add(new JLabel("Incident date: "));
        vBox.add(panLabel1);
        vBox.add(incidentDate);
        vBox.add(Box.createVerticalStrut(20));
        //JPanel panLabel2= new JPanel();
        //panLabel2.add(new JLabel("Incident decision: "));
        //vBox.add(panLabel2);
       // vBox.add(incidentDecision);
        //vBox.add(Box.createVerticalStrut(20));
        JPanel panLabel3= new JPanel();
        panLabel3.add(new JLabel("Incident description: "));
        vBox.add(panLabel3);
        vBox.add(textScroll);
        vBox.add(Box.createVerticalStrut(20));

        this.add(vBox, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        vBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public boolean getOk(){
        return ok;
    }

    public String getDecision(){
        //return (String)incidentDecision.getSelectedItem();
        return decision;
    }

    public void setDecision(String aDecision){
        //incidentDecision.setSelectedItem(decision);
        decision = aDecision;
    }

    public String getDescription(){
        return incidentDescription.getText();
    }

    public void setDescription(String description){
        incidentDescription.setText(description);
    }

    public Date getDate() throws ParseException{
        String date = incidentDate.getText();
        DateFormat format = DateFormat.getDateInstance();
        java.util.Date inputDate = format.parse(date);
        return new Date(inputDate.getTime());
    }

    public void setDate(Date date){
        incidentDate.setValue(date);
    }

    private class ButtonHandler implements ActionListener{

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
