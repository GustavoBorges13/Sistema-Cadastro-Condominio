package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelEclipse.BeansUsuario;

public class ControleUsuario {

	// conectando no banco de dados (-> Classe ConexaoBD)
	// conexaoBD eh o banco de dados, conectando em um objeto "conex"
	ConexaoBD conex = new ConexaoBD();
	// instaciando beans usuario
	BeansUsuario mod = new BeansUsuario();

	// metodo Salvar/Gravar no banco de dados
	public void Salvar(BeansUsuario mod) {

		conex.conexao();
		// Statement faz a pesquisa
		// objeto pst
		// statement criou na classe ConexaoBD passando o stm, stm eh responsanvel por
		// fazer a pesquisa no banco de dados..
		// fazendo uma pesquisa atraves do string sql
		// vamos usar o insert para inserir dados na tabela
		// vamos inserir 4 colunas no insert
		try {
			PreparedStatement pst = conex.con.prepareStatement(
					"insert into usuarios(usu_nome, login_usuario, tipo_usuario, senha_usuario) values(?, ?, ?, ?)");
			pst.setString(1, mod.getUsu_nome());
			pst.setString(2, mod.getLogin_usuario());
			pst.setString(3, mod.getTipo_usuario());
			pst.setString(4, mod.getSenha_usuario());
			pst.execute();

			JOptionPane.showMessageDialog(null, "Usuario salvo com SUCESSO!! ");
		} catch (SQLException ex) {

			// menssagem de erro
			JOptionPane.showMessageDialog(null, "Erro ao Salvar Usuario!\nErro: " + ex);
		}
		conex.desconectar();

	}
	
	
	public void Editar(BeansUsuario mod) {
		conex.conexao();
		
		try {
			PreparedStatement pst = conex.con.prepareStatement("update usuarios set usu_nome=?, login_usuario=?, tipo_usuario=?, senha_usuario=? where usu_cod=?");
			pst.setString(1, mod.getUsu_nome());
			pst.setString(2, mod.getLogin_usuario());
			pst.setString(3, mod.getTipo_usuario());
			pst.setString(4, mod.getSenha_usuario());
			pst.setInt(5, mod.getUsu_cod());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Usuário Alterado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao Alterar Usuário!\nErro: "+ex);
		}
		
		
		conex.desconectar();
	}

	public void Excluir(BeansUsuario mod) {
		conex.conexao();
		
		try {
			PreparedStatement pst = conex.con.prepareStatement("delete from usuarios where usu_cod=?");
			pst.setInt(1, mod.getUsu_cod());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Usuário Excluído com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao Excluir Usuário!\nErro: "+ex);
		}
		
		conex.desconectar();
	}
	
	public BeansUsuario buscaUsuario(BeansUsuario mod) {// Sistema de busca de usuário
		conex.conexao();

		conex.executaSQL("select * From usuarios where usu_nome like'%"+mod.getPesquisa()+ "%'");
		try {
			conex.rs.last();
			mod.setUsu_cod(conex.rs.getInt("usu_cod"));
			mod.setUsu_nome(conex.rs.getString("usu_nome"));
			mod.setLogin_usuario(conex.rs.getString("login_usuario"));
			mod.setTipo_usuario(conex.rs.getString("tipo_usuario"));
			mod.setSenha_usuario(conex.rs.getString("senha_usuario"));
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Usuário não Cadastrado!\nErro: "+ex);
		}

		conex.desconectar();
		return mod;
	}
}