package smingolera.views.sysmgm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import smingolera.controller.LoginController;
import smingolera.models.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class ViewChgPassword {

	private JFrame frmChgPass;
	private JPasswordField passNewPass;
	private JPasswordField passPassConfirm;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void open(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewChgPassword window = new ViewChgPassword(user);
					window.frmChgPass.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewChgPassword(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChgPass = new JFrame();
		frmChgPass.setType(Type.UTILITY);
		frmChgPass.setAlwaysOnTop(true);
		frmChgPass.setResizable(false);
		frmChgPass.setBounds(100, 100, 238, 232);
		frmChgPass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChgPass.getContentPane().setLayout(null);
		frmChgPass.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 213, 183);
		frmChgPass.getContentPane().add(panel);
		panel.setLayout(null);
		
		passNewPass = new JPasswordField();
		passNewPass.setBounds(10, 44, 181, 20);
		panel.add(passNewPass);
		
		passPassConfirm = new JPasswordField();
		passPassConfirm.setBounds(10, 101, 181, 20);
		panel.add(passPassConfirm);
		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPass = new String(passNewPass.getPassword()).toUpperCase();
				String confPass = new String(passPassConfirm.getPassword()).toUpperCase();
				LoginController loginController = new LoginController();
				if(loginController.changedPassword(user, newPass, confPass)) {
					JOptionPane.showMessageDialog(panel, "Senha Atualizada");
					frmChgPass.dispose();
				}else {
					JOptionPane.showMessageDialog(panel, "Falha ao Atualizar Senha");
					frmChgPass.dispose();
				}
			}
		});
		btnConfirm.setBounds(44, 132, 113, 29);
		panel.add(btnConfirm);
		panel.getRootPane().setDefaultButton(btnConfirm);
		
		JLabel lblNewLabel = new JLabel("Digite a nova senha:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 181, 29);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Confirme a nova senha:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 71, 181, 29);
		panel.add(lblNewLabel_1);
	}
}
