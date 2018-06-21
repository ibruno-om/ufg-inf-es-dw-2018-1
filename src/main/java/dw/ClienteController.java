package dw;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/venda-mvc/cliente")
public class ClienteController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1386383948275574989L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		op = (op == null ? "" : op);

		ClienteModel cliente = new ClienteModel();
		cliente.setCodigo(request.getParameter("codigo"));
		cliente.setNome(request.getParameter("nome"));
		String quantidadeStr = request.getParameter("matricula");
		cliente.setMatricula(Integer.parseInt((quantidadeStr == null ? "0" : quantidadeStr)));

		List<ClienteModel> clientes = null;
		try {
			if (op.equals("incluir")) {
				cliente.incluir();
			} else if (op.equals("salvar")) {
				cliente.salvar();
			} else if (op.equals("excluir")) {
				cliente.excluir();
			}

			clientes = ClienteModel.listar();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// Adiciona a variável na requisição para o JSP trabalhar.
		request.setAttribute("clientes", clientes);

		// Redireciona requisição para o JSP.
		request.getRequestDispatcher("/venda-mvc/cliente-view.jsp").forward(request, response);
	}
}
