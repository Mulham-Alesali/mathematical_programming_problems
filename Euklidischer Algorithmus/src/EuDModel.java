import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EuDModel {
		
	int a, b, ggt;	
/*
	
	int rest = 0;
	int q = 0;
	int uHelp = 1;
	int vHelp = 0;
	
	int s = 0;
	int t = 1;
	
	int sLast;
	int tLast;
	
 */
	
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
	
	public EuDModel(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public EuDModel() {
		super();
		a = 0;
		b = 0;
	}

	public void ggt() throws NumberFormatException,Exception {
		int oldggt = ggt;
		
		// TODO: hier den echten ggT berechnen	
		// Done
		if(a < 0 || b < 0)
			throw new NumberFormatException("Negative number are not allowed");
	
		if(a == 0 && b == 0)
			 throw new Exception("ggt(0,0) is not defined");
			
		if(a == 0) 
			ggt = b;
		else if(b == 0)
			ggt = a;
		else {

			/*
			while (b>0) {
				
				q = a/b;
		        rest = a-(q*b);
		        
		        a = b;
		        a = rest;
		        
		        
		        rest = uHelp-(q*s);
		        uHelp = s;
		        s = rest;
		        
		        rest = vHelp-(q*t);
		        vHelp = t;
		        t = rest;
		        
		        System.out.println("m= " +a +" n= " +b +" q= " +q +" s= " +s +" t= " +t);
		        
		        sLast = s;
		        tLast = t;
		        }
			 */
		   while (b != 0) {
			     if (a > b) {
			       a = a - b;
			     } else {
			       b = b - a;
			     }
			     
			   }			   
		ggt = a;
			}
		// notify listeners of change
		pcs.firePropertyChange(GGT_CHANGE, oldggt, ggt);
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}

}
