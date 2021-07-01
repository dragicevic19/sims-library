package model.knjiga;

import java.util.ArrayList;
import java.util.List;

import model.autor.Autor;
import model.enums.Zanr;

public class Knjiga {

	private long id;
	private String naslov;
	private String format;
	private String opis;
	private List<String> tagovi;
	private List<Autor> autori;
	private List<Zanr> zanrovi;

	public Knjiga() {
		this.autori = new ArrayList<Autor>();
		this.tagovi = new ArrayList<String>();
		this.zanrovi = new ArrayList<Zanr>();
	}

	public Knjiga(long id, String naslov, String format, String opis, List<String> tagovi, List<Autor> autori,
			List<Zanr> zanrovi) {
		this();
		this.id = id;
		this.naslov = naslov;
		this.format = format;
		this.opis = opis;
		this.tagovi = tagovi;
		this.autori = autori;
		this.zanrovi = zanrovi;
	}

	public long getId() {
		return id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<String> getTagovi() {
		return tagovi;
	}

	public void setTagovi(List<String> tagovi) {
		this.tagovi = tagovi;
	}

	public List<Zanr> getZanrovi() {
		return zanrovi;
	}

	public void setZanrovi(List<Zanr> zanrovi) {
		this.zanrovi = zanrovi;
	}

	public List<Autor> getAutori() {
		return autori;
	}

	public void setAutori(List<Autor> autori) {
		this.autori = autori;
	}

	public String autoriToString() {
		String retString = "";
		for (Autor autor : autori) {
			retString += autor.toString();
		}

		return retString;
	}

	public String zanroviToString() {
		String str = "";
		for (Zanr zanr : zanrovi) {
			str += zanr.toString() + ", ";
		}

		String finalString = (String) str.subSequence(0, str.lastIndexOf(", "));
		return finalString;
	}

	public String tagoviToString() {
		String str = "";

		for (String tag : tagovi) {
			str += tag + ", ";
		}

		String retString = (String) str.subSequence(0, str.lastIndexOf(", "));
		return retString;
	}

	@Override
	public String toString() {
		return "Knjiga [id=" + id + ", naslov=" + naslov + "]";
	}

}
