package projet3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TouslesVoyageurs extends Voyageur {
	protected String numpasseport;
	protected File dossier;
	protected Voyageur Voyageur ;
	protected File dossierPrincipal;
	protected File dossierTouslesVoyageurs;
	protected File dossierVoyageur;
	protected File dossierVoyagerSeul;
	protected File dossierVoyagerEnGroupe;
	
	public TouslesVoyageurs()
	{
		Voyageur=new Voyageur();
		dossierPrincipal=new File("C:\\Agence");
		if (dossierPrincipal.isDirectory()!=true)
			dossierPrincipal.mkdir();
		dossierTouslesVoyageurs=new File("C:\\Agence\\TouslesVoyageurs");
		if (dossierTouslesVoyageurs.isDirectory()!=true)
			dossierTouslesVoyageurs.mkdir();
		dossierVoyagerSeul=new File("C:\\Agence\\VoyagerSeul");
		if (dossierVoyagerSeul.isDirectory()!=true)
			dossierVoyagerSeul.mkdir();
		dossierVoyagerEnGroupe=new File("C:\\Agence\\VoyagerEnGroupe");
		if (dossierVoyagerEnGroupe.isDirectory()!=true)
			dossierVoyagerEnGroupe.mkdir();
	}
	
	public void AjouterVoyageur() throws IOException
	{
		System.out.println("Donner votre numero de passeport : ");
		Scanner s=new Scanner(System.in);
		numpasseport=s.nextLine();
		Voyageur.SetNumpasseport(numpasseport);
		System.out.println("Si vous voulez voyager seul,taper0,Si vous voulez voyager en groupe,taper1");
		int choix=s.nextInt();
		if (rechercher(this.numpasseport)==0)
		{
				dossierVoyageur=new File("C:\\Agence\\TouslesVoyageurs\\"+numpasseport);
				dossierVoyageur.mkdir();
				Voyageur.SaisieDonnes();
				Voyageur.setDonnes("C:\\Agence\\TouslesVoyageurs\\"+numpasseport+"\\Donnees.txt");
				Voyageur.creerNouvFichierDonnees(numpasseport);
				Voyageur.AjouterDonnes();
				Voyageur.setVoyagesFaites("C:\\Agence\\TouslesVoyageurs\\"+numpasseport+"\\VoyagesFaites.txt");
				Voyageur.creerNouvFichierVoyages(numpasseport);
				Voyageur.SaisieVoyagesFaites();
				Voyageur.AjouterUnVoyage();
				Voyageur.NomDestination=Voyageur.nomDestination();
				if (choix==0)
				{
					VoyagerSeul VS=new VoyagerSeul(numpasseport);
					Voyageur.setDonnes(VS.nomDossierVS().getAbsolutePath());
					Voyageur.creerNouvFichierDonnees(numpasseport);
					Voyageur.AjouterDonnes();
				}
				if (choix==1)
				{
					Scanner sc=new Scanner(System.in);
					VoyagerEnGroupe VG=new VoyagerEnGroupe();
					VG.NouvelleDestination(Voyageur.NomDestination);
					VG.GroupesInitiales();
					VG.listeGroupeDisponibles();
					String ch=sc.nextLine();
					Voyageur.setDonnes(VG.Destination.getAbsolutePath()+"//"+ch+"//"+Voyageur.numpasseport+".txt");
					Voyageur.creerNouvFichierDonnees(numpasseport);
					Voyageur.AjouterDonnes();
					sc.close();
				}
		}
		else if (rechercher(this.numpasseport)==1)
		{
			Voyageur.setDonnes("C:\\Agence\\TouslesVoyageurs\\"+numpasseport+"\\Donnees.txt");
			Voyageur.setVoyagesFaites("C:\\Agence\\TouslesVoyageurs\\"+numpasseport+"\\VoyagesFaites.txt");
			this.afficherVoyagesFaites(numpasseport);
			Voyageur.SaisieVoyagesFaites();
			Voyageur.AjouterUnVoyage();	
			Voyageur.NomDestination=Voyageur.nomDestination();
			if (choix==0)
			{
				VoyagerSeul VS=new VoyagerSeul(numpasseport);
				Voyageur.setDonnes(VS.nomDossierVS().getAbsolutePath());
				Voyageur.creerNouvFichierDonnees(numpasseport);
				Voyageur.AjouterDonnes();
				
			}
			else if (choix==1)
			{
				Scanner sc=new Scanner(System.in);
				VoyagerEnGroupe VG=new VoyagerEnGroupe();
				VG.NouvelleDestination(Voyageur.NomDestination);
				VG.GroupesInitiales();
				VG.listeGroupeDisponibles();
				String ch=sc.nextLine();
				Voyageur.setDonnes(VG.Destination.getAbsolutePath()+"//"+ch+"//"+Voyageur.numpasseport+".txt");
				Voyageur.creerNouvFichierDonnees(numpasseport);
				Voyageur.AjouterDonnes();
				sc.close();
			}
		}
		Voyageur.sc.close();
		s.close();
	}
	public int rechercher(String s)
	{
		File f=new File("C:\\Agence\\TouslesVoyageurs\\"+s);
		if (f.exists())
			return 1;
		else
			return 0;
		
	}
	public void afficherVoyagesFaites(String s) throws IOException
    {
		File f=new File("C:\\Agence\\TouslesVoyageurs\\"+s+"\\VoyagesFaites.txt");
    	BufferedReader reader= new BufferedReader(new FileReader(f));
    	String line;
    	System.out.println("les voyages effectuees sont ");
    	while (( line= reader.readLine())!=null  )
    	{ 
    		System.out.println(line) ;
    	}
    	reader.close();
    }
	public void afficherTouslesVoyages() throws IOException
	{
		File DossierTouslesVoyages =new File("C:\\Agence\\TouslesVoyageurs\\");
		String[] listeVoyageurs=DossierTouslesVoyages.list();
		for (int i=0;i<listeVoyageurs.length;i++)
		{
			File f=new File("C:\\Agence\\TouslesVoyageurs\\"+listeVoyageurs[i]+"\\VoyagesFaites.txt");
	    	BufferedReader reader= new BufferedReader(new FileReader(f));
	    	String line;
	    	System.out.println("les voyages effectués pour le voyageur "+listeVoyageurs[i]+" sont ");
	    	while (( line= reader.readLine())!=null  )
	    	{ 
	    		System.out.println(line) ;
	    	}
	    	reader.close();
			
		}
		
	}
}
