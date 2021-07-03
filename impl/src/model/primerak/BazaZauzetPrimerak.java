package model.primerak;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.autor.Autor;
import model.enums.VrstaAutora;
import model.idnums.BazaID;



public class BazaZauzetPrimerak {

	
	private static BazaZauzetPrimerak instance = null;

	public static BazaZauzetPrimerak getInstance() {
		if (instance == null) {
			instance = new BazaZauzetPrimerak();
		}
		return instance;
	}

	private List<ZauzetPrimerak> zPrimerci;


	private BazaZauzetPrimerak() {

		
		initZPrimerci();
		
	}

	public List<ZauzetPrimerak> getZPrimerci() {
		return zPrimerci;
	}
	
	private void initZPrimerci()
	{
		File file = new File("./idFolder/zauzetiPrimerci.txt");

		try {
			if (!file.exists()) {
		        file.createNewFile();
		    }
		BufferedReader br = new BufferedReader(new FileReader(file));
		  
		String st;
		while ((st = br.readLine()) != null)
		{

			String[] parts = st.split(";");
			long id = Long.parseLong(parts[0]);
			LocalDate datumVracanja = LocalDate.parse(parts[1]);
			boolean rokProduzen = Boolean.parseBoolean(parts[2]);
			boolean vracen = Boolean.parseBoolean(parts[3]);
			
			long prID = Long.parseLong(parts[4]);
			Primerak primerak = null;
			BazaPrimerak bPRIM = BazaPrimerak.getInstance();
			
			for (Primerak p : bPRIM.getPrimerci())
			{
				if (prID == p.getId())
				{
					primerak = p;
				}
			}
			
			try {
			String komentar = parts[5];
			int ocena = Integer.parseInt(parts[6]);
			boolean moderisano = Boolean.parseBoolean(parts[7]);
			
			Revizija revizija = new Revizija(komentar, ocena, moderisano);
			ZauzetPrimerak zPrimerk = new ZauzetPrimerak(id, datumVracanja, rokProduzen, vracen, primerak, revizija);
			this.zPrimerci.add(zPrimerk);
			}
			catch (Exception e) {
				ZauzetPrimerak zPrimerk = new ZauzetPrimerak(primerak, id, datumVracanja, rokProduzen, vracen );
				this.zPrimerci.add(zPrimerk);
			}
		}
		br.close();
		} catch (Exception e) {
			
		}
	}
	
	public void dodajZPrimerak(LocalDate datumVracanja, boolean rokProduzen, boolean vracen, Primerak primerak, Revizija revizija) {
		BazaID bID = BazaID.getInstance();
		long id = bID.getIdZPrimerak();
		ZauzetPrimerak zPrimer = new ZauzetPrimerak(id, datumVracanja, rokProduzen, vracen, primerak, revizija);
		this.zPrimerci.add(zPrimer);
		
		File file = new File("./Baza/zauzetiPrimerci.txt");
		
		try(FileWriter fw = new FileWriter(file, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String insertString = "";
				insertString += Long.toString(zPrimer.getId()) + ";";
				insertString += Boolean.toString(zPrimer.isRokProduzen()) + ";";
				insertString += Boolean.toString(zPrimer.isVracen()) + ";";
				insertString += Long.toString(zPrimer.getPrimerak().getId()) + ";";
				insertString += zPrimer.getRevizija().getKomentar() + ";";
				insertString += Integer.toString(zPrimer.getRevizija().getOcena()) + ";";
				insertString += Boolean.toString(zPrimer.getRevizija().isModerisano());
			    out.println(insertString);
			    
			    
			} catch (Exception e) {
				
			}
	}
	
	public void dodajZPrimerak(ZauzetPrimerak zPrimer) {
		this.zPrimerci.add(zPrimer);
		
		File file = new File("./Baza/zauzetiPrimerci.txt");
		
		try(FileWriter fw = new FileWriter(file, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String insertString = "";
				insertString += Long.toString(zPrimer.getId()) + ";";
				insertString += Boolean.toString(zPrimer.isRokProduzen()) + ";";
				insertString += Boolean.toString(zPrimer.isVracen()) + ";";
				insertString += Long.toString(zPrimer.getPrimerak().getId()) + ";";
				insertString += zPrimer.getRevizija().getKomentar() + ";";
				insertString += Integer.toString(zPrimer.getRevizija().getOcena()) + ";";
				insertString += Boolean.toString(zPrimer.getRevizija().isModerisano());
			    out.println(insertString);
			    
			    
			} catch (Exception e) {
				
			}
	}
	
	public void dodajZPrimerak(Primerak primerak) {
		BazaID bID = BazaID.getInstance();
		long id = bID.getIdZPrimerak();
		ZauzetPrimerak zPrimer = new ZauzetPrimerak(primerak, id);
		this.zPrimerci.add(zPrimer);
		
		File file = new File("./Baza/zauzetiPrimerci.txt");
		
		try(FileWriter fw = new FileWriter(file, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String insertString = "";
				insertString += Long.toString(zPrimer.getId()) + ";";
				insertString += Boolean.toString(zPrimer.isRokProduzen()) + ";";
				insertString += Boolean.toString(zPrimer.isVracen()) + ";";
				insertString += Long.toString(zPrimer.getPrimerak().getId()) + ";";
			    out.println(insertString);
			    
			    
			} catch (Exception e) {
				
			}
	}
	
}
