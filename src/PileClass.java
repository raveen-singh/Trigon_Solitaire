import hsa.Console;
import java.awt.*;
import java.util.*;

public class PileClass extends DeckClass {

	public PileClass() {
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		for (int i = 0; i < deck.size(); i++) {
			CardClass card = (CardClass) deck.elementAt(i);
			card.setCentre(getCentreX() + (i * 20), getCentreY());
			card.draw(g);
		}
	}

	// public void draw(Graphics g) {
	//
	// for (int i = 0; i < 3; i++) {
	// Card card = (Card) getCard(deck.size() - 3 + i);
	// card.setCenter(getCenterX() + (i * 30), getCenterY());
	// card.setColor(getColor());
	// card.setSize(getHeight());
	// card.draw(g);
	// }
	// }

	public void erase(Graphics g) {
		Color oldColor = getColor();
		g.setColor(new Color(28, 111, 49));
		draw(g);
		g.setColor(oldColor);

	}
}
