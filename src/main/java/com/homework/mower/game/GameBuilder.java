package com.homework.mower.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.homework.mower.domain.Direction;
import com.homework.mower.domain.Instructions;
import com.homework.mower.domain.Lawn;
import com.homework.mower.domain.Mower;
import com.homework.mower.domain.Position;
import com.homework.mower.domain.Instructions.Instruction;

public class GameBuilder {

  private Lawn lawn;
  private Map<Mower, Instructions> map = new HashMap<>();

  public GameBuilder fromFile(String file) {
    try(Stream<String> stream = Files.lines(Paths.get(file))) {
      Iterator<String> iterator = stream.iterator();
      this.withLawn(iterator.next());
      while(iterator.hasNext()) {
        this.withMowerAndInstructions(iterator.next(), iterator.next());
      }
      return this;
    } catch (IOException | NoSuchElementException e) {
			throw new GameError(e);
		}
  }
  
  public GameBuilder withLawn(String line) {
    try {
      String[] elems = line.split(" ");
      lawn = new Lawn(Integer.parseInt(elems[0]), Integer.parseInt(elems[1]));
      return this;
    } catch(Exception e) {
      throw new GameError(e);
    }
  }

  public GameBuilder withMowerAndInstructions(String mowerLine, String instructionsLine) {
    try {
      List<Instruction> list = instructionsLine.chars()
        .mapToObj(item -> (char) item)
        .map(elem -> Instruction.valueById(elem).<GameError>orElseThrow(() -> new GameError("parsing instructions failed")))
        .collect(Collectors.toList());
      Instructions instructions = new Instructions(list);

      String[] elems = mowerLine.split(" ");

      Position position = new Position(Integer.parseInt(elems[0]), Integer.parseInt(elems[1]));
      Direction direction = Direction.valueById(elems[2]).<GameError>orElseThrow(() -> new GameError("parsing direction failed"));
      Mower mower = new Mower(position, direction);

      map.put(mower, instructions);

      return this;
    } catch(Exception e) {
      throw new GameError(e);
    }
    
  }

  public Game build() {
    return new Game(lawn, map);
  }

}
