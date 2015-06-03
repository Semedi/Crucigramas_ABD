package DataBase;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbManager {
	
	
	
	private DataSource ds;
	
	/*
	 * @throws SQLException 
	 * @throws PropertyVetoException 
	 * @throws IOException 
	 */
	
	
	public DbManager() throws SQLException, IOException, PropertyVetoException {
		
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl(("jdbc:mysql://localhost/practica1_510"));
		cpds.setUser("UsuarioP1");
		cpds.setPassword("");
		cpds.setAcquireRetryAttempts(1);
		cpds.setAcquireRetryDelay(1);
		
		
		this.ds = cpds;		 		
	}
	
	
	public DataSource getDataSource() {
		return this.ds;
	}
	public void setMysqlDataSource(DataSource datasource) {
		this.ds = datasource;
	}

}
