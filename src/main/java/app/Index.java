package app;

import java.util.Arrays;

import app.controller.MatrixController;
import app.model.Matrix;

public class Index {
  public static void main(String[] args) {
    MatrixController controller = new MatrixController();

    try {
      System.out.println("Suma de matrices");
      float[][] sum1 = { { 2, -3, 5 }, { 4, 1, -7 } }, sum2 = { { 1, 0, 2 }, { -3, 5, 8 } };
      Matrix matrixSum1 = new Matrix(sum1), matrixSum2 = new Matrix(sum2);
      System.out.println(Arrays.deepToString(controller.sum(matrixSum1, matrixSum2).getData())); 
    } catch (Exception e) {
      System.err.println(e);
    }


    try {
      System.out.println("Multiplicación por escalar");
      float[][] scalarMultiplication = { { 1, -2, 3 }, { 0, 1, 8 } };
      Matrix matrixScalarMult = new Matrix(scalarMultiplication);
      System.out.println(Arrays.deepToString(controller.multiplicationByScalar(matrixScalarMult, -5).getData()));  
    } catch (Exception e) {
      System.err.println(e);
    }


    try {
      System.out.println("Multiplicación de matrices");
      float[][] mult1 = { { 1, 0, 2 }, { -1, 3, 1 } }, mult2 = { { 3, 1 }, { 2, 1 }, { 1, 0 } };
      Matrix matrixMult1 = new Matrix(mult1), matrixMult2 = new Matrix(mult2);
      System.out.println(Arrays.deepToString(controller.multiplicationOfTwoMatrices(matrixMult1, matrixMult2).getData())); 
    } catch (Exception e) {
      System.err.println(e);
    }


    try {
      System.out.println("Gauss-Jordan");
      float[][] gaussJordan = { { 1, -6, 2 },  { 2, -2, -1 }, { 1, -3, -5 } };
      Matrix matrix = new Matrix(gaussJordan);
      System.out.println(Arrays.deepToString(controller.getInverseByGaussJordan(matrix).getData())); 
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
