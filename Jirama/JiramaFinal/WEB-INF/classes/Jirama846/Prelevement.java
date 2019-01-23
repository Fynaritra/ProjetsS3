package table;

import connexion.DBConnection;
import fonction.*;
import java.sql.Date;

public class Prelevement{
	String idPrelevement;
	double indexPreleve;
	String numCompteur;
	Date datePrelevmt;
	int etatpv  = 1;
	
///Setters
	public void setIdprelevement(String id){
		this.idPrelevement = id;
	}
	public void setNumcompteur(String cpt)throws Exception{
		Fonctions f = new Fonctions();
		DBConnection dbcon = new DBConnection();
		try{
			Compteur toReturn = new Compteur();
			String tabName = "Compteur";
			String[] attribut = {" numCompteur "};
			String[] search = {"like '" + this.getNumcompteur() + "'"};
			Object[] temp = f.select(dbcon, toReturn, tabName, attribut, search);
			this.numCompteur = cpt;
		}
		catch(Exception ex){
			throw ex;
		}
		finally{
			dbcon.delConnect();
		}
	}
	public void setIndexpreleve(double index)throws Exception{
		if(index<0){
			throw(new Exception("Index invalide"));
		}
		else{
			this.indexPreleve = index;
		}
		
	}
	public void setDateprelevmt(Date plv){
		this.datePrelevmt = plv;
	}
	public void setEtatpv(int state){
		this.etatpv = state;
	}

///Constructor
	public Prelevement(){}
	public Prelevement(String id, double indexPv, String num, Date datepv)throws Exception{
		setIdprelevement(id);
		setNumcompteur(num);
		setIndexpreleve(indexPv);
		setDateprelevmt(datepv);
	}
	public Prelevement(String id, double indexPv, String num, Date datepv, int etat)throws Exception{
		setIdprelevement(id);
		setNumcompteur(num);
		setIndexpreleve(indexPv);
		setDateprelevmt(datepv);
		setEtatpv(etat);
	}
	
///Getters
	public String getIdprelevement(){
		return this.idPrelevement;
	} 
	public double getIndexpreleve(){
		return this.indexPreleve;
	}
	public String getNumcompteur(){
		return this.numCompteur;
	}
	public Date getDateprelevmt(){
		return this.datePrelevmt;
	}
	public int getEtatpv(){
		return this.etatpv;
	}
}