package textClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class Editor extends JFrame implements ActionListener, UndoableEditListener
{

	private static final long serialVersionUID = 1L;
	public ActionPerform action = new ActionPerform(this);
	UndoManager undoManager = new UndoManager();
	UndoAction undoAction = new UndoAction();
	RedoAction redoAction = new RedoAction();
	JMenuBar menu;
	
	JMenu file;
	JMenu edit;
	JMenu format;
	JMenu perspective;
	JMenu help;
	JMenuItem newfile;
	JMenuItem open;
	JMenuItem print;
	JMenuItem save;
	JMenuItem saveas;
	JMenuItem exit;
	JMenuItem cut;
	JMenuItem copy;
	JMenuItem paste;
	JMenuItem find;
	JMenuItem selectAll;
	JMenuItem datetime;
	JMenuItem font;
	JMenuItem color;
	JMenuItem about;
	
	static RSyntaxTextArea area;
	JPanel panel;
	JPanel calc;
	JPanel searchpane;
	JPanel thirdpane=new JPanel();
	JSplitPane below;
	RTextScrollPane scrollpane;
	String str;
	JCheckBoxMenuItem wordWrap;
	JCheckBoxMenuItem clang;
	JCheckBoxMenuItem cpluslang;
	JCheckBoxMenuItem javalang;
	JCheckBoxMenuItem htmllang;
	JSplitPane sidesplit;
	JSplitPane searchsplit;
	JSplitPane calcsplit;
	JSplitPane drawsplit;
	JPanel panel_in2;
	static String dropfile="";
    boolean skipOnce=true;

	
	static int lang=1;
	public Editor()
	{  
		menu = new JMenuBar();
		this.setJMenuBar(menu);
		setTitle("Text Editor - Gargi");
		file = new JMenu("File");
		menu.add(file);
		edit = new JMenu("Edit");
		menu.add(edit);
		format = new JMenu("Format");
		menu.add(format);
		perspective = new JMenu("Perspective");
		menu.add(perspective);
		help = new JMenu("Help");
		menu.add(help);
		file.setMnemonic('f');
		edit.setMnemonic('e');
		perspective.setMnemonic('p');
		format.setMnemonic('o');
		help.setMnemonic('h');
		newfile = new JMenuItem("New");
		newfile.addActionListener(this);
		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		file.add(newfile);
		
		open = new JMenuItem("Open...");
		open.addActionListener(this);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		file.add(open);
		
		save = new JMenuItem("Save");
		save.addActionListener(this);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		file.add(save);
		
		saveas = new JMenuItem("Save As...");
		saveas.addActionListener(this);
		file.add(saveas);
		
		print = new JMenuItem("Print");
		print.addActionListener(this);
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		file.add(print);
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		file.add(exit);
		
		file.insertSeparator(4);
		file.insertSeparator(6);
		
		edit.add(undoAction);
		edit.add(redoAction);
		cut = new JMenuItem("Cut");
		edit.add(cut);
		cut.addActionListener(this);
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copy = new JMenuItem("Copy");
		edit.add(copy);
		copy.addActionListener(this);
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		paste = new JMenuItem("Paste");
		edit.add(paste);
		paste.addActionListener(this);
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		find = new JMenuItem("Find");
		edit.add(find);
		find.addActionListener(this);
		find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		selectAll = new JMenuItem("Select All");
		edit.add(selectAll);
		selectAll.addActionListener(this);
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		datetime = new JMenuItem("Date/Time");
		edit.add(datetime);
		datetime.addActionListener(this);
		datetime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
		
		edit.insertSeparator(2);
		edit.insertSeparator(6);
		edit.insertSeparator(9);
		font = new JMenuItem("Font");
		format.add(font);
		font.addActionListener(this);
		color = new JMenuItem("Color");
		format.add(color);
		color.addActionListener(this);
		wordWrap = new JCheckBoxMenuItem("Word Wrap");
		format.add(wordWrap);
		wordWrap.addActionListener(this);
		clang = new JCheckBoxMenuItem("C Language (Default)");
		clang.setEnabled(true);
		perspective.add(clang);
		clang.addActionListener(this);

		cpluslang = new JCheckBoxMenuItem("C++ Language");
		cpluslang.setEnabled(true);
		perspective.add(cpluslang);
		cpluslang.addActionListener(this);

		javalang = new JCheckBoxMenuItem("Java Language");
		javalang.setEnabled(true);
		perspective.add(javalang);
		javalang.addActionListener(this);

		htmllang = new JCheckBoxMenuItem("HTML Language");
		htmllang.setEnabled(true);
		perspective.add(htmllang);
		htmllang.addActionListener(this);

		perspective.insertSeparator(4);

		about = new JMenuItem("Help Topics");
		help.add(about);
		about.addActionListener(this);

		createmainwindow(); 
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		}); 
	}

	public void createmainwindow(){
		panel = new JPanel();
		GridBagLayout grid = new GridBagLayout();
		panel.setLayout(grid);
		panel.setBorder(new LineBorder(Color.GREEN));
		
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx=1;
		gbc.weighty=1;

		JPanel panel_in1 = new JPanel();
		BoxLayout box = new BoxLayout(panel_in1,BoxLayout.X_AXIS );
		panel_in1.setLayout(box);
  
		area = new RSyntaxTextArea();
		setlang(); 
		area.setCodeFoldingEnabled(true);
		area.setAntiAliasingEnabled(true);
		area.getDocument().addUndoableEditListener(this);
		area.setLineWrap(false);
		area.setWrapStyleWord(false);
		area.setDragEnabled(true);
		area.setCloseCurlyBraces(true);
		area.setCurrentLineHighlightColor(new Color(230 ,228 ,225));
		
		scrollpane = new RTextScrollPane(area);
		scrollpane.setFoldIndicatorEnabled(true);
		panel_in1.add(scrollpane);
		panel_in1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.DARK_GRAY));
		panel.add(panel_in1,gbc);
		
		gbc.gridx=1;
		gbc.weightx=0.25;
		panel_in2 = new JPanel();
		panel_in2.setBorder(new LineBorder(Color.CYAN));
		panel_in2.setLayout(new BoxLayout(panel_in2, BoxLayout.Y_AXIS));

		SearchPanel obj=new SearchPanel();
		searchpane=obj.createSearchBox();
		Calculator calcobj=new Calculator();
		calc =calcobj.createCalci();
		below = new JSplitPane(JSplitPane.VERTICAL_SPLIT,calc,thirdpane);
		below.setDividerLocation(150);
		JSplitPane top = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchpane, below);
		top.setDividerLocation(80);
		panel_in2.add(top);

		thirdpane.setLayout(new BoxLayout(thirdpane, BoxLayout.Y_AXIS));
		panel.add(panel_in2,gbc);
		getContentPane().add(panel, BorderLayout.CENTER);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	};

	private void setProgPane() {
		if(skipOnce==false)
			thirdpane.removeAll();
		if(lang==1)
		{
			C_pane Cp=new C_pane();
			C_pane.javaCall=false;
			thirdpane.add(Cp.createCpane());
		}
		else if(lang==2)
		{
			C_pane Cp=new C_pane();
			C_pane.cplusCall=false;
			thirdpane.add(Cp.createCpane());
		}
		else
		{
			C_pane Cp=new C_pane();
			C_pane.javaCall=true;
			thirdpane.add(Cp.createCpane());
		}
		if(skipOnce==false)
		{thirdpane.validate();
		thirdpane.repaint();
		below.repaint();}
		else
			skipOnce=false;
	}

	public void undoableEditHappened(UndoableEditEvent e)
	{
		undoManager.addEdit(e.getEdit());
		undoAction.update();
		redoAction.update();
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == newfile)
		{
			clang.setEnabled(true);
			action.newFile();
		}
		else if(ae.getSource() == open)
		{	action.openFile(); }
		else if(ae.getSource() == save)
		{	action.saveFile();	}
		else if(ae.getSource() == saveas)
		{	action.saveAsFile();  }
		else if(ae.getSource() == print)
		{	action.printFile();	}
		else if(ae.getSource() == exit)
		{	action.exitFile();	}
		else if(ae.getSource() == cut)
		{	action.cutFile();	}
		else if(ae.getSource() == copy)
		{	action.copyFile();	}
		else if(ae.getSource() == paste)
		{	action.pasteFile();	}
		else if(ae.getSource() == datetime)
		{	action.dateTime();	}
		else if(ae.getSource() == find)
		{	action.findFile();	}
		else if(ae.getSource() == selectAll)
		{	action.selectAllFile();	}
		else if(ae.getSource() == font)
		{	action.fontFile();	}
		else if(ae.getSource() == color)
		{	action.colorFile(); 	}
		else if(ae.getSource() == wordWrap)
		{	action.wrapFile();	}
		else if(ae.getSource() == clang)
		{
			lang=1;
			refreshlang();
			setlang();
		}
		else if(ae.getSource() == cpluslang)
		{
			if(lang==2){lang=1;} else lang=2;
			refreshlang();
			setlang();
		}
		else if(ae.getSource() == javalang)
		{
			if (lang==3) lang=1; else lang=3;
			refreshlang();
			setlang();
		}
		else if(ae.getSource() == htmllang)
		{ 
			if (lang==4) lang=1; else lang=4; 
			refreshlang();
			setlang();
		}
		else if(ae.getSource() == about)
		{	action.aboutFile(); 	} 
	}
	private void refreshlang() {
		clang.setSelected(false);
		cpluslang.setSelected(false);
		javalang.setSelected(false);
		htmllang.setSelected(false);
	}

	private void setlang() {

		if(lang==1){area.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
		clang.setSelected(true);}
		else if(lang==2){area.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
		cpluslang.setSelected(true);}
		else if(lang==3){area.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		javalang.setSelected(true);}
		else if(lang==4){area.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
		htmllang.setSelected(true);}
		setProgPane();
	}

	public static void main(String args[])
	{
		Editor ed = new Editor();
		ed.setSize(1000, 460); 
		ed.setVisible(true);	
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		ed.setLocation((screenDimension.width - ed.getWidth()) / 2, (screenDimension.height - ed.getHeight()) / 2);
		//ed.setLocation(50,50);
		ed.show();
	}
	class UndoAction extends AbstractAction
	{
		private static final long serialVersionUID = 1L;
		public UndoAction()
		{
			super("Undo");
			setEnabled(false);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			try
			{	undoManager.undo();	}
			catch (Exception ex)
			{	System.out.println("Error: " + ex);	}
			update();
			redoAction.update();
		}
		
		protected void update()
		{
			if(undoManager.canUndo())
			{
				setEnabled(true);
				putValue("Undo", undoManager.getUndoPresentationName());
			}
			else
			{
				setEnabled(false);
				putValue(Action.NAME, "Undo");
			}
		}
	}
	
	class RedoAction extends AbstractAction
	{
		private static final long serialVersionUID = 1L;
		public RedoAction()
		{
			super("Redo");
			setEnabled(false);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			try
			{	undoManager.redo();	}
			catch (Exception ex)
			{	System.out.println("Error: " + ex);	}
			update();
			undoAction.update();
		}
		
		protected void update()
		{
			if(undoManager.canRedo())
			{
				setEnabled(true);
				putValue("Redo", undoManager.getRedoPresentationName());
			}
			else
			{
				setEnabled(false);
				putValue(Action.NAME, "Redo");
			}
		}
	}
}
