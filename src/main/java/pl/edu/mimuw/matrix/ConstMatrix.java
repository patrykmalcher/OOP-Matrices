package pl.edu.mimuw.matrix;

public class ConstMatrix extends DoubleMatrix {
  private double value;

  public ConstMatrix(Shape shape, double value) {
    setShape(Shape.matrix(shape.rows, shape.columns));  
    this.value = value;  
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    return new ConstMatrix(shape(), value * scalar);
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    return new ConstMatrix(shape(), value + scalar);
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override  
  public double get(int row, int column) {
    shape().assertInShape(row, column);
    return value;
  }

  @Override
  public double normOne() {
    return Math.abs(value) * shape().rows;
  }

  @Override
  public double normInfinity() {
    return Math.abs(value) * shape().columns;
  }

  @Override
  public double frobeniusNorm() {
    return Math.sqrt(value * value * shape().rows * shape().columns);
  }    

  @Override
  public String toString() {
    int n = shape().rows;
    int m = shape().columns;

    String res = "Dimensions: " + n + " x " + m + "\n";

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++)
        if (m - j >= 3) {
          res += value + " ... " + value;
          j = m;  
        }
        else {
          res += value + " ";
        }
      res += "\n";  
    }
    return res;
  }  
}
