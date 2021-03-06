package figures;

import java.util.logging.Logger;

import figures.enums.LineType;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import utils.ColorFactory;

/**
 * Ellipse Figure containing a {@link javafx.scene.shape.Ellipse} as its
 * {@link Figure#shape}
 * @warning Since This class is also named "Ellipse", you'll need to use
 * (javafx.scene.shape.Ellipse) each time you need to acces to internal
 * {@link Figure#shape} casted as a {@link javafx.scene.shape.Ellipse}
 * @implSpec It is assumed that {@link Figure#shape} will always be non null
 * during the life cycle of a Ellipse.
 * @author davidroussel
 */
public class Ellipse extends Figure
{
	/**
	 * Instances counter (to be used in {@link Figure#instanceNumber}) of each
	 * Ellipse.
	 * @implNote No need to decrease {@link Figure#instanceNumber} in
	 * {@link #finalize()}
	 */
	private static int counter = 0;

	/**
	 * Valued constructor to build a zero size Ellipse at point (x, y).
	 * Used during Ellipse construction with {@link MouseEvent}s
	 * Calls super-constructor, sets {@link Figure#instanceNumber} then
	 * {@link #createShape(double, double)} and attach {@link Figure#shape} to
	 * {@link Figure#root}.
	 * @param fillColor the fill color (or null if there is no fill color).
	 * The fill color set in this Ellipse shall be set from {@link ColorFactory}.
	 * @param edgeColor the edge color (or null if there is no edge color)
	 * The edge color set in this Ellipse shall be set from {@link ColorFactory}.
	 * @param lineType line type (Either {@link LineType#SOLID},
	 * {@link LineType#DASHED} or {@link LineType#NONE}). If there is no edge
	 * color provided the internal {@link #lineType} shall be set to
	 * {@link LineType#NONE}
	 * @param lineWidth line width of this Ellipse. If there is no edge
	 * color provided the internal {@link #lineType} shall be set to 0
	 * @param parentLogger a parent logger used to initialize the current logger
	 * @param x the initial x coordinate in the drawing panel where to create this Ellipse
	 * @param y the initial y coordinate in the drawing panel where to create this Ellipse
	 * @throws IllegalStateException if we try to set both fillColor and
	 * edgecolor as nulls
	 */
	public Ellipse(Color fillColor,
	              Color edgeColor,
	              LineType lineType,
	              double lineWidth,
	              Logger parentLogger,
	              double x,
	              double y)
	    throws IllegalStateException
	{
		super(fillColor, edgeColor, lineType, lineWidth, parentLogger);
		instanceNumber = counter++;
		createShape(x, y);
		root.getChildren().add(shape);
	}

	/**
	 * Valued constructor to build a Ellipse at point (x, y) with specified xradius and yradius
	 * Calls super-constructor, sets {@link Figure#instanceNumber} then
	 * {@link #createShape(double, double)} and attach {@link Figure#shape} to
	 * {@link Figure#root}.
	 * @param fillColor the fill color (or null if there is no fill color).
	 * The fill color set in this Ellipse shall be set from {@link ColorFactory}.
	 * @param edgeColor the edge color (or null if there is no edge color)
	 * The edge color set in this Ellipse shall be set from {@link ColorFactory}.
	 * @param lineType line type (Either {@link LineType#SOLID},
	 * {@link LineType#DASHED} or {@link LineType#NONE}). If there is no edge
	 * color provided the internal {@link #lineType} shall be set to
	 * {@link LineType#NONE}
	 * @param lineWidth line width of this Ellipse. If there is no edge
	 * color provided the internal {@link #lineType} shall be set to 0
	 * @param parentLogger a parent logger used to initialize the current logger
	 * @param x the initial x coordinate in the drawing panel where to create this Ellipse
	 * @param y the initial y coordinate in the drawing panel where to create this Ellipse
	 * @param xradius the initial xradius of the Ellipse
	 * @param yradius the initial yradius of the Ellipse
	 * @throws IllegalStateException if we try to set both fillColor and
	 * edgecolor as nulls
	 */
	public Ellipse(Color fillColor,
	              Color edgeColor,
	              LineType lineType,
	              double lineWidth,
	              Logger parentLogger,
	              double x,
	              double y,
	              double xradius,
	              double yradius)
	    throws IllegalStateException
	{
		this(fillColor, edgeColor, lineType, lineWidth, parentLogger, x, y);
		javafx.scene.shape.Ellipse Ellipse = (javafx.scene.shape.Ellipse) shape;
		Ellipse.setRadiusX(Math.abs(xradius));
		Ellipse.setRadiusY(Math.abs(yradius));
	}

	/**
	 * Copy constructor
	 * @param figure the figure to be copied
	 * @throws IllegalArgumentException if the provided figure is not a Ellipse
	 */
	public Ellipse(Figure figure) throws IllegalArgumentException
	{
		super(figure);
		if (!(figure instanceof Ellipse))
		{
			String message = "provided figure is not a Ellipse: "
			    + figure.getClass().getSimpleName();
			logger.severe(message);
			throw new IllegalArgumentException(message);
		}
		javafx.scene.shape.Ellipse figureEllipse = (javafx.scene.shape.Ellipse) figure.shape;
		shape = new javafx.scene.shape.Ellipse(figureEllipse.getCenterX(),
		                                      figureEllipse.getCenterY(),
		                                      figureEllipse.getRadiusX(),
		                                      figureEllipse.getRadiusY());
		root.getChildren().add(shape);
		applyParameters(shape);
		setSelected(figure.selected);
	}

