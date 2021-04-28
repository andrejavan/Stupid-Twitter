package chatbot;

public interface WordNodeInterface {
	public WordNode addWord(String word);
	public void addNode(WordNode node);
	public String getWordValue();
	public WordNode randomNext();
}
