package projet3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.text.DateFormat;

public class Voyageur {
	protected DateFormat date;
	protected String nom;
	protected String prenom;
	protected String datedenaissance;
	protected String cin;
	protected String numpasseport;
	protected String depart;
	protected String destVille;
	protected String destPays;
	protected String datededepart;
	protected String datederetour ; 
	protected File donnees;
	protected File VoyagesFaites;
	protected String NomDestination;
	Scanner sc;
	public void SaisieDonnes()
	{
		sc = new Scanner(System.in);
		System.out.println("Donner votre nom");
		nom=sc.nextLine();
		System.out.println("Donner votre prenom");
		prenom=sc.nextLine();
		System.out.println("Donner votre date de naissance sous la forme (annee/mois/jour)");
		datedenaissance=sc.nextLine();
		System.out.println("Donner votre numero de CIN");
		cin=sc.nextLine();
			
	}
	public void AjouterDonnes() throws IOException {
		FileWriter FW=new FileWriter(donnees);
		BufferedWriter BW=new BufferedWriter(FW);
		String ch=nom+"---"+prenom+"---"+cin+"---"+datedenaissance+"\n";
		BW.write(ch);
		BW.close();
		FW.close();	
	}
	public void SaisieVoyagesFaites()
	{
		sc =new Scanner(System.in);
		System.out.println("Donner votre place de depart");
		depart=sc.nextLine();
		System.out.println("Donner votre destination : Pays");
		destPays=sc.nextLine();
		System.out.println("Donner votre destination : Ville");
		destVille=sc.nextLine();
		System.out.println("Donner la date de depart");
		datededepart=sc.nextLine();
		System.out.println("Donner la date de retour");
		datederetour=sc.nextLine();
		
	}
	public void AjouterUnVoyage() throws IOException 
	{
		FileWriter FW=new FileWriter(VoyagesFaites,true);
		BufferedWriter BW=new BufferedWriter(FW);
		String ch=depart+"---"+destPays+"---"+destVille+"---"+datededepart+"---"+datederetour+"\n";
		BW.write(ch);
		BW.close();
		FW.close();	
	}
	public String getNom()
	{
		return (nom);
	}
	public String getPrenom()
	{ 
		return ( prenom );
	}
	public String getNumpasseport()
	{
		return (numpasseport);
	}
	String getCin()
	{ 
		return ( cin );
	}
	String getDepart() 
	{ 
		return ( depart );
	}
	String getDateretour()
	{ 
		return ( datederetour );
	}
	String getDatedepart()
	{ 
		return ( datededepart );
	}
	 public String getDestPays()
	{ 
		return ( destPays );
	}
	 public String getDestVille()
		{ 
			return ( destVille );
		}
	 
	 public void creerNouvFichierDonnees(String s) throws IOException 
		{
		 donnees=new File("C:\\Agence\\TouslesVoyageurs\\"+s+"\\Donnees.txt");
			if (!donnees.exists())
			{
				donnees.createNewFile();
			}
		}
	 public void creerNouvFichierVoyagesFaites(String s) throws IOException 
		{
		 VoyagesFaites= new File("C:\\Agence\\TouslesVoyageurs\\"+s+"\\VoyagesFaites.txt");
		 if (!VoyagesFaites.exists())
			{
				VoyagesFaites.createNewFile();
			}
		}
	 public void setDonnes(String ch)
	 {
		 donnees=new File(ch);
	 }
	 public void creerNouvFichierVoyages(String s) throws IOException 
		{
			VoyagesFaites= new File("C:\\Agence\\TouslesVoyageurs\\"+s+"\\VoyagesFaites.txt");
			if (!VoyagesFaites.exists())
			{
				VoyagesFaites.createNewFile();
			}
		}
	 public void SetNom(String ch)
	 {
		 this.nom=ch;
	 }
	 public void SetPrenom(String ch)
	 {
		 this.prenom=ch;
	 }
	 public void SetDatedenaissance(String ch)
	 {
		 this.datedenaissance=ch;
	 }
	 public void SetNumpasseport(String ch)
	 {
		 this.numpasseport=ch;
	 }
	 
