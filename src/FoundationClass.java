import java.util.Vector;

public class FoundationClass extends DeckClass {

	
	private int suitValue;
	private String[] letter = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	
	public FoundationClass() {
		// TODO Auto-generated constructor stub
	}

	public FoundationClass (int n) {
		suitValue = n;
	}
	public boolean isValid(CardClass c1) {
		for (int i = 0; i < 12; i++) {
			if (deck.size() == i && c1.getFaceValue() == letter[i] && c1.getSuitValue() == suitValue) {
				return true;
			}
		}
		return false;
	}
}
