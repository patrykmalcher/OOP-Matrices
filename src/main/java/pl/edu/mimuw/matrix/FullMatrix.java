package pl.edu.mimuw.matrix;

public class FullMatrix extends DoubleMatrix {
  private double[][] matrix;

  public FullMatrix(double[][] values) {
    assert(values != null): "Invalid input.";  

    int n = values.length;

    assert(n > 0): "Invalid input.";
    
    for (int i = 0; i < values.length; i++)
      assert(values[i] != null && values[i].length > 0): "Invalid input.";

    for (int i = 0; i + 1 < values.length; i++)
      assert(values[i].length == values[i + 1].length): "Invalid input.";

    int m = values[0].length;

    setMatrixShape(Shape.matrix(n, m));  

    matrix = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        matrix[i][j] = values[i][j];
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    Shape shape = shape();

    int n = shape.rows;
    int m = shape.columns;

    double[][] result = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        result[i][j] = matrix[i][j] * scalar;
    
    return new FullMatrix(result);    
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    Shape shape = shape();

    int n = shape.rows;
    int m = shape.columns;

    double[][] result = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        result[i][j] = matrix[i][j] + scalar;
    
    return new FullMatrix(result);  
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);

    return matrix[row][column];
  }

  @Override
  public double normOne() {
    double result = 0;

    int n = shape().rows;
    int m = shape().columns;

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

    int n = shape().rows;
    int m = shape().columns;

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
    int n = shape().rows;
    int m = shape().columns;

    double result = 0;

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        result += matrix[i][j] * matrix[i][j];

    return Math.sqrt(result);    
  }

  @Override
  public String toString() {
    int n = shape().rows;
    int m = shape().columns;

    String res = "Dimensions: " + n + " x " + m + "\n";

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++)
        res += get(i, j) + " ";
      res += "\n";  
    }
    return res;
  }  
}
