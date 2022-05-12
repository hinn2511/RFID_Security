package View;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 1L;
	private final Color whiteColor = Color.WHITE;
	private final Color greenColor = Color.GREEN;
	private final Color redColor = Color.RED;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component cr = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		cr.setBackground(whiteColor);
		if (table.getValueAt(row, column).equals("Purchased")) {
			cr.setBackground(greenColor);
		} 
		if (table.getValueAt(row, column).equals("Not purchased")) {
			cr.setBackground(redColor);
		}
		return cr;
	}
	

}
