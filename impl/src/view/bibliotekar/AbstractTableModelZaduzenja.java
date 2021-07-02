package view.bibliotekar;

import javax.swing.table.AbstractTableModel;

import controller.PrimerciController;
import model.primerak.BazaPrimerak;
import model.primerak.ZauzetPrimerak;

public class AbstractTableModelZaduzenja extends AbstractTableModel {

	private static final long serialVersionUID = -2744161400885779492L;

	private String[] columnNames = { "ID clana", "Ime", "Prezime", "Korisnicko ime", "ID primerka", "Naslov" };

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public int getRowCount() {
		// ovde neki uslov za istoriju zaduzenja?
		return PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerci().size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.getValueAt(0, columnIndex).getClass();
	}

	@Override
	public Object getValueAt(int row, int col) {
		ZauzetPrimerak z = PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerci().get(row);
		return PrimerciController.getInstance().toCell(z, col);
	}

}
