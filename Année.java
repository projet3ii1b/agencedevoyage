package agencedevoyage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
//import java.io.Reader;
import java.nio.file.Files;
import java.util.Scanner;

   public class Année {
	   protected String annee ;
	   protected String mois;
	   protected String saison;
	   protected File fichier ;
	   protected FileWriter FW;
		protected BufferedWriter BW;
	   public void saisie(String depart,String destination,String date1,String date2) throws IOException
	   {
		   annee=date1.split("-")[2];
		   mois=date1.split("-")[1];
			if (rechercher(this.annee)==0)
			{
				
				Ajoutervoyage( getsaison(this.mois),depart,destination,date1,date2);
			}
			else
				Ajoutervoyage( getsaison(this.mois),depart,destination,date1,date2);
		 
	   }
	   public void Ajoutervoyage(String saison,String depart,String destination,String date1,String date2) throws IOException
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
			String ch=depart+"---"+destination+"---"+date1+"---"+date2+"\n";
			BW.write(ch);
			BW.close();
			FW.close();
			Ajoutvoyagemois(depart,destination,date1,date2);
	   }
	   void Ajoutvoyagemois(String depart,String destination,String date1,String date2) throws IOException
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
			String ch=depart+"---"+destination+"---"+date1+"---"+date2+"\n";
			BW.write(ch);
			BW.close();
			FW.close();
	   }
	   public int rechercher(String s)
		{
		   File dossier=new File("C:\\AgencedeVoyage\\Année\\"+s);
			if (dossier.isDirectory()!=true)
			{
				dossier.mkdir();
			    return 1; }
			else
				return 0;
			
		}
	   void creeannée(String a)
	   {
		   File dossier=new File("C:\\AgencedeVoyage\\Année\\"+a+"\\");
			if (dossier.isDirectory()!=true)
			
				dossier.mkdir();
			
	   }
	   void creesaison(String a , String ses) throws IOException
	   {
		   File fich= new File("C:\\AgencedeVoyage\\Année\\"+a+"\\"+ses+".txt");
			if (!fich.exists())
			{
				fich.createNewFile();
			}
			
	   }
	   public String getsaison(String s)
	   {
		   if ( s.equals("12") || s.equals("1")|| s.equals("2")|| s.equals("01")|| s.equals("02"))
				this.saison="saison1";
		   if ( s.equals("3") || s.equals("4")|| s.equals("5")|| s.equals("03")|| s.equals("04")|| s.equals("05") )
			   this.saison="saison2";
		   if ( s.equals("6") || s.equals("7")|| s.equals("8")|| s.equals("06")|| s.equals("07")|| s.equals("08"))
			   this.saison="saison3";
		   if ( s.equals("9") || s.equals("10")|| s.equals("11")|| s.equals("09") )
			   this.saison="saison4";
		        return (this.saison) ;
		        
	   }
	   void creemois(String a, String m) throws IOException
	   {
		   File fich= new File("C:\\AgencedeVoyage\\Année\\"+a+"/"+m+".txt");
			if (!fich.exists())
			{
				fich.createNewFile();
			}
			
	   }
     public Année()
	{
		File dossier=new File("C:\\AgencedeVoyage\\Année");
		if (dossier.isDirectory()!=true)
			dossier.mkdir();
	}
     public void retardersaison(String annee,String annee2,String mois,String mois2,String date1 ,String date2 ) throws IOException
     {
    	String ses=getsaison(mois);
    	String ses2=getsaison(mois2);
    	if ( ses.equals(ses2) && annee.equals(annee2)&& mois.contentEquals(mois2))
    	{
    	retardermememois(annee,mois,date1,date2);
    	File initial = new File ("C:\\AgencedeVoyage\\Année\\"+annee+"/");
     	String liste[] = initial.list();
         int i=0;
         while (i<liste.length)
 			{
        	 if ( liste[i].equals(ses+".txt") ) 
        	 {
 				File fileToBeModified = new File("C:\\AgencedeVoyage\\Année\\"+annee+"\\"+liste[i]);
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
 				 } else 
 					 i++;
        	 } }
    	else if (annee.equals(annee2)) 
    	{
    		if (!ses.equals(ses2)) {
    		creesaison(annee,ses2);
    		creemois(annee,mois2);
    		retardernvsaison( annee, mois, mois2, date1 , date2);
    	    retardernvmois( annee, mois, mois2, date1 , date2); }
    	    else
    	        	creemois(annee,mois2);
    	    	 retardernvmois( annee, mois, mois2, date1 , date2);
    		     retardermemesaison(annee,ses,date1,date2);
    	    	
    	}
    	else 
    	{
    		creeannée(annee2);
    		creesaison(annee2,ses2);
    		creemois(annee2,mois2);
    		retarderanneesaison(annee, annee2, mois, mois2, date1 , date2);
    	    retarderanneemois(annee, annee2, mois, mois2, date1 , date2);
    	}	
     }
     void retardermemesaison(String annee,String ses,String date1,String date2) throws IOException
     {
     	String filePath="C:\\AgencedeVoyage\\Année\\"+annee+"\\"+ses+".txt";
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
    void retardernvmois(String annee,String mois,String mois2 ,String date1 ,String date2) throws IOException {
  
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
	    supprimervoyagemois(annee,mois,date2);
        
    }
    void supprimervoyagemois(String annee,String mois ,String date) throws IOException {
        
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
	    supprimervoyage(annee,mois,date2);
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
	    supprimervoyagemois(annee,mois,date2);
    }
    void retardernvsaison (String annee,String mois,String mois2,String date1 , String date2) throws IOException
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
	    supprimervoyage(annee,mois,date2);
				
			 
    	
    }

    void supprimervoyage(String annee,String mois ,String date) throws IOException {
        String ses=getsaison( mois);
        
    	File file = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+ses+".txt");
		File temp = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+"temp" +ses+".txt");
	    PrintWriter out = new PrintWriter(new FileWriter(temp));
	    Files.lines(file.toPath()).filter(line -> !line.contains(date)).forEach(out::println);
	    out.flush();
	    out.close();
	    temp.renameTo(file);
	   file.delete();
	    File ch = new File("C:\\AgencedeVoyage\\Année\\"+ annee+"\\"+ses+".txt");
	    temp.renameTo(ch);
    }
    public int nombredevoyages(String s) throws IOException {
    	FileReader fr = new FileReader("C:\\AgencedeVoyage\\Année\\"+s);
    	LineNumberReader lnr = new LineNumberReader(fr);
        int linenumber = 0;
        while (lnr.readLine() != null){
            linenumber++;
            }
        lnr.close(); 
       System.out.println(linenumber);
        return( linenumber );
    }
    
}
