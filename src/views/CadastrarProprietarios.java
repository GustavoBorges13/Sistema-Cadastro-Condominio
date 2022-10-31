package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.ConexaoBD;
import controller.ControleProprietario;
import modelEclipse.BeansProprietario;
import modelEclipse.ModeloTabela;

public class CadastrarProprietarios extends JFrame {
	private static final long serialVersionUID = 5240433323306163374L;
	ConexaoBD conex = new ConexaoBD();
	ControleProprietario control = new ControleProprietario();
	BeansProprietario mod = new BeansProprietario();
	int flag = 0;
	private JPanel contentPane;
	private JTextField jTextFieldPesquisa;
	private JTable jTableProprietarios;
	private JTextField jTextFieldDataCadastro;
	private JTextField jTextFieldCod_Proprietario;
	private JTextField jTextFieldNomeProprietario;
	private JTextField jTextFieldQuadra;
	private JTextField jTextFieldLote;
	private JTextField jTextFieldEmailProprietario;
	private ButtonGroup buttonGroupCadastro = new ButtonGroup();
	private ButtonGroup buttonGroupEndereco = new ButtonGroup();
	private JRadioButton jRadioButtonCadastrar;
	private JCheckBox jCheckBoxEndereco;
	private JButton jButtonFechaInternalFrameEndereco;
	private JButton jButtonFechaInternalFramePrincipal;
	private JButton jButtonPesquisar;
	private JButton jButtonNovo;
	private JButton jButtonSalvar;
	private JButton jButtonEditar;
	private JButton jButtonCancelar;
	private JButton jButtonDeletar;
	Date dataHoraAtual = new Date();
	String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarProprietarios frame = new CadastrarProprietarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	public void preencherTabelaProprietario(String SQL) {
		@SuppressWarnings("rawtypes")
		ArrayList dados = new ArrayList();
		String[] Colunas = new String[] { "CODIGO", "PROPRIETARIO", "TELEFONE", "CPF", "E-MAIL", "QUADRA", "LOTE" };

		conex.conexao();
		conex.executaSQL(SQL);

		try {
			conex.rs.first();
			do {
				dados.add(new Object[] { conex.rs.getInt("id_proprietario"), conex.rs.getString("nome_proprietario"),
						conex.rs.getString("telefone_proprietario"), conex.rs.getString("cpf_proprietario"),
						conex.rs.getString("email_proprietario"), conex.rs.getString("quadra_proprietario"),
						conex.rs.getString("lote_proprietario") });
			} while (conex.rs.next());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Nâo existe Proprietário cadastrado no sistema. Por favor cadastre um Proprietário!");
		}
		ModeloTabela modelo = new ModeloTabela(dados, Colunas);
		jTableProprietarios.setModel(modelo);

		// Nao deixa a aumentar a largura das colunas da tabela usando o mouse!
		jTableProprietarios.getColumnModel().getColumn(0).setPreferredWidth(70);
		jTableProprietarios.getColumnModel().getColumn(0).setResizable(false);
		jTableProprietarios.getColumnModel().getColumn(1).setPreferredWidth(250);
		jTableProprietarios.getColumnModel().getColumn(1).setResizable(false);
		jTableProprietarios.getColumnModel().getColumn(2).setPreferredWidth(130);
		jTableProprietarios.getColumnModel().getColumn(2).setResizable(false);
		jTableProprietarios.getColumnModel().getColumn(3).setPreferredWidth(90);
		jTableProprietarios.getColumnModel().getColumn(3).setResizable(false);
		jTableProprietarios.getColumnModel().getColumn(4).setPreferredWidth(250);
		jTableProprietarios.getColumnModel().getColumn(4).setResizable(false);
		jTableProprietarios.getColumnModel().getColumn(5).setPreferredWidth(70);
		jTableProprietarios.getColumnModel().getColumn(5).setResizable(false);
		jTableProprietarios.getColumnModel().getColumn(6).setPreferredWidth(140);
		jTableProprietarios.getColumnModel().getColumn(6).setResizable(false);

		// Nao vai reodernar os nomes e titulos do cabeçalho da tabela
		jTableProprietarios.getTableHeader().setReorderingAllowed(false);

		// Nao permite aumentar na tabela as colunas
		jTableProprietarios.setAutoResizeMode(jTableProprietarios.AUTO_RESIZE_OFF);

		// Faz com que o usuario selecione um dado na tabela POR VEZ
		jTableProprietarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		conex.desconectar();
	}

	public CadastrarProprietarios() {
		initComponents();
		// preencherTabelaProprietario("select * from proprietarios order by
		// nome_proprietario");
	}

