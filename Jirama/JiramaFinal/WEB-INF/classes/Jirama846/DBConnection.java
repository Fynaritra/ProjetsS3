package connexion;
import java.sql.*;
public class DBConnection{
	Connection con;
	Statement state;
	
///Recuperer Connection
	public Connection getConnect(){
		return this.con;
	}
	public Statement getStat(){
		return this.state;
	}
	
///Supprimer Connection
	public DBConnection(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jirama","jirama");
			state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
			this.getStat().execute("alter session set nls_date_format = 'YYYY-MM-DD'");
			// this.getStat().execute("alter session set nls_timestamp_format = 'YYYY-MM-DD HH24:MI:SS'");
		}
		catch(Exception e){
			System.out.println("Error connexion");
			e.printStackTrace();
		}
	}
	public void delConnect() throws Exception{
		this.state.close();
		this.con.close();
	}
}