package view.bibliotekar;

import javax.swing.table.AbstractTableModel;

import controller.PrimerciController;
import model.primerak.BazaPrimerak;
import model.primerak.ZauzetPrimerak;

public class AbstractTableModelZaduzenja extends AbstractTableModel {

	private static final long serialVersionUID = -2744161400885779492L;

	private String[] columnNames = { "ID clana", "Ime", "Prezime", "Korisnicko ime", "ID primerka", "Naslov" };
	private String mod;

	public AbstractTableModelZaduzenja(String mod) {
		this.mod = mod;
	}

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
		if (mod.equals("trenutna"))
			return PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerci().size();
		else
			return PrimerciController.getInstance().getSviIznajmljeniPrimerci().size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.getValueAt(0, columnIndex).getClass();
	}

	@Override
	public Object getValueAt(int row, int col) {
		ZauzetPrimerak z;
		if (mod.equals("trenutna"))
			z = PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerci().get(row);
		else
			z = PrimerciController.getInstance().getSviIznajmljeniPrimerci().get(row);

		return PrimerciController.getInstance().toCell(z, col);
	}

}
