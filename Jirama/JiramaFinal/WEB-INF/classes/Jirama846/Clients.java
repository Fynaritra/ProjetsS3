package table;

import fonction.*;
import connexion.DBConnection;
public class Clients{
	String idclient;
	String name;
	String idCat;
	
///Setters
	public void setIdclient(String newId){
		this.idclient = newId;
	}
	public void setName(String newName){
		this.name = newName;
	}
	public void setIdcat(String newFn){
		this.idCat = newFn;
	}
	
///Constructor
	public Clients(){}
	public Clients(String id,String name,String cat){
		setIdclient(id);
		setName(name);
		setIdcat(cat);
	}
	
///Getters
	public String getIdclient(){
		return idclient;
	}
	public String getName(){
		return name;
	}
	public String getIdcat(){
		return idCat;
	}
	
///Fonctions pour Clients
	public Categorie getCategorie(DBConnection db)throws Exception{
		Fonctions rf = new Fonctions();
		String tab = "Categorie";
		String[] att = {"idCateg "};
		String[] search = {"like '" + this.idCat + "'"};
		Object[] cat = rf.select(db,new Categorie(), tab,att,search);
		Categorie val = (Categorie)cat[0];
	return val;
	}
	public String afficherClient()throws Exception{
		String value = new String();
		String tr = "<tr>";
		String trClose = "</tr>";
		String td = "<td>";
		String tdClose = "</td>";
		String ahref = "<a href=clients.jsp?idC="; 
		String end = ">";
		String ahrefClose = "</a>";
		value += tr;
		value += td + ahref + this.getIdclient() + end + this.getName() + ahrefClose + tdClose;
		value += td + this.getCategorie(null).getNomcateg() + tdClose;
		value += trClose;
	return value;
	}
	public String affClient()throws Exception{
		String value = new String();
		String tr = "<tr>";
		String trClose = "</tr>";
		String td = "<td>";
		String tdClose = "</td>";
		String ahref = "<a href=annuler.jsp?idC="; 
		String end = ">";
		String ahrefClose = "</a>";
		value += tr;
		value += td + ahref + this.getIdclient() + end + this.getName() + ahrefClose + tdClose;
		value += td + this.getCategorie(null).getNomcateg() + tdClose;
		value += trClose;
	return value;
	}
}