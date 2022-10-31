package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelEclipse.BeansProprietario;

public class ControleProprietario {
	// conectando no banco de dados (-> Classe ConexaoBD)
	// conexaoBD eh o banco de dados, conectando em um objeto "conex"
	ConexaoBD conex = new ConexaoBD();
	// instaciando beans usuario
	BeansProprietario mod = new BeansProprietario();

	// metodo Salvar/Gravar no banco de dados
	public void Salvar(BeansProprietario mod) {

		conex.conexao();
		// Statement faz a pesquisa
		// objeto pst
		// statement criou na classe ConexaoBD passando o stm, stm eh responsanvel por
		// fazer a pesquisa no banco de dados..
		// fazendo uma pesquisa atraves do string sql
		// vamos usar o insert para inserir dados na tabela
		// vamos inserir 14 colunas no insert
		try {
			PreparedStatement pst = conex.con.prepareStatement(""
							+ "insert into proprietarios (nome_proprietario, lote_proprietario, "
							+ "quadra_proprietario, cpf_proprietario, "
							+ "telefone_proprietario, email_proprietario, "
							+ "data_cadastro, endereco, numero_endereco, "
							+ "complemento, bairro, cep, cidade, estado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pst.setString(1, mod.getNome_proprietario());
			pst.setString(2, mod.getLote_proprietario());
			pst.setString(3, mod.getQuadra_proprietario());
			pst.setString(4, mod.getCpf_proprietario());
			pst.setString(5, mod.getTelefone_proprietario());
			pst.setString(6, mod.getEmail_proprietario());
			pst.setString(7, mod.getData_cadastro());
			pst.setString(8, mod.getEndereco());
			pst.setString(9, mod.getNumero_endereco());
			pst.setString(10, mod.getComplemento());
			pst.setString(11, mod.getBairro());
			pst.setString(12, mod.getCep());
			pst.setString(13, mod.getCidade());
			pst.setString(14, mod.getEstado());
			pst.execute();

			JOptionPane.showMessageDialog(null, "Proprietario salvo com SUCESSO!! ");
		} catch (SQLException ex) {

			// menssagem de erro
			JOptionPane.showMessageDialog(null, "Erro ao Salvar Proprietario!\nErro: " + ex);
		}
		conex.desconectar();

	}
	
	public void Alterar(BeansProprietario mod) {
		conex.conexao();
		try {
			PreparedStatement pst = conex.con.prepareStatement("update proprietarios set "
					+ "nome_proprietario = ?, lote_proprietario = ?, quadra_proprietario = ?,"
					+ "cpf_proprietario = ?, telefone_proprietario = ?, email_proprietario = ?,"
					+ "data_alteracao = ?, endereco = ?, numero_endereco = ?, complemento = ?,"
					+ "bairro = ?, cep = ?, cidade = ?, estado = ? where id_proprietario = ?");
			pst.setString(1, mod.getNome_proprietario());
			pst.setString(2, mod.getLote_proprietario());
			pst.setString(3, mod.getQuadra_proprietario());
			pst.setString(4, mod.getCpf_proprietario());
			pst.setString(5, mod.getTelefone_proprietario());
			pst.setString(6, mod.getEmail_proprietario());
			pst.setString(7, mod.getData_alteracao());
			pst.setString(8, mod.getEndereco());
			pst.setString(9, mod.getNumero_endereco());
			pst.setString(10, mod.getComplemento());
			pst.setString(11, mod.getBairro()); 
			pst.setString(12, mod.getCep()); 
			pst.setString(13, mod.getCidade()); 
			pst.setString(14, mod.getEstado()); 
			pst.setInt(15, mod.getCodigo_proprietario());
			pst.execute();
			
			JOptionPane.showMessageDialog(null, "Dados Alterados com sucesso!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar os dados!\nErro: "+ex);
		}
		conex.desconectar();
	}
	
	
	public void Excluir(BeansProprietario mod) {
		conex.conexao();
		PreparedStatement pst;
		try {
			pst = conex.con.prepareStatement("delete from proprietarios where id_proprietario = ?");
			pst.setInt(1, mod.getCodigo_proprietario());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Exclusão concluída com sucesso!");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Exclusão concluída com sucesso!\nErro:" + ex);
		}
		
		
		conex.desconectar();
	}
	
	public BeansProprietario buscarProprietario(BeansProprietario mod) {
		conex.conexao();
		conex.executaSQL("select * From proprietarios where nome_proprietario like '%"+mod.getPesquisa()+"%'");
		try {
			conex.rs.last();
			mod.setCodigo_proprietario(conex.rs.getInt("id_proprietario"));
			mod.setNome_proprietario(conex.rs.getString("nome_proprietario"));
			mod.setLote_proprietario(conex.rs.getString("lote_proprietario"));
			mod.setQuadra_proprietario(conex.rs.getString("quadra_proprietario"));
			mod.setCpf_proprietario(conex.rs.getString("cpf_proprietario"));
			mod.setTelefone_proprietario(conex.rs.getString("telefone_proprietario"));
			mod.setEmail_proprietario(conex.rs.getString("email_proprietario"));
			mod.setData_cadastro(conex.rs.getString("data_cadastro"));
			mod.setEndereco(conex.rs.getString("endereco"));
			mod.setNumero_endereco(conex.rs.getString("numero_endereco"));
			mod.setComplemento(conex.rs.getString("complemento"));
			mod.setBairro(conex.rs.getString("bairro"));
			mod.setCep(conex.rs.getString("cep"));
			mod.setCidade(conex.rs.getString("cidade"));
			mod.setEstado(conex.rs.getString("estado"));
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Proprietário não Cadastrado!\nErro: "+ex);
		}

		conex.desconectar();
		return mod;
	}
	

}
