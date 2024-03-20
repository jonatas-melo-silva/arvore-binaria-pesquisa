package arvore.avl;

import arvore.No;

public class NoAVL extends No {
  private int balance;

  public NoAVL(Object value, No childLeft, No childRight, No parent) {
    super(value, childLeft, childRight, parent);
    this.balance = 0;
  }

  public int getBalance() {
    return this.balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
}
