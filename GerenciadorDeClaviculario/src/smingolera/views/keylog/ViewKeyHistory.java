package smingolera.views.keylog;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import smingolera.controller.KeyLogController;
import smingolera.models.KeyLog;
import smingolera.utilities.Table;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JCheckBox;


public class ViewKeyHistory {

	private JFrame frmKeyHistory;
	private JTextField txtKeyNumber;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewKeyHistory window = new ViewKeyHistory();
					window.frmKeyHistory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewKeyHistory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKeyHistory = new JFrame();
		frmKeyHistory.setResizable(false);
		frmKeyHistory.setAlwaysOnTop(true);
		frmKeyHistory.setBounds(100, 100, 787, 567);
		frmKeyHistory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKeyHistory.getContentPane().setLayout(null);
		frmKeyHistory.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(182, 11, 462, 103);
		frmKeyHistory.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("N\u00FAmero da Chave:");
		lblNewLabel.setBounds(6, 12, 109, 14);
		panel.add(lblNewLabel);
		
		txtKeyNumber = new JTextField();
		txtKeyNumber.setBounds(125, 9, 98, 20);
		panel.add(txtKeyNumber);
		txtKeyNumber.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Data Inicial:");
		lblNewLabel_1.setBounds(6, 35, 86, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Data Final:");
		lblNewLabel_2.setBounds(6, 66, 86, 14);
		panel.add(lblNewLabel_2);
		
		
		
		DatePicker dpInitialDate = new DatePicker();
		dpInitialDate.getComponentDateTextField().setEditable(false);
		dpInitialDate.setBounds(125, 33, 182, 28);
		panel.add(dpInitialDate);
		
		DatePicker dpFinalDate = new DatePicker();
		dpFinalDate.getComponentDateTextField().setEditable(false);
		dpFinalDate.setBounds(125, 64, 182, 28);
		panel.add(dpFinalDate);
		

		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBounds(369, 67, 83, 23);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyNumber = txtKeyNumber.getText();
				LocalDate initialDate = dpInitialDate.getDate();
				LocalDate finalDate = dpFinalDate.getDate();
				KeyLogController keyLogController = new KeyLogController();
				List<KeyLog> keyHistory = keyLogController.keyHistory(keyNumber, initialDate, finalDate);
				Table.keyHistoryTableDataGenerator(table, keyHistory);
			}
		});
		panel.add(btnSearch);
		
		JCheckBox ckbxAllKeys = new JCheckBox("Todas as Chaves");
		ckbxAllKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ckbxAllKeys.isSelected()) {
					txtKeyNumber.setEnabled(false);
					txtKeyNumber.setText("");
				}else {
					txtKeyNumber.setEnabled(true);
				}
			}
		});
		ckbxAllKeys.setBounds(231, 8, 182, 23);
		panel.add(ckbxAllKeys);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 125, 735, 402);
		frmKeyHistory.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(Table.keyHistoryTableModelGenerator());
		scrollPane.setViewportView(table);
	}
}
