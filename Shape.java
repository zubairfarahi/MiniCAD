package shape;

import cad.ShapeVisitor;

public interface Shape {

  void accept(ShapeVisitor visitor);

}
