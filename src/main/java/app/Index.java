package app;

import java.util.Arrays;

import app.controller.MatrixController;
import app.model.Matrix;

public class Index {
  public static void main(String[] args) {
    MatrixController controller = new MatrixController();

    try {
      System.out.println("Suma de matrices");
      double[][] sum1 = { { 2, -3, 5 }, { 4, 1, -7 } }, sum2 = { { 1, 0, 2 }, { -3, 5, 8 } };
      Matrix matrixSum1 = new Matrix(sum1), matrixSum2 = new Matrix(sum2);
      System.out.println(Arrays.deepToString(controller.sum(matrixSum1, matrixSum2).getData())); 
    } catch (Exception e) {
      System.err.println(e);
    }


    try {
      System.out.println("Multiplicación por escalar");
      double[][] scalarMultiplication = { { 1, -2, 3 }, { 0, 1, 8 } };
      Matrix matrixScalarMult = new Matrix(scalarMultiplication);
      System.out.println(Arrays.deepToString(controller.multiplicationByScalar(matrixScalarMult, -5).getData()));  
    } catch (Exception e) {
      System.err.println(e);
    }


    try {
      System.out.println("Multiplicación de matrices");
      double[][] mult1 = { { 1, 0, 2 }, { -1, 3, 1 } }, mult2 = { { 3, 1 }, { 2, 1 }, { 1, 0 } };
      Matrix matrixMult1 = new Matrix(mult1), matrixMult2 = new Matrix(mult2);
      System.out.println(Arrays.deepToString(controller.multiplicationOfTwoMatrices(matrixMult1, matrixMult2).getData())); 
    } catch (Exception e) {
      System.err.println(e);
    }


    try {
      System.out.println("Matriz inversa por Gauss-Jordan");
      double[][] gaussJordan = { { 1, -6, 2 },  { 2, -2, -1 }, { 1, -3, -5 } };
      Matrix matrix = new Matrix(gaussJordan);
      System.out.println(Arrays.deepToString(controller.getInverseByGaussJordan(matrix).getData())); 
    } catch (Exception e) {
      System.err.println(e);
    }

    try {
      System.out.println("Sistema de ecuaciones por Gauss-Jordan");
      double[][] gaussJordan = {
        { 3, -0.1, -0.2 },
        { 0.1, 7, -0.3 },
        { 0.3, -0.2, 10 }
      };
      double[] independentValues = { 7.85, -19.3, 71.4 };
      Matrix matrix = new Matrix(gaussJordan);
      System.out.println(Arrays.toString(controller.getSystemOfEquations(matrix, independentValues)));
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
