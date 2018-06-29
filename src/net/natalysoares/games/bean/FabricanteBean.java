package net.natalysoares.games.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.natalysoares.games.dao.FabricanteDAO;
import net.natalysoares.games.domain.Fabricante;
import net.natalysoares.games.util.JsfUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	
	private ArrayList<Fabricante> itens; //Gerais
	private ArrayList<Fabricante> itensFiltrados; //Filtrados
	private Fabricante fabricante;

	//==========GET/SET===========//
	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	//===========ACTIONS==========//
	@PostConstruct //Método será chamado antes da página ser desenhada
	public void prepararPesquisa() {
		try {
			FabricanteDAO fabDao = new FabricanteDAO();
			itens = fabDao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	//Chamado antes do método novo
	public void prepararNovo() {
		fabricante = new Fabricante();
	}

	public void novo() {
		try {
			FabricanteDAO fabDao = new FabricanteDAO();
			fabDao.salvar(fabricante);
			
			//Recarrega
			itens = fabDao.listar();

			JsfUtil.adicionarMensagemSucesso("Fabricante salvo com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			FabricanteDAO fabDao = new FabricanteDAO();
			fabDao.excluir(fabricante);
			
			//Recarrega
			itens = fabDao.listar();
			
			JsfUtil.adicionarMensagemSucesso("Fabricante removido com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			FabricanteDAO fabDao = new FabricanteDAO();
			fabDao.editar(fabricante);
			
			itens = fabDao.listar();
			
			JsfUtil.adicionarMensagemSucesso("Fabricante editado com sucesso.");
		} catch(SQLException e) {
			e.printStackTrace();
			JsfUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}