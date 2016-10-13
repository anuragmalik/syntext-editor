package textClasses;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;


public class CustomListRenderer implements ListCellRenderer {

	RSyntaxTextArea renderer = new RSyntaxTextArea();
   public Component getListCellRendererComponent(JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus) {

        
        renderer.setText(value.toString());
        renderer.setLineWrap(true);
        
		
		 if (isSelected) {
			 renderer.setBorder(BorderFactory.createLineBorder(Color.RED));
			
			 renderer.setBackground(new Color(	255 ,	228 ,	225));
			 
			 
	        } else {
	        	renderer.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
	        	renderer.setBackground(new Color(	234 ,	234, 	234));
	        }
		
        return renderer;
   }
   
   void setPerspectiveinpanel(String syntaxStyle) 
   {
	 
		   renderer.setSyntaxEditingStyle(syntaxStyle);   
   };
}