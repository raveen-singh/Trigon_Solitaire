// The DiamondClass Class
// Second in a series of demonstration programs for introducing Java

import hsa.Console;
import java.awt.*;

class DiamondClass extends SuitClass
{
    public DiamondClass ()
    {
    }


    public DiamondClass (int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor)
    {
	setHeight (iNewHeight);
	setCentre (iNewCentreX, iNewCentreY);
	setColor (cNewColor);
    }



    public void draw (Console c)
    {
	int CentreX = getCentreX ();
	int CentreY = getCentreY ();
	int Width = getWidth ();
	int Height = getHeight ();
	// declare two arrays for X & Y coordinates of Diamond
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	// calculate points on diamond & store in the arrays
	iPointsX [0] = CentreX - Width / 2;
	iPointsY [0] = CentreY;
	iPointsX [1] = CentreX;
	iPointsY [1] = CentreY - Height / 2;
	iPointsX [2] = CentreX + Width / 2;
	iPointsY [2] = CentreY;
	iPointsX [3] = CentreX;
	iPointsY [3] = CentreY + Height / 2;

	// draw the diamond using methods available from the Console object (c)
	c.setColor (getColor ());

	c.fillPolygon (iPointsX, iPointsY, 4);
    }


    public void erase (Console c)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (c);
	setColor (cOldColor);
    }


    public void draw (Graphics g)
    {
	int CentreX = getCentreX ();
	int CentreY = getCentreY ();
	int Width = getWidth ();
	int Height = getHeight ();
	// declare two arrays for X & Y coordinates of Diamond
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	// calculate points on diamond & store in the arrays
	iPointsX [0] = CentreX - Width / 2;
	iPointsY [0] = CentreY;
	iPointsX [1] = CentreX;
	iPointsY [1] = CentreY - Height / 2;
	iPointsX [2] = CentreX + Width / 2;
	iPointsY [2] = CentreY;
	iPointsX [3] = CentreX;
	iPointsY [3] = CentreY + Height / 2;

	// draw the diamond using methods available from the Console object (c)
	g.setColor (getColor ());

	g.fillPolygon (iPointsX, iPointsY, 4);

	g.drawPolygon (iPointsX, iPointsY, 4);


    }


    public void erase (Graphics g)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (g);
	setColor (cOldColor);
    }
}

