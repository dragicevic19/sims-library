package view.bibliotekar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.BibliotekariController;
import controller.ClanoviController;
import controller.PrimerciController;
import model.korisnici.Bibliotekar;

public class MainFrameBibliotekar extends JFrame {

	private static final long serialVersionUID = 4703527718003660405L;

	private static MainFrameBibliotekar instance = null;
	private static Bibliotekar ulogovaniBibliotekar = null;

	public static MainFrameBibliotekar getInstance(Bibliotekar ulogovaniBib) {
		if (instance == null) {
			ulogovaniBibliotekar = ulogovaniBib;
			instance = new MainFrameBibliotekar();
		}
		return instance;
	}

	public MainFrameBibliotekar() {

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenDimension.width / 2, screenDimension.height / 2);
		setTitle("BIBLIOTEKA - bibilotekar");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da napustite aplikaciju?",
						"Izadji?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		inicijalizujAkcije();
	}

	private void inicijalizujAkcije() {
		JPanel panelTop = new JPanel();
		JButton btnIznajmi = new JButton("Iznajmi knjigu");
		JButton btnRegistracija = new JButton("Registracija clana");
		JButton btnPovratakKnjige = new JButton("Povratak knjige");
		JButton btnAzurKnjige = new JButton("Unos/Azuriranje knjiga");

		JButton btnPregTrenZaduz = new JButton("Trenutna zaduzenja");
		JButton btnPregIstorije = new JButton("Istorija zaduzenja");
		JButton btnRezervacije = new JButton("Rezervacije");

		panelTop.add(btnRegistracija);
		panelTop.add(btnIznajmi);
		// panelTop.add(btnPovratakKnjige); moze u pregledu zaduzenja da oznaci koja je
		// knjiga vracena
		panelTop.add(btnAzurKnjige);
		panelTop.add(btnPregTrenZaduz);
		panelTop.add(btnPregIstorije);
		panelTop.add(btnRezervacije);

		this.add(panelTop, BorderLayout.NORTH);

		btnRegistracija.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegistracijaClanaFrame.getInstance();
			}
		});

		btnIznajmi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ClanoviController.getInstance().getClanovi().size() == 0) {
					JOptionPane.showMessageDialog(null, "Jos uvek ne postoji nijedan clan!");
				} else {
					IznajmljivanjeFrame.getInstance();
				}
			}
		});

		btnAzurKnjige.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// AzurKnjigeFrame.getInstance();
			}
		});

		btnPregTrenZaduz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerci().size() == 0)
					JOptionPane.showMessageDialog(null, "Trenutno nema iznajmljenih primeraka");
				else
					TrenutnaZaduzenjaFrame.getInstance();
			}
		});

		btnPregIstorije.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// IstorijaZaduzenjaFrame.getInstance();
			}
		});

		btnRezervacije.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// RezervacijeFrame.getInstance();
			}
		});
	}
}
