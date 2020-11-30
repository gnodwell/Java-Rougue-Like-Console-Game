package rogue;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;


class InventoryRenderer extends DefaultListCellRenderer {

    /**
     * allows for an item to be read.
     * @param list (JList)
     * @param value (Object)
     * @param index (int)
     * @param isSelected (boolean)
     * @param cellHasFocus (boolean)
     * @return the items name
     */
    public Component getListCellRendererComponent(
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        Item item = (Item) value;
        setText(item.getName());

        return this;
    }


}



