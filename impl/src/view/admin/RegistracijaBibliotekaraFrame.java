package view.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import view.bibliotekar.RegistracijaClanaFrame;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import controller.BibliotekariController;
import controller.ClanoviController;
import controller.KorisniciController;
import controller.MestaController;
import dto.BibliotekarDTO;
import model.korisnici.Bibliotekar;
import model.enums.VrstaBibliotekara;
import model.korisnici.VrstaClana;
import model.mesto.Mesto;

public class RegistracijaBibliotekaraFrame extends JFrame {
	
	private static final long serialVersionUID = 288759158008582343L;
	private static final int PASSWORD_LEN = 10;
	private static RegistracijaBibliotekaraFrame instance = null;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfJMBG;
	private JTextField tfAdresa;
	private JTextField tfKoriscnickoIme;
	private JComboBox cbMesto;
	private JTextField tfEmail;
	private JRadioButton rbAdmin;

	public static RegistracijaBibliotekaraFrame getInstance() {
		if (instance == null) {
			instance = new RegistracijaBibliotekaraFrame();
		}
		return instance;
	}

	public RegistracijaBibliotekaraFrame() {
		setFont(new Font("Calibri", Font.BOLD, 20));

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(864, 435);
		setTitle("BIBLIOTEKA - Registracija bibliotekara");

		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				dispose();
				instance = null;
			}
		});

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		JLabel lblReg = new JLabel("REGISTRACIJA Bibliotekara");
		lblReg.setFont(new Font("Calibri", Font.BOLD, 30));
		panel.add(lblReg);
		initFormu();
	}

	private void initFormu() {

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(
				new MigLayout("", "[][][][grow][grow][][][][][][][grow][][][][][][grow][][]", "[][][][][][][][][][]"));

		JLabel lblIme = new JLabel("Ime: ");
		panel_1.add(lblIme, "cell 2 1,alignx trailing");

		tfIme = new JTextField();
		panel_1.add(tfIme, "cell 3 1,growx");
		tfIme.setColumns(10);

		JLabel lblKIme = new JLabel("Korisnicko ime: ");
		panel_1.add(lblKIme, "cell 16 1,alignx trailing");

		tfKoriscnickoIme = new JTextField();
		panel_1.add(tfKoriscnickoIme, "cell 17 1,growx");
		tfKoriscnickoIme.setColumns(10);

		JLabel lblPrezime = new JLabel("Prezime: ");
		panel_1.add(lblPrezime, "cell 2 2,alignx trailing");

		tfPrezime = new JTextField();
		panel_1.add(tfPrezime, "cell 3 2,growx");
		tfPrezime.setColumns(10);

		JLabel lblJMBG = new JLabel("JMBG: ");
		panel_1.add(lblJMBG, "cell 2 3,alignx trailing");

		tfJMBG = new JTextField();
		panel_1.add(tfJMBG, "cell 3 3,growx");
		tfJMBG.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail: ");
		panel_1.add(lblEmail, "cell 16 3,alignx trailing");

		tfEmail = new JTextField();
		panel_1.add(tfEmail, "cell 17 3,growx");
		tfEmail.setColumns(10);

		JLabel lblMesto = new JLabel("Mesto stanovanja: ");
		panel_1.add(lblMesto, "cell 2 4,alignx trailing");

		DefaultComboBoxModel cbModel = new DefaultComboBoxModel(MestaController.getInstance().getMesta());

		cbMesto = new JComboBox(cbModel);
		cbMesto.setMaximumRowCount(1000);
		panel_1.add(cbMesto, "cell 3 4,growx");

		JLabel lblAdresa = new JLabel("Adresa stanovanja: ");
		panel_1.add(lblAdresa, "cell 2 5,alignx trailing");

		tfAdresa = new JTextField();
		panel_1.add(tfAdresa, "cell 3 5,growx");
		tfAdresa.setColumns(10);
		
		JLabel lblAdmin = new JLabel("Admin: ");
		panel_1.add(lblAdmin, "cell 2 6,alignx trailing");
		rbAdmin = new JRadioButton();
		panel_1.add(rbAdmin, "cell 3 6,growx");

		JButton btnPotvrdi = new JButton("REGISTRUJ");
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				BibliotekarDTO B = proveriUnetePodatke();
				if (B != null) {
					BibliotekariController.getInstance().dodajBibliotekara(B);
					JOptionPane.showMessageDialog(null, "Uspesno ste registrovali bibliotekara!", "USPESNO",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
					instance = null;
				}
			}
		});
		panel_1.add(btnPotvrdi, "cell 17 9,growx,aligny bottom");
		
		
	}

	private BibliotekarDTO proveriUnetePodatke() {
		BibliotekarDTO regBib = null;

		String ime = tfIme.getText().trim();
		String prezime = tfPrezime.getText().trim();
		String kIme = tfKoriscnickoIme.getText().trim();
		String lozinka = ClanoviController.getInstance().generateRandomPassword(PASSWORD_LEN);
		String jmbg = tfJMBG.getText().trim();
		Mesto mesto = (Mesto) cbMesto.getSelectedItem();
		String adresa = tfAdresa.getText().trim();
		String eMail = tfEmail.getText().trim();
		Boolean admin = rbAdmin.isSelected();

		if (ime.length() == 0 || prezime.length() == 0 || kIme.length() == 0 || adresa.length() == 0
				|| jmbg.length() != 13) {

				JOptionPane.showMessageDialog(null, "Greska pri unosu podataka!", "GRESKA", JOptionPane.ERROR_MESSAGE);

		} else if (KorisniciController.getInstance().getKorisnikZaKorisnickoIme(kIme) != null) { // postoji bibliotekar
			JOptionPane.showMessageDialog(null, "Uneto korisnicko ime vec postoji", "GRESKA",
					JOptionPane.ERROR_MESSAGE);
		} else {

			regBib = new BibliotekarDTO(kIme, ime, prezime, lozinka, jmbg, mesto, adresa, eMail, admin);
		}

		return regBib;
	}

}
