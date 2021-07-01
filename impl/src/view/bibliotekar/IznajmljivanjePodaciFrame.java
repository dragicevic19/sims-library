package view.bibliotekar;

import model.knjiga.Knjiga;
import model.korisnici.Clan;
import model.primerak.Primerak;
import controller.BibliotekariController;
import controller.ClanoviController;
import controller.PrimerciController;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IznajmljivanjePodaciFrame extends JFrame {

	private static final long serialVersionUID = -1963829151345705584L;

	private static IznajmljivanjePodaciFrame instance = null;
	private static Knjiga izabranaKnjiga = null;

	private JTable tabelaClanova;
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	protected JTextField tfSearch = new JTextField(20);
	protected JComboBox comboBox;

	public static IznajmljivanjePodaciFrame getInstance(Knjiga knjiga) { // nema potrebe da ove klase budu Singleton?
		if (instance == null) {
			izabranaKnjiga = knjiga;
			instance = new IznajmljivanjePodaciFrame();
		}
		return instance;
	}

	public IznajmljivanjePodaciFrame() {

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenDimension.width / 2, screenDimension.height / 2);
		setTitle("BIBLIOTEKA - Iznajmljivanje knjige");
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

		postaviCombo();
		postaviTabeluClanova();
		postaviPretragu();
	}

	private void postaviCombo() {

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 194, 46, 0 };
		gbl_panel.rowHeights = new int[] { 14, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblPrimerak = new JLabel("Izabrite primerak:");
		lblPrimerak.setFont(new Font("Calibri", Font.PLAIN, 17));
		GridBagConstraints gbc_lblPrimerak = new GridBagConstraints();
		gbc_lblPrimerak.insets = new Insets(0, 0, 0, 5);
		gbc_lblPrimerak.gridx = 0;
		gbc_lblPrimerak.gridy = 0;
		panel.add(lblPrimerak, gbc_lblPrimerak);

		DefaultComboBoxModel cbModel = new DefaultComboBoxModel(
				PrimerciController.getInstance().getPrimerciZaIznajmljivanje(izabranaKnjiga));
		comboBox = new JComboBox(cbModel);
		comboBox.setMaximumRowCount(100);
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 17));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);

	};

	private void postaviTabeluClanova() {
		tabelaClanova = new JTable(new AbstractTableModelClanovi());

		tabelaClanova.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaClanova.getTableHeader().setReorderingAllowed(false);
		tabelaClanova.setAutoCreateRowSorter(true);
		tableSorter.setModel((AbstractTableModel) tabelaClanova.getModel());
		tabelaClanova.setRowSorter(tableSorter);
		JScrollPane sc = new JScrollPane(tabelaClanova);
		getContentPane().add(sc, BorderLayout.CENTER);
	}

	private void postaviPretragu() {
		JPanel pSearch = new JPanel();
		pSearch.setBackground(Color.cyan);
		pSearch.setLayout(new MigLayout("", "[46px][166px][73px][][][][][][][][][][][][][][][][][][][][]", "[23px]"));
		pSearch.add(new JLabel("Pretraga:"), "cell 0 0,alignx left,aligny center");
		pSearch.add(tfSearch, "cell 1 0,alignx left,aligny center");

		getContentPane().add(pSearch, BorderLayout.SOUTH);

		JButton btnPotvrda = new JButton("IZNAJMI");

		btnPotvrda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabelaClanova.getSelectedColumnCount() == 0) {
					JOptionPane.showMessageDialog(tabelaClanova, "Morate selektovati clana koji iznajmljuje knjigu!",
							"GRESKA", JOptionPane.ERROR_MESSAGE);
				} else {
					Clan c = ClanoviController.getInstance().getClanovi().get(tabelaClanova.getSelectedRow());
					Primerak p = (Primerak) comboBox.getSelectedItem();
					if (BibliotekariController.getInstance().iznajmiKnjiguClanu(c, p)) {
						JOptionPane.showMessageDialog(tabelaClanova, "Iznajmljivanje knjige je uspesno!");
						dispose();
						instance = null;
					} else {
						JOptionPane.showMessageDialog(tabelaClanova, "Clan ima maksimalno iznajmljenih knjiga!",
								"GRESKA", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		pSearch.add(btnPotvrda, "cell 22 0,alignx left,aligny center");

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (tfSearch.getText().trim().length() == 0) {
					tableSorter.setRowFilter(null);
				} else {
					tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfSearch.getText().trim()));
				}
			}
		});

	}

}
