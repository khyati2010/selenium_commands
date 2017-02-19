package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*Method takes input as 'My name is Khyati Sehgal'
 * And return output as
 * 'Sehgal Khyati is name my'
 */
public class NameReturn {
	// public static void main(String[] args) {
	// Character a[i] = null;
	// String a= "My name is Khyati Sehgal";
	// a.replace(a, a);
	// testName();
	// }

	/*
	 * public StringBuffer testName() { // StringBuffer
	 * 
	 * 
	 * }
	 */

	// }
	/*
	 * public String reverseME(String s) { StringBuffer s1 = new StringBuffer();
	 * s = "My name is Khyati Sehgal"; for (int i = s.length(); i > 0; i--) {
	 * 
	 * s1.append(s.charAt(i)); System.out.println(s1.toString()); } return
	 * s1.toString();
	 * 
	 * }
	 */
	public static void main(String[] args) {
		String s = null;
		try {

			InputStreamReader j = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(j);

			System.out.println("Please provide a string: ");
			s = br.readLine();
			System.out.println("You have entered: " + s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		s = "My name is Khyati Sehgal";
		// char[] arr = s.toCharArray();
		StringBuffer a1 = new StringBuffer(s); // a1=StringBuffer.parseinto(a;
		for (int j = s.length(); j >= 0; j--) {
			a1.reverse();
			a1.append(s.charAt(j));
		
		StringBuffer b = a1.reverse();
		System.out.println(b);
		}
	}

	public String reverse(String source, int startIndex, int endIndex) {

		// endIndex = arr.length;
		StringBuffer s1 = new StringBuffer(endIndex - startIndex + 1);
		for (int j = endIndex; j >= startIndex; j--) {
			s1.reverse();
			s1.append(source.charAt(j));
		}
		return s1.toString();

	}
}
