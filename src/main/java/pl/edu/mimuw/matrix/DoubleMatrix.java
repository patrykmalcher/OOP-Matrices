package pl.edu.mimuw.matrix;

public abstract class DoubleMatrix implements IDoubleMatrix {
  private Shape matrixShape;

  // Setter instead of constructor due to assertions.
  public void setMatrixShape(Shape matrixShape) {
    this.matrixShape = matrixShape;   
  }

  @Override
  public IDoubleMatrix times(IDoubleMatrix other) {
    Shape shapeOther = other.shape();

    assert(matrixShape.columns == shapeOther.rows): "Invalid dimensions.";

    int a = matrixShape.rows;
    int b = matrixShape.columns;
    int c = shapeOther.columns;

    double[][] result = new double[a][c]; 

    for (int i = 0; i < a; i++)
      for (int j = 0; j < c; j++)
        for (int k = 0; k < b; k++)
          result[i][j] += get(i, k) * other.get(k, j);
    
    return new FullMatrix(result);
  }  

  @Override
  public IDoubleMatrix plus(IDoubleMatrix other) {
    assert shape().equals(other.shape()): "Invalid dimensions.";

    int n = shape().rows;
    int m = shape().columns;

    double[][] result = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        result[i][j] = get(i, j) + other.get(i, j);

    return new FullMatrix(result);    
  } 

  @Override
  public IDoubleMatrix minus(IDoubleMatrix other) {
    return plus(other.times(-1));
  }  

  @Override
  public double[][] data() {
    int n = shape().rows;
    int m = shape().columns;

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
