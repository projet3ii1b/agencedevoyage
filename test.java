package agencedevoyage;

import java.io.IOException;
import java.util.Scanner;
import java.util.Scanner;

public class test {
	

	public static void main (String args[]) throws IOException
	{
			menu.Principale();
			Touslesvoyages T=new Touslesvoyages();
			Statistique S=new Statistique();
			CalculDate c=new CalculDate();
			Scanner s=new Scanner(System.in);
			int choix=s.nextInt();
			switch(choix)
			{
			case 0:
			{
				T.AjouterVoyageur();
				System.out.println(" Ajout effectué avec succé ");
				break;
			}
			case 1 :
			{
				T.afficherVoyagesFaitesAC();
				break;
				
			}
			case 2 :
			{
				T.retarder();
				break;
				
			}
			case 3 :
			{
				T.supprimervoyage();
				break;
				
			}
			case 4:
			{
				T.gagnant();
				break;
			}
			case 5:
			{
				T.afficherTouslesVoyages();
				break;
			}
			case 6 :
			{
				T.nombreVoyagesspaysTotale();
				break;
				
			}
			case 7:
			{
				S.nombreVoyagesTotales();
				break;	
			}
			case 8 :
			{
				S.nombreClients();
				break;
			}
			case 9:
			{
				S.nombremoyenneannuels();
				break;
			}
			case 10:
			{
				S.nombrevoyagesaisonAD();
				break;
			}
			case 11:
			{
				S.retournerlemoisleplusdemandee(S.nombredevoyageparmois());
				break;
			}
			case 12:
			{
				S.retournerlemoislemoinsdemandee(S.nombredevoyageparmois());
				break;
				
			}
			case 13:
			{
				c.trancheAge();
				break;
				
			}
			
			
			}
			s.close();
	}
}
		


