package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class RowMatrix extends DoubleMatrix {
  private double[] values;
  
  public RowMatrix(Shape shape, double... values) {
    setShape(Shape.matrix(shape.rows, shape.columns));  
    this.values = Arrays.copyOf(values, values.length);  
  }
 
  @Override
  public IDoubleMatrix times(double scalar) {
    double[] res = new double[values.length];

    for (int i = 0; i < values.length; i++)
      res[i] = values[i] * scalar;

    return new RowMatrix(shape(), res);  
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    double[] res = new double[values.length];

    for (int i = 0; i < values.length; i++)
      res[i] = values[i] + scalar;

    return new RowMatrix(shape(), res);  
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);

    return values[row];
  }

  @Override
  public double normOne() {
    double res = 0;

    for (double i: values)
      res += Math.abs(i);

    return res;  
  }

  @Override
  public double normInfinity() {
    double res = 0;

    for (double i: values)
      res = Math.max(res, Math.abs(i));

    return res * shape().columns;
  }

  @Override
  public double frobeniusNorm() {
    double res = 0;

    for (double i: values)
      res += i * i * shape().columns;

    return Math.sqrt(res);  
  }  

  @Override
  public String toString() {
    int n = shape().rows;
    int m = shape().columns;

    String res = "Dimensions: " + n + " x " + m + "\n";

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++)
        if (m - j >= 3) {
          res += get(i, j) + " ... " + get(i, j);
          j = m;  
        }
        else {
          res += get(i, j) + " ";  
        }
      res += "\n";  
    }
    return res;
  } 
}
