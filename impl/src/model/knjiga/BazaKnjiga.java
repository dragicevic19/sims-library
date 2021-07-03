package model.knjiga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.autor.Autor;
import model.autor.BazaAutor;
import model.enums.Zanr;
import model.idnums.BazaID;
import model.primerak.BazaPrimerak;

public class BazaKnjiga {

	private static BazaKnjiga instance = null;

	public static BazaKnjiga getInstance() {
		if (instance == null) {
			instance = new BazaKnjiga();
		}
		return instance;
	}

	private List<Knjiga> knjige;
	private List<String> kolone;

	private BazaKnjiga() {

		this.knjige = new ArrayList<Knjiga>();
		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("NASLOV");
		this.kolone.add("AUTORI");
		this.kolone.add("ZANROVI");
		this.kolone.add("OPIS");
		this.kolone.add("TAGOVI");
		this.kolone.add("FORMAT");
		this.kolone.add("SLOBODNO");

		initKnjige();
	}

	private void initKnjige() {
		this.knjige = new ArrayList<Knjiga>();

		File file = new File("./Baza/knjige.txt");

		try {
			if (!file.exists()) {
				file.createNewFile();
				List<String> tagovi = new ArrayList<String>();
				tagovi.add("nauka");
				tagovi.add("tehnologija");
				tagovi.add("fakultet");
				List<Zanr> zanrovi = new ArrayList<Zanr>();
				zanrovi.add(Zanr.SCIFI);
				dodajKnjigu("Paralelno programiranje", "A5", "Opis knjige Paralelno programiranje...", tagovi,
						BazaAutor.getInstance().getAutori(), zanrovi);

				dodajKnjigu("Sistemska softverska podrska", "A5", "Opis knjige SSP...", tagovi,
						BazaAutor.getInstance().getAutori(), zanrovi);

			}
			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			while ((st = br.readLine()) != null) {
				String[] parts = st.split(";");

				long id = Long.parseLong(parts[0]);

				String naslov = parts[1];

				String format = parts[2];

				String opis = parts[3];

				String[] tagList = parts[4].split(",");
				List<String> tagovi = new ArrayList<String>();
				for (String tag : tagList) {
					tagovi.add(tag);
				}

				String[] autorIDList = parts[5].split(",");
				List<Autor> autori = new ArrayList<Autor>();
				for (String autID : autorIDList) {
					long idAut = Long.parseLong(autID);
					for (Autor autor : BazaAutor.getInstance().getAutori()) {
						if (idAut == autor.getId()) {
							autori.add(autor);
						}
					}
				}

				String[] listZanr = parts[6].split(",");
				List<Zanr> zanrovi = new ArrayList<Zanr>();
				for (String z : listZanr) {
					Zanr zanr = Zanr.valueOf(z);
					zanrovi.add(zanr);
				}

				Knjiga knjiga = new Knjiga(id, naslov, format, opis, tagovi, autori, zanrovi);
				this.knjige.add(knjiga);
			}
			br.close();
		} catch (Exception e) {

		}

	}

	public List<Knjiga> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<Knjiga> knjige) {
		this.knjige = knjige;
	}

	public int getColumnCount() {
		return this.kolone.size();
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Knjiga getRow(int rowIndex) {
		return this.knjige.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Knjiga knjiga = this.knjige.get(row);
		switch (column) {
		case 0:
			return Long.toString(knjiga.getId());
		case 1:
			return knjiga.getNaslov();
		case 2:
			return knjiga.autoriToString();
		case 3:
			return knjiga.zanroviToString();
		case 4:
			return knjiga.getOpis();
		case 5:
			return knjiga.tagoviToString();
		case 6:
			return knjiga.getFormat();
		case 7:
			return String.valueOf(BazaPrimerak.getInstance().getPrimerciZaIznajmljivanje(knjiga).size());
		default:
			return null;
		}
	}

	public void dodajKnjigu(String naslov, String format, String opis, List<String> tagovi, List<Autor> list,
			List<Zanr> zanrovi) {
		BazaID bID = BazaID.getInstance();
		long id = bID.getIdKnjiga();
		Knjiga knjiga = new Knjiga(id, naslov, format, opis, tagovi, list, zanrovi);
		this.knjige.add(knjiga);

		File file = new File("./Baza/knjige.txt");

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			String insertString = "";
			insertString += Long.toString(knjiga.getId()) + ";";
			insertString += knjiga.getNaslov() + ";";
			insertString += knjiga.getFormat() + ";";
			insertString += knjiga.getOpis() + ";";

			String tagString = "";
			for (String tag : knjiga.getTagovi()) {
				tagString += tag + ",";
			}
			insertString += tagString.substring(0, tagString.length() - 1) + ";";

			String autorString = "";
			for (Autor autor : knjiga.getAutori()) {
				autorString += Long.toString(autor.getId()) + ",";
			}
			insertString += autorString.substring(0, autorString.length() - 1) + ";";

			String zanrString = "";
			for (Zanr zanr : knjiga.getZanrovi()) {
				zanrString = zanr.name() + ",";
			}
			insertString += zanrString.substring(0, zanrString.length() - 1) + ";";

			out.println(insertString);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void dodajKnjigu(Knjiga knjiga) {

		this.knjige.add(knjiga);

		File file = new File("./Baza/knjige.txt");

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			String insertString = "";
			insertString += Long.toString(knjiga.getId()) + ";";
			insertString += knjiga.getNaslov() + ";";
			insertString += knjiga.getFormat() + ";";
			insertString += knjiga.getOpis() + ";";

			String tagString = "";
			for (String tag : knjiga.getTagovi()) {
				tagString += tag + ",";
			}
			insertString += tagString.substring(0, tagString.length() - 1) + ";";

			String autorString = "";
			for (Autor autor : knjiga.getAutori()) {
				autorString += Long.toString(autor.getId()) + ",";
			}
			insertString += autorString.substring(0, autorString.length() - 1) + ";";

			String zanrString = "";
			for (Zanr zanr : knjiga.getZanrovi()) {
				zanrString = zanr.name() + ",";
			}
			insertString += zanrString.substring(0, zanrString.length() - 1) + ";";

			out.println(insertString);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * da li treba?? public void izbrisiKnjigu(long id) { for (Knjiga i : knjige) {
	 * if (i.getId() == id) { knjige.remove(i); break; } } }
	 */

	public void izmeniKnjigu(long id, String naslov, String format, String opis, ArrayList<String> tagovi,
			ArrayList<Autor> autori, ArrayList<Zanr> zanrovi) {

		File file = new File("./Baza/knjige.txt");
		try {
			FileWriter writer = new FileWriter(file, false);
			writer.append("");
			writer.close();
		} catch (Exception e) {

		}
		List<Knjiga> temp = new ArrayList<>(this.knjige);
		for (int i = 0; i < temp.size(); i++) {
			Knjiga knjiga = temp.get(i);
			if (knjiga.getId() == id) {
				knjiga.setNaslov(naslov);
				knjiga.setFormat(format);
				knjiga.setAutori(autori);
				knjiga.setOpis(opis);
				knjiga.setTagovi(tagovi);
				knjiga.setZanrovi(zanrovi);

				this.knjige.remove(0);

				dodajKnjigu(knjiga);
			} else {

				this.knjige.remove(0);

				dodajKnjigu(knjiga);
			}
		}
	}
}
