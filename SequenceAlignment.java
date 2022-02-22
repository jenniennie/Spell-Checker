/* Name: Jennifer Nguyen
 * Dr. Andrew Steinberg
 * COP3503 Fall 2021
 * Programming Assignment 4
 */

package sequencealignment;

import java.util.Random;

public class SequenceAlignment {
	String one;
	String two;
	String solution1 = "";
	String solution2 = "";
	public SequenceAlignment(String one, String two) {
		this.one = one;
		this.two = two;
	}
	// This is the method that will return both alignments as one string separated by a whitespace character (‘ ’). 
	public String getAlignment()
	{
		return solution1 + " " + solution2;
	}
	
	public int letterType(char ch)
	{
		// check if letter is vowel, if not return 0
		if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' )
			return 1;
		else 
			return 0;
	}
	
	public void computeAlignment(int gap)
	{
		int i,j;
		int m = one.length();
		int n = two.length();
		int a [][] = new int[m + 1][n + 1];
		
		// initial table values
		for (i = 0; i <= m; i++)
			a[i][0] = i * gap;
		
		for (j = 0; j <= n; j++)
			a[0][j] = j * gap;
		
		int l = n + m; // max length
	     
	    int sol1index = l;
	    int sol2index = l;
	 
	    int sol1[] = new int[l + 1];
	    int sol2[] = new int[l + 1];
	    
	    for (i = 1; i <= m; i++)
	    {
	        for (j = 1; j <= n; j++)
	        {
	            if (one.charAt(i - 1) == two.charAt(j - 1))
	            {
	            	a[i][j] = a[i-1][j-1];
	            }
	            // vowel/vowel or constant/constant 
	            else if (letterType(one.charAt(i - 1)) == letterType(two.charAt(j - 1)))
	            {
	            	a[i][j] = Math.min(Math.min(1 + a[i-1][j-1], gap + a[i-1][j]), gap + a[i][j-1]);
	            }
	            else // vowel and constant
	            {
	            	a[i][j] = Math.min(Math.min(3 + a[i-1][j-1], gap + a[i-1][j]), gap + a[i][j-1]);
	            }
	        }
	    }
	    
	    i = m; j = n;
	    
	    while (!(i == 0 || j == 0))
	    {
	    	// if same go diagonal
	        if (one.charAt(i - 1) == two.charAt(j - 1))
	        {
	            sol1[sol1index--] = (int)one.charAt(i - 1);
	            sol2[sol2index--] = (int)two.charAt(j - 1);
	            i--; 
	            j--;
	        }
	        // check neighbors
	        else if (a[i - 1][j] + gap == a[i][j])
	        {
	            sol1[sol1index--] = (int)one.charAt(i - 1);
	            sol2[sol2index--] = (int)'-';
	            i--;
	        }
	        else if (a[i][j - 1] + gap == a[i][j])
	        {
	            sol1[sol1index--] = (int)'-';
	            sol2[sol2index--] = (int)two.charAt(j - 1);
	            j--;
	        }
	        // check diagonal
	        else if (a[i - 1][j - 1] + 1 == a[i][j])
	        {
	            sol1[sol1index--] = (int)one.charAt(i - 1);
	            sol2[sol2index--] = (int)two.charAt(j - 1);
	            i--; 
	            j--;
	        }
	    }
	    while (sol1index > 0)
	    {
	        if (i > 0) 
	        	sol1[sol1index--] = (int)one.charAt(--i);
	        else 
	        	sol1[sol1index--] = (int)'-';
	    }
	    while (sol2index > 0)
	    {
	        if (j > 0) 
	        	sol2[sol2index--] = (int)two.charAt(--j);
	        else 
	        	sol2[sol2index--] = (int)'-';
	    }
	 
	    
	    
	    for (i = 1; i <= l; i++)
	    {
	    	// ignore excess gaps
	        if ((char)sol1[i] == '-' && (char)sol2[i] == '-')
	        {
	            continue;
	    	}
	        solution1 += (char)sol1[i];
	    	solution2 += (char)sol2[i];
	    }
	}

}
