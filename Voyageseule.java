package agencedevoyage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class Voyageseule{
	protected File fichier ;
	protected FileWriter FW;
	public Touslesvoyages v;
	protected BufferedWriter BW;
	
	public void fichierExistant()
	{
		fichier= new File("C:\\AgencedeVoyage\\voyageseule\\"+v.getNumpasseport()+".txt");
		
	}
	public int rechercher(String s)
	{
		File f=new File("C:\\AgencedeVoyage\\voyageseule\\"+s+".txt");
		if (f.exists())
			return 1;
		else
			return 0;
}
	public Voyageseule(String ch) throws IOException
	{
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
	public Voyageseule() throws IOException
	{
		v= new Touslesvoyages();
		File dossier=new File("C:\\AgencedeVoyage\\voyageseule");
		if (dossier.isDirectory()!=true)
			dossier.mkdir();
	}
	public void Enregistrer() throws IOException
	{
		v.AjouterVoyageur();
	}
	public void creerNouvFichier(String nump) throws IOException 
	{
		fichier= new File("C:\\AgencedeVoyage\\voyageseule\\"+nump+".txt");
		if (!fichier.exists())
		{
			fichier.createNewFile();
		}
	}
	public void AjouterDonnes(String nump) throws IOException {
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
	public void AjouterUnVoyage() throws IOException 
	{
		FW=new FileWriter(fichier,true);
		BW=new BufferedWriter(FW);
		String ch=v.getDepart()+"---"+v.getDestination()+"---"+v.getDatedepart()+"---"+v.getDateretour()+"\n";
		BW.write(ch);
		BW.close();
		FW.close();	
	}
	 void supprimervoyage(String annee,String mois,String pays1,String pass,String date) throws IOException
	    {
	    	File file = new File("C:\\AgencedeVoyage\\voyageseule\\"+ pass + ".txt");
			File temp = new File("C:\\AgencedeVoyage\\voyageseule\\"+"temp" + pass+".txt");
		    PrintWriter out = new PrintWriter(new FileWriter(temp));
		    Files.lines(file.toPath()).filter(line -> !line.contains(date)).forEach(out::println);
		    out.flush();
		    out.close();
		    temp.renameTo(file);
		    file.delete();
		    File ch = new File("C:\\AgencedeVoyage\\voyageseule\\"+ pass + ".txt");
		    temp.renameTo(ch);
		    v.supprimervoyage();   
	     }
	 /*void retarder(String annee,String annee2,String mois1,String mois2,String date1 ,String date2) throws IOException
	    {
		    v.retarder(annee,annee2,mois1,mois2,date1,date2);
	    	File initial = new File ("C:\\AgencedeVoyage\\voyageseule\\");
	    	String liste[] = initial.list();
	        int i=0;
	        while (i<liste.length)
				{
					File fileToBeModified = new File("C:\\AgencedeVoyage\\voyageseule\\"+liste[i]);
					String oldContent = "";
					BufferedReader reader = null;
					FileWriter writer = null;
					reader = new BufferedReader(new FileReader(fileToBeModified));
					 String line = reader.readLine();
					 while (line != null) 
			            {
			                oldContent = oldContent + line + System.lineSeparator();;  
			                line = reader.readLine();
			            }
					 String newContent = oldContent.replaceAll(date1, date2);
					 writer = new FileWriter(fileToBeModified);
		             
			         writer.write(newContent);
			         reader.close();
		              writer.close();
		              i++;
			} 
	    }*/
}
