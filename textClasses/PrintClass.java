package textClasses;

/* Imports java.awt.print package class. */
import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
/* Imports java.awt package classes. */
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
/* Imports javax.swing package class. */
import javax.swing.RepaintManager;
/*
class PrintClass - This class implements Printable interface and enable the end user to print the document.
Fields: 
   compPrint - Contains the document that to be printed.
Methods:
   print() - This method prints the current document opened in the text editor window 
*/
public class PrintClass implements Printable
{
   /* Declares the object of Component class. */
   private Component compPrint;
   public Editor editor;
   /* Defines the default constructor. */
   public PrintClass(Component compPrint, Editor editor)
   {
      this.compPrint = compPrint;
      this.editor = editor;
   }
   /*
   print() - This method prints the document
   Parameters: lse - NA
   Return Value: NA
   */
   public void print()
   {
      /* Creates an object of the PrinterJob class */
      PrinterJob printer = PrinterJob.getPrinterJob();
      /* Sets the object of PrinterJob class to printable */
      printer.setPrintable(this);
      if(printer.printDialog())
      { 
         try
         {
            /* Prints the document */
            printer.print();
         }
         catch(PrinterException pe)
         {
            System.out.println("Error: " + pe);
         }
      }
      editor.show();
   }
   /*
   print() - This method opens a Print dialog box
   Parameters: 
   g - Represents the object of Graphics class
   format - Represents the object of PageFormat class
   index - Represents an index of page
   Return Value: int PAGE_EXIST
   */
   public int print(Graphics g, PageFormat format, int index)
   {
      if(index > 0)
      {
         return(NO_SUCH_PAGE);
      }
      else
      {
         /* 
         Creates an object of the Graphics2D class and converts simple graphics to 2D graphics 
         */
         Graphics2D g2d = (Graphics2D)g;
         /* 
         Translates the origin of the Graphics2D context to the point (x, y) 
         in the current coordinate system 
         */
         g2d.translate(format.getImageableX(), format.getImageableY());
         /* Creates the object of the RepaintManager class */ 
         RepaintManager manager1 = RepaintManager.currentManager(compPrint);
         /* Sets the double buffer to FALSE */
         manager1.setDoubleBufferingEnabled(false);
         /* Paints the component */
         compPrint.paint(g2d);
         /* Creates the object of RepaintManager class */ 
         RepaintManager manager2 = RepaintManager.currentManager(compPrint);
         /* Sets the double buffer to TRUE */
         manager2.setDoubleBufferingEnabled(true);
         /* Returns the PAGE_EXIST value */
         return(PAGE_EXISTS);
      }
   } 
}