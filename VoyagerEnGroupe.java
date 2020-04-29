package projet3;

import java.io.File;
import java.util.Scanner;

public class VoyagerEnGroupe 
{
	protected File Destination;
	protected File Groupe;
	protected long nb;
	public void NouvelleDestination(String ch)
	{
		Destination=new File("c:\\Agence\\VoyagerEnGroupe\\"+ch);
		if (!Destination.exists())
		{
			Destination.mkdir();
		}
	}
	public void GroupesInitiales()
	{
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
	public void listeGroupeDisponibles()
	{
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
	
	public void retarderdatedeRetourGroupe()
	{
		
		
		
	}
	
}
