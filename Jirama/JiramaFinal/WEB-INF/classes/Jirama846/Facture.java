package table;
import connexion.DBConnection;
import fonction.*;

import java.sql.*;

public class Facture{
	String idfacture;
	Date dateFacture;
	double totalConso;
	String mois;
	String annee;
	String idclient;
	String idPrelevement;
	double total;
	double majFacture;
	int etatFacture; //0annuler partiel -1annuler integralement
	
///Setters
	public void setTotal(double t){
		this.total = t;
	}
	public void setEtatfacture(int st){
		this.etatFacture = st;
	}
	public void setMajfacture(double maj){
		this.majFacture = maj;
	}
	public void setIdfacture(String id){
		this.idfacture = id;
	}
	public void setDatefacture(Date df){
		this.dateFacture = df;
	}
	public void setTotalconso(double conso1){
		this.totalConso = conso1;
	}
	public void setMois(String m){
		this.mois = m;
	}
	public void setAnnee(String an){
		this.annee = an;
	}
	public void setIdclient(String idc){
		this.idclient = idc;
	}
	public void setIdprelevement(String idp){
		this.idPrelevement = idp;
	}
	
///Constructor
	public Facture(){}
	public Facture(String idFac,Date dateFacture,double totalConso,String mois,String annee,String idclient,String idPrelevement, double maj){
		setIdfacture(idFac);
		setDatefacture(dateFacture);
		setTotalconso(totalConso);
		setMois(mois);
		setAnnee(annee);
		setIdclient(idclient);
		setIdprelevement(idPrelevement);
		setMajfacture(maj);
		setEtatfacture(1);
	}
	public Facture(DBConnection db,Date dateFacture,double totalConso,String mois,String annee,String idclient,String idPrelevement, double maj, double total)throws Exception{
		Fonctions f = new Fonctions();
		int id = f.getSeqValue(db,"idFact");
		String nId = String.valueOf(id);
		String idFac = "ft" + nId;
		this.setIdfacture(idFac);
		setDatefacture(dateFacture);
		setTotalconso(totalConso);
		setMois(mois);
		setAnnee(annee);
		setIdclient(idclient);
		setIdprelevement(idPrelevement);
		setMajfacture(maj);
		setTotal(total);
		setEtatfacture(1);
	}
	
///Getters
	public double getTotal(){
		return this.total;
	}
	public int getEtatfacture(){
		return this.etatFacture;
	}
	public double getMajfacture(){
		return this.majFacture;
	}
	public String getIdfacture(){
		return this.idfacture;
	}
	public Date getDatefacture(){
		return this.dateFacture;
	}
	public double getTotalconso(){
		return this.totalConso;
	}
	public String getMois(){
		return this.mois;
	}
	public String getAnnee(){
		return this.annee;
	}
	public String getIdclient(){
		return this.idclient;
	}
	public String getIdprelevement(){
		return this.idPrelevement;
	}

	public String affFacture(){
		String result = new String();
		String td = "<td>";
		String tdClose = "</td>";
		String tr = "<tr>";
		String trClose = "</tr>";
		result += tr;
		result += td + getDatefacture() + tdClose;
		result += td + getAnnee() + tdClose;
		result += td + getMois() + tdClose;
		result += td + getTotalconso() + tdClose;
		result += td + getTotal() + tdClose;
		result += td + "<form action='traiteAnnule.jsp' method='get'><div class='form-group'><label>Date d'annulation</label><input type='text' name='dateAvoir' value='2019/01/18'><label>Somme a annuler </label><input type='text' name='somme' value='0'></div><button class='btn btn-default' value='"+getIdfacture()+"' name='annulation'>Annuler</button></form>" + tdClose;
		result += td + "<form action='traiteAnnule.jsp'><button class='btn btn-default' value='"+getIdfacture()+"' name='details'>Avoir détails</button></form>" + tdClose;
		result += trClose;
	return result;
	}
}