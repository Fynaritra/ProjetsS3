package affichage;
import table.*;
import html.*;
import connexion.DBConnection;
import fonction.*;

import java.sql.Date;

public class Affichage{
	public static void main(String[] argv)throws Exception{
		///Les données
		DBConnection connect = new DBConnection();
		Fonctions f = new Fonctions();

		
		String[] idpv = {"Pv79"};
		try{
			System.out.println(f.lettrer(20.4));
		}
		catch(Exception ex){
			throw ex;
		}
		finally{
			connect.delConnect();
		}
			
		
		// boolean rs = f.insertCateg(connect,new Categorie("Ca2","Emp"));
		// Prelevement pv = new Prelevement(connect, 9856, "Cp2", "2018-8-12");
		// f.insertPv(connect, pv);
		// Prelevement[] rec = f.getRecentPv(connect,"Cp2",2);
		// for(int j=0;j<rec.length;j++){
			// System.out.println("recent " + rec[j].getDateprelevmt());
		// }
		// Tranche[] tr = f.getTranches("Cl1", "elec", connect);
		// Facture edite = f.edition(connect, pv);
		
		/*Tranche[] cat = f.getTranches("Cl4",connect);
		double[] quota = new double[cat.length];
		for(int i=0;i<cat.length;i++){
			quota[i] = cat[i].getQtelim();
			System.out.println("quota " + i + " " + quota[i]);
		}
		String[] compteur = f.getCompteurs(connect);
		for(int j=0;j<compteur.length;j++){
			System.out.println("cpt " + compteur[j]);
		}
		double[] tr = f.trancher(2456,3,quota);
		for(int i=0;i<tr.length;i++){
			System.out.println("tranche " + tr[i]);
		}
		Date auj = f.getToday(connect);
		System.out.println(auj.toString());*/
		// Facture[] fall = f.factureClient(connect,"Cl1",06,2018);
		// for(int i=0;i<fall.length;i++){
			// System.out.println(fall[i].getDatefacture());
		// }
	}
}