package model.korisnici;

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

	private long generator;
	private List<Bibliotekar> bibliotekari;
	private List<String> kolone;

	private BazaBibliotekara() {
		generator = 0;

		this.bibliotekari = new ArrayList<Bibliotekar>();
		Bibliotekar b = new Bibliotekar(generateId(), "luka", "Luka", "Lukic", "luka123", "1231231231232",
				BazaMesto.getInstance().getMesta().get(0), "Bulevar 12", getSveVrsteBibliotekara(), false);

		Bibliotekar a = new Bibliotekar(generateId(), "jova", "Jovan", "Jovanovic", "jova123", "1231231231232",
				BazaMesto.getInstance().getMesta().get(0), "Bulevar 12", getSveVrsteBibliotekara(), true);

		this.bibliotekari.add(b);
		this.bibliotekari.add(a);

		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("IME");
		this.kolone.add("PREZIME");
		this.kolone.add("KORISNICKO IME");
		this.kolone.add("ULOGA");
	}

	public List<Bibliotekar> getBibliotekari() {
		return bibliotekari;
	}

	public void setBibliotekari(List<Bibliotekar> bibliotekari) {
		this.bibliotekari = bibliotekari;
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

		this.bibliotekari.add(new Bibliotekar(generateId(), korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa,
				vrstaB, admin));
		// dodaj u bazu
	}

	public void dodajBibliotekara(Bibliotekar b) {
		this.bibliotekari.add(b);
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
