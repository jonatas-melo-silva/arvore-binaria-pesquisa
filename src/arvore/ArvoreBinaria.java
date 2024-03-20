package arvore;

import java.util.ArrayList;

public class ArvoreBinaria {
  private No root;
  private int size;
  private ArrayList<No> nodes;

  public ArvoreBinaria() {
    this.root = null;
    this.size = 0;
    this.nodes = null;
  }

  public ArvoreBinaria(Object value) {
    this.root = new No(value, null, null, null);
    this.size = 1;
  }

  public boolean isEmpty() {
    return this.root == null && this.size == 0;
  }

  public boolean isRoot(No no) {
    return no.getParent() == null && this.root == no;
  }

  public boolean hasRoot() {
    return this.root != null;
  }

  public Object find(Object value) {
    No no = this.search(value);
    return (int) no.getValue() == (int) value ? no.getValue() : null;
  }

  public void add(Object value) {
    if (!this.hasRoot()) {
      this.root = new No(value, null, null, null);
      this.size += 1;
      // System.out.println("Adicionando " + this.root.toString());
      return;
    }

    No parent = this.search(value);

    if ((int) parent.getValue() == (int) value) {
      return;
    }

    No newNo = new No(value, null, null, parent);

    if ((int) parent.getValue() > (int) value) {
      parent.setChildLeft(newNo);
    } else {
      parent.setChildRight(newNo);
    }
    // System.out.println("Adicionando " + newNo.toString());
    this.size += 1;
  }

  private No search(Object value) { // busca um valor na árvore
    return this.search(this.root, value);
  }

  private No search(No no, Object value) { // busca um valor a partir de um nó
    if (no.isLeaf() || (int) no.getValue() == (int) value)
      return no;

    if ((int) no.getValue() > (int) value && no.containsChildLeft()) {
      return this.search(no.getChildLeft(), value);
    }

    if ((int) no.getValue() < (int) value && no.containsChildRight()) {
      return this.search(no.getChildRight(), value);
    }

    return no;
  }

  public void remover(Object value) throws ValueNotFoundException {
    if (!this.hasRoot()) {
      return;
    }

    No noRemove = this.search(value);
    if ((int) noRemove.getValue() != (int) value) {
      throw new ValueNotFoundException("Value not found");
    }

    remove(noRemove);
    this.size -= 1;
  }

  private void remove(No no) {
    // System.out.println("Removendo " + no.toString());
    if (no.isLeaf()) {
      removeLeaf(no);
    }
    if (no.countChilds() == 1) {
      removeOneChild(no);
    }
    if (no.countChilds() == 2) {
      removeTwoChilds(no);
    }
  }

  private void removeLeaf(No no) {
    if (this.isRoot(no)) {
      this.root = null;
      return;
    }

    No parent = no.getParent();

    if (no == parent.getChildLeft()) {
      parent.setChildLeft(null);
      return;
    }

    parent.setChildRight(null);
  }

  private void removeOneChild(No no) {
    No child = no.containsChildLeft() ? no.getChildLeft() : no.getChildRight();

    No parent = no.getParent();

    if (this.isRoot(no)) {
      this.root = child;
      child.setParent(null);
      return;
    }

    if (no == parent.getChildLeft()) {
      parent.setChildLeft(child);
    } else {
      parent.setChildRight(child);
    }

    child.setParent(parent);
  }

  private void removeTwoChilds(No no) {
    No sucessor = no.getChildRight();
    while (sucessor.containsChildLeft()) {
      sucessor = sucessor.getChildLeft();
    }

    no.setValue(sucessor.getValue());
    remove(sucessor);
  }

  public int getSize() { // tamanho da árvore
    return this.size;
  }

  public int height() { // altura da árvore
    return this.height(this.root);
  }

  private int height(No no) { // altura do nó até as folhas
    if (no == null || no.isLeaf()) {
      return 0;
    }

    int result = 0;

    if (no.containsChildLeft()) {
      result = Math.max(result, this.height(no.getChildLeft()));
    }
    if (no.containsChildRight()) {
      result = Math.max(result, this.height(no.getChildRight()));
    }

    return result + 1;
  }

  public int depth() { // profundidade da árvore
    return this.depth(this.root);
  }

  private int depth(No no) { // profundidade do nó até a raiz
    if (no == null || this.isRoot(no)) {
      return 0;
    }

    int parentDepth = this.depth(no.getParent());
    return parentDepth + 1;
  }

  private void traverse(No no, TraversalMode mode) {
    if (no == null) {
      return;
    }

    if (mode == TraversalMode.PRE_ORDER) {
      nodes.add(no);
    }

    traverse(no.getChildLeft(), mode);

    if (mode == TraversalMode.IN_ORDER) {
      nodes.add(no);
    }

    traverse(no.getChildRight(), mode);

    if (mode == TraversalMode.POST_ORDER) {
      nodes.add(no);
    }
  }

  public enum TraversalMode {
    PRE_ORDER,
    IN_ORDER,
    POST_ORDER
  }

  private ArrayList<No> traverseAndCollectNodes(TraversalMode mode) {
    this.nodes = new ArrayList<>();
    this.traverse(this.root, mode);
    return this.nodes;
  }

  public void display(TraversalMode mode) {
    int lines = this.height() + 1;
    int columns = this.size;
    
    ArrayList<No> nosInMode = this.traverseAndCollectNodes(mode);
    
    int[][] matriz = this.populateMatriz(nosInMode, lines, columns);

    this.printMatriz(matriz);
  }

  private int[][] populateMatriz(ArrayList<No> nosInMode, int lines, int columns) {
    int[][] matriz = new int[lines][columns];

    for (int colum = 0; colum < nosInMode.size(); colum++) {
      int line = depth(nosInMode.get(colum));
      int value = (int) nosInMode.get(colum).getValue();
      matriz[line][colum] = value;
    }

    return matriz;
  }

  private void printMatriz(int[][] matriz) {
    int lines = matriz.length;

    for (int line = 0; line < lines; line++) {
      int columns = matriz[line].length;

      for (int colum = 0; colum < columns; colum++) {
        int value = matriz[line][colum];
        if (value == 0) {
          System.out.print("\t");
        } else {
          System.out.print(matriz[line][colum] + "\t");
        }
      }

      System.out.println();
    }
  }
}
