package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class TelaLogin extends JFrame {
	private static final long serialVersionUID = 4117881805261097307L;
	private JPanel contentPane;
	private JTextField jTextField_Login_user;
	private JPasswordField jPasswordField_Login_senha;

	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) {
	        System.out.println("Error setting native LAF: " + e);
	    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("Sair");
		mnNewMenu_2.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/exit.png")));
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 24, 533, 685);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 26));
		lblNewLabel_1.setBounds(184, 184, 161, 23);
		panel.add(lblNewLabel_1);

		jTextField_Login_user = new JTextField();
		jTextField_Login_user.setBackground(Color.WHITE);
		jTextField_Login_user.setFont(new Font("Dialog", Font.PLAIN, 26));
		jTextField_Login_user.setBounds(105, 213, 319, 43);
		panel.add(jTextField_Login_user);
		jTextField_Login_user.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 26));
		lblNewLabel_1_1.setBounds(184, 298, 161, 23);
		panel.add(lblNewLabel_1_1);

		// Acessar -> valida login
		JButton jButtonLogin = new JButton("Acessar");
		jButtonLogin.setBackground(new Color(0, 206, 209));
		jButtonLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (jTextField_Login_user.getText().equals("admin")
						&& (jPasswordField_Login_senha.getText().equals("admin"))) {
					// TelaPrincipal tela = new TelaPrincipal();
					TelaSplashCondominio tela = new TelaSplashCondominio();
					tela.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(rootPane,
							"Usuário e senha incorreto! Verifique ou recupere seu usuário e senha.");
				}
			}
		});
		jButtonLogin.setFont(new Font("Dialog", Font.BOLD, 26));
		jButtonLogin.setBounds(105, 409, 319, 43);
		panel.add(jButtonLogin);

		jPasswordField_Login_senha = new JPasswordField();
		jPasswordField_Login_senha.setBackground(Color.WHITE);
		jPasswordField_Login_senha.setFont(new Font("Dialog", Font.PLAIN, 26));
		jPasswordField_Login_senha.setBounds(105, 327, 319, 43);
		panel.add(jPasswordField_Login_senha);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 206, 209));
		panel_1.setBounds(532, 24, 552, 615);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/UFCAT_CirculoBranco.png")));
		lblNewLabel.setBounds(0, 0, 552, 615);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel(
				"Programa desenvolvido pelos alunos: Éric Regis; Gustavo Borges; Marcos Ferreira; Pedro Odden");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 590, 552, 14);
		panel_1.add(lblNewLabel_2);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(105, 105, 105));
		panel_2.setBounds(0, 0, 1184, 25);
		contentPane.add(panel_2);
	}
}
