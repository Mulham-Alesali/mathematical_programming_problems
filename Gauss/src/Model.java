/**
 *	calculating the matrix inverse using Gauss algorithm
 * @author Mulham Alesali, Nawid Shadab, Mahmoud Abdalrahman
 *
 */


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;





public class Model {
	
	Frame frame;
	String matrix;
	String matrix2;
	
	File file;

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public Model(Frame frame) {
		super();
		this.frame = frame;
		
	}
	
	public Frame getFrame() {
		return frame;
	}
	
	public void setFrame(Frame frame) {
		
		this.frame = frame;
	}
	
	public void setMatrix(String text) {

		this.matrix = text;
	}
	public void setMatrix2(String text) {
		this.matrix2 = text;
	}
	public String getMatrix() throws Exception {
		

		return matrix;
	}
	public String getMatrix2() {
		return matrix2;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
		
	}
	
	
	   /**
	   * reading a text file
	   * @param file the file to read
	   * @return String the text file as a string.
	   */
	   
	public String readFile(File file) throws IOException, Exception
	{
	    String content = null;
	
	    FileReader reader = null;
	    if(file != null)
	    try {
	    	
	    	if(!file.exists())
	    	{
	    		throw new Exception("File does not exist!");
	    	}
	    	
	        reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        content = new String(chars);
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if(reader != null){
	            reader.close();
	        }
	    }
	    if(content == null)
	    {
	    	return content;
	    }
	    return content;
	}
	
	
	   /**
	   * calculate the inverse of a matrix
	   * @param matrix of numbers
	   * @return the inverse of the matrix.
	   */
	public double[][] calculate (double[][] c) throws Exception {
			if(det(c.clone()) == 0) throw new NichtInvertierbarException("nicht invertierbar");
		Bruch [] [] m = new Bruch [c.length] [2*c.length] ;
		
		
		for (int i = 0; i<c.length; i++) {
			for (int j = 0; j<c.length * 2; j++) {
				if(j >= c.length)
					m[i][j] = new Bruch(0,1);
				else
				m [i][j] = Bruch.toBruch(c [i][j]);
			}
			m [i][c.length+i] = new Bruch(1,1); 
		}
		
		for (int i = 0; i<m.length; i++) {
			Bruch p= m[i][i];
			for (int j = 0; j<m[i].length; j++)	{
			//	this.LOGGER.info(i + " " + j);
				m[i][j] = m[i][j].dividieren(p);
				
			} 
			for (int k = i+1; k<m.length; k++)  {
				p= m[k][i];
				for (int j = 0; j<m[i].length; j++) {
					m[k][j] = m[k][j].subtrahieren(
							p.multiplizieren(m[i][j]));
				
			}
		} 
	}
		
		for (int i = m.length-1; i>0; i--) {
		
			for (int k = i-1; k>=0; k--)  {
				Bruch p= m[k][i];
				for (int j = 0; j<m[i].length; j++) {
					m[k][j]= m[k][j].subtrahieren(p.multiplizieren(m[i][j]));
				
			}
		} 
	}

			double[][] result = new double[c.length][c.length];
			
			for(int i = 0; i < result.length; i ++)
				for(int j = 0; j < result.length;j++) {
					result[i][j] = Bruch.toDouble(m[i ][j+ c.length]);
				}
			
		return result;
	}
	
	
	public static double det(double[][] A){
	    int n =  A.length;
	    
	    if(n==1)
    		return A[0][0];
	   
	    if(n==2)
    		return A[0][0]*A[1][1]-A[1][0]*A[0][1];
    		
	    if(n==3){
	    	return 	A[0][0]*(A[1][1]*A[2][2]-A[1][2]*A[2][1]) -
					A[0][1]*(A[1][0]*A[2][2]-A[1][2]*A[2][0]) +
					A[0][2]*(A[1][0]*A[2][1]-A[1][1]*A[2][0]) ;
		}else{
	    	return detLR(A);
		}
	}


	/**
	 * Returns the determinant of a matrix, computed by a LR-decomposition with pivot. For matrices 3-by-3 or smaller,
	 * the method <code>det()</code> is faster.
	 * <p>
	 * The determinant is only defined on a square matrix. But here is no dimension check. If the number of columns is bigger than the 
	 * number of lines of the second, the methods returns the determinant of a truncated matrix.
	 * In the opposite case, an <code>IndexOutOfBoundsException</code> will be thrown.
	 * 
	 * @param A the matrix
	 * @return its determinant.
	 * 
	 */
	public static double detLR(double[][] A){
		// based on the chapter IV of the course "Analyse numerique" taugth by Pr Ernst Hairer at the University of Geneva
		
		int n = A.length;
		double[][] R = new double[n][n];
		int nbOfSwaps = 1;
		// although we perform a LU decomposition with pivot, we don't need to store L and P
		
		// R = copy of A (the matrix on which we make the operations, so we leave A unchanged)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				R[i][j] = A[i][j];
			}
		}
		// Triangulation of R
		for(int i=0; i<n-1; i++){
			// Find pivot and swap lines
			double a = Math.abs(R[i][i]); 
			for (int j = i+1; j < n; j++) {
				if(Math.abs(R[j][i]) > a){
					a = Math.abs(R[j][i]);
					double[] tempLine = R[j]; R[j] = R[i] ; R[i] = tempLine;
					nbOfSwaps = -nbOfSwaps;
				}
			}
			// Elimination of (i+i)th element of each line >i
			for(int j = i+1; j<n; j++){
				double l = R[j][i]/R[i][i];
				for(int k = i; k<n; k++){
					R[j][k] = R[j][k] - l*R[i][k];
				}
			}
		}
		// Compute the det of the triangular Matrix R
		double determinant = nbOfSwaps;   // to keep the correct signe (inverted at each swap)
		for(int i=0; i<n; i++){
			determinant *= R[i][i];
		}
		return determinant;
	}
	public static double[][] stringToMatrix(String s) throws Exception, NumberFormatException{
		
		//test if the text is a matrix
		int reihen = -1;
		int spalten = -1;
		double[][] matrix = null;
		
		String[] lines;
		

		
		lines = s.split("\\r?\\n");
		reihen = lines.length;
		

		
		//save the element of each line in the new array
		for(int i = 0;i < lines.length;i++) {
			String[] numbers = lines[i].split("\t");
			
			if(spalten == -1)
			spalten = numbers.length;
			
			else
				if(spalten != numbers.length)
					throw new Exception("the Matrix rows does not have the same number of elements");
			
			if(i == 0) {
				matrix = new double[spalten][reihen];
			}
			
			for(int j = 0; j < spalten; j++) {
				matrix[j][i] = Double.parseDouble(numbers[j]);
			}
			
		}

	return matrix;
}
	
	public static String matrixToString(double[][] m) {
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[i].length;j++) {
				sb.append((long)(Math.round(m[i][j] * 10000))/10000.0 + "\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
