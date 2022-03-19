package smingolera.views.keylog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;

import smingolera.controller.KeyMgmtController;
import smingolera.models.Key;
import smingolera.models.User;
import smingolera.utilities.Table;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class ViewKeyInsertion {

	private JFrame frmKeyInsert;
	private JTextField txtDescription;
	private JTextField txtNumber;
	@SuppressWarnings("unused")
	private User user;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void open(User user, JTable table,DefaultTableModel tableModel) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewKeyInsertion window = new ViewKeyInsertion(user, table, tableModel);
					window.frmKeyInsert.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewKeyInsertion(User user, JTable table, DefaultTableModel tableModel) {
		this.user = user;
		this.table = table;
		this.tableModel = tableModel;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKeyInsert = new JFrame();
		frmKeyInsert.setType(Type.POPUP);
		frmKeyInsert.setResizable(false);
		frmKeyInsert.setAlwaysOnTop(true);
		frmKeyInsert.setBounds(100, 100, 289, 148);
		frmKeyInsert.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmKeyInsert.setLocationRelativeTo(null);
		KeyMgmtController keyControlController = new KeyMgmtController();
		JPanel panel = new JPanel();
		frmKeyInsert.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel.setBounds(10, 14, 89, 14);
		panel.add(lblNewLabel);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(109, 11, 151, 20);
		panel.add(txtDescription);
		txtDescription.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00FAmero");
		lblNewLabel_1.setBounds(10, 40, 89, 14);
		panel.add(lblNewLabel_1);
		
		txtNumber = new JTextField();
		txtNumber.setToolTipText("Numero da Chave, maximo 5 caracteres.");
		txtNumber.setBounds(109, 37, 114, 20);
		panel.add(txtNumber);
		txtNumber.setColumns(10);
		
		JButton btnInsert = new JButton("Cadastrar");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyDesc = txtDescription.getText();
				String keyNumber = txtNumber.getText();
				Key key = new Key(keyDesc, keyNumber);
				if(keyControlController.insertKey(key)) {
					txtNumber.setText("");
					txtDescription.setText("");
					JOptionPane.showMessageDialog(panel, "Chave incluída com sucesso!");
					tableModel = Table.keyTableModelGenerator();
					table.setModel(tableModel);
					Table.keyTableDataGenerator(table);
					}else {
						JOptionPane.showMessageDialog(panel, "Não foi possível inserir essa chave! Cheque o número digitado.");
					}
			}
		});
		btnInsert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInsert.setBounds(10, 68, 114, 33);
		panel.add(btnInsert);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmKeyInsert.dispose();
			}
		});
		btnCancel.setBounds(146, 68, 114, 33);
		panel.add(btnCancel);
		frmKeyInsert.getRootPane().setDefaultButton(btnInsert);

	}
}
