
public class Mot_dict {
	private String Mot;
	private String Définition ;
	public Mot_dict(String m , String d ) {
		Mot=m;
		Définition=d;
	}
	
	public String getMot() {
		return this.Mot;
	};
	public String getDéfinition () {
		return this.Définition ;
	}
	public void setDéfinition(String d) {
		this.Définition=d;
	}
	public void setMot(String m) {
		this.Mot=m;
	}
	public boolean synonyme (String a) {
        return this.Mot.equals(a);
    }
	}
	
	



