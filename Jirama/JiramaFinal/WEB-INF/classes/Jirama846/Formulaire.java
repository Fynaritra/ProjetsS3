package html;
public class Formulaire{
	Champs[] champ;
	String buttonHtml;
	
	public Champs[] getChamp(){
		return this.champ;
	}
	public String getButtonhtml(){
		return this.buttonHtml;
	}
	public void setButtonhtml(String name, String value){
		String result = "<p><button class='btn btn-success' name='" + name + "' value='" + value + "'" + ">" + name + "</button></p>";
		this.buttonHtml = result;
	}
	public Champs getChamp(int ind){
		return this.champ[ind];
	}
	public void setChamps(int indice, Champs newChamp){
		this.champ[indice] = newChamp;
	}
	
	public Formulaire(){}
	public Formulaire(Champs[] ch){
		this.champ = ch;
		for(int i=0;i<ch.length;i++){
			this.setChamps(i,ch[i]);
		}
	}
	
	public String show(){
		Champs[] zone = this.getChamp();
		String value = new String();
		// this.setButtonhtml(valider);
		for(int i=0;i<zone.length;i++){
			value = value.concat(zone[i].getHtmltext());
		}
		value = value.concat(this.getButtonhtml());
	return value;
	}
}