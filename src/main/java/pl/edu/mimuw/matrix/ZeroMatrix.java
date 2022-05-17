package pl.edu.mimuw.matrix;

public class ZeroMatrix extends ConstMatrix {
  public ZeroMatrix(Shape shape) {
    super(shape, 0); 
  }

  @Override
  public IDoubleMatrix times(IDoubleMatrix other) {
    Shape shapeOther = other.shape();

    assert(shape().columns == shapeOther.rows): "Invalid dimensions.";

    int a = shape().rows;
    int b = shapeOther.columns; 

    return new ZeroMatrix(Shape.matrix(a, b));
  }  
}
