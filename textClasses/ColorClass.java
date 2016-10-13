package textClasses;

/* Imports javax.swing package classes. */
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JDialog;
import javax.swing.BorderFactory;
/* Imports java.awt package classes. */
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
/* Imports javax.swing.event package classes. */
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
/*
class ColorClass - This class creates a Color dialog box that enables the end user to 
change the color of the file text.
Fields: 
   panel - Contains all the components of the Color dialog.
   redLabel - Contains the content of Red label.
   greenLabel - Contains the content of Green label.
   blueLabel - Contains the content of Blue label.
   previewLabel - Contains the content of Preview label.
   redSlider - Enables the end user to select the red value.
   greenSlider - Enables the end user to select the green value.
   blueSlider - Enables the end user to select the blue value.
   labelText - Contains the content of preview text.
   ok - Creates an OK button.
   cancel - Creates a cancel button.
   r - Stores the Red value.
   g - Stores the Green value. 
   b - Stores the Blue value. 
Methods:
   getOK() - This method returns the OK button object.
   getCancel() - This method returns the Cancel button object.
   stateChanged() - This method is invoked when an end user slide slider.
   color() - This method returns the color.
   */
public class ColorClass extends JDialog implements ChangeListener
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/* Declares the object of the JPanel class. */
   JPanel panel;
   /* Declares the objects of the JLabel class. */
   JLabel redLabel;
   JLabel greenLabel;
   JLabel blueLabel;
   JLabel previewLabel;
   /* Declares the objects of the JSlider class */
   JSlider redSlider;
   JSlider greenSlider;
   JSlider blueSlider;
   /* Declares the object of the JTextField class */
   JTextField labelText;
   /* Declares the objects of the JButton class */
   JButton ok;
   JButton cancel;
   /* Declares the integer for storing the RGB values */
   int r = 0;
   int g = 0;
   int b = 0;
   public Editor ed;
   /* Defines the default constructor of the ColorClass class. */
   public ColorClass()
   {
      /* Sets the title of the Font dialog. */
      setTitle("Color Dialog");
      /* Sets resizable button to false. */
      setResizable(false);
      /* Initializes the object of the JPanel class. */
      panel = new JPanel();
      /* Sets the Layout as GridLayout.*/
      panel.setLayout(new GridLayout(5,2,1,1));
      /* Adds the panel to Color dialog frame */
      getContentPane().add(panel);
      /* Initializes and adds Red label to the panel. */ 
      redLabel = new JLabel("Red: ");
      panel.add(redLabel);
      /* Initializes and adds Red slider to the panel. */
      redSlider = new JSlider(0, 255, 1);
      panel.add(redSlider);
      /* Sets Border to the Red slider. */
      redSlider.setBorder(BorderFactory.createEtchedBorder());
      /* Adds state change listener to Red slider. */
      redSlider.addChangeListener(this);
      /* Initializes and adds Green label to the panel. */ 
      greenLabel = new JLabel("Green: ");
      panel.add(greenLabel);
      /* Initializes and adds Green slider to the panel. */
      greenSlider = new JSlider(0, 255, 1);
      panel.add(greenSlider);
      /* Sets Border to the Green slider. */
      greenSlider.setBorder(BorderFactory.createEtchedBorder());
      /* Adds state change listener to Green slider. */
      greenSlider.addChangeListener(this);
      /* Initializes and adds Blue label to the panel. */ 
      blueLabel = new JLabel("Blue: ");
      panel.add(blueLabel);
      /* Initializes and adds Blue slider to the panel. */
      blueSlider = new JSlider(0, 255, 1);
      panel.add(blueSlider);
      /* Sets Border to the Blue slider. */
      blueSlider.setBorder(BorderFactory.createEtchedBorder());
      /* Adds state change listener to Blue slider. */
      blueSlider.addChangeListener(this);
      /* Initializes and add Preview label to the panel. */ 
      previewLabel = new JLabel("Preview: ");
      panel.add(previewLabel);
      /* 
      Initializes and adds Preview text field to the panel. 
      */ 
      labelText = new JTextField(10);
      panel.add(labelText);
      /* Initializes and adds OK button to the panel. */ 
      ok = new JButton("OK");
      panel.add(ok);
      /* Initializes and adds Cancel button to the panel. */ 
      cancel = new JButton("Cancel");
      panel.add(cancel);
   }
   /*
   stateChanged() - This method is called when the user slides any slider of the Color dialog box.
   Parameters: ce – Represents an object of the ChangeEvent class that contains the details of the event.
   Return Value: NA
   */
   public void stateChanged(ChangeEvent ce) 
   {
      /* 
      This section is executed when end user slides the Red slider. 
      */
      if(ce.getSource() == redSlider)
      {
         r = redSlider.getValue(); 
      }
      /* 
      This section is executed, when end user slides the Green slider. 
      */
      else if(ce.getSource() == greenSlider)
      {
         g = greenSlider.getValue();
      }
      /* 
      This section is executed, when end user slides the Blue slider. 
      */
      else if(ce.getSource() == blueSlider)
      {
         b = blueSlider.getValue(); 
      }
      /* Creates the object of the Color class. */
      Color c = new Color(r, g, b);
      /* 
      Sets the background color of the preview text field. 
      */
      labelText.setBackground(c); 
   }
   /*
   color() - This method is set color of the file text.
   Parameters: NA
   Return Value: color
   */
   public Color color()
   {
      Color color = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
      return color;
   }
   /*
   getOk() method - This method is invoked when end user click the OK button of the Color dialog box.
   parameter - NA
   return value - ok
   */
   public JButton getOk()
   {
      return ok;
   }
   /*
   getCancel() method - This method is invoked when end user click the Cancel 
   button of the Color dialog box.
   parameter - NA
   return value - cancel
   */
   public JButton getCancel()
   {
      return cancel;
   }
}