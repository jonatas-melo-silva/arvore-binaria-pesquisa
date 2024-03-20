package arvore;

public class No {
  private Object value;
  private No childLeft;
  private No childRight;
  private No parent;

  public No(Object value, No childLeft, No childRight, No parent) { // construtor
    this.value = value;
    this.childLeft = childLeft;
    this.childRight = childRight;
    this.parent = parent;
  }

  public Object getValue() { // pegar o valor do nó
    return value;
  }

  public void setValue(Object value) { // setar o valor do nó
    this.value = value;
  }

  public No getChildLeft() { // pegar o filho da esquerda
    return childLeft;
  }

  public void setChildLeft(No childLeft) { // setar o filho da esquerda
    this.childLeft = childLeft;
  }

  public No getChildRight() { // pegar o filho da direita
    return childRight;
  }

  public void setChildRight(No childRight) { // setar o filho da direita
    this.childRight = childRight;
  }

  public No getParent() { // pegar o pai do nó
    return parent;
  }

  public void setParent(No parent) { // setar o pai do nó
    this.parent = parent;
  }

  // public boolean isRoot() { // é raiz (não tem pai)
  //   return parent == null;
  // }

  public boolean isLeaf() { // é folha (não tem filhos)
    return childLeft == null && childRight == null;
  }

  public boolean isInternal() { // é interno (não é folha)
    return !isLeaf();
  }

  public boolean isExternal() { // é externo (folha)
    return isLeaf();
  }

  public boolean hasOneChild() { // tem um filho
    return (childLeft != null && childRight == null) || (childLeft == null && childRight != null);
  }

  public boolean hasTwoChildren() { // tem dois filhos
    return childLeft != null && childRight != null;
  }

  public boolean isLeftChild() { // é filho da esquerda
    return parent != null && parent.getChildLeft() == this;
  }

  public boolean isRightChild() { // é filho da direita
    return parent != null && parent.getChildRight() == this;
  }

  public boolean containsChildLeft() { // tem filho da esquerda
    return childLeft != null;
  }

  public boolean containsChildRight() { // tem filho da direita
    return childRight != null;
  }

  public No getSibling() { // pegar o irmão do nó (se tiver)
    if (isLeftChild()) {
      return parent.getChildRight();
    } else if (isRightChild()) {
      return parent.getChildLeft();
    }
    return null;
  }

  public int countChilds() { // contar os filhos
    if (isLeaf()) {
      return 0;
    }

    int childCount = 0;

    if (containsChildLeft()) {
      childCount += 1;
    }
    if (containsChildRight()) {
      childCount += 1;
    }

    return childCount;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Value: ").append(value);

    if (childLeft != null) {
        sb.append("\nLeft Child: ").append(childLeft.getValue());
    } else {
        sb.append("\nLeft Child: null");
    }

    if (childRight != null) {
        sb.append("\nRight Child: ").append(childRight.getValue());
    } else {
        sb.append("\nRight Child: null");
    }

    if (parent != null) {
        sb.append("\nParent: ").append(parent.getValue());
    } else {
        sb.append("\nParent: null");
    }

    return sb.toString();
}
}
