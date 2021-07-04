package model.primerak;

import model.izdanje.Izdanje;
import model.knjiga.Knjiga;

public class Primerak {

	private long id;
	private Knjiga knjiga;
	private Izdanje izdanje;
	private boolean zauzet;
	private boolean iznosDozvoljen;
	private int polica;

	public Primerak() {
		super();
	}

	public Primerak(long id, Knjiga knjiga, Izdanje izdanje, boolean iznosDozvoljen, int polica) {
		this();
		this.id = id;
		this.knjiga = knjiga;
		this.izdanje = izdanje;
		this.zauzet = false;
		this.iznosDozvoljen = iznosDozvoljen;
		this.polica = polica;
	}

	public Primerak(long id, Knjiga knjiga, Izdanje izdanje, boolean zauzet, boolean iznosDozvoljen, int polica) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.izdanje = izdanje;
		this.zauzet = zauzet;
		this.iznosDozvoljen = iznosDozvoljen;
		this.polica = polica;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public Izdanje getIzdanje() {
		return izdanje;
	}

	public void setIzdanje(Izdanje izdanje) {
		this.izdanje = izdanje;
	}

	public boolean isZauzet() {
		return zauzet;
	}

	public void setZauzet(boolean zauzet) {
		this.zauzet = zauzet;
	}

	public boolean isIznosDozvoljen() {
		return iznosDozvoljen;
	}

	public void setIznosDozvoljen(boolean iznosDozvoljen) {
		this.iznosDozvoljen = iznosDozvoljen;
	}

	public int getPolica() {
		return polica;
	}

	public void setPolica(int polica) {
		this.polica = polica;
	}

	@Override
	public String toString() {
		return "id=" + id + ", " + izdanje + ", polica: " + polica;
	}

}
