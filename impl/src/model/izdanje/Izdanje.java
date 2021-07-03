package model.izdanje;

public class Izdanje {

	private String izdavac;
	private String godIzdanja;
	private int brIzdanja;
	private long id;

	public Izdanje() {
		super();
	}

	public Izdanje(long id,String izdavac, String godIzdanja, int brIzdanja) {
		this();
		this.izdavac = izdavac;
		this.godIzdanja = godIzdanja;
		this.brIzdanja = brIzdanja;
		this.id = id;
	}

	public String getIzdavac() {
		return izdavac;
	}

	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}

	public String getGodIzdanja() {
		return godIzdanja;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setGodIzdanja(String godIzdanja) {
		this.godIzdanja = godIzdanja;
	}

	public int getBrIzdanja() {
		return brIzdanja;
	}

	public void setBrIzdanja(int brIzdanja) {
		this.brIzdanja = brIzdanja;
	}

	@Override
	public String toString() {
		return izdavac + ", " + godIzdanja;
	}

}
