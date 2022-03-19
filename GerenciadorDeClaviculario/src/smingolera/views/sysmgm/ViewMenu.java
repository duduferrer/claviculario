package smingolera.views.sysmgm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import smingolera.models.User;
import smingolera.utilities.Verifiers;
import smingolera.views.keylog.ViewKeyMenu;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class ViewMenu {

	private JFrame frmMenu;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void show(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMenu window = new ViewMenu(user);
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public ViewMenu(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setIconImage(Toolkit.getDefaultToolkit().getImage("dtcea_cf.png"));
		frmMenu.setResizable(false);
		frmMenu.setBounds(100, 100, 590, 352);
		frmMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmMenu.getContentPane().setLayout(new BorderLayout(0, 0));
		frmMenu.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frmMenu.getContentPane().add(panel, BorderLayout.WEST);
		
		JButton btnKeyControl = new JButton("Clavicul\u00E1rio");
		btnKeyControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewKeyMenu.open(user);
			}
		});
		panel.setLayout(new MigLayout("", "[302px]", "[101][101][101.00][101][101][][][][]"));
		panel.add(btnKeyControl, "cell 0 0,grow");
		
		JButton btnVisitorsParking = new JButton("Estacionamento de Visitantes");
		btnVisitorsParking.setEnabled(false);
		panel.add(btnVisitorsParking, "cell 0 1,grow");
		
		JButton btnColabAccess = new JButton("Acesso Colaboradores");
		btnColabAccess.setEnabled(false);
		panel.add(btnColabAccess, "cell 0 2,grow");
		
		JButton btnEntranceAndExit = new JButton("Controle de Entrada e Sa\u00EDda");
		btnEntranceAndExit.setEnabled(false);
		panel.add(btnEntranceAndExit, "cell 0 3,grow");
		
		JButton btnSettings = new JButton("Gerenciamento do Sistema");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewSysAdm.open(user);
			}
		});
		panel.add(btnSettings, "cell 0 7,grow");
		if(!Verifiers.isAdmin(user)) {
			btnSettings.setEnabled(false);
		}else {
			btnSettings.setEnabled(true);
		}
		
		JButton btnExit = new JButton("Sair");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnExit, "cell 0 8,growx");
		
		JPanel panel_1 = new JPanel();
		frmMenu.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\duduf\\eclipse-workspace\\ControleDeSeguranca\\src\\dtcea_cf.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblDescUser = new JLabel("Usu\u00E1rio ativo:");
		panel_2.add(lblDescUser);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setText(user.getUser());
		panel_2.add(lblUsername);
	}

}
