package dw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author UFG
 *
 */
public class ClienteModel {

	private String codigo;
	private String nome;
	private Integer matricula;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	private static Connection obterConexao() throws SQLException {
		// Estabelecer uma conexão com o banco de dados.
		String url = "jdbc:derby://localhost:1527/vendadb;create=true";
		String user = "app";
		String password = "app";
		return DriverManager.getConnection(url, user, password);
	}

	public void incluir() throws SQLException {
		Connection conn = obterConexao();

		// Obter sentença SQL.
		String sql = "insert into cliente (codigo, nome, matricula) values (?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, codigo);
		pstmt.setString(2, nome);
		pstmt.setInt(3, matricula);
		pstmt.execute();
	}

	public void salvar() throws SQLException {
		Connection conn = obterConexao();

		// Obter sentença SQL.
		String sql = "update cliente set nome = ?, matricula = ? where codigo = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nome);
		pstmt.setInt(2, matricula);
		pstmt.setString(3, codigo);
		pstmt.execute();
	}

	public void excluir() throws SQLException {
		Connection conn = obterConexao();

		// Obter sentença SQL.
		String sql = "delete from cliente where codigo = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, codigo);
		pstmt.execute();
	}

	public static List<ClienteModel> listar() throws SQLException {
		Connection conn = obterConexao();

		Statement stmt = conn.createStatement();
		String sql = "select * from cliente order by codigo";
		ResultSet rs = stmt.executeQuery(sql);

		List<ClienteModel> listaDeVendas = new ArrayList<ClienteModel>();
		while (rs.next()) {
			// Cria um venda para o registro.
			ClienteModel cliente = new ClienteModel();
			cliente.setCodigo(rs.getString("codigo"));
			cliente.setNome(rs.getString("nome"));
			cliente.setMatricula(rs.getInt("matricula"));
			// Adiciona o venda na lista de vendas.
			listaDeVendas.add(cliente);
		}
		return listaDeVendas;
	}
}
