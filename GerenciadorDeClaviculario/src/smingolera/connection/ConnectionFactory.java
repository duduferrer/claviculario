package smingolera.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class ConnectionFactory {
	private final String URL = "jdbc:mysql://localhost:3306/dtcea_db";
	private final String USER = "root";
	private final String PASS = "010203";
	public DataSource dataSource;
	
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDS = new ComboPooledDataSource();
		comboPooledDS.setJdbcUrl(URL);
		comboPooledDS.setUser(USER);
		comboPooledDS.setPassword(PASS);
		comboPooledDS.setMaxPoolSize(10);
		this.dataSource = comboPooledDS;
	}

	
	public Connection getConnection() {
		
		try {
			return this.dataSource.getConnection();
		}catch(SQLException ex){
			throw new RuntimeException("Erro na conexao DB" + ex);
			
		}
		
	}

	
}
