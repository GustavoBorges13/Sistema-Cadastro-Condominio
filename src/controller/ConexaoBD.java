package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexaoBD {
	public Statement stm;
	public ResultSet rs;
	private String driver = "org.postgresql.Driver";
	private String local = "jdbc:postgresql://localhost:5432/condominio_bd";
	private String usuario = "postgres";//usuario do sql
	private String password = "admin";//senha do sql
	public Connection con;
	
	public void conexao () {
		System.setProperty("jdbc.Drivers", driver);//responsavel por setar a propriedade do driver de conexão
		
		try {
			con = DriverManager.getConnection(local, usuario, password);
			//JOptionPane.showMessageDialog(null, "Sistema conectado com sucesso");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar com o sistema\n" + ex.getMessage());
		}
		
	}
	
	@SuppressWarnings("static-access")
	public void executaSQL(String sql) {
		try {
			stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao executar pesquisa\n Erro:" + ex.getMessage());
		}
		
	}
	
	public void desconectar() {//desconecta do banco de dados
		try {
			con.close();
			//JOptionPane.showMessageDialog(null, "Sistema desconectado com sucesso");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao desconectar com o sistema\n" + ex.getMessage());
		}
	}
}