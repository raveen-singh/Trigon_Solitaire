import java.awt.Graphics;

public class TopCardClass extends DeckClass {

	public TopCardClass() {
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		if (deck.size() > 0) {
			for (int i = 0; i < deck.size(); i++) {
				CardClass card = (CardClass) deck.elementAt(i);
				card.setCentre(getCentreX(), getCentreY() + (i*30));
				card.draw(g);
				// System.out.println(card.getCentreX());
				// System.out.println(card.getCentreX());

			}
		}
	}
}