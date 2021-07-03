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
import model.korisnici.Admin;

public class MainFrameAdmin extends JFrame {

	private static final long serialVersionUID = 4703527718003660405L;

	private static MainFrameAdmin instance = null;
	private static Admin ulogovaniAdmin = null;

	public static MainFrameAdmin getInstance(Admin ulogovaniAd) {
		if (instance == null) {
			ulogovaniAdmin = ulogovaniAd;
			instance = new MainFrameAdmin();
		}
		return instance;
	}

	public MainFrameAdmin() {

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenDimension.width / 2, screenDimension.height / 2);
		setTitle("BIBLIOTEKA - Admin");
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
		JButton btnBlokiranje = new JButton("Blokiranje clana");
		JButton btnPovratakKnjige = new JButton("Povratak knjige");

		JButton btnPregTrenZaduz = new JButton("Trenutna zaduzenja");
		JButton btnPregIstorije = new JButton("Istorija zaduzenja");
		JButton btnRezervacije = new JButton("Rezervacije");

		panelTop.add(btnRegistracija);
		panelTop.add(btnBlokiranje);
		panelTop.add(btnIznajmi);
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

		btnBlokiranje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BlokiranjeFrame.getInstance();
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
				ZaduzenjaFrame.getInstance("istorija", null);
			}
		});

		btnRezervacije.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//RezervacijeFrame.getInstance();
			}
		});
	}
}

