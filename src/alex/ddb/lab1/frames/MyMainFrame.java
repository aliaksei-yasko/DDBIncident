/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alex.ddb.lab1.frames;

import alex.ddb.lab1.actions.BaseUpdater;
import alex.ddb.lab1.actions.BaseViewer;
import alex.ddb.lab1.table.ResultSetTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MyMainFrame extends JFrame{
    
    private ResultSetTableModel tableModel;
    private JTable table;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private JComboBox tableNames;
    private JScrollPane scrollPane;
    private JMenu incidentMenu;
    private JMenu personMenu;
    private JMenu criminalCaseMenu;
    private JMenu articleMenu;
    private JMenu specificOperationsMenu;
    private JFrame thisFrame;
    
    public MyMainFrame(){
        this.setTitle("Incidents");
        this.setSize(900, 400);
        this.setLocation(100, 200);

        thisFrame = this;
        
        tableNames = new JComboBox();
        tableNames.addActionListener(new TableNamesHandler());

        //Creating menu
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        incidentMenu = new JMenu("Incident");
        menuBar.add(incidentMenu);
        JMenuItem viewIncidentMenuItem = new JMenuItem("View information");
        viewIncidentMenuItem.addActionListener(new ViewIncidentMenuItemHandler());
        incidentMenu.add(viewIncidentMenuItem);
        JMenuItem addIncidentMenuItem = new JMenuItem("Add incident");
        addIncidentMenuItem.addActionListener(new AddIncidentMenuItemHandler());
        incidentMenu.add(addIncidentMenuItem);
        JMenuItem deleteIncidentMenuItem = new JMenuItem("Delete incident");
        deleteIncidentMenuItem.addActionListener(new DeleteIncidentdHandler());
        incidentMenu.add(deleteIncidentMenuItem);
        JMenuItem connectWithPersonMenuItem = new JMenuItem("Add person");
        connectWithPersonMenuItem.addActionListener(new ConnectWithPersonHandler());
        incidentMenu.add(connectWithPersonMenuItem);
        JMenuItem addCtiminalCaseMenuItem = new JMenuItem("Add criminal case");
        addCtiminalCaseMenuItem.addActionListener(new AddCriminalCaseHandler());
        incidentMenu.add(addCtiminalCaseMenuItem);

        personMenu = new JMenu("Person");
        menuBar.add(personMenu);
        JMenuItem viewPersonMenuItem = new JMenuItem("View information");
        viewPersonMenuItem.addActionListener(new ViewPersonHandler());
        personMenu.add(viewPersonMenuItem);
        JMenuItem addPersonMenuItem = new JMenuItem("Add person");
        addPersonMenuItem.addActionListener(new AddPersonHandler());
        personMenu.add(addPersonMenuItem);
        JMenuItem deletePersonMenuItem = new JMenuItem("Delete person");
        deletePersonMenuItem.addActionListener(new DeletePersonHandler());
        personMenu.add(deletePersonMenuItem);

        criminalCaseMenu = new JMenu("Criminal case");
        menuBar.add(criminalCaseMenu);
        JMenuItem viewCriminalCaseMenuItem = new JMenuItem("View information");
        viewCriminalCaseMenuItem.addActionListener(new ViewCriminalCaseHandler());
        criminalCaseMenu.add(viewCriminalCaseMenuItem);
        JMenuItem deleteCriminalCaseMenuItem = new JMenuItem("Delete criminal case");
        deleteCriminalCaseMenuItem.addActionListener(new DeleteCriminalCaseHandler());
        criminalCaseMenu.add(deleteCriminalCaseMenuItem);

        articleMenu = new JMenu("Article");
        menuBar.add(articleMenu);
        JMenuItem viewArticleMenuItem = new JMenuItem("View information");
        viewArticleMenuItem.addActionListener(new ViewArticleHandler());
        articleMenu.add(viewArticleMenuItem);
        JMenuItem addArticleMenuItem = new JMenuItem("Add article");
        addArticleMenuItem.addActionListener(new AddArticleHandler());
        articleMenu.add(addArticleMenuItem);
        JMenuItem deleteArticleMenuItem = new JMenuItem("Delete article");
        deleteArticleMenuItem.addActionListener(new DeleteArticleHandler());
        articleMenu.add(deleteArticleMenuItem);

        specificOperationsMenu = new JMenu("Specific operations");
        menuBar.add(specificOperationsMenu);
        JMenuItem incidentsInTimeIntervalMenuItem = new JMenuItem("Incident quantity");
        incidentsInTimeIntervalMenuItem.addActionListener(new IncidentsInTimeIntervalHandler());
        specificOperationsMenu.add(incidentsInTimeIntervalMenuItem);
        JMenuItem personIncidentsQuantity = new JMenuItem("Person's quantity of incidents");
        personIncidentsQuantity.addActionListener(new PersonIncidentsQuantityHandler());
        specificOperationsMenu.add(personIncidentsQuantity);

        //Creating combobox
        JPanel panel = new JPanel();
        panel.add(tableNames);
        this.add(panel, BorderLayout.NORTH);

        //Connecting to data base
        try {
            connection = this.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet tables = metaData.getTables("ALEX",
                    null, null, new String[]{"TABLE"});

            while (tables.next()) {
                tableNames.addItem(tables.getString(3));
            }
            tables.close();

            tableNames.setSelectedItem("INCIDENT");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        this.addWindowListener(new WindowHandler());
    }

    public static Connection getConnection() throws SQLException{
        String username = "alex";
        String password = "alex";
        String url = "jdbc:derby://localhost:1527/IncidentDataBase";

        return DriverManager.getConnection(url, username, password);
    }


    private class PersonIncidentsQuantityHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("PERSON")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int personNumber = (Integer)table.getValueAt(table.getSelectedRow(), 0);
                BaseViewer viewer = new BaseViewer();
                int quantuty = viewer.viewPersonIncidentsQuantity(connection, personNumber);

                JOptionPane.showMessageDialog(null, "Количество происшествий в "
                        + "которых зарегистрированно лицо: " + quantuty,
                            "Message", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class IncidentsInTimeIntervalHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("INCIDENT")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                //Getting nessesary information
                IncidentsInTimeIntervalDialog dialog = new IncidentsInTimeIntervalDialog(thisFrame);
                dialog.setVisible(rootPaneCheckingEnabled);
                if (!dialog.getOk()) {
                    return;
                }

                Date afterDate = dialog.getAfterDate();
                Date beforeDate = dialog.getBeforeDate();

                BaseViewer viewer = new BaseViewer();
                int quantuty = viewer.viewQuantityIncidentInTimeInterval(connection, afterDate, beforeDate);

                JOptionPane.showMessageDialog(null, "Количество происшествий в данный период: " + quantuty,
                            "Message", JOptionPane.INFORMATION_MESSAGE);

            } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Неверный формат даты.", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class AddCriminalCaseHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("INCIDENT")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int selectedRow = table.getSelectedRow();
                int incidentNumber = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                //Selecte all having articles
                String query = "select articlenumber, articlename from alex.article";
                PreparedStatement pStatment = connection.prepareStatement(query);
                ResultSet result = pStatment.executeQuery();

                Map<String, Integer> articles = new HashMap<String, Integer>();
                while (result.next()){
                    articles.put(result.getString("articlenumber") + ": "
                            + result.getString("articlename"),
                            Integer.valueOf(result.getInt("articlenumber")));
                }

                //Getting nessesary information
                AddCriminalCaseDialog dialog = new AddCriminalCaseDialog(thisFrame, articles);
                dialog.setVisible(rootPaneCheckingEnabled);
                if (!dialog.getOk()) {
                    return;
                }

                //Execute operation
                BaseUpdater updater = new BaseUpdater();
                boolean resultBool = updater.addCriminalCase(connection, dialog.getCriminalDate(),
                        dialog.getArticleKeys(), dialog.getCriminalNote(), incidentNumber);

                if (resultBool) {
                    JOptionPane.showMessageDialog(null, "Уголовное дело было добавлено.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    tableNames.setSelectedItem("INCIDENT");
                } else {
                    JOptionPane.showMessageDialog(null, "Уголовное дело не было добавлено.",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Неверный формат даты.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка. Уголовное дело не было добавлено.",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ConnectWithPersonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("INCIDENT")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int selectedRow = table.getSelectedRow();
                int incidentNumber = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                String query = "select personnumber, firstName, lastName, passportnumber from "
                        + "alex.person";
                PreparedStatement pStatment = connection.prepareStatement(query);
                ResultSet result = pStatment.executeQuery();
                Map<String, Integer> persons = new HashMap<String, Integer>();
                while (result.next()){
                    persons.put(result.getString("firstname") + " " + result.getString("lastname")
                            + ": " + result.getString("passportnumber"),
                            Integer.valueOf(result.getInt("personnumber")));
                }

                query = "select * from alex.status";
                pStatment = connection.prepareStatement(query);
                result = pStatment.executeQuery();
                Map<String, Integer> status= new HashMap<String, Integer>();
                while (result.next()){
                    status.put(result.getString("statusname"),
                            Integer.valueOf(result.getInt("statusnumber")));
                }

                ConnectWithPersonDialog dialog = new ConnectWithPersonDialog(thisFrame,
                        persons, status);
                dialog.setVisible(rootPaneCheckingEnabled);
                if (!dialog.getOk()) {
                    return;
                }

                BaseUpdater updater = new BaseUpdater();
                boolean resultBool = updater.addPersonToIncident(connection, dialog.getPersonKey(),
                        incidentNumber, dialog.getStatusKey());

                if (resultBool) {
                    JOptionPane.showMessageDialog(null, "Лицо было добавлено к происшествию.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Лицо не было добавлено к происшествию",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка. Лицо не было добавлено к происшествию",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ViewArticleHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(!((String)tableNames.getSelectedItem()).equals("ARTICLE")){
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int selectedRow = table.getSelectedRow();
                int articleNumber = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                BaseViewer viewer = new BaseViewer();
                String resultString = viewer.viewAllInformationAboutArticle(
                        connection, articleNumber);

                ViewAllDialog dialog = new ViewAllDialog(thisFrame, resultString);
                dialog.setVisible(true);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class DeleteArticleHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("ARTICLE")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int key = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                BaseUpdater updater = new BaseUpdater();
                boolean result = updater.deleteArticle(connection, key);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Статья была удалена", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    tableNames.setSelectedItem("ARTICLE");
                } else {
                    JOptionPane.showMessageDialog(null, "Статья не была удалена.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class AddArticleHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("ARTICLE")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                AddArticleDialog addArticle = new AddArticleDialog(thisFrame);
                addArticle.setVisible(true);
                if (!addArticle.getOk()) {
                    return;
                }

                BaseUpdater updater = new BaseUpdater();
                boolean result = updater.addArticle(connection, addArticle.getArticleNumber(),
                        addArticle.getArticleName(), addArticle.getArticleDescription());

                if (result) {
                    JOptionPane.showMessageDialog(null, "Статья была добавлена", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    tableNames.setSelectedItem("ARTICLE");
                } else {
                    JOptionPane.showMessageDialog(null, "Статья не была добавлена.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                 JOptionPane.showMessageDialog(null, "Ошибка. Статья не была добавлена.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class DeleteCriminalCaseHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("CRIMINALCASE")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int key = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                BaseUpdater updater = new BaseUpdater();
                boolean result = updater.deleteCriminalCase(connection, key);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Уголовное дело было удалено", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    tableNames.setSelectedItem("CRIMINALCASE");
                } else {
                    JOptionPane.showMessageDialog(null, "Уголовное дело не было удалено.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ViewCriminalCaseHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(!((String)tableNames.getSelectedItem()).equals("CRIMINALCASE")){
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int selectedRow = table.getSelectedRow();
                int criminalNumber = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                BaseViewer viewer = new BaseViewer();
                String resultString = viewer.viewAllInformationAboutCriminalCase(
                        connection, criminalNumber);

                ViewAllDialog dialog = new ViewAllDialog(thisFrame, resultString);
                dialog.setVisible(true);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class AddPersonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("PERSON")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                AddPersonDialog addPerson = new AddPersonDialog(thisFrame);
                addPerson.setVisible(true);
                if (!addPerson.getOk()) {
                    return;
                }
                BaseUpdater updater = new BaseUpdater();
                boolean result = updater.addPerson(connection, addPerson.getFirstName(),
                        addPerson.getLastName(), addPerson.getAddress(),
                        addPerson.getPassportNumber(), addPerson.getCourtRate(),
                        addPerson.getBurnDate());

                if (result) {
                    JOptionPane.showMessageDialog(null, "Лицо было добавлено", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    tableNames.setSelectedItem("PERSON");
                } else {
                    JOptionPane.showMessageDialog(null, "Лицо не было добавлено.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Неверный формат даты.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                 JOptionPane.showMessageDialog(null, "Ошибка. Лицо не было добавлено.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class DeletePersonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("PERSON")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int key = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                BaseUpdater updater = new BaseUpdater();
                boolean result = updater.deletePerson(connection, key);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Лицо было удалено", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    tableNames.setSelectedItem("PERSON");
                } else {
                    JOptionPane.showMessageDialog(null, "Лицо не было удалено.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private class ViewPersonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(!((String)tableNames.getSelectedItem()).equals("PERSON")){
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int selectedRow = table.getSelectedRow();
                int personNumber = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                BaseViewer viewer = new BaseViewer();
                String resultString = viewer.viewAllInformationAboutPerson(
                        connection, personNumber);

                ViewAllDialog dialog = new ViewAllDialog(thisFrame, resultString);
                dialog.setVisible(true);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteIncidentdHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("INCIDENT")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int key = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                BaseUpdater updater = new BaseUpdater();
                boolean result = updater.deleteIncident(connection, key);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Происшествие было удалено", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    tableNames.setSelectedItem("INCIDENT");
                } else {
                    JOptionPane.showMessageDialog(null, "Происшествие не было удалено.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class AddIncidentMenuItemHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!((String) tableNames.getSelectedItem()).equals("INCIDENT")) {
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                AddIncidentDialog addIncident = new AddIncidentDialog(thisFrame);
                addIncident.setVisible(true);
                if (!addIncident.getOk()) {
                    return;
                }
                BaseUpdater updater = new BaseUpdater();
                boolean result = updater.addIncedent(connection, addIncident.getDecision(), addIncident.getDescription(), addIncident.getDate());
                if (result) {
                    JOptionPane.showMessageDialog(null, "Происшествие было добавлено", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    tableNames.setSelectedItem("INCIDENT");
                } else {
                    JOptionPane.showMessageDialog(null, "Происшествие не было добавлено.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Неверный формат даты.", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                 JOptionPane.showMessageDialog(null, "Ошибка. Происшествие не было добавлено.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    private class ViewIncidentMenuItemHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                if(!((String)tableNames.getSelectedItem()).equals("INCIDENT")){
                    JOptionPane.showMessageDialog(null, "Не выбрана необходимая таблица.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Запись не выбрана.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int selectedRow = table.getSelectedRow();
                int registrationNumber = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            
                BaseViewer viewer = new BaseViewer();
                String resultString = viewer.viewAllInformationAboutIncident(
                        connection, registrationNumber);
                
                ViewAllDialog dialog = new ViewAllDialog(thisFrame, resultString);
                dialog.setVisible(true);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка при работе с базой.",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    private class WindowHandler extends WindowAdapter{

        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            try {
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    private class TableNamesHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (scrollPane != null) remove(scrollPane);
                String tableName = (String)tableNames.getSelectedItem();

                if (resultSet != null) resultSet.close();
                String query = "SELECT * FROM " + tableName;
                resultSet = statement.executeQuery(query);
                tableModel = new ResultSetTableModel(resultSet);

                table = new JTable(tableModel);
                scrollPane = new JScrollPane(table);
                add(scrollPane, BorderLayout.CENTER);

                validate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
