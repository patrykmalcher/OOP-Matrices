package pl.edu.mimuw.matrix;

public class fullMatrix extends sparseMatrix {
  double[][] matrix;

  public fullMatrix(double[][] values) {
    assert(values != null): "Podana tablica jest nullem.";  

    int n = values.length;

    assert(n > 0): "Dana tablica jest pusta.";
    
    for (int i = 0; i < values.length; i++)
      assert(values[i] != null && values[i].length > 0): "Element tablicy jest nullem lub jest pusty.";

    for (int i = 0; i + 1 < values.length; i++)
      assert(values[i].length == values[i + 1].length): "Tablica nie jest macierzą.";

    int m = values[0].length;

    setMatrixShape(Shape.matrix(n, m));  

    matrix = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        matrix[i][j] = values[i][j];
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    Shape shape = super.shape();

    int n = shape.rows;
    int m = shape.columns;

    double[][] result = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        result[i][j] = matrix[i][j] * scalar;
    
    return new fullMatrix(result);    
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    Shape shape = super.shape();

    int n = shape.rows;
    int m = shape.columns;

    double[][] result = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        result[i][j] = matrix[i][j] + scalar;
    
    return new fullMatrix(result);  
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    super.shape().assertInShape(row, column);

    return matrix[row][column];
  }

  @Override
  public double normOne() {
    double result = 0;

    int n = super.shape().rows;
    int m = super.shape().columns;

    for (int j = 0; j < m; j++) {
      double current = 0;

      for (int i = 0; i < n; i++)
        current += Math.abs(matrix[i][j]);

      result = Math.max(result, current);  
    }

    return result;
  }

  @Override
  public double normInfinity() {
    double result = 0;

    int n = super.shape().rows;
    int m = super.shape().columns;

    for (int i = 0; i < n; i++) {
      double current = 0;

      for (int j = 0; j < m; j++)
        current += Math.abs(matrix[i][j]);

      result = Math.max(result, current);  
    }

    return result;
  }

  @Override
  public double frobeniusNorm() {
    int n = super.shape().rows;
    int m = super.shape().columns;

    double result = 0;

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        result += matrix[i][j] * matrix[i][j];

    return Math.sqrt(result);    
  }  

  //public toString() {}
}
