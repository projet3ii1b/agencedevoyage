package agencedevoyage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.lang.String;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Statistique {
	protected Touslesvoyages tv ; 
	protected Année an;
	protected File TouslesVoyages;
	public Statistique() throws IOException  //constructeur du classe Statistique
	{
		TouslesVoyages=new File("C:\\AgencedeVoyage\\Tousvoyages\\");
		an = new Année();//instanciation de l'attribut an qui est de type année 
		tv = new Touslesvoyages();//instanciation de l'attribut tv de type Touslesvoyages pour avoir accées aux methodes de ce classe 
	}
	public void nombreVoyagesTotales() throws IOException//permettre l'affichage du nombre de voyages Totales effectués au sein de l'agence 
	{
		int Somme=0;
		String[] tab=TouslesVoyages.list();//une liste contenant les numero des passeport avec l'extension (.txt)
		for(int i=0;i<tab.length;i++)
		{
			String nump=tab[i].substring(0,tab[i].indexOf("."));//extraire le numero de passeport de chaque voyageur en eliminant l'extension(.txt)
			tv.nombredevoyage(nump);
			Somme+=tv.nombredevoyage(nump);
		}
		System.out.println("le nombre de Voyages Totales de l'agence est "+ Somme);
		
	}
	public int nombreVoyages() throws IOException//retourne le nombre de voyages Totales effectués au sein de l'agence 
	{
		int Somme=0;
		String[] tab=TouslesVoyages.list();//une liste contenant les numero des passeport avec l'extension (.txt)
		for(int i=0;i<tab.length;i++)
		{
			String nump=tab[i].substring(0,tab[i].indexOf("."));//extraire le numero de passeport de chaque voyageur en eliminant l'extension(.txt)
			tv.nombredevoyage(nump);
			Somme+=tv.nombredevoyage(nump);
		}
		return Somme;
		
	}
	public void nombreClients()//afficher le nombre de clients de l'agence 
	{
		System.out.println("le nombre de voyageurs distincts est "+TouslesVoyages.list().length);	
	}
	
	public void nombremoyenneannuels() throws IOException//permet le calcul du nombre moyen des voyages annuels
	{
		double X=(double)nombreVoyages();//avoir le nombre de voyages de l'agence 
		HashMap<String,Double > nbMoy =new HashMap<>() ;//HashMap qui va contenir chaque année comme clé et le nombre moyen des voyages qui lui est associee comme valeur 
    	File DossierAnnées = new File ("C:\\AgencedeVoyage\\Année");
    	String liste[] = DossierAnnées.list();//creer une liste contenant les année dans les quels il ya des reservation dans l'agence 
        int i=0;
        while (i<liste.length)
        { 
        	File AnneeSP = new File ("C:\\AgencedeVoyage\\Année\\"+liste[i]);
        	String l1[] = AnneeSP.list();//avoir la liste des saisons et mois dans lesquels il y a des reservations pour une année specifique 
            double nbmoy=0.0;
            for (int j=0;j<l1.length;j++)
            {
            	if ( l1[j].equals("saison1.txt") || l1[j].equals("saison2.txt") || l1[j].equals("saison3.txt")|| l1[j].equals("saison4.txt") )
                 	
            	{
            		nbmoy+=an.nombredevoyages(liste[i]+"\\"+l1[j]);//ajoute le nombre de voyages pour chaque saison 
            	}
            	
            }
            nbMoy.put(liste[i], (nbmoy/X) );//ajouter au HashMap le nombre de voyage moyen pour chaque année 
            i++;
           }
        System.out.println("le nombre moyen des voyages pour chaque année est comme suit"+nbMoy);
	}
	public int nombreVoyagesaisonSP(String annee,String saison) throws IOException//calcule le nombre de voyages pour une saison specifique d'une année 
	{
		File fichier=new File("C:\\AgencedeVoyage\\Année\\"+annee+"\\"+saison);
		FileReader fr = new FileReader(fichier);
    	LineNumberReader lnr = new LineNumberReader(fr);
        int linenumber = 0;
        while (lnr.readLine() != null)
        {
            linenumber++;
        }
        lnr.close(); 
        return( linenumber );
		
	}
	public void nombrevoyagesaisonAD() throws IOException//permet d'afficher le nombre de voyage pour tous les saisons d'une année donnée (saisie au clavier)
	{
		System.out.println("Donner l'année pour laquelle vous voulez savoir le nombre de voyages par saison : ");
		Scanner sc=new Scanner(System.in);
		String annee=sc.nextLine();
		File dossierAnneeSP=new File("C:\\AgencedeVoyage\\Année\\"+annee);//acceder au dossier d'une Annee Specifique 
		String[] tab=dossierAnneeSP.list();//tableau contenant 
		for(int i=0;i<tab.length;i++)
		{
			if (tab[i].equals("saison1.txt")) 
			{
				int x=nombreVoyagesaisonSP(annee,tab[i]);//appel a la fonction permettant de calculer le nombre de voyage pour une saison specifique 	
				System.out.println("le nombre de Voyages effectués durant l'année "+annee+" pour le saison1 est "+x);
			}
			else if (tab[i].equals("saison2.txt")) 
			{
				int x=nombreVoyagesaisonSP(annee,tab[i]);	
				System.out.println("le nombre de Voyages effectués durant l'année "+annee+" pour le saison2 est "+x);
			}
			else if (tab[i].equals("saison3.txt")) 
			{
				int x=nombreVoyagesaisonSP(annee,tab[i]);	
				System.out.println("le nombre de Voyages effectués durant l'année "+annee+" pour le saison3 est "+x);
			}
			else if (tab[i].equals("saison4.txt")) 
			{
				int x=nombreVoyagesaisonSP(annee,tab[i]);	
				System.out.println("le nombre de Voyages effectués durant l'année "+annee+" pour le saison4 est "+x);
			}	
		}
		sc.close();
		
	}
	public int nombreVoyagemoisSP(String annee,String mois) throws IOException//calcule le nombre de voyages pour un mois specifique d'une année
	{
		File fichier=new File("C:\\AgencedeVoyage\\Année\\"+annee+"\\"+mois);
		FileReader fr = new FileReader(fichier);
    	LineNumberReader lnr = new LineNumberReader(fr);
        int linenumber = 0;
        while (lnr.readLine() != null){
            linenumber++;
            }
        lnr.close(); 
        return( linenumber );
		
	}
	public HashMap<String,Integer> nombredevoyageparmois() throws IOException//creer une Hashmap contenant le nombre de voyage totale pour chaque mois de tous les années
	{
		File dossierPrincipal=new File("C:\\AgencedeVoyage\\Année\\");
		int compt1=0;
		int compt2=0;
		int compt3=0;
		int compt4=0;
		int compt5=0;
		int compt6=0;
		int compt7=0;
		int compt8=0;
		int compt9=0;
		int compt10=0;
		int compt11=0;
		int compt12=0;
		String[] tab=dossierPrincipal.list();
		for (int i=0;i<tab.length;i++)
		{
			File dossierAnneeSP=new File("C:\\AgencedeVoyage\\Année\\"+tab[i]);
			String[] tab2=dossierAnneeSP.list();
			for (int j=0;j<tab2.length;j++)
				{
			if (tab2[j].equals("01.txt")||tab2[j].equals("1.txt"))
			{	
				compt1+=nombreVoyagemoisSP(tab[i],"01.txt");
			}
			else if (tab2[j].equals("02.txt")||tab2[j].equals("2.txt"))
			{
				compt2+=nombreVoyagemoisSP(tab[i],"02.txt");
			}
			else if (tab2[j].equals("03.txt")||tab2[j].equals("3.txt"))
			{
				compt3+=nombreVoyagemoisSP(tab[i],"03.txt");
			}
			else if (tab2[j].equals("04.txt")||tab2[j].equals("4.txt"))
			{
				compt4+=nombreVoyagemoisSP(tab[i],"05.txt");
			}
			else if (tab2[j].equals("05.txt")||tab2[j].equals("5.txt"))
			{
				compt5+=nombreVoyagemoisSP(tab[i],"05.txt");
			}
			else if (tab2[j].equals("06.txt")||tab2[j].equals("6.txt"))
			{
				compt6+=nombreVoyagemoisSP(tab[i],"06.txt");
			}
			else if (tab2[j].equals("07.txt")||tab2[j].equals("7.txt"))
			{
				compt7+=nombreVoyagemoisSP(tab[i],"07.txt");
			}
			else if (tab2[j].equals("08.txt")||tab2[j].equals("8.txt"))
			{
				compt8+=nombreVoyagemoisSP(tab[i],"08.txt");
			}
			else if (tab2[j].equals("09.txt")||tab2[j].equals("9.txt"))
			{
				compt9+=nombreVoyagemoisSP(tab[i],"09.txt");
			}
			else if (tab2[j].equals("10.txt"))
			{
				compt10+=nombreVoyagemoisSP(tab[i],"10.txt");
			}
			else if (tab2[j].equals("11.txt"))
			{
				compt11+=nombreVoyagemoisSP(tab[i],"11.txt");
			}
			else if (tab2[j].equals("12.txt"))
			{
				compt12+=nombreVoyagemoisSP(tab[i],"12.txt");
			}
		}
		}
		HashMap<String,Integer > Moy =new HashMap<>() ;
		Moy.put("janvier", compt1);
		Moy.put("fevrier", compt2);
		Moy.put("mars", compt3);
		Moy.put("avril", compt4);
		Moy.put("mai", compt5);
		Moy.put("juin", compt6);
		Moy.put("juillet", compt7);
		Moy.put("aout", compt8);
		Moy.put("septembre", compt9);
		Moy.put("octobre", compt10);
		Moy.put("novembre", compt11);
		Moy.put("decembre", compt12);
		return Moy;
		
	}
	public void retournerlemoisleplusdemandee(HashMap<String, Integer> MapVoyageparMois)//determine le mois pendant lequel l'agence a le plus de voyages
	{
		int maxValueInMap=(int) (Collections.max(MapVoyageparMois.values()));
		System.out.println("le(s) mois le(s) plus demandé(s) est/sont : ");
		for (Entry<String, Integer> entry : MapVoyageparMois.entrySet()) 
		{
			
        	if (entry.getValue()==maxValueInMap) 
        	{
                System.out.println(entry.getKey());// affiche la clé ayant la valeur maximale 
            }
        }	
	}
	public void retournerlemoislemoinsdemandee(HashMap<String, Integer> MapVoyageparMois)//determine le mois pendant lequel l'agence a le plus de voyages
	{
		int minValueInMap=(int) (Collections.min(MapVoyageparMois.values()));
		System.out.println("le(s) mois le(s) moins demandé(s) est/sont : ");
		for (Entry<String, Integer> entry : MapVoyageparMois.entrySet()) 
		{
			
        	if (entry.getValue()==minValueInMap) {
                System.out.println(entry.getKey());// affiche la clé ayant la valeur maximale
            }
        }	
	}
}
	


