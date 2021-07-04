package controller;

import model.korisnici.BazaBibliotekara;
import model.korisnici.BazaClanova;
import model.korisnici.BazaKorisnika;
import model.korisnici.Bibliotekar;
import model.korisnici.Clan;
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
				// BazaKorisnika.promenaLozinke(); zbog osvezavanja baze?
				if (ulogovaniKorisnik instanceof Clan)
				{
					Clan clan = (Clan) ulogovaniKorisnik;
					BazaClanova.getInstance().izmeniClana(clan.getId(), clan.getKorisnickoIme(), clan.getIme(), clan.getPrezime(), clan.getLozinka(), clan.getJmbg(),
							clan.getMesto(), clan.getAdresa(), clan.getVrsta(), clan.getDatumRodjenja(), clan.getPozivNaBr(), 
							clan.getDatumIstekaClanarine(), clan.getIznajmljeniPrimerci(), clan.isObrisan());
				} else
					if (ulogovaniKorisnik instanceof Bibliotekar)
					{
						Bibliotekar bibliotekar = (Bibliotekar) ulogovaniKorisnik;
						BazaBibliotekara.getInstance().izmeniBibliotekara(bibliotekar.getId(), bibliotekar.getKorisnickoIme(),
								bibliotekar.getIme(), bibliotekar.getPrezime(), bibliotekar.getLozinka(), bibliotekar.getJmbg(), 
								bibliotekar.getMesto(), bibliotekar.getAdresa(), bibliotekar.getUloge(), bibliotekar.isAdmin());
					}
				return true;
			}
		return false;
	}
}
