package view.bibliotekar;

import javax.swing.table.AbstractTableModel;

import model.knjiga.BazaKnjiga;

public class AbstractTableModelKnjige extends AbstractTableModel {

	private static final long serialVersionUID = -8585929473672516300L;

	@Override
	public int getRowCount() {
		return BazaKnjiga.getInstance().getKnjige().size();
	}

	@Override
	public int getColumnCount() {
		return BazaKnjiga.getInstance().getColumnCount();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return BazaKnjiga.getInstance().getColumnName(columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.getValueAt(0, columnIndex).getClass();
	}

	/*
	 * @Override public Class<?> getColumnClass(int columnIndex) { switch
	 * (columnIndex) { case 0: case 1: case 2: case 3: case 4: case 5: case 6:
	 * return String.class; case 7: return JButton.class; default: return null; } }
	 */

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaKnjiga.getInstance().getValueAt(rowIndex, columnIndex);
	}
}
