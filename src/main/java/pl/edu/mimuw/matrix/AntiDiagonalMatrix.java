package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class AntiDiagonalMatrix extends DoubleMatrix {
  private double[] antiDiagonal;

  public AntiDiagonalMatrix(double... antiDiagonalValues) {
    assert(antiDiagonalValues != null): "Invalid input.";

    int n = antiDiagonalValues.length;  

    setShape(Shape.matrix(n, n));

    antiDiagonal = Arrays.copyOf(antiDiagonalValues, n);
  }  

  @Override
  public IDoubleMatrix times(double scalar) {
    int n = shape().rows;
        
    double[] result = Arrays.copyOf(antiDiagonal, n);

    for (int i = 0; i < n; i++)
      result[i] *= scalar;

    return new AntiDiagonalMatrix(result);  
  }

  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);

    int n = shape().rows;

    return (row == n - column - 1 ? antiDiagonal[row] : 0);
  }

  @Override
  public double normOne() {
    double result = 0;
    int n = shape().rows;

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
        
  @Override
  public String toString() {
    int n = shape().rows;

    String res = "Dimensions: " + n + " x " + n + "\n";

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++)
        if (i == n - j - 1) {
          res += get(i, j) + " ";
        }
        else if (n - i - 1 - j >= 3) {
          res += "0.0 ... 0.0 ";
          j = n - i - 2;
        } 
        else if (j > n - i - 1 && n - j >= 3) {
          res += "0.0 ... 0.0 ";
          j = n;
        }
        else {
          res += "0.0 ";
        }
      res += "\n";  
    }   
    return res;
  }        
}
