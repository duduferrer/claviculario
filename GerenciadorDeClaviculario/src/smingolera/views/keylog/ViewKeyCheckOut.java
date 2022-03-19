package smingolera.views.keylog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import smingolera.controller.KeyLogController;
import smingolera.controller.KeyMgmtController;
import smingolera.models.Key;
import smingolera.models.User;
import smingolera.utilities.Table;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ViewKeyCheckOut {

	private JFrame frmCheckOut;
	private JTextField txtKeyName;
	private JTextField txtRequester;
	private User user;
	private JTable table;
	private List<String> keyNameList = new ArrayList<>();
	private List<String> keyNumberList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void open(User user, JTable table) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewKeyCheckOut window = new ViewKeyCheckOut(user, table);
					window.frmCheckOut.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewKeyCheckOut(User user, JTable table) {
		this.user = user;
		this.table = table;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmCheckOut = new JFrame();
		frmCheckOut.setResizable(false);
		frmCheckOut.setAlwaysOnTop(true);
		frmCheckOut.setType(Type.POPUP);
		frmCheckOut.setBounds(100, 100, 333, 244);
		frmCheckOut.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCheckOut.getContentPane().setLayout(null);
		frmCheckOut.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 308, 191);
		frmCheckOut.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox cbKeyNumber = new JComboBox();
		cbKeyNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int keyIndex = cbKeyNumber.getSelectedIndex();
				String keyName = keyNameList.get(keyIndex) ;
				txtKeyName.setText(keyName);
			}
		});		
		KeyMgmtController keyMgmtController = new KeyMgmtController();
		List<Key> keyList = new ArrayList<>(); 
		keyList = keyMgmtController.keyListGenerator();
		for (Key key: keyList) {
			String keyNumber = key.getKeyNumber();
			String keyName = key.getKeyDescription();
			keyNumberList.add(keyNumber);
			keyNameList.add(keyName);
		}
		cbKeyNumber.setModel(new DefaultComboBoxModel(keyNumberList.toArray()));
		cbKeyNumber.setBounds(10, 27, 122, 22);
		panel.add(cbKeyNumber);
		
		txtKeyName = new JTextField();
		txtKeyName.setEditable(false);
		String keyName = keyNameList.get(0);
		txtKeyName.setText(keyName);
		txtKeyName.setBounds(10, 63, 287, 20);
		panel.add(txtKeyName);
		txtKeyName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Selecione uma Chave:");
		lblNewLabel.setBounds(10, 2, 273, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Solicitante:");
		lblNewLabel_1.setBounds(10, 94, 89, 14);
		panel.add(lblNewLabel_1);
		
		txtRequester = new JTextField();
		txtRequester.setBounds(10, 118, 288, 20);
		panel.add(txtRequester);
		txtRequester.setColumns(10);
		
		JButton btnRegister = new JButton("Registrar");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String requesterName = txtRequester.getText();
				String keyNumber = (String)cbKeyNumber.getSelectedItem();
				String username = user.getUser();
				KeyLogController keyLogController = new KeyLogController();
				if(keyLogController.keyCheckout(username, requesterName, keyNumber)==true) {
					JOptionPane.showMessageDialog(panel, "Registro Incluído");
					Table.keysOutTableDataGenerator(table);
				}else {
					JOptionPane.showMessageDialog(panel, "Falha ao Incluir Registro");
				}
			}
		});
		btnRegister.setBounds(107, 157, 89, 23);
		panel.add(btnRegister);
	}
}
