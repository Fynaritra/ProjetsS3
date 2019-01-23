<%@ include file = "navbar.jsp" %>
<div class="col-md-8 ">
    <div class="card border-primary mb-3" style="width: 50rem;">
    <div class="card-body">
    <form action ="detail.jsp" method="get">
        <div class="form-group">
            <label for="exampleFormControlSelect2">Nom clients</label>
            <select multiple class="form-control" id="exampleFormControlSelect2" name="idClient" required="required">
            <%
			Fonctions f = new Fonctions();
			Clients[] client = f.getClients(null);
			for(int i=0;i<client.length;i++){ %>
				<option value="<%out.println(client[i].getIdclient());%>"><% out.print(client[i].getName());%></option>
			<% } %>
            </select>
        </div>
        <div>
		
		<label>Nom Catégorie</label>
			<input type="text" name="categ" required="required">
		</br>
		<div class = "form-inline">
		<label>PU tranche 1 </label>
			<input type="text" class="form-control" name="tr1" required="required">
		<label>Quota tranche 1 </label>
			<input type="text" class="form-control" name="quota1" required="required">
		</div>
		<div class = "form-inline">
		<label>PU tranche 2 </label>
			<input type="text" class="form-control" name="tr2" required="required">
		<label>Quota tranche 2 </label>
			<input type="text" class="form-control" name="quota2" required="required">
		</div>
		<div class = "form-inline">
		<label>PU tranche 3 </label>
			<input type="text" class="form-control" name="tr3" required="required">
			<input type="hidden" class="form-control" name="quota3" value="-1">
		</div>
		</br>
		</div>
        <div class="offset-4">
       <button type="submit" class="btn btn-outline-primary btn-lg">Valider</button>
        </div>
        </div>
    </form>
    </div>
</div>
<!-- Formulaire pour inserer facture-->
</div>
    <script src="js/jQuery.js"></script>
    <script>window.jQuery || document.write('<script src="js/jquery-slim.min.js"><\/script>')</script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script> 
</body>
</html>