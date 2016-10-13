package textClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;

public class Calculator extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	final int MAX_INPUT_LENGTH = 20;
	final int INPUT_MODE = 0;
	final int RESULT_MODE = 1;
	final int ERROR_MODE = 2;
	int displayMode;

	boolean clearOnNextDigit, percent;
	double lastNumber;
	String lastOperator;

	private JTextField jlbOutput;
	private JButton jbnButtons[];
	private JPanel jplMaster, jplBackSpace;
	public String all_cal;
	double x=0,y=0,z=0;
	Editor obj;
	Font f12 = new Font("Times New Roman", 2, 14);
	Font f121 = new Font("Times New Roman", 1, 12);

	public JPanel createCalci() 
	{	JPanel mainPanel=new JPanel();
	GridBagLayout grid=new GridBagLayout();
	mainPanel.setLayout(grid);
	getContentPane().add(mainPanel, BorderLayout.CENTER);

	GridBagConstraints gbc=new GridBagConstraints();
	gbc.gridx=0;
	gbc.gridy=0;
	gbc.weighty=0.5;
	gbc.weightx=1;
	gbc.fill=GridBagConstraints.BOTH;

	setBackground(Color.gray);
	jlbOutput = new JTextField("0");
	jlbOutput.setHorizontalAlignment(JTextField.RIGHT);
	jlbOutput.setBackground(Color.WHITE);
	jlbOutput.setOpaque(true);
	Font BIGGER_FONT = new Font("monspaced", Font.PLAIN, 20);
	jlbOutput.setFont(BIGGER_FONT);
	mainPanel.add(jlbOutput, gbc);
	gbc.weighty=0.75;
	gbc.gridy=1;
	jbnButtons = new JButton[27];
	jplMaster = new JPanel();
	JPanel jplButtons = new JPanel();
	for (int i=0; i<=9; i++)
	{	jbnButtons[i] = new JButton(String.valueOf(i)); }
	jbnButtons[10] = new JButton("+/-");
	jbnButtons[11] = new JButton(".");
	jbnButtons[12] = new JButton("=");
	jbnButtons[13] = new JButton("/");
	jbnButtons[14] = new JButton("*");
	jbnButtons[15] = new JButton("-");
	jbnButtons[16] = new JButton("+");
	jbnButtons[17] = new JButton("sqrt");
	jbnButtons[18] = new JButton("1/x");
	jbnButtons[19] = new JButton("%");

	jplBackSpace = new JPanel();
	jplBackSpace.setLayout(new GridLayout(1,8, 2, 1));
	jbnButtons[23]=new JButton("Insert");
	jplBackSpace.add(jbnButtons[23]);
	jbnButtons[24]=new JButton("X");
	jplBackSpace.add(jbnButtons[24]);
	jbnButtons[25]=new JButton("Y");
	jplBackSpace.add(jbnButtons[25]);
	jbnButtons[26]=new JButton("Z");
	jplBackSpace.add(jbnButtons[26]);
	jbnButtons[20] = new JButton("Del");
	jplBackSpace.add(jbnButtons[20]);
	jbnButtons[21] = new JButton(" CE ");
	jbnButtons[22] = new JButton("C");
	jplBackSpace.add(jbnButtons[21]);
	jplBackSpace.add(jbnButtons[22]);

	for (int i=0; i<jbnButtons.length; i++)	{			
		jbnButtons[i].setFont(f12);
		jbnButtons[i].setBorder(null);
		jbnButtons[i].setBorderPainted(false);
		if(i>19)
		{jbnButtons[i].setMargin(new Insets(0,0,0,0));}
		else 
		{jbnButtons[i].setMargin(new Insets(1,0,1,0));}
		if (i<10)
			jbnButtons[i].setForeground(Color.blue);
		else
			jbnButtons[i].setForeground(Color.red);
	}
	jplButtons.setLayout(new GridLayout(4, 5, 2, 2));
	for(int i=7; i<=9; i++)		{	jplButtons.add(jbnButtons[i]); }
	jplButtons.add(jbnButtons[13]);
	jplButtons.add(jbnButtons[17]);
	for(int i=4; i<=6; i++)
	{	jplButtons.add(jbnButtons[i]);	}
	jplButtons.add(jbnButtons[14]);
	jplButtons.add(jbnButtons[18]);
	for( int i=1; i<=3; i++)
	{	jplButtons.add(jbnButtons[i]);	}
	jplButtons.add(jbnButtons[15]);
	jplButtons.add(jbnButtons[19]);
	jplButtons.add(jbnButtons[0]);
	jplButtons.add(jbnButtons[10]);
	jplButtons.add(jbnButtons[11]);
	jplButtons.add(jbnButtons[16]);
	jplButtons.add(jbnButtons[12]);

	jplMaster.setLayout(new BorderLayout());
	jplMaster.add(jplBackSpace, BorderLayout.CENTER);
	jplMaster.add(jplButtons, BorderLayout.SOUTH);
	mainPanel.add(jplMaster, gbc);

	requestFocus();
	for (int i=0; i<jbnButtons.length; i++) 
	{	jbnButtons[i].addActionListener(this);	}
	clearAll();
	return mainPanel;
	}

	public void actionPerformed(ActionEvent e){
		double result = 0;
		for (int i=0; i<jbnButtons.length; i++)
		{
			if(e.getSource() == jbnButtons[i])
			{
				switch(i)
				{
				case 10: processSignChange();	break;
				case 11: addDecimalPoint();	break;
				case 12: updateString("=");
				processEquals();
				updateString("\n");
				break;
				case 13: updateString("/");
				processOperator("/");
				break;
				case 14: updateString("*");
				processOperator("*");
				break;
				case 15: updateString("-");
				processOperator("-");
				break;
				case 16: updateString("+");
				processOperator("+");
				break;
				case 17: all_cal+="sqrt"+getNumberInDisplay()+"= ";
				if (displayMode != ERROR_MODE)
				{
					try
					{
						if (getDisplayString().indexOf("-") == 0)
							displayError("Invalid input for function!");
						result = Math.sqrt(getNumberInDisplay());
						displayResult(result);		
					}
					catch(Exception ex)
					{
						displayError("Invalid input for function!");
						displayMode = ERROR_MODE;
					}	}
				updateString(";");
				break; 

				case 18:	// 1/x
					all_cal+="1/"+getNumberInDisplay()+"= ";
					if (displayMode != ERROR_MODE){
						try
						{
							if (getNumberInDisplay() == 0)
								displayError("Cannot divide by zero!");

							result = 1 / getNumberInDisplay();
							displayResult(result);
						}

						catch(Exception ex)	{
							displayError("Cannot divide by zero!");
							displayMode = ERROR_MODE;
						}
					}

					updateString(";");
					break;

				case 19:	// %
					if (displayMode != ERROR_MODE){
						try	{
							result = getNumberInDisplay() / 100;
							displayResult(result);
						}

						catch(Exception ex)	{
							displayError("Invalid input for function!");
							displayMode = ERROR_MODE;
						}
					}
					break;

				case 20:	// backspaced
					if (displayMode != ERROR_MODE){
						setDisplayString(getDisplayString().substring(0,
								getDisplayString().length() - 1));

						if (getDisplayString().length() < 1)
							setDisplayString("0");
					}
					break;

				case 21:	// CE
					clearExisting();
					break;

				case 22:	// C
					clearAll();
					break;

				case 23: //insert
					Editor.area.append(all_cal);
					all_cal="";
					break;

				case 24: //store in X
					if(x==0)
					{
						x=getNumberInDisplay();
						all_cal+=("x="+x+"\n");
					}
					else
					{
						setDisplayString(""+x);
						all_cal+=("x("+x+")");
					}
					break;

				case 25: //store in Y
					if(y==0)
					{
						y=getNumberInDisplay();
						all_cal+=("y="+y+"\n");
					}
					else
					{
						setDisplayString(""+y);
						all_cal+=("y("+y+")");
					}
					break;

				case 26: //store in Z
					if(z==0)
					{
						z=getNumberInDisplay();
						all_cal+=("z="+z+"\n");
					}
					else
					{
						setDisplayString(""+z);
						all_cal+=("z("+z+")");
					}
					break;

				default:
					//all_cal+=i;
					addDigitToDisplay(i);
					break;

				}
			}
		}
	}

	void setDisplayString(String s){
		jlbOutput.setText(s);
	}

	String getDisplayString (){
		return jlbOutput.getText();
	}

	void addDigitToDisplay(int digit){
		if (clearOnNextDigit)
			setDisplayString("");

		String inputString = getDisplayString();

		if (inputString.indexOf("0") == 0){
			inputString = inputString.substring(1);
		}

		if ((!inputString.equals("0") || digit > 0)  && inputString.length() < MAX_INPUT_LENGTH){
			setDisplayString(inputString + digit);
		}
		displayMode = INPUT_MODE;
		clearOnNextDigit = false;
	}

	void addDecimalPoint(){
		displayMode = INPUT_MODE;
		if (clearOnNextDigit)
			setDisplayString("");
		String inputString = getDisplayString();
		if (inputString.indexOf(".") < 0)
			setDisplayString(new String(inputString + "."));
	}

	void processSignChange(){
		if (displayMode == INPUT_MODE)
		{
			String input = getDisplayString();
			if (input.length() > 0 && !input.equals("0"))
			{
				if (input.indexOf("-") == 0)
					setDisplayString(input.substring(1));
				else
					setDisplayString("-" + input);
			}
		}
		else if (displayMode == RESULT_MODE)
		{
			double numberInDisplay = getNumberInDisplay();
			if (numberInDisplay != 0)
				displayResult(-numberInDisplay);
		}
	}

	void updateString(String op)
	{	all_cal+=getDisplayString()+" "+op;	}

	void clearAll()	{
		setDisplayString("0");
		lastOperator = "0";
		lastNumber = 0;
		displayMode = INPUT_MODE;
		clearOnNextDigit = true;

		all_cal="";
		x=0;
		y=0;
		z=0;
	}

	void clearExisting(){
		setDisplayString("0");
		clearOnNextDigit = true;
		displayMode = INPUT_MODE;
	}

	double getNumberInDisplay()	{
		String input = jlbOutput.getText();
		return Double.parseDouble(input);
	}

	void processOperator(String op) {
		if (displayMode != ERROR_MODE)
		{
			double numberInDisplay = getNumberInDisplay();
			if (!lastOperator.equals("0"))	
			{
				try
				{
					double result = processLastOperator();
					displayResult(result);
					lastNumber = result;
				}
				catch (DivideByZeroException e)
				{
				}
			}
			else
			{	lastNumber = numberInDisplay;	}
			clearOnNextDigit = true;
			lastOperator = op;
		}
	}

	void processEquals(){
		double result = 0;
		if (displayMode != ERROR_MODE){
			try			
			{	result = processLastOperator();
			displayResult(result);	}
			catch (DivideByZeroException e)	{
				displayError("Cannot divide by zero!");
			}
			lastOperator = "0";
		}
	}

	double processLastOperator() throws DivideByZeroException {
		double result = 0;
		double numberInDisplay = getNumberInDisplay();
		//updateString("");
		if (lastOperator.equals("/"))
		{	if (numberInDisplay == 0)
			throw (new DivideByZeroException());
		result = lastNumber / numberInDisplay;
		}
		if (lastOperator.equals("*"))
			result = lastNumber * numberInDisplay;
		if (lastOperator.equals("-"))
			result = lastNumber - numberInDisplay;
		if (lastOperator.equals("+"))
			result = lastNumber + numberInDisplay;
		return result;
	}

	void displayResult(double result){
		setDisplayString(Double.toString(result));
		lastNumber = result;
		displayMode = RESULT_MODE;
		clearOnNextDigit = true;
	}

	void displayError(String errorMessage){
		setDisplayString(errorMessage);
		lastNumber = 0;
		displayMode = ERROR_MODE;
		clearOnNextDigit = true;
	}

	class DivideByZeroException extends Exception{
		public DivideByZeroException()
		{	super();	}
		public DivideByZeroException(String s)
		{	super(s);
		}}	}
