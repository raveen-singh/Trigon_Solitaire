import hsa.Console;
import java.awt.*;

public abstract class ShapeClass {

	private int iWidth;
	private int iHeight;
	private int iCentreX;
	private int iCentreY;
	private Color iColor;

	public void setWidth(int iNewWidth) {
		iWidth = iNewWidth;
	}

	public void setHeight(int iNewHeight) {
		iHeight = iNewHeight;
	}

	public void setCentre(int iNewCentreX, int iNewCentreY) {
		iCentreX = iNewCentreX;
		iCentreY = iNewCentreY;
	}

	public void setColor(Color cNewColor) {

		iColor = cNewColor;
	}

	public int getWidth() {
		return iWidth;
	}

	public int getHeight() {
		return iHeight;
	}

	public int getCentreX() {
		return iCentreX;
	}

	public int getCentreY() {
		return iCentreY;
	}

	public Color getColor() {
		return iColor;
	}

	public abstract void draw(Graphics g);

	public void erase(Graphics g) {
		Color cOldColor = getColor();
		g.setColor(Color.white);
		draw(g);
		setColor(cOldColor);
	}

	public void delay(int iDelayTime) {
		long lFinalTime = System.currentTimeMillis() + iDelayTime;
		do {
		} while (lFinalTime >= System.currentTimeMillis());
	}

	public boolean isPointInside(int x, int y) {
		if (x >= getCentreX() - getWidth() / 2 && x <= getCentreX() + getWidth() / 2
				&& y >= getCentreY() - getHeight() / 2 && y <= getCentreY() + getHeight() / 2) {
			return true;
		}
		return false;
	}
}
