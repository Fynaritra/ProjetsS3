<%@ include file = "navbar.jsp" %>
<%
	Fonctions f = new Fonctions();
	DBConnection db = new DBConnection();
	Clients[] client = f.getClients(db);
	if(request.getParameter("idC")!=null){
		String id = request.getParameter("idC");
		Compteur[] cpt = f.getCompteurByCl(db, id);
		Offre[] offres = f.getOffre(db);
	%>
	<div class="row">
		<%	for(int i=0;i<cpt.length;i++){ %>
		<div class="card border-primary" style="width:10%; margin-left:5%;">
			<div class="card-body">
			<% 
			try{
				out.print(cpt[i].afficher(f,db));
				
			}catch(Exception e){
				out.print(e.getMessage());
			}	%>
			</div>
		</div>
		<% } %>
	</div>
	
	<div class="row">
	<div id = "abonne">
	<form action = "abonnement.jsp" method = "post">
		<div class="form-group">
			<label>Compteur</label>
			<select name ="compteur">
			<% for(int i=0;i<cpt.length;i++){%>
				<option value ="<%out.print(cpt[i].getNumcompteur());%>"><%out.print(cpt[i].getNumcompteur() + "-" + cpt[i].getNature());%></option>
			<% } %>
			</select>
		</div>
		<div class="form-group">
			<label>Offres</label>
			<select name ="offre">
			<% for(int i=0;i<offres.length;i++){%>
				<option value ="<%out.print(offres[i].getIdoffre());%>"><%out.print(offres[i].afficherOffre());%></option>
			<% } %>
			</select>
		</div>
		<button class="btn btn-primary" name="sabonner" value="<%out.print(id);%>">Acheter</button>
	</form>
	
	</div>
	
	</div>
		<form action="situation.jsp">
			<button class="btn btn-default" name="state" value="<%out.print(id);%>">Avoir situation</button>
		</form>
	<%
	}else{
		%>
<div class = "container">
<div class = "row">
<div class="col-md-4">
<table class = "table table-bordered">
<%	for(int i=0;i<client.length;i++){ 
		out.print(client[i].afficherClient());
	} %>
</table>
</div>
<div id = "ajout">
<h1>Ajout client</h1>
<form action = "ajout.jsp" method = "get">
	<div class="form-group">
		<label>Nom</label>
		<input type = "text" name="nom">
	</div>
	<div class="form-group">
		<label>Catégorie</label>
		<select name="categ">
		<%Categorie[] categ = f.getCategorie(db);
		for(int i=0;i<categ.length;i++){%>
			<option value="<% out.print(categ[i].getIdcateg());%>"><% out.println(categ[i].getNomcateg());%></option>
		<%}%>
		</select>
	</div>
	<button class="btn btn-primary" name="ajout" value="client">Ajouter</button>
</form>
</div>
<div id = "ajout">
<h1>Ajout compteur</h1>
<%
Clients[] cl = f.getClients(db);
%>
<form action = "ajout.jsp" method = "get">
	<div class="form-group">
		<label>Client</label>
		<select name="client">
		<%for(int i=0;i<cl.length;i++){%>
			<option value="<%out.print(cl[i].getIdclient());%>"><%out.print(cl[i].getName());%></option>
		<% } %>
		</select>
	</div>
	<div class="form-group">
		<label>Nature</label>
		<select name="nature">
			<option value="eau">Eau</option>
			<option value="elect">Electricité</option>
		</select>
	</div>
	<button class="btn btn-primary" name="ajout" value="compteur">Ajouter</button>
</form>
</div>
</div>
</div>
	<%}
	db.delConnect();%>