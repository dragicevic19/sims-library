package view.bibliotekar;

import javax.swing.table.AbstractTableModel;

import controller.PrimerciController;
import model.korisnici.Clan;
import model.primerak.BazaPrimerak;
import model.primerak.ZauzetPrimerak;

public class AbstractTableModelZaduzenja extends AbstractTableModel {

	private static final long serialVersionUID = -2744161400885779492L;

	private String[] columnNames = { "ID clana", "Ime", "Prezime", "Korisnicko ime", "ID primerka", "Naslov",
			"Rok za vracanje" };

	private String mod;
	private Clan ulogovaniClan;

	public AbstractTableModelZaduzenja(String mod, Clan ulogovani) {
		this.mod = mod;
		this.ulogovaniClan = ulogovani;
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
			return (ulogovaniClan == null) ? PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerci().size()
					: PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerciZaClana(ulogovaniClan).size();

		else if (mod.equals("istorija"))
			return (ulogovaniClan == null) ? PrimerciController.getInstance().getSviIznajmljeniPrimerci().size()
					: PrimerciController.getInstance().getSviIznajmljeniPrimerciZaClana(ulogovaniClan).size();

		else
			return -1;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.getValueAt(0, columnIndex).getClass();
	}

	@Override
	public Object getValueAt(int row, int col) {
		ZauzetPrimerak z;
		if (mod.equals("trenutna"))

			z = (ulogovaniClan == null) ? PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerci().get(row)
					: PrimerciController.getInstance().getTrenutnoIznajmljeniPrimerciZaClana(ulogovaniClan).get(row);
		else
			z = (ulogovaniClan == null) ? PrimerciController.getInstance().getSviIznajmljeniPrimerci().get(row)
					: PrimerciController.getInstance().getSviIznajmljeniPrimerciZaClana(ulogovaniClan).get(row);

		return PrimerciController.getInstance().toCell(z, col);
	}

}
