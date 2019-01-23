<%@ include file = "navbar.jsp" %>
<!-- Formulaire pour inserer prelevement-->
<div class="col-md-8 offset-4">
    <div class="card border-primary mb-3" style="width: 18rem;">
    <div class="card-body">
    <form action ="facture.jsp" method="post">
        <div class="form-group">
            <label for="exampleFormControlSelect2">Numero Compteur</label>
            <select multiple class="form-control" id="exampleFormControlSelect2" name="numCompteur" required="required">
            <%
			String[] compteurs = new String[0];
			try{
				Fonctions f = new Fonctions();
				compteurs = f.getCompteurs(null);
				f.actualiser(null);
			}catch(Exception e){
				out.print(e);
			}
			for(int i=0;i<compteurs.length;i++){ %>
				<option value="<%out.println(compteurs[i]);%>"><% out.print(compteurs[i]);%></option>
			<% } %>
            </select>
        </div>
        <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">Index Preleve</span>
        </div>
        <input type="text" class="form-control"  aria-label="Username" aria-describedby="basic-addon1" name="indexPreleve" required="required">
        </div>
       <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">Date Prelevement</span>
        </div>
        <input type="text" class="form-control"  aria-label="Username" aria-describedby="basic-addon1" name="date" required="required">
        </div>
            <br>
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