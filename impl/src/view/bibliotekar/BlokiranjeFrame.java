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

public class BlokiranjeFrame extends JFrame {
	
	private static final long serialVersionUID = -1963829151345705584L;

	private static BlokiranjeFrame instance = null;

	private JTable tabelaClanova;
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	protected JTextField tfSearch = new JTextField(20);
	protected JComboBox comboBox;

	public static BlokiranjeFrame getInstance() { 
		if (instance == null) {
			instance = new BlokiranjeFrame();
		}
		return instance;
	}

	public BlokiranjeFrame() {

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenDimension.width / 2, screenDimension.height / 2);
		setTitle("BIBLIOTEKA - Blokiranje clanova");
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
		postaviTabeluClanova();
		postaviPretragu();
	}


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

		JButton btnBlokiraj = new JButton("Blokiraj");

		btnBlokiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabelaClanova.getSelectedColumnCount() == 0) {
					JOptionPane.showMessageDialog(tabelaClanova, "Morate selektovati clana kojeg hocete da blokirate!",
							"GRESKA", JOptionPane.ERROR_MESSAGE);
				} else {
					Clan c = ClanoviController.getInstance().getClanovi().get(tabelaClanova.getSelectedRow());
					if (c.isObrisan() == true) {
						JOptionPane.showMessageDialog(tabelaClanova, "Korisnik je blokiran!",
							"GRESKA", JOptionPane.ERROR_MESSAGE);}
					c.setObrisan(true);
					if (c.isObrisan() == true) {
						JOptionPane.showMessageDialog(tabelaClanova, "Blokiranje korisnika je uspesno!");
						dispose();
						instance = null;
						} 
					}
			}
			
		});

		pSearch.add(btnBlokiraj, "cell 22 0,alignx left,aligny center");
		
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
