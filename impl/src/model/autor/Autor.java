package model.autor;

import model.enums.VrstaAutora;

public class Autor {

	private long id;
	private String ime;
	private String prezime;
	private VrstaAutora vrstaAutora;

	public Autor() {
	}

	public Autor(long id, String ime, String prezime, VrstaAutora vrstaAutora) {
		this();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.vrstaAutora = vrstaAutora;
	}

	public long getId() {
		return id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public VrstaAutora getVrstaAutora() {
		return vrstaAutora;
	}

	public void setVrstaAutora(VrstaAutora vrstaAutora) {
		this.vrstaAutora = vrstaAutora;
	}

	@Override
	public String toString() {
		return ime + " " + prezime + " " + vrstaAutora + "\n";
	}

}
