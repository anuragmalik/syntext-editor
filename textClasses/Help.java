package textClasses;

/* Imports javax.swing package classes. */
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
/* Imports java.awt package classes. */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
/* Imports javax.swing.event package classes. */
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
/*
class Help - This class creates an Help dialog that enables the end user to read the user manual of Editor.
Fields: 
   panel - Contains the document that to be printed.
   title - Contains the content of Title label.
   label1 - Contains the content of Help Topic label.
   label2 - Contains the content of Topic Details label.
   area - Displays the detail of sleeted topic.
   ok - Creates an OK button.
Methods:
valueChanged() - This method is invoked when an end user select the item from the List box. 
*/
public class Help extends JDialog implements ListSelectionListener
{
   
	private static final long serialVersionUID = 1L;
/* Declares the object of the JPanel class. */
   JPanel panel;
   /* Declares the objects of the JLabel class. */
   JLabel title;
   JLabel label1;
   JLabel label2;
   /* Declares the objects of the JScrollPane class. */
   JScrollPane listScroll;
   JScrollPane areaScroll;
   /* Declares the object of the JList class. */
   JList list;
   /* Declares the object of the JTextArea class. */
   JTextArea area;
   /* Declares the object of the JButton class. */
   JButton ok;
   GridBagLayout gbl;
   GridBagConstraints gbc;
   String str = null;
   /* Defines the default constructor. */
   public Help()
   {
      /* Sets the title of the Help dialog. */
      setTitle("Help");
      /* Sets the size of the Help dialog. */
      setSize(600, 300);
      /* Sets resizable button to false. */ 
      setResizable(false);
      /* Initializes the object of the GridBagLayout class. */
      gbl = new GridBagLayout();
      /* Sets the Layout. */
      JPanel panel_main = new JPanel();
      panel_main.setLayout(gbl);
      /* Creates an object of GridBagConstraints class. */
      gbc = new GridBagConstraints();
      /* 
      Initializes the Help Topic label object and adds it to the 1, 1, 1, 1 positions with WEST alignment. 
      */
      gbc.fill = GridBagConstraints.BOTH;
      gbc.gridx = 1; 
      gbc.gridy = 1;
      gbc.weightx=0.5;
      gbc.weighty=0.1;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      label1 = new JLabel("Help Topics:");
      panel_main.add(label1, gbc);
      /* 
      Initializes the Topic Details label object and adds it to the 2, 1, 1, 1 positions with WEST alignment. 
      */
      gbc.gridx = 2; 
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.weightx=1.0;
      gbc.anchor = GridBagConstraints.WEST;
      label2 = new JLabel("Topic Details:");
      panel_main.add(label2, gbc);
      /* 
      Initializes the Help Topic List object and adds it to the Help Topic scroll pane object.
      Next, adds this scroll pane object to 1, 2, 1, 1 positions with WEST alignment. 
      */
      gbc.gridx = 1; 
      gbc.gridy = 2;
      gbc.weightx=1;
      gbc.weighty=0.5;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      String[] listString = {"Change the text color", 
      "Cut, Copy and Paste the text", 
      "Find a word in the file", 
      "Insert date and time in the file", 
      "Open a file", 
      "Print a file", 
      "Save a file", 
      "Change the text font", 
      "Using file locks", 
      "Wrap the file text"}; 
      list = new JList(listString);
      /* Sets the height of the list box. */
      list.setFixedCellHeight(25); 
      list.addListSelectionListener(this);
      listScroll = new JScrollPane(list);
      panel_main.add(listScroll, gbc);
      /* 
      Initializes the text area object and adds it to the text area scroll pane object. 
      Next, adds this scroll pane objects to 2, 2, 1, 1 positions with WEST alignment. 
      */
      gbc.gridx = 2; 
      gbc.gridy = 2;
      gbc.weightx=1;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.WEST;
      area = new JTextArea(12, 60);
      /* 
      Sets the editable property of the text area to false. 
      */
      area.setEditable(false);
      /* Sets the Line and word wrap style to TRUE */
      area.setLineWrap(true);
      area.setWrapStyleWord(true);
      areaScroll = new JScrollPane(area);
      panel_main.add(areaScroll, gbc);
      /* 
      Initializes the OK button and adds this OK buttons to the panel. Next, sets the layout 
      of the panel to FlowLayout. Now, adds this panel to the 1, 3, 2, 1 positions with CENTER alignment. 
      */
      gbc.gridx = 1; 
      gbc.gridy = 3;
      gbc.weighty=0.1;
      gbc.gridwidth = 2;
      gbc.gridheight = 1;
      gbc.anchor = GridBagConstraints.CENTER;
      panel = new JPanel();
      panel.setLayout(new FlowLayout());
      ok = new JButton("OK");
      panel.add(ok);
      panel_main.add(panel, gbc);
      
      getContentPane().add(panel_main, BorderLayout.CENTER);
   }
   /*
   valueChanged() - This method is called when the user selects any help topic from the list
   Parameters: lse - Represents an object of the ListSelectionEvent class that contains the details of the event.
   Return Value: NA
   */
   public void valueChanged(ListSelectionEvent lse)
   { 
      try
      {
         /* 
         This section is executed, when end user selects the item from Help Topic list. 
         */
         if(lse.getSource() == list)
         {
            /* 
            When end user selects "Change the text color" from the List. 
            */
            if(list.getSelectedIndex() == 0)
            {
               str = "The steps to change the font color are:\n\n" + " 1." + 
               " Select Format->Color command from the menu bar.\n\n" + " 2. " +
               " Scroll the Red slider, Green slider, and Blue sliders to specify the " +
              " R, G, B values for the color.\n\n" + " 3. Click OK button."; 
            }
            /* 
            When end user selects "Cut, Copy, and Paste the text" from the List.
            */
            else if(list.getSelectedIndex() == 1)
            {
               str = "The steps to cut, copy, and paste the text are:\n\n" + 
               " 1. Select the Edit->Cut command from the menu bar to cut the selected text. \n\n" +
               " 2. Select the Edit->Copy command from the menu bar to copy the selected text.\n\n" + 
               " 3. Select the Edit-Paste command from the menu bar to paste the cut or copied text to the specified location.";
            }
            /* 
            When end user selects "Find a word in the file" from the List.
            */
            else if(list.getSelectedIndex() == 2)
            {
               str = "The steps to find a specific character or word are:\n\n" + 
               " 1. Select the Edit->Find command from the menu bar.\n\n" +
               " 2. Specify the character or word in the Type the Word to find text box.\n\n" + 
               " 3. Click the OK button.\n\n" + 
               " 4. Select the Edit->Find Next command from the menu bar to find the next word.";
            }
            /* 
            When end user selects "Insert date and time in the file" from the List.
            */
            else if(list.getSelectedIndex() == 3)
            {
               str = "The step to insert date and time in the document is:\n\n" +
               " 1. Select Edit->Date/Time command from the menu bar to insert the current date and time in the file."; 
            }
            /* 
            When end user selects "Open a file" from the List.
            */
            else if(list.getSelectedIndex() == 4)
            {
               str = "The steps to open a file are:\n\n" +
               " 1. Select File-> Open command from the menu bar.\n\n"
                + " 2. Browse the File that you want to open.\n\n" +
                " 3. Select the appropriate file in File name text box.\n\n" 
                + " 4. Click OK button.";
               
            }
            /* 
            When end user selects "Print a file" from the List.
            */
            else if(list.getSelectedIndex() == 5)
            {
               str = "The steps to print the document are:\n\n" +
               " 1. Select File->  Print command from the menu bar.\n\n"
               + " 2. Specify the print range.\n\n" +
               "  3. Specify the copies in Number of copies text box.\n\n" + " 4. Click OK button.";
              
            }
            /* 
            When end user selects "Save a file" from the List.
            */
            else if(list.getSelectedIndex() == 6)
            {
               str = "The steps to save a new document are:\n\n" +
               " 1. Select File->  Save As command from the menu bar.\n\n"
               + " 2. Browse the location where you want to save the file.\n\n"
                + " 3. Specify the name of the file in  File name text box.\n\n"
               + " 4. Click OK button.\n\n" +
               "  5. Select File->Save command from the menu bar to save the existing file.";
              
            }
            /* 
            When end user selects "Change the text font" from the List.
            */
            else if(list.getSelectedIndex() == 7)
            {
               str = "The steps to change the font are:\n\n" +
               " 1. Select the Format->  Font command from the menu bar.\n\n" 
              + " 2. Select the font from the Fonts list box.\n\n"
                + " 3. Select the font size from the Size list box.\n\n" +
                "4. Select the font style from the Style list box.";
               
            }
            /* 
            When end user selects "Using file locks" from the List.
            */
            else if(list.getSelectedIndex() == 8)
            {
               str = "The steps to lock the files are:\n\n" +
               " 1. Select Lock->  Exclusive command from the menu bar to lock the file with exclusive lock.\n\n"
              + " 2. Select Lock->Share command from the menu bar to lock the file with share lock.";
               
            }
            /* 
            When end user selects "Wrap the file text" from the List.
            */
            else if(list.getSelectedIndex() == 9)
            {
               str = "The step to wrap a text in the window is:\n\n" + 
               " 1. Select Format->Word Wrap command from the menu bar.";
            }
            /* Sets the text to the text area. */
            area.setText(str);
            } 
         }
         catch(Exception nfe)
         {
      }
   }
   /*
   getOk() method - This method is invoked when end user click the OK button of the Font dialog box.
   parameter - NA
   return value - ok
   */
   public JButton getOk()
   {
      return ok;
   }
}