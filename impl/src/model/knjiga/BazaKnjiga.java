package model.knjiga;

import java.util.ArrayList;
import java.util.List;

import model.autor.Autor;
import model.autor.BazaAutor;
import model.enums.Zanr;

public class BazaKnjiga {

	private static BazaKnjiga instance = null;

	public static BazaKnjiga getInstance() {
		if (instance == null) {
			instance = new BazaKnjiga();
		}
		return instance;
	}

	private long generator;

	private List<Knjiga> knjige;
	private List<String> kolone;

	private BazaKnjiga() {
		generator = 0;

		this.knjige = new ArrayList<Knjiga>();
		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("NASLOV");
		this.kolone.add("AUTORI");
		this.kolone.add("ZANROVI");
		this.kolone.add("OPIS");
		this.kolone.add("TAGOVI");
		this.kolone.add("FORMAT");

		initKnjige();
	}

	private void initKnjige() {
		this.knjige = new ArrayList<Knjiga>();
		List<String> tagovi = new ArrayList<String>();
		tagovi.add("nauka");
		tagovi.add("tehnologija");
		tagovi.add("fakultet");
		List<Zanr> zanrovi = new ArrayList<Zanr>();
		zanrovi.add(Zanr.SCIFI);

		knjige.add(new Knjiga(generateId(), "Paralelno programiranje", "A5", "Opis knjige Paralelno programiranje...",
				tagovi, BazaAutor.getInstance().getAutori(), zanrovi));

		knjige.add(new Knjiga(generateId(), "Sistemska softverska podrska", "A5", "Opis knjige SSP...", tagovi,
				BazaAutor.getInstance().getAutori(), zanrovi));

	}

	public List<Knjiga> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<Knjiga> knjige) {
		this.knjige = knjige;
	}

	private long generateId() {
		return ++generator;
	}

	public int getColumnCount() {
		return this.kolone.size();
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Knjiga getRow(int rowIndex) {
		return this.knjige.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Knjiga knjiga = this.knjige.get(row);
		switch (column) {
		case 0:
			return Long.toString(knjiga.getId());
		case 1:
			return knjiga.getNaslov();
		case 2:
			return knjiga.autoriToString();
		case 3:
			return knjiga.zanroviToString();
		case 4:
			return knjiga.getOpis();
		case 5:
			return knjiga.tagoviToString();
		case 6:
			return knjiga.getFormat();
		default:
			return null;
		}
	}

	public void dodajKnjigu(String naslov, String format, String opis, ArrayList<String> tagovi,
			ArrayList<Autor> autori, ArrayList<Zanr> zanrovi) {
		this.knjige.add(new Knjiga(generateId(), naslov, format, opis, tagovi, autori, zanrovi));
		// dodaj u bazu
	}

	/*
	 * da li treba?? public void izbrisiKnjigu(long id) { for (Knjiga i : knjige) {
	 * if (i.getId() == id) { knjige.remove(i); break; } } }
	 */

	public void izmeniKnjigu(long id, String naslov, String format, String opis, ArrayList<String> tagovi,
			ArrayList<Autor> autori, ArrayList<Zanr> zanrovi) {
		for (Knjiga k : knjige) {
			if (k.getId() == id) {
				k.setNaslov(naslov);
				k.setFormat(format);
				k.setAutori(autori);
				k.setOpis(opis);
				k.setTagovi(tagovi);
				k.setZanrovi(zanrovi);
			}
		}
	}
}
