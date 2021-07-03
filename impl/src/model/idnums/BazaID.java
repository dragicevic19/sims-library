package model.idnums;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class BazaID {
	
	
	private static BazaID instance = null;

	public static BazaID getInstance() {
		if (instance == null) {
			instance = new BazaID();
		}
		return instance;
	}
	
	private long idKorisnik;
	private long idAutor;
	private long idIzdanje;
	private long idKnjiga;

	private BazaID()
	{
		initKorisnik();
		initAutor();
		initIzdanje();
		initKnjiga();
		
	}
	public long getIdKorisnik() {
		this.idKorisnik++;
		File idKfile = new File("./idFolder/idKorisnik.txt");
		try {
	    	FileWriter writeHere = new FileWriter(idKfile, false);
	        String uling = Long.toString(idKorisnik);
	        writeHere.append(uling);
	        writeHere.close();
		} catch (Exception e) {
			
		}
		
		return idKorisnik;
	}

	public long getIdAutor() {
		this.idAutor++;
		File idKfile = new File("./idFolder/idAutor.txt");
		try {
	    	FileWriter writeHere = new FileWriter(idKfile, false);
	        String uling = Long.toString(idAutor);
	        writeHere.append(uling);
	        writeHere.close();
		} catch (Exception e) {
			
		}
		return idAutor;
	}

	public long getIdIzdanje() {
		this.idIzdanje++;
		File idKfile = new File("./idFolder/idIzdanje.txt");
		try {
	    	FileWriter writeHere = new FileWriter(idKfile, false);
	        String uling = Long.toString(idIzdanje);
	        writeHere.append(uling);
	        writeHere.close();
		} catch (Exception e) {
			
		}
		return idIzdanje;
	}

	public long getIdKnjiga() {
		this.idKnjiga++;
		File idKfile = new File("./idFolder/idKnjiga.txt");
		try {
	    	FileWriter writeHere = new FileWriter(idKfile, false);
	        String uling = Long.toString(idKnjiga);
	        writeHere.append(uling);
	        writeHere.close();
		} catch (Exception e) {
			
		}
		return idKnjiga;
	}
	
	private void initKorisnik()
	{
		File file = new File("./idFolder/idKorisnik.txt");

		try {
			if (!file.exists()) {
		        file.createNewFile();
		        String hello = "0";
		        FileWriter writer = new FileWriter(file, true);
		        writer.append(hello);
		        writer.close();
		    }
		BufferedReader br = new BufferedReader(new FileReader(file));
		  
		String st;
		while ((st = br.readLine()) != null)
		{
			
			this.idKorisnik = Long.parseLong(st);
		}
		br.close();
		} catch (Exception e) {
			
		}
	}
	
	private void initKnjiga()
	{
		File file = new File("./idFolder/idKnjiga.txt");
		try {
			if (!file.exists()) {
		        file.createNewFile();
		        String hello = "0";
		        FileWriter writer = new FileWriter(file, true);
		        writer.append(hello);
		        writer.close();
		    }
		BufferedReader br = new BufferedReader(new FileReader(file));
		  
		String st;
		while ((st = br.readLine()) != null)
			this.idKnjiga = Long.parseLong(st);
		br.close();
		} catch (Exception e) {
			
		}
	}
	
	private void initAutor()
	{
		File file = new File("./idFolder/idAutor.txt");
		try {
			if (!file.exists()) {
		        file.createNewFile();
		        String hello = "0";
		        FileWriter writer = new FileWriter(file, true);
		        writer.append(hello);
		        writer.close();
		    }
		BufferedReader br = new BufferedReader(new FileReader(file));
		  
		String st;
		while ((st = br.readLine()) != null)
			this.idAutor = Long.parseLong(st);
		br.close();
		} catch (Exception e) {
			
		}
	}
	
	private void initIzdanje()
	{
		File file = new File("./idFolder/idIzdanje.txt");
		try {
			if (!file.exists()) {
		        file.createNewFile();
		        String hello = "0";
		        FileWriter writer = new FileWriter(file, true);
		        writer.append(hello);
		        writer.close();
		    }
		BufferedReader br = new BufferedReader(new FileReader(file));
		  
		String st;
		while ((st = br.readLine()) != null)
			this.idIzdanje = Long.parseLong(st);
		br.close();
		} catch (Exception e) {
			
		}
	}

}
