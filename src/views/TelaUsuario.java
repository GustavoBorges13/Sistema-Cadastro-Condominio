package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ConexaoBD;
import controller.ControleUsuario;
import modelEclipse.BeansUsuario;
import modelEclipse.ModeloTabela;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaUsuario extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5500090069044925739L;
	private JPanel contentPane;
	private JTextField JTextField_pesquisa;
	private JTextField jTextField_nome;
	private JTextField jTextField_login;
	private JPasswordField jPasswordField_senha;
	private JPasswordField jPasswordField_confirmar_senha;
	private JTable jTable_tabela_usuario;
	private JButton jButton_novo;

	ConexaoBD conex = new ConexaoBD();
	ControleUsuario control = new ControleUsuario();
	BeansUsuario mod = new BeansUsuario();
	int flag = 0;
	private JTextField jTextField_Cod_Usuario;

	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) {
	        System.out.println("Error setting native LAF: " + e);
	    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario frame = new TelaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaUsuario() {
		initComponents();

		// preencherTabela("Select * from usuarios order by usu_nome");

	}

	@SuppressWarnings("unchecked")
	public void preencherTabela(String SQL) {
		ArrayList dados = new ArrayList();
		String[] Colunas = new String[] { "CODIGO", "NOME", "LOGIN", "TIPO DE USUÁRIO", "SENHA" };

		conex.conexao();
		conex.executaSQL(SQL);

		try {
			conex.rs.first();
			do {
				dados.add(new Object[] { conex.rs.getInt("usu_cod"), conex.rs.getString("usu_nome"),
						conex.rs.getString("login_usuario"), conex.rs.getString("tipo_usuario"),
						conex.rs.getString("senha_usuario") });
			} while (conex.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Nâo existe Usuário cadastrado no sistema. Por favor cadastre um Usuário!");
		}
		ModeloTabela modelo = new ModeloTabela(dados, Colunas);
		jTable_tabela_usuario.setModel(modelo);

		// Nao deixa a aumentar a largura das colunas da tabela usando o mouse!
		jTable_tabela_usuario.getColumnModel().getColumn(0).setPreferredWidth(70);
		jTable_tabela_usuario.getColumnModel().getColumn(0).setResizable(false);
		jTable_tabela_usuario.getColumnModel().getColumn(1).setPreferredWidth(403);
		jTable_tabela_usuario.getColumnModel().getColumn(1).setResizable(false);
		jTable_tabela_usuario.getColumnModel().getColumn(2).setPreferredWidth(270);
		jTable_tabela_usuario.getColumnModel().getColumn(2).setResizable(false);
		jTable_tabela_usuario.getColumnModel().getColumn(3).setPreferredWidth(200);
		jTable_tabela_usuario.getColumnModel().getColumn(3).setResizable(false);
		jTable_tabela_usuario.getColumnModel().getColumn(4).setPreferredWidth(154);
		jTable_tabela_usuario.getColumnModel().getColumn(4).setResizable(false);

		// Nao vai reodernar os nomes e titulos do cabeçalho da tabela
		jTable_tabela_usuario.getTableHeader().setReorderingAllowed(false);

		// Nao permite aumentar na tabela as colunas
		jTable_tabela_usuario.setAutoResizeMode(jTable_tabela_usuario.AUTO_RESIZE_OFF);

		// Faz com que o usuario selecione um dado na tabela POR VEZ
		jTable_tabela_usuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		conex.desconectar();
	}

	public void initComponents() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 761);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
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
		mnNewMenu_2.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imagens/exit.png")));
		menuBar.add(mnNewMenu_2);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 21, 1184, 123);
		contentPane.add(panel_2);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imagens/Logo-CondominioV3.png")));
		lblNewLabel_4.setBounds(10, 11, 318, 112);
		panel_2.add(lblNewLabel_4);

		JPanel pnCadastrarUsuario = new JPanel();
		pnCadastrarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnCadastrarUsuario.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Cadastro  de Usu\u00E1rios", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20), new Color(0, 0, 0)));
		pnCadastrarUsuario.setBounds(10, 211, 1164, 500);
		contentPane.add(pnCadastrarUsuario);
		pnCadastrarUsuario.setLayout(null);

		JTextField_pesquisa = new JTextField();
		JTextField_pesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JTextField_pesquisa.setBounds(731, 64, 346, 40);
		pnCadastrarUsuario.add(JTextField_pesquisa);
		JTextField_pesquisa.setColumns(10);

		JComboBox jComboBox_tipo_usuario = new JComboBox();
		jComboBox_tipo_usuario.setEnabled(false);
		jComboBox_tipo_usuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jComboBox_tipo_usuario.setModel(new DefaultComboBoxModel(
				new String[] { "Selelecionar Tipo de Usuario", "Administrador", "Funcionario" }));
		jComboBox_tipo_usuario.setBounds(821, 190, 312, 32);
		pnCadastrarUsuario.add(jComboBox_tipo_usuario);

		JLabel lblNewLabel = new JLabel("Nome.:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(33, 190, 46, 32);
		pnCadastrarUsuario.add(lblNewLabel);

		jTextField_nome = new JTextField();
		jTextField_nome.setEnabled(false);
		jTextField_nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTextField_nome.setBounds(89, 192, 380, 32);
		pnCadastrarUsuario.add(jTextField_nome);
		jTextField_nome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Login.:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(489, 192, 46, 32);
		pnCadastrarUsuario.add(lblNewLabel_1);

		jTextField_login = new JTextField();
		jTextField_login.setEnabled(false);
		jTextField_login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTextField_login.setBounds(555, 192, 256, 32);
		pnCadastrarUsuario.add(jTextField_login);
		jTextField_login.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Senha.:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(33, 254, 52, 32);
		pnCadastrarUsuario.add(lblNewLabel_2);

		jPasswordField_senha = new JPasswordField();
		jPasswordField_senha.setEnabled(false);
		jPasswordField_senha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPasswordField_senha.setBounds(89, 256, 380, 32);
		pnCadastrarUsuario.add(jPasswordField_senha);

		JLabel lblNewLabel_3 = new JLabel("Confirmar Senha.:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(489, 254, 112, 32);
		pnCadastrarUsuario.add(lblNewLabel_3);

		jPasswordField_confirmar_senha = new JPasswordField();
		jPasswordField_confirmar_senha.setEnabled(false);
		jPasswordField_confirmar_senha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPasswordField_confirmar_senha.setBounds(611, 256, 380, 32);
		pnCadastrarUsuario.add(jPasswordField_confirmar_senha);

		jButton_novo = new JButton("");
		jButton_novo.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imagens/Mais-Telefone-icon.png")));
		jButton_novo.setBounds(29, 321, 89, 40);
		pnCadastrarUsuario.add(jButton_novo);

		JButton jButton_editar = new JButton("");
		jButton_editar.setEnabled(false);

		jButton_editar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imagens/alterar-01.png")));
		jButton_editar.setBounds(227, 321, 89, 40);
		pnCadastrarUsuario.add(jButton_editar);

		JButton jButton_cancelar = new JButton("");
		jButton_cancelar.setEnabled(false);
		jButton_cancelar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imagens/Cancel.png")));
		jButton_cancelar.setBounds(326, 321, 105, 40);
		pnCadastrarUsuario.add(jButton_cancelar);

		JButton jButton_deleta = new JButton("");
		jButton_deleta.setEnabled(false);

		// Botao SALVAR
		JButton jButton_salvar = new JButton("");
		jButton_salvar.setEnabled(false);
		jButton_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Botao de salvar usuario na tela de usuario
				if (jTextField_nome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo Nome do Usuário para continuar.");
					jTextField_nome.requestFocus();
				} else if (jTextField_login.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo Login do Usuário para continuar.");
					jTextField_login.requestFocus();
				} else if (jComboBox_tipo_usuario.getSelectedItem().equals("Selelecionar Tipo de Usuario")) {
					JOptionPane.showMessageDialog(null, "Preencha o campo Tipo de Usuário para continuar.");
					jComboBox_tipo_usuario.requestFocus();
				} else if (jPasswordField_senha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo Senha do Usuário para continuar.");
					jPasswordField_senha.requestFocus();
				} else if (jPasswordField_confirmar_senha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Preencha o campo de Confirmar senha do Usuário para continuar.");
					jPasswordField_confirmar_senha.requestFocus();
				} else if (jPasswordField_senha.getText().equals(jPasswordField_confirmar_senha.getText())) {
					if (flag == 1) {
						mod.setUsu_nome(jTextField_nome.getText());
						mod.setLogin_usuario(jTextField_login.getText());
						mod.setTipo_usuario((String) jComboBox_tipo_usuario.getSelectedItem());	
						mod.setSenha_usuario(jPasswordField_senha.getText());
						control.Salvar(mod);

						// Botoes
						jButton_novo.setEnabled(true);
						jButton_salvar.setEnabled(!true);
						jButton_editar.setEnabled(!true);
						jButton_cancelar.setEnabled(true);
						jButton_deleta.setEnabled(!true);

						// Campos de Formularios ativos
						jTextField_nome.setEnabled(false);
						jTextField_login.setEnabled(false);
						jComboBox_tipo_usuario.setEnabled(false);
						jPasswordField_senha.setEnabled(false);
						jPasswordField_confirmar_senha.setEnabled(false);

						// limpar campos do formulario
						jTextField_nome.setText("");
						jTextField_login.setText("");
						jComboBox_tipo_usuario.setSelectedItem("Selelecionar Tipo de Usuario");
						jPasswordField_senha.setText("");
						jPasswordField_confirmar_senha.setText("");
						JTextField_pesquisa.setText("");
						preencherTabela("Select * from usuarios order by usu_nome");
					} else {
						mod.setUsu_cod(Integer.parseInt(jTextField_Cod_Usuario.getText()));
						mod.setUsu_nome(jTextField_nome.getText());
						mod.setLogin_usuario(jTextField_login.getText());
						mod.setTipo_usuario((String) jComboBox_tipo_usuario.getSelectedItem());
						mod.setSenha_usuario(jPasswordField_senha.getText());

						control.Editar(mod);

						// Botoes
						jButton_novo.setEnabled(true);
						jButton_salvar.setEnabled(!true);
						jButton_editar.setEnabled(!true);
						jButton_cancelar.setEnabled(true);
						jButton_deleta.setEnabled(!true);

						// Campos de Formularios ativos ou nao
						jTextField_nome.setEnabled(false);
						jTextField_login.setEnabled(false);
						jComboBox_tipo_usuario.setEnabled(false);
						jPasswordField_senha.setEnabled(false);
						jPasswordField_confirmar_senha.setEnabled(false);

						// limpar campos do formulario
						jTextField_Cod_Usuario.setText("");
						jTextField_nome.setText("");
						jTextField_login.setText("");
						jComboBox_tipo_usuario.setSelectedItem("Selelecionar Tipo de Usuario");
						jPasswordField_senha.setText("");
						jPasswordField_confirmar_senha.setText("");
						JTextField_pesquisa.setText("");
						JTextField_pesquisa.setText("");
						preencherTabela("Select * from usuarios order by usu_nome");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Atenção Senhas Diferentes!");
				}
			}
		});
		jButton_salvar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imagens/salvar-46X49.png")));
		jButton_salvar.setBounds(128, 321, 89, 40);
		pnCadastrarUsuario.add(jButton_salvar);

		// Botao PESQUISAR
		JButton jButton_pesquisa = new JButton("");
		jButton_pesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Comando para pesquisar usuario na tela de usuario
				mod.setPesquisa(JTextField_pesquisa.getText());
				BeansUsuario model = control.buscaUsuario(mod);

				jTextField_Cod_Usuario.setText(String.valueOf(mod.getUsu_cod()));
				jTextField_nome.setText(model.getUsu_nome());
				jTextField_login.setText(model.getLogin_usuario());
				jComboBox_tipo_usuario.setSelectedItem(model.getTipo_usuario());
				jPasswordField_senha.setText(model.getSenha_usuario());

				// Habilitar Botoes
				jButton_novo.setEnabled(false);
				jButton_editar.setEnabled(true);
				jButton_cancelar.setEnabled(true);
				jButton_deleta.setEnabled(true);

				// Ocultar botoes
				jButton_salvar.setEnabled(false);

				// Limpa campos de pesquisa
				JTextField_pesquisa.setText("");

				preencherTabela("Select * from usuarios where usu_nome like '%" + JTextField_pesquisa.getText() + "%'");

			}
		});

		// Botao EDITAR/ATUALIZAR
		jButton_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;

				// Botoes
				jButton_novo.setEnabled(false);
				jButton_salvar.setEnabled(true);
				jButton_editar.setEnabled(false);
				jButton_cancelar.setEnabled(true);
				jButton_deleta.setEnabled(true);

				// Campos de Formularios ativos ou nao
				jTextField_nome.setEnabled(true);
				jTextField_login.setEnabled(true);
				jComboBox_tipo_usuario.setEnabled(true);
				jPasswordField_senha.setEnabled(true);
				jPasswordField_confirmar_senha.setEnabled(true);

			}
		});

		// Botao NOVO
		jButton_novo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;

				// Botoes
				jButton_salvar.setEnabled(true);
				jButton_editar.setEnabled(false);
				jButton_cancelar.setEnabled(true);
				jButton_deleta.setEnabled(false);

				// Campos de Formularios ativos ou nao
				jTextField_nome.setEnabled(true);
				jTextField_login.setEnabled(true);
				jComboBox_tipo_usuario.setEnabled(true);
				jPasswordField_senha.setEnabled(true);
				jPasswordField_confirmar_senha.setEnabled(true);

				// limpar campos do formulario
				jTextField_Cod_Usuario.setText("");
				jTextField_nome.setText("");
				jTextField_login.setText("");
				jComboBox_tipo_usuario.setSelectedItem("Selelecionar Tipo de Usuario");
				jPasswordField_senha.setText("");
				jPasswordField_confirmar_senha.setText("");
				JTextField_pesquisa.setText("");
				JTextField_pesquisa.setText("");
			}
		});

		// Botao CANCELAR
		jButton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Botoes
				jButton_novo.setEnabled(true);
				jButton_salvar.setEnabled(false);
				jButton_editar.setEnabled(false);
				jButton_cancelar.setEnabled(false);
				jButton_deleta.setEnabled(false);

				// Campos de Formularios ativos ou nao
				jTextField_nome.setEnabled(!true);
				jTextField_login.setEnabled(!true);
				jComboBox_tipo_usuario.setEnabled(!true);
				jPasswordField_senha.setEnabled(!true);
				jPasswordField_confirmar_senha.setEnabled(!true);

				// limpar campos do formulario
				jTextField_Cod_Usuario.setText("");
				jTextField_nome.setText("");
				jTextField_login.setText("");
				jComboBox_tipo_usuario.setSelectedItem("Selelecionar Tipo de Usuario");
				jPasswordField_senha.setText("");
				jPasswordField_confirmar_senha.setText("");
				JTextField_pesquisa.setText("");
				JTextField_pesquisa.setText("");

				jTable_tabela_usuario.setModel(new DefaultTableModel());

			}
		});

		// Botao DELETAR
		jButton_deleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirma = 0;
				confirma = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir usuário?");

				if (confirma == JOptionPane.YES_OPTION) {
					mod.setUsu_cod(Integer.parseInt(jTextField_Cod_Usuario.getText()));
					control.Excluir(mod);

					// Botoes
					jButton_novo.setEnabled(true);
					jButton_salvar.setEnabled(false);
					jButton_editar.setEnabled(false);
					jButton_cancelar.setEnabled(!false);
					jButton_deleta.setEnabled(false);

					// Campos de Formularios ativos ou nao
					jTextField_nome.setEnabled(!true);
					jTextField_login.setEnabled(!true);
					jComboBox_tipo_usuario.setEnabled(!true);
					jPasswordField_senha.setEnabled(!true);
					jPasswordField_confirmar_senha.setEnabled(!true);

					// limpar campos do formulario
					jTextField_Cod_Usuario.setText("");
					jTextField_nome.setText("");
					jTextField_login.setText("");
					jComboBox_tipo_usuario.setSelectedItem("Selelecionar Tipo de Usuario");
					jPasswordField_senha.setText("");
					jPasswordField_confirmar_senha.setText("");
					JTextField_pesquisa.setText("");
					JTextField_pesquisa.setText("");
					preencherTabela("Select * from usuarios order by usu_nome");
				}
			}
		});

		jButton_pesquisa.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imagens/procurar.png")));
		jButton_pesquisa.setBounds(1087, 64, 46, 40);
		pnCadastrarUsuario.add(jButton_pesquisa);

		jButton_deleta.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imagens/deleta-45X48.png")));
		jButton_deleta.setBounds(441, 321, 105, 40);
		pnCadastrarUsuario.add(jButton_deleta);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 373, 1100, 116);
		pnCadastrarUsuario.add(scrollPane);

		jTable_tabela_usuario = new JTable();
		jTable_tabela_usuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Metodo de selecionar um usuario quando clicar atraves da tabela

				String usuario = "" + jTable_tabela_usuario.getValueAt(jTable_tabela_usuario.getSelectedRow(), 1);
				conex.conexao();

				conex.executaSQL("select * from usuarios where usu_nome='" + usuario + "'");
				try {
					conex.rs.first();
					jTextField_Cod_Usuario.setText(String.valueOf(conex.rs.getInt("usu_cod")));
					jTextField_nome.setText(conex.rs.getString("usu_nome"));
					jTextField_login.setText(conex.rs.getString("login_usuario"));
					jComboBox_tipo_usuario.setSelectedItem(conex.rs.getString("tipo_usuario"));
					jPasswordField_senha.setText(conex.rs.getString("senha_usuario"));
					
					jTextField_nome.setEnabled(true);
					jTextField_login.setEnabled(true);
					jComboBox_tipo_usuario.setEnabled(true);
					jPasswordField_senha.setEnabled(true);
					jPasswordField_confirmar_senha.setEnabled(true);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro ao Selecionar Usuário!\nErro: " + ex);
				}

				conex.desconectar();
				jButton_editar.setEnabled(!false);
				jButton_deleta.setEnabled(!false);
				jButton_cancelar.setEnabled(!false);
			}
		});
		scrollPane.setViewportView(jTable_tabela_usuario);

		JLabel lblCodigo = new JLabel("Codigo.:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(33, 70, 85, 32);
		pnCadastrarUsuario.add(lblCodigo);

		jTextField_Cod_Usuario = new JTextField();
		jTextField_Cod_Usuario.setEnabled(false);
		jTextField_Cod_Usuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jTextField_Cod_Usuario.setColumns(10);
		jTextField_Cod_Usuario.setBounds(140, 72, 329, 32);
		pnCadastrarUsuario.add(jTextField_Cod_Usuario);

		JLabel lblNewLabel_5 = new JLabel("Pesquisar por nome.:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(731, 39, 158, 14);
		pnCadastrarUsuario.add(lblNewLabel_5);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlDkShadow);
		panel_1.setBounds(0, 143, 1184, 22);
		contentPane.add(panel_1);
	}
}
