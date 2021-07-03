package view.bibliotekar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.KorisniciController;
import model.korisnici.Korisnik;

public class PromenaLozinkeDialog extends JDialog {

	private static final long serialVersionUID = -3176827975030168590L;
	private JPasswordField pfNova;
	private JPasswordField pfNovaPotvrda;
	private JPasswordField pfStaraLozinka;

	public PromenaLozinkeDialog(Korisnik ulogovaniKorisnik) {
		setTitle("Promena lozinke");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][][][][]"));

		JLabel lblStara = new JLabel("Unesite trenutnu lozinku: ");
		lblStara.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblStara, "flowx,cell 1 1,alignx trailing");

		pfStaraLozinka = new JPasswordField();
		panel.add(pfStaraLozinka, "cell 2 1,growx");

		JLabel lblNewLabel = new JLabel("Unesite novu lozinku: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel, "cell 1 3,alignx trailing");

		pfNova = new JPasswordField();
		pfNova.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(pfNova, "cell 2 3,growx");

		JLabel lblNewLabel_1 = new JLabel("Potvrdite novu lozinku: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1, "cell 1 5,alignx trailing");

		pfNovaPotvrda = new JPasswordField();
		pfNovaPotvrda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(pfNovaPotvrda, "cell 2 5,growx");

		JButton btnPotvrda = new JButton("PROMENI");
		btnPotvrda.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String stara = new String(pfStaraLozinka.getPassword());
				String nova = new String(pfNova.getPassword());
				String novaPotvrda = new String(pfNovaPotvrda.getPassword());

				if (stara.length() == 0 || nova.length() == 0 || novaPotvrda.length() == 0) {
					JOptionPane.showMessageDialog(null, "Popunite sva polja!");
				} else if (KorisniciController.getInstance().promenaLozinke(ulogovaniKorisnik, stara, nova,
						novaPotvrda)) {
					JOptionPane.showMessageDialog(null, "Uspesno promenjena lozinka!");
					setVisible(false);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Greska pri unosu stare ili nove lozinke! Pokusajte ponovo!",
							"GRESKA", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPotvrda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(btnPotvrda, "cell 2 7,alignx right");
		pack();
	}

}
