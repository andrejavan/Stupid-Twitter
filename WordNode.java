package chatbot;

import java.util.ArrayList;
import java.util.Random;

public class WordNode implements WordNodeInterface {
	private String word;
	private ArrayList<WordNode> children;
	private ArrayList<Integer> weights;
	private int totalChildren;
	
	public WordNode(String word) {
		this.word = word;
		this.children = new ArrayList<WordNode>();
		this.weights = new ArrayList<Integer>();
		this.totalChildren = 0;
	}
	
	public String getWordValue() {
		return this.word;
	}
	
	public int hasWord(String word) {
		for(int i=0; i<this.children.size();i++) {
			if(this.children.get(i).getWordValue().equals(word)) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public WordNode addWord(String word) {
		this.totalChildren++;
		//already a child
		int i = this.hasWord(word);
		if(i>-1) {
			this.weights.set(i, this.weights.get(i)+1);
			
			return this.children.get(i);
		}
		//not already a child
		else {
			WordNode toAdd = new WordNode(word);
			this.children.add(toAdd);
			this.weights.add(1);
			return toAdd;
		}
	}
	
	public void addNode(WordNode node) {
		this.totalChildren++;
		for(int i=0; i<this.children.size();i++) {
			if(this.children.get(i).getWordValue().equals(node.getWordValue())) {
				this.weights.set(i, this.weights.get(i)+1);
				return;
			}
		}
		this.children.add(node);
		this.weights.add(1);
		return;
	}

	
	public WordNode randomNext() {
		Random rand = new Random();
		if(this.totalChildren < 1) return null;
		if(this.totalChildren < 2) return this.children.get(0);
		int r = rand.nextInt(this.totalChildren);
		int s = 0;
		int i;
		for(i=0; i<this.children.size(); i++) {
			s+=this.weights.get(i);
			if(s>=r) {
				return this.children.get(i);
			}
		}
		return this.children.get(i);
	}
	
	public void printChildren() {
		for(WordNode child : this.children){
			System.out.print(child.getWordValue()+"\n");
		}
	}
	
	public WordNode getChild(String word) {
		int i = this.hasWord(word);
		if(i>-1) {
			return this.children.get(i);
		}
		else return null;
	}
	
	public WordNode getChild(int i) {
		return this.children.get(i);
	}
}
