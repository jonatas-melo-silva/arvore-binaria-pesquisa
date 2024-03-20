package arvore.avl;

import arvore.ValueNotFoundException;
import arvore.binaria.ArvoreBinaria;

public class ArvoreAVL extends ArvoreBinaria {
  public ArvoreAVL() {
    super();
  }

  public ArvoreAVL(Object value) {
    super(value);
  }

  @Override
  public void add(Object value) {
    if(!this.hasRoot()) {
      this.root = new NoAVL(value, null, null, null);
      this.size += 1;
      return;
    }

    NoAVL parent = (NoAVL) this.search(value);

    if((int) parent.getValue() == (int) value) {
      return;
    }

    NoAVL newNo = new NoAVL(value, null, null, parent);

    if((int) parent.getValue() > (int) value) {
      parent.setChildLeft(newNo);
      this.updateBalanceInAdd(newNo);
    } else {
      parent.setChildRight(newNo);
      this.updateBalanceInAdd(newNo);
    }

    this.size += 1;
  }

  private void updateBalanceInAdd(NoAVL no) {
    if(this.isRoot(no)) {
      return;
    }

    NoAVL parent = (NoAVL) no.getParent();

    if(parent.getChildLeft() == no) {
      parent.setBalance(parent.getBalance() + 1);
    } else {
      parent.setBalance(parent.getBalance() - 1);
    }

    if(parent.getBalance() != 0 && (parent.getBalance() < -1 || parent.getBalance() > 1)) {
      this.rebalance(parent);
      return;
    } 
    if (parent.getBalance() != 0) {
      this.updateBalanceInAdd(parent);
    }
  }

  private void rebalance(NoAVL no) {
    int balance = no.getBalance();

    if(balance == 2) {
      if(no.containsChildLeft() && ((NoAVL) no.getChildLeft()).getBalance() < 0) {
        this.rotateDoubleRight(no);
      }
      this.rotateSingleRight(no);
    }
    if(balance == -2) {
      if(no.containsChildRight() && ((NoAVL) no.getChildRight()).getBalance() > 0) {
        this.rotateDoubleLeft(no);
      }
      this.rotateSingleLeft(no);
    }
  }

  private void rotateSingleRight(NoAVL no) {
    NoAVL parent = (NoAVL) no.getParent();
    NoAVL childLeft = (NoAVL) no.getChildLeft();
    NoAVL childRight = (NoAVL) childLeft.getChildRight();

    childLeft.setParent(parent);
    if(parent != null) {
      if(parent.getChildLeft() == no) {
        parent.setChildLeft(childLeft);
      } else {
        parent.setChildRight(childLeft);
      }
    } else {
      this.root = childLeft;
    }

    no.setParent(childLeft);
    childLeft.setChildRight(no);

    if(childRight != null) {
      childRight.setParent(no);
    }
    no.setChildLeft(childRight);

    no.setBalance(no.getBalance() + 1 - Math.min(childLeft.getBalance(), 0));
    childLeft.setBalance(childLeft.getBalance() + 1 + Math.max(no.getBalance(), 0));
  }
  private void rotateDoubleRight(NoAVL no) {
    NoAVL childLeft = (NoAVL) no.getChildLeft();
    this.rotateSingleLeft(childLeft);
    this.rotateSingleRight(no);
  }

  private void rotateDoubleLeft(NoAVL no) {
    NoAVL childRight = (NoAVL) no.getChildRight();
    this.rotateSingleRight(childRight);
    this.rotateSingleLeft(no);
  }

  @Override
  public void remover(Object value) throws ValueNotFoundException {
    super.remover(value);
  }
}
