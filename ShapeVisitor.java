package cad;

import shape.Circle;
import shape.Diamond;
import shape.Line;
import shape.Polygon;
import shape.Rectangle;
import shape.Square;
import shape.Triangle;

/**
 * Visitor pattern - this helps decouple and encapsulate functionality being
 * performed on shapes.
 */

public interface ShapeVisitor {

  void visit(Circle circle);

  void visit(Diamond diamond);

  void visit(Line line);

  void visit(Polygon polygon);

  void visit(Rectangle rectangle);

  void visit(Square square);

  void visit(Triangle triangle);

  void visit(Canvas canvas);

}
