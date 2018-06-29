package net.natalysoares.games.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	//Atributo constante
	private static final String USUARIO = "root";
	private static final String SENHA = "jsfprimefaces3306";
	private static final String URL = "jdbc:mysql://localhost:3306/drogaria";
	
	//Quem chamar a conexao trata o erro
	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conectou com sucesso!");
		} catch (SQLException e) {
			System.out.println("Não foi possível realizar a conexão.");
		}
	}
}