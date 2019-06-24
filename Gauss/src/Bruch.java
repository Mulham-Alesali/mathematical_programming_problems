/**
 *	Bruch class statt double
 * @author Mulham Alesali, Nawid Shadab, Mahmoud Abdalrahman
 *
 */


public class Bruch {

	private long nenner;
	private long zahler;
	

	public long getNenner() {
		return nenner;
	}

	public void setNenner(long nenner) {
		if(nenner == 0) throw new ArithmeticException();
		this.nenner = nenner;
	}

	public long getZahler() {
		return zahler;
	}

	public void setZahler(long zahler) {
		this.zahler = zahler;
	}

	public void kürzen() {
		long ggt = ggt(this.zahler,this.nenner);
		this.nenner /= ggt;
		this.zahler /= ggt;
	}
	
    public static long ggt(long a, long b) {
        // Hier versuche ich Arbeitsaufwand (Rechnenzeit) zu sparen in dem ich
        // mir die kleinste Zahl suche.
        long h = (a > b) ? b : a;
        // Der GGT wird hier berechnet.
        for (long i = h; i > 1; i--) {
            if ((a % i) == 0 && (b % i) == 0) {
                return i;
            }
        }
        // teilerfremde Zahlen haben immer den Teiler 1
        return 1;
    }
    
    private static long lcm(long a, long b)
    {
        return a * (b / ggt(a, b));
    }

    @SuppressWarnings("unused")
	private static long lcm(long[] input)
    {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

	
	
	public Bruch() {
		this.setNenner(1);
		this.setZahler(0);
	}
	
	public Bruch(long zahler, long nenner) {
		this.setNenner(nenner);
		this.setZahler(zahler);
	}
	
	public Bruch addieren(Bruch b) {
		Bruch result = new Bruch();
		long lcm = lcm(this.nenner,b.nenner);
		result.setNenner(lcm);
		result.setZahler(this.zahler * (lcm / this.nenner) + b.zahler * (lcm / b.nenner));
		return result;
		
	}
	
	public Bruch subtrahieren(Bruch b) throws CloneNotSupportedException {
		Bruch b2 = (Bruch)b.clone();
		b2.setZahler(b.getZahler() * -1);
		return addieren(b2);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Bruch(this.zahler,this.nenner);
	}

	public Bruch multiplizieren(Bruch b) {
	 return new Bruch(this.zahler * b.zahler,this.nenner * b.nenner);
	}
	
	public Bruch dividieren(Bruch b) {
		return new Bruch(this.zahler * b.nenner, this.nenner * b.zahler);
	}
	
	public static double toDouble(Bruch b) {
		return (double)b.zahler/b.nenner;
	}
	
	public static Bruch toBruch(double d) {
			long nenner = 1;
			double zahler = d;
			
			while(zahler % 1 > 0) {
				nenner *= 10;
				zahler *= 10;
			}
		return new Bruch((long) zahler, nenner);
	}
	
	public String toString() {
		return this.getZahler() + "/" + this.getNenner();
	}

}

