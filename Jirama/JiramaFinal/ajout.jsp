<%@ include file = "navbar.jsp" %>
<%
	Fonctions f = new Fonctions();
	if(request.getParameter("ajout").compareToIgnoreCase("client")==0){
		String nom = request.getParameter("nom");
		String categ = request.getParameter("categ");
		try{
			f.saveClient(null,nom,categ);
			response.sendRedirect("clients.jsp");
		}catch(Exception e){
			// throw e;
			out.print(e.getMessage());
		}
	}
	else if(request.getParameter("ajout").compareToIgnoreCase("compteur")==0){
		String idClient = request.getParameter("client");
		String nat = request.getParameter("nature");
		try{
			f.saveCompteur(null, idClient, nat);
			response.sendRedirect("clients.jsp");
		}catch(Exception e){
			// throw e;
			out.print(e.getMessage());
		}
	}
%>