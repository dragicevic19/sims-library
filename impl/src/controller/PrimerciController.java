package controller;

import java.util.List;
import java.util.Vector;

import model.knjiga.Knjiga;
import model.korisnici.BazaClanova;
import model.korisnici.Clan;
import model.primerak.BazaPrimerak;
import model.primerak.BazaZauzetPrimerak;
import model.primerak.Primerak;
import model.primerak.Revizija;
import model.primerak.ZauzetPrimerak;

public class PrimerciController {

	private static PrimerciController instance = null;

	public static PrimerciController getInstance() {
		if (instance == null) {
			instance = new PrimerciController();
		}
		return instance;
	}

	private PrimerciController() {
	}

	public Vector<Primerak> getPrimerciZaIznajmljivanje(Knjiga izabranaKnjiga) {
		return BazaPrimerak.getInstance().getPrimerciZaIznajmljivanje(izabranaKnjiga);
	}

	public List<ZauzetPrimerak> getTrenutnoIznajmljeniPrimerci() {
		return BazaPrimerak.getInstance().getTrenutnoIznajmljeniPrimerci();
	}

	public List<ZauzetPrimerak> getSviIznajmljeniPrimerci() {
		return BazaPrimerak.getInstance().getSviIznajmljeniPrimerci();
	}

	public List<ZauzetPrimerak> getSviIznajmljeniPrimerciZaClana(Clan ulogovaniClan) {
		return BazaPrimerak.getInstance().getSviIznajmljeniPrimerciZaClana(ulogovaniClan);
	}

	public List<ZauzetPrimerak> getTrenutnoIznajmljeniPrimerciZaClana(Clan ulogovaniClan) {
		return BazaPrimerak.getInstance().getTrenutnoIznajmljeniPrimerciZaClana(ulogovaniClan);
	}

	public Object toCell(ZauzetPrimerak z, int col) {
		Clan clan = BazaClanova.getInstance().getClanZaIznajmljeniPrimerak(z);
		return BazaPrimerak.getInstance().zauzetiPrimerakToCell(z, clan, col);
	}

	public void vracanjePrimerka(ZauzetPrimerak zauzet) {
		zauzet.setVracen(true);
		Primerak primerak = zauzet.getPrimerak();
		primerak.setZauzet(false);

		BazaZauzetPrimerak.getInstance().izmeniZPrimerak(zauzet);

		BazaPrimerak.getInstance().izmeniPrimerak(primerak);
	}

	public boolean produzenjeRokaZaVracanjePrimerka(ZauzetPrimerak z, Clan clan) {
		if (z.isRokProduzen())
			return false;

		z.setRokProduzen(true);
		z.setDatumVracanja(z.getDatumVracanja().plusDays(BazaClanova.getInstance().getRokZaVracanjeZaClana(clan)));

		BazaZauzetPrimerak.getInstance().izmeniZPrimerak(z);
		return true;
	}

	public boolean isClanProcitaoPrimerak(ZauzetPrimerak z) {
		return z.isVracen();
	}

	public void napisanaRevizija(Integer ocena, String komentar, ZauzetPrimerak z) {
		Revizija r = new Revizija(komentar, ocena, false);
		z.setRevizija(r);
		BazaZauzetPrimerak.getInstance().izmeniZPrimerak(z);

	}

}
