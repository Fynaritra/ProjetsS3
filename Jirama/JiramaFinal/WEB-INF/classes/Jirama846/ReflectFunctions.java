package fonction;

import java.sql.*;
import java.lang.String;
import java.lang.Long;
import java.lang.Math;
import java.util.Vector;
import java.math.BigDecimal;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import html.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connexion.DBConnection;

public class ReflectFunctions{
	public Date addDays(Date dt, int nb){
		Date res = new Date(dt.getYear(), dt.getMonth(), dt.getDate()+nb);
	return res;
	}
	public String[] filtrer(String[] temp){
		int nb = 0, ind = 0;
		for(int l=0;l<temp.length;l++){
			for(int j=l+1;j<temp.length;j++){
				if(temp[l].compareTo(temp[j])==0){
					temp[j] = "null";
				}
			}
		}
		for(int i=0;i<temp.length;i++){
			if(temp[i].compareTo("null")!=0){
				nb++;
			}
		}
		String[] values = new String[nb];
		for(int i=0;i<temp.length;i++){
			if(temp[i].compareTo("null")!=0){
				values[ind] = temp[i];
				ind++;
			}
		}
	return values;
	}
	public String joinString(String[] ls, String join){
		String value = new String();
		int nb = ls.length;
		if(nb>0){
			int i=0;
			for(i=i;i<nb-1;i++){
				value += ls[i] + join;
			}
		value+= ls[i];
		}
	return value; 
	}
	public String formatDouble(double somme, String join){
		String result = new String();
		String montant = BigDecimal.valueOf(somme).toPlainString();
		String dot = "\\.";
		String[] argent = montant.split(dot);
		String entier = argent[0];
		String decimal = argent[1];
		Vector<String> tempResult = new Vector();
		
		int nb = entier.length();
		if(nb>3){
			int n = nb;
			while(n>3){
				tempResult.addElement(entier.substring(n-3,n));
				n = n-3;
			}
			tempResult.addElement(entier.substring(0,n));
		}else{
			tempResult.addElement(entier);
		}
		String[] vola = tempResult.toArray(new String[0]);
		String[] money = new String[vola.length];
		int indice = vola.length-1;
		for(int i=0;i<vola.length;i++){
			money[i] = vola[indice];
			indice--;
		}
		
		result = joinString(money, join) + ","+decimal;
	return result;
	}
	
///Conversion double en lettres
	public String[] splitNumberToHundreds(double number){
        int numberInt = (int)number;
        String numberString = String.valueOf(numberInt);
        int numberLength = numberString.length();
        int resultLength = (int)(numberLength/3);
        if(numberLength%3!=0)
        {
            resultLength++;
        }
        String[] result = new String[resultLength];
        // String[] resultTemp = new String[resultLength];
        char[] resultTempChar = new char[3];
        int rest = numberLength-1;
        int restResult = resultLength - 1;
        while(rest >= 0)
        {
            if(rest >= 2)
            {
                for(int j = 2; j>=0; j--)
                {
                    resultTempChar[j] = numberString.charAt(rest);
                    rest--;
                    
                }
            }else{
                resultTempChar = new char[rest+1];
                for(int restTemp = rest; restTemp >= 0; restTemp--)
                {
                    resultTempChar[restTemp] = numberString.charAt(rest);
                    rest--;
                }
            }
            result[restResult] = String.valueOf(resultTempChar);
            restResult--;
        }
        return result;
    }
    public String hundredToString(int number){
        String result = "";
        if(number < 1000){
            String[] unity1 = {"","un","deux","trois","quatre","cinq","six","sept","huit","neuf"};
            String[] unity2 = {"","onze","douze","treize","quatorze","quinze","seize","dix-sept","dix-huit","dix-neuf"};
            String[] decade = {"","dix","vingt","trente","quarante","cinquante","soixante","soixante","quatre-vingt","quatre-vingt"};
            int[] divideNumber = new int[3];
            int puissance = 2, numberTemp = number, puissanceCalcul = 0;
            for (int i = 0; i < divideNumber.length; i++) {
                puissanceCalcul = (int)Math.pow(10,puissance);
                divideNumber[i] =(int)numberTemp/puissanceCalcul;
                numberTemp =(int) numberTemp-(divideNumber[i]*puissanceCalcul);
                puissance--;
            }
            if(divideNumber[0] != 0){
                if(divideNumber[0] != 1)
                {
                    result +=unity1[divideNumber[0]]+" cent";
                }else{
                    result += "cent";
                }
                
            }
            if(divideNumber[1] != 0){
                if(divideNumber[1] != 1){
                    result +=" "+decade[divideNumber[1]];
                    if(divideNumber[2] != 0)
                    {
                        if(divideNumber[1] == 7 || divideNumber[1] == 9)
                        {
                            result +=" "+unity2[divideNumber[2]];
                        }else{
                            result +=" "+unity1[divideNumber[2]];
                        }
                    }
                }else{
                    result +=" "+unity2[divideNumber[2]];
                }

            }else{
                result += unity1[divideNumber[2]];
            }
        }else{
            result = "non calculable";
        }
        return result;
    }
    public String lettrer(double number){
		String dot = "\\.";
		String[] values = String.valueOf(number).split(dot);
		String tempEntier = values[0];
		String tempDec = values[1];
        String result = "";
		String finalRs = new String();
		String decimal = hundredToString(Integer.parseInt(tempDec));
        String[] plusHundred = {"","mille","million","milliard"};
        String[] hundredSplit = this.splitNumberToHundreds(Integer.parseInt(tempEntier));
        int[] hundredSplitInt = new int[hundredSplit.length];
        for (int i = 0; i <hundredSplit.length; i++) {
            hundredSplitInt[i] = Integer.parseInt(hundredSplit[i]);
        }
        String[] resultTemp = new String[hundredSplit.length];
        for (int i = 0; i < resultTemp.length; i++) {
            resultTemp[i] = this.hundredToString(hundredSplitInt[i]);
        }
        int indice = resultTemp.length - 1;
        String hundredTemp = "";
        for (int i = 0; i < resultTemp.length; i++) {
            if(hundredSplitInt[i]==1 || indice == 0){
                hundredTemp = plusHundred[indice];
            }else{
                hundredTemp = plusHundred[indice]+"s";
            }
            if(hundredSplitInt[i]==1 && indice == 1){
                result +=" "+hundredTemp;
                indice--;
            }else{
                result +=" "+resultTemp[i]+" "+hundredTemp;
                indice--;
            }
        }
		String[] ls = {result, decimal};
		if(result.compareTo("")!=0){
			finalRs = joinString(ls, ",");
		}else{
			finalRs = "zero";
		}
        return finalRs;
    }
	
