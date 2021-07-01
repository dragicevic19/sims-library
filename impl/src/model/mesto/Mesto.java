package model.mesto;

public class Mesto {
	private String naziv;
	private String postanskiBr;

	public Mesto() {
		// TODO Auto-generated constructor stub
	}

	public Mesto(String naziv, String postanskiBr) {
		super();
		this.naziv = naziv;
		this.postanskiBr = postanskiBr;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPostanskiBr() {
		return postanskiBr;
	}

	public void setPostanskiBr(String postanskiBr) {
		this.postanskiBr = postanskiBr;
	}

	@Override
	public String toString() {
		return naziv;
	}

}
