import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
	
	Frame frame;
	String matrix;
	String matrix2;
	
	File file;

	
	
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

	public String getMatrix() {
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
	
	public double[][] rechnen(double[][] matrix) {
			
		return null;
	}
	
	
	
	public static double[][] stringtomatrix(String s) throws Exception, NumberFormatException{
		
		//test if the text is a matrix
		int reihen = -1;
		int spalten = -1;
		double[][] matrix = null;
		
		String[] lines;
		lines = s.split("(\r\n|\r|\n)");
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

}
