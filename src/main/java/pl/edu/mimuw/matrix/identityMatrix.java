package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class identityMatrix extends sparseMatrix {
  public identityMatrix(int size) {
    setMatrixShape(Shape.matrix(size, size));  
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    int size = super.shape().rows;  
    
    double[] diagonal = new double[size];

    Arrays.fill(diagonal, scalar);

    return new diagonalMatrix(diagonal);
  }
  
  @Override
  public IDoubleMatrix plus(double scalar) {
    int size = super.shape().rows;

    double[][] result = new double[size][size];

    for (int i = 0; i < size; i++)
      for (int j = 0; j < size; j++)
        result[i][j] = (i == j ? 1 + scalar : scalar);

    return new fullMatrix(result);    
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    super.shape().assertInShape(row, column);
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
    int size = super.shape().rows;
    return Math.sqrt(size);
  }

  // public String toString();    
}
