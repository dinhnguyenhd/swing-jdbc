package BUS;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableAction {

	public TableAction() {
		;
	}

	public Vector<Vector<Object>> getData(JTable jtable) {
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		TableModel tablemodel = jtable.getModel();
		int column = jtable.getColumnCount();
		int row = jtable.getRowCount();
		for (int i = 0; i < row; i++) {
			Vector<Object> datarow = new Vector<>();
			for (int j = 0; j < column; j++) {
				datarow.addElement(tablemodel.getValueAt(i, j));
			}

			data.addElement(datarow);

		}

		return data;
	}

	public Vector<Object> getHeder(JTable jtable) {
		Vector<Object> listTitle = new Vector<>();
		TableModel mode = jtable.getModel();
		int column = mode.getColumnCount();
		for (int i = 0; i < column; i++) {
			listTitle.addElement(mode.getColumnName(i));
		}
		return listTitle;
	}

	public Vector<Object> getRow(JTable jtable, int row) {
		TableModel tablemodel = jtable.getModel();
		Vector<Object> datarow = new Vector<>();
		for (int j = 0; j < jtable.getColumnCount(); j++) {
			datarow.addElement(tablemodel.getValueAt(row, j));
		}

		return datarow;
	}

	public Vector<Vector<Object>> addRow(JTable jtable, Vector<Object> newrow) {

		Vector<Vector<Object>> data = new TableAction().getData(jtable);
		Vector<Object> row = new TableAction().getRow(jtable, jtable.getSelectedRow());
		data.addElement(row);

		return data;
	}

	public Vector<Vector<Object>> removeRow(JTable jtable, Vector<Object> newrow) {

		Vector<Vector<Object>> data = new TableAction().getData(jtable);
		Vector<Object> row = new TableAction().getRow(jtable, jtable.getSelectedRow());
		data.removeElement(row);

		return data;
	}

}
