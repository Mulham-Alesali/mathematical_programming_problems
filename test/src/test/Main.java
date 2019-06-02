package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		
		String s = "2.0	4.0	-2.0	2.0\r\n" + 
				"4.0	9.0	-3.0	8.0\r\n" + 
				"-2.0	-3.0	7.0	10.0";
		double[][] d = {
	            {2,4,-2,2},
	            {4,9,-3,8},
	            {-2,-3,7,10}
	        };
		
		 
		System.out.println(matrixToString(d));
		try {
			d = stringToMatrix(s);	
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public static String matrixToString(double[][] d) {
		StringBuilder sb = new StringBuilder("");
		if(d != null)
		for(int i = 0;i < d.length;i++) {
			for(int j = 0; j < d[i].length;j++) {
				sb.append(d[i][j]);
				if(j != d[i].length - 1)
				sb.append("\t");
				
			}
		sb.append("\n");	
		}
		return sb.toString();
	}
	
	
	public static double[][] stringToMatrix(String s) throws Exception, NumberFormatException{
		
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
