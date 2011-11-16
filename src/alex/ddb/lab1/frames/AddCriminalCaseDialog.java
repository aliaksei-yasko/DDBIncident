/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alex.ddb.lab1.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Admin
 */
public class AddCriminalCaseDialog extends JDialog{
    private JList articlesList;
    private JFormattedTextField criminalDateField;
    private JTextArea criminalNoteField;
    private JList addedArticlesList;
    private boolean ok = false;
    private Map<String, Integer> articles;
    private DefaultListModel addedArticlesListModel;
    private DefaultListModel articlesListModel;

    public AddCriminalCaseDialog(JFrame owner, Map<String, Integer> aArticles){
        super(owner, "Add person to incident", true);

        this.setSize(350, 600);
        this.setLocation(200, 100);

        articles = aArticles;

        addedArticlesList = new JList();
        articlesList = new JList();
        articlesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addedArticlesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        articlesList.addMouseListener(new MouseClickedHandler());
        addedArticlesList.addMouseListener(new MouseClickedHandler());
        addedArticlesListModel = new DefaultListModel();
        articlesListModel = new DefaultListModel();
        addedArticlesList.setModel(addedArticlesListModel);
        articlesList.setModel(articlesListModel);

        criminalDateField = new JFormattedTextField(DateFormat.getDateInstance());
        criminalDateField.setInputVerifier(new FormattesTextFieldVerifier());
        criminalNoteField = new JTextArea(10, 50);

        JScrollPane textScrollPane = new JScrollPane(criminalNoteField);
        JScrollPane addedArticlesScroll = new JScrollPane(addedArticlesList);
        JScrollPane articlesScroll = new JScrollPane(articlesList);

        Set<String> keys = articles.keySet();
        for(String key : keys){
            articlesListModel.addElement(key);
        }

        Box vBox = Box.createVerticalBox();
        vBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelL1 = new JPanel();
        panelL1.add(new JLabel("Criminal date: "));
        vBox.add(panelL1);
        vBox.add(criminalDateField);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL2 = new JPanel();
        panelL2.add(new JLabel("Existing articles: "));
        vBox.add(panelL2);
        vBox.add(articlesScroll);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL3 = new JPanel();
        panelL3.add(new JLabel("Criminal case articles: "));
        vBox.add(panelL3);
        vBox.add(addedArticlesScroll);
        vBox.add(Box.createVerticalStrut(10));
        JPanel panelL4 = new JPanel();
        panelL4.add(new JLabel("Criminal note: "));
        vBox.add(panelL4);
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

    public Date getCriminalDate() throws ParseException{
        String date = criminalDateField.getText();
        DateFormat format = DateFormat.getDateInstance();
        java.util.Date inputDate = format.parse(date);
        return new Date(inputDate.getTime());
    }

    public String getCriminalNote(){
        return criminalNoteField.getText();
    }

    public int[] getArticleKeys(){
        int listSize = addedArticlesListModel.getSize();
        String[] elements = new String[listSize];
        int[] keys = new int[listSize];
        for(int i = 0; i < listSize; i++){
            elements[i] = (String)addedArticlesListModel.getElementAt(i);
        }
        int i = 0;
        for(String element : elements){
            keys[i] = articles.get(element);
            i++;
        }

        return keys;
    }


    private class MouseClickedHandler extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2){
                if (addedArticlesList == e.getSource()){
                    int selectedIndex = addedArticlesList.getSelectedIndex();
                    addedArticlesListModel.remove(selectedIndex);
                }
                if (articlesList == e.getSource()){
                    int selectedIndex = articlesList.getSelectedIndex();
                    String selectedElement = (String)articlesListModel.getElementAt(selectedIndex);
                    if  (!addedArticlesListModel.contains(selectedElement)){
                        addedArticlesListModel.addElement(selectedElement);
                    } else{
                        JOptionPane.showMessageDialog(null, "Данная статья уже добавлена к делу.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

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
