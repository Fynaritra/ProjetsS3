<%@ include file = "navbar.jsp" %>
<%
	String idc = request.getParameter("state");
	Fonctions f = new Fonctions();
	DBConnection db = new DBConnection();
	try{
		Facture[] fact = f.factByCl(idc, db);
		FactureAvoir[] avoir = f.factAvByCl(idc, db);
		double totalFact = 0;
		double totalAvoir = 0;
		if(fact.length>0){
			totalFact = f.Somme(fact, "total");
		}
		if(avoir.length>0){
			totalAvoir = f.Somme(avoir, "sommeMoins");
		}%>
		<table class="table table-bordered">
		<tr>
			<th>Total factures</th>
			<th>Total factures avoir</th>
			<th>Reste à payer</th>
		</tr>
		<tr>
			<td><% out.print(f.formatDouble(totalFact, " "));%></td>
			<td><% out.print(f.formatDouble(totalAvoir, " "));%></td>
			<td><% out.print(f.formatDouble((totalAvoir+totalFact), " "));%></td>
		</tr>
		</table>
		<%
	}catch(Exception e){
		out.print(e.getMessage());
	}finally{
		db.delConnect();
	}
	
%>