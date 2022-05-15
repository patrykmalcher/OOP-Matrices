package pl.edu.mimuw.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SparseMatrix extends DoubleMatrix {
  protected ArrayList<MatrixCellValue> list;
  
  public SparseMatrix(Shape shape, MatrixCellValue... values) {
    setMatrixShape(shape);

    MatrixCellValue[] copyOfValues = Arrays.copyOf(values, values.length);  
    list = new ArrayList<MatrixCellValue>();

    for (var i: copyOfValues)
      list.add(i);

    Collections.sort(list, new Comparator<MatrixCellValue>() {
      public int compare(final MatrixCellValue x, MatrixCellValue y) {
        if (x.row == y.row)
          return x.column - y.column;
        return x.row - y.row;  
      }
    });

    MatrixCellValue curr = MatrixCellValue.cell(-1, -1, 0);

    for (MatrixCellValue i: list) {
      assert(i.row != curr.row || i.column != i.row): "Błedne dane.";
      shape().assertInShape(i.row, i.column);
    }
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    var result = new ArrayList<MatrixCellValue>();

    for (var i: list)
      result.add(MatrixCellValue.cell(i.row, i.column, i.value * scalar));

    return new SparseMatrix(shape(), result.toArray(new MatrixCellValue[0]));    
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    double[][] res = new double[shape().rows][shape().columns];

    for (var i: list)
      res[i.row][i.column] = i.value;

    for (int i = 0; i < shape().rows; i++)
      for (int j = 0; j < shape().columns; j++)
        res[i][j] += scalar;

    return new FullMatrix(res);    
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    shape().assertInShape(row, column);

    for (var i: list)
      if (i.row == row && i.column == column)
        return i.value;

    return 0;      
  }

  @Override
  public double normOne() {
    Map<Integer, Double> M = new HashMap<Integer, Double>();

    for (var i: list) {
      Double x = M.get(i.column);

      if (x == null) {
        M.put(i.column, Math.abs(i.value));
      }
      else {
        M.remove(i.column);
        M.put(i.column, x + Math.abs(i.value));
      }
    }  

    double res = 0;
    
    for (var i: M.entrySet())
      res = Math.max(res, i.getValue());

    return res;    
  }

  @Override
  public double normInfinity() {
    Map<Integer, Double> M = new HashMap<Integer, Double>();

    for (var i: list) {
      Double x = M.get(i.row);

      if (x == null) {
        M.put(i.row, Math.abs(i.value));
      }
      else {
        M.remove(i.row);
        M.put(i.row, x + Math.abs(i.value));
      }
    }  

    double res = 0;
    
    for (var i: M.entrySet())
      res = Math.max(res, i.getValue());

    return res;  
  }

  @Override
  public double frobeniusNorm() {
    double res = 0;

    for (var i: list)
      res += i.value * i.value;

    return Math.sqrt(res);    
  }

  @Override
  public IDoubleMatrix plus(IDoubleMatrix other) { // Optymalizacja: sparse + sparse.
    assert shape().equals(other.shape()): "Niepoprawne wymiary macierzy przy dodawaniu.";

    if (getClass() != other.getClass())
      return super.plus(other);

    SparseMatrix otherAsSparse = (SparseMatrix) other;  

    var res = new ArrayList<MatrixCellValue>();

    int i = 0, j = 0;

    while (i < list.size() || j < otherAsSparse.list.size()) {
      if (i == list.size()) {
        res.add(otherAsSparse.list.get(j));
        j ++;
      }
      else if (j == otherAsSparse.list.size()) {
        res.add(list.get(i));
        i ++;
      }
      else {
        var c1 = list.get(i);
        var c2 = otherAsSparse.list.get(j);
        
        if (c1.row == c2.row && c1.column == c2.column) {
          res.add(MatrixCellValue.cell(c1.row, c1.column, c1.value + c2.value));
          i ++;
          j ++;
        }
        else if (c1.row < c2.row || (c1.row == c2.row && c1.column < c2.column)) {
          res.add(c1);
          i ++; 
        } 
        else {
          res.add(c2);
          j ++;
        }
      }
    }    

    return new SparseMatrix(shape(), res.toArray(new MatrixCellValue[0]));
  }  
  
  @Override
  public IDoubleMatrix minus(IDoubleMatrix other) { // Optymalizacja: sparse - sparse.
    if (getClass() != other.getClass())
      return super.minus(other);

    return plus((SparseMatrix) other.times(-1));  
  }    

  @Override
  public IDoubleMatrix times(IDoubleMatrix other) { // Optymalizacja: sparse * sparse.
    Shape shapeOther = other.shape();

    assert(shape().columns == shapeOther.rows): "Niepoprawne wymiary macierzy przy mnożeniu.";

    if (getClass() != other.getClass())
      return super.times(other);

    SparseMatrix otherAsSparse = (SparseMatrix) other;  

    var res = new ArrayList<MatrixCellValue>();

    for (var i: list)
      for (var j: otherAsSparse.list)
        if (i.column == j.row) {
          boolean isAdded = false;

          for (int k = 0; k < res.size(); k++)
            if (res.get(k).row == i.row && res.get(k).column == j.column) {
              res.set(k, MatrixCellValue.cell(i.row, j.column, res.get(k).value + i.value * j.value));
              isAdded = true;
            }
          
          if (!isAdded)
            res.add(MatrixCellValue.cell(i.row, j.column, i.value * j.value));  
        }
    
    return new SparseMatrix(Shape.matrix(shape().rows, other.shape().columns), res.toArray(new MatrixCellValue[0]));
  }
  
  @Override
  public String toString() {
    int n = shape().rows;
    int m = shape().columns;

    String res = "Rozmiar macierzy: " + n + " x " + m + "\n";

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++)
        res += get(i, j) + " ";
      res += "\n";  
    }
    return res;
  }  
}   