	public double[] convert(double toConvert){
		String val = BigDecimal.valueOf(toConvert).toPlainString();
		String dot = "\\.";
		String[] values = val.split(dot);
		String tempEntier = values[0];
		String tempDec = values[1];
		//Obtenir parties entiere et decimale
		double entier = Double.parseDouble(tempEntier);
		double dec = Double.parseDouble(tempDec);
		System.out.println("nb " + val);
			
		//Les decomposer
		double mld = entier/1000000000;
		String ml = BigDecimal.valueOf(mld).toPlainString();
		String[] sep1 = ml.split(dot);
		double Mild = Double.parseDouble(sep1[0]);
		double resteMild = Double.parseDouble(sep1[1]);
		System.out.println("milliard " + ml);
		
		double miln = resteMild/1000000;
		String resteMln = BigDecimal.valueOf(miln).toPlainString();
		String[] sep2 = resteMln.split(dot);
		double Miln = Double.parseDouble(sep2[0]);
		double resteMiln = Double.parseDouble(sep2[1]);
		System.out.println("million " + resteMln);
		
		double mil = resteMiln/1000;
		String resteMl = BigDecimal.valueOf(mil).toPlainString();
		String[] sep3 = resteMl.split(dot);
		double Mil = Double.parseDouble(sep3[0]);
		double resteMil = Double.parseDouble(sep3[1]);
		System.out.println("millier " + resteMl);
		System.out.println("reste " + resteMil);
		
		double hundred = resteMil;
		double[] converted = new double[5];
		converted[0] = Mild;
		converted[1] = Miln;
		converted[2] = Mil;
		converted[3] = hundred;
		converted[4] = dec;
		
	return converted; 
	}
	public String lettres(double nombre){
	String[] all = {"un","deux","trois","quatre","cinq","six","sept","huit","neuf","dix","onze","douze","treize","quatorze","quinze","seize","dix-sept","dix-huit","dix-neuf","vingt","vingt et un","vingt-deux","vingt-trois","vingt-quatre","vingt-cinq","vingt-six","vingt-sept","vingt-huit","vingt-neuf","trente","trente et un","trente-deux","trente-trois","trente-quatre","trente-cinq","trente-six","trente-sept","trente-huit","trente-neuf","quarante","quarante et un","quarante-deux","quarante-trois","quarante-quatre","quarante-cinq","quarante-six","quarante-sept","quarante-huit","quarante-neuf","cinquante","cinquante et un","cinquante-deux","cinquante-trois","cinquante-quatre","cinquante-cinq","cinquante-six","cinquante-sept","cinquante-huit","cinquante-neuf","soixante","soixante et un","soixante-deux","soixante-trois","soixante-quatre","soixante-cinq","soixante-six","soixante-sept","soixante-huit","soixante-neuf","soixante-dix","soixante et onze","soixante-douze","soixante-treize","soixante-quatorze","soixante-quinze","soixante-seize","soixante-dix-sept","soixante-dix-huit","soixante-dix-neuf","quate-vingt","quate-vingt-un","quate-vingt-deux","quate-vingt-trois","quate-vingt-quatre","quate-vingt-cinq","quate-vingt-six","quate-vingt-sept","quate-vingt-huit","quate-vingt-neuf","quate-vingt-dix","quate-vingt-onze","quate-vingt-douze","quate-vingt-treize","quate-vingt-quatorze","quate-vingt-quinze","quate-vingt-seize","quate-vingt-dix-sept","quate-vingt-dix-huit","quate-vingt-dix-neuf"};
	String ret = "";
	Double nb = new Double(nombre);
	for(int i=0;i<all.length;i++){
		double temp = i+1;
		Double tempo = new Double(temp);
		if(nb.compareTo(temp)==0){
			ret = all[i];
		}
	}
	return ret;
	}
	double[] centaine(double nb){
	double div = nb/100;
	String dot = "\\.";
	String resteC = BigDecimal.valueOf(div).toPlainString();
	String[] cent = resteC.split(dot);
	double[] val = new double[cent.length];
	for(int i=0;i<val.length;i++){
		val[i] = Double.parseDouble(cent[i]);
	}
	return val;
	}
	String centaines(double nb){
	double[] cents = centaine(nb);
	Double temp = new Double(cents[0]);
	Double zero = 0.0;
	String val = new String();
	if(temp.compareTo(zero)==0){
		val = lettres(cents[1]);
	} 
	else{
		val = " cent " + lettres(cents[1]);
		if(lettres(cents[0]).compareToIgnoreCase("un")!=0){
			val = lettres(cents[0]) + " cent " + lettres(cents[1]);
		}
	}
	return val;
	}
	public String allString(double nombre){ ///A regarder de pres
		String[] addings = {"milliard","million","mille",""};
		double[] convertis = convert(nombre);
		String value = new String();
		int j = 0;
		for(int i=0;i<convertis.length - 1;i++){
			if(centaines(convertis[i]).compareToIgnoreCase("un")!=0){
				System.out.println(convertis[i]);
				value += centaines(convertis[i]);
			}
			if(new Double(convertis[i]).compareTo(0.0) != 0){
				value += " " + addings[j] + " ";
			}
			j++;
		}
		double[] temp = centaine(convertis[convertis.length-1]);
		value += "." + centaines(convertis[convertis.length-1]);
	return value;
	}
	
