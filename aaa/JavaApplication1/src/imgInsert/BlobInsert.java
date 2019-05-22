/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imgInsert;

/**
 *
 * @author a106-23
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class BlobInsert {
 
    public static void main(String a[]){
         
        Connection con = null;
        PreparedStatement ps = null;
        InputStream is = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/tutorial?user=root&password=");
            ps = con.prepareCall("insert into myimages values (?,?)");
            ps.setString(1, "19991");
            is = new FileInputStream(new File("src/imgs/student.jpg"));
            ps.setBinaryStream(2, is);
            int count = ps.executeUpdate();
            System.out.println("Count: "+count);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(is != null) is.close();
                if(ps != null) ps.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}
