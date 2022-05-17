package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class VectorMatrix extends DoubleMatrix {
  private double[] vector;
  
  public VectorMatrix(double... values) {
    assert(values != null): "Invalid input.";
  
    int n = values.length;  

    setShape(Shape.matrix(n, 1));

    vector = Arrays.copyOf(values, n);
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    int n = shape().rows;

    double[] result = Arrays.copyOf(vector, n);

    for (int i = 0; i < n; i++)
      result[i] *= scalar;

    return new VectorMatrix(result);  
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    int n = shape().rows;

    double[] result = Arrays.copyOf(vector, n);

    for (int i = 0; i < n; i++)
      result[i] += scalar;

    return new VectorMatrix(result);     
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);

    return vector[row];
  }
}
