package table;

public class Categorie{
	String idCateg;
	String nomCateg;
	
///Setters
	public void setIdcateg(String id){
		this.idCateg = id;
	}
	public void setNomcateg(String nom){
		this.nomCateg = nom;
	}
	
///Constructor
	public Categorie(){}
	public Categorie(String id,String nom){
		setIdcateg(id);
		setNomcateg(nom);
	}
	
///Getters
	public String getIdcateg(){
		return this.idCateg;
	}
	public String getNomcateg(){
		return this.nomCateg;
	}
}