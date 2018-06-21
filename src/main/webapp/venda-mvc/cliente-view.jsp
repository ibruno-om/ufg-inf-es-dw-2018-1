<!DOCTYPE html>
<%@page import="dw.ClienteModel"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Venda</title>
<meta charset="utf-8">
<link rel="stylesheet" href="bootstrap.min.css">
</head>
<body style="margin-top: 15px">
	<div class="container">
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
				<ol class="breadcrumb">
					<li><a href="/">Menu</a></li>
					<li class="active">Cliente</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-body">
						<form>
							<div class="form-group">
								<input name="codigo" value="${param.codigo}" type="text"
									placeholder="Código" class="form-control">
							</div>
							<div class="form-group">
								<input name="nome" value="${param.nome}" type="text"
									placeholder="Nome" class="form-control">
							</div>
							<div class="form-group">
								<input name="matricula" value="${param.matricula}" type="number"
									placeholder="Matrícula" class="form-control">
							</div>
							<button name="op" value="incluir" class="btn btn-default">Incluir</button>
							<button name="op" value="salvar" class="btn btn-default">Salvar</button>
						</form>
					</div>
				</div>
				<table class="table table-bordered table-striped">
					<tr>
						<td>Código</td>
						<td>Nome</td>
						<td>Matrícula</td>
						<td>#</td>
					</tr>
					<%
						List<ClienteModel> clientes = (List<ClienteModel>) request.getAttribute("clientes");
						for (ClienteModel v : clientes) {
					%>
					<tr>
						<td><a
							href="cliente?codigo=<%=v.getCodigo()%>&nome=<%=v.getNome()%>&matricula=<%=v.getMatricula()%>"><%=v.getCodigo()%></a></td>
						<td><%=v.getNome()%></td>
						<td><%=v.getMatricula()%></td>
						<td><a href="cliente?op=excluir&codigo=<%=v.getCodigo()%>">Excluir</a></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>
</body>
</html>