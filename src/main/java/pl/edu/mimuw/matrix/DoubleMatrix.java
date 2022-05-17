package pl.edu.mimuw.matrix;

public abstract class DoubleMatrix implements IDoubleMatrix {
  private Shape shape;

  // Setter instead of constructor due to assertions.
  public void setShape(Shape shape) {
    this.shape = shape;   
  }

  @Override
  public IDoubleMatrix times(IDoubleMatrix other) {
    Shape shapeOther = other.shape();

    assert(shape().columns == shapeOther.rows): "Invalid dimensions.";

    int a = shape().rows;
    int b = shape().columns;
    int c = shapeOther.columns;

    double[][] result = new double[a][c]; 

    for (int i = 0; i < a; i++)
      for (int j = 0; j < c; j++)
        for (int k = 0; k < b; k++)
          result[i][j] += get(i, k) * other.get(k, j);
    
    return new FullMatrix(result);
  }  

  @Override
  public IDoubleMatrix times(double scalar) {
    int n = shape().rows;
    int m = shape().columns;

    double[][] res = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        res[i][j] = scalar * get(i, j);

    return new FullMatrix(res);    
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
  public IDoubleMatrix plus(double scalar) {
    int n = shape().rows;
    int m = shape().columns;

    double[][] res = new double[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        res[i][j] = scalar + get(i, j);

    return new FullMatrix(res);    
  }

  @Override
  public IDoubleMatrix minus(IDoubleMatrix other) {
    return plus(other.times(-1));
  }  

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override 
  public abstract double get(int row, int column);

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
  public double normOne() {
    double result = 0;

    int n = shape().rows;
    int m = shape().columns;

    for (int j = 0; j < m; j++) {
      double current = 0;

      for (int i = 0; i < n; i++)
        current += Math.abs(get(i, j));

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
        current += Math.abs(get(i, j));

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
        result += get(i, j) * get(i, j);

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

  @Override
  public Shape shape() {
    return shape;
  }
}
