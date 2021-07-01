package controller;

import java.util.Vector;

import model.mesto.BazaMesto;
import model.mesto.Mesto;

public class MestaController {

	private static MestaController instance = null;

	public static MestaController getInstance() {
		if (instance == null) {
			instance = new MestaController();
		}
		return instance;
	}

	private MestaController() {
	}

	public Vector<Mesto> getMesta() {
		return BazaMesto.getInstance().getMesta();
	}

}
