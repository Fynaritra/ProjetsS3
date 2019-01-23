<%@ include file = "navbar.jsp" %>
<%
Fonctions f = new Fonctions();
PvAnnules[] pva = f.getPvAnnules(null);
if(request.getParameter("idN")!=null){
	String idpv = request.getParameter("idN");
	%>
	<form action="traiteNouv.jsp" method="post">
	<input type="hidden" name="pv" value="<%out.print(idpv);%>">
	<div class="form-group">
		<label>Numero compteur</label>
		<input name="cpt" type="text">
	</div>
	<div class="form-group">
		<label>Index</label>
		<input name="index" type="text">
	</div>
	<div class="form-group">
		<label>Date prélèvement</label>
		<input name="datepv" type="text">
	</div>
	<button class="btn btn-success">Valider</button>
	</form>
<%}
else{%>
	<table class="table table-bordered">
	<% for(int i=0;i<pva.length;i++){
		out.print(pva[i].afficher());
	}
	%>
	</table>
<%
}%>