	public Timestamp getCurrTimestamp(DBConnection db)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		String query = "SELECT CURRENT_TIMESTAMP AS now from dual";
		Timestamp result = null;
		ResultSet rs = db.getStat().executeQuery(query);
		while(rs.next()){
			result = rs.getTimestamp("now");
		}
		rs.close();
		if(!check){
			db.delConnect();
		}
	return result;
	}
	public int getDiffDate(Date begin, Date end){
		int result = 0;
		long diff = end.getTime() - begin.getTime();
		long hour = 3600 * 1000;
		long jour = 24 * hour; 
		long days = diff/jour;
		long year = days/365;
		Long temp = new Long(year);
		result = temp.intValue();
	return result;
	}
	public Date getToday(DBConnection db)throws Exception{
		String query = "select sysdate as now from dual";
		boolean check = true;
		if(db==null){
			db = new DBConnection();
			check = false;
		}
		ResultSet rs = db.getStat().executeQuery(query);
		Date today =  null;
		while(rs.next()){
			today = rs.getDate("now");
		}
		rs.close();
		if(!check){
			db.delConnect();
		}
	return today;
	}
	public int getSeqValue(DBConnection db,String seqName)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		int resp = 0;
		// db.connect();
		Statement st = db.getStat();
		String query = "SELECT " + seqName + ".NEXTVAL AS VALUE FROM DUAL";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			resp = rs.getInt("VALUE");
		}
		rs.close();
		if(!check){
			db.delConnect();
		}
	return resp;
	}
	
