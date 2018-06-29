package net.natalysoares.games.domain;

public class Produto {

	private Long codigo;
	private String descricao;
	private Long quantidade;
	private Double preco;
	
	//Sempre que criar um produto, vai criar um fabricante junto
	private Fabricante fabricante = new Fabricante(); //Composição 1:N

	// ===========GETTERS/SETTERS===========//
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}