import java.awt.*;
import java.awt.event.*;

import java.applet.*;

public class Main extends Applet implements MouseListener, MouseMotionListener {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	private Graphics bufferGraphics;
	private Image offscreen;
	private Dimension dim;
	private int curX, curY;
	private DeckClass d1 = new DeckClass('s');
	private PileClass waste = new PileClass();
	private TopCardClass top = new TopCardClass();
	private TableauClass[] tableau = new TableauClass[7];
	private FoundationClass[] foundation = new FoundationClass[4];
	private Color bgColor = (new Color(28, 111, 49));
	private int oldCentreX, oldCentreY;
	private int master_deck = -1;
	private int tab_deck = -1;
	private int found_deck = -1;
	private int score = 0;
	private boolean isDrawn = true;

	public void init() {
		bufferGraphics = getGraphics();
		d1.shuffle();
		setSize(800, 900);
		setBackground(bgColor);
		if (isDrawn == true) {
			addMouseListener(this);
			addMouseMotionListener(this);
			isDrawn = false;
		}

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
			foundation[i] = new FoundationClass();
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
		bufferGraphics.fillRoundRect(45, 25, 70, 100, 5, 5);

		int c = 365;
		for (int i = 0; i < 4; i++) {
			bufferGraphics.fillRoundRect(c, 40, 70, 100, 5, 5);
			c += 100;
		}
		int d = 65;
		for (int i = 0; i < 7; i++) {
			bufferGraphics.fillRoundRect(d, 200, 70, 100, 5, 5);
			d += 100;
		}
		for (int i = 0; i < 4; i++) {
			foundation[i].draw(bufferGraphics);
		}
		int j = 0;
		for (int i = 0; i < 7; i++) {
			tableau[i].fanOut(bufferGraphics);
			j += 100;
		}
		showStatus("Score:  " + score);
		d1.drawStock(bufferGraphics);
		waste.draw(bufferGraphics);
		top.draw(bufferGraphics);
		g.drawImage(offscreen, 0, 0, this);

	}