///Somme objets
	String firstMaj(String str){
		str = str.trim();
		String valiny = new String();
		str = str.toLowerCase();
		char[] cara = str.toCharArray();
		char[] vao = new char[cara.length-1];
		String debut = String.valueOf(cara[0]);
		debut = debut.toUpperCase();
		for(int i=0;i<vao.length;i++){
			vao[i] = cara[i+1];
		}
		String fin = new String(vao);
		valiny = debut.concat(fin);
	return valiny;
	}
	public double Somme(Object[] liste, String attribut)throws Exception{
		double somme = 0;
		Object max = new Object();
		String maj = firstMaj(attribut);
		Object[] objet = new Object[0];
		Class[] clas = new Class[objet.length];
		for(int i=0;i<clas.length;i++){
			clas[i] = objet[i].getClass();
		}
		Class classe = null;
		if(liste.length>0){
			classe = liste[0].getClass();
		}
		try{
			String nomfonc = "get".concat(maj);
			Method fonction = classe.getMethod(nomfonc,clas);
			for(int i=0;i<liste.length;i++){
				somme+=(double)fonction.invoke(liste[i],objet); 
			}
		}catch(Exception e){
			throw e;
		}
	return somme;
	}
	
	///Minimum et Maximum
	public Object getMin(Object[] liste, String attribut)throws Exception{
		Object max = null;
		String maj = firstMaj(attribut);
		Object[] objet = new Object[0];
		Class[] clas = new Class[objet.length];
		for(int i=0;i<clas.length;i++){
			clas[i] = objet[i].getClass();
		}
		Class classe = liste[0].getClass();
		
		String nomfonc = "get".concat(maj);
		Method fonction = classe.getMethod(nomfonc,clas);
		
		if(liste.length == 1){
			max = liste[0];
		}
		for(int i=0;i<liste.length;i++){
			for(int j=i+1;j<liste.length;j++){
				if(Double.compare((double)fonction.invoke(liste[i],objet),(double)fonction.invoke(liste[j],objet))<0){
					max = liste[i];
				}else{
					max = liste[j];
				}
			}
		}
		return max;
	}

	///Tri croissant
	public void triCroissant(Object[] liste, String attribut)throws Exception{
		String maj = firstMaj(attribut);
		Object[] objet = new Object[0];
		Class[] clas = new Class[objet.length];
		for(int i=0;i<clas.length;i++){
			clas[i] = objet[i].getClass();
		}
		Class classe = liste[0].getClass();
		
		String nomfonc = "get".concat(maj);
		Method fonction = classe.getMethod(nomfonc,clas);
		
		for(int i=0;i<liste.length;i++){
			for(int j=i+1;j<liste.length;j++){
				if(Double.compare((double)fonction.invoke(liste[i],objet),(double)fonction.invoke(liste[j],objet))>0){
					Object temp = liste[i];
					liste[i] = liste[j];
					liste[j] = temp;
				}
			}
		}
	} 
	
	///Find object with name
	public Object initObject(String name,String pack)throws Exception{
		Object find = new Object();
		try{
			Class toFind = Class.forName(pack + "." + name);
			find = toFind.newInstance();
		}
		catch(Exception e){
			throw new Exception("La classe n`existe pas, ou n`a pas de constructeur sans param ou non accessible");
		}
	return find;
	}
	public int countLigne(ResultSet ra)throws Exception{
		int n = 0;
		if(ra.last()){
			n = ra.getRow();
		}
		ra.beforeFirst();
	return n;
	}
	
