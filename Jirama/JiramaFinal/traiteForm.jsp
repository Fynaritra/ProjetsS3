<%
Fonctions f = new Fonctions();
String objValide = request.getParameter("Valider");
out.print(objValide);
String tabName = "Facture";
// f.getTable(objValide);
String[] att = f.getAttName(objValide);
String[] value = new String[att.length];
for(int i=0;i<att.length;i++){
	value[i] = request.getParameter(att[i]);
}
try{
	f.treatFormulaire(objValide, tabName, value);
}
catch(Exception e){
	out.println(e.getMessage());
}
%>