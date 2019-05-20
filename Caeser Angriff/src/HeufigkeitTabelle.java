import java.util.Hashtable;

public class HeufigkeitTabelle {
	
	Hashtable<Character,Integer> httext;
	Hashtable<Character,Double> htdeutsch;
	
	public HeufigkeitTabelle() {
		htdeutsch = new Hashtable<Character,Double>();
		htdeutsch.put('e', 17.40);
		htdeutsch.put('n', 9.78);
		htdeutsch.put('i', 7.55);
		htdeutsch.put('s', 7.27);
		htdeutsch.put('r', 7.00);
		htdeutsch.put('a', 6.51);
		htdeutsch.put('t', 6.15);
		htdeutsch.put('d', 5.08);
		htdeutsch.put('h', 4.76);
		htdeutsch.put('u', 4.35);
		htdeutsch.put('l', 3.44);
		htdeutsch.put('c', 3.06);
		htdeutsch.put('g', 3.01);
		htdeutsch.put('m', 2.53);
		htdeutsch.put('o', 2.51);
		htdeutsch.put('b', 1.89);
		htdeutsch.put('w', 1.89);
		htdeutsch.put('f', 1.66);
		htdeutsch.put('k', 1.21);
		htdeutsch.put('z', 1.13);
		htdeutsch.put('p', 0.79);
		htdeutsch.put('v', 0.67);
		htdeutsch.put('j', 0.27);
		htdeutsch.put('y', 0.04);
		htdeutsch.put('x', 0.03);
		htdeutsch.put('q', 0.02);
	}
	
	
	public double rechneDistanz(String s) {
		httext = new Hashtable<Character,Integer>();
		
		//hashtable mit den Alphabet ausfüllen und die Heufigkeit der Buchstaben
		for(char c = 'a'; c <= 'z'; c++) {
			httext.put(c, countChar(c,s));
		}
		
		double result = 0;
		for(char c = 'a'; c <= 'z'; c++) {
			result += Math.pow((((Integer)httext.get(c) / (double)s.length()) 
					- (double) htdeutsch.get(c)), 2);
			
		}

		return result;
	}
	
	//Diese Methode rechnet wie oft kommt ein Char in einem Text
	private int countChar(char c, String s) {
		int result = 0;
		for(int i = 0; i < s.length();i++) {
			if(s.charAt(i) == c) {
				result++;
			}
		}
		return result;
	}
	
	
	
	
	
}
