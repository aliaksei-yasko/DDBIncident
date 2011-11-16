/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alex.ddb.lab1.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
public class ViewAllDialog extends JDialog{

    private JTextArea viewTextArea;
    private JButton okButton;

    public ViewAllDialog(JFrame owner, String displayInformation){
        super(owner, "View information", false);

        this.setSize(400, 400);
        this.setLocation(200, 200);

        viewTextArea = new JTextArea(displayInformation);
        viewTextArea.setLineWrap(true);
        JScrollPane scrolPane = new JScrollPane(viewTextArea);

        okButton = new JButton("Ok");
        okButton.addActionListener(new OkButtonHandler());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        this.add(scrolPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }

    private class OkButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
        }

    }
}
