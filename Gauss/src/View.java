/**
 *	View class
 * @author Mulham Alesali, Nawid Shadab, Mahmoud Abdalrahman
 *
 */


import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.*;

import java.util.logging.Logger;


public class View extends JPanel implements ActionListener, PropertyChangeListener{
	 private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static final long serialVersionUID = 1L;
	Model model;
	
	/*butttons*/
	JButton btnopenfile = new JButton("Open File");
	JButton btnberechnen = new JButton("Berechnen");
	JTextArea areamartix = new JTextArea(20,20);
	JTextArea areamatrix2 = new JTextArea(20,20);
	
	JScrollPane scrollpane = new JScrollPane(areamartix);
	JScrollPane scrollpane2 = new JScrollPane(areamatrix2);
	
	
	private static File openFile(Frame f) {
		 
        String filename = File.separator + "txt";
        JFileChooser fileChooser = new JFileChooser(new File(filename));
 
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // only directories
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // only files 
        // pop up an "Open File" file chooser dialog
        fileChooser.showOpenDialog(f);

        return fileChooser.getSelectedFile();
        
    }
	
	
	public View(Model model) {
		this.model = model;
		this.model = model;
		model.addPropertyChangeListener(this);
		setBackground(Color.lightGray);
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("Matrix1");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		areamartix.setAlignmentX(LEFT_ALIGNMENT);
		areamartix.setLineWrap(true);
	
		
		scrollpane.setPreferredSize(new Dimension(400,400));
		box.add(scrollpane, areamartix);
		add(box);
		
		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		
		
		box2.add(Box.createVerticalStrut(20));
		btnopenfile.addActionListener(this);
		btnopenfile.setAlignmentX(LEFT_ALIGNMENT);
		btnopenfile.setAlignmentY(TOP_ALIGNMENT);
		box2.add(btnopenfile);
		
		box2.add(Box.createVerticalStrut(20));
		btnberechnen.addActionListener(this);
		btnberechnen.setAlignmentX(LEFT_ALIGNMENT);
		btnberechnen.setAlignmentY(TOP_ALIGNMENT);
		box2.add(btnberechnen);
		

		add(box2);
		
		
		Box box3 = Box.createVerticalBox();
		box3.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		box3.add(new JLabel("matrix ^ - 1"));
		box3.add(Box.createVerticalStrut(5));
		areamatrix2.setAlignmentX(LEFT_ALIGNMENT);
		areamatrix2.setLineWrap(true);
		scrollpane2.setPreferredSize(new Dimension(400,400));
		box3.add(scrollpane2, areamatrix2);
		box3.add(Box.createVerticalStrut(15));
		add(box3);	
		
	}
	@Override
	public void actionPerformed(ActionEvent e)  {		

		
		if (e.getSource() == btnopenfile){

			
			model.setFile(openFile(model.getFrame()));
			try {
				this.areamartix.setText(model.readFile(model.getFile()));
				
			} 
			catch (Exception e1) {
		        JOptionPane.showMessageDialog(null ,e1.getMessage(), "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == btnberechnen) {
			try {
				
				readInput();
				double[][] result =	model.calculate(Model.stringToMatrix(model.getMatrix()));
				this.areamatrix2.setText(Model.matrixToString(result));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				this.areamatrix2.setText(e1.getMessage());
				e1.printStackTrace();
			}
		}
				
		
	}
	
	public void readInput() {

		LOGGER.info(this.areamartix.getText());
		
		model.setMatrix(this.areamartix.getText());
	//	model.setMatrix2(this.areamatrix2.getText());
	}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		
				
	}



}
