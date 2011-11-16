/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alex.ddb.lab1.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class ConnectWithPersonDialog extends JDialog{

    private JComboBox personsComboBox;
    private JComboBox statusComboBox;
    private boolean ok = false;
    private Map<String, Integer> persons;
    private Map<String, Integer> status;

    public ConnectWithPersonDialog(JFrame owner, Map<String, Integer> aPersons,
            Map<String, Integer> aStatus){
        super(owner, "Add person to incident", true);

        this.setSize(250, 200);
        this.setLocation(200, 200);

        persons = aPersons;
        status = aStatus;

        personsComboBox = new JComboBox();
        statusComboBox = new JComboBox();

        Set<String> keys = persons.keySet();
        for(String key : keys){
            personsComboBox.addItem(key);
        }

        keys = status.keySet();
        for(String key : keys){
            statusComboBox.addItem(key);
        }

        Box vBox = Box.createVerticalBox();
        vBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelL1 = new JPanel();
        panelL1.add(new JLabel("Person: "));
        vBox.add(panelL1);
        vBox.add(personsComboBox);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL2 = new JPanel();
        panelL2.add(new JLabel("Status: "));
        vBox.add(panelL2);
        vBox.add(statusComboBox);
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

    public int getPersonKey(){
        String selectedPerson = (String)personsComboBox.getSelectedItem();
        int key = persons.get(selectedPerson);
        return key;
    }

    public int getStatusKey(){
        String selectedStatus = (String)statusComboBox.getSelectedItem();
        int key = status.get(selectedStatus);
        return key;
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
}
