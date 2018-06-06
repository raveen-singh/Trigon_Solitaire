import java.awt.Graphics;

public class TopCardClass extends DeckClass {

	public TopCardClass() {
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g,int n) {
		int j = 0;
		if (deck.size() > 0) {
			for (int i = 0; i < n; i++) {
				CardClass card = (CardClass) deck.elementAt(i);
				card.setCentre(getCentreX(), getCentreY() + j);
				card.draw(g);
				j += 30;
				// System.out.println(card.getCentreX());
				// System.out.println(card.getCentreX());

			}
		}
	}

	public void fanOut(Graphics g) {
		int j = 0;
		for (int i = 0; i < deck.size(); i++) {
			CardClass card = (CardClass) deck.elementAt(i);
			card.setCentre(getCentreX(), getCentreY() + j);
			card.draw(g);
			j += 30;
		}
	}
}