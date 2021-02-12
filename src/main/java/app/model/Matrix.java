package app.model;

public class Matrix {
  private double[][] data;

  public Matrix(double[][] data) {
    this.data = data;
  }

  public Matrix(int width, int height) {
    this.data = new double[width][height];
  }

  public double[][] getData() {
    return this.data;
  }

  public void setData(double[][] newData) {
    this.data = newData;
  }

  public int getWidth() {
    return this.data.length;
  }

  public int getHeight() {
    return this.data[0].length;
  }
}
