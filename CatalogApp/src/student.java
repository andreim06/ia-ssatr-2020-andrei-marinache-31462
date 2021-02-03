
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class student {
    
    public void insertUpdateDeleteStudent(char operation, Integer id, String fname, String lname, String sex, String bdate, String phone, String adress)
    {
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        // i de la insert
        if(operation == 'i')
        {
            try {
                ps = con.prepareStatement("INSERT INTO student(first_name, last_name, sex, birthday, phone, adress) VALUES (?,?,?,?,?,?)");
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, sex);
                ps.setString(4, bdate);
                ps.setString(5, phone);
                ps.setString(6, adress);
            
                if(ps.executeUpdate()>0)
                {
                    JOptionPane.showMessageDialog(null, "Studentul a fost adaugat");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
         if(operation == 'u')// update
        {
            try {
                ps = con.prepareStatement("UPDATE `student` SET `first_name`= ?, `last_name`= ?, `sex`= ?, `birthday`= ?, `phone`= ?, `adress`= ? WHERE `id` = ?");
                
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, sex);
                ps.setString(4, bdate);
                ps.setString(5, phone);
                ps.setString(6, adress);
                ps.setInt(7,id);
                if(ps.executeUpdate()>0)
                {
                    JOptionPane.showMessageDialog(null, "Informatii actualizate");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(operation == 'd')// delete
        {
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Notele acestui student vor fi sterse","Sterge student",JOptionPane.OK_CANCEL_OPTION,0);
            
            if(yesOrNo == JOptionPane.OK_OPTION)
            {
                 try {
                ps = con.prepareStatement("DELETE FROM `student` WHERE `id` =  ?");
                ps.setInt(1,id);
                if(ps.executeUpdate()>0)
                {
                    JOptionPane.showMessageDialog(null, "Studentul a fost eliminat din baza de date");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
  
        }
    }
    
    public void fillStudentJtable(JTable table, String valueToSearch)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT `id`, `first_name`, `last_name`, `sex`, `birthday`, `phone`, `adress` FROM `student`");
            //ps.setString(1, "%"+valueToSearch+"%");
            //SELECT * FROM `student` CONCAT(`first_name`, `last_name`, `phone`, `adress`)LIKE ?
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                
                model.addRow(row);
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
