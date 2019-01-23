package table;

import java.util.Vector;
import fonction.Fonctions;

public class FactureClients{
	Vector <DetailFacture> electricity;
	Vector <DetailFacture> water;
	
	public void addElectricity(DetailFacture det){
		this.electricity.addElement(det);
	}
	public void addWater(DetailFacture det){
		this.water.addElement(det);
	}
	public DetailFacture getElectricity(int indice){
		return (DetailFacture)electricity.get(indice);
	}
	public DetailFacture getWater(int indice){
		return (DetailFacture)water.get(indice);
	}
	public FactureClients(){
		this.electricity = new Vector();
		this.water = new Vector();
	}
	public Facture getFact()throws Exception{
		Fonctions f = new Fonctions();
		Facture fact = new Facture();
		if(electricity.size()>0){
			fact = f.getFactById(null, this.electricity.get(0).getIdfact());
		}else if(water.size()>0){
			fact = f.getFactById(null, this.water.get(0).getIdfact());
		}
	return fact;
	}
	public double getCoutElec()throws Exception{
		Fonctions f = new Fonctions();
		double result = 0;
		try{
			result = f.Somme(this.electricity.toArray(new DetailFacture[0]), "soustotal");
		}catch(Exception e){
			throw e;
		}
		return result;
	}
	public double getCoutEau()throws Exception{
		Fonctions f = new Fonctions();
		double result = 0;
		try{
			result = f.Somme(this.water.toArray(new DetailFacture[0]), "soustotal");
		}catch(Exception e){
			throw e;
		}
	return result;
	}
	public double getTotal()throws Exception{
		double result = 0;
		try{
			result = getCoutElec() + getCoutEau();
		}catch(Exception e){
			throw e;
		}
	return result;
	}
	public String afficher()throws Exception{
		Fonctions f = new Fonctions();
		String value = new String();
		// value += "<h1>Facture de </h1>";
		String tab = "<table class='table table-bordered'>";
		String tabClose = "</table>";
		String tr = "<tr>";
		String trClose = "</tr>";
		String th = "<th>";
		String thClose = "</th>";
		String td = "<td>";
		String tdClose = "</td>";
		value += tab;
		value += tr;
		double eau = 0;
		double elec = 0;
			if(this.electricity.size()>0){
				value += td;
				value += tab;
				value += tr;
				value += th + "Libelle" + thClose;
				value += th + "Quantite" + thClose;
				value += th + "Prix unitaire" + thClose;
				value += th + "Sous-total" + thClose;
				value += trClose;
				elec = getCoutElec();
				for(int i=0;i<electricity.size();i++){
					value += tr;
					value += td + getElectricity(i).getNom() + tdClose;
					value += td + getElectricity(i).getConso() + tdClose;
					value += td + getElectricity(i).getPu() + tdClose;
					value += td + f.formatDouble(getElectricity(i).getSoustotal(), " ") + tdClose;
					value += trClose;
				}
				value += tr + "Total electricite : " + f.formatDouble(elec, " ") + trClose;
				value += tabClose;
				value += tdClose;
			}if(water.size()>0){
				value += td;
				value += tab;
				value += tr;
				value += th + "Libelle" + thClose;
				value += th + "Quantite" + thClose;
				value += th + "Prix unitaire" + thClose;
				value += th + "Sous-total" + thClose;
				value += trClose;
				eau = getCoutEau();
				for(int i=0;i<water.size();i++){
					value += tr;
					value += td + getWater(i).getNom() + tdClose;
					value += td + getWater(i).getConso() + tdClose;
					value += td + getWater(i).getPu() + tdClose;
					value += td + f.formatDouble(getWater(i).getSoustotal(), " ") + tdClose;
					value += trClose;
				}
				value += tr + "Total eau : " + f.formatDouble(eau, " ") + trClose;
				value += tabClose;
				value += tdClose;
			}
		value += trClose;
		value += tabClose;
		value += "<p>Total a payer: " + f.formatDouble((eau+elec), " ") + "</p><p> Soit: " + f.lettrer(eau+elec) +" Ariary </p>";
	return value;
	}
}