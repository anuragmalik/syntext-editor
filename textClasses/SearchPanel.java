package textClasses;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;


public class SearchPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public JPanel createSearchBox()
	{
		JPanel searchpane = new JPanel();
		GridBagLayout grid = new GridBagLayout();
		searchpane.setLayout(grid);
		getContentPane().add(searchpane, BorderLayout.CENTER);

		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx=1;
		gbc.weighty=0.25;
		gbc.gridwidth=4;
		final JTextArea area=new JTextArea("Search");
		area.setTransferHandler(new TransferHandler("text"));
		searchpane.add(area,gbc);
		gbc.gridwidth=1;

		ImageIcon Y_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				(Editor.class.getResource("/META-INF/images/small-icons/yahoo.png"))));
		JButton y_search=new JButton();
		y_search.setMargin(new Insets(0,0,0,0));
		y_search.setBorder(null);
		y_search.setBorderPainted(false);

		y_search.setIcon(Y_icon);
		y_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				OpenBrowser.openURL("http://search.yahoo.com/search?p="+area.getText().trim());
			}
		});

		ImageIcon G_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				(Editor.class.getResource("/META-INF/images/small-icons/google.png"))));
		JButton g_search=new JButton();
		g_search.setMargin(new Insets(0,0,0,0));
		g_search.setIcon(G_icon);
		g_search.setBorder(null);
		g_search.setBorderPainted(false);

		g_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				OpenBrowser.openURL("http://www.google.co.in/#hl=en&output=search&sclient=psy-ab&q="+area.getText().trim());
			}
		});

		ImageIcon W_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				(Editor.class.getResource("/META-INF/images/small-icons/wiki.png"))));
		JButton w_search=new JButton();
		w_search.setIcon(W_icon);	
		w_search.setBorder(null);
		w_search.setBorderPainted(false);
		w_search.setMargin(new Insets(0,0,0,0));
		w_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				OpenBrowser.openURL("http://en.wikipedia.org/wiki/"+area.getText().trim());
			}
		});

		ImageIcon O_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				(Editor.class.getResource("/META-INF/images/small-icons/oxford.png"))));
		JButton o_search=new JButton();
		o_search.setIcon(O_icon);
		o_search.setBorder(null);
		o_search.setBorderPainted(false);
		o_search.setMargin(new Insets(0,0,0,0));
		o_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				OpenBrowser.openURL("http://oxforddictionaries.com/definition/english/"+area.getText().trim()+"?q="+area.getText().trim());
			}
		});

		gbc.gridy=1;
		gbc.weightx=0.25;
		gbc.weighty=0.5;
		searchpane.add(y_search,gbc);
		gbc.gridx=1;
		searchpane.add(g_search,gbc);
		gbc.gridx=2;
		searchpane.add(w_search,gbc);
		gbc.gridx=3;
		searchpane.add(o_search,gbc);

		return searchpane;

	}

}
