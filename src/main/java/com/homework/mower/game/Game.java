package com.homework.mower.game;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.homework.mower.domain.Instructions;
import com.homework.mower.domain.Lawn;
import com.homework.mower.domain.Mower;
import com.homework.mower.domain.Instructions.Instruction;

public class Game {
    
  private final static Logger log = Logger.getLogger(Game.class.getName());
  
  private final Lawn lawn;
  private final Map<Mower, Instructions> mowers;

  protected Game(Lawn lawn, Map<Mower, Instructions> mowers) {
    this.lawn = lawn;
    this.mowers = mowers;
  }

  protected Mower moveMower(Mower mower, Instruction instruction) {
    switch(instruction) {
      case ADVANCE : return mower.move();
      case LEFT : return mower.turnLeft();
      case RIGHT : return mower.turnRight();
      default :
        log.warning("instruction not allowed");
        return mower;
    }
  }

  private Mower moveMower(Mower mower, Instructions instructions) {
    Mower newMower = mower;
    for(Instruction instruction : instructions.getInstructions()) {
      newMower = moveMower(newMower, instruction);
    }
    return newMower;
  }

  public List<Mower> play() {
    return mowers.entrySet().stream()
      .map(entry -> moveMower(entry.getKey(), entry.getValue()))
      .collect(Collectors.toList());
  }

}
