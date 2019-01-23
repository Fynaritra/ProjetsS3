package table;

import java.sql.Date;
import fonction.Fonctions;

public class FactureAvoir{
	String idfactureav;
	String idfacture;
	String idclient;
	Date dateFacture;
	Date dateAvoir;
	double sommeMoins;
	
///Setters
	public void setIdfactureav(String factAv){
		this.idfactureav = factAv;
	}
	public void setIdfacture(String idfact){
		this.idfacture = idfact;
	}
	public void setIdclient(String cl){
		this.idclient = cl;
	}
	public void setDatefacture(Date df){
		this.dateFacture = df;
	}
	public void setDateavoir(Date da){
		this.dateAvoir = da;
	}
	public void setSommemoins(double s){
		this.sommeMoins = s;
	}
	
///Constructor
	public FactureAvoir(){}
	public FactureAvoir(String id, String fact, String cl, Date da, Date df, double moins){
		setIdfactureav(id);
		setIdfacture(fact);
		setIdclient(cl);
		setDateavoir(da);
		setDatefacture(df);
		setSommemoins(moins);
	}

///Getters
	public String getIdfactureav(){
		return this.idfactureav ;
	}
	public String getIdfacture(){
		return this.idfacture ;
	}
	public String getIdclient(){
		return this.idclient ;
	}
	public Date getDateavoir(){
		return this.dateAvoir;
	}
	public Date getDatefacture(){
		return this.dateFacture;
	}
	public double getSommemoins(){
		return this.sommeMoins;
	}
	
	public String affAvoir(){
		Fonctions f = new Fonctions();
		String res = new String();
		String tr = "<tr>";
		String trClose = "</tr>";
		String td = "<td>";
		String tdClose = "</td>";
		res += tr;
		res += td + getDateavoir() + tdClose;
		res += td +f.formatDouble(getSommemoins(), " ") + tdClose;
		res += trClose;
	return res;
	}
}