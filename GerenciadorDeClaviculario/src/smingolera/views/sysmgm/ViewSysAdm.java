package smingolera.views.sysmgm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import smingolera.controller.UserController;
import smingolera.models.User;
import smingolera.utilities.Conversion;
import smingolera.utilities.Table;
import smingolera.utilities.enums.PermissionLevelENUM;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import javax.swing.JButton;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class ViewSysAdm {

	private JFrame frmViewSysAdm;
	private JTextField txtName;
	private JPasswordField txtPass;
	private JTextField txtUser;
	private JTable table;
	@SuppressWarnings("unused")
	private User user;

	/**
	 * Launch the application.
	 */
	public static void open(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSysAdm window = new ViewSysAdm(user);
					window.frmViewSysAdm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewSysAdm(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmViewSysAdm = new JFrame();
		frmViewSysAdm.setResizable(false);
		frmViewSysAdm.setBounds(100, 100, 386, 424);
		frmViewSysAdm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmViewSysAdm.getContentPane().setLayout(null);
		frmViewSysAdm.setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 356, 377);
		frmViewSysAdm.getContentPane().add(tabbedPane);
		
		JPanel pnlRegistration = new JPanel();
		tabbedPane.addTab("Cadastro Usu\u00E1rio", null, pnlRegistration, null);
		pnlRegistration.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 10, 103, 14);
		pnlRegistration.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setBounds(132, 3, 189, 20);
		pnlRegistration.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Usu\u00E1rio:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 33, 103, 14);
		pnlRegistration.add(lblNewLabel_2);
		
		txtUser = new JTextField();
		txtUser.setBounds(132, 27, 189, 20);
		pnlRegistration.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Senha:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 53, 103, 14);
		pnlRegistration.add(lblNewLabel);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(132, 50, 189, 20);
		pnlRegistration.add(txtPass);
		
		JLabel lblNewLabel_3 = new JLabel("Nivel de Permiss\u00E3o:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 83, 122, 14);
		pnlRegistration.add(lblNewLabel_3);
		
		JLabel label = new JLabel("");
		label.setBounds(429, 47, 0, 0);
		pnlRegistration.add(label);
		
		JComboBox cbPermissionLvl = new JComboBox();
		cbPermissionLvl.setModel(new DefaultComboBoxModel(PermissionLevelENUM.values()));
		cbPermissionLvl.setBounds(132, 75, 189, 22);
		pnlRegistration.add(cbPermissionLvl);

	

		UserController userController = new UserController();
		JButton btnSubmit = new JButton("Cadastrar");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("CLicado");
				String username = txtUser.getText().toUpperCase();
				String pass = new String(txtPass.getPassword()).toUpperCase();
				String name = txtName.getText().toUpperCase();
				String selectedPermissionDescription = cbPermissionLvl.getSelectedItem().toString();
				int permissionLevelID = Conversion.permissionLevel_StrIntoInt(selectedPermissionDescription) ;
				User user = new User(username, name, pass, permissionLevelID);
				if (userController.submit(user)) {
					JOptionPane.showMessageDialog(frmViewSysAdm, "Usuário Cadastrado com Sucesso!");
				}else {
					JOptionPane.showMessageDialog(frmViewSysAdm, "Não foi possível cadastrar o usuário!");
				}
				txtUser.setText("");
				txtName.setText("");
				txtPass.setText("");
				DefaultTableModel tableModel = Table.userTableModelGenerator();
				table.setModel(tableModel);
				Table.userTableDataGenerator(table);


			}
		});
		btnSubmit.setBounds(101, 123, 112, 42);
		pnlRegistration.add(btnSubmit);
		
		JPanel pnlUserList = new JPanel();
		tabbedPane.addTab("Lista Usu\u00E1rios", null, pnlUserList, null);
		pnlUserList.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 331, 289);
		pnlUserList.add(panel);
		panel.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 331, 289);
		panel.add(scrollPane);
		table = new JTable();		
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel tableModel = Table.userTableModelGenerator();
		table.setModel(tableModel);
		Table.userTableDataGenerator(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(92);
		table.getColumnModel().getColumn(1).setPreferredWidth(182);
		table.getColumnModel().getColumn(2).setPreferredWidth(114);
		
		JButton btnDelete = new JButton("Excluir");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username;
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(scrollPane, "Selecione um registro de usuário");
				}
				username = (String) table.getValueAt(row, 0);
				String deleteUserMessage = "Tem certeza que deseja EXCLUIR o usuário: " + username;
				int input = JOptionPane.showConfirmDialog(scrollPane, deleteUserMessage, "EXCLUSÃO DE REGISTRO", JOptionPane.YES_NO_OPTION);
				if(input == 0) {
					userController.delete(username);
				}
				DefaultTableModel tableModel = Table.userTableModelGenerator();
				table.setModel(tableModel);
				Table.userTableDataGenerator(table);
			}
		});
		btnDelete.setBounds(123, 311, 122, 38);
		pnlUserList.add(btnDelete);
	}
}
