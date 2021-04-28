package chatbot;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Graph {

	public static void main(String[] args) {
		String handle = "";
		File tweetFile = new File("C:\\Users\\afjav\\eclipse-workspace\\chatbot\\src\\chatbot/"+handle+".txt");
		WordNode START = new WordNode("START");
		//BUILD GRAPH
		WordNode ALL = new WordNode("ALL");
		try {
			Scanner s = new Scanner(tweetFile);
			
			WordNode current = START;
			while(s.hasNext()) {
				String temp = s.next();
				//spaghetti
				WordNode toAdd = ALL.addWord(temp);
				current.addNode(toAdd);
				if(temp.equals("END")) {
					current = START;
				}
				else {
					current = toAdd;
				}
				
			}
			s.close();
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		for(int i=0; i<5;i++)
			System.out.print(makeTweet(START)+"\n");
	}

	//MAKE A TWEET
	public static String makeTweet(WordNode start) {
		WordNode current = start.randomNext();
		String s = current.getWordValue();
		current = current.randomNext();
		while(s.length()<140 && !current.getWordValue().equals("END")) {
			s+=" "+current.getWordValue();
			current = current.randomNext();
		}
		return s;
	}
}
