<%@ include file = "navbar.jsp" %>
<%
	Fonctions f = new Fonctions();
	DBConnection connect = new DBConnection();
	try{
	String idc = request.getParameter("idClient");
	idc = idc.trim();
	int c = f.getSeqValue(connect,"idcat");
	String idcat = "Ca" + c;
	String nom = request.getParameter("categ");
	Categorie cat = new Categorie(idcat,nom);
	
	String[] att = {"idClient "};
	String[] search = {" = '" + idc + "'"};
	String tab = "Clients";
	String pack = "table";
	Object[] value = f.select(connect,new Clients(),tab,att,search);
	String nature = "elect";
	Clients concerne = (Clients)value[0];

	f.updateClient(connect,idc,idcat);
	
	double[] tranche = new double[3];
	double[] tarif = new double[3];
	Tranche[] toInsert = new Tranche[3];
	String[] libelle = {"tranche1 elec","tranche2 elec","tranche3 elec"};
	String param = new String();
	String quota = new String();
	for(int i=0;i<3;i++){
		int num = f.getSeqValue(connect,"idtr");
		String id = String.valueOf(num);
		int a = i+1;
		param = "tr" + a;
		quota = "quota" + a;
		tranche[i] = Double.parseDouble(request.getParameter(quota));
		tarif[i] = Double.parseDouble(request.getParameter(param));
		toInsert[i] = new Tranche(id,libelle[i],tranche[i],tarif[i],idcat,nature);
	}
	for(int i = 0;i<3;i++){
		boolean in = f.insertTranche(connect,toInsert[i]);
	}
	boolean ins = f.insertCateg(connect,cat);
%>
<table class = "table table-bordered">
	<tr>
		<th>Nom</th>
		<th>Categorie</th>
		<th>PU tr1</th>
		<th>Quota tr1</th>
		<th>PU tr2</th>
		<th>Quota tr2</th>
		<th>PU tr3</th>
		<th>Quota tr3</th>
	</tr>
	<tr>
		<td><% out.println(concerne.getName()); %></td>
		<td><% out.println(cat.getNomcateg()); %></td>
		<td><% out.println(tarif[0]); %></td>
		<td><% out.println(tranche[0]); %></td>
		<td><% out.println(tarif[1]); %></td>
		<td><% out.println(tranche[1]); %></td>
		<td><% out.println(tarif[2]); %></td>
		<td><% out.println(tranche[2]); %></td>
	</tr>
</table>
<%
	}catch(Exception e){
		out.print("Veuillez remplir correctement les champs");
	}finally{
		connect.delConnect();
	}
%>