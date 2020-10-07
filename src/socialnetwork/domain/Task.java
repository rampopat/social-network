package socialnetwork.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Comparable<Task> {

  private static AtomicInteger nextTaskId = new AtomicInteger(0);

  private final Command command;
  private final Message message;
  private final Board board;
  private final int id;

  public Task(Command command, Message message, Board board) {
    this.command = command;
    this.message = message;
    this.board = board;
    this.id = nextTaskId.getAndIncrement();
  }

  public Command getCommand() {
    return command;
  }

  public Message getMessage() {
    return message;
  }

  public Board getBoard() {
    return board;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Task task = (Task) o;

    return id == task.id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public int compareTo(Task task) {
    return task.id - this.id;
  }

  public enum Command {
    POST, DELETE
  }
}