	public void setVoyagesFaites(String ch)
	{
		VoyagesFaites= new File(ch);
		
	}
	public String nomDestination()
	{
		NomDestination=depart+'-'+destPays+'-'+destVille+'-'+datededepart+'-'+datederetour;
		return NomDestination;
	}
	public int NbVoyages(String s) throws IOException
	{
		VoyagesFaites=new File("C:\\Agence\\TouslesVoyageurs\\"+s+"\\VoyagesFaites.txt");
		BufferedReader reader= new BufferedReader(new FileReader(VoyagesFaites));
		int compt=0;
		String line;
		while ((line= reader.readLine())!=null  )
		{ 
			compt++;
		}
		reader.close();
		return compt;
	}
	public int anneedenaissance()
	{
		String[] list=this.datedenaissance.split("/");
		int annee=Integer.parseInt(list[0]);
		return annee;
	}
	public void SupprimerUnVoyage(String nump) throws IOException
	{
		VoyagesFaites=new File("C:\\Agence\\TouslesVoyageurs\\"+nump+"\\VoyagesFaites.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(VoyagesFaites)));
        StringBuffer sb = new StringBuffer(); 
        String line;    
        int nbLinesRead = 0; 
        int lineNumber=NbVoyages(nump)-1;
        while ((line = reader.readLine()) != null) 
        {
            if (nbLinesRead != lineNumber) 
            {
                sb.append(line + "\n");
            }
            nbLinesRead++;
        }
        reader.close();
        VoyagesFaites.delete();
        creerNouvFichierVoyages(nump);
        BufferedWriter out = new BufferedWriter(new FileWriter(VoyagesFaites));
        out.write(sb.toString());
        out.close();
	}
	public void retarderDatedeDepartVSpecifique() throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println(" Donner le numero de passeport de voyageur ");
		String ch0=sc.nextLine();
		System.out.println(" Donner la date a retarder sous la forme (aaaa/mm/jj): ");
		String ch=sc.nextLine();
		System.out.println(" Donner la nouvelle date sous la forme (aaaa/mm/jj): ");
		String ch1=sc.nextLine();
		if (ContientDatedeDepart(ch0,ch)>0)
			{
				modifierVoyageDateDepart(ch0,ch,ch1);
			}
		sc.close();
		}
	public void retarderDatedeDepartTV() throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println(" Donner la date a retarder sous la forme (aaaa/mm/jj): ");
		String ch=sc.nextLine();
		System.out.println(" Donner la nouvelle date sous la forme (aaaa/mm/jj): ");
		String ch1=sc.nextLine();
		File DossierTouslesVoyages =new File("C:\\Agence\\TouslesVoyageurs\\");
		String[] listeVoyageurs=DossierTouslesVoyages.list();
		for (int i=0;i<listeVoyageurs.length;i++)
		{
			if (ContientDatedeDepart(listeVoyageurs[i],ch)>0)
			{
				modifierVoyageDateDepart(listeVoyageurs[i],ch,ch1);
			}
		}
		sc.close();
		}
	public void retarderDatederetourVSpecif() throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println(" Donner le numero de passeport de voyageur ");
		String ch0=sc.nextLine();
		System.out.println(" Donner la date a retarder sous la forme (aaaa/mm/jj): ");
		String ch=sc.nextLine();
		System.out.println(" Donner la nouvelle date sous la forme (aaaa/mm/jj): ");
		String ch1=sc.nextLine();
		if (ContientDatedeDepart(ch0,ch)>0)
		{
			modifierVoyageDateDepart(ch0,ch,ch1);
		}
		sc.close();
	}
	
	public void retarderDatederetourTV() throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println(" Donner la date a retarder sous la forme (aaaa/mm/jj): ");
		String ch=sc.nextLine();
		System.out.println(" Donner la nouvelle date sous la forme (aaaa/mm/jj): ");
		String ch1=sc.nextLine();
		File DossierTouslesVoyages =new File("C:\\Agence\\TouslesVoyageurs\\");
		String[] listeVoyageurs=DossierTouslesVoyages.list();
		for (int i=0;i<listeVoyageurs.length;i++)
		{
			if (ContientDatedeDepart(listeVoyageurs[i],ch)>0)
			{
				modifierVoyageDateDepart(listeVoyageurs[i],ch,ch1);
			}
		}
		sc.close();
		}
	
	public void modifierVoyageDateDepart(String nump,String ancDate,String nouvDate) throws IOException 
	{
		VoyagesFaites=new File("C:\\Agence\\TouslesVoyageurs\\"+nump+"\\VoyagesFaites.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(VoyagesFaites)));
		String line;
		String placedepart;
		String placedestination;
		String ladatederetour;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) 
        {
			if (!line.contains(ancDate))
			{
				sb.append(line+"\n");
				
			}
			else 
            {
				String[] tab=line.split("---");
				placedepart=tab[0];
				placedestination=tab[1];
				ladatederetour=tab[2];
				sb.append(placedepart+"---"+placedestination+"---"+nouvDate+"---"+ladatederetour+"---*DateModifiée*---"+"\n");
            }
		}
		reader.close();
        VoyagesFaites.delete();
        creerNouvFichierVoyages(nump);
        BufferedWriter out = new BufferedWriter(new FileWriter(VoyagesFaites));
        out.write(sb.toString());
        out.close();
	}
	public void modifierVoyageDateRetour(String nump,String ancDate,String nouvDate) throws IOException 
	{
		VoyagesFaites=new File("C:\\Agence\\TouslesVoyageurs\\"+nump+"\\VoyagesFaites.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(VoyagesFaites)));
		String line;
		String placedepart;
		String placedestination;
		String ladatederetour;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) 
        {
			if (!line.contains(ancDate))
			{
				sb.append(line+"\n");
				
			}
			else 
            {
				String[] tab=line.split("---");
				placedepart=tab[0];
				placedestination=tab[1];
				ladatederetour=tab[2];
				sb.append(placedepart+"---"+placedestination+"---"+nouvDate+"---"+ladatederetour+"---*DateModifiée*---"+"\n");
            }
		}
		reader.close();
        VoyagesFaites.delete();
        creerNouvFichierVoyages(nump);
        BufferedWriter out = new BufferedWriter(new FileWriter(VoyagesFaites));
        out.write(sb.toString());
        out.close();
	}
	public int ContientDatedeDepart(String nump,String date) throws IOException
	{
		VoyagesFaites=new File("C:\\Agence\\TouslesVoyageurs\\"+nump+"\\VoyagesFaites.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(VoyagesFaites)));
        String line;    
        int lineNumber=0;
        while ((line = reader.readLine()) != null) 
        {
            if (line.contains(date)) 
            {
                lineNumber++;
            }
        }
        reader.close();
        return lineNumber;	
	}
	public void ModifierNom(String nump) throws IOException
	{
		donnees=new File("C:\\Agence\\TouslesVoyageurs\\"+nump+"\\Donnees.txt");
		Scanner sc=new Scanner(System.in);
		System.out.println("donner le nouveau nom ");
		String ch=sc.nextLine();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(donnees)));
		String line=reader.readLine();
		String[] tab=line.split("---");
		StringBuffer sb = new StringBuffer();
		sb.append(ch+"---"+tab[1]+"---"+tab[2]+"---"+tab[3]+"\n");
		reader.close();
        donnees.delete();
        creerNouvFichierDonnees(nump);
        BufferedWriter out = new BufferedWriter(new FileWriter(donnees));
        out.write(sb.toString());
        out.close();
		sc.close();
	}
	public void ModifierPrenom(String nump) throws IOException
	{
		donnees=new File("C:\\Agence\\TouslesVoyageurs\\"+nump+"\\Donnees.txt");
		Scanner sc=new Scanner(System.in);
		System.out.println("donner le nouveau nom ");
		String ch=sc.nextLine();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(donnees)));
		String line=reader.readLine();
		String[] tab=line.split("---");
		StringBuffer sb = new StringBuffer();
		sb.append(tab[0]+"---"+ch+"---"+tab[2]+"---"+tab[3]+"\n");
		reader.close();
        donnees.delete();
        creerNouvFichierDonnees(nump);
        BufferedWriter out = new BufferedWriter(new FileWriter(donnees));
        out.write(sb.toString());
        out.close();
		sc.close();
	}
	public void ModifierCIN(String nump) throws IOException
	{
		donnees=new File("C:\\Agence\\TouslesVoyageurs\\"+nump+"\\Donnees.txt");
		Scanner sc=new Scanner(System.in);
		System.out.println("donner le nouveau numero de cin ");
		String ch=sc.nextLine();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(donnees)));
		String line=reader.readLine();
		String[] tab=line.split("---");
		StringBuffer sb = new StringBuffer();
		sb.append(tab[0]+"---"+tab[1]+"---"+ch+"---"+tab[3]+"\n");
		reader.close();
        donnees.delete();
        creerNouvFichierDonnees(nump);
        BufferedWriter out = new BufferedWriter(new FileWriter(donnees));
        out.write(sb.toString());
        out.close();
		sc.close();
	}
	public void ModifierDatedeNaissance(String nump) throws IOException
	{
		donnees=new File("C:\\Agence\\TouslesVoyageurs\\"+nump+"\\Donnees.txt");
		Scanner sc=new Scanner(System.in);
		System.out.println("donner la nouvelle date de naissance ");
		String ch=sc.nextLine();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(donnees)));
		String line=reader.readLine();
		String[] tab=line.split("---");
		StringBuffer sb = new StringBuffer();
		sb.append(tab[0]+"---"+tab[1]+"---"+tab[2]+"---"+ch+"\n");
		reader.close();
        donnees.delete();
        creerNouvFichierDonnees(nump);
        BufferedWriter out = new BufferedWriter(new FileWriter(donnees));
        out.write(sb.toString());
        out.close();
		sc.close();
	}

}
