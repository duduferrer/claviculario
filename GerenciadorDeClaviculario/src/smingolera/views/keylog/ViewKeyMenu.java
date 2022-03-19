package smingolera.views.keylog;

import java.awt.EventQueue;

import javax.swing.JFrame;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import smingolera.controller.KeyLogController;
import smingolera.models.User;
import smingolera.utilities.Table;

import javax.swing.ListSelectionModel;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewKeyMenu {

	private JFrame frmKeyLog;
	private JTable table;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void open(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewKeyMenu window = new ViewKeyMenu(user);
					window.frmKeyLog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewKeyMenu(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmKeyLog = new JFrame();
		frmKeyLog.setBounds(100, 100, 953, 429);
		frmKeyLog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKeyLog.getContentPane().setLayout(null);
		frmKeyLog.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 272, 387);
		frmKeyLog.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnManageKeys = new JButton("Gerenciar Lista de Chaves");
		btnManageKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewKeyRegister.open(user);
				frmKeyLog.dispose();
			}
		});
		btnManageKeys.setBounds(10, 298, 252, 62);
		panel.add(btnManageKeys);		
		JButton btnKeyCheckout = new JButton("Retirada de Chave");
		btnKeyCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewKeyCheckOut.open(user, table);
			}
		});
		btnKeyCheckout.setBounds(10, 11, 252, 62);
		panel.add(btnKeyCheckout);
		
		JButton btnKeyLog = new JButton("Hist\u00F3rico de Chaves");
		btnKeyLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewKeyHistory.open();
			}
		});
		btnKeyLog.setBounds(10, 159, 252, 62);
		panel.add(btnKeyLog);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(282, 11, 645, 355);
		frmKeyLog.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 47, 645, 227);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Nro Chave", "Se\u00E7\u00E3o", "Solicitante", "Data da Retirada"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(0).setMinWidth(5);
		Table.keysOutTableDataGenerator(table);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		
		JLabel lblNewLabel = new JLabel("CHAVES FORA:");
		lblNewLabel.setBounds(250, 11, 150, 25);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblNewLabel);
		
		JButton btnCheckin = new JButton("Devolvida");
		btnCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyLogController keyLogController = new KeyLogController();
				String keyNumber;
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(scrollPane, "Selecione uma chave!");
				}
				keyNumber = (String) table.getValueAt(row, 0);
				if(keyLogController.keyCheckin(keyNumber, user)==true) {
					JOptionPane.showMessageDialog(scrollPane, "Chave "+keyNumber+" Devolvida!");
					Table.keysOutTableDataGenerator(table);
					
				}
				
			}
		});
		btnCheckin.setBounds(288, 321, 89, 23);
		panel_1.add(btnCheckin);
	}
}
