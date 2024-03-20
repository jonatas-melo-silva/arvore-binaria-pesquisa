# Arvore Binaria de Pesquisa

## Descrição

Implementação de uma árvore binária de pesquisa em Java.

## Estrutura

A árvore é composta por nós(No), que possuem um valor e referências para os nós filhos (esquerdo e direito) e para o nó pai.

## Funcionalidades

- Inserção de um novo valor
- Busca de um valor
- Remoção de um valor
- Impressão da árvore em ordem, pré-ordem e pós-ordem

## Como usar

### Criação da árvore

- ArvoreBinaria arvore = new ArvoreBinaria();

### Inserção de nós

- arvore.inserir(10);
- arvore.inserir(5);
- arvore.inserir(15);
- arvore.inserir(3);
- arvore.inserir(7);
- arvore.inserir(12);
- arvore.inserir(20);

### Busca de um nó

- No no = arvore.buscar(7);

### Remoção de um nó

- arvore.remover(10);

### Impressão da árvore

- arvore.display(TraversalMode.IN_ORDER);
