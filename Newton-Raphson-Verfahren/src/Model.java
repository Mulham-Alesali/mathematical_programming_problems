import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model {
		
	int a, b, ggt;	
	static final String GGT_CHANGE = "ggt";
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getGgt() {
		return ggt;
	}
	
	public Model(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public Model() {
		super();
		a = 0;
		b = 0;
	}

	public void ggt() {
		int oldggt = ggt;
		// TODO: hier den echten ggT berechnen
		ggt = a + b;
		// notify listeners of change
		pcs.firePropertyChange(GGT_CHANGE, oldggt, ggt);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}

}
