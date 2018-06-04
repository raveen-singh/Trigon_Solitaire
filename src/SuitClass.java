import hsa.Console;
import java.awt.*;

public abstract class SuitClass extends ShapeClass
{
    public void setWidth (int nWidth)
    {
	super.setHeight ((int) (Math.round (nWidth / 0.70)));
	super.setWidth (nWidth);
    }


    public void setHeight (int nHeight)
    {
	super.setHeight (nHeight);
	super.setWidth ((int) (Math.round (nHeight * 0.70)));
    }
}
