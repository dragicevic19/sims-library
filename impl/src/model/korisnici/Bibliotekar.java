package model.korisnici;

import java.util.ArrayList;
import java.util.List;

import model.enums.VrstaBibliotekara;
import model.enums.Zanr;
import model.mesto.Mesto;

public class Bibliotekar extends Korisnik {

	private List<VrstaBibliotekara> uloge;

	public Bibliotekar() {
		this.uloge = new ArrayList<VrstaBibliotekara>();
	}

	public Bibliotekar(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg,
			Mesto mesto, String adresa) {
		super(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa);
		this.uloge = new ArrayList<VrstaBibliotekara>();
	}

	public Bibliotekar(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg,
			Mesto mesto, String adresa, List<VrstaBibliotekara> uloge) {
		super(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa);
		this.uloge = uloge;
	}
	
	

	public List<VrstaBibliotekara> getUloge() {
		return uloge;
	}

	public void setUloge(List<VrstaBibliotekara> uloge) {
		this.uloge = uloge;
	}

	@Override
	void prijava() {
		// TODO Auto-generated method stub

	}

	@Override
	void odjava() {
		// TODO Auto-generated method stub

	}

	@Override
	void promenaLozinke() {
		// TODO Auto-generated method stub

	}

	public String ulogeToString() {
		String str = "";
		for (VrstaBibliotekara uloga : uloge) {
			str += uloga.toString() + ", ";
		}

		String finalString = (String) str.subSequence(0, str.lastIndexOf(", "));
		return finalString;
	}

}
