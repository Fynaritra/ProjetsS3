<%@ include file = "navbar.jsp" %>
<%
	Fonctions f = new Fonctions();

if(request.getParameter("numCompteur")!= null){
	String numComp = request.getParameter("numCompteur");
	numComp = numComp.trim();
	String index = request.getParameter("indexPreleve");
	String datepv = request.getParameter("date");
	DBConnection db = new DBConnection();
	try{
		f.savePrelevement(index, numComp, datepv, db);
	}
	catch(Exception e){
		out.println(e.getMessage());
	}finally{
		db.delConnect();
	}
}
	Prelevement[] nonfacture = f.getPrelevements(null);
%>
<form action="traitement.jsp" method="post">
<table class = "table table-striped">
	<tr>
		<th>idPv</th>
		<th>N° Compteur</th>
		<th>Index </th>
		<th>Date </th>
	</tr>
	<% for(int i=0;i<nonfacture.length;i++){ %>
		<tr>
			<td><% out.print(nonfacture[i].getIdprelevement()); %></td>
			<td><% out.print(nonfacture[i].getNumcompteur()); %></td>
			<td><% out.print(nonfacture[i].getIndexpreleve()); %></td>
			<td><% out.print(nonfacture[i].getDateprelevmt()); %></td>
			<td><input type = "checkbox" value ="<% out.print(nonfacture[i].getIdprelevement()); %>" name="id"></td>
		</tr>
	<% } %>
</table>
<button class="btn btn-default">Facturer</button>
</form>