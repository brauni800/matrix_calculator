package app.controller;

import app.model.Matrix;

public class MatrixController {
  /**
   * Función para sumar dos matrices con las mismas dimensiones
   * @param matrix1
   * @param matrix2
   * @return Suma de 2 matrices
   * @throws Error Las matrices no son del mismo tamaño
   */
  public Matrix sum(Matrix matrix1, Matrix matrix2) throws Error {
    if (matrix1.getWidth() == matrix2.getWidth() && matrix1.getHeight() == matrix2.getHeight()) {
      double[][] result = new double[matrix1.getWidth()][matrix1.getHeight()];
      for (int coordX = 0; coordX < matrix1.getWidth(); coordX++) {
        for (int coordY = 0; coordY < matrix1.getHeight(); coordY++) {
          result[coordX][coordY] = matrix1.getData()[coordX][coordY] + matrix2.getData()[coordX][coordY];
        }
      }
      return new Matrix(result);
    } else throw new Error("The matrices are off different sizes");
  }

  /**
   * Multiplicacion de una matriz con un escalar
   * @param matrix
   * @param scalar
   * @return Matriz resultado de la multiplicación de una matriz con un escalar
   */
  public Matrix multiplicationByScalar(Matrix matrix, int scalar) {
    double[][] result = new double[matrix.getWidth()][matrix.getHeight()];
    for (int coordX = 0; coordX < matrix.getWidth(); coordX++) {
      for (int coordY = 0; coordY < matrix.getHeight(); coordY++) {
        result[coordX][coordY] = scalar * matrix.getData()[coordX][coordY];
      }
    }
    return new Matrix(result);
  }

  /**
   * Multiplicación de dos matrices con el numero de columnas de la matriz 1 igual al numero de filas de la matriz 2
   * @param matrix1 El número de columnas debe ser igual al número de filas de la otra matriz
   * @param matrix2 El número de filas debe ser igual al número de columnas de la otra matriz
   * @return Matriz resultado de la multiplicación de dos matrices
   * @throws Error El número de columnas de la matriz 1 no coincide con el número de filas de la matriz 2
   */
  public Matrix multiplicationOfTwoMatrices(Matrix matrix1, Matrix matrix2) throws Error {
    if (matrix1.getHeight() == matrix2.getWidth()) {
      double[][] result = new double[matrix1.getWidth()][matrix2.getHeight()];
      for (int x = 0; x < matrix1.getWidth(); x++) {
        for (int y = 0; y < matrix2.getHeight(); y++) {
          for (int z = 0; z < matrix1.getHeight(); z++) {
            result[x][y] += matrix1.getData()[x][z] * matrix2.getData()[z][y];
          }
        }
      }
      return new Matrix(result);
    } else throw new Error("The number of columns in the first matrix is different from the number of rows in the second matrix");
  }

  /**
   * Función para obtener la matriz inversa con el método de Gauss-Jordan de la matriz que se proporcione como parámetro
   * @param matrix Matriz la cual se obtendrá su matriz inversa
   * @return Matriz inversa
   */
  public Matrix getInverseByGaussJordan(Matrix matrix) {
    double[][] identity = new double[matrix.getWidth()][matrix.getHeight()];
    for (int i = 0; i < matrix.getWidth(); i++) {
      for (int j = 0; j < matrix.getHeight(); j++) {
        identity[i][j] = i == j ? 1 : 0;
      }
    }
    for (int y = 0; y < matrix.getHeight(); y++) {
      // matrix.getData()[y][y2] debe ser igual a 1
      double denominator = matrix.getData().clone()[y][y];
      for (int y2 = 0; y2 < matrix.getHeight(); y2++) {
        matrix.getData()[y][y2] /= denominator;
        identity[y][y2] /= denominator;
      }
      for (int x = 0; x < matrix.getWidth(); x++) {
        if(x != y) {
          // matrix.getData()[x][y] debe ser igual a 0
          double multiplicator = matrix.getData().clone()[x][y];
          for (int y2 = 0; y2 < matrix.getHeight(); y2++) {
            matrix.getData()[x][y2] += (-multiplicator) * matrix.getData()[y][y2];
            identity[x][y2] += (-multiplicator) * identity[y][y2];
          }
        }
      }
    }
    return new Matrix(identity);
  }

  /**
   * Resolución de un sistema de ecuaciones por método de Gauss-Jordan
   * @param matrix Matriz que representa los valores de un sistema de ecuaciones.
   * Cada fila representa una ecuación, las columnas de cada fila representa el valor de la variable independiente
   * @param independentValues Valores independientes de la matriz. Representan la igualdad de cada ecuación en la matriz.
   * La posición de los valores independientes deberán ser equivalente a la posición de la ecuación correspondiente.
   * Ejemplo: ecuacion1 = resultado1, ecuacion2 = resultado2 | matriz = { { ...ecuacion1 }, { ...ecuacion2 } }, valoresIndependientes = { resultado1, resultado2 }
   * @return Arreglo de valores que corresponden con su posición a cada variable del sistema de ecuaciones
   */
  public double[] getSystemOfEquations(Matrix matrix, double[] independentValues) {
    for (int y = 0; y < matrix.getHeight(); y++) {
      // matrix.getData()[y][y2] debe ser igual a 1
      double denominator = matrix.getData().clone()[y][y];
      independentValues[y] /= denominator;
      for (int y2 = 0; y2 < matrix.getHeight(); y2++) {
        matrix.getData()[y][y2] /= denominator;
      }
      for (int x = 0; x < matrix.getWidth(); x++) {
        if(x != y) {
          // matrix.getData()[x][y] debe ser igual a 0
          double multiplicator = matrix.getData().clone()[x][y];
          independentValues[x] += (-multiplicator) * independentValues[y];
          for (int y2 = 0; y2 < matrix.getHeight(); y2++) {
            matrix.getData()[x][y2] += (-multiplicator) * matrix.getData()[y][y2];
          }
        }
      }
    }
    return independentValues;
  }
}
