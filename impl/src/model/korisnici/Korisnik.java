package model.korisnici;

import model.mesto.Mesto;

public abstract class Korisnik {

	protected long id;
	protected String ime;
	protected String prezime;
	protected String korisnickoIme;
	protected String lozinka;
	protected String jmbg;
	protected Mesto mesto;
	protected String adresa;
	protected boolean obrisan;

	public Korisnik() {
		// TODO Auto-generated constructor stub
	}

	public Korisnik(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg, Mesto mesto,
			String adresa) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.jmbg = jmbg;
		this.mesto = mesto;
		this.adresa = adresa;
		this.obrisan = false;
	}

	public abstract void prijava();

	public abstract void izmeniKorisnika();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

}