///Ma fonction Select
	public Object[] select(DBConnection dbcon,Object toReturn, String tabName, String[] attribut,String[] search)throws Exception{
		boolean check = true;
		if(dbcon==null){
			check = false;
			dbcon = new DBConnection();
		}
		Statement stat = dbcon.getStat();
		String complement = attribut[0] + search[0];
		for(int k=1;k<attribut.length && attribut.length>1;k++){
			complement = complement + " AND " + attribut[k] + search[k];
		}
		String queryToSend = "SELECT * FROM " + tabName + " WHERE "  + complement;
		System.out.println("select " + queryToSend);
		
		ResultSet results = stat.executeQuery(queryToSend);
		
		int nbRow = countLigne(results);
		
		Class cl = toReturn.getClass();
		Field[] fields = cl.getDeclaredFields();
		int nbCol = fields.length;
		String[] param = new String[nbCol];
		String[] para = new String[nbCol];
		Method[] setters = new Method[nbCol];
		Class[] paramTypes = new Class[nbCol];
		Object[] values = new Object[nbRow];
		
		Class[] clas = new Class[1];
		Vector tempResult = new Vector();
		int n = 0;
		
		while(results.next()){
			for(int i=0;i<fields.length;i++){
				para[i] = fields[i].getName();
				param[i] = "set".concat(firstMaj(fields[i].getName()));
				paramTypes[i] = fields[i].getType();
				clas[0] = paramTypes[i];
				
				String typ = paramTypes[i].getSimpleName();
				if((typ.compareToIgnoreCase("String"))==0){
					tempResult.addElement(results.getString(para[i]));
				}
				else if((typ.compareToIgnoreCase("Int"))==0){
					tempResult.addElement(results.getInt(para[i]));
				}
				else if((typ.compareToIgnoreCase("Timestamp"))==0){
					tempResult.addElement(results.getTimestamp(para[i]));
				}
				else if((typ.compareToIgnoreCase("Date"))==0){
					tempResult.addElement(results.getDate(para[i]));
				}
				else{
					tempResult.addElement(results.getDouble(para[i]));
				}
				setters[i] = cl.getMethod(param[i],clas);
			}
		}
		for(int i=0;i<nbRow;i++){
			values[i] = (Class.forName(toReturn.getClass().getName())).newInstance();
			for(int j=0;j<setters.length;j++){
				Object tempArg = tempResult.get(n);
				setters[j].invoke(values[i],tempArg);
				n++;
			}
		}
		results.close();
		if(!check){
			dbcon.delConnect();
		}
	return values;
	}
	public Vector selectAll(DBConnection dbcon,Object toReturn, String tabName, String[] attribut,String[] search, String coor)throws Exception{
		boolean check = true;
		if(dbcon==null){
			check = false;
			dbcon = new DBConnection();
		}
		Statement stat = dbcon.getStat();
		String complement = attribut[0] + search[0];
		for(int k=1;k<attribut.length && attribut.length>1;k++){
			complement = complement + coor + attribut[k] + search[k];
		}
		String queryToSend = "SELECT * FROM " + tabName + " WHERE "  + complement;
		System.out.println("Query " + queryToSend);
		ResultSet results = stat.executeQuery(queryToSend);
		
		int nbRow = countLigne(results);
		
		Vector responses = new Vector();
		
		Class cl = toReturn.getClass();
		Field[] fields = cl.getDeclaredFields();
		int nbCol = fields.length;
		String[] param = new String[nbCol];
		String[] para = new String[nbCol];
		Method[] setters = new Method[nbCol];
		Class[] paramTypes = new Class[nbCol];
		Object[] values = new Object[nbRow];
		Object tempVal = null;
		
		Class[] clas = new Class[1];
		Vector tempResult = new Vector();
		int n = 0;
		
		while(results.next()){
			for(int i=0;i<fields.length;i++){
				para[i] = fields[i].getName();
				param[i] = "set".concat(firstMaj(fields[i].getName()));
				paramTypes[i] = fields[i].getType();
				clas[0] = paramTypes[i];
				
				String typ = paramTypes[i].getSimpleName();
				if((typ.compareToIgnoreCase("String"))==0){
					tempResult.addElement(results.getString(para[i]));
				}
				else if((typ.compareToIgnoreCase("Int"))==0){
					tempResult.addElement(results.getInt(para[i]));
				}
				else if((typ.compareToIgnoreCase("Timestamp"))==0){
					tempResult.addElement(results.getTimestamp(para[i]));
				}
				else if((typ.compareToIgnoreCase("Date"))==0){
					tempResult.addElement(results.getDate(para[i]));
				}
				else{
					tempResult.addElement(results.getDouble(para[i]));
				}
				setters[i] = cl.getMethod(param[i],clas);
			}
		}
		for(int i=0;i<nbRow;i++){
			tempVal = (Class.forName(toReturn.getClass().getName())).newInstance();
			for(int j=0;j<setters.length;j++){
				Object tempArg = tempResult.get(n);
				setters[j].invoke(tempVal,tempArg);
				n++;
			}
			responses.addElement(tempVal);
		}
		results.close();
		if(!check){
			dbcon.delConnect();
		}
	return responses;
	}
	
///Ma fonction insert partout
	public boolean insert(DBConnection con,String tabName,Object toInsert)throws Exception{
		boolean check = true;
		if(con==null){
			con = new DBConnection();
			check = false;
		}
		boolean result = false;
		String objName = toInsert.getClass().getSimpleName();
		Field[] champs = toInsert.getClass().getDeclaredFields();
		String[] att = new String[champs.length];
		Method[] functions = new Method[champs.length];
		Class[] clas = new Class[0];
		Object[] arg = new Object[0];
		Object[] valeurs = new Object[functions.length];
		
		String donnees = new String();
		
		att[0] = champs[0].getName();
		att[0] = "get".concat(firstMaj(att[0]));
		functions[0] = toInsert.getClass().getMethod(att[0],clas);
		valeurs[0] = functions[0].invoke(toInsert,arg);
		if(valeurs[0] instanceof String){
			donnees = "'"+valeurs[0]+"'";
		}
		else if(valeurs[0] instanceof Timestamp){
			donnees = "timestamp '"+valeurs[0]+"'";
		}
		else if(valeurs[0] instanceof Date){
			donnees = "to_date '"+valeurs[0]+"'";
		}
		else{
			donnees = String.valueOf(valeurs[0]);
		}
		for(int i=1;i<champs.length;i++){
			att[i] = champs[i].getName();
			att[i] = "get".concat(firstMaj(att[i]));
			functions[i] = toInsert.getClass().getMethod(att[i],clas);
			valeurs[i] = functions[i].invoke(toInsert,arg);
			if(valeurs[i] instanceof String){
				valeurs[i] = "'"+valeurs[i]+"'";
			}
		if(valeurs[i] instanceof Date){
				valeurs[i] = "'"+valeurs[i]+"'";
			}
			if(valeurs[i] instanceof Timestamp){
				valeurs[i] = "timestamp '"+valeurs[i]+"'";
			}
			donnees = donnees + "," + valeurs[i];
		}
		if(objName.compareToIgnoreCase(tabName)==0){
			String query = "INSERT INTO " + tabName + " VALUES (" + donnees + ")"; 
			result = true;
			con.getStat().execute(query);
		}
		if(!check){
			con.delConnect();
		}
	return result;
	}
	
