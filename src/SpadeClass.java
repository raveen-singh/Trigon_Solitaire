// The DiamondClass Class
// Second in a series of demonstration programs for introducing Java

import hsa.Console;
import java.awt.*;

class SpadeClass extends SuitClass
{


    public SpadeClass ()
    {
    }


    public SpadeClass (int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor)
    {
	setHeight (iNewHeight);
	setCentre (iNewCentreX, iNewCentreY);
	setColor (cNewColor);
    }


    public void draw (Graphics g)
    {
	int CentreX = getCentreX ();
	int CentreY = getCentreY ();
	int Width = getWidth ();
	int Height = getHeight ();
	Color color = getColor();
	// declare two arrays for X & Y coordinates of Diamond
	 int iPointsX[] = new int [5];
     int iPointsY[] = new int [5];

     iPointsX [0] = CentreX - Width / 2;
     iPointsY [0] =CentreY;
     iPointsX [1] = CentreX + Width / 2;
     iPointsY [1] = CentreY;
     iPointsX [2] =CentreX;
     iPointsY [2] = CentreY - Height / 2;
     iPointsX [3] =CentreX - Width / 2;
     iPointsY [3] = CentreY - Height / 4;
     iPointsX [4] = CentreX;
     iPointsY [4] = CentreY - Height / 4;

     int triPointsX[] = new int [3];
     int triPointsY[] = new int [3];

     triPointsX [0] = CentreX;
     triPointsY [0] = CentreY;
     triPointsX [1] = CentreX - Width / 8;
     triPointsY [1] = CentreY + Height / 2;
     triPointsX [2] = CentreX + Width / 8;
     triPointsY [2] = CentreY + Height / 2;

     g.setColor (color);
     g.fillArc (iPointsX [3], iPointsY [3], Width / 2, Height / 2, 180, 180);
     g.fillArc (iPointsX [4], iPointsY [4], Width / 2, Height / 2, 180, 180);
     g.fillPolygon (iPointsX, iPointsY, 3);
     g.fillPolygon (triPointsX, triPointsY, 3);
    }


    public void erase (Graphics g)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (g);
	setColor (cOldColor);
    }
}
