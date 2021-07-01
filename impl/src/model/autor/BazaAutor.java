package model.autor;

import java.util.ArrayList;
import java.util.List;

import model.enums.VrstaAutora;

public class BazaAutor {

	private static BazaAutor instance = null;

	public static BazaAutor getInstance() {
		if (instance == null) {
			instance = new BazaAutor();
		}
		return instance;
	}

	private long generator;

	private List<Autor> autori;
	private List<String> kolone;

	private BazaAutor() {
		generator = 0;

		initAutore();

		this.kolone = new ArrayList<String>();
		this.kolone.add("ID");
		this.kolone.add("IME");
		this.kolone.add("PREZIME");
		this.kolone.add("VRSTA AUTORA");

	}

	private void initAutore() {
		this.autori = new ArrayList<Autor>();
		autori.add(new Autor(generateId(), "Mika", "Mikic", VrstaAutora.PISAC));
		autori.add(new Autor(generateId(), "Zika", "Zikic", VrstaAutora.ILUSTRATOR));
		autori.add(new Autor(generateId(), "Pera", "Peric", VrstaAutora.PREVODIOC));
	}

	public List<Autor> getAutori() {
		return autori;
	}

	public void setIgraci(List<Autor> autori) {
		this.autori = autori;
	}

	private long generateId() {
		return ++generator;
	}

	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Autor getRow(int rowIndex) {
		return this.autori.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Autor autor = this.autori.get(row);
		switch (column) {
		case 0:
			return Long.toString(autor.getId());
		case 1:
			return autor.getIme();
		case 2:
			return autor.getPrezime();
		case 3:
			return autor.getVrstaAutora().toString();
		default:
			return null;
		}
	}

	public void dodajAutora(String ime, String prezime, VrstaAutora vrstaAutora) {
		this.autori.add(new Autor(generateId(), ime, prezime, vrstaAutora));
		// dodaj u bazu
	}

	/*
	 * da li treba brisati autora?? public void izbrisiAutora(long id) { for (Autor
	 * i : autori) { if (i.getId() == id) { i.ob //autori.remove(i); break; } } }
	 */

	public void izmeniAutora(long id, String ime, String prezime, VrstaAutora vrstaAutora) {
		for (Autor i : autori) {
			if (i.getId() == id) {
				i.setIme(ime);
				i.setPrezime(prezime);
				i.setVrstaAutora(vrstaAutora);
			}
		}
	}

}
