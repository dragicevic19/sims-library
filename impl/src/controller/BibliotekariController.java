package controller;

import java.time.LocalDate;

import dto.BibliotekarDTO;
import dto.ClanDTO;
import model.idnums.BazaID;
import model.korisnici.BazaBibliotekara;
import model.korisnici.BazaClanova;
import model.korisnici.BazaKorisnika;
import model.korisnici.Bibliotekar;
import model.korisnici.Clan;
import model.primerak.BazaPrimerak;
import model.primerak.BazaZauzetPrimerak;
import model.primerak.Primerak;
import model.primerak.ZauzetPrimerak;

public class BibliotekariController {

	private static BibliotekariController instance = null;

	public static BibliotekariController getInstance() {
		if (instance == null) {
			instance = new BibliotekariController();
		}
		return instance;
	}

	private BibliotekariController() {
	}

	public void dodajClana(ClanDTO cDTO) {
		Clan noviClan = new Clan();
		noviClan.setId(BazaID.getInstance().getIdKorisnik());
		noviClan.setIme(cDTO.getIme());
		noviClan.setPrezime(cDTO.getPrezime());
		noviClan.setKorisnickoIme(cDTO.getKorisnickoIme());
		noviClan.setLozinka(cDTO.getLozinka());
		noviClan.setAdresa(cDTO.getAdresa());
		noviClan.setDatumRodjenja(cDTO.getDatumRodjenja());
		noviClan.setMesto(cDTO.getMesto());
		noviClan.setVrsta(cDTO.getVrsta());
		noviClan.setPozivNaBr(cDTO.getPozivNaBr());
		noviClan.setJmbg(cDTO.getJmbg());
		noviClan.seteMail(cDTO.geteMail());

		noviClan.setDatumIstekaClanarine(LocalDate.now().plusMonths(6)); // za sada moze clanarina samo za 6 meseci

		BazaClanova.getInstance().dodajClana(noviClan);
	}

	public boolean iznajmiKnjiguClanu(Clan clan, Primerak p) {
		if (clan.getBrTrenutnoIznajmljenih() >= BazaClanova.getInstance().getMaxBrIznajmljenihKnjigaZaClana(clan))
			return false;

		ZauzetPrimerak zp = new ZauzetPrimerak(p);
		zp.setDatumVracanja(LocalDate.now().plusDays(BazaClanova.getInstance().getRokZaVracanjeZaClana(clan)));
		zp.setId(BazaID.getInstance().getIdZPrimerak());
		BazaClanova.getInstance().dodajIznajmljeniPrimerakZaClana(clan, zp);

		BazaPrimerak.getInstance().iznajmljenPrimerak(p);
		BazaZauzetPrimerak.getInstance().dodajZPrimerak(zp);

		return true;
	}

	public void blokirajClana(Clan clan) {
		clan.setObrisan(true);
		BazaClanova.getInstance().izmeniClana(clan.getId(), clan.getKorisnickoIme(), clan.getIme(), clan.getPrezime(), clan.getLozinka(), clan.getJmbg(),
				clan.getMesto(), clan.getAdresa(), clan.getVrsta(), clan.getDatumRodjenja(), clan.getPozivNaBr(), 
				clan.getDatumIstekaClanarine(), clan.getIznajmljeniPrimerci(), clan.isObrisan());
	}
	
	public void dodajBibliotekara(BibliotekarDTO bDTO) {
		Bibliotekar noviBibliotekar = new Bibliotekar();
		noviBibliotekar.setId(BazaID.getInstance().getIdKorisnik());
		noviBibliotekar.setIme(bDTO.getIme());
		noviBibliotekar.setPrezime(bDTO.getPrezime());
		noviBibliotekar.setKorisnickoIme(bDTO.getKorisnickoIme());
		noviBibliotekar.setLozinka(bDTO.getLozinka());
		noviBibliotekar.setAdresa(bDTO.getAdresa());
		noviBibliotekar.setMesto(bDTO.getMesto());
		noviBibliotekar.setUloge(BazaBibliotekara.getInstance().getSveVrsteBibliotekara());
		noviBibliotekar.setJmbg(bDTO.getJmbg());


		BazaBibliotekara.getInstance().dodajBibliotekara(noviBibliotekar);
	}
}
