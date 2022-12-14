package model.korisnici;

import java.util.ArrayList;
import java.util.List;

import model.idnums.BazaID;

public class BazaKorisnika {

	private static BazaKorisnika instance = null;

	public static BazaKorisnika getInstance() {
		if (instance == null) {
			instance = new BazaKorisnika();
		}
		return instance;
	}

	private BazaKorisnika() {

	}

	public long generateId() {
		BazaID bID = BazaID.getInstance();
		long id = bID.getIdKorisnik();
		return id;
	}

	public List<Korisnik> getKorisnici() {
		List<Korisnik> sviKorisnici = new ArrayList<Korisnik>();

		for (Korisnik korisnik : BazaClanova.getInstance().getClanovi()) {
			sviKorisnici.add(korisnik);
		}

		for (Korisnik korisnik : BazaBibliotekara.getInstance().getBibliotekari()) {
			sviKorisnici.add(korisnik);
		}
		return sviKorisnici;
	}

	public Korisnik getKorisnikZaKorisnickoImeILozinku(String korisnickoIme, String lozinka) {
		Korisnik retVal = null;
		for (Korisnik k : getKorisnici()) {
			if (k.getKorisnickoIme().equals(korisnickoIme) && k.getLozinka().equals(lozinka)) {
				retVal = k;
				break;
			}
		}
		return retVal;
	}

	public Korisnik getKorisnikZaKorisnickoIme(String kIme) {
		Korisnik retVal = null;
		for (Korisnik k : getKorisnici()) {
			if (k.getKorisnickoIme().equals(kIme)) {
				retVal = k;
				break;
			}
		}
		return retVal;
	}

}
