import java.awt.event.*;
import javax.swing.*;



public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Frame(){
		super("C�sar- und Vigen�re-Code");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		Model model = new Model(this);
		View view = new View(model);
		getContentPane().add(view);
		
		
		pack();
	}

	public static void main(String[] args)  {
		Frame f = new Frame();
		f.setVisible(true);
		
	}

}
 	