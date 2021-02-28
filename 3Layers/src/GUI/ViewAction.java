package GUI;

import BUS.TableAction;
import DAO.DaoAction;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class ViewAction extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout gb = new GridBagLayout();
	private JTable jTable = null;
	private JScrollPane jScrollPane = null;
	private JButton jDelete = null;
	private JButton jInsert = null;
	private JButton jExit = null;
	private JLabel jId = null;
	private JTextField jtid = null;
	private JLabel jLfirstName = null;
	private JTextField jTfirstName = null;
	private JLabel jlLastName = null;
	private JTextField jLlastName = null;
	private JLabel jlClass = null;
	private JTextField jTClass = null;
	private JButton jExport = null;

	public ViewAction() {
		super();
		Initialize();
	}

	public void Initialize() {
		this.add(getViewTables());
		this.add(getJTextLables());
		this.add(getJButton());
		this.setLayout(new GridLayout(3, 0));
	}

	public JScrollPane getViewTables() {
		if (this.jScrollPane == null) {
			this.jScrollPane = new JScrollPane();
			this.jScrollPane.setViewportView(getJTable());
			this.jScrollPane.setBorder(new TitledBorder(new LineBorder(Color.GREEN), "Du Lieu Trong JTable"));
		}
		return jScrollPane;
	}

	public JPanel getJTextLables() {
		JPanel pt = new JPanel();
		pt.add(getjId());
		pt.add(getJtid());
		pt.add(getjLfirstName());
		pt.add(getjTfirstName());
		pt.add(getJlLastName());
		pt.add(getjLlastName());
		pt.add(getLabelClass());
		pt.add(getTextClass());
		pt.setBorder(new TitledBorder(new LineBorder(Color.green), "Nhap Thong Tin Them Vao Bang"));

		return pt;
	}

	public JPanel getJButton() {
		JPanel pb = new JPanel();
		pb.add(getjInsert());
		pb.add(getjDelete());
		pb.add(getExportData());
		pb.add(getjExit());
		pb.setLayout(new FlowLayout());
		pb.setBorder(new TitledBorder(new LineBorder(Color.green),
				"Chuc Nang: 1. Insert - Chen Du Lieu - 2. Delete - Xoa Du Lieu  - 3. Export - Export Data to CSDL - 4. Exit : Thoat"));
		return pb;
	}

	public JButton getjInsert() {
		if (this.jInsert == null) {
			this.jInsert = new JButton("Insert");

			this.jInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Vector<Object> row = new Vector<>();
					boolean flag = checkID();
					if (flag) {
						JOptionPane.showMessageDialog(getParent(), "ID đã tồn tại - Vui long kiểm tra lại !");
					} else {
						row.addElement(getJtid().getText().trim());
						row.addElement(getjTfirstName().getText().trim());
						row.addElement(getjLlastName().getText().trim());
						row.addElement(getTextClass().getText().trim());
						Vector<Object> titleTables = new TableAction().getHeder(jTable);
						Vector<Vector<Object>> dataTables = new TableAction().getData(jTable);
						dataTables.addElement(row);
						getJTable().setModel(new DefaultTableModel(dataTables, titleTables));
					}

				}
			});
		}
		return jInsert;
	}

	public boolean checkID() {
		boolean flag = false;
		String id = getJtid().getText().trim();
		Vector<Vector<Object>> datas = new TableAction().getData(jTable);
		for (Vector<Object> row : datas) {
			for (Object ob : row) {
				if (id.equalsIgnoreCase(ob.toString())) {
					flag = true;
					break;
				}
				if (flag)
					break;
			}
		}
		return flag;
	}

	public void setjInsert(JButton jInsert) {
		this.jInsert = jInsert;
	}

	public JButton getjDelete() {
		if (this.jDelete == null) {
			this.jDelete = new JButton("Delete");
			this.jDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int row = getJTable().getSelectedRow();
					Vector<Object> titleTables = new TableAction().getHeder(jTable);
					Vector<Vector<Object>> dataTables = new TableAction().getData(jTable);
					Vector<Object> removeRow = new TableAction().getRow(jTable, row);

					if (dataTables == null) {
						JOptionPane.showMessageDialog(getParent(), "Hien Tai Khong Co Du lieu Trong Bang");
					}
					if (row == -1) {
						JOptionPane.showMessageDialog(getParent(), "Vui Long Chon Hang Rui Nhan Delete");
					}
					dataTables.removeElement(removeRow);
					getJTable().setModel(new DefaultTableModel(dataTables, titleTables));

					if (jTable.getColumnCount() == 0) {
						getjDelete().setEnabled(false);
					}

				}
			});
		}
		return jDelete;
	}

	public void setjDelete(JButton jDelete) {
		this.jDelete = jDelete;
	}

	public JButton getExportData() {
		if (this.jExport == null) {
			this.jExport = new JButton("Export");
			this.jExport.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					Vector<Vector<Object>> dataTables = new ViewAction().getTablesData(getJTable(), null);
					if (dataTables != null)
						new DaoAction().export(dataTables);

				}
			});
		}
		return jExport;
	}

	public void setExportData(JButton jExport) {
		this.jExport = jExport;
	}

	public JButton getjExit() {
		if (this.jExit == null) {
			this.jExit = new JButton("Exit");
			this.jExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);

				}
			});
		}
		return jExit;
	}

	public void setjExit(JButton jExit) {
		this.jExit = jExit;
	}

	public JLabel getjId() {
		if (this.jId == null) {
			this.jId = new JLabel("ID");
			GridBagConstraints s = new GridBagConstraints();
			s.gridx = 1;
			s.gridy = 1;
			s.gridwidth = 1;
			s.gridheight = 1;
			s.ipadx = 0;
			s.ipady = 0;
			s.insets = new Insets(15, 15, 15, 15);
			gb.setConstraints(this.jId, s);
		}
		return jId;
	}

	public void setjId(JLabel jId) {
		this.jId = jId;
	}

	public JTextField getJtid() {
		if (this.jtid == null) {
			this.jtid = new JTextField(10);
			GridBagConstraints s = new GridBagConstraints();
			s.gridx = 2;
			s.gridy = 1;
			s.gridwidth = 1;
			s.gridheight = 1;
			s.ipadx = 0;
			s.ipady = 0;
			s.fill = GridBagConstraints.NONE;
			gb.setConstraints(this.jtid, s);

		}
		return jtid;
	}

	public void setJtid(JTextField jtid) {
		this.jtid = jtid;
	}

	public JLabel getjLfirstName() {
		if (this.jLfirstName == null) {
			this.jLfirstName = new JLabel("FirstName");
			GridBagConstraints s = new GridBagConstraints();
			s.gridx = 3;
			s.gridy = 1;
			s.gridwidth = 1;
			s.gridheight = 1;
			s.ipadx = 0;
			s.ipady = 0;
			s.insets = new Insets(15, 15, 15, 15);
			gb.setConstraints(this.jLfirstName, s);
		}
		return jLfirstName;
	}

	public void setjLfirstName(JLabel jLfirstName) {
		this.jLfirstName = jLfirstName;
	}

	public JTextField getjTfirstName() {
		if (this.jTfirstName == null) {
			this.jTfirstName = new JTextField(10);
			GridBagConstraints s = new GridBagConstraints();
			s.gridx = 4;
			s.gridy = 1;
			s.gridwidth = 1;
			s.gridheight = 1;
			s.ipadx = 0;
			s.ipady = 0;
			s.insets = new Insets(15, 15, 15, 15);
			s.fill = GridBagConstraints.NONE;
			gb.setConstraints(this.jTfirstName, s);

		}
		return jTfirstName;
	}

	public void setjTfirstName(JTextField jTfirstName) {
		this.jTfirstName = jTfirstName;
	}

	public JLabel getJlLastName() {
		if (this.jlLastName == null) {
			this.jlLastName = new JLabel("LastName");
			GridBagConstraints s = new GridBagConstraints();
			s.gridx = 1;
			s.gridy = 2;
			s.gridwidth = 1;
			s.gridheight = 1;
			s.ipadx = 0;
			s.ipady = 0;
			s.insets = new Insets(15, 15, 15, 15);
			gb.setConstraints(this.jlLastName, s);
		}
		return jlLastName;
	}

	public void setJlLastName(JLabel jlLastName) {
		this.jlLastName = jlLastName;
	}

	public JTextField getjLlastName() {
		if (this.jLlastName == null) {
			this.jLlastName = new JTextField(10);
			GridBagConstraints s = new GridBagConstraints();
			s.gridx = 2;
			s.gridy = 2;
			s.gridwidth = 1;
			s.gridheight = 1;
			s.ipadx = 0;
			s.ipady = 0;
			s.insets = new Insets(15, 15, 15, 15);
			s.fill = GridBagConstraints.NONE;
			gb.setConstraints(this.jLlastName, s);

		}
		return jLlastName;
	}

	public void setjLlastName(JTextField jLlastName) {
		this.jLlastName = jLlastName;
	}

	public JLabel getLabelClass() {
		if (this.jlClass == null) {
			this.jlClass = new JLabel("Class");
			GridBagConstraints s = new GridBagConstraints();
			s.gridx = 3;
			s.gridy = 2;
			s.gridwidth = 1;
			s.gridheight = 1;
			s.ipadx = 0;
			s.ipady = 0;
			s.insets = new Insets(15, 15, 15, 15);
			gb.setConstraints(this.jlClass, s);
		}
		return jlClass;
	}

	public void setJlClass(JLabel jlClass) {
		this.jlClass = jlClass;
	}

	public JTextField getTextClass() {
		if (this.jTClass == null) {
			this.jTClass = new JTextField(10);
			GridBagConstraints s = new GridBagConstraints();
			s.gridx = 4;
			s.gridy = 2;
			s.gridwidth = 1;
			s.gridheight = 1;
			s.ipadx = 0;
			s.ipady = 0;
			s.insets = new Insets(15, 15, 15, 15);
			s.fill = GridBagConstraints.NONE;
			gb.setConstraints(this.jTClass, s);

		}
		return jTClass;
	}

	public void setjLClass(JTextField jTClass) {
		this.jTClass = jTClass;
	}

	public JTable getJTable() {
		if (this.jTable == null) {
			Vector<Vector<Object>> dataTables = new DaoAction().getList("students");
			Vector<String> dataTitle = new DaoAction().getListNameField("students");
			this.jTable = new JTable(dataTables, dataTitle) {

				private static final long serialVersionUID = 1L;

				public String getToolTipText(MouseEvent e) {
					Point p = e.getPoint();
					int row = rowAtPoint(p);
					int column = columnAtPoint(p);
					Object valueAt = ((JTable) (e.getSource())).getModel().getValueAt(row, column);

					return "Value at Row " + row + "Column" + column + "is " + valueAt;
				}
			};
			this.jTable.setAutoCreateRowSorter(true);
			TableColumnModel model = this.jTable.getColumnModel();
			int columnCount = model.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				model.getColumn(i).setPreferredWidth(30 * (i + 1));
			}
			this.jTable.getModel().addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					int currentRow = e.getFirstRow();
					int currentColumn = e.getColumn();
					TableModel data = getJTable().getModel();
					String valueAt = data.getValueAt(currentRow, currentColumn).toString();
					System.out.println("Ban dang tac dong tren Row " + currentRow + "Column " + currentColumn
							+ "Value At" + valueAt);

				}
			});
		}
		this.jTable.setFillsViewportHeight(true);
		return jTable;
	}

	public Vector<Vector<Object>> getTablesData(JTable jtable, Vector<Object> newrow) {
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
		if (newrow != null)
			data.addElement(newrow);
		return data;
	}

	public Vector<Object> getTablesTitle(JTable jtable) {
		Vector<Object> listTitle = new Vector<>();
		TableModel mode = jtable.getModel();
		int column = mode.getColumnCount();
		for (int i = 0; i < column; i++) {
			listTitle.addElement(mode.getColumnName(i));
		}
		return listTitle;
	}

	public void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame fr = new JFrame("Working with JTable in Java");
		fr.getContentPane().add(new ViewAction());
		fr.setVisible(true);
		fr.setSize(800, 500);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ViewAction().createAndShowGUI();
			}
		});

	}

}
