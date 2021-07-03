package model.primerak;

public class Revizija {
	private String komentar;
	private int ocena;
	private boolean moderisano;

	public Revizija() {
		this.moderisano = false;
	}

	public Revizija(String komentar, int ocena, boolean moderisano) {
		super();
		this.komentar = komentar;
		this.ocena = ocena;
		this.moderisano = moderisano;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public boolean isModerisano() {
		return moderisano;
	}

	public void setModerisano(boolean moderisano) {
		this.moderisano = moderisano;
	}

}
