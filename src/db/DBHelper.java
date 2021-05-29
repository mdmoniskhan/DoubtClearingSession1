/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author monis
 */
public class DBHelper {
    static Connection con;
    static Statement st;
    static PreparedStatement getData, getDataWithMultiValues;
    static{
        try {
            //It will load our Driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CLASSICMODELS", "root", "123456789");
            st = con.createStatement();
            getDataWithMultiValues = con.prepareStatement("SELECT * FROM EMPLOYEES WHERE firstName like ? and lastName like ? ");
            getData = con.prepareStatement("SELECT * FROM EMPLOYEES WHERE EMPLOYEENUMBER like ?");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static ResultSet getDataWithMultiValues(String first, String last) throws SQLException{
        getDataWithMultiValues.setString(1, "%"+first+"%");
        getDataWithMultiValues.setString(2, "%"+last+"%");
        return getDataWithMultiValues.executeQuery();
    }
    
    public static ResultSet getData(String id) throws SQLException{
        getData.setString(1, "%"+id+"%");
        // 1003 -> 1%
        // 213 -> %1%
        // %
        return getData.executeQuery();
    }
}
