package table;

public class DetailFacture{
	String idFactDet;
	String idFact;
	String nom;
	double conso;
	double PU;
	double major;
	double sousTotal;
	
///Getters
	public String getIdfactdet(){
		return this.idFactDet;
	}
	public double getMajor(){
		return this.major;
	}
	public String getIdfact(){
		return this.idFact;
	}
	public String getNom(){
		return this.nom;
	}
	public double getConso(){
		return this.conso;
	}
	public double getPu(){
		return this.PU;
	}
	public double getSoustotal(){
		return this.sousTotal;
	}

///Setters
	public void setIdfactdet(String id){
		this.idFactDet = id;
	}
	public void setIdfact(String idf){
		this.idFact = idf;
	}
	public void setNom(String name){
		this.nom = name;
	}
	public void setConso(double cons){
		this.conso = cons;
	}
	public void setPu(double pu){
		this.PU = pu;
	}
	public void setSoustotal(double st){
		this.sousTotal = st;
	}
	public void setMajor(double maj){
		this.major = maj;
	}

///Constructor
	public DetailFacture(){}
	public DetailFacture(String id,String idf,String name,double cons,double pu,double st){
		setIdfactdet(id);
		setIdfact(idf);
		setNom(name);
		setConso(cons);
		setPu(pu);
		setSoustotal(st);
	}
	public String affDetail(){
		String res = new String();
		String td = "<td>";
		String tdClose = "</td>";
		String tr = "<tr>";
		String trClose = "</tr>";
		res += tr;
		res += td + getNom() + tdClose;
		res += td + getConso() + tdClose;
		res += td + getPu() + tdClose;
		res += td + getSoustotal() + tdClose;
		res += trClose;
	return res;
	}
}