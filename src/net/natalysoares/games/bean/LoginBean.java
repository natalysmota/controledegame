package net.natalysoares.games.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import net.natalysoares.games.domain.Usuario;
import net.natalysoares.games.util.SessionUtil;

@RequestScoped
@ManagedBean(name = "MBLogin")
public class LoginBean {

	private Usuario usuario = new Usuario();
	private String nomeUsuario;
	private String senhaUsuario;
	
	//============GETTERS/SETTERS===========//
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	//================MÉTODOS============//
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if("admin".equals(this.nomeUsuario) && "admin".equals(this.senhaUsuario)) {
			System.out.println("Usuario conectou.");
			
			Object b = new Object();
			SessionUtil.setParam("UsuarioLogado", b);

			return "principal?faces-redirect=true";
		} else {
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválido(s).");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		return null;
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		System.out.println("Usuário desconectou.");
		
		return "login?faces-redirect=true";
	}
}