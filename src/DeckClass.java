import hsa.Console;
import java.awt.*;
import java.util.*;

public class DeckClass extends SuitClass {
	protected Vector deck = new Vector(0, 1);

	public DeckClass() {
	}

	public DeckClass(char deckType) {
		setHeight(100);
		setWidth(80);
		setCentre(300, 500);

		if (deckType == 's') {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j <= 12; j++) {
					CardClass card = new CardClass(i, j);
					card.setCentre(150, 500);
					card.setSize(3);
					card.setFaceUp(true);
					addCard(card, 0);
				}
			}
		}
	}

	public void addCard(CardClass cardToAdd, int Pos) {
		if (Pos == 0 || deck.size() == 0) {
			deck.addElement(cardToAdd);
		} else if (Pos > deck.size()) {
			deck.insertElementAt(cardToAdd, deck.size());
		} else {
			deck.insertElementAt(cardToAdd, Pos);
		}
	}

	public void addCard(CardClass cardToAdd) {
		deck.addElement(cardToAdd);
	}

	public CardClass dealCard() {
		return (CardClass) deck.lastElement();

	}
	
	public CardClass cardAt(int n) {
		return (CardClass) deck.elementAt(n);
	}

	public void removeCard(int Pos) {
		deck.remove(Pos);
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public void removeLast() {
		deck.removeElement(deck.lastElement());
	}

	public void remove(CardClass cardtoremove) {
		if (deck.size() > 0) {
			deck.removeElement(cardtoremove);
		}
	}

	public void draw(Graphics g) {
		if (deck.size() > 0) {
			CardClass card = (CardClass) deck.lastElement();
			card.setCentre(getCentreX(), getCentreY());
			card.setColor(getColor());
			card.setSize(getHeight());
			card.draw(g);
		}
	}

	public void drawStock(Graphics g) {
		if (deck.size() > 0) {
			int x = 0, y = 0;
			for (int i = 0; i < deck.size(); i++) {
				CardClass card = new CardClass();
				card.setCentre(getCentreX() + x, getCentreY() + y);
				card.setFaceUp(false);
				card.setSize(3);
				card.draw(g);
				x += 1;
				y += 1;
			}
		}
	}

	public int deckSize() {
		return deck.size();
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

	public void erase(Graphics g) {
		int Height = getHeight();
		int Width = getWidth();
		int CentreX = getCentreX();
		int CentreY = getCentreY();
		Color oldColor = getColor();

		g.setColor(Color.white);
		g.fillRect(CentreX - Width / 2, CentreY - Height / 2, Width + 1, Height + 1);
		g.setColor(oldColor);
	}

	public void flipUp() {
		if (deck.size() > 0) {
			CardClass card = (CardClass) deck.lastElement();
			card.setFaceUp(true);
		}
	}
}
