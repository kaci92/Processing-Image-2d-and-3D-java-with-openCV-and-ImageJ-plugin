package Heelo;

import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import org.apache.derby.impl.jdbc.*;
import org.apache.derby.database.*;

import javafx.scene.image.Image;
public class DB {
	public String Driver = "org.apache.derby.jdbc.EmbeddedDriver";
	 public String JDBC_URl ="jdbc:derby:Base;create =true";
	public Connection conn;
	public Statement st;
	public ResultSet rs;
	public String Nom;	
	public DB()
	{
		
		try {
			
			
				Class.forName(Driver);
				this.conn = DriverManager.getConnection(JDBC_URl);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	

	public void CreerTable()
	{
		try {
			
			this.conn.createStatement().execute("CREATE TABLE patient (Id varchar,Nom varchar,Prenom varchar,Type varchar)");
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Insertion(String id, String nom,String prenom,String type)
	{
	      try {
	    	 
			conn.createStatement().executeQuery("INSERT INTO patient(Id,Nom,Prenom,Type) Values('"+id.toString()+"','"+nom.toString()+"','"+prenom.toString()+"','"+type.toString()+"')");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	public void recherche(String Nom,String Prenom) throws SQLException
	{ 
		 try {
			st =  this.conn.createStatement();
			 rs =  st.executeQuery("Select * FROM Base where  Nom like ='%"+Nom+"%'   or Nom like ='%"+Prenom+"%'");
		while( rs.next()) 
		{
			
			rs.getString("Nom");
			rs.getString("Prenom");
			 
	
	
	
}
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	
}

}
