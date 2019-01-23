package fonction;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.Vector;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Class;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

import forfait.*;
import table.*;
import html.*;
import connexion.DBConnection;

public class Fonctions extends ReflectFunctions{
///Object By Id
	public Facture getFactById(DBConnection db, String id)throws Exception{
		String tabName = "Facture";
		String[] attribut = {"idfacture "};
		String[] search = {"like '" + id + "'"};
		Object[] all = select(db, new Facture(), tabName, attribut, search);
		Facture val = (Facture)all[0];
	return val;
	}
	public Offre getOffreById(DBConnection db, String id)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		Offre result = new Offre();
		String tab = "Offre";
		String[] attribut = {"idOffre"};
		String[] search = {" like '"+id+"'"};
		try{
			Object[] temp = select(db, result, tab, attribut, search);
			result = (Offre)temp[0];
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	return result;
	}
	public Compteur getCompteur(DBConnection db, String numCompteur)throws Exception{
		String tabName = "Compteur";
		String[] attribut = {" numCompteur "};
		String[] search = {"like '" + numCompteur + "'"};
		Object[] all = select(db, new Compteur(), tabName, attribut, search);
		Compteur val = (Compteur)all[0];
	return val;
	}
	public Prelevement getPvById(DBConnection db, String id) throws Exception{
		String tabName = "Prelevement";
		String[] attribut = {"idprelevement "};
		String[] search = {"like '" + id + "'"};
		Object[] all = select(db, new Prelevement(), tabName, attribut, search);
		Prelevement val = (Prelevement)all[0];
	return val;
	}
	
