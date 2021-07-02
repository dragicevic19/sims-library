package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.KorisniciController;
import model.korisnici.Bibliotekar;
import model.korisnici.Clan;
import model.korisnici.Korisnik;
import net.miginfocom.swing.MigLayout;
import view.bibliotekar.MainFrameBibliotekar;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Korisnik ulogovaniKorisnik;
	private static LoginFrame instance = null;

	public static LoginFrame getInstance() {

		if (instance == null) {
			instance = new LoginFrame();
		}

		return instance;
	}

	public LoginFrame() {
		loginDialog();
	}

	private void loginDialog() {
		JDialog d = new JDialog();
		d.setTitle("Prijava");
		d.setLocationRelativeTo(null);
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		d.setResizable(false);
		initLoginGUI(d);
		d.pack();
		d.setVisible(true);
	}

	private void initLoginGUI(JDialog dialog) {

		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		dialog.setLayout(layout);

		JTextField tfKorisnickoIme = new JTextField(20);
		JPasswordField pfLozinka = new JPasswordField(20);
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		dialog.getRootPane().setDefaultButton(btnOk);

		dialog.add(new JLabel("Dobrodosli. Molimo da se prijavite."), "span 2");
		dialog.add(new JLabel("Korisnicko ime"));
		dialog.add(tfKorisnickoIme);
		dialog.add(new JLabel("Sifra"));
		dialog.add(pfLozinka);
		dialog.add(new JLabel());
		dialog.add(btnOk, "split 2");
		dialog.add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = tfKorisnickoIme.getText().trim();
				String lozinka = new String(pfLozinka.getPassword()).trim();

				ulogovaniKorisnik = KorisniciController.getInstance().proveriLoginPodatke(korisnickoIme, lozinka);
				if (ulogovaniKorisnik == null)
					JOptionPane.showMessageDialog(null, "Niste uneli ispravne podatke.", "GRESKA",
							JOptionPane.ERROR_MESSAGE);

				else {
					dialog.setVisible(false);
					dialog.dispose();
					LoginFrame.this.setVisible(true);
					mainFrame();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
	}

	private void mainFrame() {

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);

		initMainGUI();
	}

	private void initMainGUI() {

		if (this.ulogovaniKorisnik instanceof Clan)
			System.out.println("Clan");
		// MainFrameClan.getInstance((Clan) this.ulogovaniKorisnik);

		else if (this.ulogovaniKorisnik instanceof Bibliotekar) {
			dispose();
			MainFrameBibliotekar.getInstance((Bibliotekar) this.ulogovaniKorisnik);
		}
		/*
		 * else if (this.ulogovaniKorisnik instanceof Administrator)
		 * MainFrameAdmin.getInstance((Administrator) this.ulogovaniKorisnik);
		 */
	}

}