///Fonction update
	public void Update(DBConnection dbcon, String tabName, String colName, String value, String[] attribut, String[] search)throws Exception{ 
		boolean check = true;
		if(dbcon==null){
			check = false;
			dbcon = new DBConnection();
		}
		Statement stat = dbcon.getStat();
		String complement = attribut[0] + search[0];
		for(int k=1;k<attribut.length && attribut.length>1;k++){
			complement = complement + " AND " + attribut[k] + search[k];
		}
		String query = "UPDATE " + tabName + " SET " + colName + "='" + value + "' WHERE " + complement;
		stat.execute(query);
		System.out.println(query);
		stat.execute("commit");
		if(!check){
			dbcon.delConnect();
		}
	}
	public void UpdateNumber(DBConnection dbcon, String tabName, String colName, String value, String[] attribut, String[] search)throws Exception{ 
		boolean check = true;
		if(dbcon==null){
			check = false;
			dbcon = new DBConnection();
		}
		Statement stat = dbcon.getStat();
		String complement = attribut[0] + search[0];
		for(int k=1;k<attribut.length && attribut.length>1;k++){
			complement = complement + " AND " + attribut[k] + search[k];
		}
		String query = "UPDATE " + tabName + " SET " + colName + "=" + value + " WHERE " + complement;
		stat.execute(query);
		System.out.println(query);
		stat.execute("commit");
		if(!check){
			dbcon.delConnect();
		}
	}
	
///Fonction filtre
	public Object[] filtre(Object[] list, String attribut, Object value)throws Exception{
		String maj = firstMaj(attribut);
		Object[] result = new Object[0];
		Object[] objet = new Object[0];
		Class[] clas = new Class[0];
		Class classe = null;
		if(list.length>0){
			classe = list[0].getClass();
		}
		int nb = 0, indice = 0;
		String nomFonction = "get".concat(maj);
		Method fonction = classe.getMethod(nomFonction,clas);
		
		for(int i=0;i<list.length;i++){
			Object temp = fonction.invoke(list[i],objet);
				if(temp.equals(value)){
					nb++;
				}
		}
		result = new Object[nb];
		for(int i=0;i<list.length;i++){
			Object temp = fonction.invoke(list[i],objet);
				if(temp.equals(value)){
					result[indice] = list[i];
					indice++;
				}
		}
	return result;
	}
	public Object[] filtreOpt(Object[] list, String attribut, Object value)throws Exception{
		Vector liste = new Vector();
		Vector temp = new Vector();
		String maj = firstMaj(attribut);
		Object[] result = new Object[0];
		Object[] objet = new Object[0];
		Class classe = null;
		Class[] clas = new Class[0];
		if(list.length>0){
			classe = list[0].getClass();
		}
		int nb = 0, indice = 0;
		String nomFonction = "get".concat(maj);
		Method fonction = classe.getMethod(nomFonction,clas);
		
		for(int i=0;i<list.length;i++){
			liste.addElement(list[i]);
		}
		for(int j=0;j<liste.size();j++){
			Object tempValue = fonction.invoke(liste.get(j),objet);
				if(tempValue.equals(value)){
					temp.addElement(liste.get(j));
					liste.removeElement(liste.get(j));
				}
		}
		Object[] results = temp.toArray();
	return results;
	}
	
///Get HtmlText
	public String getHtml(Object obj)throws Exception{
		String value = new String();
		String tr = "<tr>";
		String trClose = "</tr>";
		String td = "<td>";
		String tdClose = "</td>";
		Class cl = obj.getClass();
		Field[] att = cl.getDeclaredFields();
		String[] attName = new String[att.length];
		String[] methName = new String[att.length];
		Method[] methods = new Method[att.length];
		Class[] classArg = new Class[0];
		Object[] objArg = new Object[0];
		value += tr;
		for(int i=0;i<att.length;i++){
			attName[i] = att[i].getName();
			methName[i] = "get"+firstMaj(attName[i]);
			methods[i] = cl.getMethod(methName[i],classArg);
		}
		for(int i=0;i<att.length;i++){
			value += td + methods[i].invoke(obj,objArg) + tdClose;
		}
		value += trClose;
	return value;
	}
	
