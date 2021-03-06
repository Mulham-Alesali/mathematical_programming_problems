import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class Newton_View extends JPanel implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	JButton compute = new JButton("Berechnen");

	JTextField a = new JTextField("0", 10);
	JTextField n = new JTextField("0", 10);
	JTextField d = new JTextField("0", 10);
	JTextField result = new JTextField("", 20);

	Newton_Model model;

	
	public Newton_View(Newton_Model model) {
		this.model = model;
		
		model.addPropertyChangeListener(this);

		setBackground(Color.lightGray);

		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 100));
		JLabel label1 = new JLabel("  a");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		
		
		a.setAlignmentX(LEFT_ALIGNMENT);
		
		box.add(a);
		box.add(Box.createVerticalStrut(10));

		// box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  n"));
		n.setAlignmentX(LEFT_ALIGNMENT);
		box.add(n);
		box.add(Box.createVerticalStrut(10));

		JLabel label3 = new JLabel("  d");
		label3.setAlignmentY(TOP_ALIGNMENT);
		box.add(label3);
		d.setAlignmentX(LEFT_ALIGNMENT);

		box.add(d);
		box.add(Box.createVerticalStrut(10));
		add(box);

		Box box2 = Box.createVerticalBox();
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		compute.setAlignmentY(TOP_ALIGNMENT);

		box2.add(compute);
		box2.add(Box.createVerticalStrut(10));

		JLabel label4 = new JLabel("Ergebnis: ");
		label4.setAlignmentY(TOP_ALIGNMENT);
		label4.setAlignmentX(LEFT_ALIGNMENT);

		box2.add(label4);
		result.setAlignmentX(LEFT_ALIGNMENT);
		result.setEditable(false);
		box2.add(result);

		add(box2);

	}

	private void readInput() {
		try {
			if (a.getText().equals("") || d.getText().equals("") || n.getText().equals("")) {
				throw new Exceptions("Sie haben nichts eingegeben!");
			}
			model.setA(Double.valueOf(a.getText()));
			model.setN(Double.valueOf(n.getText()));
			model.setD(Integer.valueOf(d.getText()));
			
			model.process();

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, nfe.getMessage() != null? nfe.getMessage():"Eingabe fehler ist passiert", "Eingabe fehler", JOptionPane.ERROR_MESSAGE);
		} catch (Exceptions e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute)
			readInput();
	}

	public void propertyChange(PropertyChangeEvent e) {

		String pn = e.getPropertyName();
		switch (pn) {
		case Newton_Model.RESULT_CHANGE:
			// process change of "ggt" property
			result.setText(e.getNewValue() + "");
			break;
		default:
			throw new IllegalArgumentException("Unknown property name: " + pn);
		}
	}
}