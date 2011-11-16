/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alex.ddb.lab1.actions;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class BaseViewer {
    public BaseViewer(){

    }

    public int viewQuantityIncidentInTimeInterval(Connection connection, Date afterDate,
            Date beforeDate) throws SQLException{
        //Select data about article
        String query = "select count(*) from alex.incident where "
                + "incidentdate > ? and incidentdate < ?";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setDate(1, afterDate);
        pStatment.setDate(2, beforeDate);
        ResultSet result = pStatment.executeQuery();
        result.next();
        int quantity = result.getInt(1);
        return quantity;
    }

    public String viewAllInformationAboutArticle(Connection connection, int articleNumber)
            throws SQLException{
        //Select data about article
        String query = "select * from alex.article where "
                + "alex.article.articlenumber=?";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, articleNumber);
        ResultSet result = pStatment.executeQuery();
        result.next();
        String resultString = "   Номер статьи:  " + result.getString("articlenumber")
                    + "\n   Название статьи:  " + result.getString("articlename")
                    + "\n   Описание:  " + result.getString("discription");

        return resultString;
    }

    public String viewAllInformationAboutCriminalCase(Connection connection, int criminalNumber)
            throws SQLException{
        //Select data about criminal case and them articles
        String query = "select alex.criminalcase.criminaldate, alex.criminalcase.criminalnote,"
                + " alex.article.articlenumber, alex.article.articlename, alex.article.discription"
                + " from alex.criminalcase, alex.article, alex.articleapply where "
                + "alex.criminalcase.criminalnumber=? and alex.articleapply.criminalnumber = "
                + "alex.criminalcase.criminalnumber and alex.articleapply.articlenumber = "
                + "alex.article.articlenumber";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, criminalNumber);
        ResultSet result = pStatment.executeQuery();
        int i = 0;
        String resultString = "";
        while (result.next()){
            if (i == 0){
             resultString = "   Дата возбуждения дела:  " + result.getString("criminaldate")
                        + "\n   Замечание по делу:  " + result.getString("criminalnote")
                        + "\n   Статьи по которым возбуждено дело:  ";
            }
            resultString = resultString + "\n\n   Номер статьи:   " + result.getString("articlenumber")
                        + "\n   Название статьи:   " + result.getString("articlename")
                        + "\n   Описание:   " + result.getString("discription");
            i++;
        }

        return resultString;
    }

    public String viewAllInformationAboutPerson(Connection connection, int personNumber)
            throws SQLException{
        //Select data about person
        String query = "select * from alex.person where "
                + "alex.person.personnumber=?";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, personNumber);
        ResultSet result = pStatment.executeQuery();
        result.next();
        String resultString = "   Фамилия:  " + result.getString("firstname")
                    + "\n   Имя:  " + result.getString("lastname") + "\n   Дата рождения:  "
                    + result.getString("burndate") + "\n   Номер паспорта:  "
                    + result.getString("passportnumber") + "\n   Адрес:  "
                    + result.getString("address") + "\n   Количество судимостей:  "
                    + result.getString("courtrate");

        return resultString;
    }

    public String viewAllInformationAboutIncident(Connection connection, int registrationNumber)
            throws SQLException{
        //Select data about incident
        String query = "select * from alex.incident where "
                + "alex.incident.registrationnumber=?";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, registrationNumber);
        ResultSet result = pStatment.executeQuery();
        result.next();
        String resultString = "   Номер проишествия:  " + result.getString("registrationnumber")
                    + "\n   Дата:  " + result.getString("incidentdate") + "\n   Описание:  "
                    + result.getString("description") + "\n   Принятое решение:  "
                    + result.getString("decision");

        //Selecte data about criminalcase of incident
        query = "select * from alex.criminalcase, alex.articleapply, alex.article where "
                + "alex.criminalcase.registrationnumber=? and "
                + "alex.criminalcase.criminalnumber=alex.articleapply.criminalnumber and "
                + "alex.articleapply.articlenumber=alex.article.articlenumber";
        pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, registrationNumber);
        result = pStatment.executeQuery();
        int i = 0;
        while (result.next()){
            if (i == 0){
                resultString = resultString + "\n   Номер уголовного дела:  "
                        + result.getString("criminalnumber") + "\n   Дата возбуждения:  "
                        + result.getString("criminaldate") + "\n   Замечание:  "
                        + result.getString("criminalnote") + "\n   Статьи:  ";
             }
            resultString = resultString + "\n        " + result.getString("articlenumber")
                    + ": " + result.getString("articlename");

            i++;
        }

        //Select data about person involvment in incident
        query = "select firstname, statusname from alex.person, alex.incidentinvolvment, alex.status where "
                + "alex.incidentinvolvment.registrationnumber=? and "
                + "alex.incidentinvolvment.personnumber=alex.person.personnumber and "
                + "alex.incidentinvolvment.statusnumber=alex.status.statusnumber";
        pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, registrationNumber);
        result = pStatment.executeQuery();
        i = 0;
        while (result.next()){
            if (i == 0){
                resultString = resultString + "\n   Лица, учавствовавшие в проишествии:  ";
             }
            resultString = resultString + "\n        " + result.getString("firstname")
                    + " - " + result.getString("statusname");
            i++;
        }

        return resultString;
    }
}
