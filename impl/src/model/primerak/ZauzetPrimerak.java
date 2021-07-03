package model.primerak;

import java.time.LocalDate;

import model.idnums.BazaID;

public class ZauzetPrimerak {

	private long id;
	private LocalDate datumVracanja;
	private boolean rokProduzen;
	private boolean vracen;
	private Primerak primerak;
	private Revizija revizija;

	public ZauzetPrimerak() {
		this.rokProduzen = false;
		this.vracen = false;
		this.revizija = null;
	}

	public ZauzetPrimerak(long id, LocalDate datumVracanja, boolean rokProduzen, boolean vracen, Primerak primerak,
			Revizija revizija) {
		super();
		this.id = id;
		this.datumVracanja = datumVracanja;
		this.rokProduzen = rokProduzen;
		this.vracen = vracen;
		this.primerak = primerak;
		this.revizija = revizija;
	}

	public ZauzetPrimerak(Primerak p, long id) {
		this();
		this.id = id;
		this.primerak = p;
	}
	
	public ZauzetPrimerak(Primerak p) {
		this();
		this.id = BazaID.getInstance().getIdZPrimerak();
		this.primerak = p;
	}
	
	public ZauzetPrimerak(Primerak p, long id, LocalDate datumVracanja, boolean rokProduzen, boolean vracen) {
		this.id = id;
		this.vracen = vracen;
		this.datumVracanja = datumVracanja;
		this.rokProduzen = rokProduzen;
		this.primerak = p;
		this.revizija = null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDatumVracanja() {
		return datumVracanja;
	}

	public void setDatumVracanja(LocalDate datumVracanja) {
		this.datumVracanja = datumVracanja;
	}

	public boolean isRokProduzen() {
		return rokProduzen;
	}

	public void setRokProduzen(boolean rokProduzen) {
		this.rokProduzen = rokProduzen;
	}

	public boolean isVracen() {
		return vracen;
	}

	public void setVracen(boolean vracen) {
		this.vracen = vracen;
	}

	public Primerak getPrimerak() {
		return primerak;
	}

	public void setPrimerak(Primerak primerak) {
		this.primerak = primerak;
	}

	public Revizija getRevizija() {
		return revizija;
	}

	public void setRevizija(Revizija revizija) {
		this.revizija = revizija;
	}

}
