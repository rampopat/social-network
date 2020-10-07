package socialnetwork.sortedlinkedlist;


import java.util.concurrent.atomic.AtomicMarkableReference;

public class LockFreeNode<T> {
  private T val;
  private AtomicMarkableReference<LockFreeNode<T>> next;

  public LockFreeNode(T val, LockFreeNode<T> next) {
    this.next = new AtomicMarkableReference<>(next, true);
    this.val = val;
  }

  public LockFreeNode(LockFreeNode<T> next) {
    this.next = new AtomicMarkableReference<>(next, true);
    this.val = null;
  }

  public LockFreeNode() {
    this(null);
  }

  public T getVal() {
    return val;
  }

  public LockFreeNode<T> getNext() {
    return next.getReference();
  }

  public boolean setInvalid() {
      LockFreeNode<T> nextNode = getNext();
      return next.compareAndSet(nextNode, nextNode, true, false);
  }

  public boolean setNextIfValid(LockFreeNode<T> expected, LockFreeNode<T> newNext) {
    return next.compareAndSet(expected, newNext, true, true);
  }

  public boolean isValid() {
    return next.isMarked();
  }
}
