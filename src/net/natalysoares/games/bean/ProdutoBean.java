package net.natalysoares.games.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.natalysoares.games.dao.FabricanteDAO;
import net.natalysoares.games.dao.ProdutoDAO;
import net.natalysoares.games.domain.Fabricante;
import net.natalysoares.games.domain.Produto;
import net.natalysoares.games.util.JsfUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;
	private Produto produto;
	private ArrayList<Fabricante> comboFabricantes;

	//=============GETTERS/SETTERS=============//
	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Fabricante> getComboFabricantes() {
		return comboFabricantes;
	}

	public void setComboFabricantes(ArrayList<Fabricante> comboFabricantes) {
		this.comboFabricantes = comboFabricantes;
	}

	//=================MÉTODOS================//
	public void carregarListagem() {
		try {
			ProdutoDAO prodDao = new ProdutoDAO();
			itens = prodDao.listar();
		} catch(SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararNovo() {
		try {
			produto = new Produto();
			FabricanteDAO fabDao = new FabricanteDAO();
			comboFabricantes = fabDao.listar();
		} catch(SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void novo() {
		try {
			ProdutoDAO prodDao = new ProdutoDAO();
			prodDao.salvar(produto);

			itens = prodDao.listar();
			JsfUtil.adicionarMensagemSucesso("Produto salvo com sucesso.");
		} catch(SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutoDAO prodDao = new ProdutoDAO();
			prodDao.excluir(produto);

			itens = prodDao.listar();
			JsfUtil.adicionarMensagemSucesso("Produto excluído com sucesso.");
		} catch(SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararEditar() {
		try {
			FabricanteDAO fabDao = new FabricanteDAO();
			comboFabricantes = fabDao.listar();
		} catch(SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO prodDao = new ProdutoDAO();
			prodDao.editar(produto);

			itens = prodDao.listar();
			JsfUtil.adicionarMensagemSucesso("Produto editado com sucesso.");
		} catch(SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}