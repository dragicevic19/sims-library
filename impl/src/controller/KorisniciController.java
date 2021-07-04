package controller;

import model.korisnici.BazaKorisnika;
import model.korisnici.Korisnik;

public class KorisniciController {

	private static KorisniciController instance = null;

	public static KorisniciController getInstance() {
		if (instance == null) {
			instance = new KorisniciController();
		}
		return instance;
	}

	private KorisniciController() {
	}

	public Korisnik proveriLoginPodatke(String korisnickoIme, String lozinka) {
		Korisnik retVal = null;
		if (korisnickoIme.length() == 0 || lozinka.length() == 0)
			return retVal;

		retVal = BazaKorisnika.getInstance().getKorisnikZaKorisnickoImeILozinku(korisnickoIme, lozinka);
		return retVal;
	}

	public Korisnik getKorisnikZaKorisnickoIme(String kIme) {
		return BazaKorisnika.getInstance().getKorisnikZaKorisnickoIme(kIme);
	}

	public boolean promenaLozinke(Korisnik ulogovaniKorisnik, String stara, String nova, String novaPotvrda) {
		if (ulogovaniKorisnik.getLozinka().equals(stara))
			if (nova.equals(novaPotvrda)) {
				ulogovaniKorisnik.setLozinka(nova);

				ulogovaniKorisnik.izmeniKorisnika();
				return true;
			}

		return false;
	}
}
