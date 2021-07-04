package model.korisnici;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.mesto.Mesto;
import model.primerak.ZauzetPrimerak;

public class Clan extends Korisnik {

	private VrstaClana vrsta;
	private LocalDate datumRodjenja;
	private String pozivNaBr;
	private LocalDate datumIstekaClanarine;
	private List<ZauzetPrimerak> iznajmljeniPrimerci;
	private String eMail;
	// private List<RezervacijaKnjige> rezervisaniPrimerci;

	public Clan() {
		this.iznajmljeniPrimerci = new ArrayList<ZauzetPrimerak>();
		// this.rezervisaniPrimerci = new ArrayList<RezervacijaKnjige>();
	}

	public Clan(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg, Mesto mesto,
			String adresa, VrstaClana vrsta) {
		super(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa);
		this.vrsta = vrsta;
		this.iznajmljeniPrimerci = new ArrayList<ZauzetPrimerak>();
		// this.rezervisaniPrimerci = new ArrayList<RezervacijaKnjige>();
	}

	public Clan(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg, Mesto mesto,
			String adresa, VrstaClana vrsta, LocalDate datumRodj, String pozivNaBr, LocalDate datumIstekaClan,
			String eMail) {
		super(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa);
		this.vrsta = vrsta;
		this.datumRodjenja = datumRodj;
		this.pozivNaBr = pozivNaBr;
		this.datumIstekaClanarine = datumIstekaClan;
		this.iznajmljeniPrimerci = new ArrayList<ZauzetPrimerak>();
		this.eMail = eMail;
		// this.rezervisaniPrimerci = new ArrayList<RezervacijaKnjige>();
	}

	public Clan(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg, Mesto mesto,
			String adresa, VrstaClana vrsta, LocalDate datumRodj, String pozivNaBr, LocalDate datumIstekaClan,
			String eMail, List<ZauzetPrimerak> iznajmljeniPrimeci, boolean izbrisan) {
		super(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa);
		this.vrsta = vrsta;
		this.datumRodjenja = datumRodj;
		this.pozivNaBr = pozivNaBr;
		this.datumIstekaClanarine = datumIstekaClan;
		this.iznajmljeniPrimerci = iznajmljeniPrimeci;
		this.obrisan = izbrisan;
		this.eMail = eMail;
		// this.rezervisaniPrimerci = new ArrayList<RezervacijaKnjige>();
	}

	public VrstaClana getVrsta() {
		return vrsta;
	}

	public void setVrsta(VrstaClana vrsta) {
		this.vrsta = vrsta;
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

	public LocalDate getDatumIstekaClanarine() {
		return datumIstekaClanarine;
	}

	public void setDatumIstekaClanarine(LocalDate datumIstekaClanarine) {
		this.datumIstekaClanarine = datumIstekaClanarine;
	}

	public List<ZauzetPrimerak> getIznajmljeniPrimerci() {
		return iznajmljeniPrimerci;
	}

	public void setIznajmljeniPrimerci(List<ZauzetPrimerak> iznPrimerci) {
		this.iznajmljeniPrimerci = iznPrimerci;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public int getBrTrenutnoIznajmljenih() {
		int brojac = 0;
		for (ZauzetPrimerak zauzPrimerak : iznajmljeniPrimerci) {
			if (!zauzPrimerak.isVracen())
				brojac++;
		}
		return brojac;
	}

	/*
	 * public List<RezervacijaKnjige> getRezervisaniPrimerci() { return
	 * rezervisaniPrimerci; }
	 * 
	 * public void setRezervisaniPrimerci(List<RezervacijaKnjige>
	 * rezervisaniPrimerci) { this.rezervisaniPrimerci = rezervisaniPrimerci; }
	 */

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

	public String iznajmljeniToFile() {
		String str = "";

		if (iznajmljeniPrimerci.size() == 0)
			return str;

		for (ZauzetPrimerak zauzetPrimerak : iznajmljeniPrimerci) {
			str += zauzetPrimerak.getId() + ",";
		}
		return (String) str.subSequence(0, str.lastIndexOf(","));

	}

}
