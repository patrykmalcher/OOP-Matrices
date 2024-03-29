package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class ColumnMatrix extends DoubleMatrix {
  private double[] values;
  
  public ColumnMatrix(Shape shape, double... values) {
    setShape(Shape.matrix(shape.rows, shape.columns));  
    this.values = Arrays.copyOf(values, values.length);  
  }
 
  @Override
  public IDoubleMatrix times(double scalar) {
    double[] res = new double[values.length];

    for (int i = 0; i < values.length; i++)
      res[i] = values[i] * scalar;

    return new ColumnMatrix(shape(), res);  
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    double[] res = new double[values.length];

    for (int i = 0; i < values.length; i++)
      res[i] = values[i] + scalar;

    return new ColumnMatrix(shape(), res);  
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);

    return values[column];
  }

  @Override
  public double normOne() {
    double res = 0;

    for (double i: values)
      res = Math.max(res, Math.abs(i));

    return res * shape().rows;
  }

  @Override
  public double normInfinity() {
    double res = 0;

    for (double i: values)
      res += Math.abs(i);

    return res; 
  }

  @Override
  public double frobeniusNorm() {
    double res = 0;

    for (double i: values)
      res += i * i * shape().rows;

    return Math.sqrt(res);  
  } 
}
