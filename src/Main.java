import java.awt.*;
import java.awt.event.*;
import hsa.Console;

import java.applet.*;

public class Main extends Applet implements MouseListener, MouseMotionListener {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	Graphics bufferGraphics;
	Image offscreen;
	Dimension dim;
	int curX, curY;
	DeckClass d1 = new DeckClass('s');
	PileClass waste = new PileClass();
	TopCardClass top = new TopCardClass();
	TableauClass[] tableau = new TableauClass[7];
	FoundationClass[] foundation = new FoundationClass[4];
	Color bgColor = (new Color(28, 111, 49));
	int oldCentreX, oldCentreY;
	int master_deck = -1;
	int tab_deck = -1;
	int found_deck = -1;
	int l = 1;

	public void init() {
		bufferGraphics = getGraphics();
		d1.shuffle();
		setSize(800, 600);
		setBackground(bgColor);
		addMouseListener(this);
		addMouseMotionListener(this);
		d1.setColor(Color.blue);
		dim = getSize();
		offscreen = createImage(dim.width, dim.height);
		bufferGraphics = offscreen.getGraphics();
		for (int i = 0; i < 7; i++) {
			tableau[i] = new TableauClass();
			tableau[i].setHeight((i * 30) + 100);
			tableau[i].setWidth(70);
			for (int j = 0; j < i + 1; j++) {
				if (i == j) {
					d1.dealCard().setFaceUp(true);
				} else {
					d1.dealCard().setFaceUp(false);
				}
				tableau[i].addCard(d1.dealCard());
				d1.removeLast();
			}
		}
		int x = 0;

		for (int i = 0; i < 4; i++) {
			foundation[i] = new FoundationClass(i);
			foundation[i].setHeight(100);
			foundation[i].setWidth(70);
			foundation[i].setCentre(400 + x, 90);
			x += 100;

		}
		int j = 0;

		for (int i = 0; i < 7; i++) {
			tableau[i].setCentre(100 + j, 250);
			tableau[i].setColor(Color.blue);
			j += 100;
		}

		d1.setCentre(80, 75);
		waste.setCentre(200, 95);
		waste.setHeight(100);
		waste.setWidth(70);
	}

	public void paint(Graphics g) {
		bufferGraphics.clearRect(0, 0, dim.width, dim.width);
		bufferGraphics.setColor(new Color(40, 160, 70));
		int c = 365;
		for (int i = 0; i < 4; i++) {
			bufferGraphics.fillRoundRect(c, 40, 70, 100, 5, 5);
			c += 100;
		}
		for (int i = 0; i < 4; i++) {
			foundation[i].draw(bufferGraphics);
		}
		int j = 0;
		for (int i = 0; i < 7; i++) {
			tableau[i].fanOut(bufferGraphics);
			j += 100;
		}
		d1.drawStock(bufferGraphics);
		waste.draw(bufferGraphics);
		top.draw(bufferGraphics, l);
		g.drawImage(offscreen, 0, 0, this);

	}

