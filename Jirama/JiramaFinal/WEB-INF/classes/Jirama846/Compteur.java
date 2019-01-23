package table;

import connexion.DBConnection;
import fonction.Fonctions;
import forfait.*;
public class Compteur{
	String numCompteur;
	String idclient;
	String nature;
	
///Setters
	public void setNumcompteur(String num){
		this.numCompteur = num;
	}
	public void setIdclient(String id){
		this.idclient = id;
	}
	public void setNature(String nat){
		this.nature = nat;
	}
	
///Constructors
	public Compteur(){}
	public Compteur(String num,String id,String nat){
		this.setNumcompteur(num);
		this.setIdclient(id);
		this.setNature(nat);
	}
	
///Getters
	public String getIdclient(){
		return this.idclient;
	}
	public String getNumcompteur(){
		return this.numCompteur;
	}
	public String getNature(){
		return this.nature;
	}
	public String afficher(Fonctions f, DBConnection db)throws Exception{
		Abonnes[] ab = f.getAbonnement(db, this.getNumcompteur());
		String value = new String();
		value += numCompteur + "-" + nature;
		for(int i=0;i<ab.length;i++){
			value += "--Qte achete " + ab[i].getQteachete();
			value += " Solde actuel " + ab[i].getQtecourante();
			value += " Valable de " + ab[i].getDebut() + " au " + ab[i].getFin() + "\\";
		}
	return value;
	}
}