package controller;

import java.util.Vector;

import model.knjiga.Knjiga;
import model.primerak.BazaPrimerak;
import model.primerak.Primerak;

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

}
