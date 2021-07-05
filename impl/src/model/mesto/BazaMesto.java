package model.mesto;

import java.util.Vector;

import model.mesto.Mesto;

public class BazaMesto {

	private static BazaMesto instance = null;

	public static BazaMesto getInstance() {
		if (instance == null) {
			instance = new BazaMesto();
		}
		return instance;
	}

	private Vector<Mesto> mesta;

	private BazaMesto() {
		this.mesta = new Vector<Mesto>();
		this.mesta.add(new Mesto("Loznica", "15300"));
		this.mesta.add(new Mesto("Novi Sad", "21000"));
		this.mesta.add(new Mesto("Beograd", "11000"));
		this.mesta.add(new Mesto("Zrenjanin", "12000"));
		this.mesta.add(new Mesto("Pozarevac", "25000"));
	}

	public Vector<Mesto> getMesta() {
		return mesta;
	}
}