	public DetailFacture[] getDetByFact(DBConnection db, String idFact)throws Exception{
		String tab = "DetailFacture";
		String[] attribut = {"idfact "};
		String[] search = {"like '"+idFact+"'"};
		Object toReturn = new DetailFacture();
		Vector<DetailFacture> temp = selectAll(db, toReturn, tab, attribut, search, "AND");
		DetailFacture[] res = new DetailFacture[0];
		if(temp.size()>0){
			res = temp.toArray(new DetailFacture[0]);
		}
	return res;
	}
	
///Les offres
	public Offre[] getOffre(DBConnection db)throws Exception{
		Offre[] result = new Offre[0];
		Object toReturn = new Offre();
		String tabName = "Offre";
		String[] attribut = {"idoffre"};
		String[] search = {" like '%%'"};
		Object[] temp = select(db, toReturn, tabName, attribut, search);
		result = new Offre[temp.length];
		for(int i=0;i<temp.length;i++){
			result[i] = (Offre)temp[i];
		}
	return result;
	}
	void checkOffre(DBConnection db, String nature, String qte, String prix, String duree)throws Exception{
		Object toReturn = new Offre();
		String table = "Offre";
		String[] attribut = {"nature", "qte", "cout", "duree"};
		String[] search = {" like '"+ nature +"'", "="+qte, "="+prix, "="+duree};
		Object[] temp = select(db, toReturn, table, attribut, search);
		if(temp.length>0){
			throw new Exception("Offre existe deja");
		}
	}
	public void saveOffre(DBConnection db, String nature, String qte, String prix, String duree, String majoration)throws Exception{
		boolean check = true;
		String idOffre = new String();
		if(db==null){
			db = new DBConnection();
			check = false;
		}
		try{
			checkOffre(db, nature, qte, prix, duree);
			idOffre = "O" + getSeqValue(db, "idoffre");
			Offre toInsert = new Offre(idOffre, nature, qte, prix, duree, majoration);
			insert(db, "Offre", toInsert);
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	}
	
///Compteurs par client
	public Compteur[] getCompteurByCl(DBConnection db, String id)throws Exception{
		Compteur[] result = new Compteur[0];
		Object toReturn = new Compteur();
		String tabName = "Compteur";
		String[] attribut = {"idclient"};
		String[] search = {" like '" + id + "'"};
		Object[] temp = select(db, toReturn, tabName, attribut, search);
		result = new Compteur[temp.length];
		for(int i=0;i<temp.length;i++){
			result[i] = (Compteur)temp[i];
		}
	return result;
	}
	
///Compteurs abonnés aux offres prépayés
	public Compteur[] getAbonnes(DBConnection db)throws Exception{
		boolean check = true;
		if(db==null){
			db = new DBConnection();
			check = false;
		}
		String tab = "Abonnes,Compteur";
		String[] attribut = {"Abonnes.Compteur ", "fin>"};
		String[] search = {"= Compteur.numCompteur", "to_date('"+getToday(db)+"')"};
		Object toReturn = new Compteur();
		Compteur[] result = new Compteur[0];
		try{
			Object[] temp = select(db, toReturn, tab, attribut, search);
			int nb = temp.length;
			result = new Compteur[nb];
			for(int i=0;i<nb;i++){
				result[i] = (Compteur)temp[i];
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
		return result;
	}
	
///Abonnements par compteur	
	public Abonnes[] getAbonnement(DBConnection db, String id)throws Exception{ 
		boolean check = true;
		if(db==null){
			db = new DBConnection();
			check = false;
		}
		Abonnes[] result = new Abonnes[0];
		String tabName = "Abonnes";
		String[] attribut = {"compteur", "etat","fin>"};
		String[] search = {" like '"+id+"'", "<11", "to_date('"+getToday(db)+"') ORDER BY fin DESC"};
		Object toReturn = new Abonnes();
		try{
			Object[] temp = select(db, toReturn, tabName, attribut, search);
			int nb = temp.length;
			result = new Abonnes[nb];
			for(int i=0;i<nb;i++){
				result[i] = (Abonnes)temp[i];
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	return result;
	}
	public Abonnes getLastAbon(DBConnection connect, String cpt) throws Exception{
		boolean dbCheck = true;
		if(connect == null){
			dbCheck = false;
			connect = new DBConnection();
		}
		Abonnes result = null;
		String tabName = "Abonnes";
		String[] attribut = {"compteur"};
		String[] search = {" like '"+cpt+"' ORDER BY fin DESC"};
		try{
			Object[] temp = select(connect, new Abonnes(), tabName, attribut, search);
			int nb = temp.length;
			if(nb>0){
				result = (Abonnes)temp[0];
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!dbCheck){
				connect.delConnect();
			}
		}
	return result;
	}
	public Abonnes getAbonnementAfter(DBConnection db, Abonnes toContinue)throws Exception{
		boolean controle = true;
		if(db==null){
			controle = false;
			db = new DBConnection();
		}
		Abonnes results = null;
		String tabName = "Abonnes";
		Object toReturn = new Abonnes();
		String cpt = toContinue.getCompteur();
		String[] attribut = {"compteur", "idAbonnes", "DEBUT"};
		String[] search = {" like '"+cpt+"'", "!='"+toContinue.getIdabonnes()+"'"," between to_date('" + toContinue.getDebut() + "') AND to_date('" + toContinue.getFin() + "') ORDER BY DEBUT"};
		Object[] temp = select(db, toReturn, tabName, attribut, search);
		int t = temp.length;
		if(t>0){
			results = (Abonnes)temp[0];
		}
		if(!controle){
			db.delConnect();
		}
	return results;
	}
	public Abonnes getAbonnementById(DBConnection db, String id)throws Exception{
		String tabName = "Abonnes";
		String[] attribut = {"idAbonnes "};
		String[] search = {"like '" + id + "'"};
		Object[] all = select(db, new Abonnes(), tabName, attribut, search);
		Abonnes val = (Abonnes)all[0];
	return val;
	}
	public void updateContinu(DBConnection db, Abonnes toContinue)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		try{
			String[] attribut = {"idAbonnes"};
			Abonnes apres = new Abonnes();
			while(toContinue!=null){
				apres = getAbonnementAfter(db,toContinue);
				if(apres!=null){
					String[] search = {" like '"+apres.getIdabonnes()+"'"};
					double qte = toContinue.getQtecourante();
					double currQte = apres.getQtecourante();
					currQte += qte;
					UpdateNumber(db, "Abonnes", "qteCourante", String.valueOf(currQte), attribut, search);
					apres = getAbonnementById(db, apres.getIdabonnes());
				}
				toContinue = apres;
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	}
	
///Achat offre/abonnement
	void controleOffre(DBConnection db, Offre offre, Compteur cpt)throws Exception{//controle: offre eau = cpt eau, de meme pour elec
		boolean check = true;
		if(db==null){
			db = new DBConnection();
			check = false;
		}
		String natOffre = offre.getNature();
		String natComp = cpt.getNature();
		if(natOffre.compareTo(natComp)!=0){
			throw new Exception("La nature de l'offre differe de la nature du compteur");
		}
		if(!check){
			db.delConnect();
		}
	}
	public void actualiser(DBConnection connect)throws Exception{
		boolean check = true;
		if(connect == null){
			connect = new DBConnection();
			check = false;
		}
		try{
			String[] att = {"fin<="};
			String[] search = {"to_date('"+getToday(connect)+"')"};
			Update(connect, "Abonnes", "etat", "11",att , search);
		}catch(Exception e){
			throw new Exception("L'actualisation echouee, veuillez reactualiser la page");
		}finally{
			if(!check){
				connect.delConnect();
			}
		}
	}
	public void saveAbonnement(DBConnection db, String offre, String cpt)throws Exception{ 
		boolean check = true;
		if(db==null){
			db = new DBConnection();
			check = false;
		}
		try{
			Offre choice = getOffreById(db, offre);
			Compteur tempComp = getCompteur(db, cpt);
			controleOffre(db, choice, tempComp);
			Abonnes[] findForfait = getAbonnement(db, cpt);
			
			double achete = choice.getQte();
			double current = choice.getQte();
			Date deb = getToday(db);
			Date end = addDays(deb, choice.getDuree());
			Abonnes last = new Abonnes();
			int t = findForfait.length;
			if(t>0 && findForfait[0].getFin().after(deb)){
				last = findForfait[0];
				current += last.getQtecourante();
				String[] att = {"idAbonnes"};
				String[] search = {" ='"+ findForfait[0].getIdabonnes() +"'"};
				String[] search2 = {" ='"+ last.getIdabonnes() +"'"};
				UpdateNumber(db, "Abonnes", "etat", "11",att , search);
			}//Si findForfait>0, je regarde si la date de fin de findForfait[0] after today; si c'est le cas: je prends la qte courante et je l'ajoute à la qté courante du nouv forfait
			//Sinon je ne touche pas le reste
			String id = "A"+getSeqValue(db,"idabonnes");
			Abonnes toInsert = new Abonnes(id, offre, cpt, deb, end, choice.getCout(), choice.getMajoration(), achete, current);
			insert(db, "Abonnes", toInsert);
		}
		catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	}
	
///Prelevements a facturer
	public Prelevement[] getPrelevements(DBConnection db)throws Exception{
		Prelevement toReturn = new Prelevement();
		String tabName = "Prelevement";
		String[] attribut = {" etatPv "," indexPreleve "};
		String[] search = {"<10", ">0 ORDER BY DatePrelevmt"};
		Object[] temp = select(db, toReturn, tabName, attribut, search);
		Prelevement[] pv = new Prelevement[temp.length];
		for(int i=0;i<pv.length;i++){
			pv[i] = (Prelevement)temp[i];
		}
	return pv;
	}
	
///Tranches  par client
	public Tranche[] getTranches(String idClient, String nature, DBConnection db)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		ReflectFunctions rf = new ReflectFunctions();
		String tabName = "Clients";
		String packName = "table";
		String[] attribut = {"idClient "};
		String[] search = {"like '"+idClient+"'"};
		Object[] categ = rf.select(db, new Clients(), tabName, attribut, search);
		Clients val = (Clients)categ[0];
		
		String idCateg = val.getIdcat();
		String tab = "Tranche";
		String[] att = {"categClients "," nature "};
		String[] toSearch = {"= '" + idCateg + "'", " like '" + nature + "' order by libelle"};
		Object[] values = rf.select(db, new Tranche(), tab, att, toSearch);
		Tranche[] rep = new Tranche[values.length];
		for(int i=0;i<values.length;i++){
			rep[i] = (Tranche)values[i];
		}
		if(!check){
			db.delConnect();
		}
		return rep;
	}
	
///Les clients
	public Clients[] getClients(DBConnection db)throws Exception{ 
		Clients toReturn = new Clients();
		String tabName = "clients";
		String[] attribut = {" idclient "};
		String[] search = {"like '%%'"};
		Vector temp = selectAll(db, toReturn, tabName, attribut, search, "");
		Clients[] values = new Clients[temp.size()];
		for(int i=0;i<temp.size();i++){
			values[i] = (Clients)temp.get(i);
		}
	return values;
	}
	public Categorie[] getCategorie(DBConnection db)throws Exception{ 
		Categorie toReturn = new Categorie();
		String tabName = "Categorie";
		String[] attribut = {" idcateg "};
		String[] search = {"like '%%'"};
		Vector temp = selectAll(db, toReturn, tabName, attribut, search, "");
		Categorie[] values = new Categorie[temp.size()];
		for(int i=0;i<temp.size();i++){
			values[i] = (Categorie)temp.get(i);
		}
	return values;
	}
	
///Les compteurs
	public String[] getCompteurs(DBConnection db)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		Object toReturn = new Compteur();
		String tabName = "Compteur";
		String[] attribut = {" numcompteur "};
		String[] search = {"like '%%'"};
		Vector temp = selectAll(db, toReturn, tabName, attribut, search, "");
		Compteur[] values = new Compteur[temp.size()];
		String[] resp = new String[values.length];
		for(int i=0;i<temp.size();i++){
			values[i] = (Compteur)temp.get(i);
			resp[i] = values[i].getNumcompteur();
		}
		if(!check){
			db.delConnect();
		}
	return resp;
	}
	public double[] trancher(double conso, int nbTranche, double[] quota){ 
		double[] rep = new double[nbTranche];
		// int i=0;
		for(int i = 0;i<quota.length - 1;i++){
			if(conso>quota[i] && conso>0){
				rep[i] = quota[i];
				conso = conso - quota[i];
			}
			else if(conso<quota[i] && conso>0){
				rep[i] = conso;
				conso = conso - rep[i];
			}
		}
		rep[nbTranche-1] = conso;
	return rep;
	}
	
///Save prelevement
	public Prelevement[] getRecentPv(DBConnection db,String num,int nb,String datepv) throws Exception{
		ReflectFunctions f = new ReflectFunctions();
		String n = String.valueOf(nb);
		Prelevement[] recents = null;
		
		db = new DBConnection();
		Statement st = db.getStat();
		
		String query = "select * from(select * from Prelevement where etatpv>0 and NumCompteur like '" + num + "' and Dateprelevmt<=to_date('"+datepv+"') order by Dateprelevmt desc) where rownum<=" + n;
		try{
			ResultSet rs = st.executeQuery(query);
			Vector temp = new Vector();
			while(rs.next()){
				temp.addElement(new Prelevement(rs.getString("idPrelevement"), rs.getDouble("indexPreleve"), rs.getString("numCompteur"), rs.getDate("datePrelevmt"),rs.getInt("etatPv")));
			}
			
			recents = new Prelevement[temp.size()];
			for(int i=0;i<temp.size();i++){
				recents[i] = (Prelevement)temp.get(i);
			}
		}
		catch(Exception e){
			throw e;
		}finally{
			db.delConnect();
		}
	return recents;
	}
	void verifPv(DBConnection db, String datepv, String num)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		Date pvDate = strToDate(datepv);
		String year = String.valueOf(pvDate.getYear()+1900);
		int m = pvDate.getMonth()+1;
		String month = String.valueOf(m);
		if(m<10){
			month = "0"+m;
		}
		String tab = "Prelevement";
		String[] attribut = {"numCompteur", "etatpv", "datePrelevmt"};
		String[] search = {" like '" + num + "'", "=1", " like '"+year+"-"+month+"-%'"};
		Object[] temp = select(db, new Prelevement(), tab, attribut, search);
		if(temp.length>0){
			throw(new Exception("Prelevement deja effectue pour ce compteur durant " + month + year));
		}
		if(!check){
			db.delConnect();
		}
	}
	public void savePrelevement(String indexPv, String num, String datepv, DBConnection db)throws Exception{ 
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		int idValue = getSeqValue(db,"idpv");
		String nb = String.valueOf(idValue);
		String id = "Pv"+nb;
		Prelevement pv = new Prelevement();
		
		try{
			verifPv(db, datepv, num); ///Un seul pv chaque mois
			pv = new Prelevement(id, Double.parseDouble(indexPv), num, strToDate(datepv));
			Prelevement[] precedant = getRecentPv(db, num, 1, datepv);
			if(precedant[0].getDateprelevmt().after(strToDate(datepv))){
				throw new Exception("Date de prelevement doit s'effectuer apres la date du precedant");
			}
			else if(precedant[0].getIndexpreleve()>Double.parseDouble(indexPv)){
				throw new Exception("Index inferieur au precedant");
			}
			insert(db, "Prelevement" ,pv);
		}
		catch(Exception ex){
			throw ex;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	}
	public void savePrelevement(String id,String indexPv, String num, String datepv, DBConnection db)throws Exception{ 
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
	
		Prelevement pv = new Prelevement();

		try{
			verifPv(db, datepv, num); ///Un seul pv chaque mois
			pv = new Prelevement("", Double.parseDouble(indexPv), num, strToDate(datepv));
			Prelevement[] precedant = getRecentPv(db, num, 1, datepv);
			if(precedant[0].getDateprelevmt().after(strToDate(datepv))){
				throw new Exception("Date de prelevement doit s'effectuer apres la date du precedant");
			}
			else if(precedant[0].getIndexpreleve()>Double.parseDouble(indexPv)){
				throw new Exception("Index inferieur au precedant");
			}
			String[] att = {"idPrelevement "};
			String[] search = {"like '"+ id + "'"};
			update(db, pv, "Prelevement",  att,  search);
		}
		catch(Exception ex){
			throw ex;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	}
///Tranche individuelle
	public boolean insertCateg(DBConnection db,Categorie ct) throws Exception{
		boolean rep = insert(db,"Categorie",ct);
	return rep;
	}
	public boolean insertTranche(DBConnection db,Tranche tr) throws Exception{
		boolean result = insert(db,"Tranche",tr);
	return result;
	}
	public void updateClient(DBConnection db,String idCl,String idCat) throws Exception{
		db = new DBConnection();
		String query = "UPDATE Clients SET idCat = '" + idCat + "' where idclient = '" + idCl + "'";
		db.getStat().execute(query);
		db.getStat().execute("commit");
		
		db.delConnect();
	}

///Abonnements par rapport à une date et par compteur
	Vector getAbonnementByDate(DBConnection db, String cpt, String datepv1, String datepv2)throws Exception{
		boolean controle = true;
		if(db==null){
			db = new DBConnection();
			controle = false;
		}
		Vector toreturn = new Vector();
		Abonnes result = new Abonnes();
		String tabName = "Abonnes";
		Object toReturn = new Abonnes();
		String[] att1 = {"compteur", "to_date('"+datepv1+"') "};
		String[] search1 = {" like '"+cpt+"'", "BETWEEN DEBUT AND FIN ORDER BY FIN DESC"};
		String[] att2 = {"compteur", "debut ", "fin "};
		String[] search2 = {" like '"+cpt+"'", "between '"+datepv1+"' AND to_date('" +datepv2+"')",  "between '"+datepv1+"' AND to_date(" + "'"+datepv2+"') ORDER BY FIN DESC"};
		String[] att3 = {"compteur", "to_date('"+datepv2+"') "};
		String[] search3 = {" like '"+cpt+"'", "BETWEEN DEBUT AND FIN ORDER BY FIN DESC"};
		
		String coo = " AND ";
		int etat = 1; ///1:normal, 11:un abonnement en cours au moment du pv2, 21:abonnement en cours entre pv1 et pv2, 31:abonnement en cours au moment du pv1
		try{
			Object[] temp = new Abonnes[0];
			Vector temp1 = selectAll(db, toReturn, tabName, att1, search1,coo);
			Vector temp2 = selectAll(db, toReturn, tabName, att2, search2,coo);
			Vector temp3 = selectAll(db, toReturn, tabName, att3, search3,coo);
			temp = temp1.toArray();
			etat = 11;
			int t1 = temp1.size();
			int t2 = temp2.size();
			int t3 = temp3.size();
			if(t1==0){
				temp = temp2.toArray();
				etat = 21;
				if(t2==0){
					temp = temp3.toArray();
					etat = 31;
					if(t3==0){
						etat = 1;
					}
				}
			}
			int t = temp.length;
			if(t>0){
				result = (Abonnes)temp[0];
				toreturn.addElement(result);
				toreturn.addElement(etat);
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!controle){
				db.delConnect();
			}
		}
	return toreturn;
	}
	
///Factures/Situation par client
	public Facture[] factByCl(String idc, DBConnection db)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		Facture[] result = new Facture[0];
		try{
			String[] att = {"idclient"};
			String[] search = {" like '"+idc+"'"};
			Object toreturn = new Facture();
			String tab = "Facture";
			Vector<Facture> temp = selectAll(db, toreturn, tab, att, search, " AND ");
			if(temp.size()>0){
				result = temp.toArray(new Facture[0]);
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	return result;
	}
	public FactureAvoir[] factAvByCl(String idc, DBConnection db)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		FactureAvoir[] result = new FactureAvoir[0];
		try{
			String[] att = {"idclient"};
			String[] search = {" like '"+idc+"'"};
			Object toreturn = new FactureAvoir();
			String tab = "FactureAvoir";
			Vector<FactureAvoir> temp = selectAll(db, toreturn, tab, att, search, " AND ");
			if(temp.size()>0){
				result = temp.toArray(new FactureAvoir[0]);
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	return result;
	}

///Facturation 
	public DetailPrelev[] getDetailPrelevs(DBConnection db) throws Exception{
		DetailPrelev toReturn = new DetailPrelev();
		String tabName = "DetailPrelev";
		String[] attribut = {" etatPv "," indexPreleve "};
		String[] search = {"<10", ">0"};
		Object[] temp = select(db, toReturn, tabName, attribut, search);
		DetailPrelev[] pv = new DetailPrelev[temp.length];
		for(int i=0;i<pv.length;i++){
			pv[i] = (DetailPrelev)temp[i];
		}
	return pv;
	}
	public DetailPrelev[] findPv(String[] idpv, DBConnection db)throws Exception{
		DetailPrelev[] result = new DetailPrelev[0];
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		try{
			DetailPrelev[] tempPv = getDetailPrelevs(db);
			int nb = 0;
				for(int j=0;j<idpv.length;j++){
					for(int i=0;i<tempPv.length;i++){
						if(idpv[j].compareToIgnoreCase(tempPv[i].getIdprelevement())==0){
							nb++;
						}
					}
				}
			result = new DetailPrelev[nb];
			int indice = 0;
			for(int j=0;j<idpv.length;j++){
					for(int i=0;i<tempPv.length;i++){
						if(idpv[j].compareToIgnoreCase(tempPv[i].getIdprelevement())==0){
							result[indice] = tempPv[i];
							indice++;
						}
					}
				}
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	return result;
	}
	public DetailFacture[] detailler(DBConnection db,String nature,String idfact)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		String[] attribut = {" idfacture "};
		String[] search = {"like '"+idfact +"'"};
		String tab = "Facture";
		Object[] temp = select(db, new Facture(), tab, attribut, search);
		Facture fact = (Facture)temp[0];
		double taux = fact.getMajfacture();
		
		///Trancher 
		Tranche[] tranches  = getTranches(fact.getIdclient(), nature, db);
		int nb = tranches.length;
		double[] quota = new double[nb];
		for(int i=0;i<nb;i++){
			quota[i] = tranches[i].getQtelim();
		}
		double[] consos = trancher(fact.getTotalconso(), nb, quota);
		
		///Inserer details facture
		DetailFacture[] detail = new DetailFacture[nb];
		for(int i=0;i<nb;i++){
			int seq = getSeqValue(db,"factDet");
			String idfactd = "fd" + seq;
			detail[i] = new DetailFacture();
			detail[i].setIdfactdet(idfactd);
			detail[i].setIdfact(idfact);
			detail[i].setNom(tranches[i].getLibelle());
			detail[i].setConso(consos[i]);
			detail[i].setPu(tranches[i].getPrixunitaire()*taux);
			double st = consos[i]*tranches[i].getPrixunitaire();
			detail[i].setSoustotal(st);
		}
		if(!check){
			db.delConnect();
		}
	return detail;
	}
	public void updateEtatPv(String idpv,DBConnection db) throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		String query = "UPDATE Prelevement set EtatPv = " + 21 + " where idPrelevement = '" + idpv + "'";
		db.getStat().execute(query);
		if(!check){
			db.delConnect();		
		}
	}
	public Facture saveFacture(String idpv, DBConnection db)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
			int id = getSeqValue(db,"idFact");
			String idf = "ft"+id;
			Date day = getToday(db);
			String idc = new String();
			String num = new String();
			String nature = new String();
			int etat = 0;
			
			String view = "DetailPrelev";
			String[] attribut = {"idPrelevement"};
			String[] search = {" like '" + idpv + "'"};
			Object[] tempDetail = select(db, new DetailPrelev(), view, attribut, search);
			DetailPrelev tempDP = (DetailPrelev)tempDetail[0];
			idc = tempDP.getIdclient();
			num = tempDP.getNumcompteur();
			nature = tempDP.getNature();
			etat = Integer.parseInt(tempDP.getEtatpv());
			
		///Les 2 plus recents prelevements du compteur
			Prelevement[] recents = getRecentPv(db,num,2,tempDP.getDateprelevmt().toString());
			
			double conso = recents[0].getIndexpreleve() - recents[1].getIndexpreleve();
			Date dateConso = recents[1].getDateprelevmt();
			String mois = String.valueOf(dateConso.getMonth()+1);
			String annee = String.valueOf(dateConso.getYear()+1900);
			if(recents[1].getEtatpv()<11){
				throw new Exception("Veuillez facturer le precedant prelevement");
			}
			
		///Facturation
			//Vérifier si un abonnement est valide au moment du pv et majorer
			
			int etatFacture = 0; ///normal
			double majoration = 1;
			
				String[] att = {"idAbonnes"};
				Date Datepv1 = recents[0].getDateprelevmt();
				Date Datepv2 = recents[1].getDateprelevmt();
				Vector tempAb = getAbonnementByDate(db, tempDP.getNumcompteur(), Datepv1.toString(), Datepv2.toString());
				if(tempAb.size()!=0){
					Abonnes current = (Abonnes)tempAb.get(0);
					String[] searching = {" like'"+current.getIdabonnes() + "'"};
					int pos = ((Integer)tempAb.get(1)).intValue();
					double consoPrepaid = current.getQtecourante();
					double qFinal = 0;
					if(pos==11){///Majorer
						if(conso>consoPrepaid){
							conso -= consoPrepaid;
							majoration = current.getTaux();
						}else if(conso<=consoPrepaid){
							conso = 0;
							qFinal = consoPrepaid - conso;
						}
					}else if(pos==1 || pos==31){
						if(conso>consoPrepaid){
							conso -= consoPrepaid;
						}else if(conso<=consoPrepaid){
							conso = 0;
							qFinal = consoPrepaid - conso;
						}
					}
					UpdateNumber(db, "Abonnes", "qteCourante", String.valueOf(qFinal), att, searching);
					updateContinu(db, current);
				}
				
			Facture insert = new Facture(idf,day,conso,mois,annee,idc,idpv,majoration);
			insert.setTotal(0);
			if(etat > 1){
				throw new Exception("Prelevement deja facture");
			}
			else{
				String[] at = {"idfacture "};
				String[] sea = {"like '"+idf+ "'"};
				boolean fact = insert(db,"Facture",insert);
				updateEtatPv(idpv, db);
				DetailFacture[] ffa = detailler(db,nature,idf);
				for(int i=0;i<ffa.length;i++){
					boolean v = insert(db, "DetailFacture", ffa[i]); //Insertion detailsFacture
				}
				double total = Somme(ffa, "sousTotal");
				UpdateNumber(db, "Facture", "total", ""+total, at, sea);
			}
			
		if(!check){
			db.delConnect();
		}
	return insert;
	}
	void checkProprio(String[] idpv,DBConnection dbcon)throws Exception{
		boolean check = true;
		if(dbcon==null){
			dbcon = new DBConnection();
			check = false;
		}
		String[] temp = new String[idpv.length];
		for(int i=0;i<idpv.length;i++){
			String query = "SELECT * FROM DetailPrelev where idPrelevement = '".concat(idpv[i]).concat("'");
			ResultSet rs = dbcon.getStat().executeQuery(query);
			while(rs.next()){
				temp[i] = rs.getString("idClient");
			}
		}
		for(int i=0;i<temp.length;i++){
			for(int j=i+1;j<temp.length;j++){
				if(temp[i].compareToIgnoreCase(temp[j]) != 0){
					throw new Exception("Les pv doivent avoir le meme proprio");
				}
			}
		}
		if(!check){
			dbcon.delConnect();
		}
	}
	public FactureClients rassembler(String[] idpv, DBConnection db)throws Exception{
	boolean check = true;
	DetailFacture[] valiny = new DetailFacture[0];
	FactureClients result = new FactureClients();
	if(db==null){
		check = false;
		db = new DBConnection();
	}
		try{
			checkProprio(idpv,db);
			DetailPrelev[] detPv = findPv(idpv, db);
			for(int i=0;i<idpv.length;i++){
				Facture temp = saveFacture(idpv[i], db);
				DetailFacture[] det = detailler(db, detPv[i].getNature(), temp.getIdfacture());
				double[] conso = new double[det.length];
				double[] sousTotal = new double[det.length];
				String[] libelle = new String[det.length];
				String[] idFact = new String[det.length];
				double[] pu = new double[det.length];
				for(int c=0; c<det.length; c++){
					conso[c] = conso[c]+det[c].getConso();
					sousTotal[c] = sousTotal[c]+det[c].getSoustotal();
					libelle[c] = det[c].getNom();
					idFact[c] = det[c].getIdfact();
					pu[c] = det[c].getPu();
					DetailFacture tempValiny = new DetailFacture("fd" + getSeqValue(db,"factDet"),idFact[c], libelle[c], conso[c], pu[c], sousTotal[c]);
					if(detPv[i].getNature().compareToIgnoreCase("elect")==0){
						result.addElectricity(tempValiny);
					}else{
						result.addWater(tempValiny);
					}
				}
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	return result;
	}
	
///Annulation de factures
	FactureAvoir[] getFAByIdFact(DBConnection db, String factAn)throws Exception{
		String tab = "FactureAvoir";
		String[] attribut = {"idFacture "};
		String[] search = {"like '"+factAn + "'"};
		Vector<FactureAvoir> temp = selectAll(db, new FactureAvoir(), tab, attribut, search, "AND");
		FactureAvoir[] res = new FactureAvoir[0];
		if(temp.size()>0){
			res = temp.toArray(new FactureAvoir[0]);
		}
	return res;
	}
	void verifSommeMinus(DBConnection db, String factAn, String sum)throws Exception{
		Facture fact = getFactById(db, factAn);
		FactureAvoir[] annules = getFAByIdFact(db, factAn);
		int nb = annules.length;
		double sumAnnules = 0;
		double total = fact.getTotal();
		double newSom = 0;
		newSom = Double.parseDouble(sum);
		
		if(newSom>total){
			throw new Exception("Somme a annuler sup a la somme dans la facture");
		}else{
			if(nb>0){
				sumAnnules = Somme(annules, "sommeMoins");
				sumAnnules = -sumAnnules;
				newSom = sumAnnules + Double.parseDouble(sum);
				if(sumAnnules>total || newSom>total){
					throw new Exception("Somme a annuler sup a la somme dans la facture");
				}
			}
		}
	}
	public FactureAvoir annulerIntegraleFacture(DBConnection db, String factAn, String sum, String FADate)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		FactureAvoir res = null;
		try{
			Facture fact = getFactById(db, factAn);
			String idFact = fact.getIdfacture();
			String cl = fact.getIdclient();
			String idAn = "Av"+getSeqValue(db, "idAvoir");
			Date da = fact.getDatefacture();
			Date df = strToDate(FADate);
			double moins = Double.parseDouble(sum);
			res = new FactureAvoir(idAn, idFact, cl, da, df, -moins);
			String[] attribut = {"idfacture"};
			String[] search = {" like '"+ idFact + "'"};
			String idpv = fact.getIdprelevement();
			
			///Update Etatpv(0) et ajouter dans pvAnnules
			Prelevement prelev = getPvById(db, idpv);
			String idannule = "An"+getSeqValue(db, "idAnnules"); 
			String cpt = prelev.getNumcompteur();
			double index = prelev.getIndexpreleve();
			Date datePv = prelev.getDateprelevmt();
			PvAnnules toAnnule = new PvAnnules(idannule, idpv, cpt, index, datePv);
			String[] att = {"idPrelevement "};
			String[] sea = {"like '"+idannule + "'"};
			
			UpdateNumber(db, "Prelevement", "etatPv", "0", att, sea);
			insert(db, "PvAnnules", toAnnule);
			
			UpdateNumber(db, "Facture", "etatFacture", "-1", attribut, search);
			insert(db, "FactureAvoir", res);
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
		
	return res; 
	}
	public FactureAvoir annulerFacture(DBConnection db, String factAn, String montant, String dateAv)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		FactureAvoir toinsert = null;
		try{
			verifSommeMinus(db, factAn, montant);
			Facture fact = getFactById(db, factAn);
			String[] attribut = {"idfacture"};
			String[] search = {" like '"+ fact.getIdfacture() + "'"};
			double total = fact.getTotal();
			double moins = Double.parseDouble(montant);
			FactureAvoir[] annules = getFAByIdFact(db, factAn);
			double somme = 0;
			if(annules.length>0){
				somme = Somme(annules, "sommeMoins");
			}
			somme += moins;
			
			if(moins==total || somme==total){
				toinsert = annulerIntegraleFacture(db, factAn, montant, dateAv);
			}else{
				String idAvoir = "Av" + getSeqValue(db, "idAvoir");
				toinsert = new FactureAvoir(idAvoir, fact.getIdfacture(), fact.getIdclient(), fact.getDatefacture(), strToDate(dateAv), -moins);
				UpdateNumber(db, "Facture", "etatFacture", "0", attribut, search);
				insert(db, "FactureAvoir", toinsert);
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	return toinsert;
	}
	
///Addings
	void verifClient(String nom, String categ,DBConnection db)throws Exception{
		String tabName = "Clients";
		String[] attribut = {"name", "idcat"};
		String[] search = {" like '"+nom+"'", " like '"+categ+"'"};
		Object[] temp = select(db, new Clients(), tabName, attribut, search);
		if(temp.length!=0){
			throw new Exception("Client deja enregistre");
		}
	}
	public void saveClient(DBConnection db, String nom, String categ)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		try{
			verifClient(nom, categ, db);
			String id = "Cl"+getSeqValue(db, "idClient");
			Clients toInsert = new Clients(id, nom, categ);
			insert(db, "Clients", toInsert);
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	}
	public void saveCompteur(DBConnection db, String id, String nature)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		String idPv = "Pv"+getSeqValue(db,"idpv");
		double indexPv = 0;
		
		String num = "Cp"+getSeqValue(db, "idCp");
		Compteur toInsert = new Compteur(num, id, nature);
		Date datepv = getToday(db);
		int etat = 21;
		Prelevement pv = new Prelevement(idPv, indexPv, num, datepv, etat);
		
		insert(db, "Compteur", toInsert);
		insert(db, "Prelevement", pv);
		if(!check){
			db.delConnect();
		}
	}
	
	public PvAnnules[] getPvAnnules(DBConnection db)throws Exception{
		PvAnnules[] res = new PvAnnules[0];
		String tab = "PvAnnules";
		String[] attribut = {"idPvAn "};
		String[] search = {"like '%%'"};
		Object toReturn = new PvAnnules();
		Vector<PvAnnules> temp = selectAll(db, toReturn, tab, attribut, search, "");
		if(temp.size()>0){
			res = temp.toArray(new PvAnnules[0]);
		}
	return res;
	}
}