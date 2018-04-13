/*  Created by riccardild on Apr 5, 2018 4:59:30 PM
 *  (C) Copyright Gianni Riccardi.
 */
package vue;

import javax.swing.*;
import java.awt.*;
import modele.*;

/**
 *
 * @author riccardild
 */
public class LabyGraphique extends JFrame /*implements ActionListener*/{
    private final JPanel pan ; // panneau
    private final JPanel lab;
    private final JPanel menu; // gère les choix
    private JButton boutons[][]; // matrice de boutons
    
    public LabyGraphique (){ // constructeur        
        pan = new JPanel(); // instancier le panneau 
        menu = new JPanel(); // menu
        lab = new JPanel(); // labyrinthe
        pan.setLayout(new BoxLayout(pan, BoxLayout.LINE_AXIS));
        pan.add(lab);
        pan.add(menu);
    }
    
    public void init() {
        getContentPane().add(pan); // ajouter le panneau dans la fenêtre
        setTitle ("Labyrinthe");
        setSize (470, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuInit();
    }
    
    // Méthode qui affiche la grille du labyrinthe 
    public void affiche(Labyrinthe laby) {
        /*
        Créer deuxième matrice de boutons por afficher perso + départ/arrivée
        Images devront être transparentes
        */
        for (int y = 0; y < laby.getTailleY(); y++) {
            for (int x = 0; x < laby.getTailleX(); x++) {
                Case c = laby.getCase(y, x);
                if (c instanceof CaseMur) {
                    // Mur
                    boutons[y][x].setText("X");
                    boutons[y][x].setBorder(null);
                } else {
                    if (laby.getCurrentPositionX() == x && laby.getCurrentPositionY() == y) {
                        // Case actuelleÒ
                        boutons[y][x].setText("V");
                        boutons[y][x].setBorder(null);
                    } else {
                        // Case vide
                        boutons[y][x].setText("_");
                        boutons[y][x].setBorder(null);
                    }
                }
            }
        }
        // rendre la fenetre visible
        this.setVisible(true);
    }
    
    public void labInit(Labyrinthe laby) {
        lab.setLayout(new GridLayout(laby.getTailleY(), laby.getTailleX())); // mise en forme avec une grille 
        boutons = new JButton[laby.getTailleY()][]; // instancier les lignes de la matrice de boutons
        for (int i = 0; i < laby.getTailleY(); i++)
            boutons[i] = new JButton[laby.getTailleX()];// Pour chaque ligne de la matrice, instancier les boutons
        // Ajouter les boutons dans le panneau
        for (int i = 0; i < laby.getTailleY(); i++) {
            for (int j = 0; j < laby.getTailleX(); j++) {
                boutons[i][j] = new JButton(); // instancier chaque bouton 
                lab.add(boutons[i][j]);
            }
        }
    }
    
    public void menuInit() {
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        JButton setDFS = new JButton("DFS");
        JButton setRandom = new JButton("Random");
        JButton setExit = new JButton("Exit");
        
        menu.add(setDFS);
        menu.add(setRandom);
        menu.add(setExit);
    }
}
