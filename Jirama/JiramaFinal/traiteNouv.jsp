<%@ include file = "navbar.jsp" %>
<%
Fonctions f = new Fonctions();
String id = request.getParameter("id");
String indexPv = request.getParameter("index");
String num = request.getParameter("cpt");
String datepv = request.getParameter("datepv");
try{
	f.savePrelevement(id, indexPv, num, datepv, null); 
	response.sendRedirect("index.jsp");
}catch(Exception e){
	out.print(e.getMessage());
}
%>
