package net.natalysoares.games.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import net.natalysoares.games.dao.ProdutoDAO;
import net.natalysoares.games.domain.Fabricante;
import net.natalysoares.games.domain.Produto;

public class ProdutoDAOTeste {
	
	@Test
	@Ignore
	public void salvar() throws SQLException {
		Produto produto = new Produto();
		produto.setDescricao("Need For Speed");
		produto.setPreco(250.00);
		produto.setQuantidade(5L);
		
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(4L);
		
		produto.setFabricante(fabricante);
		
		ProdutoDAO prodDao = new ProdutoDAO();
		
		prodDao.salvar(produto);
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException {
		ProdutoDAO prodDao = new ProdutoDAO();
		
		ArrayList<Produto> lista = prodDao.listar();
		
		for(Produto p : lista) {
			System.out.println("Código do produto: " + p.getCodigo());
			System.out.println("Descricao do produto: " + p.getDescricao());
			System.out.println("Preço: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Código do fabricante: " + p.getFabricante().getCodigo());
			System.out.println("Descrição do fabricante: " + p.getFabricante().getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException {
		Produto produto = new Produto();
		produto.setCodigo(1L);
		
		ProdutoDAO prodDao = new ProdutoDAO();
		prodDao.excluir(produto);
	}

	@Test
	@Ignore
	public void editar() throws SQLException {
		Produto produto = new Produto();
		produto.setCodigo(2L);
		produto.setDescricao("Rainbow Six Vegas 2");
		produto.setPreco(125.00);
		produto.setQuantidade(7L);
		
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(4L);
		
		produto.setFabricante(fabricante);
		
		ProdutoDAO prodDao = new ProdutoDAO();
		prodDao.editar(produto);
	}
}