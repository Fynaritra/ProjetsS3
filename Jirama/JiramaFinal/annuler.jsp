<%@ include file = "navbar.jsp" %>
<%	Fonctions f = new Fonctions();
	Clients[] client = f.getClients(null); 
	if(request.getParameter("idC")!=null){
		String idc = request.getParameter("idC");
		Facture[] fact = f.factByCl(idc, null);%>
		<table class="table table-bordered">
		<tr>
			<th>Date facture</th>
			<th>Année</th>
			<th>Mois</th>
			<th>Consommation</th>
			<th>Total à payer</th>
		</tr>
		<% for(int i=0;i<fact.length;i++){ 
			if( fact[i].getTotal()>0){
				out.print(fact[i].affFacture());
			}
		}%>
		</table>
		
	<%if(fact.length==0){
			out.print("Pas de factures pour ce client");
		}
	}else{		
%>
<table class = "table table-bordered">
<%	for(int i=0;i<client.length;i++){ 
		out.print(client[i].affClient());
	} %>
</table>
<%	}	%>