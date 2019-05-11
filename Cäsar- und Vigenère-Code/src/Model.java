import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Model {
	Frame frame;
	
	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	static final String CHANGE = "";
	
	String klartext;
	String Ciphertext;
	String key;
	File file;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getKlartext() {
		return klartext;
	}

	public void setKlartext(String klartext) {
		this.klartext = klartext;
	}

	public String getCiphertext() {
		return Ciphertext;
	}

	public void setCiphertext(String ciphertext) {
		Ciphertext = ciphertext;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	
	public Model(Frame frame) {
		super();
		this.frame = frame;
	}

	
	public String readFile(File file) throws IOException
	{
	    String content = null;
	
	    FileReader reader = null;
	    if(file != null)
	    try {
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
	    return content;
	}
	
	
	public void chiffrieren() {
		
	}
	public void clear() {
		
	}
	public void dechiffrieren() {
		
	}
	public void openCipherText() {
		
	}
	public void openPlainText() {
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
		
	}

}
