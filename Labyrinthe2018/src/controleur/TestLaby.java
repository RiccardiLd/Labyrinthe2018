
package controleur;

import java.awt.event.ActionEvent;
import java.io.File;
import modele.*;
import vue.*;


public class TestLaby {

    private static Labyrinthe laby;
    private static LabyConsole console;
    private static LabyGraphique graphique;
    
    /**
     * Constructeur qui initialise le labyrinthe à partir du fichier en
     * paramètre
     *
     * @param fic : fichier du labyrinthe
     * @throws FileFormatException : problème de format de ficher
     */
    public TestLaby(File fic) throws FileFormatException {
        laby = new Labyrinthe(fic);
    }

    /**
     * Déplacement récursif en profondeur dans le labyrinthe
     *
     * @param ligne de la case
     * @param colonne de la case
     * @return d'un booléen d'arrêt du déplacement
     */
    public boolean deplacerDFS(int ligne, int colonne) {
        boolean stop = false;
        Case macase;
        try {
            // Si la sortie, on s'arrête
            if (colonne == laby.getArriveeX() && ligne == laby.getArriveeY()) {
                System.out.println("ARRIVEE");
                stop = true;
            } else {
                // visiter la case
                macase = laby.getCase(ligne, colonne);
                laby.move(ligne, colonne);
                macase.setVisited();

                // afficher position de la case visitée et le labyrinthe
                console.affiche(macase);
                console.affiche(laby);
                graphique.affiche(laby);

                // visiter récursivemet tous les voisins non marqués de macase
                for (int i = 0; i < macase.getNbVoisins(); i++) {
                    Case voisin = macase.getVoisin(i);
                    if (!stop && !voisin.getVisited()) {
                        stop = deplacerDFS(voisin.getPositionY(), voisin.getPositionX());
                    }
                }
            }
        } catch (ImpossibleMoveException ex) {

        }
        return stop;
    }

    /**
     * Déplacement aléatoire dans le labyrinthe
     *
     * @return d'un booléen d'arrêt du déplacement 
     */
    public boolean deplacerAuto() {
        boolean stop = false;
        Case macase;
        while (!stop) {
            try {
                // se déplacer aléatoirement
                laby.autoMove();

                // afficher position de la case visitée et le labyrinthe
                macase = laby.getCase(laby.getCurrentPositionY(), laby.getCurrentPositionX());
                console.affiche(macase);
                console.affiche(laby);
                graphique.affiche(laby);
                
                // Si la sortie, on s'arrête
                if (laby.getCurrentPositionX() == laby.getArriveeX() 
                    && laby.getCurrentPositionY() == laby.getArriveeY()) {
                    System.out.println("ARRIVEE");
                    stop = true;
                }
            } catch (ImpossibleMoveException ex) {

            }

        }
        return stop;
    }

    //public void 
    public static void main(String[] args) {            
        try {
            console = new LabyConsole(); //  instancier la console 
            /*
            System.out.println("Entrez le nom du fichier du labyrinthe :");
            String nomlaby = console.toString(); // récupérer le nom du fichier
            */
            String nomlaby = "labyrinthe.txt";
            TestLaby test;
            test = new TestLaby(new File(nomlaby)); // instancier le labyrinthe à partir du fichier
            console.affiche(laby); // affiche le labyrinthe en console

            graphique = new LabyGraphique(laby);
            graphique.init(laby);
            graphique.affiche(laby);
            
            graphique.setDFS.addActionListener((ActionEvent e) -> {
                //Perform function when button is pressed
                test.deplacerDFS(laby.getDepartY(), laby.getDepartX());
            }); 
            graphique.setRandom.addActionListener((ActionEvent e) -> {
                //Perform function when button is pressed
                test.deplacerAuto();
            }); 
            graphique.setExit.addActionListener((ActionEvent e) -> {
                //Perform function when button is pressed
                System.exit(0);
            });
            
        } catch (FileFormatException ffe) {
            System.out.println("Problème de format du fichier !");
        }
    }
}
