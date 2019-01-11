package com.homework.mower.game;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.homework.mower.domain.Direction;
import com.homework.mower.domain.Instructions;
import com.homework.mower.domain.Lawn;
import com.homework.mower.domain.Mower;
import com.homework.mower.domain.Position;
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
      case ADVANCE : {
        if(!checkTondeuseInBorn(mower, new Position(lawn.getUpOrdinate(), lawn.getRightAbscissa())))
          return mower.move();
        log.warning("Can not move beyound the bounds");
        return mower;
      }
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

  private Boolean checkTondeuseInBorn(Mower mower, Position born) {
    Position position = mower.getPosition();
    return (position.getX() == born.getX() && mower.getDirection().equals(Direction.EAST)) ||
      (position.getX() == 0 && mower.getDirection().equals(Direction.WEST)) ||
      (position.getY() == born.getY() && mower.getDirection().equals(Direction.NORTH)) ||
      (position.getY() == 0 && mower.getDirection().equals(Direction.SOUTH));
  }

  public List<Mower> play() {
    return mowers.entrySet().stream()
      .map(entry -> moveMower(entry.getKey(), entry.getValue()))
      .collect(Collectors.toList());
  }

}
