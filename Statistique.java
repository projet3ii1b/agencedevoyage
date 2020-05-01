package agencedevoyage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
//import java.lang.* ;
//import java.lang.Object;
import java.lang.String;
import java.util.Collections;
//import java.lang.String;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Statistique {
	Touslesvoyages tv ; 
	Année an;
	protected File TouslesVoyages;
	public Statistique() {
		TouslesVoyages=new File("C:\\AgencedeVoyage\\Tousvoyages\\");
		an = new Année();
		tv = new Touslesvoyages();
	}
	public void nombreVoyagesTotales() throws IOException
	{
		int Somme=0;
		String[] tab=TouslesVoyages.list();
		for(int i=0;i<tab.length;i++)
		{
			String tab2=tab[i].substring(0,tab[i].indexOf("."));
			tv.nombredevoyage(tab2);
			Somme+=tv.nombredevoyage(tab2);
		}
		System.out.println("le nombre de Voyages Totales de l'agence est "+ Somme);
		
	}
	public void nombreClients()
	{
		System.out.println("le nombre de voyageurs distincts est "+TouslesVoyages.list().length);	
	}
	public double tousnombredevoyage() throws IOException
	{
		File repertoire = new File("C:\\AgencedeVoyage\\Tousvoyages\\");
        String liste[] = repertoire.list();      
        if (liste != null) {  
        	int i=0;
        	double s=(double) 0;
            while (i<liste.length) {
            	if (liste[i].equals("pays"))
            	{i++;}
            	else
            	{
                String ch ="." ;
                int j = liste[i].indexOf ( ch ) ;
                String str= liste[i].substring (0,j) ;
                s=s+tv.nombredevoyage(str);
                i++;}
            }
            return (s);
        }
        
		return 0;
		
    }
	public void nombremoyenneannuels() throws IOException{
	
		double X=tousnombredevoyage();
		HashMap<String,Double > Moy =new HashMap<>() ;
    	File initial = new File ("C:\\AgencedeVoyage\\Année");
    	String liste[] = initial.list();
        int i=0;
        while (i<liste.length)
        { 
        	File init = new File ("C:\\AgencedeVoyage\\Année\\"+liste[i]);
        	String l1[] = init.list();
            int j=0;
            while (j<l1.length)
            {
            	if ( l1[j].equals("saison1.txt") || l1[j].equals("saison2.txt") || l1[j].equals("saison3.txt")|| l1[j].equals("saison4.txt") )
             	
            	{double nbmoy= ( an.nombredevoyages(liste[i]+"\\"+l1[j]) / X  ) ;
        	     Moy.put(liste[i], nbmoy );
        	     j++; 
            	} else 
            		j++;
            }
            i++;
        }
        System.out.println("le nombre moyen des voyages pour chaque année est comme suit"+Moy);
	}
	public void nombrevoyagesaisonAD() throws IOException
	{
		System.out.println("Donner l'année pour laquelle vous voulez savoir le nombre de voyages par saison : ");
		Scanner sc=new Scanner(System.in);
		String ch=sc.nextLine();
		File f=new File("C:\\AgencedeVoyage\\Année\\"+ch);
		String[] tab=f.list();
		for(int i=0;i<tab.length;i++)
		{
			if (tab[i].equals("saison1.txt")) 
			{
				int x=nombreVoyagesaisonSP(ch,tab[i]);	
				System.out.println("le nombre de Voyages effectués durant l'année "+ch+" pour le saison1 est "+x);
			}
			else if (tab[i].equals("saison2.txt")) 
			{
				int x=nombreVoyagesaisonSP(ch,tab[i]);	
				System.out.println("le nombre de Voyages effectués durant l'année "+ch+" pour le saison2 est "+x);
			}
			else if (tab[i].equals("saison3.txt")) 
			{
				int x=nombreVoyagesaisonSP(ch,tab[i]);	
				System.out.println("le nombre de Voyages effectués durant l'année "+ch+" pour le saison3 est "+x);
			}
			else if (tab[i].equals("saison4.txt")) 
			{
				int x=nombreVoyagesaisonSP(ch,tab[i]);	
				System.out.println("le nombre de Voyages effectués durant l'année "+ch+" pour le saison4 est "+x);
			}	
		}
		sc.close();
		
	}
	public int nombreVoyagesaisonSP(String annee,String saison) throws IOException
	{
		File fichier=new File("C:\\AgencedeVoyage\\Année\\"+annee+"\\"+saison);
		FileReader fr = new FileReader(fichier);
    	LineNumberReader lnr = new LineNumberReader(fr);
        int linenumber = 0;
        while (lnr.readLine() != null){
            linenumber++;
            }
        lnr.close(); 
        return( linenumber );
		
	}
	public int nombreVoyagemoiSP(String annee,String mois) throws IOException
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
	public HashMap nombredevoyageparmois() throws IOException
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
				compt1+=nombreVoyagemoiSP(tab[i],"01.txt");
			}
			else if (tab2[j].equals("02.txt")||tab2[j].equals("2.txt"))
			{
				compt2+=nombreVoyagemoiSP(tab[i],"02.txt");
			}
			else if (tab2[j].equals("03.txt")||tab2[j].equals("3.txt"))
			{
				compt3+=nombreVoyagemoiSP(tab[i],"03.txt");
			}
			else if (tab2[j].equals("04.txt")||tab2[j].equals("4.txt"))
			{
				compt4+=nombreVoyagemoiSP(tab[i],"05.txt");
			}
			else if (tab2[j].equals("05.txt")||tab2[j].equals("5.txt"))
			{
				compt5+=nombreVoyagemoiSP(tab[i],"05.txt");
			}
			else if (tab2[j].equals("06.txt")||tab2[j].equals("6.txt"))
			{
				compt6+=nombreVoyagemoiSP(tab[i],"06.txt");
			}
			else if (tab2[j].equals("07.txt")||tab2[j].equals("7.txt"))
			{
				compt7+=nombreVoyagemoiSP(tab[i],"07.txt");
			}
			else if (tab2[j].equals("08.txt")||tab2[j].equals("8.txt"))
			{
				compt8+=nombreVoyagemoiSP(tab[i],"08.txt");
			}
			else if (tab2[j].equals("09.txt")||tab2[j].equals("9.txt"))
			{
				compt9+=nombreVoyagemoiSP(tab[i],"09.txt");
			}
			else if (tab2[j].equals("10.txt"))
			{
				compt10+=nombreVoyagemoiSP(tab[i],"10.txt");
			}
			else if (tab2[j].equals("11.txt"))
			{
				compt11+=nombreVoyagemoiSP(tab[i],"11.txt");
			}
			else if (tab2[j].equals("12.txt"))
			{
				compt12+=nombreVoyagemoiSP(tab[i],"12.txt");
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
	public void retournerlemoisleplusdemandee(HashMap<String, Integer> h)
	{
		int maxValueInMap=(int) (Collections.max(h.values()));
		System.out.println("le(s) mois le(s) plus demandé(s) est/sont : ");
		for (Entry<String, Integer> entry : h.entrySet()) 
		{
			
        	if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey());     // Print the key with max value
            }
        }	
	}
	public void retournerlemoislemoinsdemandee(HashMap<String, Integer> h)
	{
		int minValueInMap=(int) (Collections.min(h.values()));
		System.out.println("le(s) mois le(s) moins demandé(s) est/sont : ");
		for (Entry<String, Integer> entry : h.entrySet()) 
		{
			
        	if (entry.getValue()==minValueInMap) {
                System.out.println(entry.getKey());     // Print the key with max value
            }
        }
		
		
	}
	
}
	


