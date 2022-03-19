package smingolera.views.sysmgm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import smingolera.controller.LoginController;
import smingolera.models.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Cursor;

public class ViewLogin {

	private JFrame frmLogin;
	private JPasswordField txtPassword;
	private JTextField txtUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin window = new ViewLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewLogin() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setLocationByPlatform(true);
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("dtcea_cf.png"));
		frmLogin.setTitle("Entrar");
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 360, 262);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setLocationRelativeTo(null);
		JPanel pnlEntryData = new JPanel();
		pnlEntryData.setBounds(10, 11, 330, 210);
		frmLogin.getContentPane().add(pnlEntryData);
		pnlEntryData.setLayout(null);
		
		
		JLabel lblUser = new JLabel("Usu\u00E1rio:");
		lblUser.setBounds(47, 11, 207, 22);
		pnlEntryData.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setBounds(47, 30, 246, 30);
		pnlEntryData.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Senha:");
		lblPassword.setBounds(47, 84, 207, 29);
		pnlEntryData.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(47, 106, 246, 29);
		pnlEntryData.add(txtPassword);
		
		LoginController loginController = new LoginController();
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(47, 145, 111, 53);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUser.getText().toUpperCase();
				String pass = new String(txtPassword.getPassword()).toUpperCase();
				User user = new User(username,pass);
				if(loginController.login(user)) {
					User authUser = loginController.getUserData(user);
					ViewMenu.show(authUser);
					frmLogin.dispose();
				}else {
					JOptionPane.showMessageDialog(pnlEntryData, "Usuário ou Senha incorretos!");
				}
			
			}
		});
		pnlEntryData.add(btnLogin);
		pnlEntryData.getRootPane().setDefaultButton(btnLogin);
		
		JButton btnChgPass = new JButton("<html><center>Atualizar<br>Senha</center></html>");
		btnChgPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUser.getText().toUpperCase();
				String pass = new String(txtPassword.getPassword()).toUpperCase();
				User user = new User(username,pass);
				if(loginController.login(user)) {
					ViewChgPassword.open(user);
					txtUser.setText("");
					txtPassword.setText("");
				}else {
					JOptionPane.showMessageDialog(pnlEntryData, "Usuário ou Senha incorretos!");
				}
			}
		});
		btnChgPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChgPass.setBounds(182, 145, 111, 53);
		pnlEntryData.add(btnChgPass);
		

	}
}
