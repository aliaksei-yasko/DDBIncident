/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alex.ddb.lab1.table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class ResultSetTableModel extends AbstractTableModel{

    private ResultSet rs;
    private ResultSetMetaData rsMd;

    public ResultSetTableModel(ResultSet aResultSet){
        rs = aResultSet;

        try{
            rsMd = rs.getMetaData();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String getColumnName(int column){
        try {
            return rsMd.getColumnName(column + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    @Override
    public int getColumnCount(){
        try {
            return rsMd.getColumnCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getValueAt(int row, int column){
        try {
            rs.absolute(row + 1);
            return rs.getObject(column + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int getRowCount(){
        try {
            rs.last();
            return rs.getRow();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
