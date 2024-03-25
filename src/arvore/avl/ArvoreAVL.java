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
    if (!this.hasRoot()) {
      this.root = new NoAVL(value, null, null, null);
      this.size += 1;
      return;
    }

    NoAVL parent = (NoAVL) this.search(value);

    if ((int) parent.getValue() == (int) value) {
      return;
    }

    NoAVL newNo = new NoAVL(value, null, null, parent);

    if ((int) parent.getValue() > (int) value) {
      parent.setChildLeft(newNo);
    } else {
      parent.setChildRight(newNo);
    }
    this.updateBalanceAfterInsert(newNo);
    this.size += 1;
  }

  private void updateBalanceAfterInsert(NoAVL no) {
    NoAVL parent = (NoAVL) no.getParent();

    boolean isChildLeft = parent.getChildLeft() == no;

    if (isChildLeft) {
      parent.setBalance(parent.getBalance() + 1);
    } else {
      parent.setBalance(parent.getBalance() - 1);
    }

    if (parent.getBalance() == 0) {
      return;
    }

    if (parent.getBalance() == 2) {
      this.rebalanceAfterInsertLeft(parent);
      return;
    }

    if (parent.getBalance() == -2) {
      this.rebalanceAfterInsertRight(parent);
      return;
    }

    if (this.isRoot(parent)) {
      return;
    }

    this.updateBalanceAfterInsert(parent);
  }

  private void rebalanceAfterInsertLeft(NoAVL no) {
    NoAVL childLeft = (NoAVL) no.getChildLeft();

    if (childLeft.getBalance() >= 0) {
      this.rotateSingleRight(no);
      return;
    }

    this.rotateDoubleRight(no);
  }

  private void rebalanceAfterInsertRight(NoAVL no) {
    NoAVL childRight = (NoAVL) no.getChildRight();

    if (childRight.getBalance() <= 0) {
      this.rotateSingleLeft(no);
      return;
    }

    this.rotateDoubleLeft(no);
  }

  private void rotateSingleLeft(NoAVL no) {
    NoAVL parent = (NoAVL) no.getParent();
    NoAVL childRight = (NoAVL) no.getChildRight();
    NoAVL childLeft = (NoAVL) childRight.getChildLeft();
    boolean hasParent = parent != null;
    boolean isChildLeft = hasParent && parent.getChildLeft() == no;

    childRight.setParent(parent);

    if (!hasParent) {
      this.root = childRight;
    }
    if (isChildLeft) {
      parent.setChildLeft(childRight);
    }
    if (hasParent) {
      parent.setChildRight(childRight);
    }

    no.setParent(childRight);
    childRight.setChildLeft(no);

    if (childLeft != null) {
      childLeft.setParent(no);
    }
    no.setChildRight(childLeft);

    no.setBalance(no.getBalance() + 1 - Math.min(childRight.getBalance(), 0));
    childRight.setBalance(childRight.getBalance() + 1 + Math.min(no.getBalance(), 0));
  }

  private void rotateSingleRight(NoAVL no) {
    NoAVL parent = (NoAVL) no.getParent();
    NoAVL childLeft = (NoAVL) no.getChildLeft();
    NoAVL childRight = (NoAVL) childLeft.getChildRight();
    boolean hasParent = parent != null;
    boolean isChildLeft = hasParent && parent.getChildLeft() == no;

    childLeft.setParent(parent);

    if (!hasParent) {
      this.root = childLeft;
    }
    if (isChildLeft) {
      parent.setChildLeft(childLeft);
    }
    if (hasParent) {
      parent.setChildRight(childLeft);
    }

    no.setParent(childLeft);
    childLeft.setChildRight(no);

    if (childRight != null) {
      childRight.setParent(no);
    }
    no.setChildLeft(childRight);

    no.setBalance(no.getBalance() - 1 - Math.max(childLeft.getBalance(), 0));
    childLeft.setBalance(childLeft.getBalance() - 1 + Math.min(no.getBalance(), 0));
  }

  private void rotateDoubleLeft(NoAVL no) {
    NoAVL childRight = (NoAVL) no.getChildRight();
    this.rotateSingleRight(childRight);
    this.rotateSingleLeft(no);
  }

  private void rotateDoubleRight(NoAVL no) {
    NoAVL childLeft = (NoAVL) no.getChildLeft();
    this.rotateSingleLeft(childLeft);
    this.rotateSingleRight(no);
  }

  @Override
  public void remove(Object value) throws ValueNotFoundException {
    super.remove(value);
  }
}
