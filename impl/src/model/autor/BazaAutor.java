package model.autor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.enums.VrstaAutora;
import model.idnums.BazaID;

public class BazaAutor {

	private static BazaAutor instance = null;

	public static BazaAutor getInstance() {
		if (instance == null) {
			instance = new BazaAutor();
		}
		return instance;
	}


	private List<Autor> autori;
	private List<String> kolone;

	private BazaAutor() {


		initAutore();

		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("IME");
		this.kolone.add("PREZIME");
		this.kolone.add("VRSTA AUTORA");

	}

	private void initAutore() {
		this.autori = new ArrayList<Autor>();

		File file = new File("./Baza/autori.txt");
		try {
			if (!file.exists()) {
		        file.createNewFile();
		        dodajAutora("Mika", "Mikic", VrstaAutora.PISAC);
		        dodajAutora("Zika", "Zikic", VrstaAutora.ILUSTRATOR);
		        dodajAutora("Pera", "Peric", VrstaAutora.PREVODIOC);
		    }
		BufferedReader br = new BufferedReader(new FileReader(file));
		  
		String st;
		while ((st = br.readLine()) != null)
		{
			String[] parts = st.split(";");
			long id = Long.parseLong(parts[0]);
			String ime = parts[1];
			String prezime = parts[2];
			VrstaAutora vrsta = VrstaAutora.valueOf(parts[3]);
			Autor autor = new Autor(id,ime,prezime,vrsta);
			this.autori.add(autor);
		}
		br.close();
		} catch (Exception e) {
			
		}
	}

	public List<Autor> getAutori() {
		return autori;
	}

	public void setIgraci(List<Autor> autori) {
		this.autori = autori;
	}



	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Autor getRow(int rowIndex) {
		return this.autori.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Autor autor = this.autori.get(row);
		switch (column) {
		case 0:
			return Long.toString(autor.getId());
		case 1:
			return autor.getIme();
		case 2:
			return autor.getPrezime();
		case 3:
			return autor.getVrstaAutora().toString();
		default:
			return null;
		}
	}

	public void dodajAutora(String ime, String prezime, VrstaAutora vrstaAutora) {
		BazaID bID = BazaID.getInstance();
		long id = bID.getIdAutor();
		Autor autor = new Autor(id, ime, prezime, vrstaAutora);
		this.autori.add(autor);
		
		File file = new File("./Baza/autori.txt");
		
		try(FileWriter fw = new FileWriter(file, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String insertString = "";
				insertString += Long.toString(autor.getId()) + ";";
				insertString += autor.getIme() + ";";
				insertString += autor.getPrezime() + ";";
				insertString += autor.getVrstaAutora().name();
			    out.println(insertString);
			    
			    
			} catch (Exception e) {
				
			}
	}
	
	public void dodajAutora(Autor autor) {

		this.autori.add(autor);
		
		File file = new File("./Baza/autori.txt");
		
		try(FileWriter fw = new FileWriter(file, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				String insertString = "";
				insertString += Long.toString(autor.getId()) + ";";
				insertString += autor.getIme() + ";";
				insertString += autor.getPrezime() + ";";
				insertString += autor.getVrstaAutora().name();
			    out.println(insertString);
			    
			    
			} catch (Exception e) {
				
			}
	}

	/*
	 * da li treba brisati autora?? public void izbrisiAutora(long id) { for (Autor
	 * i : autori) { if (i.getId() == id) { i.ob //autori.remove(i); break; } } }
	 */

	public void izmeniAutora(long id, String ime, String prezime, VrstaAutora vrstaAutora) {
		File file = new File("./Baza/autori.txt");
		try {
			FileWriter writer = new FileWriter(file, false);
			writer.append("");
			writer.close();
		} catch (Exception e) {

		}
		List<Autor> temp = new ArrayList<>(this.autori); 
		for ( int i = 0; i < temp.size(); i++ ) {
			Autor autor = temp.get(i);
			if (autor.getId() == id) {
				autor.setIme(ime);
				autor.setPrezime(prezime);
				autor.setVrstaAutora(vrstaAutora);

				this.autori.remove(0);

				dodajAutora(autor);
			} else {

				this.autori.remove(0);

				dodajAutora(autor);
			}
		}
	}
	
}
