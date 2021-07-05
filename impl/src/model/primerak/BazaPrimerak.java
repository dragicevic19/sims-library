package model.primerak;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.idnums.BazaID;
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

		File file = new File("./Baza/primerci.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(0),
						BazaIzdanja.getInstance().getIzdanja().get(0), true, 0);
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(0),
						BazaIzdanja.getInstance().getIzdanja().get(0), true, 0);
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(0),
						BazaIzdanja.getInstance().getIzdanja().get(0), true, 0);
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(0),
						BazaIzdanja.getInstance().getIzdanja().get(0), true, 0);
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(0),
						BazaIzdanja.getInstance().getIzdanja().get(0), true, 0);
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(0),
						BazaIzdanja.getInstance().getIzdanja().get(0), true, 0);

				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(0),
						BazaIzdanja.getInstance().getIzdanja().get(1), false, 1);
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(0),
						BazaIzdanja.getInstance().getIzdanja().get(1), false, 1);

				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(1),
						BazaIzdanja.getInstance().getIzdanja().get(1), false, 2);
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(1),
						BazaIzdanja.getInstance().getIzdanja().get(0), true, 3);
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(1),
						BazaIzdanja.getInstance().getIzdanja().get(0), true, 3);
				dodajPrimerak(BazaKnjiga.getInstance().getKnjige().get(1),
						BazaIzdanja.getInstance().getIzdanja().get(0), true, 3);

			}

			else {
				BufferedReader br = new BufferedReader(new FileReader(file));

				String st;
				while ((st = br.readLine()) != null) {
					String[] parts = st.split(";");
					long id = Long.parseLong(parts[0]);

					long knjID = Long.parseLong(parts[1]);
					Knjiga knjiga = null;
					BazaKnjiga bKnjiga = BazaKnjiga.getInstance();
					for (Knjiga k : bKnjiga.getKnjige()) {
						if (k.getId() == knjID) {
							knjiga = k;
						}
					}

					long izdID = Long.parseLong(parts[2]);
					Izdanje izdanje = null;
					BazaIzdanja bIZD = BazaIzdanja.getInstance();
					for (Izdanje i : bIZD.getIzdanja()) {
						if (izdID == i.getId()) {
							izdanje = i;
						}
					}

					boolean zauzet = Boolean.parseBoolean(parts[3]);

					boolean izDov = Boolean.parseBoolean(parts[4]);
					int polica = Integer.parseInt(parts[5]);

					Primerak primerak = new Primerak(id, knjiga, izdanje, zauzet, izDov, polica);
					this.primerci.add(primerak);
				}
				br.close();
			}
		} catch (Exception e) {

		}

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

		long id = BazaID.getInstance().getIdPrimerak();
		Primerak primerak = new Primerak(id, knjiga, izdanje, iznosDozvoljen, polica);
		this.primerci.add(primerak);

		dodajPrimerakUBazu(primerak);
	}

	public void dodajPrimerak(Primerak primerak) {
		this.primerci.add(primerak);
		dodajPrimerakUBazu(primerak);
	}

	private void dodajPrimerakUBazu(Primerak primerak) {
		File file = new File("./Baza/primerci.txt");

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			String insertString = "";
			insertString += Long.toString(primerak.getId()) + ";";
			insertString += Long.toString(primerak.getKnjiga().getId()) + ";";
			insertString += Long.toString(primerak.getIzdanje().getId()) + ";";
			insertString += Boolean.toString(primerak.isZauzet()) + ";";
			insertString += Boolean.toString(primerak.isIznosDozvoljen()) + ";";
			insertString += Integer.toString(primerak.getPolica()) + ";";
			out.println(insertString);

		} catch (Exception e) {

		}
	}

	public void izmeniPrimerak(Primerak p) {

		File file = new File("./Baza/primerci.txt");
		try {
			FileWriter writer = new FileWriter(file, false);
			writer.append("");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Primerak> temp = new ArrayList<>(this.primerci);
		for (int i = 0; i < temp.size(); i++) {
			Primerak primerak = temp.get(i);
			if (primerak.getId() == p.getId()) {
				primerak.setKnjiga(p.getKnjiga());
				primerak.setIzdanje(p.getIzdanje());
				primerak.setZauzet(p.isZauzet());
				primerak.setIznosDozvoljen(p.isIznosDozvoljen());
				primerak.setPolica(p.getPolica());

				this.primerci.remove(0);

				dodajPrimerak(primerak);
			} else {

				this.primerci.remove(0);

				dodajPrimerak(primerak);
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
		izmeniPrimerak(p);
	}

	public List<ZauzetPrimerak> getTrenutnoIznajmljeniPrimerci() {
		return BazaClanova.getInstance().getTrenutnoIznajmljeniPrimerci();
	}

	public List<ZauzetPrimerak> getSviIznajmljeniPrimerci() {
		return BazaClanova.getInstance().getSviIznajmljeniPrimerci();

	}

	public List<ZauzetPrimerak> getSviIznajmljeniPrimerciZaClana(Clan ulogovaniClan) {
		return BazaClanova.getInstance().getSviIznajmljeniPrimerciZaClana(ulogovaniClan);
	}

	public List<ZauzetPrimerak> getTrenutnoIznajmljeniPrimerciZaClana(Clan ulogovaniClan) {
		return BazaClanova.getInstance().getTrenutnoIznajmljeniPrimerciZaClana(ulogovaniClan);
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
		case 6:
			return z.getDatumVracanja().toString();
		default:
			return null;
		}
	}

}
