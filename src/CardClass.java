import hsa.Console;
import java.awt.*;

public class CardClass extends ShapeClass {

	public CardClass() {
	}

	public CardClass(int i, int j) {
		setSuitValue(i);
		setFaceValue(j);
	}

	private String iFaceValue;
	private boolean iFaceUp = true;
	private int iSuit;
	private String[] val = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	static Font f1 = new Font("SanSerif", Font.PLAIN, 16);

	public void setSuitValue(int n) {
		iSuit = n;
	}

	public String getFaceValue() {
		return iFaceValue;
	}

	public void setFaceValue(int n) {
		iFaceValue = val[n];
	}

	public int getSuitValue() {
		return iSuit;
	}

	public void setSize(int n) {
		if (n == 1) {
			setHeight(60);
		}
		if (n == 2) {
			setHeight(80);
		}
		if (n == 3) {
			setHeight(100);
		}
		if (n == 4) {
			setHeight(120);
		}
		setWidth((int) Math.round(getHeight() * 0.70));
	}

	public boolean isFaceUp() {
		return iFaceUp;
	}

	public void setFaceUp(boolean n) {
		iFaceUp = n;
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

	public void draw(Graphics g) {
		int CentreX = getCentreX();
		int CentreY = getCentreY();
		int Width = getWidth();
		int Height = getHeight();
		Color color = getColor();
		if (isFaceUp() == true) {
			g.setColor(Color.white);
			g.fillRoundRect(CentreX - Width / 2, CentreY - Height / 2, Width, Height, 5, 5);
			g.setColor(Color.black);
			g.drawRoundRect(CentreX - Width / 2, CentreY - Height / 2, Width, Height, 5, 5);
			g.setFont(f1);
			g.drawString(iFaceValue, CentreX - Width / 2 + Width / 8, CentreY - Height / 4);
			if (iSuit == 0) {
				DiamondClass d1 = new DiamondClass((int) (Math.round(Height * 0.25)), CentreX, CentreY, Color.red);
				d1.draw(g);
			}
			if (iSuit == 2) {
				HeartClass h1 = new HeartClass((int) (Math.round(Height * 0.25)), CentreX, CentreY, Color.red);
				h1.draw(g);
			}
			if (iSuit == 3) {
				SpadeClass s1 = new SpadeClass((int) (Math.round(Height * 0.25)), CentreX, CentreY, Color.black);
				s1.draw(g);
			}
			if (iSuit == 1)

			{
				ClubClass c1 = new ClubClass((int) (Math.round(Height * 0.25)), CentreX, CentreY, Color.black);
				c1.draw(g);
			}

		}
		if (isFaceUp() == false) {
			g.setColor(Color.blue);
			g.fillRoundRect(CentreX - Width / 2, CentreY - Height / 2, Width, Height, 5, 5);
			g.setColor(Color.black);
			g.drawRoundRect(CentreX - Width / 2, CentreY - Height / 2, Width, Height, 5, 5);
		}

	}
}
