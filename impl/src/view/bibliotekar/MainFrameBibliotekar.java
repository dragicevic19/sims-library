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

import controller.ClanoviController;
import controller.PrimerciController;
import model.korisnici.Bibliotekar;
import view.LoginFrame;

import java.awt.Font;

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
		btnIznajmi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JButton btnRegistracija = new JButton("Registracija clana");
		btnRegistracija.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JButton btnAzurKnjige = new JButton("Unos/Azuriranje knjiga");
		btnAzurKnjige.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JButton btnPregTrenZaduz = new JButton("Trenutna zaduzenja");
		btnPregTrenZaduz.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JButton btnPregIstorije = new JButton("Istorija zaduzenja");
		btnPregIstorije.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JButton btnRezervacije = new JButton("Rezervacije");
		btnRezervacije.setFont(new Font("Tahoma", Font.PLAIN, 13));

		panelTop.add(btnRegistracija);
		panelTop.add(btnIznajmi);
		panelTop.add(btnAzurKnjige);
		panelTop.add(btnPregTrenZaduz);
		panelTop.add(btnPregIstorije);
		panelTop.add(btnRezervacije);

		getContentPane().add(panelTop, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button = new JButton("ODJAVA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da se odjavite?", "Odjava",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					instance = null;
					ulogovaniBibliotekar = null;
					dispose();
					LoginFrame.getInstance();
				}
			}
		});

		JButton btnPromenaLozinke = new JButton("PROMENA LOZINKE");
		btnPromenaLozinke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PromenaLozinkeDialog(ulogovaniBibliotekar);
			}
		});
		btnPromenaLozinke.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnPromenaLozinke);

		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(button);

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
					ZaduzenjaFrame.getInstance("trenutna", null);
			}
		});

		btnPregIstorije.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (PrimerciController.getInstance().getSviIznajmljeniPrimerci().size() == 0)
					JOptionPane.showMessageDialog(null, "Nema iznajmljenih primeraka");
				else
					ZaduzenjaFrame.getInstance("istorija", null);
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
