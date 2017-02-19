//Remove all characters of a string from another string. 
//The algorithm should be less than O(m*n) where m & n are lengths of strings and the space used
//should be less than O(m+n).

package test;

import java.util.HashSet;
import java.util.Set;

public class PatternRemoval {
	public static void main (String [] args){
		String source = "This is the best day";
		String pattern = "aeiou";
		Set<Character> set = new HashSet<Character>();
		for (int i=0; i<pattern.length(); i++){
			set.add(pattern.charAt(i));
		}
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<source.length(); i++){
			if(set.contains(source.charAt(i)))
				continue;
			else
				sb.append(source.charAt(i));
		}
		System.out.println(sb.toString());
	}
}
