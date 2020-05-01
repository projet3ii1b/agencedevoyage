package agencedevoyage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Voyageengroupe{
	protected FileWriter FW;
	protected BufferedWriter BW;
	protected File fichierVoyageur; 
	protected File Destination;
	protected File Groupe;
	protected long nb;
	protected Scanner s;
	public Voyageengroupe()
	{
		s=new Scanner(System.in);
		File dossierVoyageEnGroupe=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\");
		if (!dossierVoyageEnGroupe.exists())
		{
			dossierVoyageEnGroupe.mkdir();
		}
	}
	public void creerNouvFichierDonnees(String Dest,String Groupechoisie,String numpasseport) throws IOException
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+Dest);
		Groupe=new File(Destination.getAbsolutePath()+"\\"+Groupechoisie);
		fichierVoyageur=new File(Groupe.getAbsoluteFile()+"\\"+numpasseport+".txt");
		if (!fichierVoyageur.exists())
		{
			fichierVoyageur.createNewFile();
		} 
		System.out.println("les données sont enregistrés avec succés!");
	}
	public void ajouterDonnees(String Dest,String Groupechoisie,String numpasseport) throws IOException
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+Dest);
		Groupe=new File(Destination.getAbsolutePath()+"\\"+Groupechoisie);
		fichierVoyageur=new File(Groupe.getAbsoluteFile()+"\\"+numpasseport+".txt");
		BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+numpasseport+".txt"));
		FW=new FileWriter(fichierVoyageur,true);
		BW=new BufferedWriter(FW);
		String ch=reader.readLine();
		BW.write(ch);
		BW.close();
		FW.close();	
		reader.close();
	}
	public void NouvelleDestination(String ch)
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch);
		if (!Destination.exists())
		{
			Destination.mkdir();
		}
	}
	public void GroupesInitiales(String ch)
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch);
		for(int i=1;i<5;i++)
		{
			Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+i);
			if (!Groupe.exists())
			{
				Groupe.mkdir();
			}
		}
	}
	public long nbGroupeExistant()
	{
		return nb=Destination.length();
	}
	public void ajouterNouvGroupe()
	{
		Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+(Destination.length()+1));
		if (!Groupe.exists())
		{
			Groupe.mkdir();
		}	
	}
	public void listeGroupeDisponibles(String ch1)
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch1);
		String ch="";
		String[] list = Destination.list();
		for (int i = 0; i < list.length; i++) {
			Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+(i+1));
			nb=Groupe.listFiles().length;
			if (Groupe.listFiles().length<16)
			{
				ch+=list[i]+" ";
			}
			else 
				ch+="";
		}
		if (ch.length()==0)
		{
			Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+(Destination.listFiles().length+1));
			if (!Groupe.exists())
			{
				Groupe.mkdir();
			}
			ch="Groupe"+(Destination.listFiles().length);
		}
		System.out.println("les groupes disponibles sont : "+ch+" Veuillez choisir votre groupe souhaité");
	}
	public String choixGroupeaRetarder()
	{
		System.out.println("donner le nom Dest");
		String ch=s.nextLine();
		return ch;
		
	}
	
	public void retarderdatedeRetourGroupe(String ch1)
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch1);
		System.out.println("donner la nouvelle date de retour");
		String ch=s.nextLine();
		String[] tab=Destination.getName().split("_");
		String nouvNom=tab[0]+'_'+tab[1]+'_'+tab[2]+'_'+ch;
		File DestApres=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+nouvNom);
		Destination.renameTo(DestApres);
	}
	public void retarderdatedepartGroupe(String ch1)
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch1);
		System.out.println("donner la nouvelle date de depart");
		String ch=s.nextLine();
		String[] tab=Destination.getName().split("_");
		String nouvNom=tab[0]+'_'+tab[1]+'_'+ch+'_'+tab[3];
		File DepartApres=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+nouvNom);
		Destination.renameTo(DepartApres);
	}
	public void finalize()
	{
		s.close();;
	}
	public void lireNomPrenomVoyageur(String ch,String Groupe,String numpasseport) throws IOException
	{
		fichierVoyageur=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch+"\\"+Groupe+"\\"+numpasseport);
		BufferedReader reader= new BufferedReader(new FileReader(fichierVoyageur));
		String [] tab=reader.readLine().split("---");
		System.out.println(tab[1]+"  "+tab[2]);
		reader.close();
	}
	public void afficherCaracteristiquesGroupe(String ch) throws IOException
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch);
		for(int i=0;i<Destination.list().length;i++)
		{
			Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+String.valueOf(i+1));
			if (Groupe.list().length==0)
			{
				System.out.println("Il y a aucune persone dans le Groupe"+String.valueOf(i+1));
			}
			else
			{
				System.out.println("les personnes dans le Groupe"+String.valueOf(i+1)+ " sont : ");
				for(int j=0;j<Groupe.list().length;j++)
				{
					lireNomPrenomVoyageur(ch,Destination.list()[i],Groupe.list()[j]);
				}
			}
		}
		
	}
	
}
