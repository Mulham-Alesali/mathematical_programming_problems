
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String chipher = "PJOMSVNHTZHTZAHNUHJOZABAANHYABTKVYALPULDVOUBUNGBILZPJOAPNLU";
			char key = getKey(chipher);
			
			System.out.println(decipher(chipher,key));
	}

	
	public static char getKey(String cipher) {
		HeufigkeitTabelle ht = new HeufigkeitTabelle();
		
		double min = Double.MAX_VALUE;
		char key = 'a';
		double testing = 0;
		
		for(char c = 'a'; c <= 'z'; c++) {
		//	System.out.println(decipher(cipher,c)+ "\n");
			testing = ht.rechneDistanz(decipher(cipher,c));
			if(testing < min) {
				min = testing;
				key = c;
			}
		}
		
		return key;
	}
	
	public static String decipher(String cipherText, char key)
	{
		//cipherText = HelpMethods.replaceUmlaut(cipherText);
		cipherText = cipherText.toUpperCase();
		String plainText = "";
		key = Character.toUpperCase(key);
		for(int i = 0, count = 0, length = cipherText.length(); i< length; i++)
		{
			if(Character.isAlphabetic(cipherText.charAt(i)))
			{
				char result = (char) (((cipherText.charAt(i)) - key + 26) % 26 + 65);
				plainText += Character.toString((char) (result));
				count++;
			}
			
		
		}
		return plainText.toLowerCase();
	}
	
}
