<%@ include file = "navbar.jsp" %>
<%
	if(request.getParameter("ajout")!= null){
		Fonctions f = new Fonctions();
		String nature = request.getParameter("choice");
		String qte = request.getParameter("qte");
		String cout = request.getParameter("prix");
		String duration = request.getParameter("duree");
		String majoration = request.getParameter("taux");
		try{
			f.saveOffre(null, nature, qte, cout, duration, majoration);
		}catch(SQLSyntaxErrorException sql){
			out.print("Veuillez remplir correctement tous les champs");
		}
		catch(Exception e){
			out.print(e.getMessage());
		}
	}
%>
<html>
<div class = "container-fluid">
<div class = "row">
	<div class = "col-md-5">
	<%
		Fonctions f = new Fonctions();
		Offre[] offre = f.getOffre(null);%>
		<table class = "table table-bordered">
		<tr>
			<th>IdOffre</th>
			<th>Nature</th>
			<th>Quantité</th>
			<th>Prix</th>
			<th>Durée(en jours)</th>
			<th>Majoration</th>
		</tr>
		<%for(int i=0;i<offre.length;i++){ 
			out.print(f.getHtml(offre[i]));
		} %>
		</table>
	</div>
	<div id="offer" class="col-md-offset-2">
	<h1>Nouvel offre</h1>
	<form action="offres.jsp" method = "POST">
		<div class = "form-group">
			<label>Pour</label>
			<select name = "choice">
				<option value="eau">Eau</option>
				<option value="elect">Electricité</option>
			</select>
		</div>
		<div class = "form-group">
			<label>Quantite</label>
			<input type = "text" name="qte">
		</div>
		<div class = "form-group">
			<label>Cout</label>
			<input type = "text" name="prix">
		</div>
		<div class = "form-group">
			<label>Durée</label>
			<input type = "text" name="duree">
		</div>
		<div class = "form-group">
			<label>Majoration</label>
			<input type = "text" name="taux">
		</div>
		<button name="ajout" class="btn btn-success">Ajouter offre</button>
	</form>
	</div>
</div>
</div>
</html>