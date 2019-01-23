package forfait;

public class Offre{
	String idOffre;
	String nature;
	double qte;
	double cout;
	int duree;
	double majoration;
	
///Setters
	public void setIdoffre(String offre){
		this.idOffre = offre;
	}
	public void setNature(String nat){
		this.nature = nat;
	}
	public void setQte(double q){
		this.qte = q;
	}
	public void setCout(double cost){
		this.cout = cost;
	}
	public void setDuree(int duree){
		this.duree = duree;
	}
	public void setMajoration(double plus){
		this.majoration = plus;
	}
	
///Constructor
	public Offre(){}
	public Offre(String idOffre, String nature, String qte, String prix, String duree, String majoration){
		double q = Double.parseDouble(qte);
		double price = Double.parseDouble(prix);
		int duration = Integer.parseInt(duree);
		double taux = Double.parseDouble(majoration);
		setIdoffre(idOffre);
		setNature(nature);
		setQte(q);
		setCout(price);
		setDuree(duration);
		setMajoration(taux);
	}
	
///Getters
	public String getIdoffre(){
		return this.idOffre;
	}
	public String getNature(){
		return this.nature;
	}
	public double getQte(){
		return this.qte;
	}
	public double getCout(){
		return this.cout;
	}
	public int getDuree(){
		return this.duree;
	}
	public double getMajoration(){
		return this.majoration;
	}
	public String afficherOffre(){
		String value = new String();
		value += nature + "-Qte:" + qte + "-Duree:" + duree +"jrs-Prix:"+ cout +"AR-Majoration:" + majoration + "%";
	return value;
	}
}