package model.korisnici;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.enums.VrstaBibliotekara;
import model.mesto.BazaMesto;
import model.mesto.Mesto;

public class BazaBibliotekara {

	private static BazaBibliotekara instance = null;

	public static BazaBibliotekara getInstance() {
		if (instance == null) {
			instance = new BazaBibliotekara();
		}
		return instance;
	}

	private List<Bibliotekar> bibliotekari;
	private List<String> kolone;

	private BazaBibliotekara() {

		this.bibliotekari = new ArrayList<Bibliotekar>();

		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("IME");
		this.kolone.add("PREZIME");
		this.kolone.add("KORISNICKO IME");
		this.kolone.add("ULOGA");

		initBibliotekari();
	}

	private void initBibliotekari() {

		this.bibliotekari = new ArrayList<Bibliotekar>();
		File file = new File("./Baza/bibliotekari.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
				dodajBibliotekara("luka", "Luka", "Lukic", "luka123", "1231231231232",
						BazaMesto.getInstance().getMesta().get(0), "Bulevar 12", getSveVrsteBibliotekara(), false);

				dodajBibliotekara("jova", "Jovan", "Jovanovic", "jova123", "1231231231232",
						BazaMesto.getInstance().getMesta().get(0), "Bulevar 12", getSveVrsteBibliotekara(), true);

			}

			else {
				BufferedReader br = new BufferedReader(new FileReader(file));

				String st;
				while ((st = br.readLine()) != null) {
					String[] parts = st.split(";");
					long id = Long.parseLong(parts[0]);
					String korisnickoIme = parts[1];
					String ime = parts[2];
					String prezime = parts[3];
					String lozinka = parts[4];
					String jmbg = parts[5];

					Mesto mesto = null;
					for (Mesto m : BazaMesto.getInstance().getMesta()) {
						if (m.getNaziv().equals(parts[6])) {
							mesto = m;
							break;
						}
					}
					String adresa = parts[7];

					List<VrstaBibliotekara> uloge = new ArrayList<VrstaBibliotekara>();

					String[] ulogSt = parts[8].split(",");
					for (String ulogan : ulogSt) {
						uloge.add(VrstaBibliotekara.valueOf(ulogan));
					}
					boolean admin = Boolean.valueOf(parts[9]);

					Bibliotekar bibliotekar = new Bibliotekar(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto,
							adresa, uloge, admin);

					bibliotekari.add(bibliotekar);
				}
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Bibliotekar> getBibliotekari() {
		return bibliotekari;
	}

	public void setBibliotekari(List<Bibliotekar> bibliotekari) {
		this.bibliotekari = bibliotekari;
	}

	public int getColumnCount() {
		return this.kolone.size();
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Bibliotekar getRow(int rowIndex) {
		return this.bibliotekari.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Bibliotekar bibliotekar = this.bibliotekari.get(row);
		switch (column) {
		case 0:
			return Long.toString(bibliotekar.getId());
		case 1:
			return bibliotekar.getIme();
		case 2:
			return bibliotekar.getPrezime();
		case 3:
			return bibliotekar.getKorisnickoIme();
		case 4:
			return bibliotekar.ulogeToString();
		default:
			return null;
		}
	}

	public void dodajBibliotekara(String korisnickoIme, String ime, String prezime, String lozinka, String jmbg,
			Mesto mesto, String adresa, List<VrstaBibliotekara> vrstaB, boolean admin) {

		long id = BazaKorisnika.getInstance().generateId();
		Bibliotekar bibliotekar = new Bibliotekar(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa, vrstaB,
				admin);
		this.bibliotekari.add(bibliotekar);
		dodajBibliotekaraUBazu(bibliotekar);
	}

	public void dodajBibliotekara(Bibliotekar bibliotekar) {
		this.bibliotekari.add(bibliotekar);
		dodajBibliotekaraUBazu(bibliotekar);
	}

	private void dodajBibliotekaraUBazu(Bibliotekar bibliotekar) {
		File file = new File("./Baza/bibliotekari.txt");

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			String insertString = "";
			insertString += Long.toString(bibliotekar.getId()) + ";";
			insertString += bibliotekar.getKorisnickoIme() + ";";
			insertString += bibliotekar.getIme() + ";";
			insertString += bibliotekar.getPrezime() + ";";
			insertString += bibliotekar.getLozinka() + ";";

			insertString += bibliotekar.getJmbg() + ";";
			insertString += bibliotekar.getMesto() + ";";
			insertString += bibliotekar.getAdresa() + ";";

			String ulogeString = "";
			for (VrstaBibliotekara vrB : bibliotekar.getUloge()) {
				ulogeString += vrB.name() + ",";
			}
			insertString += ulogeString.substring(0, ulogeString.length() - 1) + ";";
			insertString += Boolean.toString(bibliotekar.isAdmin());

			out.println(insertString);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void izmeniBibliotekara(Bibliotekar bibliotekar) {

		File file = new File("./Baza/bibliotekari.txt");
		try {
			FileWriter writer = new FileWriter(file, false);
			writer.append("");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Bibliotekar> temp = new ArrayList<Bibliotekar>(this.bibliotekari);
		for (int i = 0; i < temp.size(); i++) {
			Bibliotekar b = temp.get(i);
			if (b.getId() == bibliotekar.getId()) {
				b.setKorisnickoIme(bibliotekar.getKorisnickoIme());
				b.setIme(bibliotekar.getIme());
				b.setPrezime(bibliotekar.getPrezime());
				b.setLozinka(bibliotekar.getLozinka());
				b.setJmbg(bibliotekar.getJmbg());
				b.setMesto(bibliotekar.getMesto());
				b.setAdresa(bibliotekar.getAdresa());
				b.setUloge(bibliotekar.getUloge());
				b.setAdmin(bibliotekar.isAdmin());
				this.bibliotekari.remove(0);

				dodajBibliotekara(b);
			} else {

				this.bibliotekari.remove(0);

				dodajBibliotekara(b);
			}
		}
	}

	public Vector<VrstaBibliotekara> getSveVrsteBibliotekara() {
		Vector<VrstaBibliotekara> retList = new Vector<VrstaBibliotekara>();
		retList.add(VrstaBibliotekara.IZDAVANJE);
		retList.add(VrstaBibliotekara.OBRADA);

		return retList;
	}

}
