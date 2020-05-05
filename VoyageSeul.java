package agencedevoyage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VoyageSeul{
	protected File fichier ;
	protected FileWriter FW;
	public Touslesvoyages v;
	protected BufferedWriter BW;
	public VoyageSeul(String ch) throws IOException
	{ 
		v= new Touslesvoyages();
		File dossierPrincipal=new File("C:\\AgencedeVoyage\\voyageseule");
		if (dossierPrincipal.exists()!=true)
		{
			dossierPrincipal.mkdir();
		}
		File fichierVoyagerSeul=new File("C:\\AgencedeVoyage\\voyageseule\\"+ch+".txt");
		if (fichierVoyagerSeul.exists()!=true)
		{
			fichierVoyagerSeul.createNewFile();
		}
		
	}
	public int rechercher(String s)//retourne 1 si le voyageur existe deja dans le dossier voyageseule sinon 0
	{
		File f=new File("C:\\AgencedeVoyage\\voyageseule\\"+s+".txt");
		if (f.exists())
			return 1;
		else
			return 0;
	}
	public void creerNouvFichier(String nump) throws IOException //permet de creer un nouveau fichier d'un voyageur dans le dossier Voyageseule
	{
		fichier= new File("C:\\AgencedeVoyage\\voyageseule\\"+nump+".txt");
		if (!fichier.exists())
		{
			fichier.createNewFile();
		}
	}
	public void AjouterDonnes(String nump) throws IOException //permet d'ajouter les donnees d'un voyageur qui va voyager seul a son fichier
	{
		fichier= new File("C:\\AgencedeVoyage\\voyageseule\\"+nump+".txt");
		BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+nump+".txt"));
		FW=new FileWriter(fichier,true);
		BW=new BufferedWriter(FW);
		String ch=reader.readLine();
		BW.write(ch);
		BW.close();
		FW.close();	
		reader.close();
	}
}
