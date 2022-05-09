package pl.edu.mimuw.matrix;

public abstract class sparseMatrix implements IDoubleMatrix {
  private Shape matrixShape;

  public void setMatrixShape(Shape matrixShape) {
    this.matrixShape = matrixShape;   
  }

  @Override
  public IDoubleMatrix times(IDoubleMatrix other) {
    Shape shapeOther = other.shape();

    assert(matrixShape.columns == shapeOther.rows): "Niepoprawne wymiary macierzy przy mnożeniu.";

    int a = matrixShape.rows;
    int b = matrixShape.columns;
    int c = matrixShape.columns;

    double[][] result = new double[a][c]; 

    for (int i = 0; i < a; i++)
      for (int j = 0; j < c; j++)
        for (int k = 0; k < b; k++)
          result[i][j] += get(i, k) * other.get(k, j);
    
    return new fullMatrix(result);
  }  

  @Override
  public IDoubleMatrix plus(IDoubleMatrix other) {
    assert matrixShape != other.shape(): "Niepoprawne wymiary macierzy przy dodawaniu.";

    int n = matrixShape.rows;
    int m = matrixShape.columns;

    double[][] result = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        result[i][j] = get(i, j) + other.get(i, j);

    return new fullMatrix(result);    
  } 

  @Override
  public IDoubleMatrix minus(IDoubleMatrix other) {
    return plus(other.times(-1));
  }  

  @Override
  public double[][] data() {
    int n = matrixShape.rows;
    int m = matrixShape.columns;

    double[][] result = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        result[i][j] = get(i, j);

    return result;    
  }

  @Override
  public Shape shape() {
    return matrixShape;
  }
}
