package textClasses;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class C_pane extends JFrame {
	private static final long serialVersionUID = 1L;
	String for_loopi,if_else,for_loopd,while_loop,dowhile_loop,Cclass,switch_case,struct,add_func,main_fn;
	String nclass;
	JPanel C_panel=new JPanel();
	DefaultListModel listModel=new DefaultListModel();
	JList C_list=new JList(listModel);
	JScrollPane pane = new JScrollPane();
	JPanel DynamicPane=new JPanel();
	static boolean javaCall;
	static boolean cplusCall;
	


	JPanel createCpane()
	{   
	main_fn="//add main function\n return_type main()\n{\n}";
	for_loopi="//For Loop (inc)\nfor(var=0;var<=limit;var++)\n  {\t}";
	for_loopd="//For Loop (dec)\nfor(var=limit;var>=0;var--)\n  {\t}";
	if_else="//if else\nif('condition')\n  {\t}\nelse\n  {\t}";
	while_loop="//While Loop\nwhile('condition')\n  {\n\n  }";
	dowhile_loop="//do while Loop\n do\n {\n'statements'\n }\n while('condition')";
	switch_case="//Switch Case Statements\n switch(var)\n  {\n\tcase 'case1': 'statements'; break;\n\t.\n\t.\n" +
			"\tcase 'caseN': 'statements'; break;\n\tdefault : 'statements'; break;\n  }";
	add_func="//Function :\n return_type fn_name(list of args)\n{\n}";
	struct="//Structure:\n struct structure_name\n {\n public: //variables \n protected : //variables \n private: //variables\n };";
    nclass="//Class\n'Access' class 'class name'\n {\n\n }";
    Cclass="//Class:\n class class_name\n {\n public: //variables \n protected : //variables \n private: //variables\n };";
	
	pane.setPreferredSize(new Dimension(100,200));
	pane.getViewport().add(C_list);
	C_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	C_list.setLayoutOrientation(JList.VERTICAL);
	C_list.setVisibleRowCount(-1);
	CustomListRenderer listRendererObj=new CustomListRenderer();
	 C_list.setCellRenderer(listRendererObj);
	 
	 if(javaCall==true)
	listRendererObj.setPerspectiveinpanel(SyntaxConstants.SYNTAX_STYLE_JAVA);
	 else if(cplusCall==true)
	 listRendererObj.setPerspectiveinpanel(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
	 else
		 listRendererObj.setPerspectiveinpanel(SyntaxConstants.SYNTAX_STYLE_C); 
	
	 add_listener();
	listModel.addElement(main_fn);
	listModel.addElement(for_loopi);
	listModel.addElement(for_loopd);
	listModel.addElement(if_else);
	listModel.addElement(while_loop);
	listModel.addElement(dowhile_loop);
	
	if(javaCall==true)
	{listModel.addElement(nclass);}
	else
	{listModel.addElement(struct);}
	
	listModel.addElement(add_func);
	listModel.addElement(switch_case);
	if(cplusCall==true)
		listModel.addElement(Cclass);
	
	
		
	
	C_panel.setLayout(new BoxLayout(C_panel, BoxLayout.Y_AXIS));
	DynamicPane.setBorder(new LineBorder(Color.PINK));

	C_panel.add(pane);
	C_panel.add(DynamicPane);
	return C_panel;
	}

	public void add_listener(){
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					add_pane(e);
				}
			}
		};
		C_list.addMouseListener(mouseListener);
	}

	public void add_pane(MouseEvent e)
	{
		int index = C_list.locationToIndex(e.getPoint());
		boolean Loop=true;

		switch(index)
		{
		case 0:
			removeComponents();
			create_func(Loop);
			break;
		case 1:
			removeComponents();
			create_forloop(Loop);
			break;
		case 2:
			removeComponents();
			Loop=false;
			create_forloop(Loop);
			break;
		case 3:
			removeComponents();
			create_ifelse();
			break;
		case 4:
			removeComponents();
			create_while_dowhl(Loop);
			break;
		case 5:
			removeComponents();
			Loop=false;
			create_while_dowhl(Loop);
			break;
		case 6:
			removeComponents();
			if(javaCall==true)
				create_class();
			else
			create_struct();
			break;
		case 7:
			removeComponents();
			Loop=false;
			create_func(Loop);
			break;
		case 8:
			removeComponents();
			create_switch();
			break;
		case 9:
			removeComponents();
			create_Cclass();
			break;
		}
	}
	
	public void create_class()
	{
		JLabel l1=new JLabel("Enter Class Name");
		final JTextArea a1=new JTextArea(1,20);
		JLabel l2=new JLabel("Enter Modifier");
		final JTextArea a2=new JTextArea(1,20);
		a1.setOpaque(true);
		a2.setOpaque(true);
		
		final JButton ok=new JButton("ok");
		
		DynamicPane.add(l1);
		DynamicPane.add(a1);
		DynamicPane.add(l2);
		DynamicPane.add(a2);
		DynamicPane.add(ok);
		addComponents();

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String str1=a1.getText();
				String str2=a2.getText();
				String strF=str2+" class "+str1+"\n {\n\n }";
							
				Editor.area.append(strF);
				removeComponents();
			}
		});
		
	}
	
	public void create_forloop(final boolean incLoop)
	{
		JLabel lb1=new JLabel("Enter Variable");
		final JTextArea ar1=new JTextArea(1,5);
		ar1.setOpaque(true);
		JLabel lb2=new JLabel("enter start");
		final JTextArea ar2=new JTextArea(1,5);
		ar2.setOpaque(true);
		JLabel lb3=new JLabel("enter end");
		final JTextArea ar3=new JTextArea(1,5);
		ar3.setOpaque(true);
		JButton ok=new JButton("ok");
		DynamicPane.add(lb1);
		DynamicPane.add(ar1);
		DynamicPane.add(lb2);
		DynamicPane.add(ar2);
		DynamicPane.add(lb3);
		DynamicPane.add(ar3);
		DynamicPane.add(ok);
		ar1.setText("x");
		ar2.setText("0");
		ar3.setText("0");
		addComponents();



		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				String var =ar1.getText(); 	
				String limit=ar3.getText();
				String start=ar2.getText();
				String for_loop=null;
				if(incLoop == true)
				{for_loop="for("+var+"="+start+";"+var+"<="+limit+";"+var+"++)\n  {\n\n  }";
				}
				else
				{
					for_loop="for("+var+"="+limit+";"+var+">="+start+";"+var+"--)\n  {\n\n  }";
				}
				System.out.println(for_loop); 
				Editor.area.append(for_loop+"\n");


				removeComponents();
			}


		});}

	public void create_ifelse()
	{
		JLabel l1=new JLabel("Enter if condition");
		final JTextArea a1=new JTextArea(1,20);
		a1.setOpaque(true);
		final JCheckBox elsebox=new JCheckBox("add else?");
		final JButton ok=new JButton("ok");
		final JLabel l2=new JLabel("else selected");
		DynamicPane.add(l1);
		DynamicPane.add(a1);
		DynamicPane.add(elsebox);
		DynamicPane.add(ok);
		addComponents();
		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();
				if (state == ItemEvent.SELECTED) {

					DynamicPane.remove(ok);
					DynamicPane.add(l2);

					DynamicPane.add(ok);
					DynamicPane.validate();

				}
				else
					if(state==ItemEvent.DESELECTED)
					{
						DynamicPane.remove(l2);
						DynamicPane.validate();
					}

			}
		};
		elsebox.addItemListener(itemListener);

		ok.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) 
			{
				String str="";
				String if_str=a1.getText();
				System.out.println(DynamicPane.countComponents());
				if(DynamicPane.countComponents()<5)
				{
					str="if("+if_str+")\n\t{\n\n\t}";
				}
				else
				{
					str="if("+if_str+")\n\t{\n\n\t}\nelse\n\t{\n\n\t}";
				}
				Editor.area.append(str);
				removeComponents();
			}
		});
	}

	public void create_while_dowhl(final boolean whlLoop)
	{
		final JLabel l1=new JLabel("Enter condition");
		final JTextArea a1=new JTextArea(1,20);
		final JButton ok=new JButton("ok");
		DynamicPane.add(l1);
		DynamicPane.add(a1);
		DynamicPane.add(ok);
		addComponents();

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String str="";
				if(whlLoop==true)
				{
					str="while("+a1.getText()+")\n\t{\n\n  }";
				}

				else
				{
					str="do\n {\n }\n while("+a1.getText()+");";
				}
				Editor.area.append(str);
				removeComponents();
			}
		}
				);
	}

	public void create_switch()
	{
		JLabel l1=new JLabel("Enter variable");
		final JTextArea a1=new JTextArea(1,5);
		JLabel l2=new JLabel("enter number of cases");
		final JTextArea a2=new JTextArea(1,5);
		JButton ok=new JButton("ok");
		DynamicPane.add(l1);
		DynamicPane.add(a1);
		DynamicPane.add(l2);
		DynamicPane.add(a2);
		DynamicPane.add(ok);
		addComponents();


		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int n=Integer.parseInt(a2.getText());
				String str="";
				String cases="\tcase val1:\n break;\n";

				for(int i=2;i<=n;i++)
				{
					String x=""+i;
					cases+="\tcase val"+x+":\n break;\n";
				}
				str="switch("+a1.getText()+")\n{"+cases+"\tdefault:\n break;\n}";
				Editor.area.append(str);
				removeComponents();
			}
		}
				);

	}

	public void create_func(final boolean mainfnc)
	{

		JLabel l1=new JLabel("Enter function name");
		final JTextArea a1=new JTextArea(1,20);
		JLabel l2=new JLabel("enter return type");
		final JTextArea a2=new JTextArea(1,20);
		JLabel l3=new JLabel("enter arguments");
		final JTextArea a3=new JTextArea(1,20);
		JButton ok=new JButton("ok");


		if(mainfnc==false)
		{
			DynamicPane.add(l1);
			DynamicPane.add(a1);
			DynamicPane.add(l2);
			DynamicPane.add(a2);
			DynamicPane.add(l3);
			DynamicPane.add(a3);
			DynamicPane.add(ok);
		}
		else
		{
			DynamicPane.add(l2);
			DynamicPane.add(a2);
			DynamicPane.add(ok);
		}
		addComponents();

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String str="";
				String ret="return var;";
				if(mainfnc==true)
				{
					if(a2.getText().equals("void"))
					{
						str="void main()\n {\n }";
					}
					else
					{
						str=a2.getText()+" main()\n {\n "+ret+"\n }";
					}
				}

				else
				{
					if(a2.getText().equals("void"))
					{
						str="void "+a1.getText()+"("+a3.getText()+")\n {\n }";
					}
					else
					{
						str=a2.getText()+" "+a1.getText()+"("+a3.getText()+")\n {\n "+ret+"\n }";
					}
				}

				Editor.area.append(str);
				removeComponents();

			}
		}
				);
	}
	public void create_struct()
	{
		JLabel l1=new JLabel("Enter structure name");
		final JTextArea a1=new JTextArea(1,15);
		JButton ok=new JButton("ok");
		DynamicPane.add(l1);
		DynamicPane.add(a1);
		DynamicPane.add(ok);
		addComponents();

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String str="struct "+a1.getText()+"\n {\n public: //variables \n protected : //variables \n private: //variables\n };";
				Editor.area.append(str);
				removeComponents();
			}
		}
				);


	}

	public void create_Cclass()
	{
		JLabel l1=new JLabel("Enter class name");
		final JTextArea a1=new JTextArea(1,15);
		JButton ok=new JButton("ok");
		DynamicPane.add(l1);
		DynamicPane.add(a1);
		DynamicPane.add(ok);
		addComponents();

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String str="class "+a1.getText()+"\n {\n public: //variables \n protected : //variables \n private: //variables\n };";
				Editor.area.append(str);
				removeComponents();
			}
		}
				);


	}
	private void removeComponents() {	  

		DynamicPane.removeAll();
		DynamicPane.repaint();  


	}
	private void addComponents()
	{
		DynamicPane.revalidate();
		DynamicPane.repaint();
	}
}




