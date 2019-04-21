import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class ZD_View extends JPanel implements ActionListener, PropertyChangeListener {
	private static final long serialVersionUID = 1L;

	String[] numbers = {"2 Binary", "3", "4", "5", 
			"6", "7", "8 Octal", "9", 
			"10 Decimal", "11", "12", "13",
			"14", "15", "16 Hexadecimal"};
	
	JTextField number_input = new JTextField("",10);
	JTextField number_output = new JTextField("",10);
	String selected_item = "";
	JComboBox numbersList1;
	JComboBox numbersList2;
	JButton compute = new JButton("Berechnen");

	ZD_Model model;

	public ZD_View(ZD_Model model)
	{
		this.model = model;
		model.addPropertyChangeListener(this);
		
		numbersList1 = new JComboBox(numbers);
		numbersList1.setSelectedItem(0);
		numbersList1.addActionListener(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("  Number Input");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		number_input.setAlignmentX(LEFT_ALIGNMENT);
		box.add(number_input);
		box.add(Box.createVerticalStrut(20));
		
		
		box.add(new JLabel("  Base Input"));
		box.add(Box.createVerticalStrut(5));
		box.add(numbersList1);
		numbersList1.setAlignmentX(LEFT_ALIGNMENT);
		
		
		add(box);
		
		numbersList2 = new JComboBox(numbers);
		Box box2 = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label2 = new JLabel("  Number Output");
		label2.setAlignmentY(TOP_ALIGNMENT);
		box2.add(label2);
		box2.add(Box.createVerticalStrut(5));
		number_output.setAlignmentX(LEFT_ALIGNMENT);
		number_output.setEditable(false);
		box2.add(number_output);
		box2.add(Box.createVerticalStrut(20));
		box2.add(new JLabel("  Base Output"));
		box2.add(Box.createVerticalStrut(5));
		box2.add(numbersList2);
		numbersList2.setAlignmentX(LEFT_ALIGNMENT);
		
		add(box2);
		
		Box box3 = Box.createHorizontalBox();
		box3.add(Box.createHorizontalStrut(15));
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		box3.add(compute);
		add(box3);
		
	}
	private void readInput(){
		try {
			if(numbersList1.getSelectedIndex() >= 8 && numbersList1.getSelectedIndex() <= 14)
			{
				model.setBase_input(Integer.valueOf(numbersList1.getSelectedItem().toString().substring(0,2)));
			}
			else
			{

				model.setBase_input(Integer.valueOf(numbersList1.getSelectedItem().toString().charAt(0) - '0'));
			}
			if(numbersList2.getSelectedIndex() >= 8 && numbersList2.getSelectedIndex() <= 14) {
				model.setBase_output(Integer.valueOf(numbersList2.getSelectedItem().toString().substring(0,2)));
			}
			else
			{
				model.setBase_output(Integer.valueOf(numbersList2.getSelectedItem().toString().charAt(0) - '0'));

			}
			
			if(number_input.getText().charAt(0) == '-')
			{
				throw new NumberFormatException();
			}
			model.setNumber_input(number_input.getText());
			
			model.process();
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,
					"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
		}
		catch(ArithmeticException e)
		{
			JOptionPane.showMessageDialog(this,
					"Die eingegebene Nummer verursacht Integerueberlauf!","Eingabefehler",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// TODO Auto-generated method stub
		
		String pn = e.getPropertyName();
		switch(pn) {
		case ZD_Model.OUTPUT_CHANGE:
		// process change of "ggt" property
			number_output.setText(e.getNewValue()+"");
			break;
		default: 
			throw new IllegalArgumentException("Unknown property name: "+pn);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute) readInput();
		
	}
	
}