package table;

public class Tranche{
	String idTranche;
	String libelle;
	double qteLim;
	double prixUnitaire;
	String categClients;
	String nature;
	
///Setters
	public void setIdtranche(String id){
		this.idTranche = id;
	}
	public void setLibelle(String nt){
		this.libelle = nt;
	}
	public void setQtelim(double t1){
		this.qteLim = t1;
	}
	public void setPrixunitaire(double t2){
		this.prixUnitaire = t2;
	}
	public void setCategclients(String cat){
		this.categClients = cat;
	}
	public void setNature(String nat){
		this.nature = nat;
	}
	
///Constructor
	public Tranche(){}
	public Tranche(String id,String nt,double t1,double t2,String cat,String nat){
		setIdtranche(id);
		setLibelle(nt);
		setQtelim(t1);
		setPrixunitaire(t2);
		setCategclients(cat);
		setNature(nat);
	}
	
///Getters
	public String getIdtranche(){
		return this.idTranche;
	}
	public String getLibelle(){
		return this.libelle;
	}
	public double getQtelim(){
		return this.qteLim;
	}
	public double getPrixunitaire(){
		return this.prixUnitaire;
	}
	public String getCategclients(){
		return this.categClients;
	}
	public String getNature(){
		return this.nature;
	}
}