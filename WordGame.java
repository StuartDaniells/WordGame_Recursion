/**
 * Class to find the words that can be derived from a user input word
 * @author Stuart Daniells
 * @studentNumber C0829441
 * Assignment - 04
 * Question - q19
 * @date 21th March 2022
 */

package q19;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** 
 * Class that allows user to play a word game that determines 
 * all possible words from words.txt that can be constructed 
 * from a word provided by the user via keyboard 
 */

public class WordGame {

	/** 
     * Main method used as the program driver 
     * @param args Command line arguments 
     */ 
     public static void main(String[] args) 
     {
    	 String userWordInput; 
         String wordFileData;
         
         //Reads a String from the user and stores it in userWordInput variable. 
         Scanner input = new Scanner(System.in);
         System.out.println("Enter a word: ");
         userWordInput = input.nextLine();
         
         System.out.println("\n******USER WORD******");
         displayHist(userWordInput); //Displays the word along with its histogram 
         
         System.out.println("\n******FOUND MATCHES******");
         
         //Go line by line through the words.txt reading the word   
         //on each line into wordFileData. If wordFileData can be constructed 
         //using only the letters in userWord, then displays the wordFileData 
         //along with its histogram.
         try (Scanner fileScanner = new Scanner(new FileInputStream("words.txt")))
         {
        	 //Reads line from file
		      while (fileScanner.hasNext()) {
		    	  wordFileData = fileScanner.next().toUpperCase();
		    	  if(canBeGeneratedFrom(wordFileData, userWordInput)) {
		    		  displayHist(wordFileData);
		    	  }
		      }
		      fileScanner.close();
         }         
         catch (FileNotFoundException e)
         {
        	 e.printStackTrace();
         }
         
         input.close();  
     }      
       
    /** 
     * Generate histogram containing the occurrence of letters A-Z 
     * @param word English word with no spaces or special characters 
     * @return A 26 element array containing histogram of character   
     * frequency in given word  
     */ 
    public static int[] generateHistogram(String word) 
    { 
    	/* Index 0 is A, 1 is B, ... , 25 is Z */ 
       int[] char_Hist = new int[26];  
  
       /* It takes away any characters that are not alphabetical  
          characters */ 
		word = word.replaceAll("[^a-zA-Z]", "").toUpperCase();  
		  
		for(int i = 0; i < word.length(); i++) { 
		   char_Hist[word.charAt(i) - 'A'] += 1; //'A' is 65 in ASCII 
		}  
		return char_Hist; 
    } 
    
    /** 
	* Display histogram representation of provided word 
	* @param word Word containing alphabetical characters 
	*/ 
    public static void displayHist(String word) 
    { 
        int index = 0;
    	int[] charHist = generateHistogram(word); 
    	
    	char[] alphabetArray = {'A','B','C','D','E','F','G','H','I','J','K', 'L','M','N',
    							'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    	
        System.out.print(word.toUpperCase());        
        for(index = 0; index < charHist.length; index++) {
        	if(charHist[index] != 0) {
        		System.out.print(" -> ");
        		System.out.print(alphabetArray[index] + "|" + charHist[index]);        		
        	}
        }
        System.out.println("");
    } 

    /** 
     * Returns the boolean true or false depending on whether word1 
     * can be built from the letters in word2 
     * @param word1 Word you which to construct 
     * @param word2 Word that contains the letters you have available 
     * @return true if word1 can be built from word2, false 
     * otherwise 
     */ 
    public static boolean canBeGeneratedFrom(String word1, String word2) 
    { 	
    	int[] word1Hist = generateHistogram(word1);
    	int[] word2Hist = generateHistogram(word2);
    	
    	for (int index = 0; index < word1Hist.length; index++) {
    		if (word1Hist[index] > word2Hist[index]) {
    			return false;
    		}
    	}
    	return true;
    }  
    
}

