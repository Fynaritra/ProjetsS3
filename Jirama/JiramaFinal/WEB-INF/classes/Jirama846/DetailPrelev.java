package table;

import java.sql.Date;

public class DetailPrelev{
	String idprelevement;
	String idclient;
	String numCompteur;
	String nature;
	String etatpv;
	double indexPreleve;
	Date datePrelevmt;
	
	public Date getDateprelevmt(){
		return this.datePrelevmt;
	}
	public double getIndexpreleve(){
		return this.indexPreleve;
	}
	public String getIdprelevement(){
		return this.idprelevement ;
	}
	public String getIdclient(){
		return this.idclient ;
	}
	public String getNumcompteur(){
		return this.numCompteur ;
	}
	public String getNature(){
		return this.nature ;
	}
	public String getEtatpv(){
		return this.etatpv ;
	}
	public void setDateprelevmt(Date datepv){
		this.datePrelevmt = datepv;
	}
	public void setIndexpreleve(double index){
		this.indexPreleve = index;
	}
	public void setIdprelevement(String pv){
		this.idprelevement = pv;
	}
	public void setIdclient(String cl){
		this.idclient = cl;
	}
	public void setNature(String nat){
		this.nature = nat;
	}
	public void setEtatpv(String etat){
		this.etatpv = etat;
	}
	public void setNumcompteur(String num){
		this.numCompteur = num;
	}
}