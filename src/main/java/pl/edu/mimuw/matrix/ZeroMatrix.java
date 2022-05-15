package pl.edu.mimuw.matrix;

public class ZeroMatrix extends DoubleMatrix {
  public ZeroMatrix(Shape shape) {
    setMatrixShape(shape);  
  }

  @Override
  public IDoubleMatrix times(double scalar) {  
    return new ZeroMatrix(shape());
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    int n = shape().rows;
    int m = shape().columns;

    double[][] res = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        res[i][j] = scalar;

    return new FullMatrix(res);  
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);

    return 0;
  }

  @Override
  public double normOne() {
    return 0;
  }

  @Override
  public double normInfinity() {
    return 0;
  }

  @Override
  public double frobeniusNorm() {
    return 0;
  }

  //public String toString;  
}
