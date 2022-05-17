package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class DiagonalMatrix extends DoubleMatrix {
  private double[] diagonal;

  public DiagonalMatrix(double... diagonalValues) {
    assert(diagonalValues != null): "Invalid input.";
  
    int n = diagonalValues.length;  

    setShape(Shape.matrix(n, n));

    diagonal = Arrays.copyOf(diagonalValues, n);
  }  

  @Override
  public IDoubleMatrix times(double scalar) {
    int n = shape().rows;
    
    double[] result = Arrays.copyOf(diagonal, n);

    for (int i = 0; i < n; i++)
      result[i] *= scalar;

    return new DiagonalMatrix(result);  
  }

  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);

    return (row == column ? diagonal[row] : 0);
  }

  @Override
  public double normOne() {
    double result = 0;
    int n = shape().rows;

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
    
  @Override
  public String toString() {
    int n = shape().rows;

    String res = "Dimensions: " + n + " x " + n + "\n";

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++)
        if (i == j) {
          res += get(i, j) + " ";
        }
        else if (i - j >= 3) {
          res += "0.0 ... 0.0 ";
          j = i - 1;
        } 
        else if (j > i && n - j >= 3) {
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
