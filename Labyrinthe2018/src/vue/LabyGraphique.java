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
public class LabyGraphique extends JFrame{
    private JPanel pan ; // panneau
    private JButton boutons[][]; // matrice de boutons
    
    public LabyGraphique (){ // constructeur
        setTitle ("Mon labyrinthe");
        setSize (300, 150);
        pan = new JPanel(); // instancier le panneau 
        getContentPane().add(pan); // ajouter le panneau dans la fenêtre
    }
    
    // Méthode qui affiche la grille du labyrinthe 
    public void affiche(Labyrinthe laby) {
        pan.setLayout(new GridLayout(laby.getTailleY(), laby.getTailleX())); // mise en forme avec une grille 
        boutons = new JButton[laby.getTailleY()][]; // instancier les lignes de la matrice de boutons
        for (int i = 0; i < laby.getTailleY(); i++)
            boutons[i] = new JButton[laby.getTailleX()];// Pour chaque ligne de la matrice, instancier les boutons
        // Ajouter les boutons dans le panneau
        for (int i = 0; i < laby.getTailleY(); i++) {
            for (int j = 0; j < laby.getTailleX(); j++) {
                boutons[i][j] = new JButton(); // instancier chaque bouton 
                pan.add(boutons[i][j]);
            }
        }
        // rendre la fenetre visible
        this.setVisible(true);
    }
}
