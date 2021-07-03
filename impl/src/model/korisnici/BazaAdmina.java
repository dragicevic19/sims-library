package model.korisnici;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.enums.VrstaBibliotekara;
import model.mesto.BazaMesto;
import model.mesto.Mesto;

public class BazaAdmina {
	
	private static BazaAdmina instance = null;

	public static BazaAdmina getInstance() {
		if (instance == null) {
			instance = new BazaAdmina();
		}
		return instance;
	}

	private long generator;
	private List<Admin> admini;
	private List<String> kolone;

	private BazaAdmina() {
		generator = 0;

		this.admini = new ArrayList<Admin>();
		Admin a = new Admin(generateId(), "jova", "Jovan", "Jovanovic", "jova123", "1231231231232",
				BazaMesto.getInstance().getMesta().get(0), "Bulevar 12", getSveVrsteBibliotekara());

		this.admini.add(a);

		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("IME");
		this.kolone.add("PREZIME");
		this.kolone.add("KORISNICKO IME");
		this.kolone.add("ULOGA");
	}

	public List<Admin> getAdmini() {
		return admini;
	}

	public void setAdmini(List<Admin> admini) {
		this.admini = admini;
	}

	private long generateId() {
		return ++generator;
	}

	public int getColumnCount() {
		return this.kolone.size();
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Admin getRow(int rowIndex) {
		return this.admini.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Admin admin = this.admini.get(row);
		switch (column) {
		case 0:
			return Long.toString(admin.getId());
		case 1:
			return admin.getIme();
		case 2:
			return admin.getPrezime();
		case 3:
			return admin.getKorisnickoIme();
		case 4:
			return admin.ulogeToString();
		default:
			return null;
		}
	}

	public void dodajAdmina(String korisnickoIme, String ime, String prezime, String lozinka, String jmbg,
			Mesto mesto, String adresa, List<VrstaBibliotekara> vrstaB) {

		this.admini
				.add(new Admin(generateId(), korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa, vrstaB));
		// dodaj u bazu
	}

	public void dodajAdmina(Admin a) {
		this.admini.add(a);
		// dodaj u bazu
	}

	public Vector<VrstaBibliotekara> getSveVrsteBibliotekara() {
		Vector<VrstaBibliotekara> retList = new Vector<VrstaBibliotekara>();
		retList.add(VrstaBibliotekara.IZDAVANJE);
		retList.add(VrstaBibliotekara.OBRADA);

		return retList;
	}

	/*
	 * public void izbrisiClana(long id) { for (Knjiga i : knjige) { if (i.getId()
	 * == id) { knjige.remove(i); break; } } }
	 */

	/*
	 * public void izmeniBibliotekara(long id, String korisnickoIme, String ime,
	 * String prezime, String lozinka, String jmbg, Mesto mesto, String adresa,
	 * VrstaClana vrsta, LocalDate datumRodj, String pozivNaBr, LocalDate
	 * datumIstekaClan) { for (Bibliotekar c : clanovi) { if (c.getId() == id) {
	 * c.setKorisnickoIme(korisnickoIme); c.setIme(ime); c.setPrezime(prezime);
	 * c.setLozinka(lozinka); c.setJmbg(jmbg); c.setMesto(mesto);
	 * c.setAdresa(adresa); c.setVrsta(vrsta); c.setDatumRodjenja(datumRodj);
	 * c.setPozivNaBr(pozivNaBr); c.setDatumIstekaClanarine(datumIstekaClan); } }
	 * 
	 * }
	 */
}
