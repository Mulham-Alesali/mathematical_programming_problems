import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Newton_Model {
	double a, n, result;
	int d;
	double x;
	double xold;

	static final String RESULT_CHANGE = "res";

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public Newton_Model(double a, double n, int d) {
		super();
		this.a = a;
		this.n = n;
		this.d = d;
	}

	public Newton_Model() {

	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
	}

	public double getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public void process() throws Exceptions {
		if (a < 0)
			throw new Exceptions("a soll Positiv sein");
		if (n <= 0)
			throw new Exceptions("n soll grosser als null sein");
		if (d < 0)
			throw new Exceptions("d soll positiv sein");

		x = a / 2;
		xold = 0;

		double nullen = Math.pow(10, d);

		double temp = 0;
		double counter = 0;
		while ((Math.abs(x - xold) >= 1.0 / (nullen + 1)) && x != temp) {
			temp = xold;
			xold = x;
			x = f2(x, n, a);

			// folgendes Code kann gel�scht werden, der Ziel davon ist nur Operationen die
			// sehr lange dauern zu vermeiden
			counter++;
			if (counter > 1000)
				break;
		}

		result = round(x, d);
		pcs.firePropertyChange(RESULT_CHANGE, 0, result);

	}

	private static double round(double value, int places) throws Exceptions {

		if (places < 0)
			throw new Exceptions("illegal Argument");

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.FLOOR);
		return bd.doubleValue();

	}

	// das ist die Funktion aber die habe ich in andere Form umgewandelt, um go�ere
	// Werte rechnen zu k�nnen
	// f(x) = x ^ n - a
	// f'(x) = n * x ^ (n - 1)
	//
	private static double f2(double x, double n, double a) {
		double tem = Math.pow(x, n - 1);
		double tem2 = 0;
		if (!Double.isInfinite(tem)) {
			tem2 = a / (n * tem);
		}
		return x - (x / n - tem2);

	}

	public void addPropertyChangeListener(Newton_View newton_View) {
		pcs.addPropertyChangeListener(newton_View);

	}

}