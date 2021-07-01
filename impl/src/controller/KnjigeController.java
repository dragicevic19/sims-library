package controller;

import java.util.List;

import model.knjiga.BazaKnjiga;
import model.knjiga.Knjiga;

public class KnjigeController {
	private static KnjigeController instance = null;

	public static KnjigeController getInstance() {
		if (instance == null) {
			instance = new KnjigeController();
		}
		return instance;
	}

	private KnjigeController() {
	}

	public List<Knjiga> getKnjige() {
		return BazaKnjiga.getInstance().getKnjige();
	}

}
