package model.primerak;

import java.time.LocalDate;

public class ZauzetPrimerak {

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

	public ZauzetPrimerak(LocalDate datumVracanja, boolean rokProduzen, boolean vracen, Primerak primerak,
			Revizija revizija) {
		super();
		this.datumVracanja = datumVracanja;
		this.rokProduzen = rokProduzen;
		this.vracen = vracen;
		this.primerak = primerak;
		this.revizija = revizija;
	}

	public ZauzetPrimerak(Primerak p) {
		this();
		this.primerak = p;
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
