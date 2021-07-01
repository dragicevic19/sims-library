package model.korisnici;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.mesto.Mesto;
import model.primerak.ZauzetPrimerak;

public class BazaClanova {

	private static BazaClanova instance = null;

	public static BazaClanova getInstance() {
		if (instance == null) {
			instance = new BazaClanova();
		}
		return instance;
	}

	private long generator;
	private List<Clan> clanovi;
	private List<String> kolone;

	private BazaClanova() {
		generator = 0;

		this.clanovi = new ArrayList<Clan>();

		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("IME");
		this.kolone.add("PREZIME");
		this.kolone.add("KORISNICKO IME");
		this.kolone.add("TIP");
		this.kolone.add("TRENUTNO CITA");
	}

	public List<Clan> getClanovi() {
		return clanovi;
	}

	public void setClanovi(List<Clan> clanovi) {
		this.clanovi = clanovi;
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

	public Clan getRow(int rowIndex) {
		return this.clanovi.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Clan clan = this.clanovi.get(row);
		switch (column) {
		case 0:
			return Long.toString(clan.getId());
		case 1:
			return clan.getIme();
		case 2:
			return clan.getPrezime();
		case 3:
			return clan.getKorisnickoIme();
		case 4:
			return clan.getVrsta().name();
		case 5:
			return Integer.toString(clan.getBrTrenutnoIznajmljenih());
		default:
			return null;
		}
	}

	public void dodajClana(String korisnickoIme, String ime, String prezime, String lozinka, String jmbg, Mesto mesto,
			String adresa, VrstaClana vrsta, LocalDate datumRodj, String pozivNaBr, LocalDate datumIstekaClan,
			String eMail) {

		this.clanovi.add(new Clan(generateId(), korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa, vrsta,
				datumRodj, pozivNaBr, datumIstekaClan, eMail));
		// dodaj u bazu
	}

	public void dodajClana(Clan clan) {
		this.clanovi.add(clan);
		// dodaj u bazu
	}

	/*
	 * public void izbrisiClana(long id) { for (Knjiga i : knjige) { if (i.getId()
	 * == id) { knjige.remove(i); break; } } }
	 */

	public void izmeniClana(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg,
			Mesto mesto, String adresa, VrstaClana vrsta, LocalDate datumRodj, String pozivNaBr,
			LocalDate datumIstekaClan) {
		for (Clan c : clanovi) {
			if (c.getId() == id) {
				c.setKorisnickoIme(korisnickoIme);
				c.setIme(ime);
				c.setPrezime(prezime);
				c.setLozinka(lozinka);
				c.setJmbg(jmbg);
				c.setMesto(mesto);
				c.setAdresa(adresa);
				c.setVrsta(vrsta);
				c.setDatumRodjenja(datumRodj);
				c.setPozivNaBr(pozivNaBr);
				c.setDatumIstekaClanarine(datumIstekaClan);
			}
		}
	}

	public int getMaxBrIznajmljenihKnjigaZaClana(Clan clan) {
		switch (clan.getVrsta()) {
		case PREDSKOLAC:
		case UCENIK:
		case STUDENT:
		case PENZIONER:
			return 3;
		case ZAPOSLEN:
			return 5;
		default:
			return -1;
		}
	}

	public long getRokZaVracanjeZaClana(Clan clan) {
		switch (clan.getVrsta()) {
		case PREDSKOLAC:
		case UCENIK:
		case STUDENT:
		case ZAPOSLEN:
			return 15;
		case PENZIONER:
			return 21;
		default:
			return -1;
		}
	}

	public void dodajIznajmljeniPrimerakZaClana(Clan clan, ZauzetPrimerak zp) {
		clan.getIznajmljeniPrimerci().add(zp);

	}

	public String generateRandomPassword(int len) { // da li ovo treba u kontroler ili ovde?
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}

	public Vector<VrstaClana> getVrsteClanova() {
		Vector<VrstaClana> retList = new Vector<VrstaClana>();
		retList.add(VrstaClana.PREDSKOLAC);
		retList.add(VrstaClana.UCENIK);
		retList.add(VrstaClana.STUDENT);
		retList.add(VrstaClana.ZAPOSLEN);
		retList.add(VrstaClana.PENZIONER);

		return retList;
	}
}