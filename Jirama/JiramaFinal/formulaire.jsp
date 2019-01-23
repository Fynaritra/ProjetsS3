<%@ include file = "navbar.jsp" %>
<%
	Fonctions f = new Fonctions();
	Object toCreate = new Facture();
	Formulaire form =  f.createForm(toCreate);
%>
<form action="traiteForm.jsp" method="post">
<% 
	// form.getChamp(0).setDefaultvalue(String.valueOf(f.getSeqValue(connect,"idfact")));
	out.print(form.show());
%>
</form>