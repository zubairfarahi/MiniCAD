package shape;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import cad.ShapeVisitor;

public class Polygon implements Shape {

  private List<Point> points;
  private Color outlineColor;
  private Color fillColor;

  public Polygon(final List<Point> points, final Color outlineColor, final Color fillColor) {
    this.points = points;
    this.outlineColor = outlineColor;
    this.fillColor = fillColor;
  }

  @Override
  public final void accept(final ShapeVisitor visitor) {
    visitor.visit(this);
  }

  public final List<Point> getPoints() {
    return points;
  }

  public final Color getOutlineColor() {
    return outlineColor;
  }

  public final Color getFillColor() {
    return fillColor;
  }

}
