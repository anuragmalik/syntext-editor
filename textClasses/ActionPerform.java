package textClasses;
/* Imports the java.util package classes. */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ActionPerform
{
	int value; 
	int option; 
	String content = null; 
	String name = null; 
	String word; 
	String str;
	static boolean cdnd=false;
	DropFile dropobj;
	static JFileChooser jfc = new JFileChooser("."); 
	public Editor ed; 
	public FontClass font = new FontClass();
	public ColorClass cc = new ColorClass();
	public Help h = new Help();
	public PrintClass pc;
	
	public ActionPerform() {}
	public ActionPerform(Editor ed)
	{
		this.ed = ed; }
	
	public void newFile()
	{
		if(!ed.area.getText().equals("") && !ed.area.getText().equals(content))
		{	if(name == null)
			{	option = JOptionPane.showConfirmDialog(null,"Do you want to save the file?");
				if(option == 0)
				{	saveAs();
				 	ed.area.setText("");	}
				if(option == 1)
				{	ed.area.setText("");	}
			}
			else
			{
				option = JOptionPane.showConfirmDialog(null,"Do you want to save the file?");
				if(option == 0)
				{	save();
					ed.area.setText("");	}
				if(option == 1)
				{	ed.area.setText("");	}
			}
		}
		else
		{	ed.area.setText("");	}
		ed.setTitle("Untitled - Text Editor");
	}
	
	public void openFile()
	{
		if(!ed.area.getText().equals("") && !ed.area.getText().equals(content))
		{
			if(name == null)
			{	option = JOptionPane.showConfirmDialog(null,"Do you want to save the file?");
				if(option == 0)
				{	saveAs(); 
					if(cdnd==true)	open2();
					else	open();
				}
				if(option == 1)
				{
					if(cdnd==true)	open2();
					else	open();
				}
			}
			else
			{	option = JOptionPane.showConfirmDialog(null,"Do you want to save the file?");
				if(option == 0)
				{	save();
					if(cdnd==true)	open2();
					else	open();
				}
				if(option == 1)
				{	if(cdnd==true)	open2();
					else	open();
				}
			}
		}
		else
		{	if(cdnd==true)		open2();
			else	open();
		}
	}

	public void saveFile()
	{
		if(name == null)
		{	saveAs(); 	}
		else
		{	save();	}
	}

	public void saveAsFile()
	{	saveAs(); 	}
	
	public void printFile()
	{	pc = new PrintClass(ed.area, ed);
		pc.print();	}
	
	public void exitFile()
	{	if(!ed.area.getText().equals("") && !ed.area.getText().equals(content))
		{	if(name == null)
			{
			option = JOptionPane.showConfirmDialog(null,"Do you want to save the file?");
			if(option == 0)
			{		saveAs();
					System.exit(0); 	}
				if(option == 1)
				{
					System.exit(0);
				}
			}
			else
			{	option = JOptionPane.showConfirmDialog(null,"Do you want to save the file?");
				if(option == 0)
				{	save();
					System.exit(0);	}
				if(option == 1)
				{	System.exit(0);	}
			}
		}
		else
		{	System.exit(0);	}
	}
	
	public void cutFile()
	{	ed.area.cut();	}
	public void copyFile()
	{	ed.area.copy();	}	
	public void pasteFile()
	{	ed.area.paste();	}
	public void selectAllFile()
	{	ed.area.selectAll();	}
	public void findFile()
	{	try
		{	word = JOptionPane.showInputDialog("Type the word to find");
			while(ed.area.getText().indexOf(word) == -1)
			{	JOptionPane.showMessageDialog(null,"Word not found!","No match",JOptionPane.WARNING_MESSAGE);
				word = JOptionPane.showInputDialog(" Type the word to find");	}
 			ed.area.select(ed.area.getText().indexOf(word),
			ed.area.getText().indexOf(word) + word.length());
		}
		catch(Exception ex)
		{	JOptionPane.showMessageDialog
			(null,"Search canceled","Aborted",JOptionPane.WARNING_MESSAGE); 	}
	}

	public void dateTime()
	{	Date d = new Date();
		String str = d.toLocaleString(); 
		ed.area.append(str);
	}
	
	public void fontFile()
	{	font.setVisible(true); 
		font.pack(); 
		font.getOk().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	ed.area.setFont(font.font());
				font.label.setFont(new Font("Arial",Font.PLAIN,15));
				font.setVisible(false);	}
		}); 
		
		font.getCancel().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	font.label.setFont(new Font("Arial",Font.PLAIN,15));
			 	font.setVisible(false);	}
		});
	}

	public void colorFile()
	{	cc.setVisible(true); 
		cc.pack(); 
		cc.getOk().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	ed.area.setForeground(cc.color());
				cc.setVisible(false);	}
		}); 
		cc.getCancel().addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent ae)
			{	cc.setVisible(false);	}
		});
	}
	
	public void wrapFile()
	{	if(ed.wordWrap.getState() == true)
		{	ed.area.setLineWrap(true);
			ed.area.setWrapStyleWord(true);	}
		else
		{	ed.area.setLineWrap(false);
			ed.area.setWrapStyleWord(false);	}
	}
	
	public void aboutFile()
	{	h.setVisible(true);
		h.getOk().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	h.setVisible(false);	}
		}); 
	}
	
	public void open2()
	{	Editor.area.setText(null);
		try
		{	FileInputStream fin = new FileInputStream(Editor.dropfile);
			FileChannel fchan = fin.getChannel();
			long fsize = fchan.size(); 
			ByteBuffer buff = ByteBuffer.allocate((int)fsize);
			fchan.read(buff);
		 	buff.rewind();
			String str = new String(buff.array());
			ed.area.append(str);
			content = ed.area.getText();
			fchan.close();
			fin.close();
		}
		catch(IOException ioe)
		{	System.err.println("I/O Error on Open");	}
	}
	
	public void open()
	{	value = jfc.showOpenDialog(ed); 
		if(value == JFileChooser.APPROVE_OPTION)
		{
			ed.area.setText(null);
			name = jfc.getSelectedFile().getPath();
			try
			{	FileInputStream fin = new FileInputStream(jfc.getSelectedFile());
				FileChannel fchan = fin.getChannel();
				long fsize = fchan.size();
				ByteBuffer buff = ByteBuffer.allocate((int)fsize);
				fchan.read(buff);
				buff.rewind();
				String str = new String(buff.array());
				ed.area.append(str);
				content = ed.area.getText();
				fchan.close();
				fin.close();
			}
			catch(IOException ioe)
			{	System.err.println("I/O Error on Open");	}
			ed.setTitle(jfc.getSelectedFile().getAbsolutePath()+ " - Text Editor");
			str = jfc.getSelectedFile().getAbsolutePath();
		}
	}
	
	public void save()
	{	if(value == JFileChooser.APPROVE_OPTION)
		{	try
			{	FileOutputStream fout = new FileOutputStream(jfc.getSelectedFile());
				PrintWriter pw = new PrintWriter(fout);
				content = ed.area.getText();
				StringTokenizer st=new StringTokenizer(content,System.getProperty("line.separator"));
				while(st.hasMoreTokens())
				{
					pw.println(st.nextToken());
				}
				pw.close();
				fout.close();	}
			catch(IOException ioe)
			{	System.err.println("I/O Error on Save");	}
			ed.setTitle(jfc.getSelectedFile().getName()+ " - Text Editor");
			str = jfc.getSelectedFile().getAbsolutePath();
		}
	}
	
	public void saveAs()
	{	jfc.setDialogTitle("Save As"); 
		value = jfc.showSaveDialog(ed);
		if(value == JFileChooser.APPROVE_OPTION)
		{	try
			{	FileOutputStream fout = new FileOutputStream(jfc.getSelectedFile() + ".txt");
				PrintWriter pw = new PrintWriter(fout);
				content = ed.area.getText();
				name = jfc.getSelectedFile().getPath();
				StringTokenizer st=new StringTokenizer(content,System.getProperty("line.separator"));
			while(st.hasMoreTokens())
				{	pw.println(st.nextToken());	}
				pw.close();
				fout.close();
			}
			catch(IOException ioe)
			{	System.err.println ("I/O Error on Save");	}
			ed.setTitle(jfc.getSelectedFile().getAbsolutePath() + " - Text Editor");
			str = jfc.getSelectedFile().getAbsolutePath();
		} 
	}
}