<%@ include file = "navbar.jsp" %>
<!DOCTYPE html>
<html>
<%
	Fonctions f = new Fonctions();
	String[] idpv = request.getParameterValues("id");
	FactureClients facture = new FactureClients();
	try{
		facture = f.rassembler(idpv,null);
		out.print(facture.afficher());
	}
	catch(Exception e){
		out.println(e.getMessage());
		// throw e;
	} 
%>
</html>