package com.homework.mower.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Instructions {
  
  private final List<Instruction> instructions;

  public Instructions(List<Instruction> instructions) {
    this.instructions = instructions;
  }

  public List<Instruction> getInstructions() {
    return instructions;
  }

  public enum Instruction {
    ADVANCE('F'), RIGHT('R'), LEFT('L');
    
    private final char id;

    Instruction(char id) {
      this.id = id;
    }

    public char getId() {
      return id;
    }

    public static Optional<Instruction> valueById(char value) {
      return Arrays.stream(values())
          .filter(i -> i.id == value)
          .findFirst();
    }
  }
}
