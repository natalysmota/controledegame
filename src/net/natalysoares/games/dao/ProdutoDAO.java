package net.natalysoares.games.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.natalysoares.games.domain.Fabricante;
import net.natalysoares.games.domain.Produto;
import net.natalysoares.games.factory.ConexaoFactory;

public class ProdutoDAO {

	public void salvar(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, preco, quantidade, codigo_fabricante) ");
		sql.append("VALUES (?, ?, ?, ?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, produto.getDescricao());
		comando.setDouble(2, produto.getPreco());
		comando.setLong(3, produto.getQuantidade());
		comando.setLong(4, produto.getFabricante().getCodigo()); //Composição
		
		comando.executeUpdate();
	}
	
	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f ON f.codigo = p.codigo_fabricante");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		//Quando a conexao é fechada, os dados são perdidos
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produto> itens = new ArrayList<Produto>();
		
		while(resultado.next()) {
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("f.codigo"));
			fabricante.setDescricao(resultado.getString("f.descricao"));
			
			Produto produto = new Produto();
			produto.setCodigo(resultado.getLong("p.codigo"));
			produto.setDescricao(resultado.getString("p.descricao"));
			produto.setPreco(resultado.getDouble("p.preco"));
			produto.setQuantidade(resultado.getLong("p.quantidade"));
			produto.setFabricante(fabricante);
			
			itens.add(produto);
		}

		return itens;
	}
	
	public void excluir(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM produto ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, produto.getCodigo());

		comando.executeUpdate();
	}
	
	public void editar(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE produto ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, codigo_fabricante = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, produto.getDescricao());
		comando.setDouble(2, produto.getPreco());
		comando.setLong(3, produto.getQuantidade());
		comando.setLong(4, produto.getFabricante().getCodigo());
		comando.setLong(5, produto.getCodigo());
		
		comando.executeUpdate();		
	}
}