/*  Created by riccardild on Apr 5, 2018 4:59:30 PM
 *  (C) Copyright Gianni Riccardi.
 */
package vue;

import javax.swing.*;
import java.awt.*;
import modele.*;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author riccardild
 */
public class LabyGraphique extends JFrame{
    private final JPanel pan ; // panneau
    private final JPanel lab;
    private final JPanel menu; // gère les choix
    private Image mur;
    private Image chemin;
    private JButton boutons[][]; // matrice de boutons
    
    public LabyGraphique (){ // constructeur
        init();
        
        pan = new JPanel(); // instancier le panneau 
        menu = new JPanel(); // menu
        lab = new JPanel(); // labyrinthe
        
        pan.setLayout(new BoxLayout(pan, BoxLayout.LINE_AXIS));
        pan.add(lab);
        pan.add(menu);
        getContentPane().add(pan); // ajouter le panneau dans la fenêtre
        
        menuInit();
    }
    
    public void init() {
        setTitle ("Labyrinthe");
        setSize (470, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    // Méthode qui affiche la grille du labyrinthe 
    public void affiche(Labyrinthe laby) {
        labInit(laby);   
        
        
        for (int y = 0; y < laby.getTailleY(); y++) {
            for (int x = 0; x < laby.getTailleX(); x++) {
                Case c = laby.getCase(y, x);
                if (c instanceof CaseMur) {
                    // Mur
                    boutons[y][x].setIcon(new ImageIcon(mur));
                    boutons[y][x].setBorder(null);
                } else {
                    if (laby.getCurrentPositionX() == x && laby.getCurrentPositionY() == y) {
                        // Case actuelle
                        boutons[y][x].setText("V");
                        boutons[y][x].setBorder(null);
                    } else {
                        // Case vide non visitée
                        boutons[y][x].setIcon(new ImageIcon(chemin));
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
        
        BufferedImage bufMur = null;
        BufferedImage bufChemin = null;
        try {
          bufMur = ImageIO.read(new File("Img/wall.jpg"));
          bufChemin = ImageIO.read(new File("Img/floor.jpg"));
        } catch (IOException e) {
            
        }
        mur = bufMur.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        chemin = bufChemin.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
    }
    
    public void menuInit() {
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        JButton setDFS = new JButton();
        JButton setRandom = new JButton();
        JButton setExit = new JButton();
        
        menu.add(setDFS);
        setDFS.setText("DFS");
        menu.add(setRandom);
        setRandom.setText("Random");
        menu.add(setExit);
        setExit.setText("Exit");
    }
}