	public void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnCadastrarUsuario = new JPanel();
		pnCadastrarUsuario.setLayout(null);
		pnCadastrarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnCadastrarUsuario.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Informa\u00E7\u00F5es Propriet\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20), new Color(0, 0, 0)));
		pnCadastrarUsuario.setBounds(10, 71, 1064, 579);
		contentPane.add(pnCadastrarUsuario);

		JInternalFrame jInternalFramePrincipal = new JInternalFrame("Cadastro do Proprietário");
		jInternalFramePrincipal.setFocusable(false);
		jInternalFramePrincipal.setToolTipText("Cadastro do Proprietário");
		jInternalFramePrincipal.setBounds(10, 27, 1044, 541);
		pnCadastrarUsuario.add(jInternalFramePrincipal);

		JLabel lblNewLabel = new JLabel("Data.:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		jTextFieldDataCadastro = new JTextField();
		jTextFieldDataCadastro.setColumns(10);

		jTextFieldCod_Proprietario = new JTextField();
		jTextFieldCod_Proprietario.setColumns(10);

		JLabel lblCodigo_1 = new JLabel("Codigo.:");
		lblCodigo_1.setHorizontalAlignment(SwingConstants.RIGHT);

		jTextFieldNomeProprietario = new JTextField();
		jTextFieldNomeProprietario.setColumns(10);

		JLabel lblCodigo_1_1 = new JLabel("Proprietário.:");
		lblCodigo_1_1.setHorizontalAlignment(SwingConstants.RIGHT);

		jTextFieldQuadra = new JTextField();
		jTextFieldQuadra.setColumns(10);

		JLabel lblCodigo_1_2 = new JLabel("Quadra.:");
		lblCodigo_1_2.setHorizontalAlignment(SwingConstants.RIGHT);

		jTextFieldLote = new JTextField();
		jTextFieldLote.setColumns(10);

		JLabel lblCodigo_1_2_1 = new JLabel("Lote.:");
		lblCodigo_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);

		jButtonFechaInternalFramePrincipal = new JButton("Fecha");
		jButtonFechaInternalFramePrincipal.setFocusable(false);

		JLabel lblCodigo_1_3 = new JLabel("CPF.:");
		lblCodigo_1_3.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblCodigo_1_3_1 = new JLabel("Telefone.:");
		lblCodigo_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);

		jTextFieldEmailProprietario = new JTextField();
		jTextFieldEmailProprietario.setColumns(10);

		JLabel lblCodigo_1_1_1 = new JLabel("Email.:");
		lblCodigo_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);

		jCheckBoxEndereco = new JCheckBox("Não mora no condomínio.");
		jCheckBoxEndereco.setFont(new Font("Tahoma", Font.BOLD, 14));

		JFormattedTextField jFormattedCpf_proprietario = new JFormattedTextField();

		JFormattedTextField jFormattedFoneProprietario = new JFormattedTextField();

		JInternalFrame jInternalFrameCadastroEndereco = new JInternalFrame("Endereço da cidade onde mora");
		jInternalFrameCadastroEndereco.setToolTipText("Endereço da cidade onde mora");
		jInternalFrameCadastroEndereco.getContentPane().setLayout(null);

		JFormattedTextField jTextFieldEnderecoProprietario = new JFormattedTextField();
		jTextFieldEnderecoProprietario.setBounds(66, 55, 463, 31);
		jInternalFrameCadastroEndereco.getContentPane().add(jTextFieldEnderecoProprietario);

		JLabel lblCodigo_1_3_2 = new JLabel("Endereço.:");
		lblCodigo_1_3_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo_1_3_2.setBounds(10, 63, 61, 14);
		jInternalFrameCadastroEndereco.getContentPane().add(lblCodigo_1_3_2);

		JFormattedTextField jTextFieldBairro = new JFormattedTextField();
		jTextFieldBairro.setBounds(66, 95, 428, 31);
		jInternalFrameCadastroEndereco.getContentPane().add(jTextFieldBairro);

		JLabel lblCodigo_1_3_2_1 = new JLabel("Bairro.:");
		lblCodigo_1_3_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo_1_3_2_1.setBounds(10, 103, 61, 14);
		jInternalFrameCadastroEndereco.getContentPane().add(lblCodigo_1_3_2_1);

		JFormattedTextField jTextFieldEstado = new JFormattedTextField();
		jTextFieldEstado.setBounds(66, 137, 117, 31);
		jInternalFrameCadastroEndereco.getContentPane().add(jTextFieldEstado);

		JLabel lblCodigo_1_3_2_2 = new JLabel("Estado.:");
		lblCodigo_1_3_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo_1_3_2_2.setBounds(10, 145, 61, 14);
		jInternalFrameCadastroEndereco.getContentPane().add(lblCodigo_1_3_2_2);

		JFormattedTextField jTextFieldNumeroEndereco = new JFormattedTextField();
		jTextFieldNumeroEndereco.setBounds(583, 55, 112, 31);
		jInternalFrameCadastroEndereco.getContentPane().add(jTextFieldNumeroEndereco);

		JLabel lblCodigo_1_3_2_3 = new JLabel("N°.:");
		lblCodigo_1_3_2_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo_1_3_2_3.setBounds(555, 63, 25, 14);
		jInternalFrameCadastroEndereco.getContentPane().add(lblCodigo_1_3_2_3);

		JLabel lblCodigo_1_3_2_1_1 = new JLabel("Complemento.:");
		lblCodigo_1_3_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo_1_3_2_1_1.setBounds(719, 64, 84, 14);
		jInternalFrameCadastroEndereco.getContentPane().add(lblCodigo_1_3_2_1_1);

		JFormattedTextField jTextFieldComplemento = new JFormattedTextField();
		jTextFieldComplemento.setBounds(803, 55, 179, 31);
		jInternalFrameCadastroEndereco.getContentPane().add(jTextFieldComplemento);

		JFormattedTextField jTextFieldCep = new JFormattedTextField();
		jTextFieldCep.setBounds(536, 95, 141, 31);
		jInternalFrameCadastroEndereco.getContentPane().add(jTextFieldCep);

		JLabel lblCodigo_1_3_2_2_1 = new JLabel("Cep.:");
		lblCodigo_1_3_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo_1_3_2_2_1.setBounds(504, 103, 61, 14);
		jInternalFrameCadastroEndereco.getContentPane().add(lblCodigo_1_3_2_2_1);

		JFormattedTextField jTextFieldCidade = new JFormattedTextField();
		jTextFieldCidade.setBounds(754, 95, 228, 31);
		jInternalFrameCadastroEndereco.getContentPane().add(jTextFieldCidade);

		JLabel lblCodigo_1_3_2_2_2 = new JLabel("Cidade.:");
		lblCodigo_1_3_2_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigo_1_3_2_2_2.setBounds(698, 103, 61, 14);
		jInternalFrameCadastroEndereco.getContentPane().add(lblCodigo_1_3_2_2_2);

		jButtonFechaInternalFrameEndereco = new JButton("Fecha");
		jButtonFechaInternalFrameEndereco.setFocusable(false);

		jButtonFechaInternalFrameEndereco.setBounds(911, 11, 71, 32);
		jInternalFrameCadastroEndereco.getContentPane().add(jButtonFechaInternalFrameEndereco);

		jButtonNovo = new JButton("");
		jButtonNovo.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/Mais-Telefone-icon.png")));
		jButtonNovo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jButtonSalvar = new JButton("");
		jButtonSalvar.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/salvar-46X49.png")));
		jButtonSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jButtonEditar = new JButton("");
		jButtonEditar.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/alterar-01.png")));
		jButtonEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jButtonCancelar = new JButton("");
		jButtonCancelar.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/cancelar.png")));
		jButtonCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jButtonDeletar = new JButton("");
		jButtonDeletar.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/deleta-45X48.png")));
		jButtonDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		GroupLayout groupLayout = new GroupLayout(jInternalFramePrincipal.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(31).addComponent(lblNewLabel).addGap(6)
								.addComponent(jTextFieldDataCadastro, GroupLayout.PREFERRED_SIZE, 115,
										GroupLayout.PREFERRED_SIZE)
								.addGap(755).addComponent(jButtonFechaInternalFramePrincipal,
										GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(31).addComponent(jCheckBoxEndereco,
								GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(
								jInternalFrameCadastroEndereco, GroupLayout.PREFERRED_SIZE, 1008,
								GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(237)
								.addComponent(jButtonNovo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(jButtonSalvar, GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE)
								.addGap(17)
								.addComponent(jButtonEditar, GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(jButtonCancelar, GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE)
								.addGap(16).addComponent(jButtonDeletar, GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(16)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addComponent(lblCodigo_1, GroupLayout.PREFERRED_SIZE, 45,
														GroupLayout.PREFERRED_SIZE)
												.addGap(7)
												.addComponent(jTextFieldCod_Proprietario, GroupLayout.PREFERRED_SIZE,
														115, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(lblCodigo_1_1, GroupLayout.PREFERRED_SIZE, 73,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(jTextFieldNomeProprietario, GroupLayout.PREFERRED_SIZE,
														424, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblCodigo_1_2, GroupLayout.PREFERRED_SIZE, 45,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(jTextFieldQuadra, GroupLayout.PREFERRED_SIZE, 103,
														GroupLayout.PREFERRED_SIZE)
												.addGap(7)
												.addComponent(lblCodigo_1_2_1, GroupLayout.PREFERRED_SIZE, 45,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(jTextFieldLote, 0, 0, Short.MAX_VALUE))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addComponent(lblCodigo_1_3, GroupLayout.PREFERRED_SIZE, 45,
														GroupLayout.PREFERRED_SIZE)
												.addGap(7)
												.addComponent(jFormattedCpf_proprietario, GroupLayout.PREFERRED_SIZE,
														161, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(lblCodigo_1_3_1, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE)
												.addGap(7)
												.addComponent(jFormattedFoneProprietario, GroupLayout.PREFERRED_SIZE,
														161, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(lblCodigo_1_1_1, GroupLayout.PREFERRED_SIZE, 45,
														GroupLayout.PREFERRED_SIZE)
												.addGap(7).addComponent(jTextFieldEmailProprietario,
														GroupLayout.PREFERRED_SIZE, 479, GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(10, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(76).addComponent(lblCodigo_1_1))
						.addGroup(groupLayout.createSequentialGroup().addGap(25).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(9).addComponent(lblNewLabel))
								.addGroup(groupLayout.createSequentialGroup().addGap(3).addComponent(
										jTextFieldDataCadastro, GroupLayout.PREFERRED_SIZE, 31,
										GroupLayout.PREFERRED_SIZE))
								.addComponent(jButtonFechaInternalFramePrincipal, GroupLayout.PREFERRED_SIZE, 32,
										GroupLayout.PREFERRED_SIZE))
								.addGap(11)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(jTextFieldNomeProprietario, GroupLayout.PREFERRED_SIZE,
														31, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCodigo_1_2)
												.addComponent(jTextFieldQuadra, GroupLayout.PREFERRED_SIZE, 31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCodigo_1_2_1).addComponent(jTextFieldLote,
														GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
										.addGroup(
												groupLayout.createSequentialGroup().addGap(6).addComponent(lblCodigo_1))
										.addComponent(jTextFieldCod_Proprietario, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(11)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(lblCodigo_1_3))
						.addComponent(jFormattedCpf_proprietario, GroupLayout.PREFERRED_SIZE, 31,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(lblCodigo_1_3_1))
						.addComponent(jFormattedFoneProprietario, GroupLayout.PREFERRED_SIZE, 31,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(lblCodigo_1_1_1))
						.addComponent(jTextFieldEmailProprietario, GroupLayout.PREFERRED_SIZE, 31,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(jCheckBoxEndereco, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addGap(13)
				.addComponent(
						jInternalFrameCadastroEndereco, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
				.addGap(22)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
								.addComponent(jButtonSalvar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonNovo, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addComponent(jButtonEditar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButtonCancelar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButtonDeletar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
				.addGap(22)));
		jInternalFramePrincipal.getContentPane().setLayout(groupLayout);
		/*
		 * jCheckBoxEndereco.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { if (jCheckBoxEndereco.isSelected()) {
		 * jInternalFrameCadastroEndereco.setVisible(true); } else {
		 * jInternalFrameCadastroEndereco.setVisible(false); } } });
		 */
		
		// Botoes de marcar/selecionar -> GRUPOS
		buttonGroupCadastro.add(jRadioButtonCadastrar);
		buttonGroupEndereco.add(jCheckBoxEndereco);

		jButtonFechaInternalFrameEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jInternalFrameCadastroEndereco.dispose();
				jCheckBoxEndereco.setSelected(false);
				buttonGroupEndereco.clearSelection();

				if (jInternalFrameCadastroEndereco.isClosed()) {
					jTextFieldEnderecoProprietario.setText("");
					jTextFieldNumeroEndereco.setText("");
					jTextFieldComplemento.setText("");
					jTextFieldBairro.setText("");
					jTextFieldCep.setText("");
					jTextFieldCidade.setText("");
					jTextFieldEstado.setText("");
				}
			}
		});

		jButtonFechaInternalFramePrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jInternalFramePrincipal.dispose();
				jInternalFrameCadastroEndereco.dispose();
				jTableProprietarios.setVisible(true);
				// linha de correcao de bug! - nao excluir
				jTextFieldPesquisa.setVisible(true); // linha de correcao de bug! - nao excluir
				buttonGroupEndereco.clearSelection();
				buttonGroupCadastro.clearSelection();
				preencherTabelaProprietario("select * from proprietarios order by nome_proprietario");
			}
		});
		buttonGroupEndereco.add(jCheckBoxEndereco);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setBounds(33, 146, 1001, 332);
		pnCadastrarUsuario.add(scrollPane);

		jTableProprietarios = new JTable();
		jTableProprietarios.addMouseListener(new MouseAdapter() {
			boolean isAlreadyOneClick;

			@Override
			public void mouseClicked(MouseEvent e) {
				// Metodo de selecionar um usuario quando clicar atraves da tabela
				if (isAlreadyOneClick) {
					String usuario = "" + jTableProprietarios.getValueAt(jTableProprietarios.getSelectedRow(), 1);
					conex.conexao();
					conex.executaSQL("select * from proprietarios where nome_proprietario like '%" + usuario + "%'");

					// Habilitar os botoes/tabela
					jTableProprietarios.setVisible(false);
					// jButton_novo.setEnabled(false);
					// jButton_editar.setEnabled(true);
					// jButton_cancelar.setEnabled(true);
					// jButton_deleta.setEnabled(true);

					// Ocultar os botoes
					// jButton_salvar.setEnabled(false);

					// Limpar os campos de pesquisa
					// jTextFieldPesquisa.setText(" ");

					try {
						conex.rs.first();
						jTextFieldCod_Proprietario.setText(String.valueOf(conex.rs.getString("id_proprietario")));
						jTextFieldNomeProprietario.setText(conex.rs.getString("nome_proprietario"));
						jTextFieldLote.setText(conex.rs.getString("lote_proprietario"));
						jTextFieldQuadra.setText(conex.rs.getString("quadra_proprietario"));
						jFormattedCpf_proprietario.setText(conex.rs.getString("cpf_proprietario"));
						jFormattedFoneProprietario.setText(conex.rs.getString("telefone_proprietario"));
						jTextFieldEmailProprietario.setText(conex.rs.getString("email_proprietario"));
						jTextFieldDataCadastro.setText(conex.rs.getString("data_cadastro"));
						jTextFieldEnderecoProprietario.setText(conex.rs.getString("endereco"));
						jTextFieldNumeroEndereco.setText(conex.rs.getString("numero_endereco"));
						jTextFieldComplemento.setText(conex.rs.getString("complemento"));
						jTextFieldBairro.setText(conex.rs.getString("bairro"));
						jTextFieldCep.setText(conex.rs.getString("cep"));
						jTextFieldCidade.setText(conex.rs.getString("cidade"));
						jTextFieldEstado.setText(conex.rs.getString("estado"));
						// jButtonPesquisar.setVisible(false); //linha de correcao do bug! - nao excluir
						// jInternalFramePrincipal.setVisible(true);
						jInternalFramePrincipal.setVisible(true);
						
						if (jTextFieldEnderecoProprietario.getText().isBlank()
								&& jTextFieldNumeroEndereco.getText().isBlank() && jTextFieldBairro.getText().isBlank()
								&& jTextFieldCep.getText().isBlank() && jTextFieldCidade.getText().isBlank()
								&& jTextFieldEstado.getText().isBlank()) {

							flag = 3;
							// Botoes
							jButtonNovo.setEnabled(false);
							jButtonSalvar.setEnabled(false);
							jButtonEditar.setEnabled(!false);
							jButtonCancelar.setEnabled(!false);
							jButtonDeletar.setEnabled(!false);

							// Campos de formulario
							jTextFieldDataCadastro.setEnabled(false);
							jTextFieldCod_Proprietario.setEnabled(false);
							jTextFieldNomeProprietario.setEnabled(false);
							jTextFieldLote.setEnabled(false);
							jTextFieldQuadra.setEnabled(false);
							jFormattedCpf_proprietario.setEnabled(false);
							jFormattedFoneProprietario.setEnabled(false);
							jTextFieldEmailProprietario.setEnabled(false);
							jTextFieldEnderecoProprietario.setEnabled(false);
							jTextFieldNumeroEndereco.setEnabled(false);
							jTextFieldComplemento.setEnabled(false);
							jTextFieldBairro.setEnabled(false);
							jTextFieldCep.setEnabled(false);
							jTextFieldCidade.setEnabled(false);
							jTextFieldEstado.setEnabled(false);
							jCheckBoxEndereco.setSelected(false);
							jCheckBoxEndereco.setEnabled(false);
							jTextFieldPesquisa.setVisible(false);

							// janelas
							jInternalFrameCadastroEndereco.setVisible(false);
						} else {
							flag = 2;
							// Botoes
							jButtonNovo.setEnabled(false);
							jButtonSalvar.setEnabled(false);
							jButtonEditar.setEnabled(!false);
							jButtonCancelar.setEnabled(!false);
							jButtonDeletar.setEnabled(!false);

							// Campos de formulario
							jTextFieldDataCadastro.setEnabled(false);
							jTextFieldCod_Proprietario.setEnabled(false);
							jTextFieldNomeProprietario.setEnabled(false);
							jTextFieldLote.setEnabled(false);
							jTextFieldQuadra.setEnabled(false);
							jFormattedCpf_proprietario.setEnabled(false);
							jFormattedFoneProprietario.setEnabled(false);
							jTextFieldEmailProprietario.setEnabled(false);
							jTextFieldEnderecoProprietario.setEnabled(false);
							jTextFieldNumeroEndereco.setEnabled(false);
							jTextFieldComplemento.setEnabled(false);
							jTextFieldBairro.setEnabled(false);
							jTextFieldCep.setEnabled(false);
							jTextFieldCidade.setEnabled(false);
							jTextFieldEstado.setEnabled(false);
							jCheckBoxEndereco.setSelected(isAlreadyOneClick);
							jCheckBoxEndereco.setEnabled(false);
							jTextFieldPesquisa.setVisible(false);

							// janelas
							jInternalFrameCadastroEndereco.setVisible(true);
						}

						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao Selecionar Proprietario!\nErro: " + ex);
					}
					conex.desconectar();
					isAlreadyOneClick = false;
				} else {
					isAlreadyOneClick = true;
					Timer t = new Timer("doubleclickTimer", false);
					t.schedule(new TimerTask() {

						@Override
						public void run() {
							isAlreadyOneClick = false;
						}
					}, 300);
				}
			}
		});
		scrollPane.setViewportView(jTableProprietarios);

		jTextFieldPesquisa = new JTextField();
		jTextFieldPesquisa.setFocusable(true);
		jTextFieldPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jTextFieldPesquisa.setColumns(10);
		jTextFieldPesquisa.setBounds(646, 73, 323, 40);
		pnCadastrarUsuario.add(jTextFieldPesquisa);

		JLabel lblNewLabel_5 = new JLabel("Pesquisar por nome.:");
		lblNewLabel_5.setFocusable(false);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(646, 42, 176, 32);
		pnCadastrarUsuario.add(lblNewLabel_5);

		jButtonPesquisar = new JButton("");
		jButtonPesquisar.setFocusable(false);
		jButtonPesquisar.setFocusTraversalKeysEnabled(false);
		jButtonPesquisar.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/procurar.png")));
		jButtonPesquisar.setBounds(978, 73, 46, 40);
		pnCadastrarUsuario.add(jButtonPesquisar);

		// OPCAO PESQUISAR
		jButtonPesquisar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mod.setPesquisa(jTextFieldPesquisa.getText());
				BeansProprietario model = control.buscarProprietario(mod);
				jTextFieldCod_Proprietario.setText(String.valueOf(mod.getCodigo_proprietario()));
				jTextFieldNomeProprietario.setText(model.getNome_proprietario());
				jTextFieldLote.setText(model.getLote_proprietario());
				jTextFieldQuadra.setText(model.getQuadra_proprietario());
				jFormattedCpf_proprietario.setText(model.getCpf_proprietario());
				jFormattedFoneProprietario.setText(model.getTelefone_proprietario());
				jTextFieldEmailProprietario.setText(model.getEmail_proprietario());
				jTextFieldEnderecoProprietario.setText(model.getEndereco());
				jTextFieldNumeroEndereco.setText(model.getNumero_endereco());
				jTextFieldComplemento.setText(model.getComplemento());
				jTextFieldBairro.setText(model.getBairro());
				jTextFieldCep.setText(model.getCep());
				jTextFieldCidade.setText(model.getCidade());
				jTextFieldEstado.setText(model.getEstado());
				// jInternalFramePrincipal.setVisible(true);

				// Habilitar os botoes
				// jButton_novo.setEnabled(false);
				// jButton_editar.setEnabled(true);
				// jButton_cancelar.setEnabled(true);
				// jButton_deleta.setEnabled(true);

				// Ocultar os botoes
				// jButton_salvar.setEnabled(false);

				// Limpar os campos de pesquisa
				// jTextFieldPesquisa.setText(" ");

				preencherTabelaProprietario("select * from proprietarios order by nome_proprietario");
			}
		});

		JButton jButtonPrimeiro = new JButton("");
		jButtonPrimeiro.setFocusable(false);
		jButtonPrimeiro.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/Seta-Primaira.png")));
		jButtonPrimeiro.setBounds(324, 491, 89, 68);
		pnCadastrarUsuario.add(jButtonPrimeiro);

		JButton jButtonAnterior = new JButton("");
		jButtonAnterior.setFocusable(false);
		jButtonAnterior.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/seta-Anterior.png")));
		jButtonAnterior.setBounds(423, 491, 89, 68);
		pnCadastrarUsuario.add(jButtonAnterior);

		JButton jButtonProximo = new JButton("");
		jButtonProximo.setFocusable(false);
		jButtonProximo.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/seta-Proximo.png")));
		jButtonProximo.setBounds(522, 491, 89, 68);
		pnCadastrarUsuario.add(jButtonProximo);

		JButton jButtonUltimo = new JButton("");
		jButtonUltimo.setFocusable(false);
		jButtonUltimo.setIcon(new ImageIcon(CadastrarProprietarios.class.getResource("/imagens/Seta-Ultima.png")));
		jButtonUltimo.setBounds(621, 491, 105, 68);
		pnCadastrarUsuario.add(jButtonUltimo);

		// OPCAO NAO MORA NO CONDOMINIO
		jCheckBoxEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jInternalFrameCadastroEndereco.setVisible(true);
			}
		});

		// OPCAO CADASTRAR USUARIO!
		jRadioButtonCadastrar = new JRadioButton("Cadastrar proprietario");
		jRadioButtonCadastrar.setFocusable(false);
		jRadioButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jRadioButtonCadastrar.isSelected()) {
					// janela
					jInternalFramePrincipal.setVisible(true);

					// Botoes
					jButtonNovo.setEnabled(true);
					jButtonSalvar.setEnabled(false);
					jButtonEditar.setEnabled(false);
					jButtonCancelar.setEnabled(false);
					jButtonDeletar.setEnabled(false);

					// Campos de formulario
					jTextFieldDataCadastro.setEnabled(false);
					jTextFieldCod_Proprietario.setEnabled(false);
					jTextFieldNomeProprietario.setEnabled(false);
					jTextFieldLote.setEnabled(false);
					jTextFieldQuadra.setEnabled(false);
					jFormattedCpf_proprietario.setEnabled(false);
					jFormattedFoneProprietario.setEnabled(false);
					jTextFieldEmailProprietario.setEnabled(false);
					jTextFieldEnderecoProprietario.setEnabled(false);
					jTextFieldNumeroEndereco.setEnabled(false);
					jTextFieldComplemento.setEnabled(false);
					jTextFieldBairro.setEnabled(false);
					jTextFieldCep.setEnabled(false);
					jTextFieldCidade.setEnabled(false);
					jTextFieldEstado.setEnabled(false);
					jCheckBoxEndereco.setEnabled(false);
					jTextFieldPesquisa.setVisible(false);

					// Limpar campos
					jTextFieldDataCadastro.setText(data);
					jTextFieldCod_Proprietario.setText("");
					jTextFieldNomeProprietario.setText("");
					jTextFieldLote.setText("");
					jTextFieldQuadra.setText("");
					jFormattedCpf_proprietario.setText("");
					jFormattedFoneProprietario.setText("");
					jTextFieldEmailProprietario.setText("");
					jTextFieldEnderecoProprietario.setText("");
					jTextFieldNumeroEndereco.setText("");
					jTextFieldComplemento.setText("");
					jTextFieldBairro.setText("");
					jTextFieldCep.setText("");
					jTextFieldCidade.setText("");
					jTextFieldEstado.setText("");

				} else {
					jInternalFramePrincipal.setVisible(false);

				}
			}
		});
		jRadioButtonCadastrar.setBounds(6, 30, 220, 23);
		contentPane.add(jRadioButtonCadastrar);

		// BOTAO NOVO!
		jButtonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;

				// Botoes
				jButtonSalvar.setEnabled(true);
				jButtonEditar.setEnabled(false);
				jButtonCancelar.setEnabled(true);
				jButtonDeletar.setEnabled(false);

				// Campos de formulario
				jTextFieldDataCadastro.setEnabled(false);
				jTextFieldCod_Proprietario.setEnabled(false);
				jTextFieldNomeProprietario.setEnabled(true);
				jTextFieldLote.setEnabled(true);
				jTextFieldQuadra.setEnabled(true);
				jFormattedCpf_proprietario.setEnabled(true);
				jFormattedFoneProprietario.setEnabled(true);
				jTextFieldEmailProprietario.setEnabled(true);
				jTextFieldEnderecoProprietario.setEnabled(true);
				jTextFieldNumeroEndereco.setEnabled(true);
				jTextFieldComplemento.setEnabled(true);
				jTextFieldBairro.setEnabled(true);
				jTextFieldCep.setEnabled(true);
				jTextFieldCidade.setEnabled(true);
				jTextFieldEstado.setEnabled(true);
				jCheckBoxEndereco.setEnabled(true);

				// limpar campos do formulario
				jTextFieldDataCadastro.setText(data);
				;
				jTextFieldCod_Proprietario.setText("");
				jTextFieldNomeProprietario.setText("");
				jTextFieldLote.setText("");
				jTextFieldQuadra.setText("");
				jFormattedCpf_proprietario.setText("");
				jFormattedFoneProprietario.setText("");
				jTextFieldEmailProprietario.setText("");
				jTextFieldEnderecoProprietario.setText("");
				jTextFieldNumeroEndereco.setText("");
				jTextFieldComplemento.setText("");
				jTextFieldBairro.setText("");
				jTextFieldCep.setText("");
				jTextFieldCidade.setText("");
				jTextFieldEstado.setText("");

			}
		});

		// BOTAO SALVAR
		jButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Salvar dados Proprietario

				if (flag == 1) {
					mod.setNome_proprietario(jTextFieldNomeProprietario.getText());
					mod.setLote_proprietario(jTextFieldLote.getText());
					mod.setQuadra_proprietario(jTextFieldQuadra.getText());
					mod.setCpf_proprietario(jFormattedCpf_proprietario.getText());
					mod.setTelefone_proprietario(jFormattedFoneProprietario.getText());
					mod.setEmail_proprietario(jTextFieldEmailProprietario.getText());
					mod.setData_cadastro(jTextFieldDataCadastro.getText());
					mod.setEndereco(jTextFieldEnderecoProprietario.getText());
					mod.setNumero_endereco(jTextFieldNumeroEndereco.getText());
					mod.setComplemento(jTextFieldComplemento.getText());
					mod.setBairro(jTextFieldBairro.getText());
					mod.setCep(jTextFieldCep.getText());
					mod.setCidade(jTextFieldCidade.getText());
					mod.setEstado(jTextFieldEstado.getText());
					control.Salvar(mod);
					preencherTabelaProprietario("select * from proprietarios order by nome_proprietario");
					jInternalFramePrincipal.dispose();
					jInternalFrameCadastroEndereco.dispose();

					// Botoes
					jButtonNovo.setEnabled(true);
					jButtonSalvar.setEnabled(true);
					jButtonEditar.setEnabled(false);
					jButtonCancelar.setEnabled(true);
					jButtonDeletar.setEnabled(false);

					// Campos de Formularios ativos ou nao
					jTextFieldDataCadastro.setEnabled(false);
					jTextFieldCod_Proprietario.setEnabled(false);
					jTextFieldNomeProprietario.setEnabled(true);
					jTextFieldLote.setEnabled(true);
					jTextFieldQuadra.setEnabled(true);
					jFormattedCpf_proprietario.setEnabled(true);
					jFormattedFoneProprietario.setEnabled(true);
					jTextFieldEmailProprietario.setEnabled(true);
					jTextFieldEnderecoProprietario.setEnabled(true);
					jTextFieldNumeroEndereco.setEnabled(true);
					jTextFieldComplemento.setEnabled(true);
					jTextFieldBairro.setEnabled(true);
					jTextFieldCep.setEnabled(true);
					jTextFieldCidade.setEnabled(true);
					jTextFieldEstado.setEnabled(true);
					// jCheckBoxEndereco.setEnabled(true);

					// Campos de formulario
					jTextFieldPesquisa.setVisible(true);

					jInternalFramePrincipal.dispose();
					jInternalFrameCadastroEndereco.dispose();
					jTableProprietarios.setVisible(true);
					// linha de correcao de bug! - nao excluir
					jTextFieldPesquisa.setVisible(true); // linha de correcao de bug! - nao excluir
					buttonGroupEndereco.clearSelection();
					buttonGroupCadastro.clearSelection();
					preencherTabelaProprietario("select * from proprietarios order by nome_proprietario");
				} else {
					if (flag == 3) {
						mod.setCodigo_proprietario(Integer.parseInt(jTextFieldCod_Proprietario.getText()));
						mod.setNome_proprietario(jTextFieldNomeProprietario.getText());
						mod.setLote_proprietario(jTextFieldLote.getText());
						mod.setQuadra_proprietario(jTextFieldQuadra.getText());
						mod.setCpf_proprietario(jFormattedCpf_proprietario.getText());
						mod.setTelefone_proprietario(jFormattedFoneProprietario.getText());
						mod.setEmail_proprietario(jTextFieldEmailProprietario.getText());
						mod.setData_cadastro(jTextFieldDataCadastro.getText());
						mod.setEndereco("");
						mod.setNumero_endereco("");
						mod.setComplemento("");
						mod.setBairro("");
						mod.setCep("");
						mod.setCidade("");
						mod.setEstado("");
						control.Alterar(mod);
						preencherTabelaProprietario("select * from proprietarios order by nome_proprietario");
						jInternalFramePrincipal.dispose();
						jInternalFrameCadastroEndereco.dispose();

						// Botoes
						jButtonNovo.setEnabled(true);
						jButtonSalvar.setEnabled(false);
						jButtonEditar.setEnabled(false);
						jButtonCancelar.setEnabled(true);
						jButtonDeletar.setEnabled(false);

						// Campos de Formularios ativos ou nao
						jTextFieldDataCadastro.setEnabled(false);
						jTextFieldCod_Proprietario.setEnabled(false);
						jTextFieldNomeProprietario.setEnabled(true);
						jTextFieldLote.setEnabled(true);
						jTextFieldQuadra.setEnabled(true);
						jFormattedCpf_proprietario.setEnabled(true);
						jFormattedFoneProprietario.setEnabled(true);
						jTextFieldEmailProprietario.setEnabled(true);
						jTextFieldEnderecoProprietario.setEnabled(true);
						jTextFieldNumeroEndereco.setEnabled(true);
						jTextFieldComplemento.setEnabled(true);
						jTextFieldBairro.setEnabled(true);
						jTextFieldCep.setEnabled(true);
						jTextFieldCidade.setEnabled(true);
						jTextFieldEstado.setEnabled(true);
						// jCheckBoxEndereco.setEnabled(true);
						jTextFieldPesquisa.setVisible(true); // antibug

						jInternalFramePrincipal.dispose();
						jInternalFrameCadastroEndereco.dispose();
						jTableProprietarios.setVisible(true);
						// linha de correcao de bug! - nao excluir
						jTextFieldPesquisa.setVisible(true); // linha de correcao de bug! - nao excluir
						buttonGroupEndereco.clearSelection();
						buttonGroupCadastro.clearSelection();
						preencherTabelaProprietario("select * from proprietarios order by nome_proprietario");
					} else {
						mod.setCodigo_proprietario(Integer.parseInt(jTextFieldCod_Proprietario.getText()));
						mod.setNome_proprietario(jTextFieldNomeProprietario.getText());
						mod.setLote_proprietario(jTextFieldLote.getText());
						mod.setQuadra_proprietario(jTextFieldQuadra.getText());
						mod.setCpf_proprietario(jFormattedCpf_proprietario.getText());
						mod.setTelefone_proprietario(jFormattedFoneProprietario.getText());
						mod.setEmail_proprietario(jTextFieldEmailProprietario.getText());
						mod.setData_cadastro(jTextFieldDataCadastro.getText());
						mod.setEndereco(jTextFieldEnderecoProprietario.getText());
						mod.setNumero_endereco(jTextFieldNumeroEndereco.getText());
						mod.setComplemento(jTextFieldComplemento.getText());
						mod.setBairro(jTextFieldBairro.getText());
						mod.setCep(jTextFieldCep.getText());
						mod.setCidade(jTextFieldCidade.getText());
						mod.setEstado(jTextFieldEstado.getText());
						control.Alterar(mod);
						preencherTabelaProprietario("select * from proprietarios order by nome_proprietario");
						jInternalFramePrincipal.dispose();
						jInternalFrameCadastroEndereco.dispose();

						// Botoes
						jButtonNovo.setEnabled(true);
						jButtonSalvar.setEnabled(false);
						jButtonEditar.setEnabled(false);
						jButtonCancelar.setEnabled(true);
						jButtonDeletar.setEnabled(false);

						// Campos de Formularios ativos ou nao
						jTextFieldDataCadastro.setEnabled(false);
						jTextFieldCod_Proprietario.setEnabled(false);
						jTextFieldNomeProprietario.setEnabled(true);
						jTextFieldLote.setEnabled(true);
						jTextFieldQuadra.setEnabled(true);
						jFormattedCpf_proprietario.setEnabled(true);
						jFormattedFoneProprietario.setEnabled(true);
						jTextFieldEmailProprietario.setEnabled(true);
						jTextFieldEnderecoProprietario.setEnabled(true);
						jTextFieldNumeroEndereco.setEnabled(true);
						jTextFieldComplemento.setEnabled(true);
						jTextFieldBairro.setEnabled(true);
						jTextFieldCep.setEnabled(true);
						jTextFieldCidade.setEnabled(true);
						jTextFieldEstado.setEnabled(true);
						// jCheckBoxEndereco.setEnabled(true);
						jTextFieldPesquisa.setVisible(true); // antibug

						jInternalFramePrincipal.dispose();
						jInternalFrameCadastroEndereco.dispose();
						jTableProprietarios.setVisible(true);
						// linha de correcao de bug! - nao excluir
						jTextFieldPesquisa.setVisible(true); // linha de correcao de bug! - nao excluir
						buttonGroupEndereco.clearSelection();
						buttonGroupCadastro.clearSelection();
						preencherTabelaProprietario("select * from proprietarios order by nome_proprietario");
					}
				}

			}
		});

		// BOTAO EDITAR
		jButtonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;

				// Botoes
				jButtonNovo.setEnabled(false);
				jButtonSalvar.setEnabled(true);
				jButtonEditar.setEnabled(false);
				jButtonCancelar.setEnabled(true);
				jButtonDeletar.setEnabled(true);

				// Campos de Formularios ativos ou nao
				jTextFieldDataCadastro.setEnabled(false);
				jTextFieldCod_Proprietario.setEnabled(false);
				jTextFieldNomeProprietario.setEnabled(true);
				jTextFieldLote.setEnabled(true);
				jTextFieldQuadra.setEnabled(true);
				jFormattedCpf_proprietario.setEnabled(true);
				jFormattedFoneProprietario.setEnabled(true);
				jTextFieldEmailProprietario.setEnabled(true);
				jTextFieldEnderecoProprietario.setEnabled(true);
				jTextFieldNumeroEndereco.setEnabled(true);
				jTextFieldComplemento.setEnabled(true);
				jTextFieldBairro.setEnabled(true);
				jTextFieldCep.setEnabled(true);
				jTextFieldCidade.setEnabled(true);
				jTextFieldEstado.setEnabled(true);
				jCheckBoxEndereco.setEnabled(true);

				if (jInternalFrameCadastroEndereco.isClosed()) {
					flag = 3;
				}
			}
		});

		// BOTAO CANCELAR
		jButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Janela
				jInternalFrameCadastroEndereco.setVisible(false);

				// Botoes
				jButtonNovo.setEnabled(true);
				jButtonSalvar.setEnabled(false);
				jButtonEditar.setEnabled(false);
				jButtonCancelar.setEnabled(false);
				jButtonDeletar.setEnabled(false);

				// Campos de Formularios ativos ou nao
				jTextFieldDataCadastro.setEnabled(false);
				jTextFieldCod_Proprietario.setEnabled(false);
				jTextFieldNomeProprietario.setEnabled(false);
				jTextFieldLote.setEnabled(false);
				jTextFieldQuadra.setEnabled(false);
				jFormattedCpf_proprietario.setEnabled(false);
				jFormattedFoneProprietario.setEnabled(false);
				jTextFieldEmailProprietario.setEnabled(false);
				jTextFieldEnderecoProprietario.setEnabled(false);
				jTextFieldNumeroEndereco.setEnabled(false);
				jTextFieldComplemento.setEnabled(false);
				jTextFieldBairro.setEnabled(false);
				jTextFieldCep.setEnabled(false);
				jTextFieldCidade.setEnabled(false);
				jTextFieldEstado.setEnabled(false);
				jCheckBoxEndereco.setEnabled(false);
				jCheckBoxEndereco.setSelected(false);

				// limpar campos do formulario
				jTextFieldDataCadastro.setText("");
				jTextFieldCod_Proprietario.setText("");
				jTextFieldNomeProprietario.setText("");
				jTextFieldLote.setText("");
				jTextFieldQuadra.setText("");
				jFormattedCpf_proprietario.setText("");
				jFormattedFoneProprietario.setText("");
				jTextFieldEmailProprietario.setText("");
				jTextFieldEnderecoProprietario.setText("");
				jTextFieldNumeroEndereco.setText("");
				jTextFieldComplemento.setText("");
				jTextFieldBairro.setText("");
				jTextFieldCep.setText("");
				jTextFieldCidade.setText("");
				jTextFieldEstado.setText("");
			}
		});

		// BOTAO DELETAR
		jButtonDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirma = 0;
				confirma = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excuir o proprietario?");
				if (confirma == JOptionPane.YES_OPTION) {
					mod.setCodigo_proprietario(Integer.parseInt(jTextFieldCod_Proprietario.getText()));
					control.Excluir(mod);

					// Botoes
					jButtonNovo.setEnabled(true);
					jButtonSalvar.setEnabled(false);
					jButtonEditar.setEnabled(false);
					jButtonCancelar.setEnabled(!false);
					jButtonDeletar.setEnabled(false);

					// Campos de Formularios ativos ou nao
					jTextFieldDataCadastro.setEnabled(false);
					jTextFieldCod_Proprietario.setEnabled(false);
					jTextFieldNomeProprietario.setEnabled(false);
					jTextFieldLote.setEnabled(false);
					jTextFieldQuadra.setEnabled(false);
					jFormattedCpf_proprietario.setEnabled(false);
					jFormattedFoneProprietario.setEnabled(false);
					jTextFieldEmailProprietario.setEnabled(false);
					jTextFieldEnderecoProprietario.setEnabled(false);
					jTextFieldNumeroEndereco.setEnabled(false);
					jTextFieldComplemento.setEnabled(false);
					jTextFieldBairro.setEnabled(false);
					jTextFieldCep.setEnabled(false);
					jTextFieldCidade.setEnabled(false);
					jTextFieldEstado.setEnabled(false);
					jCheckBoxEndereco.setEnabled(false);
					jTableProprietarios.setVisible(true);
					jTextFieldPesquisa.setVisible(true);

					// limpar campos do formulario
					jTextFieldDataCadastro.setText("");
					jTextFieldCod_Proprietario.setText("");
					jTextFieldNomeProprietario.setText("");
					jTextFieldLote.setText("");
					jTextFieldQuadra.setText("");
					jFormattedCpf_proprietario.setText("");
					jFormattedFoneProprietario.setText("");
					jTextFieldEmailProprietario.setText("");
					jTextFieldEnderecoProprietario.setText("");
					jTextFieldNumeroEndereco.setText("");
					jTextFieldComplemento.setText("");
					jTextFieldBairro.setText("");
					jTextFieldCep.setText("");
					jTextFieldCidade.setText("");
					jTextFieldEstado.setText("");

					// Fechar janelas
					jInternalFramePrincipal.setVisible(false);
					jInternalFrameCadastroEndereco.setVisible(false);

					preencherTabelaProprietario("select * from proprietarios order by nome_proprietario");
				}
			}
		});

	}
}
