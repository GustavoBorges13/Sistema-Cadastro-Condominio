package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JButton;
import controller.ConexaoBD;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipalCondominio extends JFrame {
	private static final long serialVersionUID = 5829131245819973481L;
	private JPanel contentPane;

	// Instancia a class ConexaoBD responsavel pela coenxao com o banco de
	// dados!!!!!!!
	ConexaoBD conectar = new ConexaoBD();

	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) {
	        System.out.println("Error setting native LAF: " + e);
	    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalCondominio frame = new TelaPrincipalCondominio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Cria o frame
	public TelaPrincipalCondominio() {
		initComponents();
		conectar.conexao();
	}

	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1184, 22);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("Sair");
		mnNewMenu_2.setIcon(new ImageIcon(TelaPrincipalCondominio.class.getResource("/imagens/exit.png")));
		menuBar.add(mnNewMenu_2);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 21, 1184, 123);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipalCondominio.class.getResource("/imagens/Logo-CondominioV3.png")));
		lblNewLabel.setBounds(10, 11, 318, 112);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(105, 105, 105));
		panel_1.setBounds(0, 144, 1184, 22);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 177, 1064, 473);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setRequestFocusEnabled(false);
		separator.setForeground(Color.GRAY);
		separator.setBounds(23, 47, 1020, 9);
		panel_2.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Solicitações:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(23, 30, 90, 14);
		panel_2.add(lblNewLabel_1);

		JButton jButton_Cadastrar_Usuario = new JButton("Cadastra Usuário");
		jButton_Cadastrar_Usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Botao de Cadastrar Usuario na Tela principal
				TelaUsuario tela = new TelaUsuario();
				tela.setVisible(true);
				 
			}
		});
		jButton_Cadastrar_Usuario.setFocusable(false);
		jButton_Cadastrar_Usuario.setRequestFocusEnabled(false);
		jButton_Cadastrar_Usuario.setIcon(new ImageIcon(TelaPrincipalCondominio.class.getResource("/imagens/User.png")));
		jButton_Cadastrar_Usuario.setBounds(24, 77, 190, 52);
		panel_2.add(jButton_Cadastrar_Usuario);

		JButton jButton_Cadastrar_Proprietario = new JButton("Cadastra Proprietário");
		jButton_Cadastrar_Proprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarProprietarios tela = new CadastrarProprietarios();
				tela.setVisible(true);
				 
			}
		});
		jButton_Cadastrar_Proprietario.setFocusable(false);
		jButton_Cadastrar_Proprietario
				.setIcon(new ImageIcon(TelaPrincipalCondominio.class.getResource("/imagens/addUSer.png")));
		jButton_Cadastrar_Proprietario.setBounds(249, 77, 214, 52);
		panel_2.add(jButton_Cadastrar_Proprietario);

		JButton jButton_Cadastrar_Veiculo = new JButton("Cadastra Veículo");
		jButton_Cadastrar_Veiculo.setFocusable(false);
		jButton_Cadastrar_Veiculo.setIcon(new ImageIcon(TelaPrincipalCondominio.class.getResource("/imagens/veiculo.png")));
		jButton_Cadastrar_Veiculo.setBounds(500, 77, 197, 52);
		panel_2.add(jButton_Cadastrar_Veiculo);
	}
}
