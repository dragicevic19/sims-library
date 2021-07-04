package dto;

import java.util.ArrayList;
import java.util.List;

import model.enums.VrstaBibliotekara;
import model.mesto.Mesto;

public class BibliotekarDTO {

	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;
	private String jmbg;
	private Mesto mesto;
	private String adresa;
	private String eMail;
	private Boolean admin;

	public BibliotekarDTO(String ime, String prezime, String korisnickoIme, String lozinka, String jmbg, Mesto mesto,
			String adresa,String eMail, Boolean admin) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.jmbg = jmbg;
		this.mesto = mesto;
		this.adresa = adresa;
		this.eMail = eMail;
		this.admin = admin;
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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public Boolean getAdmin() {
		return admin;
	}
	
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	
}
