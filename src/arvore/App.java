package arvore;

import arvore.ArvoreBinaria.TraversalMode;

public class App {
    public static void main(String[] args) {
        try {
            ArvoreBinaria arvore = new ArvoreBinaria();
            arvore.add(10);
            arvore.add(5);
            arvore.add(2);
            arvore.add(8);
            arvore.add(15);
            arvore.add(22);

            System.out.println();
            arvore.display(TraversalMode.IN_ORDER);
            System.out.println(lineAndSize(arvore));

            System.out.println();
            arvore.add(25);
            arvore.display(TraversalMode.IN_ORDER);
            System.out.println(lineAndSize(arvore));

            System.out.println();
            arvore.remover(5);
            arvore.display(TraversalMode.IN_ORDER);
            System.out.println(lineAndSize(arvore));

            System.out.println();
            Object value = arvore.find(10); 
            System.out.println("No encontrado: " + value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String lineAndSize(ArvoreBinaria arvore) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arvore.getSize(); i++) {
            sb.append("--------");
        }
        sb.append(" Elementos: " + arvore.getSize());

        return sb.toString();
    }
}
