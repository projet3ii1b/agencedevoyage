package agencedevoyage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


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
	public Touslesvoyages() throws IOException//constructeur permettant de creer le dossier d'Agencedevoyage,de Tousvoyages,et d'annee
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
	public void creerNouvFichier() throws IOException //permet de creer un nouv fichier d'un voyageur s'il n'existe pas 
	{
		fichier= new File("C:\\AgencedeVoyage\\Tousvoyages\\"+this.numpasseport+".txt");
		if (!fichier.exists())
		{
			fichier.createNewFile();
		}
	}
	public void SaisieDonnes()//permet la saisie des donnees d'un voyageur
	{
		sc = new Scanner(System.in);
		System.out.println("Donner votre nom");
		nom=sc.nextLine();
		System.out.println("Donner votre prenom");
		prenom=sc.nextLine();
		System.out.println("Donner votre date de naissance sous la forme (jj-mm-aaaa)");
		datedenaissance=sc.nextLine();
		System.out.println("Donner votre numero de CIN");
		cin=sc.nextLine();
		
			
	}	
	public void SaisieVoyagesFaites() throws IOException//permet la saisie d'un nouveau voyage
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
		a.saisieAnnee(this.depart,this.destination,this.datededepart,this.datederetour);
		FW.close();
		BW.close();
	}
	public void fichierExistant()//permet d'attribuer le fichier de donnees d'un voyageur existant deja
	{
		fichier= new File("C:\\AgencedeVoyage\\Tousvoyages\\"+this.numpasseport+".txt");	
	}
	public void prendreDonnees(String pass) throws IOException//permet d'extraire les donnees personnels d'un voyageur 
	{
		BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+pass+".txt"));
		String[] listeDonnees=reader.readLine().split("---");
		nom=listeDonnees[0];
		prenom=listeDonnees[1];
		datedenaissance=listeDonnees[2];
		cin=listeDonnees[3];
		reader.close();
	}
	public void AjouterDonnes() throws IOException//permet d'ajouter les donnees d'un voyageur dans son fichier  
	{
		FW=new FileWriter(fichier,true);
		BW=new BufferedWriter(FW);
		String ch=this.numpasseport+"---"+this.nom+"---"+this.prenom+"---"+this.cin+"---"+this.datedenaissance+"\n";
		BW.write(ch);
		BW.close();
		FW.close();	
	}
	public void AjouterUnVoyage() throws IOException //permet d'ajouter un voyage a un fichier d'un voyageur existant dans le dossier Touslesvoyages
	{
		FW=new FileWriter(fichier,true);
		BW=new BufferedWriter(FW);
		String ch=this.depart+"---"+this.destination+"---"+this.datededepart+"---"+this.datederetour+"\n";
		BW.write(ch);
		BW.close();
		FW.close();	
	}
	public void AjouterVoyageur() throws IOException//permet d'ajouter un voyageur tout en verifiant d'il existe ou pas auparavant
	//et en lui donnant la liberté de choisir s'il veut effectuer un voyage seul ou en groupe
	{
		a = new Année();
		paysfolder();
		System.out.println("Donner votre numero de passeport : ");
		sc=new Scanner(System.in);
		numpasseport=sc.nextLine();
		System.out.println("Si vous voulez voyager seul taper 0 , Si vous voulez voyager en groupe taper 1 ");
		int choix=sc.nextInt();
		if (rechercher(this.numpasseport)==0)//si le voyageur n'a pas effectué un voyage auparavant au sein de l'agence
		{
				SaisieDonnes();
				creerNouvFichier();
				AjouterDonnes();
				SaisieVoyagesFaites();
				AjouterUnVoyage();
				Ajoutvoyagepays();
				NomDestination=nomDestination();
				if (choix==0)//si il a choisi de voyager seul
				{
					VoyageSeul VS=new VoyageSeul(numpasseport);
					VS.creerNouvFichier(numpasseport);
					VS.AjouterDonnes(numpasseport);
				}
				else if (choix==1)//si il a choisi de voyager en groupe
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
		else if (rechercher(this.numpasseport)==1)//si le voyageur a effectué deja un voyage auparavant au sein de l'agence 
		{
			fichierExistant();
			prendreDonnees(numpasseport);//extraire tous les donnees pour ne pas les saisir une nouvelle fois 
			afficherVoyagesFaites(numpasseport);
			SaisieVoyagesFaites();
			AjouterUnVoyage();	
			Ajoutvoyagepays();
			NomDestination=nomDestination();
			if (choix==0)//si il choisi cette fois d'effectuer un voyage seul
			{
				VoyageSeul VS=new VoyageSeul(numpasseport);
				VS.creerNouvFichier(numpasseport);
				VS.AjouterDonnes(numpasseport);
				
			}
			else if (choix==1)//si il choisi cette fois d'effectuer un voyage en groupe
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
	public String nomDestination()//sert lors de la creation du dossier de voyage en groupe 
	{
		NomDestination=depart+'_'+destination+'_'+datededepart+'_'+datederetour;
		return NomDestination;
	}
	
	public String getNumpasseport()//retourne le numero de passeport d'un voyageur 
	{ 
		return ( numpasseport );
	}
	public String getNom()//retourne le nom d'un voyageur 
	{
		return (nom);
	}
	public String getPrenom()//retourne le prenom d'un voyageur 
	{ 
		return ( prenom );
	}
	public String getCin()//retourne le numero de cin d'un voyageur 
	{ 
		return ( cin );
	}
	public String getDatedenaissance()//retourne la date de naissance d'un voyageur 
	{
		return ( datedenaissance);
	}
	
	public String getDepart()//retourne la place de depart d'un voyageur 
	{ 
		return ( depart );
	}
	public String getDateretour()//retourne la date de retour d'un voyageur 
	{ 
		return ( datederetour );
	}
	public String getDatedepart()//retourne la date de depart d'un voyageur 
	{ 
		return ( datededepart );
	}
	public String getDestination()//retourne la destination d'un voyageur 
	{ 
		return ( destination );
	}
	
	public void finalize()//destructeur dans lequel on va fermer le Scanner 
	{
		sc.close();;
	}
	public int rechercher(String nump)//rechercher un voyageur dans Touslesvoyages a partir de son numero de passeport 
	{
		File f=new File("C:\\AgencedeVoyage\\Tousvoyages\\"+nump+".txt");
		if (f.exists())
			return 1;
		else
			return 0;
		
	}
    public void afficherDonneesetVoyages(String pass) throws IOException//afficher tout le contenu d'un fichier d'un voyageur existant dans le dossier Touslesvoyages
    {
    	BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+pass+".txt"));
    	String line;
    	while (( line= reader.readLine())!=null  )
    	{ 
    		System.out.println(line) ;
    	}
    	reader.close();
    }
    public void afficherVoyagesFaitesAC() throws IOException//afficher les voyages faites par un voyageur dont le numero de passeport va etre saisie au clavier
    {
    	System.out.println("Donner le numéro de passeport du voyageur que vous voulez affichez ses voyages ");
    	sc=new Scanner(System.in);
    	String nump=sc.nextLine();
    	BufferedReader reader= new BufferedReader(new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+nump+".txt"));
    	String line;
    	System.out.println("Les voyages effectués par "+nump+" sont : ");
    	while (( line= reader.readLine())!=null)
    	{
    		if (!line.contains(nump))
    		{
    			System.out.println(line);
    		}
    		
    	}
    	reader.close();
    }
    public void afficherVoyagesFaites(String pass) throws IOException//permet d'afficher les voyages faites par un voyageur specifique 
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
    public void afficherTouslesVoyages() throws IOException//permet d'afficher tous les voyages effectués au sein de l'agence
	{
		File dossierTouslesVoyageurs =new File("C:\\AgencedeVoyage\\Tousvoyages\\");
		String[] listeVoyageurs=dossierTouslesVoyageurs.list();
		for (int i=0;i<listeVoyageurs.length;i++)
		{
			File f=new File("C:\\AgencedeVoyage\\Tousvoyages\\"+listeVoyageurs[i]);
	    	BufferedReader reader= new BufferedReader(new FileReader(f));
	    	String line;
	    	int x=listeVoyageurs[i].indexOf('.');
	    	
	    	System.out.println("les voyages effectués par le voyageur "+listeVoyageurs[i].substring(0,x)+" sont ");
	    	while (( line= reader.readLine())!=null  )
	    	{ 
	    		if (!line.contains(listeVoyageurs[i].substring(0,x)))
	    		System.out.println(line) ;
	    	}
	    	reader.close();
		}
		
	}  
  public int nombredevoyage(String pass) throws IOException//permet de calculer le nombre de voyages pour un client 
    {
             FileReader fr = new FileReader("C:\\AgencedeVoyage\\Tousvoyages\\"+pass+".txt");
             LineNumberReader lnr = new LineNumberReader(fr);
             int linenumber = 0;

              while (lnr.readLine() != null){
                 linenumber++;
                 }
              lnr.close(); 
                 return( linenumber-1  );
    }
    public void paysfolder() //permet de creer le dossier pays conçu pour faciliter la gestion des voyages vers des pays specifiques 
    {
		File dossierpays=new File("C:\\AgencedeVoyage\\pays\\");
		if (dossierpays.isDirectory()!=true)
		{
			dossierpays.mkdir();
		}
		
	}
    void afficheVoyagepaysSP(String nompays) throws IOException//affiche Tous les voyages vers un pays specifique 
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
    public void afficheVoyagesspaysTotale() throws IOException//affiches Tous les voyages existants dans le dossier pays
	{
		File dossierPays=new File("C:\\AgencedeVoyage\\pays\\");
		String[] tab=dossierPays.list();
		for(int i=0;i<tab.length;i++)
		{
			afficheVoyagepaysSP(tab[i]);
		}
	}
    void Ajoutvoyagepays() throws IOException//permet d'ajouter le voyage courant au fichier de pays qui lui est approprié 
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
       void supprimervoyagepays(String pays1,String date) throws IOException
       {
       	File fichierpays = new File("C:\\AgencedeVoyage\\pays\\"+pays1+".txt");
   		File temp = new File("C:\\AgencedeVoyage\\pays\\"+"temp"+pays1+".txt");//fichier temporaire
   		//ecrire toutes les lignes deja existants auparavant dans le fichier pays sauf celui qui contient la date qu'on veut supprimer dans le fichier temp
   	    PrintWriter out = new PrintWriter(new FileWriter(temp));
   	    Files.lines(fichierpays.toPath()).filter(line -> !line.contains(date)).forEach(out::println);
   	    out.flush();
   	    out.close();
   	    temp.renameTo(fichierpays);
   	    //effacer l'ancien fichier du pays et le remplacer par le fichier temp et ensuite renommer temp par le nom du fichier deja supprimé
   	    fichierpays.delete();
   	    File nomfichierorigine = new File("C:\\AgencedeVoyage\\pays\\"+pays1+".txt");
   	    temp.renameTo(nomfichierorigine);
       }
       void retarderpays(String date1 ,String date2) throws IOException//permet de retarder une date dans un fichier d'un pays 
       
       {
       	File initial = new File ("C:\\AgencedeVoyage\\pays\\");
       	String liste[] = initial.list();
        int i=0;
        while (i<liste.length)
   		{
   				File fichieraModifier = new File("C:\\AgencedeVoyage\\pays\\"+liste[i]);
   		     	String ancContenu = "";
   				BufferedReader reader = null;
   				FileWriter writer = null;
   				reader = new BufferedReader(new FileReader(fichieraModifier));
   				String line = reader.readLine();
   				while (line != null) 
   		        {
   					ancContenu = ancContenu + line + System.lineSeparator();
   		            line = reader.readLine();
   		        }
   				String nouvContenu = ancContenu.replaceAll(date1, date2);//permet de remplacer la date1 par la nouvelle date 
   				writer = new FileWriter(fichieraModifier);
   				writer.write(nouvContenu);
   				reader.close();
   	            writer.close();
   	            i++;
   			}
        }
       

    void supprimervoyage() throws IOException//permet de supprimer un voyage de tous les fichiers du dossier Agencedevoyage
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
      	a.supprimervoyagesaison(tab[2], tab[1], datededepart,datederetour);//supprimer un voyage du fichier saison qui lui est approprié 
    	a.supprimervoyagemois(tab[2], tab[1], datededepart,datederetour);//supprimer un voyage du fichier mois qui lui est approprié 
    	VG.SupprimerVoyageurEnGroupe(depart, datededepart,destination, datederetour,numpasseport);//supprimer un voyage du fichier du groupe qui lui est approprié 
    	supprimervoyagepays(destination.substring(0,destination.indexOf('-')),datededepart);
    	//supprime un voyage du fichier du dossier Touslesvoyages
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
     }
   
   public void RetarderparSaisieNouvDate() throws IOException//permet de retarder une date saisie au clavier par une autre date choisie par l'utilisateur 
    {	
    	sc=new Scanner(System.in);
    	System.out.println("donner la date a retarder ");
    	String date1=sc.nextLine();
    	String [] tabdate1=date1.split("-");
    	String annee=tabdate1[2];
    	String mois1=tabdate1[1];
    	System.out.println("donner la nouvelle date voulu");
    	String date2=sc.nextLine();
    	String [] tabdate2=date2.split("-");
    	String annee2=tabdate2[2];
    	String mois2=tabdate2[1];
    	Voyageengroupe VG=new Voyageengroupe();
    	VG.retarderGroupe(date1, date2);//retarder la date dans le fichier de voyage en groupe qui lui est approprié 
    	retarderpays(date1,date2);//retarder la date dans le fichier de pays qui lui est approprié 
    	a.retardersaison(annee,annee2,mois1,mois2,date1,date2);//retarder la date dans le fichier de saison qui lui est approprié 
    	File dossierTousvoyages = new File ("C:\\AgencedeVoyage\\Tousvoyages\\");
    	String listeClients[] = dossierTousvoyages.list();
        int i=0;
        while (i<listeClients.length)//effectuer les modifications dans les fichiers du dossier Tousvoyages
        {
        	File fichieraModifier = new File("C:\\AgencedeVoyage\\Tousvoyages\\"+listeClients[i]);
		    String ancContenu = "";
			BufferedReader reader = null;
			FileWriter writer = null;
			reader = new BufferedReader(new FileReader(fichieraModifier));
			String line = reader.readLine();
			while (line != null) 
			{
				ancContenu =ancContenu + line + System.lineSeparator();
		        line = reader.readLine();
		    }
			String nouvContenu = ancContenu.replaceAll(date1, date2);
			writer = new FileWriter(fichieraModifier);
			writer.write( nouvContenu);
			reader.close();
			writer.close();
			i++;
			}		
     File f1= new File ("C:\\AgencedeVoyage\\Tousvoyages\\");
     String l1[] = f1.list();
     int j=0;
    while (j<l1.length) 
    {
    	BufferedReader reader1= new BufferedReader(new FileReader( "C:\\AgencedeVoyage\\Tousvoyages\\"+l1[j] ));
    	String line1;
    	while (( line1= reader1.readLine())!=null  )
    	{ 
    		if ( line1.contains(date2))
    			System.out.println(line1);
    	}
    	reader1.close(); 
  	
  	j++;
    }
    } 
  public void RetardparNbJour() throws IOException//permet d'effectuer le retard d'
   {
   	sc=new Scanner(System.in);
   	System.out.println("donner la date a retarder ");
   	String date1=sc.nextLine();
   	System.out.println("donner le nombre de jour :");
   	int nbjourRetard=sc.nextInt();
   	SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");//specifier la date format qui va avec ce qu'on a saisi
   	Calendar c = Calendar.getInstance();
   	try
   	{
   		 c.setTime(sdf.parse(date1));
   	}
   	catch(ParseException e)
   	{
   			e.printStackTrace();
   	}
   	c.add(Calendar.DAY_OF_MONTH, nbjourRetard); 
   	String date2 = sdf.format(c.getTime());  
   	System.out.println("nouvelle date: "+date2);
   	String [] tab1=date1.split("-");
   	String annee=tab1[2];
   	String mois1=tab1[1];
   	String [] tab2=date2.split("-");
   	String annee2=tab2[2];
   	String mois2=tab2[1];
   	Voyageengroupe VG=new Voyageengroupe();
   	VG.retarderGroupe(date1, date2);
   	retarderpays(date1,date2);
   	a.retardersaison(annee,annee2,mois1,mois2,date1,date2);
   	File dossierTousvoyages = new File ("C:\\AgencedeVoyage\\Tousvoyages\\");
   	String listeClients[] = dossierTousvoyages.list();
    int i=0;
    while (i<listeClients.length)
	{
    	File fichiervoyageur=new File("C:\\AgencedeVoyage\\Tousvoyages\\"+listeClients[i]);
   		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fichiervoyageur)));
   		String line;
   		StringBuffer sb = new StringBuffer();
    	while ((line = reader.readLine()) != null) 
        {
   		if (!line.contains(date1))
   		{
   				sb.append(line+"\n");
   				
   		}
   		else 
        {
   			String ch=line.replaceAll(date1, date2);
   			sb.append(ch+"\n");
   			
        }
   		}
   		reader.close();
        fichiervoyageur.delete();
        File fich=new File("C:\\AgencedeVoyage\\Tousvoyages\\"+listeClients[i]);
        if (!fich.exists())
        {
        	   fich.createNewFile();
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(fich));
        out.write(sb.toString());
        out.close();
        i++;
		}	
     //partie pour afficher les lignes modifiés 
       File f1= new File ("C:\\AgencedeVoyage\\Tousvoyages\\");
       String l1[] = f1.list();
       int j=0;
       while (j<l1.length) 
       {
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
        	String ch ="." ;
            int j = liste[i].indexOf ( ch ) ;
            String str= liste[i].substring (0,j);
            conc.put(str, nombredevoyage(str));
            str="";
            i++;
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
    

    
    

		
    