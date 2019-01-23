<%@ include file = "navbar.jsp" %>
<%
	DBConnection db = new DBConnection();
	String cpt = request.getParameter("compteur");
	String offre = request.getParameter("offre");
	String id = request.getParameter("sabonner");
	Fonctions f = new Fonctions();
	try{
		f.saveAbonnement(db, offre, cpt);
		response.sendRedirect("clients.jsp?idC="+id);
	}catch(Exception e){
		out.print(e.getMessage());
	}finally{
		db.delConnect();
	}
%>