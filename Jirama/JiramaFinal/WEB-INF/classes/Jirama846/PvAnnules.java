package table;

import java.sql.Date;
public class PvAnnules{
	String idPvAn;
	String idPv;
	String numCompteur;
	double indexPreleve;
	Date datePrelevmt;
	
///Setters
	public void setIdpvan(String pvAn){
		this.idPvAn = pvAn;
	}
	public void setIdpv(String id){
		this.idPv = id;
	}
	public void setNumcompteur(String num){
		this.numCompteur = num;
	}
	public void setIndexpreleve(double index){
		this.indexPreleve = index;
	}
	public void setDateprelevmt(Date datePv){
		this.datePrelevmt = datePv;
	}
	
///Getters
	public String getIdpvan(){
		return this.idPvAn;
	}
	public String getIdpv(){
		return this.idPv;
	}
	public String getNumcompteur(){
		return this.numCompteur;
	}
	public double getIndexpreleve(){
		return this.indexPreleve;
	}
	public Date getDateprelevmt(){
		return this.datePrelevmt;
	}
	
	public PvAnnules(){}
	public PvAnnules(String id, String pv, String cpt, double ind, Date datepv){
		setIdpvan(id);
		setIdpv(pv);
		setNumcompteur(cpt);
		setIndexpreleve(ind);
		setDateprelevmt(datepv);
	}
	
	public String afficher(){
		String res = new String();
		String tr = "<tr>";
		String trClose = "</tr>";
		String td = "<td>";
		String tdClose = "</td>";
		res += tr;
		res += td + getIdpvan() + tdClose;
		res += td + getIdpv() + tdClose;
		// res += td + "<a href='nouveau.jsp?idN="+getIdpv()+"'" + ">Nouveau prelevement</a>" + tdClose;
		res += trClose;
	return res;
	}
}
