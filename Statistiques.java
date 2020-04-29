package projet3;

import java.io.File;
import java.io.IOException;

public class Statistiques {
	protected File TouslesVoyages;
	protected Voyageur Voyageur;
	public Statistiques()
	{
		TouslesVoyages=new File("C:\\Agence\\TouslesVoyageurs");
	}
	public void nombreVoyagesTotales() throws IOException
	{
		int Somme=0;
		String[] tab=TouslesVoyages.list();
		for(int i=0;i<tab.length;i++)
		{
			Voyageur=new Voyageur();
			System.out.println(tab[i]);
			Voyageur.setVoyagesFaites("C:\\Agence\\TouslesVoyageurs\\"+tab[i]+"\\VoyagesFaites.txt");
			Somme+=Voyageur.NbVoyages(tab[i]);
			
		}
		System.out.println("le nombre de Voyages Totales de l'agence est "+ Somme);
		
	}
	public void nombreClients()
	{
		System.out.println("le nombre de voyageurs distincts est "+TouslesVoyages.list().length);
		
	}

}
