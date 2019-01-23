package forfait;

import java.sql.Date;

public class Abonnes{
	String idAbonnes;
	String choix;
	String compteur;
	Date debut;
	Date fin;
	double qteAchete;
	double qteCourante;
	double prixAchat;
	double taux;
	int etat = 1;
	
///Setters
	public void setEtat(int st){
		this.etat = st;
	}
	public void setIdabonnes(String id){
		this.idAbonnes = id;
	}
	public void setChoix(String choice){
		this.choix = choice;
	}
	public void setCompteur(String cp){
		this.compteur = cp;
	}
	public void setDebut(Date deb){
		this.debut = deb;
	}
	public void setFin(Date end){
		this.fin = end;
	}
	public void setPrixachat(double achat){
		this.prixAchat = achat;
	}
	public void setTaux(double maj){
		this.taux = maj;
	}
	public void setQteachete(double ste){
		this.qteAchete = ste;
	}
	public void setQtecourante(double ste){
		this.qteCourante = ste;
	}
	
///Constructor
	public Abonnes(){}
	public Abonnes(String id, String choice, String cp, Date deb, Date end, double achat, double maj, double achete, double cour){
		setIdabonnes(id);
		setChoix(choice);
		setCompteur(cp);
		setDebut(deb);
		setFin(end);
		setPrixachat(achat);
		setTaux(maj);
		setQteachete(achete);
		setQtecourante(cour);
		// setEtat(1);
	}
	
///Getters
	public int getEtat(){
		return this.etat;
	}
	public double getQteachete(){
		return this.qteAchete;
	}
	public double getQtecourante(){
		return this.qteCourante;
	}
	public double getPrixachat(){
		return this.prixAchat;
	}
	public double getTaux(){
		return this.taux;
	}
	public String getIdabonnes(){
		return this.idAbonnes;
	}
	public String getChoix(){
		return this.choix;
	}  
	public String getCompteur(){
		return this.compteur;
	}  
	public Date getDebut(){
		return this.debut;
	} 
	public Date getFin(){
		return this.fin;
	} 
}