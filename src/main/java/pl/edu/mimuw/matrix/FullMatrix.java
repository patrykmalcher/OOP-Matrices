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

    setShape(Shape.matrix(n, m));  

    matrix = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        matrix[i][j] = values[i][j];
  }

  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);

    return matrix[row][column];
  }
}