///Formulaire d'insertion d'objet
	public Formulaire createForm(Object toCreate){
		Class cl = toCreate.getClass();
		Field[] attribut = cl.getDeclaredFields();
		String[] noms = new String[attribut.length];
		Champs[] result = new Champs[attribut.length];
		for(int i=0;i<result.length;i++){
			noms[i] = attribut[i].getName();
			result[i] = new Champs(noms[i], noms[i]);
		}
		Formulaire f = new Formulaire(result);
		f.setButtonhtml("Valider", cl.getName());
	return f;
	}
	public String[] getAttName(String objName)throws Exception{
		Class objClass = Class.forName(objName);
		Field[] fields = objClass.getDeclaredFields();
		String[] result = new String[fields.length];
		for(int i=0;i<result.length;i++){
			result[i] = fields[i].getName();
		}
	return result;
	}
	public String getTable(String cl){
		String dot = "\\.";
		String[] temp = cl.split(dot);
		int nb = temp.length;
	return temp[nb-1];
	}
	public void treatFormulaire(String objValide, String tabName, String[] value)throws Exception{
		DBConnection con = new DBConnection();
		Class objClass = Class.forName(objValide);
		Object obj = objClass.newInstance();
		Field[] fields = objClass.getDeclaredFields();
		String[] att = getAttName(objValide);
		int nb = att.length;
		String[] methNames = new String[nb];
		Class[] classArg = new Class[nb];
		Method[] functions = new Method[nb];
		Object[] objArg = new Object[nb];
		for(int i=0;i<nb;i++){
			att[i] = firstMaj(att[i]);
			methNames[i] = "set".concat(att[i]);
			classArg[i] = fields[i].getType();
			objArg[i] = value[i];
			// objArg[i] = classArg[i].cast(value[i]);
			if(classArg[i].getSimpleName().compareToIgnoreCase("double") == 0){
				objArg[i] = Double.parseDouble(value[i]);
			}
			else if(classArg[i].getSimpleName().compareToIgnoreCase("int") == 0){
				objArg[i] = Integer.parseInt(value[i]);
			}
			else if(classArg[i].getSimpleName().compareToIgnoreCase("Timestamp") == 0){
				objArg[i] = Timestamp.valueOf(value[i]);
			}
			else if(classArg[i].getSimpleName().compareToIgnoreCase("Date") == 0){
				objArg[i] = Date.valueOf(value[i]);
			}
			functions[i] = objClass.getMethod(methNames[i],classArg[i]); 
		}
		for(int i=0;i<nb;i++){
			functions[i].invoke(obj,objArg[i]);
		}
		insert(con, tabName, obj);
	}
		
///Conversion String en Date/Timestamp
	public String[] getOccurenceMatch(String str, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		Vector<String> liste = new Vector<String>();
		while(m.find()) {
			liste.add(m.group(0));
		}
		String[] listefin = new String[liste.size()];
		for(int i=0; i < listefin.length; i++) {
			listefin[i] = liste.get(i);
		}
		return listefin;
	}	
	public Date strToDate(String date) throws Exception {
		String dateMotif = "([0-9]{2}|[0-9]{4})[-\\/, .][0-9]{2}[-\\/, .]([0-9]{4}|[0-9]{2})";
		String datePart = "";
		String[] tmpDate = getOccurenceMatch(date, dateMotif);
		if(tmpDate.length == 0) {
			throw new Exception("Date introuvable");
		} else {
			datePart = tmpDate[0];
		}
		
		String[] res = datePart.split("[-\\.,_/ ]");
		
		Date u = null; //resultat
		try {
			String tmp = joinString(res, "-");
			u = Date.valueOf(tmp);
		} catch(Exception e) {
				try {
					String tmp2 = res[0];
					res[0] = res[2];
					res[2] = tmp2;
					String tmp = joinString(res, "-");
					u = Date.valueOf(tmp);
				} catch(Exception e2) {
					throw new Exception("Date invalide essayer avec DD MM YYYY ou YYYY MM DD.");
				}
		}
		if(u == null) {
			throw new Exception("Date invalide  essayer DD-MM-YYYY ou YYYY-MM-DD.");
		}
	return u;
	}
	public Timestamp strToTimestamp(String date) throws Exception {
		String dateMotif = "([0-9]{2}|[0-9]{4})[-\\/, .][0-9]{2}[-\\/, .]([0-9]{4}|[0-9]{2})";
		String heureMotif = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
		String datePart = "";
		String hourPart = " 00:00:00";
		String[] tmpDate = getOccurenceMatch(date, dateMotif);
		String[] tmpHour = getOccurenceMatch(date, heureMotif);
		if(tmpDate.length == 0) {
			throw new Exception("Date introuvable");
		} else {
			datePart = tmpDate[0];
		}
		
		if(tmpHour.length == 1) {
			hourPart = " "+ tmpHour[0];
		}
		String[] res = datePart.split("[-\\.,_/ ]");
		
		Timestamp u = null; //resultat
		try {
			String tmp = joinString(res, "-");
			u = Timestamp.valueOf(tmp + hourPart);
		} catch(Exception e) {
				try {
					String tmp2 = res[0];
					res[0] = res[2];
					res[2] = tmp2;
					String tmp = joinString(res, "-");
					u = Timestamp.valueOf(tmp + hourPart);
				} catch(Exception e2) {
					throw new Exception("Date invalide essayer avec DD MM YYYY ou YYYY MM DD. On peut rajouter HH:MI:SS");
				}
		}
		if(u == null) {
			throw new Exception("Date invalide  essayer DDMMYYYY ou YYYYMMDD. On peut rajouter HH:MI:SS");
		}
	return u;
	}
	
		
