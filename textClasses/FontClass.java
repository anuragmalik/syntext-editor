package textClasses;

/* Imports javax.swing package classes. */
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.BorderFactory;
/* Imports java.awt package classes. */
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Font;
/* Imports javax.swing.event package classes. */
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
/*
class FontClass - This class creates a Font dialog box that enables the 
end user to change the font, size, and type of the text.
Fields: 
   fontLabel - Contains the content of Font label.
   sizeLabel - Contains the content of Size label.
   typeLabel - Contains the content of Type label.
   previewLabel - Contains the content of preview.
   label - Contains the preview contents.
   fontText - Contains the selected font name.
   typeText - Contains the selected font type name.
   sizeText - Contains the selected font size name.
   fontScroll - Contains the Font list.
   typeScroll - Contains the Type list.
   sizeScroll - Contains the Size list.
   fontList - Contains all the available font.
   typeList - Contains all the available types of font style.
   sizeList - Contains all the available font size.
   ok - Creates an OK button.
   cancel - Creates a cancel button.
Methods:
   getOK() - This method returns the OK button object
   getCancel() - This method returns the Cancel button object
   valueChanged() - This method is invoked when an end user select the item from the List box.
   font() - This method returns the font
*/
public class FontClass extends JDialog implements ListSelectionListener
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/* Declares the objects of the JPanel class. */
   JPanel pan1;
   JPanel pan2;
   JPanel pan3;
   /* Declares the objects of the JLabel class. */
   JLabel fontLabel;
   JLabel sizeLabel;
   JLabel typeLabel;
   JLabel previewLabel;
   /* Declares the objects of the JTextField class. */
   JTextField label;
   JTextField fontText;
   JTextField sizeText;
   JTextField typeText;
   /* Declares the objects of the JScrollPane class. */
   JScrollPane fontScroll;
   JScrollPane typeScroll;
   JScrollPane sizeScroll;
   /* Declares the objects of the JList class. */
   JList fontList;
   JList sizeList;
   JList typeList;
   /* Declares the objects of the JButton class. */
   JButton ok;
   JButton cancel;
   GridBagLayout gbl;
   GridBagConstraints gbc;
   /* Defines the default constructor. */
   public FontClass()
   {
      /* Sets the title of the Font dialog. */
      setTitle("Font Dialog");
      /* Sets the size of Font dialog. */
      setSize(300, 400);
      /* Sets resizable button to false. */
      setResizable(false);
      /* Initializes the object of the GridBagLayout class. */
      gbl = new GridBagLayout();
      /* Sets the Layout. */
      getContentPane().setLayout(gbl);
      /* Creates an object of GridBagConstraints class. */
      gbc = new GridBagConstraints();
      /* 
      Initializes the Font label object and add it to the 1, 1, 1, 1 positions with WEST alignment. 
      */
      gbc.gridx = 1; 
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      fontLabel = new JLabel("Fonts: ");
      getContentPane().add(fontLabel, gbc);
      /* 
      Initializes the Size label object and adds it to the 2, 1, 1, 1 positions with WEST alignment. 
      */ 
      gbc.gridx = 2; 
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      sizeLabel = new JLabel("Sizes: ");
      getContentPane().add(sizeLabel, gbc);
      /* 
      Initializes the Types label object and adds it to the 3, 1, 1, 1 positions with WEST alignment. 
      */
      gbc.gridx = 3; 
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      typeLabel = new JLabel("Types: ");
      getContentPane().add(typeLabel, gbc);
      /* 
      Initializes the Font text field object and adds it to the 1, 2, 1, 1 positions with WEST alignment. 
      */
      gbc.gridx = 1; 
      gbc.gridy = 2;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      fontText = new JTextField("Arial", 12);
      getContentPane().add(fontText, gbc);
      /* 
      Initializes the Size text field object and adds it to the 2, 2, 1, 1 positions with WEST alignment. 
      */
      gbc.gridx = 2; 
      gbc.gridy = 2;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      sizeText = new JTextField("8", 4);
      getContentPane().add(sizeText, gbc);
      /* 
      Initializes the Types text field object and adds it to the 3, 2, 1, 1 positions with WEST alignment. 
      */
      gbc.gridx = 3; 
      gbc.gridy = 2;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      typeText = new JTextField("Regular", 6);
      getContentPane().add(typeText, gbc);
      /* 
      Initializes the Font list object and add it to the Font scroll pane object. 
      Adds this scroll pane object to 1, 3, 1, 1 positions with WEST alignment 
      */
      gbc.gridx = 1; 
      gbc.gridy = 3;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
      fontList = new JList(fonts);
      fontList.setFixedCellWidth(110); 
      fontList.addListSelectionListener(this);
      fontList.setSelectedIndex(0);
      fontScroll = new JScrollPane(fontList);
      getContentPane().add(fontScroll, gbc);
      /* 
      Initializes the Size list object and add it to the Size scroll pane object. 
      Adds this scroll pane object to 2, 3, 1, 1 positions with WEST alignment 
      */
      gbc.gridx = 2; 
      gbc.gridy = 3;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      String[] sizes = {"8","10","12","14","16","18","20","24","28","32","48","72"};
      sizeList = new JList(sizes);
      sizeList.setSelectedIndex(0);
      sizeList.setFixedCellWidth(20); 
      sizeList.addListSelectionListener(this);
      sizeScroll = new JScrollPane(sizeList); 
      getContentPane().add(sizeScroll, gbc);
      /* 
      Initializes the Types list object and adds it to the Types scroll pane object. 
      Next, adds this scroll pane object to 3, 3, 1, 1 positions with WEST alignment 
      */
      gbc.gridx = 3; 
      gbc.gridy = 3;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      String[] types = {"Regular", "Bold", "Italic", "Bold Italic"};
      typeList = new JList(types);
      typeList.setFixedCellWidth(60); 
      typeList.addListSelectionListener(this);
      typeList.setSelectedIndex(0);
      typeScroll = new JScrollPane(typeList); 
      getContentPane().add(typeScroll, gbc);
      /* 
      Initializes the preview label and adds it to 1,4,3,1 positions with CENTER alignment. 
      */
      gbc.gridx = 1; 
      gbc.gridy = 4;
      gbc.gridwidth = 3;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.CENTER;
      pan1 = new JPanel();
      pan1.setLayout(new FlowLayout());
      previewLabel = new JLabel("Preview:");
      pan1.add(previewLabel);
      getContentPane().add(pan1, gbc);
      /* 
      Initializes the preview text field and adds it to 1,5,3,1 positions with CENTER alignment. 
      */
      gbc.gridx = 1; 
      gbc.gridy = 5;
      gbc.gridwidth = 3;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.CENTER;
      pan2 = new JPanel();
      pan2.setLayout(new FlowLayout());
      label = new JTextField("AaBaCcDdeEfFgGhHjJ", 15);
      label.setEditable(false);
      label.setBorder(BorderFactory.createEtchedBorder());
      label.setFont(new Font("Arial",Font.PLAIN,20));
      pan2.add(label);
      getContentPane().add(pan2, gbc);
      /* 
      Initializes the OK and Cancel button and adds these two buttons to the panel. 
      Sets layout of the panel to FlowLayout. Now add this panel to the 1, 6, 4, 1 
      positions with CENTER alignment. 
      */
      gbc.gridx = 1; 
      gbc.gridy = 6;
      gbc.gridwidth = 3;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.CENTER;
      pan3 = new JPanel();
      pan3.setLayout(new FlowLayout());
      ok = new JButton("OK");
      cancel = new JButton("Cancel");
      pan3.add(ok);
      pan3.add(cancel);
      getContentPane().add(pan3, gbc);
   }
   /*
   valueChanged() - This method is called when the user selects any menu item from the menu bar.
   Parameters: lse - a ListSelectionEvent object containing details of the event.
   Return Value: NA
   */
   public void valueChanged(ListSelectionEvent lse)
   {
      try
      {
         /* 
         This section is executed, when end user selects the item from Font list. 
         */
         if(lse.getSource() == fontList)
         {
            Font f1 = new Font(String.valueOf(fontList.getSelectedValue()),typeList.getSelectedIndex(),
            Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
            fontText.setText(String.valueOf(fontList.getSelectedValue()));
            label.setFont(f1);
         }
         /* 
         This section is executed, when end user selects the item from Size list. 
         */
         else if(lse.getSource() == sizeList)
         {
            Font f2 = new Font(String.valueOf(fontList.getSelectedValue()),typeList.getSelectedIndex(),
            Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
            sizeText.setText(String.valueOf(sizeList.getSelectedValue()));
            label.setFont(f2);
         }
         /* 
         This section is executed, when end user selects the item from Type list. 
         */
         else if(lse.getSource() == typeList)
         {
            Font f2 = new Font(String.valueOf(fontList.getSelectedValue()),typeList.getSelectedIndex(),
            Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
            typeText.setText(String.valueOf(typeList.getSelectedValue()));
            label.setFont(f2);
         }
      }
      catch(Exception nfe){}
   }
   /*
   font() - This method is set the font of the file text.
   Parameters: NA
   Return Value: font
   */
   public Font font()
   {
      /* Creates an object of the Font class. */
      Font font = new Font(String.valueOf(fontList.getSelectedValue()), typeList.getSelectedIndex(),
      Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
      /* Returns the font object */
      return font;
   }
   /*
   getOk() method - This method is invoked when end user click the OK button of the Font dialog box
   parameter - NA
   return value - ok
   */
   public JButton getOk()
   {
      return ok;
   }
   /*
   getCancel() method - This method is invoked when end user click the Cancel button of the Font dialog box.
   parameter - NA
   return value - cancel
   */
   public JButton getCancel()
   {
      return cancel;
   }
}