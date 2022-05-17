package pl.edu.mimuw;

import pl.edu.mimuw.matrix.*;
public class Main {
  public static void main(String[] args) {
    // SPARSE MATRIX
    IDoubleMatrix matrix = DoubleMatrixFactory.sparse(Shape.matrix(10, 10), 
    MatrixCellValue.cell(0, 2, 1), MatrixCellValue.cell(5, 7, 3));

    System.out.println(matrix);

    // FULL MATRIX
    double[][] arr = new double[10][10];

    for (int i = 0; i < 10; i++)
      for (int j = 0; j < 10; j++)
        arr[i][j] = i;
      
    matrix = DoubleMatrixFactory.full(arr);
    System.out.println(matrix);

    // IDENTITY MATRIX
    matrix = DoubleMatrixFactory.identity(10);
    System.out.println(matrix);

    // DIAGONAL MATRIX
    matrix = DoubleMatrixFactory.diagonal(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(matrix);

    // ANTIDIAGONAL MATRIX
    matrix = DoubleMatrixFactory.antiDiagonal(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(matrix);

    // VECTOR MATRIX
    matrix = DoubleMatrixFactory.vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(matrix);

    // ZERO MATRIX
    matrix = DoubleMatrixFactory.zero(Shape.matrix(10, 10));
    System.out.println(matrix);

    // CONSTANT MATRIX
    matrix = DoubleMatrixFactory.constant(Shape.matrix(10, 10), 2);
    System.out.println(matrix);

    // ROW MATRIX
    matrix = DoubleMatrixFactory.row(Shape.matrix(10, 10), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(matrix);

    // COLUMN MATRIX
    matrix = DoubleMatrixFactory.column(Shape.matrix(10, 10), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(matrix);

    // OPERATIONS

    // MATRIX * MATRIX

    IDoubleMatrix matrix1 = DoubleMatrixFactory.sparse(Shape.matrix(2, 3), 
    MatrixCellValue.cell(0, 2, 1), MatrixCellValue.cell(1, 1, 3));

    IDoubleMatrix matrix2 = DoubleMatrixFactory.full(new double[][]{
      new double[]{1, 2},
      new double[]{3, 4},
      new double[]{5, 6}
    });

    System.out.println(matrix1 + "*\n" + matrix2 + "=\n" + matrix1.times(matrix2));

    // MATRIX * SCALAR

    matrix = DoubleMatrixFactory.identity(3);

    System.out.println(matrix + "* 3 =\n" + matrix.times(3));

    // MATRIX + MATRIX

    matrix1 = DoubleMatrixFactory.diagonal(1, 2, 3);
    matrix2 = DoubleMatrixFactory.antiDiagonal(1, 2, 3);

    System.out.println(matrix1 + "+\n" + matrix2 + "=\n" + matrix1.plus(matrix2));

    // MATRIX + SCALAR

    matrix = DoubleMatrixFactory.vector(1, 2, 3);

    System.out.println(matrix + "+ 3 =\n" + matrix.plus(3));

    // MATRIX - MATRIX

    matrix1 = DoubleMatrixFactory.zero(Shape.matrix(2, 3));
    matrix2 = DoubleMatrixFactory.constant(Shape.matrix(2, 3), 2);

    System.out.println(matrix1 + "-\n" + matrix2 + "=\n" + matrix1.minus(matrix2));

    // MATRIX - SCALAR

    matrix = DoubleMatrixFactory.row(Shape.matrix(2, 2), 1, 2);

    System.out.println(matrix + "- 5 =\n" + matrix.minus(5));
  }
}
