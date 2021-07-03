package controller;

import java.util.List;
import model.korisnici.BazaClanova;
import model.korisnici.Clan;

public class ClanoviController {

	private static ClanoviController instance = null;

	public static ClanoviController getInstance() {
		if (instance == null) {
			instance = new ClanoviController();
		}
		return instance;
	}

	private ClanoviController() {
	}

	public List<Clan> getClanovi() {
		return BazaClanova.getInstance().getClanovi();
	}

	public String generateRandomPassword(int passwordLen) {
		return BazaClanova.getInstance().generateRandomPassword(passwordLen);
	}

}
