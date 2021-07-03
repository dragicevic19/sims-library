package view.clan;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;

import controller.PrimerciController;
import model.korisnici.Clan;
import model.primerak.ZauzetPrimerak;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KomentariIOceneDialog extends JDialog {
	private static final long serialVersionUID = 5080247977965553887L;

	private ZauzetPrimerak primerak;
	private Clan clan;

	public KomentariIOceneDialog(ZauzetPrimerak primerak, Clan clan) {
		this.primerak = primerak;
		this.clan = clan;

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int dialogWidth = screenDimension.width / 4;
		int dialogHeight = screenDimension.height / 4;
		int dialogX = screenDimension.width / 2 - dialogWidth / 2;
		int dialogY = screenDimension.height / 2 - dialogHeight / 2;

		setBounds(dialogX, dialogY, dialogWidth, dialogHeight);
		setResizable(false);
		setVisible(true);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da izadjete?", "Izadji?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		setTitle("Revizija - " + primerak.getPrimerak().getKnjiga().getNaslov());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][][grow]", "[grow]"));

		JComboBox cbOcena = new JComboBox();

		JLabel label = new JLabel("Komentar: ");
		panel.add(label, "cell 1 0,aligny top");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JEditorPane editorPane = new JEditorPane();
		panel.add(editorPane, "cell 2 0,grow");

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		JButton btnPotvrda = new JButton("POSTAVI");
		btnPotvrda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer ocena = Integer.parseInt((String) cbOcena.getSelectedItem());
				String komentar = editorPane.getText().trim();
				PrimerciController.getInstance().napisanaRevizija(ocena, komentar, primerak);
				JOptionPane.showMessageDialog(null, "Uspesno ste poslali komentar!");
				setVisible(false);
				dispose();
			}
		});
		btnPotvrda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_2.add(btnPotvrda);

		JButton btnOtkazi = new JButton("OTKAZI");
		btnOtkazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da izadjete?", "Izadji?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					setVisible(false);
					dispose();
				}
			}
		});
		btnOtkazi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_2.add(btnOtkazi);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JLabel lblOcena = new JLabel("Ocena:");
		lblOcena.setHorizontalAlignment(SwingConstants.LEFT);
		lblOcena.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(lblOcena);

		cbOcena.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbOcena.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		panel_1.add(cbOcena);

		setLocationRelativeTo(null);

	}

}
