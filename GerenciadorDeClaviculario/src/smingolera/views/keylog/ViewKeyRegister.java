package smingolera.views.keylog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import smingolera.controller.KeyMgmtController;
import smingolera.models.Key;
import smingolera.models.User;
import smingolera.utilities.Table;
import smingolera.utilities.Verifiers;

import javax.swing.JButton;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class ViewKeyRegister {

	private JFrame frmKeyRegister;
	private JTable table;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void open(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewKeyRegister window = new ViewKeyRegister(user);
					window.frmKeyRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewKeyRegister(User user) {
		this.user=user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKeyRegister = new JFrame();
		frmKeyRegister.setAlwaysOnTop(true);
		frmKeyRegister.setBounds(100, 100, 518, 523);
		frmKeyRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKeyRegister.getContentPane().setLayout(null);
		frmKeyRegister.setLocationRelativeTo(null);
		
		JPanel pnlList = new JPanel();
		pnlList.setBounds(0, 0, 505, 412);
		frmKeyRegister.getContentPane().add(pnlList);
		pnlList.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 54, 452, 347);
		pnlList.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel tableModel = Table.keyTableModelGenerator();
		table.setModel(tableModel);
		Table.keyTableDataGenerator(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(96);
		table.getColumnModel().getColumn(1).setPreferredWidth(216);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Lista de Chaves");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(134, 0, 245, 50);
		pnlList.add(lblNewLabel);
		
		JPanel pnlControl = new JPanel();
		pnlControl.setBounds(0, 414, 505, 72);
		frmKeyRegister.getContentPane().add(pnlControl);
		pnlControl.setLayout(null);
		if(!Verifiers.isAdmin(user)) {
			pnlControl.setVisible(false);
		}else {
			pnlControl.setVisible(true);
		}
		
		
		JButton btnInsert = new JButton("Inserir");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewKeyInsertion.open(user,table, tableModel);
			}
		});
		btnInsert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInsert.setBounds(30, 11, 117, 46);
		pnlControl.add(btnInsert);
		
		JButton btnDelete = new JButton("Excluir");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyMgmtController keyMgmtController = new KeyMgmtController();
				String keyNumber;
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(scrollPane, "Selecione uma chave!");
				}
				keyNumber = (String) table.getValueAt(row, 0);
				String deleteKeyMessage = "Tem certeza que deseja EXCLUIR a chave: " + keyNumber;
				Key key = new Key(keyNumber);
				int input = JOptionPane.showConfirmDialog(scrollPane, deleteKeyMessage, "EXCLUSÃO DE REGISTRO", JOptionPane.YES_NO_OPTION);
				if(input == 0) {
					if(keyMgmtController.deleteKey(key)==true) {
						tableModel.setNumRows(0);
						table.setModel(tableModel);
						Table.keyTableDataGenerator(table);
						JOptionPane.showMessageDialog(table, "Chave excluída com sucesso!");
					}else {
						JOptionPane.showMessageDialog(table, "Não foi possível excluir essa chave!");
					}
				}
			}
		});
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setBounds(366, 11, 117, 46);
		pnlControl.add(btnDelete);
	}
}
