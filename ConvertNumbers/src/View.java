import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class View extends JPanel implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	Model model;
	
	JButton compute = new JButton("Umwandeln");

	JTextField txtN1 = new JTextField("",10);
	JTextField txtN2 = new JTextField("",10);
	
	JTextField txtBasis1 = new JTextField("",10);
	JTextField txtBasis2 = new JTextField("",10);

	public View(Model model)  {
		this.model = model;
		model.addPropertyChangeListener(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("  a");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		txtN1.setAlignmentX(LEFT_ALIGNMENT);
		box.add(txtN1);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  b"));
		box.add(Box.createVerticalStrut(5));
		txtN2.setAlignmentX(LEFT_ALIGNMENT);
		box.add(txtN1);
		box.add(Box.createVerticalStrut(15));
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		box.add(compute);
		add(box);
		
		JLabel label3 = new JLabel("  a");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label3);
		box.add(Box.createVerticalStrut(5));
		txtN1.setAlignmentX(LEFT_ALIGNMENT);
		box.add(txtBasis1);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  b"));
		box.add(Box.createVerticalStrut(5));
		txtN2.setAlignmentX(LEFT_ALIGNMENT);
		box.add(txtBasis2);
		box.add(Box.createVerticalStrut(15));
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		add(box2);		

	}

	private void readInput(){
		try {
			model.setA(Integer.valueOf(txtN1.getText()));
			model.setB(Integer.valueOf(txtN2.getText()));
			model.ggt();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,
					"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
		} 		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute) readInput();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// The switch is not strictly necessary in this example as there is
		// only one property "ggt". Do it anyway to show the general pattern.
		String pn = e.getPropertyName();
		switch(pn) {
		case Model.GGT_CHANGE:
			// process change of "ggt" property
			txtN2.setText(e.getNewValue()+"");
			break;
		default: 
			throw new IllegalArgumentException("Unknown property name: "+pn);
		}
	}
}



