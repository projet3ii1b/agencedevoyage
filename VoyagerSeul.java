package projet3;
import java.io.File;
import java.io.IOException;

public class VoyagerSeul {
	protected File fichierVoyagerSeul;
	public int rechercher(String s)
	{
		File f=new File("C:\\Agence\\VoyagerSeul\\"+s+".txt");
		if (f.exists())
			return 1;
		else
			return 0;
		
	}
	public VoyagerSeul(String ch) throws IOException
	{
		fichierVoyagerSeul=new File("C:\\Agence\\VoyagerSeul\\"+ch+".txt");
		if (fichierVoyagerSeul.exists()!=true)
		{
			fichierVoyagerSeul.createNewFile();
		}
		
	}
	public File nomDossierVS()
	{
		return fichierVoyagerSeul;
	}
	
	

}
