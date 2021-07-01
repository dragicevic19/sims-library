package model.izdanje;

import java.util.ArrayList;
import java.util.List;

public class BazaIzdanja {

	private static BazaIzdanja instance = null;

	public static BazaIzdanja getInstance() {
		if (instance == null) {
			instance = new BazaIzdanja();
		}
		return instance;
	}

	private List<Izdanje> izdanja;

	private BazaIzdanja() {
		this.izdanja = new ArrayList<Izdanje>();
		this.izdanja.add(new Izdanje("Laguna", "2020", 1));
		this.izdanja.add(new Izdanje("FTN", "2020", 1));

	}

	public List<Izdanje> getIzdanja() {
		return izdanja;
	}
}
