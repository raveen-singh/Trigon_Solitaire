
public class TableauClass extends DeckClass {

	public TableauClass() {
		// TODO Auto-generated constructor stub
	}

	private String[] letter = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	public boolean isValid(CardClass c1, CardClass c2) {
		if (deck.size() == 0 && c1.getFaceValue() == "K") {
			return true;
		}
		return false;
	}

	public void flipUp() {
		CardClass card = (CardClass) deck.lastElement();
		if (card.isFaceUp() == false) {
			card.setFaceUp(true);
		}
	}
}
