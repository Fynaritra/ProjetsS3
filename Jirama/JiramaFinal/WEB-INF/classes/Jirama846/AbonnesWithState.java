package forfait;

import java.util.Vector;
public class AbonnesWithState{
	Vector<Abonnes> concerne = new Vector();
	int etat;///1:normal, 11:un abonnement en cours au moment du pv2, 21:abonnement en cours entre pv1 et pv2, 31:abonnement en cours au moment du pv1
	
	public Abonnes getConcerne(){
		Abonnes p = new Abonnes();
		if(concerne.size()>0){
			p = (this.concerne.toArray(new Abonnes[0]))[0];
		}
	return p;
	}
	public int getEtat(){
		return this.etat;
	}
	public void setConcerne(Abonnes ab){
		this.concerne.addElement(ab);
	}
	public void setEtat(int state){
		this.etat = state;
	}
}