/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alex.ddb.lab1.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class AddArticleDialog extends JDialog{

    private JTextField articleNameField;
    private JTextArea articleDescriptionField;
    private JFormattedTextField articleNumberField;
    private boolean ok = false;

    public AddArticleDialog(JFrame owner){
        super(owner, "Add article", true);

        this.setSize(250, 400);
        this.setLocation(200, 200);

        articleNameField = new JTextField();
        articleDescriptionField = new JTextArea(10, 50);
        articleDescriptionField.setLineWrap(true);
        JScrollPane textScrollPane = new JScrollPane(articleDescriptionField);

        articleNumberField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        articleNumberField.setInputVerifier(new FormattesTextFieldVerifier());

        Box vBox = Box.createVerticalBox();
        vBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelL1 = new JPanel();
        panelL1.add(new JLabel("Article number: "));
        vBox.add(panelL1);
        vBox.add(articleNumberField);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL2 = new JPanel();
        panelL2.add(new JLabel("Article name: "));
        vBox.add(panelL2);
        vBox.add(articleNameField);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL3 = new JPanel();
        panelL3.add(new JLabel("Article description: "));
        vBox.add(panelL3);
        vBox.add(textScrollPane);
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

    public int getArticleNumber(){
        return Integer.parseInt(articleNumberField.getValue().toString());
    }

    public String getArticleName(){
        return articleNameField.getText();
    }

    public String getArticleDescription(){
        return articleDescriptionField.getText();
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
