package model.korisnici;

import java.util.ArrayList;
import java.util.List;

import model.enums.VrstaBibliotekara;
import model.mesto.Mesto;
import view.admin.MainFrameAdmin;
import view.bibliotekar.MainFrameBibliotekar;

public class Bibliotekar extends Korisnik {

	private List<VrstaBibliotekara> uloge;
	private boolean admin;

	public Bibliotekar() {
		this.uloge = new ArrayList<VrstaBibliotekara>();
	}

	public Bibliotekar(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg,
			Mesto mesto, String adresa, boolean admin) {
		super(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa);
		this.uloge = new ArrayList<VrstaBibliotekara>();
		this.admin = admin;
	}

	public Bibliotekar(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg,
			Mesto mesto, String adresa, List<VrstaBibliotekara> uloge, boolean admin) {
		super(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa);
		this.uloge = uloge;
		this.admin = admin;
	}

	@Override
	public void prijava() {
		if (admin)
			MainFrameAdmin.getInstance(this);
		else
			MainFrameBibliotekar.getInstance(this);
	}

	@Override
	public void izmeniKorisnika() {
		BazaBibliotekara.getInstance().izmeniBibliotekara(this);
	}

	public List<VrstaBibliotekara> getUloge() {
		return uloge;
	}

	public void setUloge(List<VrstaBibliotekara> uloge) {
		this.uloge = uloge;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
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
