package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class VectorMatrix extends DoubleMatrix {
  double[] vector;
  
  public VectorMatrix(double... values) {
    assert(values != null): "Podana tablica jest nullem.";
  
    int n = values.length;  

    setMatrixShape(Shape.matrix(n, 1));

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

  @Override
  public double normOne() {
    double res = 0;
    int n = shape().rows;

    for (int i = 0; i < n; i++)
      res += Math.abs(vector[i]);

    return res; 
  }

  @Override
  public double normInfinity() {
    double res = 0;
    int n = shape().rows;

    for (int i = 0; i < n; i++)
      res = Math.max(res, Math.abs(vector[i]));
    
    return res; 
  }

  @Override
  public double frobeniusNorm() {
    double res = 0;
    int n = shape().rows;

    for (int i = 0; i < n; i++)
      res += vector[i] * vector[i];

    return Math.sqrt(res);  
  }
    
  @Override
  public String toString() {
    int n = shape().rows;
    int m = shape().columns;

    String res = "Rozmiar macierzy: " + n + " x " + m + "\n";

    for (var i: vector)
      res += i + "\n";
      
    return res;
  }  
}
