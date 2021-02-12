package app.model;

public class Matrix {
  private float[][] data;

  public Matrix(float[][] data) {
    this.data = data;
  }

  public Matrix(int width, int height) {
    this.data = new float[width][height];
  }

  public float[][] getData() {
    return this.data;
  }

  public void setData(float[][] newData) {
    this.data = newData;
  }

  public int getWidth() {
    return this.data.length;
  }

  public int getHeight() {
    return this.data[0].length;
  }
}
