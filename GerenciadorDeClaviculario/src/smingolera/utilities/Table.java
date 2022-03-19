package smingolera.utilities;

import java.sql.Connection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import smingolera.connection.ConnectionFactory;
import smingolera.dao.KeyLogDAO;
import smingolera.dao.KeyMgmtDAO;
import smingolera.dao.UserRegistrationDAO;
import smingolera.models.Key;
import smingolera.models.KeyLog;
import smingolera.models.User;

//TABLE MODELS
public abstract class Table {
	
	@SuppressWarnings("serial")
	public static DefaultTableModel userTableModelGenerator() {
	DefaultTableModel tableModel = new DefaultTableModel(
			new Object[][] {},
			new String[] { "Usu\u00E1rio", "Nome", "Nivel Permiss\u00E3o"}) 
		{
	    @Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }};
	tableModel.setNumRows(0);
	return tableModel;
	}
	
	public static void userTableDataGenerator(JTable table) {
		Connection connection = new ConnectionFactory().getConnection();
		UserRegistrationDAO userRegistrationDAO = new UserRegistrationDAO(connection);
		DefaultTableModel model = (DefaultTableModel) table.getModel() ;
		userRegistrationDAO.listOfUsers();
		for (User user: userRegistrationDAO.listOfUsers()) {
			model.addRow(new Object[] {
					user.getUser(),
					user.getName(),
					user.getLevelDesc()
			});
		}
	}
/**************************************************************************************/
	@SuppressWarnings("serial")
	public static DefaultTableModel keyTableModelGenerator() {
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"N\u00B0 da Chave", "Descri\u00E7\u00E3o"
				}) 
			{
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }};
		tableModel.setNumRows(0);
		return tableModel;
		}
	
	public static void keyTableDataGenerator(JTable table) {
		Connection connection = new ConnectionFactory().getConnection();
		KeyMgmtDAO keyControlDAO= new KeyMgmtDAO(connection);
		DefaultTableModel model = (DefaultTableModel) table.getModel() ;
		keyControlDAO.keyList();
		for (Key key: keyControlDAO.keyList()) {
			model.addRow(new Object[] {
					key.getKeyNumber(),
					key.getKeyDescription()
			});
		}
	}
	
/**************************************************************************************/
	@SuppressWarnings("serial")
	public static DefaultTableModel keysOutTableModelGenerator() {
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nro Chave", "Se\u00E7\u00E3o", "Solicitante", "Data da Retirada"
				}) 
			{
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }};
		tableModel.setNumRows(0);
		return tableModel;
		}
	
	public static void keysOutTableDataGenerator(JTable table) {
		Connection connection = new ConnectionFactory().getConnection();
		KeyLogDAO keyLogDAO= new KeyLogDAO(connection);
		DefaultTableModel model = (DefaultTableModel) table.getModel() ;
		keyLogDAO.keysOut();
		model.setNumRows(0);
		for (KeyLog record: keyLogDAO.keysOut()) {
			model.addRow(new Object[] {
					record.getKeyNumber(),
					record.getKeyDescription(),
					record.getRequesterName(),
					record.getDateCheckout(),
			});
		}
	}
	
	/**************************************************************************************/
	@SuppressWarnings("serial")
	public static DefaultTableModel keyHistoryTableModelGenerator() {
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Se\u00E7\u00E3o", "Retirada", "Devolu\u00E7\u00E3o", "Solicitante", "Entregue", "Recebido"
				}) 
			{
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }};
		tableModel.setNumRows(0);
		return tableModel;
		}
	
	public static void keyHistoryTableDataGenerator(JTable table, List<KeyLog> keyHistory) {
		DefaultTableModel model = (DefaultTableModel) table.getModel() ;
		model.setNumRows(0);
		for (KeyLog record: keyHistory) {
			model.addRow(new Object[] {
					record.getKeyDescription(),
					record.getDateCheckout(),
					record.getDateCheckin(),
					record.getRequesterName(),
					record.getUsernameCheckout(),
					record.getUsernameCheckin()
			});
		}
	}
}
