import java.util.Scanner;

public class Dictionnaire {

    private int nb_mots;
    private Mot_dict[] Dict;
    private String Nom;

    public Dictionnaire(int n, String nom) {
        this.nb_mots=0;
        this.Dict=new Mot_dict[n];
        this.Nom=nom;
    }

    public void Ajouter_Mot(Mot_dict mot) {
        if (nb_mots<Dict.length) {
            Dict[nb_mots++]=mot;
            Trier();
        } else {
            System.out.println("Le dictionnaire est plei.0n.");
        }
    }

    public void Trier() {
        for (int i=1; i<nb_mots; i++) {
            Mot_dict ch=Dict[i];
            int j=i-1;

            while (j>=0 && Dict[j].getMot().compareTo(ch.getMot())>0) {
                Dict[j+1]=Dict[j];
                j=j-1;
            }
            Dict[j+1]=ch;
        }
    }

    public void Supprimer_Mot(Mot_dict mot) {
        boolean ok=false;
        Mot_dict[] ch=new Mot_dict[Dict.length];
        int k=0;

        for (int i=0;i<nb_mots;i++) {
            if (!Dict[i].getMot().equals(mot.getMot())) {
                ch[k++]=Dict[i];
            } else {
                ok=true;
            }
        }

        if (ok) {
        	Dict=ch ;
			nb_mots--;
        } else {
            System.out.println("Mot non trouvé.");
        }
    }

    public String Recherche_dicho(String m) {
        int g=0;
        int d=nb_mots-1;

        while (g<=d) {
            int middle =(g+d)/2;
            String motM=Dict[middle].getMot();
            int comp=motM.compareTo(m);

            if (comp==0) {
                return Dict[middle].getDéfinition();
            } else if (comp<0) {
                g=middle+1;
            } else {
                d=middle-1;
            }
        }
        return "Mot non trouvé.";
    }

    public void Lister_dictionnaire() {
        if (nb_mots==0) {
            System.out.println("Le dictionnaire est vide.");
        } else {
            System.out.println("Contenu du dictionnaire :");
            for (int i=0;i<nb_mots;i++) {
                System.out.println(Dict[i].getMot()+":"+Dict[i].getDéfinition());
            }
        }
    }

    public int Nombre_synonyme(Mot_dict mot) {
        int nb=0;
        for (int i=0;i<nb_mots;i++) {
            if (mot.synonyme(Dict[i].getMot())) {
                nb++;
            }
        }
        return nb;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Dictionnaire dictionnaire=new Dictionnaire(10,"Mon Dictionnaire");
        int choice;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1.Ajouter un mot");
            System.out.println("2.Lister le dictionnaire");
            System.out.println("3.Rechercher un mot");
            System.out.println("4.Compter les synonymes");
            System.out.println("5.Supprimer un mot");
            System.out.println("6.Quitter");
            System.out.print("Choisissez une option:");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Entrez le mot a ajouter:");
                    String motAjout=scanner.nextLine();
                    System.out.print("Entrez la définition:");
                    String definition=scanner.nextLine();
                    Mot_dict m =new Mot_dict(motAjout,definition);
                    dictionnaire.Ajouter_Mot(m);
                    System.out.println("Mot ajoute.");
                    break;

                case 2:
                    dictionnaire.Lister_dictionnaire();
                    break;

                case 3:
                    System.out.print("Entrez le mot a rechercher:");
                    String motRecherche=scanner.nextLine();
                    System.out.println(dictionnaire.Recherche_dicho(motRecherche));
                    break;

                case 4:
                    System.out.print("Entrez le mot pour compter les synonymes:");
                    String motSynonyme=scanner.nextLine();
                    Mot_dict motToCheck=new Mot_dict(motSynonyme, "");
                    System.out.println("Nombre de synonymes : "+ dictionnaire.Nombre_synonyme(motToCheck));
                    break;

                case 5:
                    System.out.print("Entrez le mot a supprimer:");
                    String motSuppression=scanner.nextLine();
                    Mot_dict s=new Mot_dict(motSuppression,"");
                    dictionnaire.Supprimer_Mot(s);
                    break;

                case 6:
                    System.out.println("goodbye");
                    break;

                default:
                    System.out.println("agin!!!!!!!!!!!!!!");
            }
        } while (choice != 6);

        scanner.close();
    }
}
