package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class diagonalMatrix extends sparseMatrix {
  double[] diagonal;

  public diagonalMatrix(double... diagonalValues) {
    assert(diagonalValues != null): "Podana tablica jest nullem.";
  
    int n = diagonalValues.length;  

    setMatrixShape(Shape.matrix(n, n));

    diagonal = Arrays.copyOf(diagonalValues, n);
  }  

  @Override
  public IDoubleMatrix times(double scalar) {
    int n = super.shape().rows;
    
    double[] result = Arrays.copyOf(diagonal, n);

    for (int i = 0; i < n; i++)
      result[i] *= scalar;

    return new diagonalMatrix(result);  
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    int n = super.shape().rows;
    
    double[] result = Arrays.copyOf(diagonal, n);

    for (int i = 0; i < n; i++)
      result[i] += scalar;

    return new diagonalMatrix(result);  
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    super.shape().assertInShape(row, column);

    return (row == column ? diagonal[row] : 0);
  }

  @Override
  public double normOne() {
    double result = 0;
    int n = super.shape().rows;

    for (int i = 0; i < n; i++)
      result = Math.max(result, Math.abs(diagonal[i]));

    return result;  
  }

  @Override
  public double normInfinity() {
    return normOne();
  }

  @Override
  public double frobeniusNorm() {
    double result = 0;

    for (double i : diagonal)
      result += i * i;

    return Math.sqrt(result);  
  }
    
  // public String toString();
}
