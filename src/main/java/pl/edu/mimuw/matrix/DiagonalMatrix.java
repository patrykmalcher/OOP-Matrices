package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class DiagonalMatrix extends DoubleMatrix {
  double[] diagonal;

  public DiagonalMatrix(double... diagonalValues) {
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

    return new DiagonalMatrix(result);  
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    int n = super.shape().rows;
    
    double[] result = Arrays.copyOf(diagonal, n);

    for (int i = 0; i < n; i++)
      result[i] += scalar;

    return new DiagonalMatrix(result);  
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
    
  @Override
  public String toString() {
    int n = shape().rows;

    String res = "Rozmiar macierzy: " + n + " x " + n + "\n";

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
