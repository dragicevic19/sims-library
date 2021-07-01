package dto;

import java.time.LocalDate;

import model.korisnici.VrstaClana;
import model.mesto.Mesto;

public class ClanDTO {

	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;
	private String jmbg;
	private Mesto mesto;
	private String adresa;
	private LocalDate datumRodjenja;
	private String pozivNaBr;
	private VrstaClana vrsta;
	private String eMail;

	public ClanDTO(String ime, String prezime, String korisnickoIme, String lozinka, String jmbg, Mesto mesto,
			String adresa, LocalDate datumRodjenja, String pozivNaBr, VrstaClana vrsta, String eMail) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.jmbg = jmbg;
		this.mesto = mesto;
		this.adresa = adresa;
		this.datumRodjenja = datumRodjenja;
		this.pozivNaBr = pozivNaBr;
		this.vrsta = vrsta;
		this.eMail = eMail;
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

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getPozivNaBr() {
		return pozivNaBr;
	}

	public void setPozivNaBr(String pozivNaBr) {
		this.pozivNaBr = pozivNaBr;
	}

	public VrstaClana getVrsta() {
		return vrsta;
	}

	public void setVrsta(VrstaClana vrsta) {
		this.vrsta = vrsta;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}
