package model.primerak;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.izdanje.BazaIzdanja;
import model.izdanje.Izdanje;
import model.knjiga.BazaKnjiga;
import model.knjiga.Knjiga;
import model.korisnici.BazaClanova;
import model.korisnici.Clan;

public class BazaPrimerak {

	private static BazaPrimerak instance = null;

	public static BazaPrimerak getInstance() {
		if (instance == null) {
			instance = new BazaPrimerak();
		}
		return instance;
	}

	private long generator;
	private long generatorZauzetog;

	private List<Primerak> primerci;
	private List<String> kolone;

	private BazaPrimerak() {
		generator = 0;
		generatorZauzetog = 0;

		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("IZDAVAC");
		this.kolone.add("GOD. IZDANJA");
		this.kolone.add("POLICA");
		this.kolone.add("IZNOS DOZVOLJEN");
		this.kolone.add("ZAUZET");

		initPrimerci();
	}

	private void initPrimerci() {
		this.primerci = new ArrayList<Primerak>();
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(0),
				BazaIzdanja.getInstance().getIzdanja().get(0), true, 0));
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(0),
				BazaIzdanja.getInstance().getIzdanja().get(0), true, 0));
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(0),
				BazaIzdanja.getInstance().getIzdanja().get(0), true, 0));
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(0),
				BazaIzdanja.getInstance().getIzdanja().get(0), true, 0));
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(0),
				BazaIzdanja.getInstance().getIzdanja().get(0), true, 0));
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(0),
				BazaIzdanja.getInstance().getIzdanja().get(0), true, 0));

		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(0),
				BazaIzdanja.getInstance().getIzdanja().get(1), false, 1));
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(0),
				BazaIzdanja.getInstance().getIzdanja().get(1), false, 1));

		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(1),
				BazaIzdanja.getInstance().getIzdanja().get(1), false, 2));
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(1),
				BazaIzdanja.getInstance().getIzdanja().get(0), true, 3));
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(1),
				BazaIzdanja.getInstance().getIzdanja().get(0), true, 3));
		primerci.add(new Primerak(generateId(), BazaKnjiga.getInstance().getKnjige().get(1),
				BazaIzdanja.getInstance().getIzdanja().get(0), true, 3));

	}

	public List<Primerak> getPrimerci() {
		return primerci;
	}

	public void setKnjige(List<Primerak> primerci) {
		this.primerci = primerci;
	}

	private long generateId() {
		return ++generator;
	}

	public long generateIdZauzetog() {
		return ++generatorZauzetog;
	}

	public int getColumnCount() {
		return this.kolone.size();
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Primerak getRow(int rowIndex) {
		return this.primerci.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Primerak primerak = this.primerci.get(row);

		switch (column) {
		case 0:
			return Long.toString(primerak.getId());
		case 1:
			return primerak.getIzdanje().getIzdavac();
		case 2:
			return primerak.getIzdanje().getGodIzdanja();
		case 3:
			return Integer.toString(primerak.getPolica());
		case 4:
			return Boolean.toString(primerak.isIznosDozvoljen());
		case 5:
			return Boolean.toString(primerak.isZauzet());
		default:
			return null;
		}
	}

	public void dodajPrimerak(Knjiga knjiga, Izdanje izdanje, boolean iznosDozvoljen, int polica) {
		this.primerci.add(new Primerak(generateId(), knjiga, izdanje, iznosDozvoljen, polica));
		// dodaj u bazu
	}

	/*
	 * da li treba?? public void izbrisiPrimerak(long id) { for (Primerak i :
	 * primerci) { if (i.getId() == id) { primerci.remove(i); break; } } }
	 */

	public void izmeniPrimerak(long id, Knjiga knjiga, Izdanje izdanje, boolean zauzet, boolean iznosDozvoljen,
			int polica) {
		for (Primerak p : primerci) {
			if (p.getId() == id) {
				p.setKnjiga(knjiga);
				p.setIzdanje(izdanje);
				p.setZauzet(zauzet);
				p.setIznosDozvoljen(iznosDozvoljen);
				p.setPolica(polica);
			}
		}
	}

	public Vector<Primerak> getPrimerciZaIznajmljivanje(Knjiga knjiga) {
		Vector<Primerak> slobodniPrimerci = new Vector<Primerak>();

		for (Primerak primerak : primerci) {
			if (primerak.getKnjiga() == knjiga) {
				if (primerak.isIznosDozvoljen() && !primerak.isZauzet())
					slobodniPrimerci.add(primerak);
			}
		}
		return slobodniPrimerci;
	}

	public void iznajmljenPrimerak(Primerak p) {
		p.setZauzet(true);
	}

	public List<ZauzetPrimerak> getTrenutnoIznajmljeniPrimerci() {
		return BazaClanova.getInstance().getTrenutnoIznajmljeniPrimerci();
	}

	public List<ZauzetPrimerak> getSviIznajmljeniPrimerci() {
		return BazaClanova.getInstance().getSviIznajmljeniPrimerci();

	}

	public Object zauzetiPrimerakToCell(ZauzetPrimerak z, Clan clan, int col) {

		switch (col) {
		case 0:
			return clan.getId();
		case 1:
			return clan.getIme();
		case 2:
			return clan.getPrezime();
		case 3:
			return clan.getKorisnickoIme();
		case 4:
			return z.getPrimerak().getId();
		case 5:
			return z.getPrimerak().getKnjiga().getNaslov();
		default:
			return null;
		}
	}

}
