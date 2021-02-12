package app.controller;

import app.model.Matrix;

public class MatrixController {
  public Matrix sum(Matrix matrix1, Matrix matrix2) throws Error {
    if (matrix1.getWidth() == matrix2.getWidth() && matrix1.getHeight() == matrix2.getHeight()) {
      float[][] result = new float[matrix1.getWidth()][matrix1.getHeight()];
      for (int coordX = 0; coordX < matrix1.getWidth(); coordX++) {
        for (int coordY = 0; coordY < matrix1.getHeight(); coordY++) {
          result[coordX][coordY] = matrix1.getData()[coordX][coordY] + matrix2.getData()[coordX][coordY];
        }
      }
      return new Matrix(result);
    } else throw new Error("The matrices are off different sizes");
  }

  public Matrix multiplicationByScalar(Matrix matrix, int scalar) {
    float[][] result = new float[matrix.getWidth()][matrix.getHeight()];
    for (int coordX = 0; coordX < matrix.getWidth(); coordX++) {
      for (int coordY = 0; coordY < matrix.getHeight(); coordY++) {
        result[coordX][coordY] = scalar * matrix.getData()[coordX][coordY];
      }
    }
    return new Matrix(result);
  }

  public Matrix multiplicationOfTwoMatrices(Matrix matrix1, Matrix matrix2) throws Error {
    if (matrix1.getHeight() == matrix2.getWidth()) {
      float[][] result = new float[matrix1.getWidth()][matrix2.getHeight()];
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

  public Matrix getInverseByGaussJordan(Matrix matrix) {
    float[][] identity = new float[matrix.getWidth()][matrix.getHeight()];
    for (int i = 0; i < matrix.getWidth(); i++) {
      for (int j = 0; j < matrix.getHeight(); j++) {
        identity[i][j] = i == j ? 1 : 0;
      }
    }
    for (int y = 0; y < matrix.getHeight(); y++) {
      // matrix.getData()[y][y2] debe ser igual a 1
      float denominator = matrix.getData().clone()[y][y];
      for (int y2 = 0; y2 < matrix.getHeight(); y2++) {
        matrix.getData()[y][y2] /= denominator;
        identity[y][y2] /= denominator;
      }
      for (int x = 0; x < matrix.getWidth(); x++) {
        if(x != y) {
          // matrix.getData()[x][y] debe ser igual a 0
          float multiplicator = matrix.getData().clone()[x][y];
          for (int y2 = 0; y2 < matrix.getHeight(); y2++) {
            matrix.getData()[x][y2] += (-multiplicator) * matrix.getData()[y][y2];
            identity[x][y2] += (-multiplicator) * identity[y][y2];
          }
        }
      }
    }
    return new Matrix(identity);
  }

  public float[] getSystemOfEquations(Matrix matrix, float[] independentValues) {
    for (int y = 0; y < matrix.getHeight(); y++) {
      // matrix.getData()[y][y2] debe ser igual a 1
      float denominator = matrix.getData().clone()[y][y];
      independentValues[y] /= denominator;
      for (int y2 = 0; y2 < matrix.getHeight(); y2++) {
        matrix.getData()[y][y2] /= denominator;
      }
      for (int x = 0; x < matrix.getWidth(); x++) {
        if(x != y) {
          // matrix.getData()[x][y] debe ser igual a 0
          float multiplicator = matrix.getData().clone()[x][y];
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
