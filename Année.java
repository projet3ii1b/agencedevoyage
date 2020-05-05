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

   public class Année //cette classe sert pour repartir les voyageurs selon l'annee trouve dans la date de depart qu'ils ont saisi 
   {
	   protected String annee ;
	   protected String mois;
	   protected String saison;
	   protected File fichier ;
	   protected FileWriter FW;
	   protected BufferedWriter BW;
	   public Année()//sert pour creer le dossier Année 
	   {
		   File dossierAnnee=new File("C:\\AgencedeVoyage\\Année");
		   if (dossierAnnee.isDirectory()!=true)
					dossierAnnee.mkdir();
		}
		public void setAnnee(String an)
		{
			annee=an;
		}
		public void setMois(String m)
		{
			mois=m;
		}
	   public void saisieAnnee(String depart,String destination,String date1,String date2) throws IOException//sert pour saisir les fichiers correspondant a une année 
	   {
		   annee=date1.split("-")[2];//extraire l'annee du date de depart 
		   mois=date1.split("-")[1];//extraire le mois du date de depart
			if (rechercher(this.annee)==0)
			{
				
				Ajoutervoyage( getsaison(this.mois),depart,destination,date1,date2);
			}
			else
				Ajoutervoyage( getsaison(this.mois),depart,destination,date1,date2);
		 
	   }
	   public void Ajoutervoyage(String saison,String depart,String destination,String datedepart,String dateretour) throws IOException//sert pour ajouter un voyage pour un saison et un mois specifique 
	   {
		    File fichier= new File("C:\\AgencedeVoyage\\Année\\"+this.annee+"\\"+saison+".txt");
			if (!fichier.exists())
			{
				fichier.createNewFile();
			}
			FileWriter FW;
			BufferedWriter BW;
			FW=new FileWriter(fichier,true);
			BW=new BufferedWriter(FW);
			String ch=depart+"---"+destination+"---"+datedepart+"---"+dateretour+"\n";
			BW.write(ch);
			BW.close();
			FW.close();
			Ajoutvoyagemois(depart,destination,datedepart,dateretour);
	   }
	   void Ajoutvoyagemois(String depart,String destination,String datedepart,String dateretour) throws IOException//
	   {
		   File fich= new File("C:\\AgencedeVoyage\\Année\\"+this.annee+"\\"+this.mois+".txt");
			if (!fich.exists())
			{
				fich.createNewFile();
			}
			FileWriter FW;
			BufferedWriter BW;
			FW=new FileWriter(fich,true);
			BW=new BufferedWriter(FW);
			String ch=depart+"---"+destination+"---"+datedepart+"---"+dateretour+"\n";
			BW.write(ch);
			BW.close();
			FW.close();
	   }
	   public int rechercher(String s)//rechercher si une année existe si non creer un nouveau dossier pour cette année 
		{
		   File dossier=new File("C:\\AgencedeVoyage\\Année\\"+s);
			if (dossier.isDirectory()!=true)
			{
				dossier.mkdir();
			    return 1; }
			else
				return 0;
			
		}
	   void creeannée(String a)//sert pour creer un dossier d'une nouvelle année 
	   {
		   File dossier=new File("C:\\AgencedeVoyage\\Année\\"+a+"\\");
			if (dossier.isDirectory()!=true)
			
				dossier.mkdir();
			
	   }
	   void creesaison(String a , String ses) throws IOException//sert pour creer un fichier d'un saison pour une annee donnée
	   {
		   File fich= new File("C:\\AgencedeVoyage\\Année\\"+a+"\\"+ses+".txt");
			if (!fich.exists())
			{
				fich.createNewFile();
			}
			
	   }
	   void creemois(String a, String m) throws IOException//sert pour creer un fichier d'un mois pour une annee donnée
	   {
		   File fich= new File("C:\\AgencedeVoyage\\Année\\"+a+"/"+m+".txt");
			if (!fich.exists())
			{
				fich.createNewFile();
			}
			
	   }
	   public String getsaison(String mois)//permet de determiner la saison selon le mois
	   {
		   if ( mois.equals("12") || mois.equals("1")|| mois.equals("2")|| mois.equals("01")|| mois.equals("02"))
				this.saison="saison1";
		   if ( mois.equals("3") || mois.equals("4")|| mois.equals("5")|| mois.equals("03")|| mois.equals("04")|| mois.equals("05") )
			   this.saison="saison2";
		   if ( mois.equals("6") || mois.equals("7")|| mois.equals("8")|| mois.equals("06")|| mois.equals("07")|| mois.equals("08"))
			   this.saison="saison3";
		   if ( mois.equals("9") || mois.equals("10")|| mois.equals("11")|| mois.equals("09") )
			   this.saison="saison4";
		        return (this.saison) ;
		        
	   }
     public void retardersaison(String anneedate1,String anneedate2,String moisdate1,String moisdate2,String date1 ,String date2 ) throws IOException//sert pour effectuer les modifications a un fichier saison et mois si on change de saison et de mois lorsqu'on a un retard  
     {
    	String ses=getsaison(moisdate1);
    	String ses2=getsaison(moisdate2);
    	if ( ses.equals(ses2) && anneedate1.equals(anneedate2)&& moisdate1.contentEquals(moisdate2))
    	{
    	retardermememois(anneedate1,moisdate1,date1,date2);
    	File initial = new File ("C:\\AgencedeVoyage\\Année\\"+anneedate1+"\\");
     	String listeSM[] = initial.list();//liste des saisons et mois ou il y a des voyages pour une année 
         int i=0;
         while (i<listeSM.length)
 			{
        	 if ( listeSM[i].equals(ses+".txt") )
        	 {
        		    File fileToBeModified = new File("C:\\AgencedeVoyage\\Année\\"+anneedate1+"\\"+listeSM[i]);
        	   		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToBeModified)));
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
        	   		fileToBeModified.delete();
        	        File fich=new File("C:\\AgencedeVoyage\\Année\\"+anneedate1+"\\"+listeSM[i]);
        	        if (!fich.exists())
        	        {
        	        	   fich.createNewFile();
        	        }
        	        BufferedWriter out = new BufferedWriter(new FileWriter(fich));
        	        out.write(sb.toString());
        	        out.close();
        	        i++;
 				 } 
        	  else 
 			 i++;
        	 }
         }
    	else if (anneedate1.equals(anneedate2)) 
    	{
    		if (!ses.equals(ses2)) {
    		creesaison(anneedate1,ses2);
    		creemois(anneedate1,moisdate2);
    		retardernvsaison( anneedate1, moisdate1, moisdate2, date1 , date2);
    	    retardernvmois( anneedate1, moisdate1, moisdate2, date1 , date2); }
    	    else
    	    {
    	         creemois(anneedate1,moisdate2);
    	    	 retardernvmois( anneedate1, moisdate1, moisdate2, date1 , date2);
    		     retardermemesaison(anneedate1,ses,date1,date2);}
    	    
    	}
    	else 
    	{
    		creeannée(anneedate2);
    		creesaison(anneedate2,ses2);
    		creemois(anneedate2,moisdate2);
    		retarderanneesaison(anneedate1, anneedate2, moisdate1, moisdate2, date1 , date2);
    	    retarderanneemois(anneedate1, anneedate2, moisdate1, moisdate2, date1 , date2);
    	}	
     }
     void retardermemesaison(String anneedate1,String ses,String date1,String date2) throws IOException//sert pour effectuer les modifications a un fichier saison si on reste dans le mm saison lorsqu'on fait le retard de la date  
     {
    	    File filePath=new File("C:\\AgencedeVoyage\\Année\\"+anneedate1+"\\"+ses+".txt");
	   		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
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
	   		filePath.delete();
	        File fich=new File("C:\\AgencedeVoyage\\Année\\"+anneedate1+"\\"+ses+".txt");
	        if (!fich.exists())
	        {
	        	   fich.createNewFile();
	        }
	        BufferedWriter out = new BufferedWriter(new FileWriter(fich));
	        out.write(sb.toString());
	        out.close();
     }
     void retardernvmois(String annee,String mois,String mois2 ,String date1 ,String date2) throws IOException//sert pour effectuer les modifications a un fichier mois si on change de mois lorsqu'on a un retard  
     {
   
     	String filePath="C:\\AgencedeVoyage\\Année\\"+annee+"\\"+mois+".txt";
         String toDir="C:\\AgencedeVoyage\\Année\\"+annee+"\\"+mois2+".txt";
         File fileToBeModified = new File(filePath);
      	String oldContent = "";
         FileWriter FP;
  		BufferedWriter BP;
  		File file2=new File(toDir);
 		FP=new FileWriter(file2,true);
 		BP=new BufferedWriter(FP);
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
           BufferedReader reader1= new BufferedReader(new FileReader( filePath ));
           String line1;
         	while (( line1= reader1.readLine())!=null  )
         	{ 
         		if ( line1.contains(date2) )
         		{
         			System.out.println(line1);
         			BP.write(line1);
         		}
         	}
         	BP.close();
         	reader1.close();
 	    supprimervoyagemois2(annee,mois,date2);
         
     }
    void retarderanneesaison(String annee,String annee2,String mois,String mois2,String date1 ,String date2) throws IOException
    {
    	String ses=getsaison(mois);
    	String ses2=getsaison(mois2);
    	String filePath="C:\\AgencedeVoyage\\Année\\"+annee+"\\"+ses+".txt";
        String toDir="C:\\AgencedeVoyage\\Année\\"+annee2+"\\"+ses2+".txt";
        File fileToBeModified = new File(filePath);
     	String oldContent = "";
        FileWriter FP;
 		BufferedWriter BP;
 		File file2=new File(toDir);
		FP=new FileWriter(file2,true);
		BP=new BufferedWriter(FP);
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
          BufferedReader reader1= new BufferedReader(new FileReader( filePath ));
          String line1;
        	while (( line1= reader1.readLine())!=null  )
        	{ 
        		if ( line1.contains(date2) )
        		{
        			System.out.println(line1);
        			BP.write(line1);
        		}
        	}
        	BP.close();
        	reader1.close();
	    supprimervoyagesaison2(annee,mois,date2);
    }
    void retardermememois(String annee,String mois,String date1 ,String date2) throws IOException
    {
    	String filePath="C:\\AgencedeVoyage\\Année\\"+annee+"\\"+mois+".txt";
    	String oldContent = "";
    	BufferedReader reader = null;
		FileWriter writer = null;
	    File fileToBeModified = new File(filePath);
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
    }
    void retarderanneemois(String annee, String annee2,String mois,String mois2,String date1 ,String date2) throws IOException
    {
    	String filePath="C:\\AgencedeVoyage\\Année\\"+annee+"\\"+mois+".txt";
        String toDir="C:\\AgencedeVoyage\\Année\\"+annee2+"\\"+mois2+".txt";
        File fileToBeModified = new File(filePath);
     	String oldContent = "";
        FileWriter FP;
 		BufferedWriter BP;
 		File file2=new File(toDir);
		FP=new FileWriter(file2,true);
		BP=new BufferedWriter(FP);
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
          BufferedReader reader1= new BufferedReader(new FileReader( filePath ));
          String line1;
        	while (( line1= reader1.readLine())!=null  )
        	{ 
        		if ( line1.contains(date2) )
        		{
        			System.out.println(line1);
        			BP.write(line1+ System.lineSeparator());
        		}
        	}
        	BP.close();
        	reader1.close();
	    supprimervoyagemois2(annee,mois,date2);
    }
    void retardernvsaison (String annee,String mois,String mois2,String date1 , String date2) throws IOException//sert pour effectuer les modifications a un fichier saison si on change de saison lorsqu'on a un retard  
    {
    	String ses=getsaison(mois);
    	String ses2=getsaison(mois2);
    	String filePath="C:\\AgencedeVoyage\\Année\\"+annee+"\\"+ses+".txt";
        String toDir="C:\\AgencedeVoyage\\Année\\"+annee+"\\"+ses2+".txt";
        File fileToBeModified = new File(filePath);
     	String oldContent = "";
        FileWriter FP;
 		BufferedWriter BP;
 		File file2=new File(toDir);
		FP=new FileWriter(file2,true);
		BP=new BufferedWriter(FP);
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
          BufferedReader reader1= new BufferedReader(new FileReader( filePath ));
          String line1;
        	while (( line1= reader1.readLine())!=null  )
        	{ 
        		if ( line1.contains(date2) )
        		{
        			System.out.println(line1);
        			BP.write(line1);
        		}
        	}
        	BP.close();
        	reader1.close();
	    supprimervoyagesaison2(annee,mois,date2);	
    }
  void supprimervoyagemois(String annee,String mois ,String datededepart,String datederetour) throws IOException //sert pour supprimer un voyage du fichier mois pour une date d'aller et de retour bien specifique
  {
        
    	File file = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+mois+".txt");
		File temp = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+"temp" +mois+".txt");
	    PrintWriter out = new PrintWriter(new FileWriter(temp));
	    Files.lines(file.toPath()).filter(line -> (!line.contains(datededepart)&&!line.contains(datederetour))).forEach(out::println);
	    out.flush();
	    out.close();
	    temp.renameTo(file);
	   file.delete();
	    File ch = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+mois+".txt");
	    temp.renameTo(ch);
    }
  void supprimervoyagemois2(String annee,String mois ,String date) throws IOException //sert pour supprimer un voyage du fichier mois pour une date que ce soit pour aller ou retour 
  {
      
  	File file = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+mois+".txt");
		File temp = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+"temp" +mois+".txt");
	    PrintWriter out = new PrintWriter(new FileWriter(temp));
	    Files.lines(file.toPath()).filter(line -> !line.contains(date)).forEach(out::println);
	    out.flush();
	    out.close();
	    temp.renameTo(file);
	   file.delete();
	    File ch = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+mois+".txt");
	    temp.renameTo(ch);
  }
  void supprimervoyagesaison(String annee,String mois ,String datededepart,String datederetour) throws IOException //sert pour supprimer un voyage du fichier saison pour une date de depart et date de retour determiné
  {
      String ses=getsaison( mois);
  	File file = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+ses+".txt");
		File temp = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+"temp" +ses+".txt");
	    PrintWriter out = new PrintWriter(new FileWriter(temp));
	    Files.lines(file.toPath()).filter(line ->( !line.contains(datededepart)&&!line.contains(datederetour))).forEach(out::println);
	    out.flush();
	    out.close();
	    temp.renameTo(file);
	   file.delete();
	    File ch = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+ses+".txt");
	    temp.renameTo(ch);
  }
    void supprimervoyagesaison2(String annee,String mois ,String date) throws IOException //sert pour supprimer un voyage du fichier saison pour une date que ce soit pour aller ou retour 
    {
        String ses=getsaison( mois);
    	File file = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+ses+".txt");
		File temp = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+"temp" +ses+".txt");
	    PrintWriter out = new PrintWriter(new FileWriter(temp));
	    Files.lines(file.toPath()).filter(line ->( !line.contains(date))).forEach(out::println);
	    out.flush();
	    out.close();
	    temp.renameTo(file);
	   file.delete();
	    File ch = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+ses+".txt");
	    temp.renameTo(ch);
    }
    public int nombredevoyages(String s) throws IOException //sert pour determiner le nombre de voyages si on veut acceder a un mois ou saison specifique
    {
    	FileReader fr = new FileReader("C:\\AgencedeVoyage\\Année\\"+s);
    	LineNumberReader lnr = new LineNumberReader(fr);
        int linenumber = 0;
        while (lnr.readLine() != null){
            linenumber++;
            }
        lnr.close(); 
        return( linenumber );
    }
    
}
