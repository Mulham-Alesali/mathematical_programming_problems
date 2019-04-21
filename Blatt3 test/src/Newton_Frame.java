import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Newton_Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	public Newton_Frame() {
		super("Newton-Raphson-Verfahren");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Newton_Model model = new Newton_Model();
		Newton_View view = new Newton_View(model);
		getContentPane().add(view);
		setSize(800, 500);
		pack();
	}

	public static void main(String[] args) {
		Newton_Frame ef = new Newton_Frame();
		ef.setVisible(true);
		

	}
}