	/**
	 * Convenience method to get internal {@link Figure#shape} casted as a
	 * {@link javafx.scene.shape.Ellipse}
	 * @return the internal {@link Figure#shape} casted as a
	 * {@link javafx.scene.shape.Ellipse}
	 */
	private javafx.scene.shape.Ellipse getEllipseShape()
	{
		return (javafx.scene.shape.Ellipse)shape;
	}

	/**
	 * Center Point of this figure
	 * @return the center point of this figure
	 */
	@Override
	public Point2D getCenter()
	{
		javafx.scene.shape.Ellipse shapeEllipse = getEllipseShape();
		return new Point2D(shapeEllipse.getCenterX(), shapeEllipse.getCenterY());
	}

	/**
	 * Width of this figure
	 * @return the width of this figure
	 */
	@Override
	public double width()
	{
		return getEllipseShape().getRadiusX() * 2.0;
	}

	/**
	 * Height of this figure
	 * @return the width of this figure
	 */
	@Override
	public double height()
	{
		return getEllipseShape().getRadiusY() * 2.0;
	}

	/**
	 * Top left corner of this figure
	 * @return the top left {@link Point2D} of this figure
	 */
	@Override
	public Point2D topLeft()
	{
		double xradius = getEllipseShape().getRadiusX();
		double yradius = getEllipseShape().getRadiusY();
		Point2D center = getCenter();

		return new Point2D(center.getX() - xradius,
		                   center.getY() - yradius);
	}

	/**
	 * Bottom right corner of this figure
	 * @return the bottom right {@link Point2D} of this figure
	 */
	@Override
	public Point2D bottomRight()
	{
		double xradius = getEllipseShape().getRadiusX();
		double yradius = getEllipseShape().getRadiusY();
		Point2D center = getCenter();

		return new Point2D(center.getX() + xradius,
		                   center.getY() + yradius);
	}

	/**
	 * radius accessor of this Ellipse
	 * @return the xradius of this Ellipse
	 */
	public double getRadiusX()
	{
		return getEllipseShape().getRadiusX();
	}
	
	/**
	 * radius accessor of this Ellipse
	 * @return the yradius of this Ellipse
	 */
	public double getRadiusY()
	{
		return getEllipseShape().getRadiusY();
	}

	/**
	 * Creates actual {@link #shape} at specified position and apply
	 * parameters
	 * @param x the x coordinate of the initial point where to create the new shape
	 * @param y the y coordinate of the initial point where to create the new shape
	 * @post a new {@link #shape} has been created with a new
	 * {@link #instanceNumber} with {@link #fillColor}, {@link #edgeColor},
	 * {@link #lineType} & {@link #lineWidth} applied with
	 * {@link #applyParameters(Shape)}
	 */
	@Override
	public void createShape(double x, double y)
	{
		/*
		 * Note: since This class is also named Ellipse we need to explicitly
		 * use "new javafx.scene.shape.Ellipse(...)" here
		 */
		shape = new javafx.scene.shape.Ellipse(x, y, 0.0, 0.0);
		applyParameters(shape);
	}


	/**
	 * Sets the last point of this figure.
	 * Sets the xradius and yradius of this Ellipse based on the distance between center and
	 * the provided point
	 * @param lastPoint the point used to set this Ellipse's xradius and yradius
	 */
	@Override
	public void setLastPoint(Point2D lastPoint)
	{
		double distance = getCenter().distance(lastPoint);
		getEllipseShape().setRadiusX(1.618*distance);
		getEllipseShape().setRadiusY(distance);
	}

	/**
	 * Creates a copy of this Ellipse (with the same name and instance number)
	 * @return A distinct copy of this Ellipse
	 */
	@Override
	public Figure clone()
	{
		return new Ellipse(this);
	}

	/**
	 * Compare this Ellipse to another figure
	 * @return true if the other figure is also a Ellipse with the same
	 * position and size (with {@link Figure#threshold}), false otherwise.
	 * Other parameters, such as {@link Figure#fillColor},
	 * {@link Figure#edgeColor}, {@link Figure#lineType},
	 * {@link Figure#lineWidth}, and transformations
	 * are checked in {@link Figure#equals(Object)}
	 */
	@Override
	protected boolean equals(Figure figure)
	{
		if (!(figure instanceof Ellipse))
		{
			return false;
		}

		Ellipse Ellipse = (Ellipse) figure;

		if (Math.abs(getCenter().distance(Ellipse.getCenter())) > Figure.threshold)
		{
			return false;
		}

		if (Math.abs(getRadiusX() - Ellipse.getRadiusX()) > Figure.threshold)
		{
			return false;
		}
		
		if (Math.abs(getRadiusY() - Ellipse.getRadiusY()) > Figure.threshold)
		{
			return false;
		}

		return true;
	}
}