	public void restart() {
		init();
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		if (top.getMovable()) {
			top.setCentre(e.getX(), e.getY());
			repaint();
		}
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// for (int i = 0; i < 7; i++) {
		// if (tableau[i].deckSize() > 0) {
		// CardClass card = (CardClass) tableau[i].dealCard();
		// if (card.isPointInside(e.getX(), e.getY()) == true && card.isFaceUp() ==
		// true) {
		// System.out.println(card.getCentreX());
		// System.out.println(card.getCentreY());
		// System.out.println(card.getWidth());
		// System.out.println(card.getHeight());
		// for (int j = 0; j < 4; j++) {
		// if (foundation[j].isValid(card) == true) {
		// foundation[j].addCard(card);
		// tableau[i].remove(card);
		// score += 10;
		// if (tableau[i].deckSize() > 0) {
		// if (tableau[i].isFlip() == false) {
		// tableau[i].flipUp();
		// score += 5;
		// }
		// }
		// break;
		// }
		// }
		// repaint();
		// }
		// }
		// }
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == 1) {
			for (int i = 0; i < 7; i++) {
				if (tableau[i].deckSize() > 0) {
					CardClass card = (CardClass) tableau[i].dealCard();
					if (card.isPointInside(e.getX(), e.getY()) == true && card.isFaceUp() == true
							&& tableau[i].cardPosition(card) == tableau[i].deckSize() - 1) {
						oldCentreX = card.getCentreX();
						oldCentreY = card.getCentreY();
						top.addCard(card);
						tableau[i].remove(card);
						top.isMovable(true);
						tab_deck = i;
						found_deck = -1;
						top.setCentre(e.getX(), e.getY());
						repaint();
					}
				}
			}
			for (int i = 0; i < 7; i++) {
				int b = tableau[i].deckSize();
				for (int j = 0; j < b; j++) {
					CardClass card = tableau[i].cardAt(j);
					if (tableau[i].cardPosition(card) != tableau[i].deckSize() - 1) {
						if (card.isPoint(e.getX(), e.getY()) == true && card.isFaceUp() == true) {
							oldCentreX = card.getCentreX();
							oldCentreY = card.getCentreY();
							for (int k = j; k < b; k++) {
								top.addCard(tableau[i].cardAt(k));
							}
							for (int l = j; l < b; l++) {
								tableau[i].removeCard(j);
							}
							b = tableau[i].deckSize();

							top.isMovable(true);
							tab_deck = i;
							found_deck = -1;
							top.setCentre(e.getX(), e.getY());
							repaint();
						}
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
				if (d1.deckSize() + waste.deckSize() > 0) {
					if (d1.deckSize() == 0) {
						int k = waste.deckSize();
						for (int i = 0; i < k; i++) {
							d1.addCard(waste.dealCard());
							waste.removeLast();
						}
						waste.erase(bufferGraphics);
					}
					if (d1.deckSize() >= 0) {
						for (int i = 0; i < 1; i++) {
							CardClass card = d1.dealCard();
							d1.removeLast();
							card.setCentre(d1.getCentreX() + 120, d1.getCentreY() + 20);
							waste.addCard(card);
							repaint();
						}

					}
				}
			}
			if (waste.isPointInside(e.getX(), e.getY()) == true && waste.deckSize() > 0) {
				oldCentreX = waste.dealCard().getCentreX();
				oldCentreY = waste.dealCard().getCentreY();
				top.addCard(waste.dealCard());
				waste.removeLast();
				master_deck = 0;
				top.isMovable(true);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int size = 0;
		for (int i = 0; i < 4; i++) {
			size += foundation[i].deckSize();
		}
		if (size == 52) {
			System.out.println("You Win!");
		}
		int n = top.deckSize();
		for (int i = 0; i < 7; i++) {
			if (tableau[i].deckSize() > 0 && top.deckSize() > 0) {
				if (tableau[i].dealCard().isPointInside(top.getCentreX(), top.getCentreY()) == true) {
					if (tableau[i].isValid(top.bottomCard(), tableau[i].dealCard()) == true) {
						for (int j = 0; j < n; j++) {
							top.bottomCard().setCentre(tableau[i].getCentreX(), tableau[i].getCentreY() + (j * 30));
							tableau[i].addCard(top.cardAt(j));
						}
						for (int j = 0; j < n; j++) {
							top.removeBottom();
						}
						if (tab_deck > -1) {
							if (tableau[tab_deck].deckSize() > 0) {
								if (tableau[tab_deck].isFlip() == false) {
									tableau[tab_deck].flipUp();
									score += 5;
									tab_deck = -1;
								}
							}
						}
						if (master_deck == 0) {
							score += 5;
							master_deck = -1;
						}
						if (found_deck > -1) {
							score -= 15;
							found_deck = -1;
						}
						repaint();
					}
				}
			}
			if (tableau[i].deckSize() == 0) {
				if (tableau[i].isPointInside(top.getCentreX(), top.getCentreY()) == true && top.deckSize() > 0) {
					if (tableau[i].isValidEmpty(top.bottomCard()) == true) {
						for (int j = 0; j < n; j++) {
							top.cardAt(j).setCentre(tableau[i].getCentreX(), tableau[i].getCentreX() + (j * 30));
							tableau[i].addCard(top.cardAt(j));
						}
						for (int j = 0; j < n; j++) {
							top.removeBottom();
						}
						if (tab_deck > -1 && tableau[tab_deck].deckSize() > 0) {
							tableau[tab_deck].flipUp();
							score += 5;
							tab_deck = -1;
						}
						if (master_deck == 0) {
							score += 5;
							master_deck = -1;
						}
						if (found_deck > -1) {
							score -= 15;
							found_deck = -1;
						}
						master_deck = -1;
						repaint();
					}
				}
			}
		}
		int g = 0;
		for (int i = 0; i < 4; i++) {
			if (foundation[i].isPointInside(top.getCentreX(), top.getCentreY()) == true) {
				if (top.deckSize() == 1) {
					if (foundation[i].isValid(top.bottomCard()) == true) {
						foundation[i].addCard(top.bottomCard());
						top.removeBottom();
						if (tab_deck > -1) {
							if (tableau[tab_deck].deckSize() > 0) {
								if (tableau[tab_deck].isFlip() == false) {
									tableau[tab_deck].flipUp();
									score += 5;
								}
							}
						}
						if (master_deck == 0 || tab_deck > -1) {
							score += 10;
							master_deck = -1;
							tab_deck = -1;
						}
						tab_deck = -1;
						repaint();
					}
				}
				if (top.deckSize() > 0 && foundation[i].isValid(top.bottomCard()) == false) {
					if (master_deck == 0 && top.deckSize() > 0) {
						top.bottomCard().setCentre(oldCentreX, oldCentreY);
						waste.addCard(top.bottomCard());
						top.removeBottom();
						master_deck = -1;
						repaint();
					}
					if (tab_deck > -1 && found_deck == -1 && top.deckSize() > 0) {
						for (int j = 0; j < n; j++) {
							top.cardAt(j).setCentre(oldCentreX, oldCentreY + (j * 30));
							tableau[tab_deck].addCard(top.cardAt(j));
						}
						for (int j = 0; j < n; j++) {
							top.removeBottom();
						}
						tab_deck = -1;
						repaint();
					}
					if (tab_deck == -1 && found_deck > -1 && top.deckSize() > 0) {
						top.bottomCard().setCentre(oldCentreX, oldCentreY);
						foundation[found_deck].addCard(top.dealCard());
						top.removeLast();
						found_deck = -1;
						repaint();
					}
				}
			}
			if (foundation[i].isPointInside(top.getCentreX(), top.getCentreY()) == false) {
				g += 1;
			}
			if (g == 4) {
				if (master_deck == 0 && top.deckSize() > 0) {
					top.bottomCard().setCentre(oldCentreX, oldCentreY);
					waste.addCard(top.bottomCard());
					top.removeBottom();
					master_deck = -1;
					repaint();
				}
				if (tab_deck > -1 && found_deck == -1 && top.deckSize() > 0) {
					for (int j = 0; j < n; j++) {
						top.cardAt(j).setCentre(oldCentreX, oldCentreY + (j * 30));
						tableau[tab_deck].addCard(top.cardAt(j));
					}
					for (int j = 0; j < n; j++) {
						top.removeBottom();
					}
					tab_deck = -1;
					repaint();
				}
				if (tab_deck == -1 && found_deck > -1 && top.deckSize() > 0) {
					top.bottomCard().setCentre(oldCentreX, oldCentreY);
					foundation[found_deck].addCard(top.dealCard());
					top.removeLast();
					found_deck = -1;
					repaint();
				}
			}
		}
		int d = 0;
		for (int k = 0; k < 7; k++) {
			if (tableau[k].deckSize() == 0 && top.deckSize() > 0) {
				if (tableau[k].isPointInside(top.getCentreX(), top.getCentreY()) == false
						|| (tableau[k].isPointInside(top.getCentreX(), top.getCentreY()) == true
								&& tableau[k].isValidEmpty(top.bottomCard()) == false)) {
					d += 1;
					System.out.println("HELLO");
				}
			}
			if (tableau[k].deckSize() > 0 && top.deckSize() > 0) {
				if (tableau[k].dealCard().isPointInside(top.getCentreX(), top.getCentreY()) == false
						|| (tableau[k].dealCard().isPointInside(top.getCentreX(), top.getCentreY()) == true
								&& tableau[k].isValid(top.bottomCard(), tableau[k].dealCard()) == false)) {
					d += 1;
					if (d == 7) {
						if (master_deck == 0 && top.deckSize() > 0) {
							top.dealCard().setCentre(oldCentreX, oldCentreY);
							waste.addCard(top.dealCard());
							top.removeLast();
							master_deck = -1;
							repaint();
						}
						if (tab_deck > -1 && found_deck == -1 && top.deckSize() > 0) {
							for (int j = 0; j < n; j++) {
								top.cardAt(j).setCentre(oldCentreX, oldCentreY + (j * 30));
								tableau[tab_deck].addCard(top.cardAt(j));
							}
							for (int l = 0; l < n; l++) {
								top.removeBottom();
							}
							tab_deck = -1;
							repaint();
						}
						if (tab_deck == -1 && found_deck > -1 && top.deckSize() > 0) {
							top.bottomCard().setCentre(oldCentreX, oldCentreY);
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
}
