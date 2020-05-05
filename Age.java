package agencedevoyage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;


public class Age//cette classe est consacrée pour le calcul d'ages des voyageurs 
{
	protected int annee;
	protected HashMap<String,Integer> HMapAge;//HashMap qui va contenir les numero de passeport des voyageurs comme clé et leurs ages comme valeurs 
	public Age()//constructeur permettant l'instanciation du Hashmap HMapAge
	{
		HMapAge=new HashMap<String, Integer>();
		
	}
	public int getAnneeCourante()//retourne l'annee courante a l'aide de la classe Calendar 
	{
        Calendar c = Calendar.getInstance();
        annee = c.get(Calendar.YEAR);//retourne l'annee courante 
        return annee;
    }
	public int anneenaissance(String numpass) throws IOException//retourne le numero de passeport de chaque voyageur a partir de son fichier de donnees enregistrés dans le dossier Tousvoyages
	{
		File f=new File("C:\\AgencedeVoyage\\Tousvoyages\\"+numpass+".txt");
		BufferedReader reader= new BufferedReader(new FileReader(f));
		String[] donnees=reader.readLine().split("---");
		String[] datedenaissance=donnees[4].split("-");//l'indice 4 refert au champ 4 saisie dans le fichier et qui represente la date de naissance
		int anneenaissance=Integer.parseInt(datedenaissance[2]);//l'indice 2 refert a l'année de naissance
		reader.close();
		return anneenaissance;
	}
	public int calculAge(String nump) throws IOException//permet le calcul d'age d'un voyageur 
	{
		return getAnneeCourante()-anneenaissance(nump);
	}
	public void tableauAge () throws IOException//retourne une HashMap contenant les numero de passeport des voyageurs comme clé et leurs ages comme valeurs 
	{
		File f=new File("C:\\AgencedeVoyage\\Tousvoyages\\");
		String[] tabClients=f.list();
		for (int i=0;i<tabClients.length;i++)
		{
			int age=calculAge(tabClients[i].substring(0,tabClients[i].indexOf(".")));
			HMapAge.put(tabClients[i].substring(0,tabClients[i].indexOf(".")), age);		
		}
		System.out.println(HMapAge);
		
	}
	public void trancheAge () throws IOException//permet la classification des voyageurs selon leur ageset l'affichage de la tranche d'age la plus frequente  
	{
		tableauAge();
		int comptinf18=0;
		int compt1935=0;
		int compt3660=0;
		int comptsup60=0;
		Object[] tab=HMapAge.values().toArray();
		for(int i=0;i<HMapAge.values().size();i++)//calcul de nombre de voyageurs pour chaque tranche d'age 
		{
			if ((int)tab[i]<19)
				comptinf18++;
			else if (((int)tab[i]>18)&&((int)tab[i]<36))
				compt1935++;
			else if (((int)tab[i]>35)&&((int)tab[i]<61))
				compt3660++;
			else
				comptsup60++;	
		}
		Vector<Integer> v =new Vector<Integer>();
		v.add(comptinf18);
		v.add(compt1935);
		v.add(compt3660);
		v.add(comptsup60);
		int indice=0;
		int maxVal=comptinf18;
		for(int i = 1; i <v.size(); i++)
		{
	         if((int)v.get(i) > maxVal)
	           { 
	        	 maxVal =(int)v.get(i);
	        	 indice=i;
	           }
	         
	    }
		if (indice==0)
			System.out.println("la tranche d'age la plus frequente est : <18 ");
		else if (indice==1)
			System.out.println("la tranche d'age la plus frequente est : 18-35 ");
		else if (indice==2)
			System.out.println("la tranche d'age la plus frequente est : 36-60 ");
		else
			System.out.println("la tranche d'age la plus frequente est : >60 ");
		
	}
	
	
}
