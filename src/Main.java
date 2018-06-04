import java.awt.*;
import java.awt.event.*;
import hsa.Console;

import java.applet.*;

public class Main extends Applet implements ActionListener, MouseListener, MouseMotionListener {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	Graphics bufferGraphics;
	Image offscreen;
	Dimension dim;
	int curX, curY;
	int deal;
	DeckClass d1 = new DeckClass('s');
	PileClass waste = new PileClass();
	TopCardClass top = new TopCardClass();
	TableauClass[] tableau = new TableauClass[7];
	FoundationClass[] foundation = new FoundationClass[4];
	Color bgColor = (new Color(28, 111, 49));

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
			tableau[i].setHeight(100);
			tableau[i].setWidth(70);
			for (int j = 0; j < i + 1; j++) {
				tableau[i].addCard(d1.dealCard());
				d1.removeLast();
			}
		}
		int x = 0;

		for (int i = 0; i < 4; i++) {
			foundation[i] = new FoundationClass(i);
			foundation[i].setHeight(100);
			foundation[i].setWidth(70);
			foundation[i].setCentre(365 + x, 40);
			x += 100;

		}
		int j = 0;

		for (int i = 0; i < 7; i++) {
			tableau[i].setCentre(100 + j, 200);
			tableau[i].setColor(Color.blue);
			j += 100;
		}

		d1.setCentre(80, 500);
		waste.setCentre(170, 520);
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
		top.draw(bufferGraphics);
		g.drawImage(offscreen, 0, 0, this);

	}

	public void update(Graphics g) {
		paint(g);
	}
	 CardClass card = (CardClass) waste.dealCard();

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (card.isPointInside(e.getX(), e.getY()) == true) {
			System.out.println("HELLO");
			card.setCentre(e.getX(), e.getY());
			repaint();
			// for (int i = 0; i < 4; i++) {
			// if (foundation[i].isPointInside(e.getX(), e.getY()) == true &&
			// foundation[i].isValid(card)) {
			// foundation[i].addCard(card);
			// waste.removeLast();
			// repaint();
			// }
			//
			// }
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
			if (tableau[i].isPointInside(e.getX(), e.getY()) == true) {
				tableau[i].setCentre(e.getX(), e.getY());
				repaint();
			}
		}
		if (d1.isPointInside(e.getX(), e.getY()) == true) {
			if (d1.deckSize() > 0) {
				if (deal == 0) {
					for (int i = 0; i < 3; i++) {
						CardClass card = d1.dealCard();
						d1.removeLast();
						card.setCentre(d1.getCentreX(), d1.getCentreY());
						waste.addCard(card);
						System.out.println(d1.deckSize());
						System.out.println(waste.deckSize());
						repaint();
					}
				}

				if (deal == 1) {
					for (int i = 0; i < 2; i++) {
						CardClass card = d1.dealCard();
						d1.removeLast();
						card.setCentre(d1.getCentreX(), d1.getCentreY());
						waste.addCard(card);
						System.out.println(d1.deckSize());
						repaint();
					}
				}
				if (deal == 2) {
					for (int i = 0; i < 1; i++) {
						CardClass card = d1.dealCard();
						d1.removeLast();
						card.setCentre(d1.getCentreX(), d1.getCentreY());
						waste.addCard(card);
						System.out.println(d1.deckSize());
						repaint();
					}
				}
			}
			if (d1.deckSize() == 0) {
				deal += 1;
				int k = waste.deckSize();
				for (int i = 0; i < k; i++) {
					System.out.println(waste.deckSize());
					System.out.println(i);

					d1.addCard(waste.dealCard());
					waste.removeLast();
				}
				waste.erase(bufferGraphics);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
