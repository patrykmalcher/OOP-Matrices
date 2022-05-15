package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class AntiDiagonalMatrix extends DoubleMatrix {
  double[] antiDiagonal;

  public AntiDiagonalMatrix(double... antiDiagonalValues) {
    assert(antiDiagonalValues != null): "Podana tablica jest nullem.";

    int n = antiDiagonalValues.length;  

    setMatrixShape(Shape.matrix(n, n));

    antiDiagonal = Arrays.copyOf(antiDiagonalValues, n);
  }  

  @Override
  public IDoubleMatrix times(double scalar) {
    int n = super.shape().rows;
        
    double[] result = Arrays.copyOf(antiDiagonal, n);

    for (int i = 0; i < n; i++)
      result[i] *= scalar;

    return new AntiDiagonalMatrix(result);  
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    int n = super.shape().rows;
        
    double[] result = Arrays.copyOf(antiDiagonal, n);

    for (int i = 0; i < n; i++)
      result[i] += scalar;

    return new AntiDiagonalMatrix(result);  
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    super.shape().assertInShape(row, column);

    int n = shape().rows;

    return (row == n - column ? antiDiagonal[row] : 0);
  }

  @Override
  public double normOne() {
    double result = 0;
    int n = super.shape().rows;

    for (int i = 0; i < n; i++)
      result = Math.max(result, Math.abs(antiDiagonal[i]));

    return result;  
  }

  @Override
  public double normInfinity() {
    return normOne();
  }

  @Override
  public double frobeniusNorm() {
    double result = 0;

    for (double i : antiDiagonal)
      result += i * i;

    return Math.sqrt(result);  
  }
        
  // public String toString();       
}