	public void update(Graphics g) {
		paint(g);
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		if (top.getMovable()) {
			// System.out.println("HELLO");
			top.setCentre(e.getX(), e.getY());
			repaint();
		}
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 7; i++) {
			if (tableau[i].dealCard().isPointInside(e.getX(), e.getY()) == true && tableau[i].deckSize() > 0) {
				oldCentreX = tableau[i].dealCard().getCentreX();
				oldCentreY = tableau[i].dealCard().getCentreY();
				top.addCard(tableau[i].dealCard());
				top.isMovable(true);
				tableau[i].removeLast();
				tab_deck = i;
				found_deck = -1;
				top.setCentre(e.getX(), e.getY());
				repaint();
			}
		}
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < tableau[i].deckSize(); j++) {
				CardClass card = (CardClass) tableau[i].cardAt(j);
				if (card.isPointInside(e.getX(), e.getY()) == true && card.isFaceUp() == true) {
					System.out.println("Hello1");
					for (int k = j; k < tableau[i].deckSize(); k++) {
						top.addCard(tableau[i].cardAt(k));
						tableau[i].removeCard(k);
						top.isMovable(true);
						l = k;
						System.out.println(l);
					}
					top.setCentre(e.getX(), e.getY());
					repaint();
				}
			}
		}
		for (int j = 0; j < 4; j++) {
			if (foundation[j].isPointInside(e.getX(), e.getY()) == true && foundation[j].deckSize() > 0) {
				oldCentreX = foundation[j].dealCard().getCentreX();
				oldCentreY = foundation[j].dealCard().getCentreY();
				top.addCard(foundation[j].dealCard());
				foundation[j].removeLast();
				top.isMovable(true);
				found_deck = j;
				tab_deck = -1;
				top.setCentre(e.getX(), e.getY());
				repaint();

			}
		}
		if (d1.isPointInside(e.getX(), e.getY()) == true) {
			if (d1.deckSize() > 0) {
				for (int i = 0; i < 1; i++) {
					CardClass card = d1.dealCard();
					d1.removeLast();
					card.setCentre(d1.getCentreX() + 120, d1.getCentreY() + 20);
					waste.addCard(card);
					// System.out.println(d1.deckSize());
					// System.out.println(waste.deckSize());
					repaint();
				}

			}
		}
		if (waste.isPointInside(e.getX(), e.getY()) == true && waste.deckSize() > 0) {
			System.out.println("Hello");
			oldCentreX = waste.dealCard().getCentreX();
			oldCentreY = waste.dealCard().getCentreY();
			top.addCard(waste.dealCard());
			waste.removeLast();
			master_deck = 0;
			top.isMovable(true);
		}
		if (d1.deckSize() == 0) {
			int k = waste.deckSize();
			for (int i = 0; i < k; i++) {
				// System.out.println(waste.deckSize());
				// System.out.println(i);
				d1.addCard(waste.dealCard());
				waste.removeLast();
			}
			waste.erase(bufferGraphics);
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 7; i++) {
			if (tableau[i].dealCard().isPointInside(top.getCentreX(), top.getCentreY()) == true) {
				if (tableau[i].isValid(top.dealCard(), tableau[i].dealCard()) == true) {
					tableau[i].addCard(top.dealCard());
					if (tab_deck > -1) {
						tableau[tab_deck].flipUp();
						tab_deck = -1;
					}
					top.removeLast();
					repaint();
				}
			}
		}
		for (int j = 0; j < 4; j++) {
			if (foundation[j].isPointInside(top.getCentreX(), top.getCentreY()) == true) {
				if (foundation[j].isValid(top.dealCard()) == true) {
					foundation[j].addCard(top.dealCard());
					if (tab_deck > -1) {
						tableau[tab_deck].flipUp();
					}
					top.removeLast();
					repaint();
				}

			}
		}
		int r = 0;
		for (int l = 0; l < 4; l++) {
			if (foundation[l].isPointInside(top.getCentreX(), top.getCentreY()) == false) {
				r += 1;
				if (r == 4) {
					if (master_deck == 0) {
						top.dealCard().setCentre(oldCentreX, oldCentreY);
						waste.addCard(top.dealCard());
						top.removeLast();
						master_deck = -1;
						repaint();
					}
					if (tab_deck > -1 && found_deck == -1) {
						top.dealCard().setCentre(oldCentreX, oldCentreY);
						tableau[tab_deck].addCard(top.dealCard());
						top.removeLast();
						tab_deck = -1;
						System.out.println(oldCentreX);
						System.out.println(oldCentreY);
						repaint();
					}
					if (tab_deck == -1 && found_deck > -1) {
						top.dealCard().setCentre(oldCentreX, oldCentreY);
						foundation[found_deck].addCard(top.dealCard());
						top.removeLast();
						found_deck = -1;
						repaint();
					}
				}
			}
		}
		int d = 0;
		for (int k = 0; k < 7; k++) {
			if (tableau[k].dealCard().isPointInside(top.getCentreX(), top.getCentreY()) == false) {
				d += 1;
				if (d == 7) {
					if (master_deck == 0) {
						top.dealCard().setCentre(oldCentreX, oldCentreY);
						waste.addCard(top.dealCard());
						top.removeLast();
						master_deck = -1;
						repaint();
					}
					if (tab_deck > -1 && found_deck == -1) {
						top.dealCard().setCentre(oldCentreX, oldCentreY);
						tableau[tab_deck].addCard(top.dealCard());
						top.removeLast();
						tab_deck = -1;
						repaint();
					}
					if (tab_deck == -1 && found_deck > -1) {
						top.dealCard().setCentre(oldCentreX, oldCentreY);
						foundation[found_deck].addCard(top.dealCard());
						top.removeLast();
						found_deck = -1;
						repaint();
					}
				}
			}
		}
	}
}
