// The DiamondClass Class
// Second in a series of demonstration programs for introducing Java

import hsa.Console;
import java.awt.*;

class HeartClass extends SuitClass
{
    public HeartClass ()
    {
	setColor (Color.red);
    }




    public HeartClass (int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor)
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
	
	 int iPointsX[] = new int [5];
     int iPointsY[] = new int [5];

     iPointsX [0] = CentreX - Width / 2;
     iPointsY [0] = CentreY;
     iPointsX [1] = CentreX + Width / 2;
     iPointsY [1] = CentreY;
     iPointsX [2] = CentreX;
     iPointsY [2] = CentreY + Height / 2;
     iPointsX [3] = CentreX - Width / 2;
     iPointsY [3] = CentreY - Height / 4;
     iPointsX [4] = CentreX;
     iPointsY [4] = CentreY - Height / 4;

	// draw the diamond using methods available from the Console object (c)
	g.setColor (getColor ());
	g.fillArc (iPointsX [3], iPointsY [3], Width / 2, Height / 2, 0, 180);
	g.fillArc (iPointsX [4], iPointsY [4], Width / 2, Height / 2, 0, 180);
	g.fillPolygon (iPointsX, iPointsY, 3);

    }


    public void erase (Graphics g)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (g);
	setColor (cOldColor);
    }
}
