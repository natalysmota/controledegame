package net.natalysoares.games.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.natalysoares.games.domain.Fabricante;
import net.natalysoares.games.factory.ConexaoFactory;

public class FabricanteDAO {

	public void salvar(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante "); //Serve para juntar strings
		sql.append("(descricao) ");
		sql.append("VALUES (?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		//Permite executar comandos SQL
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, fabricante.getDescricao()); //Primeira interrogação
	
		comando.executeUpdate();
	}
	
	public void excluir(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, fabricante.getCodigo());
		
		comando.executeUpdate();
	}
	
	public void editar(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, fabricante.getDescricao());
		comando.setLong(2, fabricante.getCodigo());
		
		comando.executeUpdate();
	}
	
	public Fabricante buscarPorCodigo(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, fabricante.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		
		Fabricante retorno = null;
		
		if(resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno;
	}
	
	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		while(resultado.next()) {
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("Codigo"));
			fabricante.setDescricao(resultado.getString("descricao"));

			lista.add(fabricante);
		}
		
		return lista;
	}
	
	public ArrayList<Fabricante> buscarPorDescricao(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + fabricante.getDescricao() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		while(resultado.next()) {
			Fabricante item = new Fabricante();
			item.setCodigo(resultado.getLong("Codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);
		}
		
		return lista;
	}
	
	public static void main(String[] args) {
		
		//CADASTRAR FABRICANTE
		/* Fabricante f1 = new Fabricante();
		f1.setDescricao("Sony");
		
		Fabricante f2 = new Fabricante();
		f2.setDescricao("Samsung");
		
		FabricanteDAO dao = new FabricanteDAO();
		
		try {
			dao.salvar(f1);
			dao.salvar(f2);
			System.out.println("Novos fabricantes foram salvo com sucesso.");
		} catch (SQLException e) {
			System.out.println("Fabricante não cadastrado.");
			e.printStackTrace();
		} */
		
		//EXCLUIR FABRICANTE
		/* Fabricante f1 = new Fabricante();
		f1.setCodigo(2L);

		FabricanteDAO dao = new FabricanteDAO();
		try {
			dao.excluir(f1);
			System.out.println("O fabricante foi removido com sucesso!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar excluir um fabricante.");
			e.printStackTrace();
		} */
		
		//EDITAR FABRICANTE
		/* Fabricante f1 = new Fabricante();
		f1.setCodigo(1L);
		f1.setDescricao("DESCRIÇÃO ALTERADA");
		
		FabricanteDAO dao = new FabricanteDAO();
		try {
			dao.editar(f1);
			System.out.println("O fabricante foi editado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar editar o fabricante.");
			e.printStackTrace();
		} */
		
		//PESQUISAR FABRICANTE POR CÓDIGO
		/* Fabricante f1 = new Fabricante();
		f1.setCodigo(1L);
		
		Fabricante f2 = new Fabricante();
		f2.setCodigo(2L);
		
		FabricanteDAO dao = new FabricanteDAO();
		
		try {
			Fabricante f3 = dao.buscarPorCodigo(f1);
			Fabricante f4 = dao.buscarPorCodigo(f2);
			
			System.out.println("Resultado 1:" + f3);
			System.out.println("Resultado 2:" + f4);
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar pesquisar fabricante.");
			e.printStackTrace();
		} */
		
		//PESQUISA TUDO DE FABRICANTE
		/* FabricanteDAO dao = new FabricanteDAO();
		try {
			ArrayList<Fabricante> lista = dao.listar();
			for(Fabricante fabricante : lista) {
				System.out.println("Resultado de busca: " + fabricante);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar listgar os fabricantes.");
			e.printStackTrace();
		} */
		
		//PESQUISAR FABRICANTE POR DESCRICAO
		/* Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Alterada");
		
		FabricanteDAO dao = new FabricanteDAO();
		
		try {
			ArrayList<Fabricante> lista = dao.buscarPorDescricao(fabricante);
		
			for(Fabricante f : lista) {
				System.out.println("Resultado: " + f);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao listar fabricantes por descrição.");
			e.printStackTrace();
		} */
	} 
}