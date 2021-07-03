package model.izdanje;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.autor.Autor;
import model.enums.VrstaAutora;
import model.idnums.BazaID;

public class BazaIzdanja {

	private static BazaIzdanja instance = null;

	public static BazaIzdanja getInstance() {
		if (instance == null) {
			instance = new BazaIzdanja();
		}
		return instance;
	}

	private List<Izdanje> izdanja;

	private BazaIzdanja() {
		
		
		
		initIzdanje();

	}
	
	private void initIzdanje()
	{
		this.izdanja = new ArrayList<Izdanje>();
		
		File file = new File("./Baza/izdanja.txt");
		try {
			if (!file.exists()) {
		        file.createNewFile();
		        dodajIzdanja("Laguna", "2020", 1);
		        dodajIzdanja("FTN", "2020", 1);
		    }
		BufferedReader br = new BufferedReader(new FileReader(file));
		  
		String st;
		while ((st = br.readLine()) != null)
		{
			String[] parts = st.split(";");
			long id = Long.parseLong(parts[0]);
			String izdavac = parts[1];
			String godIzdanja = parts[2];
			int brIzdanja = Integer.parseInt(parts[3]);
			Izdanje izd = new Izdanje(id, izdavac, godIzdanja, brIzdanja);
			this.izdanja.add(izd);
		}
		br.close();
		} catch (Exception e) {
			
		}
	}
	
	public void dodajIzdanja(String izdavac, String godIzdanja, int brIzdanja) {
		BazaID bID = BazaID.getInstance();
		long id = bID.getIdIzdanje();
		Izdanje izd = new Izdanje(id, izdavac, godIzdanja, brIzdanja);
		this.izdanja.add(izd);
		
		File file = new File("./Baza/izdanja.txt");
		
		try(FileWriter fw = new FileWriter(file, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String insertString = "";
				insertString += Long.toString(izd.getId()) + ";";
				insertString += izd.getIzdavac() + ";";
				insertString += izd.getGodIzdanja() + ";";
				insertString += Integer.toString(izd.getBrIzdanja());
			    out.println(insertString);
			    
			    
			} catch (Exception e) {
				
			}
	}
	
	public void dodajIzdanja(Izdanje izd) {
		this.izdanja.add(izd);
		
		File file = new File("./Baza/izdanja.txt");
		
		try(FileWriter fw = new FileWriter(file, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String insertString = "";
				insertString += Long.toString(izd.getId()) + ";";
				insertString += izd.getIzdavac() + ";";
				insertString += izd.getGodIzdanja() + ";";
				insertString += Integer.toString(izd.getBrIzdanja());
			    out.println(insertString);
			    
			    
			} catch (Exception e) {
				
			}
	}

	public List<Izdanje> getIzdanja() {
		return izdanja;
	}
}
