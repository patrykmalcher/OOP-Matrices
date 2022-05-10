package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class zeroMatrix extends sparseMatrix {
  public zeroMatrix(Shape shape) {
    setMatrixShape(shape);  
  }

  @Override
  public IDoubleMatrix times(double scalar) {  
    return new zeroMatrix(shape());
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    int n = shape().rows;
    int m = shape().columns;

    double[][] res = new double[n][m];

    for (int i = 0; i < n; i++)
      Arrays.fill(res[i], scalar);

    return new fullMatrix(res);  
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
