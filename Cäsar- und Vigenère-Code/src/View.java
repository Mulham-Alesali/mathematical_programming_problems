import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class View extends JPanel implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = 1L;

	Model model;
	JButton btnclear = new JButton("Berechnen");
	JButton btnopenPlainText = new JButton("openPlainText");
	JButton btnopenCipherText = new JButton("openCipherText");
	
	JButton btnchiffrieren = new JButton("Chiffrieren");
	JButton btndechiffrieren = new JButton("Dechiffrieren");
	
	JTextArea klartext = new JTextArea(25,40);	
	JTextField txtkey = new JTextField("",15);
	JTextArea cipherText = new JTextArea(25,40);

	@SuppressWarnings("unused")
	private static File openFile(Frame f) {
		 
        String filename = File.separator+"txt";
        JFileChooser fileChooser = new JFileChooser(new File(filename));
 
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // only directories
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // only files 
        // pop up an "Open File" file chooser dialog
        fileChooser.showOpenDialog(f);

        return fileChooser.getSelectedFile();
 
    }
	
	
	
	public View(Model model)  {
		this.model = model;
		model.addPropertyChangeListener(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("klartext");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		klartext.setAlignmentX(LEFT_ALIGNMENT);
		box.add(klartext);
		
		
		add(box);
		
		
		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel lblkey = new JLabel("key");
		lblkey.setAlignmentX(TOP_ALIGNMENT);
		box2.add(lblkey);
		box2.add(Box.createVerticalStrut(5));
		box2.add(txtkey);
		
		box2.add(Box.createVerticalStrut(20));
		btnopenCipherText.addActionListener(this);
		btnopenCipherText.setAlignmentX(LEFT_ALIGNMENT);
		btnopenCipherText.setAlignmentY(TOP_ALIGNMENT);
		box2.add(btnopenCipherText);
		
		box2.add(Box.createVerticalStrut(20));
		btnopenPlainText.addActionListener(this);
		btnopenPlainText.setAlignmentX(LEFT_ALIGNMENT);
		btnopenPlainText.setAlignmentY(TOP_ALIGNMENT);
		box2.add(btnopenPlainText);
		
		box2.add(Box.createVerticalStrut(20));
		btnchiffrieren.addActionListener(this);
		btnchiffrieren.setAlignmentX(LEFT_ALIGNMENT);
		btnchiffrieren.setAlignmentY(TOP_ALIGNMENT);
		btnchiffrieren.setBounds(50, 50, 100, 100);
		box2.add(btnchiffrieren);
		
		box2.add(Box.createVerticalStrut(20));
		btndechiffrieren.addActionListener(this);
		btndechiffrieren.setAlignmentX(LEFT_ALIGNMENT);
		btndechiffrieren.setAlignmentY(TOP_ALIGNMENT);
		box2.add(btndechiffrieren);
		
		add(box2);
		
		
		Box box3 = Box.createVerticalBox();
		box3.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		box3.add(new JLabel("cipher Text"));
		box3.add(Box.createVerticalStrut(5));
		cipherText.setAlignmentX(LEFT_ALIGNMENT);
		box3.add(cipherText);
		box3.add(Box.createVerticalStrut(15));
		add(box3);
	}



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnchiffrieren){
			model.chiffrieren();
		}
		else if(e.getSource() == btnclear) {
			this.cipherText.setText("");
			this.klartext.setText("");
			
		}
		else if(e.getSource() == btndechiffrieren) {
			
			model.dechiffrieren();
		}
		else if(e.getSource() == btnopenCipherText) {
			model.file = openFile(model.getFrame());
			
			try {
				this.cipherText.setText(model.readFile(model.getFile()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			model.openCipherText();
		}
		else if(e.getSource() == btnopenPlainText) {
			
			model.file = openFile(model.getFrame());
			try {
				klartext.setText(model.readFile(model.getFile()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.openPlainText();
		}
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// The switch is not strictly necessary in this example as there is
		// only one property "ggt". Do it anyway to show the general pattern.
		String pn = e.getPropertyName();
		System.out.println(e.getPropertyName());
		switch(pn) {
		case Model.CHANGE:
			// process change of "ggt" property
			//g.setText(e.getNewValue()+"");
			break;
		
		default: 
			throw new IllegalArgumentException("Unknown property name: "+pn);
		}
	}
}



