package projet3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import java.util.HashMap;
import java.util.Vector;


public class CalculDate {
	protected int annee;
	protected HashMap h;
	public CalculDate()
	{
		h=new HashMap();
		
	}
	public int getAnneeCourante(){
        Calendar c = Calendar.getInstance();
        annee = c.get(Calendar.YEAR);
        return annee;
    }
	public int anneenaissance(String s) throws IOException
	{
		File f=new File("C:\\Agence\\TouslesVoyageurs\\"+s+"\\Donnees.txt");
		BufferedReader reader= new BufferedReader(new FileReader(f));
		String[] tab=reader.readLine().split("---");
		
		String[] tab2=tab[3].split("/");
		int anneenaissance=Integer.parseInt(tab2[0]);
		return anneenaissance;
	}
	public int calculAge(String s) throws IOException
	{
		return getAnneeCourante()-anneenaissance(s);
	}
	public void tableauAge () throws IOException
	{
		File f=new File("C:\\Agence\\TouslesVoyageurs");
		String[] tab=f.list();
		for (int i=0;i<tab.length;i++)
		{
			int age=calculAge(tab[i]);
			h.put(tab[i], age);		
		}
		System.out.println(h);
		
	}
	public void trancheAge ()
	{
		int comptinf18=0;
		int compt1935=0;
		int compt3660=0;
		int comptsup60=0;
		Object[] tab=h.values().toArray();
		for(int i=0;i<h.values().size();i++)
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
		Vector v =new Vector();
		v.add(comptinf18);
		v.add(compt1935);
		v.add(compt3660);
		v.add(comptsup60);
		int indice=0;
		int maxVal=comptinf18;
		for(int i = 1; i <v.size(); i++){
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
		else if (indice==1)
			System.out.println("la tranche d'age la plus frequente est : 36-60 ");
		else
			System.out.println("la tranche d'age la plus frequente est : >60 ");
		
	}
	
	
}
