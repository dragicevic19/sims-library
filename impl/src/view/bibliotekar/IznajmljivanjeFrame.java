package view.bibliotekar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import controller.KnjigeController;
import controller.PrimerciController;
import jdk.nashorn.internal.scripts.JO;
import model.knjiga.Knjiga;

public class IznajmljivanjeFrame extends JFrame {

	private static final long serialVersionUID = 3181796146312397413L;
	private static IznajmljivanjeFrame instance = null;
	protected JTextField tfSearch = new JTextField(20);
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();

	public static IznajmljivanjeFrame getInstance() {
		if (instance == null) {
			instance = new IznajmljivanjeFrame();
		}
		return instance;
	}

	private JTable tabelaKnjiga;

	public IznajmljivanjeFrame() {
		setTitle("BIBLIOTEKA - Iznajmljivanje knjige");

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1213, 593);
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

		prikaziTabeluKnjiga();
		podesavanjeAkcija();
	}

	private void prikaziTabeluKnjiga() {
		tabelaKnjiga = new JTable(new AbstractTableModelKnjige());

		tabelaKnjiga.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaKnjiga.getTableHeader().setReorderingAllowed(false);
		tabelaKnjiga.setAutoCreateRowSorter(true);
		tableSorter.setModel((AbstractTableModel) tabelaKnjiga.getModel());
		tabelaKnjiga.setRowSorter(tableSorter);
		JScrollPane sc = new JScrollPane(tabelaKnjiga);
		getContentPane().add(sc, BorderLayout.CENTER);
		tabelaKnjiga.addMouseListener(new MouseAdapter() {

			@Override // da se procita sve iz celije tabele
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					JTable target = (JTable) me.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					JOptionPane.showMessageDialog(null, tabelaKnjiga.getValueAt(row, column));
				}
			}
		});
	}

	private void podesavanjeAkcija() {

		JPanel pSearch = new JPanel();
		pSearch.setBackground(Color.cyan);
		pSearch.setLayout(new MigLayout("",
				"[46px][166px][67px][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[23px]"));
		pSearch.add(new JLabel("Pretraga:"), "cell 0 0,alignx left,aligny center");
		pSearch.add(tfSearch, "cell 1 0,alignx left,aligny center");

		getContentPane().add(pSearch, BorderLayout.SOUTH);
		JButton btnIznajmi = new JButton("Iznajmi");
		btnIznajmi.setFont(new Font("Calibri", Font.BOLD, 15));
		pSearch.add(btnIznajmi, "cell 31 0,alignx right,aligny center");

		btnIznajmi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabelaKnjiga.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Morate selektovati koju knjigu zelite da iznajmite!", "GRESKA",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int row = tabelaKnjiga.getSelectedRow();
					Knjiga k = KnjigeController.getInstance().getKnjige().get(row);
					if (PrimerciController.getInstance().getPrimerciZaIznajmljivanje(k).size() != 0) {
						IznajmljivanjePodaciFrame.getInstance(KnjigeController.getInstance().getKnjige().get(row));
						instance = null;
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Trenutno nema slobodnih primeraka izabrane knjige",
								"GRESKA", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

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
