package html;
public class Champs{
	boolean visibility;
	String defaultValue;
	String libelle;
	String name;
	String htmlText;
	
	public boolean getVisibility(){
		return this.visibility;
	}
	public String getDefaultvalue(){
		return this.defaultValue;
	}
	public String getLibelle(){
		return this.libelle;
	}
	public String getName(){
		return this.name;
	}
	public String getHtmltext(){
		return this.htmlText;
	}
	public void setHtmltext(String html){
		this.htmlText = html;
	}
	public void setHtmltext(){
		String result = new String();
		if(this.getVisibility()){
			result = "<div class='form-inline'>" + 
			"<label>"+ this.getLibelle() +"</label>" + 
			"<input type='text' name='" + this.getName() + "' value='" + this.getDefaultvalue() + "'>" + 
			"</div>";
		}
		else{
			result = "<input type='hidden' name='" + this.getName() + "' value='" + this.getDefaultvalue() + "'>";
		}
	this.htmlText = result;
	}
	public void setVisibility(boolean vis){
		this.visibility = vis;
		setHtmltext();
	}
	public void setDefaultvalue(String val){
		this.defaultValue = val;
		setHtmltext();
	}
	public void setLibelle(String label){
		this.libelle = label;
		setHtmltext();
	}
	void setName(String nom){
		this.name = nom;
		setHtmltext();
	}
	
	public Champs(){}
	public Champs(String label,String nom){
		setLibelle(label);
		setName(nom);
		// System.out.println(this.getVisibility());
		setVisibility(true);
		setDefaultvalue("-");
		setHtmltext();
	}
}