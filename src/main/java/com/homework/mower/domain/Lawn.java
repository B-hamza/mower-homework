package com.homework.mower.domain;

public class Lawn {
    
  private final int upOrdinate;
  private final int rightAbscissa;

  public Lawn(int upOrdinate, int rightAbscissa) {
    this.upOrdinate = upOrdinate;
    this.rightAbscissa = rightAbscissa;
  }

  public int getUpOrdinate() {
    return upOrdinate;
  }
  
  public int getRightAbscissa() {
    return rightAbscissa;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Lawn lawn = (Lawn) o;

    if (upOrdinate != lawn.upOrdinate) return false;
    return rightAbscissa == lawn.rightAbscissa;
  }

  @Override
  public int hashCode() {
    int result = upOrdinate;
    result = 31 * result + rightAbscissa;
    return result;
  }
}
