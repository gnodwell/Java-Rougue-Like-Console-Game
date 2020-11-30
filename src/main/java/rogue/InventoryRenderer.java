package rogue;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;


class InventoryRenderer extends DefaultListCellRenderer {
    
    public Component getListCellRendererComponent(
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        Item item = (Item)value;
        setText( item.getName() );

        return this;
    }


}



