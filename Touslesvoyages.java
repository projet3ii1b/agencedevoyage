package agencedevoyage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class Touslesvoyages {
	protected String nom;
	protected String prenom;
	protected String datedenaissance;
	protected String cin;
	protected String numpasseport;
	protected String depart;
	protected String destination;
	protected String datededepart;
	protected String datederetour ; 
	protected File fichier ;
	protected File filepays ;
	protected FileWriter FW;
	protected BufferedWriter BW;
	protected Année a;
	protected Scanner sc;
	protected String NomDestination;
	public void SaisieDonnes()
	{
		sc = new Scanner(System.in);
		System.out.println("Donner votre nom");
		nom=sc.nextLine();
		System.out.println("Donner votre prenom");
		prenom=sc.nextLine();
		System.out.println("Donner votre date de naissance sous la forme (jour-mois-annee)");
		datedenaissance=sc.nextLine();
		System.out.println("Donner votre numero de CIN");
		cin=sc.nextLine();
		
			
	}
	public void SaisieVoyagesFaites() throws IOException
	{
		sc =new Scanner(System.in);
		System.out.println("Donner votre place de depart");
		depart=sc.nextLine();
		System.out.println("Donner votre destination : Pays-Ville");
		destination=sc.nextLine();
		System.out.println("Donner la date de depart");
		datededepart=sc.nextLine();
		System.out.println("Donner la date de retour");
		datederetour=sc.nextLine();
		FW=new FileWriter(fichier,true);
		BW=new BufferedWriter(FW);
		a.saisie(this.depart,this.destination,this.datededepart,this.datederetour);
		FW.close();
		BW.close();
	}
	public String nomDestination()
	{
		NomDestination=depart+'_'+destination+'_'+datededepart+'_'+datederetour;
		return NomDestination;
	}
	public void AjouterVoyageur() throws IOException
	{
		a = new Année();
		paysfolder();
		System.out.println("Donner votre numero de passeport : ");
		sc=new Scanner(System.in);
		numpasseport=sc.nextLine();
		System.out.println("Si vous voulez voyager seul taper 0 , Si vous voulez voyager en groupe taper 1 ");
		int choix=sc.nextInt();
		if (rechercher(this.numpasseport)==0)
		{
				SaisieDonnes();
				creerNouvFichier();
				AjouterDonnes();
				SaisieVoyagesFaites();
				AjouterUnVoyage();
				Ajoutvoyagepays();
				NomDestination=nomDestination();
				if (choix==0)
				{
					Voyageseule VS=new Voyageseule(numpasseport);
					VS.creerNouvFichier(numpasseport);
					VS.AjouterDonnes(numpasseport);
				}
				else if (choix==1)
				{
					Voyageengroupe VG=new Voyageengroupe();
					VG.NouvelleDestination(NomDestination);
					VG.GroupesInitiales(NomDestination);
					VG.afficherCaracteristiquesGroupe(NomDestination);
					VG.listeGroupeDisponibles(NomDestination);
					String ch=sc.nextLine();
					VG.creerNouvFichierDonnees(NomDestination,ch,numpasseport);
					VG.ajouterDonnees(NomDestination,ch,numpasseport);
				}
		}
		else if (rechercher(this.numpasseport)==1)
		{
			fichierExistant();
			prendreDonnees(numpasseport);
			afficherVoyagesFaites(numpasseport);
			SaisieVoyagesFaites();
			AjouterUnVoyage();	
			Ajoutvoyagepays();
			NomDestination=nomDestination();
			if (choix==0)
			{
				Voyageseule VS=new Voyageseule(numpasseport);
				VS.creerNouvFichier(numpasseport);
				
			}
			else if (choix==1)
			{
				Voyageengroupe VG=new Voyageengroupe();
				VG.NouvelleDestination(NomDestination);
				VG.GroupesInitiales(NomDestination);
				VG.afficherCaracteristiquesGroupe(NomDestination);
				VG.listeGroupeDisponibles(NomDestination);
				String ch=sc.nextLine();
				VG.creerNouvFichierDonnees(NomDestination,ch,numpasseport);
				VG.ajouterDonnees(NomDestination,ch,numpasseport);
			}
		}
		sc.close();
	}
	public void paysfolder() {
		File dossier=new File("C:\\AgencedeVoyage\\pays\\");
		if (dossier.isDirectory()!=true)
		{
			dossier.mkdir();
		}
	//	filepays=new File("C:\\AgencedeVoyage\\pays\\"+this.destination+".txt");
		
	}
	public void creerNouvFichier() throws IOException 
	{
		fichier= new File("C:\\AgencedeVoyage\\Tousvoyages\\"+this.numpasseport+".txt");
		if (!fichier.exists())
		{
			fichier.createNewFile();
		}
	}
	public void fichierExistant()
	{
		fichier= new File("C:\\AgencedeVoyage\\Tousvoyages\\"+this.numpasseport+".txt");
		//filepays=new File("C:\\AgencedeVoyage\\Tousvoyages\\pays\\"+this.destination+".txt");
		
	}
	public void prendreDonnees(String pass) throws IOException
	{
		BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+pass+".txt"));
		String[] listeDonnees=reader.readLine().split("---");
		nom=listeDonnees[0];
		prenom=listeDonnees[1];
		datedenaissance=listeDonnees[2];
		cin=listeDonnees[3];
		reader.close();
	}
	public void AjouterDonnes() throws IOException {
		FW=new FileWriter(fichier,true);
		BW=new BufferedWriter(FW);
		String ch=this.numpasseport+"---"+this.nom+"---"+this.prenom+"---"+this.cin+"---"+this.datedenaissance+"\n";
		BW.write(ch);
		BW.close();
		FW.close();	
	}
	public void AjouterUnVoyage() throws IOException 
	{
		FW=new FileWriter(fichier,true);
		BW=new BufferedWriter(FW);
		String ch=this.depart+"---"+this.destination+"---"+this.datededepart+"---"+this.datederetour+"\n";
		BW.write(ch);
		BW.close();
		FW.close();	
	}
	
	void Ajoutvoyagepays() throws IOException
	{
	    FileWriter FP;
		BufferedWriter BP;
		String ch1=this.destination.substring(0,this.destination.indexOf('-'));
		filepays=new File("C:\\AgencedeVoyage\\pays\\"+ch1+".txt");
		if (!filepays.exists())
		{
			filepays.createNewFile();
		}
		FP=new FileWriter(filepays,true);
		BP=new BufferedWriter(FP);
		String ch=this.depart+"---"+this.destination+"---"+this.datededepart+"---"+this.datederetour+"\n";
		BP.write(ch);
		BP.close();
	}
	
	void nombreVoyagepaysSP(String nompays) throws IOException
	{
		BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\pays\\"+nompays));
    	String line;
    	System.out.println("Les voyages vers "+nompays.substring(0,nompays.indexOf("."))+" sont : ");
    	while (( line= reader.readLine())!=null)
    	{
    			System.out.println(line);
    		
    	}
    	reader.close();
		
	}
	void nombreVoyagesspaysTotale() throws IOException
	{
		File dossierPays=new File("C:\\AgencedeVoyage\\pays\\");
		String[] tab=dossierPays.list();
		for(int i=0;i<tab.length;i++)
		{
			nombreVoyagepaysSP(tab[i]);
		}
	}
	public String getDatedenaissance()
	{
		return ( datedenaissance);
	}
	public String getNom()
	{
		return (nom);
	}
	public String getPrenom()
	{ 
		return ( prenom );
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
	String getNumpasseport()
	{ 
		return ( numpasseport );
	}
	 public String getDestination()
	{ 
		return ( destination );
	}
	public Touslesvoyages() throws IOException
	{
		a = new Année();
		sc=new Scanner(System.in);
		File dossierPrincipal=new File("C:\\AgencedeVoyage");
		if (dossierPrincipal.isDirectory()!=true)
			dossierPrincipal.mkdir();
		File dossier=new File("C:\\AgencedeVoyage\\Tousvoyages");
		if (dossier.isDirectory()!=true)
			dossier.mkdir();
	}
	public void finalize()
	{
		sc.close();;
	}
	public int rechercher(String s)
	{
		File f=new File("C:\\AgencedeVoyage\\Tousvoyages\\"+s+".txt");
		if (f.exists())
			return 1;
		else
			return 0;
		
	}
    public void afficherDonneesetVoyages(String pass) throws IOException
    {
    	BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+pass+".txt"));
    	String line;
    	while (( line= reader.readLine())!=null  )
    	{ 
    		System.out.println(line) ;
    	}
    	reader.close();
    }
    public void afficherVoyagesFaitesAC() throws IOException//saisie au clavier
    {
    	System.out.println("Donner le numéro de passeport du voyageur que vous voulez affichez ses voyages ");
    	sc=new Scanner(System.in);
    	String ch=sc.nextLine();
    	BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+ch+".txt"));
    	String line;
    	System.out.println("Les voyages effectués par "+ch+" sont : ");
    	while (( line= reader.readLine())!=null)
    	{
    		if (!line.contains(ch))
    		{
    			System.out.println(line);
    		}
    		
    	}
    	reader.close();
    }
    public void afficherVoyagesFaites(String pass) throws IOException
    {
    	BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+pass+".txt"));
    	String line;
    	while (( line= reader.readLine())!=null)
    	{
    		if (!line.contains(pass))
    		{
    			System.out.println(line);
    		}
    		
    	}
    	reader.close();
    }
    public void afficherTouslesVoyages() throws IOException
	{
		File dossierTouslesVoyageurs =new File("C:\\AgencedeVoyage\\Tousvoyages\\");
		String[] listeVoyageurs=dossierTouslesVoyageurs.list();
		for (int i=0;i<listeVoyageurs.length;i++)
		{
			File f=new File("C:\\AgencedeVoyage\\Tousvoyages\\"+listeVoyageurs[i]);
	    	BufferedReader reader= new BufferedReader(new FileReader(f));
	    	String line;
	    	int x=listeVoyageurs[i].indexOf('.');
	    	
	    	System.out.println("les voyages effectués pour le voyageur "+listeVoyageurs[i].substring(0,x)+" sont ");
	    	while (( line= reader.readLine())!=null  )
	    	{ 
	    		if (!line.contains(listeVoyageurs[i].substring(0,x)))
	    		System.out.println(line) ;
	    	}
	    	reader.close();
		}
		
	}
    public void copier(String pass) throws IOException 
    {
    	InputStream inStream = null;
        OutputStream outStream = null;
        File bfile =new File("C:\\AgencedeVoyage\\Tousvoyages\\"+"Bfile.txt");

        inStream = new FileInputStream("C:\\AgencedeVoyage\\Tousvoyages\\"+pass+".txt");
        outStream = new FileOutputStream(bfile);

        byte[]buffer = new byte[1024];

        int length;
        length = inStream.read(buffer);
       //copy the file content in bytes
        while ((length ) > 0){

            outStream.write(buffer, 0, length);

        }

        inStream.close();
        outStream.close();
        System.out.println("File is copied successful!");

    }
    public int nombredevoyage(String pass) throws IOException
    {
             FileReader fr = new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+pass+".txt");
             LineNumberReader lnr = new LineNumberReader(fr);
             int linenumber = 0;

              while (lnr.readLine() != null){
                 linenumber++;
                 }
              lnr.close(); 
            //  String ch = Integer.toString(linenumber-1);
             // System.out.println(linenumber-1);
                 return( linenumber-1  );
    }
    void supprimervoyage() throws IOException
    {
    	sc=new Scanner(System.in);
    	Voyageengroupe VG=new Voyageengroupe();
    	System.out.println("Donner le numero de passeport de voyageur que vous voulez supprimez son voyage : ");
    	numpasseport=sc.nextLine();
    	System.out.println("Donner la place de depart");
    	depart=sc.nextLine();
    	System.out.println("Donner la destination sous la forme (PAYS-VILLE) : ");
    	destination=sc.nextLine();
    	System.out.println("Donner la date de depart de ce voyage : ");
    	datededepart=sc.nextLine();
    	System.out.println("Donner la date de retour de ce voyage");
    	datederetour=sc.nextLine();
    	String[] tab=datededepart.split("-");
    	a.setAnnee(tab[2]);
    	a.setMois(tab[1]);
      	a.supprimervoyagesaison(tab[2], tab[1], datededepart,datederetour);
    	a.supprimervoyagemois(tab[2], tab[1], datededepart,datederetour);
    	VG.SupprimerVoyageurEnGroupe(depart, datededepart,destination, datederetour,numpasseport);
    	supprimervoyagepays(destination.substring(0,destination.indexOf('-')),datededepart);
    	File file = new File("C:\\AgencedeVoyage\\Tousvoyages\\"+ numpasseport + ".txt");
		File temp = new File("C:\\AgencedeVoyage\\Tousvoyages\\"+"temp" +numpasseport+".txt");
	    PrintWriter out = new PrintWriter(new FileWriter(temp));
	    Files.lines(file.toPath()).filter(line -> (!line.contains(datededepart)&&!line.contains(datederetour))).forEach(out::println);
	    out.flush();
	    out.close();
	    temp.renameTo(file);
	    file.delete();
	    File ch = new File("C:\\AgencedeVoyage\\Tousvoyages\\"+ numpasseport+ ".txt");
	    temp.renameTo(ch);
	    System.out.println("DONE");
     }
    void supprimervoyagepays(String pays1,String date) throws IOException
    {
    	File file = new File("C:\\AgencedeVoyage\\pays\\"+pays1+".txt");
		File temp = new File("C:\\AgencedeVoyage\\pays\\"+"temp"+pays1+".txt");
	    PrintWriter out = new PrintWriter(new FileWriter(temp));
	    Files.lines(file.toPath()).filter(line -> !line.contains(date)).forEach(out::println);
	    out.flush();
	    out.close();
	    temp.renameTo(file);
	    file.delete();
	    File ch = new File("C:\\AgencedeVoyage\\pays\\"+pays1+".txt");
	    temp.renameTo(ch);
    }
    void retarderpays(String date1 ,String date2) throws IOException
    
    {
    	File initial = new File ("C:\\AgencedeVoyage\\pays\\");
    	String liste[] = initial.list();
        int i=0;
        while (i<liste.length)
			{
				File fileToBeModified = new File("C:\\AgencedeVoyage\\pays\\"+liste[i]);
		     	String oldContent = "";
				BufferedReader reader = null;
				FileWriter writer = null;
				reader = new BufferedReader(new FileReader(fileToBeModified));
				 String line = reader.readLine();
				 
				 while (line != null) 
		            {
		                oldContent = oldContent + line + System.lineSeparator();
		                line = reader.readLine();
		            }
				 
				 String newContent = oldContent.replaceAll(date1, date2);
				 writer = new FileWriter(fileToBeModified);
				 writer.write(newContent);
				 reader.close();
	              writer.close();
	              i++;
				 }}
    

    void retarder() throws IOException
    {	
    	sc=new Scanner(System.in);
    	System.out.println("donner la date a retarder ");
    	String date1=sc.nextLine();
    	String [] tab1=date1.split("-");
    	String annee=tab1[2];
    	String mois1=tab1[1];
    	System.out.println("donner la nouvelle date voulu");
    	String date2=sc.nextLine();
    	String [] tab2=date2.split("-");
    	String annee2=tab2[2];
    	String mois2=tab2[1];
    	Voyageengroupe VG=new Voyageengroupe();
    	VG.retarderGroupe(date1, date2);
    	retarderpays(date1,date2);
    	a.retardersaison(annee,annee2,mois1,mois2,date1,date2);
    	File initial = new File ("C:\\AgencedeVoyage\\Tousvoyages\\");
    	String liste[] = initial.list();
        int i=0;
        while (i<liste.length)
			{
        	   if(  liste[i].equals("pays") )
        	   {i++;
        	   }else {
				File fileToBeModified = new File("C:\\AgencedeVoyage\\Tousvoyages\\"+liste[i]);
		     	String oldContent = "";
				BufferedReader reader = null;
				FileWriter writer = null;
				reader = new BufferedReader(new FileReader(fileToBeModified));
				 String line = reader.readLine();
				 
				 while (line != null) 
		            {
		                oldContent = oldContent + line + System.lineSeparator();
		                line = reader.readLine();
		            }
				 
				 String newContent = oldContent.replaceAll(date1, date2);
				 writer = new FileWriter(fileToBeModified);
				 writer.write(newContent);
				 reader.close();
	              writer.close();
	              i++;
				 }}
				
     File f1= new File ("C:\\AgencedeVoyage\\Tousvoyages\\");
     String l1[] = f1.list();
     int j=0;
    while (j<liste.length) 
    {
    	 if(  liste[j].equals("pays") )
  	   {j++;
  	   }else {
    BufferedReader reader1= new BufferedReader(new FileReader( "C:\\AgencedeVoyage\\Tousvoyages\\"+l1[j] ));
    String line1;
  	while (( line1= reader1.readLine())!=null  )
  	{ 
  		if ( line1.contains(date2) )
  		{
  			System.out.println(line1);
  		}
  	}
  	reader1.close(); 
  	
  	j++;
    }}
    }
    
    void listedevoyageparpays() throws IOException
    {
    	File initial = new File ("C:\\AgencedeVoyage\\pays\\");
    	String liste[] = initial.list();
        int i=0;
        while (i<liste.length)
        {
        	String ch ="." ;
            int j = liste[i].indexOf ( ch ) ;
            String str= liste[i].substring (0,j) ;
            System.out.println("les voyages effectués dans la pays"+" "+str+" "+"sont");
            BufferedReader reader1= new BufferedReader(new FileReader( "C:\\AgencedeVoyage\\Tpays\\"+liste[i] ));
            String line1;
          	while (( line1= reader1.readLine())!=null  )
          	{ 
          		
          			System.out.println(line1) ;
          		
          	}
          	System.out.println("_________________") ;
          	reader1.close(); 
          	i++;
        }
    	
    }
    void gagnant() throws IOException
    {
    	HashMap<String,Integer > conc =new HashMap<>() ;
    	File initial = new File ("C:\\AgencedeVoyage\\Tousvoyages\\");
    	String liste[] = initial.list();
        int i=0;
        while (i<liste.length)
        {

    		if (liste[i].equals("pays"))
    		{
    			i++;
        	}
    		else
    		{
    			String ch ="." ;
            int j = liste[i].indexOf ( ch ) ;
            String str= liste[i].substring (0,j);
            conc.put(str, nombredevoyage(str));
            str="";
            i++;}
        }
        List<String> mapKeys = new ArrayList<>(conc.keySet());
        List<Integer> mapValues = new ArrayList<>(conc.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap <String, Integer> sortedMap =
           new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = conc.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        conc=sortedMap;
       // System.out.println(conc );
        int taille=conc.size();
        int s=taille;
       // System.out.println(taille);
        ArrayList<String> per = new ArrayList<String>();
        for(Entry<String, Integer> entry : conc.entrySet()) {
           String cle = entry.getKey();
        	taille=taille-1;
         //   Integer valeur = entry.getValue();
        	 if ( taille < s -1 )
        	      per.add(cle);  
        } 
        Random rand = new Random();
        String randomElement = per.get(rand.nextInt(per.size()));
        System.out.println("le gangant est le porteur de num passport : "+randomElement);
          }
        
      }
    
    

    
    

		
    