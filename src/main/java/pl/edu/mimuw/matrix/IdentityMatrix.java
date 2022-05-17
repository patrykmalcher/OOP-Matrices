package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class IdentityMatrix extends DoubleMatrix {
  public IdentityMatrix(int size) {
    setShape(Shape.matrix(size, size));  
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    int size = shape().rows;  
    
    double[] diagonal = new double[size];

    Arrays.fill(diagonal, scalar);

    return new DiagonalMatrix(diagonal);
  }
  
  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);
    return (row == column ? 1 : 0);
  }

  @Override
  public double normOne() {
    return 1;
  }

  @Override
  public double normInfinity() {
    return 1;
  }

  @Override
  public double frobeniusNorm() {
    int size = shape().rows;
    return Math.sqrt(size);
  }

  @Override
  public String toString() {
    int n = shape().rows;

    String res = "Dimensions: " + n + " x " + n + "\n";

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++)
        if (i == j) {
          res += "1.0 ";
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
