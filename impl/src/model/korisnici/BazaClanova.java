package model.korisnici;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.autor.Autor;
import model.enums.VrstaAutora;
import model.mesto.BazaMesto;
import model.mesto.Mesto;
import model.primerak.BazaZauzetPrimerak;
import model.primerak.ZauzetPrimerak;

public class BazaClanova {

	private static BazaClanova instance = null;

	public static BazaClanova getInstance() {
		if (instance == null) {
			instance = new BazaClanova();
		}
		return instance;
	}

	private List<Clan> clanovi;
	private List<String> kolone;

	private BazaClanova() {

		initClanove();

		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("IME");
		this.kolone.add("PREZIME");
		this.kolone.add("KORISNICKO IME");
		this.kolone.add("TIP");
		this.kolone.add("TRENUTNO CITA");
	}

	public List<Clan> getClanovi() {
		List<Clan> aktivniClanovi = new ArrayList<Clan>();
		for (Clan clan : clanovi) {
			if (!clan.isObrisan())
				aktivniClanovi.add(clan);
		}
		return aktivniClanovi;

	}

	public void setClanovi(List<Clan> clanovi) {
		this.clanovi = clanovi;
	}

	private void initClanove() {
		this.clanovi = new ArrayList<Clan>();
		File file = new File("./Baza/clanovi.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
				LocalDate datR1 = LocalDate.parse("2001-11-11");
				LocalDate datumIstekaClanarine = LocalDate.parse("2021-11-11");

				dodajClana("paja", "Paja", "Patak", "paja123", "1002001223232",
						BazaMesto.getInstance().getMesta().get(0), "Vuka Karadzica 1", VrstaClana.STUDENT, datR1,
						"05-11231-44", datumIstekaClanarine, "patkovic@gmail.com");

				dodajClana("miso", "Miso", "Misic", "miso123", "1232152123123",
						BazaMesto.getInstance().getMesta().get(1), "Rumenacka 2", VrstaClana.PENZIONER, datR1,
						"06-1223-412", datumIstekaClanarine, "miskovic@gmail.com");

			}
			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			while ((st = br.readLine()) != null) {
				String[] parts = st.split(";");
				long id = Long.parseLong(parts[0]);
				String korisnickoIme = parts[1];
				String ime = parts[2];
				String prezime = parts[3];
				String lozinka = parts[4];
				String jmbg = parts[5];

				Mesto mesto = null;
				for (Mesto m : BazaMesto.getInstance().getMesta()) {
					if (m.getNaziv().equals(parts[6])) {
						mesto = m;
					}
				}

				String adresa = parts[7];

				VrstaClana vrsta = VrstaClana.valueOf(parts[8]);

				LocalDate datumRodj = LocalDate.parse(parts[9]);
				String pozivNaBr = parts[10];
				LocalDate datumIstekaClan = LocalDate.parse(parts[11]);
				String eMail = parts[12];

				List<ZauzetPrimerak> iznPrimer = new ArrayList<ZauzetPrimerak>();
				String[] iznajmljeniID = parts[13].split(",");
				for (String iznajm : iznajmljeniID) {
					long izID = Long.parseLong(iznajm);
					BazaZauzetPrimerak bZPRIM = BazaZauzetPrimerak.getInstance();
					for (ZauzetPrimerak zPrim : bZPRIM.getZPrimerci()) {
						if (izID == zPrim.getId()) {
							iznPrimer.add(zPrim);
						}
					}
				}

				boolean izbrisan = Boolean.parseBoolean(parts[14]);
				Clan clan = new Clan(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa, vrsta, datumRodj,
						pozivNaBr, datumIstekaClan, eMail, iznPrimer, izbrisan);
				this.clanovi.add(clan);

			}
			br.close();
		} catch (Exception e) {

		}
	}

	public int getColumnCount() {
		return this.kolone.size();
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Clan getRow(int rowIndex) {
		return this.clanovi.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Clan clan = getClanovi().get(row);
		switch (column) {
		case 0:
			return Long.toString(clan.getId());
		case 1:
			return clan.getIme();
		case 2:
			return clan.getPrezime();
		case 3:
			return clan.getKorisnickoIme();
		case 4:
			return clan.getVrsta().name();
		case 5:
			return Integer.toString(clan.getBrTrenutnoIznajmljenih());
		default:
			return null;
		}
	}

	public void dodajClana(String korisnickoIme, String ime, String prezime, String lozinka, String jmbg, Mesto mesto,
			String adresa, VrstaClana vrsta, LocalDate datumRodj, String pozivNaBr, LocalDate datumIstekaClan,
			String eMail) {

		long id = BazaKorisnika.getInstance().generateId();
		Clan clan = new Clan(id, korisnickoIme, ime, prezime, lozinka, jmbg, mesto, adresa, vrsta, datumRodj, pozivNaBr,
				datumIstekaClan, eMail);
		this.clanovi.add(clan);

		dodajClanaUBazu(clan);
	}

	public void dodajClana(Clan clan) {
		this.clanovi.add(clan);
		dodajClanaUBazu(clan);
	}

	private void dodajClanaUBazu(Clan clan) {
		File file = new File("./Baza/clanovi.txt");

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			String insertString = "";
			insertString += Long.toString(clan.getId()) + ";";
			insertString += clan.getKorisnickoIme() + ";";
			insertString += clan.getIme() + ";";
			insertString += clan.getPrezime() + ";";
			insertString += clan.getLozinka() + ";";

			insertString += clan.getJmbg() + ";";
			insertString += clan.getMesto() + ";";
			insertString += clan.getAdresa() + ";";

			insertString += clan.getVrsta().name() + ";";
			insertString += clan.getDatumRodjenja().toString() + ";";
			insertString += clan.getPozivNaBr() + ";";
			insertString += clan.getDatumIstekaClanarine() + ";";
			insertString += clan.geteMail() + ";";
			insertString += (clan.isObrisan()) ? Boolean.toString(true) : Boolean.toString(false);
			out.println(insertString);

		} catch (Exception e) {

		}
	}

	public void izmeniClana(long id, String korisnickoIme, String ime, String prezime, String lozinka, String jmbg,
			Mesto mesto, String adresa, VrstaClana vrsta, LocalDate datumRodj, String pozivNaBr,
			LocalDate datumIstekaClan) {

		File file = new File("./Baza/clanovi.txt");
		try {
			FileWriter writer = new FileWriter(file, false);
			writer.append("");
			writer.close();
		} catch (Exception e) {

		}
		List<Clan> temp = new ArrayList<Clan>(this.clanovi);
		for (int i = 0; i < temp.size(); i++) {
			Clan c = temp.get(i);
			if (c.getId() == id) {
				c.setKorisnickoIme(korisnickoIme);
				c.setIme(ime);
				c.setPrezime(prezime);
				c.setLozinka(lozinka);
				c.setJmbg(jmbg);
				c.setMesto(mesto);
				c.setAdresa(adresa);
				c.setVrsta(vrsta);
				c.setDatumRodjenja(datumRodj);
				c.setPozivNaBr(pozivNaBr);
				c.setDatumIstekaClanarine(datumIstekaClan);

				this.clanovi.remove(0);

				dodajClana(c);
			} else {

				this.clanovi.remove(0);

				dodajClana(c);
			}
		}
	}

	public int getMaxBrIznajmljenihKnjigaZaClana(Clan clan) {
		switch (clan.getVrsta()) {
		case PREDSKOLAC:
		case UCENIK:
		case STUDENT:
		case PENZIONER:
			return 3;
		case ZAPOSLEN:
			return 5;
		default:
			return -1;
		}
	}

	public long getRokZaVracanjeZaClana(Clan clan) {
		switch (clan.getVrsta()) {
		case PREDSKOLAC:
		case UCENIK:
		case STUDENT:
		case ZAPOSLEN:
			return 15;
		case PENZIONER:
			return 21;
		default:
			return -1;
		}
	}

	public void dodajIznajmljeniPrimerakZaClana(Clan clan, ZauzetPrimerak zp) {
		clan.getIznajmljeniPrimerci().add(zp);

	}

	public String generateRandomPassword(int len) { // da li ovo treba u kontroler ili ovde?
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}

	public Vector<VrstaClana> getVrsteClanova() {
		Vector<VrstaClana> retList = new Vector<VrstaClana>();
		retList.add(VrstaClana.PREDSKOLAC);
		retList.add(VrstaClana.UCENIK);
		retList.add(VrstaClana.STUDENT);
		retList.add(VrstaClana.ZAPOSLEN);
		retList.add(VrstaClana.PENZIONER);

		return retList;
	}

	public Clan getClanZaIznajmljeniPrimerak(ZauzetPrimerak z) {
		Clan clan = null;
		for (Clan c : clanovi) {
			for (ZauzetPrimerak zauzeti : c.getIznajmljeniPrimerci()) {
				if (zauzeti.getId() == z.getId()) {
					clan = c;
					break;
				}
			}
		}
		return clan;
	}

	public List<ZauzetPrimerak> getTrenutnoIznajmljeniPrimerci() {
		List<ZauzetPrimerak> retList = new ArrayList<ZauzetPrimerak>();

		for (Clan clan : clanovi) {
			for (ZauzetPrimerak zauzetPrimerak : clan.getIznajmljeniPrimerci()) {
				if (!zauzetPrimerak.isVracen())
					retList.add(zauzetPrimerak);
			}
		}
		return retList;
	}

	public List<ZauzetPrimerak> getSviIznajmljeniPrimerci() {
		List<ZauzetPrimerak> retList = new ArrayList<ZauzetPrimerak>();

		for (Clan clan : clanovi) {
			for (ZauzetPrimerak zauzetPrimerak : clan.getIznajmljeniPrimerci()) {
				retList.add(zauzetPrimerak);
			}
		}
		return retList;
	}

	public List<ZauzetPrimerak> getSviIznajmljeniPrimerciZaClana(Clan ulogovaniClan) {
		List<ZauzetPrimerak> retList = new ArrayList<ZauzetPrimerak>();

		for (Clan clan : clanovi) {
			if (clan.getId() == ulogovaniClan.getId()) {
				for (ZauzetPrimerak zauzetPrimerak : clan.getIznajmljeniPrimerci()) {
					retList.add(zauzetPrimerak);
				}
			}
		}
		return retList;
	}

	public List<ZauzetPrimerak> getTrenutnoIznajmljeniPrimerciZaClana(Clan ulogovaniClan) {
		List<ZauzetPrimerak> retList = new ArrayList<ZauzetPrimerak>();

		for (Clan clan : clanovi) {
			if (clan.getId() == ulogovaniClan.getId()) {
				for (ZauzetPrimerak zauzetPrimerak : clan.getIznajmljeniPrimerci()) {
					if (!zauzetPrimerak.isVracen())
						retList.add(zauzetPrimerak);
				}
			}
		}
		return retList;
	}

}
