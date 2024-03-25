package arvore.avl;

import arvore.TraversalMode;

public class App {
    public static void main(String[] args) {
        try {
            ArvoreAVL arvore = new ArvoreAVL();
            arvore.add(10);
            arvore.add(20);
            arvore.add(30);
            arvore.add(40);
            arvore.add(50);
            arvore.add(25);
            arvore.add(60);
            arvore.add(70);
            arvore.add(80);
            arvore.add(90);

            System.out.println();
            arvore.display(TraversalMode.IN_ORDER);
            System.out.println(lineAndSize(arvore));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String lineAndSize(ArvoreAVL arvore) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arvore.getSize(); i++) {
            sb.append("--------");
        }
        sb.append(" Elementos: " + arvore.getSize());

        return sb.toString();
    }
}