///Fonction update
	Vector<Field> getAttributs(Object o)throws Exception{
		Class appel = o.getClass();
		Field[] att = appel.getDeclaredFields();
		int nb = att.length;
		String[] nameMethods = new String[nb];
		Method[] methods = new Method[nb];
		Object[] args = new Object[0];
		Class[] clArgs = new Class[0];
		
		Vector<Field> temp = new Vector();
		for(int i=0;i<nb;i++){
			nameMethods[i] = "get"+firstMaj(att[i].getName());
			methods[i] = appel.getMethod(nameMethods[i], clArgs);
			if(String.valueOf(methods[i].invoke(o, args)).compareToIgnoreCase("")!=0){
				temp.addElement(att[i]);
				System.out.println("ok");
			}
		}
	return temp;
	}
	Vector<Field> getAttributsNuls(Object o)throws Exception{
		Class appel = o.getClass();
		Field[] att = appel.getDeclaredFields();
		int nb = att.length;
		String[] nameMethods = new String[nb];
		Method[] methods = new Method[nb];
		Object[] args = new Object[0];
		Class[] clArgs = new Class[0];
		
		Vector<Field> temp = new Vector();
		for(int i=0;i<nb;i++){
			nameMethods[i] = "get"+firstMaj(att[i].getName());
			methods[i] = appel.getMethod(nameMethods[i], clArgs);
			if(String.valueOf(methods[i].invoke(o, args)).compareToIgnoreCase("")==0){
				temp.addElement(att[i]);
				System.out.println("ok");
			}
		}
	return temp;
	}
	public void update(DBConnection db, Object o, String tableName, String[] att, String[] search)throws Exception{
		boolean check = true;
		if(db==null){
			check = false;
			db = new DBConnection();
		}
		try{
			Class cl = o.getClass();
			Object[] arg = new Object[0];
			Field[] nonNuls = getAttributs(o).toArray(new Field[0]);
			int n = nonNuls.length;
			Method[] fonc = new Method[n];
			String[] toChange = new String[n];
			String[] valeurs = new String[n];
			Class[] paramTypes = new Class[n];
			Class[] args = new Class[0];
			for(int i=0;i<n;i++){
				paramTypes[i] = nonNuls[i].getType();
				toChange[i] = nonNuls[i].getName();
				fonc[i] = cl.getMethod("get"+firstMaj(nonNuls[i].getName()), args);
				valeurs[i] = String.valueOf(fonc[i].invoke(o, arg));
			}
			
			String entete = new String();
			String query = new String();
			String complement = new String();
			if(n>0){
				entete += toChange[0] + "=";
				if(paramTypes[0].getSimpleName().compareToIgnoreCase("String")==0){
					entete += "'"+valeurs[0]+"'";
				}
				for(int i=1;i<n;i++){
					entete += "," + toChange[i] + "=";
					if(paramTypes[i].getSimpleName().compareToIgnoreCase("String")==0){
						entete += "'"+valeurs[i]+"'";
					}
				}
				
				complement += att[0] + search[0];
				for(int i=1;i<att.length;i++){
					complement += " AND " + att[i] + search[i];
				}
				query += "UPDATE " + tableName + " SET " + entete + " WHERE " + complement;
				System.out.println("update " + query);
				db.getStat().execute(query);
			}else{
				throw new Exception("Pas de donnees a modifier?????????");
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(!check){
				db.delConnect();
			}
		}
	}
	
}