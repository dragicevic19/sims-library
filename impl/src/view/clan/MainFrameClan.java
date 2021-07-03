package view.clan;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.PrimerciController;
import model.korisnici.Clan;
import view.LoginFrame;
import view.bibliotekar.PromenaLozinkeDialog;
import view.bibliotekar.ZaduzenjaFrame;

public class MainFrameClan extends JFrame {

	private static final long serialVersionUID = -5737222110128467888L;

	private static MainFrameClan instance = null;
	private static Clan ulogovaniClan = null;

	public static MainFrameClan getInstance(Clan ulogovani) {
		if (instance == null) {
			ulogovaniClan = ulogovani;
			instance = new MainFrameClan();
		}
		return instance;
	}

	public MainFrameClan() {

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenDimension.width / 2, screenDimension.height / 2);
		setTitle("BIBLIOTEKA - clan");
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
		JButton btnRezerv = new JButton("Rezervacija knjige");
		JButton btnUplata = new JButton("Uplata clanarine");
		JButton btnPregTrenZaduz = new JButton("Trenutna zaduzenja"); // produzenje roka
		JButton btnPregIstorije = new JButton("Istorija zaduzenja");
		JButton btnRezervacije = new JButton("Pregled rezervacije");

		panelTop.add(btnRezerv);
		panelTop.add(btnUplata);
		panelTop.add(btnPregTrenZaduz);
		panelTop.add(btnPregIstorije);
		panelTop.add(btnRezervacije);

		this.add(panelTop, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button = new JButton("ODJAVA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da se odjavite?", "Odjava",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					instance = null;
					ulogovaniClan = null;
					dispose();
					LoginFrame.getInstance();
				}
			}
		});

		JButton btnPromenaLozinke = new JButton("PROMENA LOZINKE");
		btnPromenaLozinke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PromenaLozinkeDialog(ulogovaniClan);
			}
		});
		btnPromenaLozinke.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnPromenaLozinke);

		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(button);

		btnRezerv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// RezervacijaKnjigeFrame.getInstance();
			}
		});

		btnUplata.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// UplataClanarineFrame.getInstance();
			}
		});

		btnPregTrenZaduz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerciZaClana(ulogovaniClan).size() == 0)
					JOptionPane.showMessageDialog(null, "Trenutno nemate iznajmljenih primeraka");
				else
					ZaduzenjaFrame.getInstance("trenutna", ulogovaniClan);
			}
		});

		btnPregIstorije.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (PrimerciController.getInstance().getSviIznajmljeniPrimerciZaClana(ulogovaniClan).size() == 0)
					JOptionPane.showMessageDialog(null, "Nemate iznajmljenih primeraka");
				else
					ZaduzenjaFrame.getInstance("istorija", ulogovaniClan);
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
