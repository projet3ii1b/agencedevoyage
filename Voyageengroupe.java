package agencedevoyage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Voyageengroupe{
	protected FileWriter FW;
	protected BufferedWriter BW;
	protected File fichierVoyageur; 
	protected File Destination;
	protected File Groupe;
	protected long nb;
	protected Scanner s;
	public Voyageengroupe()//constructeur dans lequel on va creer le dossier des voyages en groupe et creer un objet de type Scanner
	{
		s=new Scanner(System.in);
		File dossierVoyageEnGroupe=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\");
		if (!dossierVoyageEnGroupe.exists())
		{
			dossierVoyageEnGroupe.mkdir();
		}
	}
	public void NouvelleDestination(String Dest)//sert pour creer le dossier d'une nouvelle destination en groupe si elle n'existe pas auparavant 
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+Dest);
		if (!Destination.exists())
		{
			Destination.mkdir();
		}
	}
	public void GroupesInitiales(String Dest)//permet la creation de 4 groupes automatiquement dés qu'une nouvelle destination est saisie 
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+Dest);
		for(int i=1;i<5;i++)
		{
			Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+i);
			if (!Groupe.exists())
			{
				Groupe.mkdir();
			}
		}
	}
	public void ajouterNouvGroupe()//permet la creation d'un nouveau groupe
	{
		Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+(Destination.length()+1));
		if (!Groupe.exists())
		{
			Groupe.mkdir();
		}	
	}
	public long nbGroupeExistant()//retourne le nombre de groupes existants pour une destination specifique 
	{
		return nb=Destination.length();
	}
	public void creerNouvFichierDonnees(String Dest,String Groupechoisie,String numpasseport) throws IOException//fonction chargée de créer le fichier de donnees pour chaque client dans le groupe qu'il choisira 
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+Dest);
		Groupe=new File(Destination.getAbsolutePath()+"\\"+Groupechoisie);
		fichierVoyageur=new File(Groupe.getAbsoluteFile()+"\\"+numpasseport+".txt");
		if (!fichierVoyageur.exists())
		{
			fichierVoyageur.createNewFile();
		} 
	}
	public void ajouterDonnees(String Dest,String Groupechoisie,String numpasseport) throws IOException//fonction qui va ecrire les donnees du client dans son propre fichier 
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+Dest);
		Groupe=new File(Destination.getAbsolutePath()+"\\"+Groupechoisie);
		fichierVoyageur=new File(Groupe.getAbsoluteFile()+"\\"+numpasseport+".txt");
		BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+numpasseport+".txt"));//lire les donnees du voyageur a partir de son fichier deja existant dans la classe tous les voyages 
		FW=new FileWriter(fichierVoyageur,true);
		BW=new BufferedWriter(FW);
		String ch=reader.readLine();
		BW.write(ch);
		BW.close();
		FW.close();	
		reader.close();
	}
	public void listeGroupeDisponibles(String Dest)//affiche les groupes qui sont encore disponibles  
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+Dest);
		String ch="";
		String[] list = Destination.list();
		for (int i = 0; i < list.length; i++) {
			Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+(i+1));
			nb=Groupe.listFiles().length;//nb contient le nombre de fichiers dans un groupe specifique
			if (nb<16)//le groupe n'est pas encore remplis 
			{
				ch+=list[i]+" ";//ajouter le nom du groupe pour qu'il soit affiché plus tard 
			}
			else //le groupe est deja rempli 
				ch+="";// le nom du groupe ne sera pas affiché 
		}
		if (ch.length()==0)//si tous les groupes qui ont été deja crées sont remplis 
		{
			Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+(Destination.listFiles().length+1));
			if (!Groupe.exists())
			{
				Groupe.mkdir();//creation d'un nouveau groupe
			}
			ch="Groupe"+(Destination.listFiles().length);
		}
		System.out.println("les groupes disponibles sont : "+ch+" Veuillez choisir votre groupe souhaité");//afficher le nouveau nom de ce groupe
	}
	void supprimerDossierVoyageEnGroupe(String depart,String datededepart,String destination,String datederetour)//effectue la suppression de tout une destination 
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+depart+'_'+destination+"_"+datededepart+"_"+datederetour);
		if (Destination.exists())
		{
			Destination.delete();
		}
		}
	void SupprimerVoyageurEnGroupe(String depart,String datededepart,String destination,String datederetour,String numpasseport ) throws IOException//effectue la suppression d'un voyageur d'un groupe specifique 
    {
		String ch=depart+'_'+destination+'_'+datededepart+'_'+datederetour;
		System.out.println(ch);
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch);
		String[] tab=Destination.list();
		for (int i=0;i<tab.length;i++)
			{
			File fichier =new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch+"\\"+tab[i]+"\\"+numpasseport+".txt");
			if (fichier.exists())
			{
				fichier.delete();
			}
		}			
	}
	public void retarderGroupe(String date1,String date2)//retarder la date du voyage d'un groupe 
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\");
		String[] liste=Destination.list();
		for(int i=0;i<liste.length;i++)
		{
			if (liste[i].contains(date1))
			{
				String nouv=liste[i].replace(date1,date2);
				File ANCIEN=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+liste[i]);
				File NOUV=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+nouv);
				ANCIEN.renameTo(NOUV);	//renomer le dossier avec la nouvelle date saisie 			
			}
		}	
	}
	public void retarderdatedeRetourGroupe(String Dest)//retarder la date de retour d'une destination 
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+Dest);
		System.out.println("donner la nouvelle date de retour");
		String nouvDateRetour=s.nextLine();
		String[] tab=Destination.getName().split("_");
		String nouvNom=tab[0]+'_'+tab[1]+'_'+tab[2]+'_'+nouvDateRetour;
		File DestApres=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+nouvNom);
		Destination.renameTo(DestApres);
	}
	public void retarderdatedepartGroupe(String Dest)//retarder la date de depart d'une destination
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+Dest);
		System.out.println("donner la nouvelle date de depart");
		String nouvDateDepart=s.nextLine();
		String[] tab=Destination.getName().split("_");
		String nouvNom=tab[0]+'_'+tab[1]+'_'+nouvDateDepart+'_'+tab[3];
		File DepartApres=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+nouvNom);
		Destination.renameTo(DepartApres);
	}
	public void finalize()
	{
		s.close();;
	}
	public void lireNomPrenomVoyageur(String ch,String Groupe,String numpasseport) throws IOException//extraire le nom et le prenom d'un client a partir de son fichier de donnees 
	{
		fichierVoyageur=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch+"\\"+Groupe+"\\"+numpasseport);
		BufferedReader reader= new BufferedReader(new FileReader(fichierVoyageur));
		String [] tab=reader.readLine().split("---");
		System.out.println(tab[1]+"  "+tab[2]);
		reader.close();
	}
	public void afficherCaracteristiquesGroupe(String ch) throws IOException//permet l'affichage des caracteristiques des groupes d'une destination
	{
		Destination=new File("C:\\AgencedeVoyage\\VoyagerEnGroupe\\"+ch);
		for(int i=0;i<Destination.list().length;i++)
		{
			Groupe=new File(Destination.getAbsolutePath()+"\\Groupe"+String.valueOf(i+1));
			if (Groupe.list().length==0)//si le groupe est vide 
			{
				System.out.println("Il y a aucune personne dans le Groupe"+String.valueOf(i+1));
			}
			else//si le groupe contient des personnes 
			{
				System.out.println("les personnes dans le Groupe"+String.valueOf(i+1)+ " sont : ");
				for(int j=0;j<Groupe.list().length;j++)
				{
					lireNomPrenomVoyageur(ch,Destination.list()[i],Groupe.list()[j]);//afficher le nom et prenom de chaque client dans un groupe 
				}
			}
		}
		
	}
	
}
