<%@ include file = "navbar.jsp" %>
<%
	Fonctions f = new Fonctions();
	if(request.getParameter("details")!=null){
		String idfact = request.getParameter("details");
		DetailFacture[] dfa = f.getDetByFact(null, idfact); %>
		<table class="table table-striped">
		<tr>
			<td>Libelle</td>
			<td>Conso</td>
			<td>PU</td>
			<td>Sous-total</td>
		</tr>
		<%
		for(int i=0;i<dfa.length;i++){
			out.print(dfa[i].affDetail());
		} %>
		</table>
	<%
	}else if(request.getParameter("annulation")!=null){
		String factAn = request.getParameter("annulation");
		String montant = request.getParameter("somme");
		String dateAv = request.getParameter("dateAvoir");
		try{
			FactureAvoir fa = f.annulerFacture(null, factAn, montant, dateAv); %>
			<table class="table table-bordered">
			<%out.print(fa.affAvoir());%>
			</table>
		<%}catch(Exception e){
			// throw e;
			out.print(e.getMessage());
		}
	}
%>