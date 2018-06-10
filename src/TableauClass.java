
public class TableauClass extends DeckClass {

	public TableauClass() {
		// TODO Auto-generated constructor stub
	}

	private String[] letter = { "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A" };

	public boolean isValid(CardClass c1, CardClass c2) {
		if (deck.size() == 0 && c1.getFaceValue() == "K") {
			return true;
		}
		int k = 0;
		int j = 0;
		if (deck.size() > 0) {
			for (int i = 0; i < 13; i++) {
				if (letter[i].equals(c2.getFaceValue())) {
					k = i;
					break;
				}
			}
			for (int i = 0; i < 13; i++) {
				if (letter[i].equals(c1.getFaceValue())) {
					j = i;
					break;
				}
			}

			if (c2.getSuitValue() == c1.getSuitValue() && k - j == -1) {
				return true;
			}
		}
		// if (deck.size() == 0 && c2.getFaceValue().equals(letter[0])) {
		// return true;
		// }
		return false;

	}

	public void flipUp() {
		CardClass card = (CardClass) deck.lastElement();
		if (card.isFaceUp() == false) {
			card.setFaceUp(true);
		}
	}

	public boolean isValidEmpty(CardClass c1) {
		if (c1.getFaceValue().equals(letter[0])) {
			return true;
		}
		return false;
	}

	public int isDrag() {
		int c = 0;
		if (deck.size() > 0) {
			for (int i = deck.size() - 2; i >= 0; i--) {
				CardClass card = (CardClass) cardAt(i);
				if (card.isFaceUp()) {
					c += 1;
				} else {
					break;
				}
			}
		}
		return c;
	}
}
