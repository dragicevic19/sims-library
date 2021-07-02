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

import controller.PrimerciController;
import model.primerak.ZauzetPrimerak;

public class ZaduzenjaFrame extends JFrame {

	private static final long serialVersionUID = -4119756951694214294L;
	private static ZaduzenjaFrame instance = null;
	private static String mod = null;

	protected JTextField tfSearch = new JTextField(20);
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();

	public static ZaduzenjaFrame getInstance(String m) {
		if (instance == null) {
			mod = m;
			instance = new ZaduzenjaFrame();
		}
		return instance;
	}

	private JTable tabelaZaduzenja;

	public ZaduzenjaFrame() {
		if (mod.equals("istorija"))
			setTitle("BIBLIOTEKA - Istorija zaduzenja");
		else
			setTitle("BIBLIOTEKA - Trenutna zaduzenja");

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenDimension.width / 2, screenDimension.height / 2);
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

		prikaziTabelu();
		podesavanjeAkcija();
	}

	private void prikaziTabelu() {
		tabelaZaduzenja = new JTable(new AbstractTableModelZaduzenja(mod));

		tabelaZaduzenja.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaZaduzenja.getTableHeader().setReorderingAllowed(false);
		tabelaZaduzenja.setAutoCreateRowSorter(true);
		tableSorter.setModel((AbstractTableModel) tabelaZaduzenja.getModel());
		tabelaZaduzenja.setRowSorter(tableSorter);
		JScrollPane sc = new JScrollPane(tabelaZaduzenja);
		getContentPane().add(sc, BorderLayout.CENTER);
		tabelaZaduzenja.addMouseListener(new MouseAdapter() {

			@Override // da se procita sve iz celije tabele
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					JTable target = (JTable) me.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					JOptionPane.showMessageDialog(null, tabelaZaduzenja.getValueAt(row, column));
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
		JButton btnVrati = new JButton("VRACENA KNJIGA");
		btnVrati.setFont(new Font("Calibri", Font.BOLD, 15));

		if (mod.equals("trenutna"))
			pSearch.add(btnVrati, "cell 31 0,alignx right,aligny center");

		btnVrati.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabelaZaduzenja.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Morate selektovati koju knjigu je clan vratio!", "GRESKA",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int row = tabelaZaduzenja.getSelectedRow();
					ZauzetPrimerak z = PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerci().get(row);
					PrimerciController.getInstance().vracanjePrimerka(z);
					JOptionPane.showMessageDialog(null, "Uspesno vracanje knjige!");
					instance = null;
					dispose();
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
