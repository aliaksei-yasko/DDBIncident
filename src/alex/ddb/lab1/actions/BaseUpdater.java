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
public class BaseUpdater {

    public BaseUpdater(){

    }

    public boolean updateIncident(
            Connection connection, int registrationNumber, String decision, String description, Date date)
            throws SQLException{
        String query = "update alex.incident set description = ?, decision = ?, incidentdate = ?"
                + " where registrationnumber = ?";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setString(1, description);
        pStatment.setString(2, decision);
        pStatment.setDate(3, date);
        pStatment.setInt(4, registrationNumber);
        int result = pStatment.executeUpdate();

        if(result != 0) return true;
        else return false;
    }

    public boolean addIncedent(
            Connection connection, String decision, String description, Date date)
            throws SQLException{

        String query = "insert into alex.incident(description, decision, incidentdate) "
                + "values(?, ?, ?)";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setString(1, description);
        pStatment.setString(2, decision);
        pStatment.setDate(3, date);
        int result = pStatment.executeUpdate();

        if(result != 0) return true;
        else return false;
    }
    
    public boolean deleteIncident(Connection connection, int key) throws SQLException{
        String query = "delete from alex.incident where registrationnumber = ?";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, key);
        int result = pStatment.executeUpdate();

        if(result != 0) return true;
        else return false;
    }

    public boolean deletePerson(Connection connection, int key) throws SQLException{
        String query = "delete from alex.person where personnumber = ?";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, key);
        int result = pStatment.executeUpdate();

        if(result != 0) return true;
        else return false;
    }

    public boolean addPerson(Connection connection, String firstName, String lastName,
            String address, int passportNumber, int courtRate, Date burnDate) throws SQLException{
        String query = "insert into alex.person(firstname, lastname, passportnumber,"
                + " address, burndate, courtrate) "
                + "values(?, ?, ?, ?, ?, ?)";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setString(1, firstName);
        pStatment.setString(2, lastName);
        pStatment.setInt(3, passportNumber);
        pStatment.setString(4, address);
        pStatment.setDate(5, burnDate);
        pStatment.setInt(6, courtRate);
        int result = pStatment.executeUpdate();

        if(result != 0) return true;
        else return false;
    }

    public boolean deleteCriminalCase(Connection connection, int key) throws SQLException{
        String query = "delete from alex.criminalcase where criminalnumber = ?";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, key);
        int result = pStatment.executeUpdate();

        if(result != 0) return true;
        else return false;
    }

    public boolean deleteArticle(Connection connection, int key) throws SQLException{
        String query = "delete from alex.article where articlenumber = ?";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, key);
        int result = pStatment.executeUpdate();

        if(result != 0) return true;
        else return false;
    }

    public boolean addArticle(Connection connection, int articleNumber, String articleName,
            String articleDescription) throws SQLException{
        String query = "insert into alex.article(articlenumber, articlename, discription) "
                + "values(?, ?, ?)";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, articleNumber);
        pStatment.setString(2, articleName);
        pStatment.setString(3, articleDescription);
        int result = pStatment.executeUpdate();

        if(result != 0) return true;
        else return false;
    }

    public boolean addPersonToIncident(Connection connection, int personNumber,
            int registrationNumber, int statusNumber) throws SQLException{
        String query = "insert into alex.incidentinvolvment(personnumber, registrationnumber,"
                + " statusnumber) values(?, ?, ?)";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setInt(1, personNumber);
        pStatment.setInt(2, registrationNumber);
        pStatment.setInt(3, statusNumber);
        int result = pStatment.executeUpdate();

        if(result != 0) return true;
        else return false;
    }

    public boolean addCriminalCase(Connection connection, Date criminalDate, int[] articles,
            String criminalNote, int registrationNumber) throws SQLException{

        String query = "insert into alex.criminalcase(criminaldate, criminalnote,"
                + " registrationnumber) values(?, ?, ?)";
        PreparedStatement pStatment = connection.prepareStatement(query);
        pStatment.setDate(1, criminalDate);
        pStatment.setString(2, criminalNote);
        pStatment.setInt(3, registrationNumber);
        int result = pStatment.executeUpdate();

        if(result == 0) return false;

        query = "select max(criminalnumber) from alex.criminalcase";
        pStatment = connection.prepareStatement(query);
        ResultSet resultSet = pStatment.executeQuery();
        resultSet.next();
        int criminalNumber = resultSet.getInt(1);

        for(int articleNumber : articles){
            query = "insert into alex.articleapply(criminalnumber, articlenumber) values(?, ?)";
            pStatment = connection.prepareStatement(query);
            pStatment.setInt(1, criminalNumber);
            pStatment.setInt(2, articleNumber);
            result = pStatment.executeUpdate();
        }

        if(result != 0) return true;
        else return false;
    }
}
