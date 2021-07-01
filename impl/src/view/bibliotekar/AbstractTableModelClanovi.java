package view.bibliotekar;

import javax.swing.table.AbstractTableModel;

import model.korisnici.BazaClanova;

public class AbstractTableModelClanovi extends AbstractTableModel {

	private static final long serialVersionUID = 6495296322575364784L;

	@Override
	public int getColumnCount() {
		return BazaClanova.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		return BazaClanova.getInstance().getClanovi().size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.getValueAt(0, columnIndex).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaClanova.getInstance().getValueAt(rowIndex, columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		return BazaClanova.getInstance().getColumnName(column);
	}

}
