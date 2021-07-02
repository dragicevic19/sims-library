package view.bibliotekar;

import javax.swing.table.AbstractTableModel;

import model.primerak.BazaPrimerak;

public class AbstractTableModelZaduzenja extends AbstractTableModel {

	private static final long serialVersionUID = -2744161400885779492L;

	private String[] columnNames = { "ID clana", "Ime", "Prezime", "Korisnicko ime", "ID primerka", "Naslov" };

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return BazaPrimerak.getInstance().getTrenutnoIznajmljeniPrimerci().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
