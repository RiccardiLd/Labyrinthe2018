/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.ImpossibleMoveException;
import controleur.FileFormatException;
import java.util.*;
import java.io.*;

/**
 *
 * @author segado
 */
public class Labyrinthe {

    /**
     * attributs prives
     */
    private int tailleX, tailleY;
    private int departX, departY;
    private int arriveeX, arriveeY;
    private int posX, posY;
    private ArrayList<Case> grille; // // L'ensemble des cases du labyrinthe 

    /**
     * Constructeur qui initialise le labyrinthe à partir du fichier en
     * paramètre
     *
     * @param fic : fichier du labyrinthe
     * @throws FileFormatException : problème de format de fichier
     */
    public Labyrinthe(File fic) throws FileFormatException {
        try {

            Scanner sc = new Scanner(fic); // lire le fichier lab

            int lig, col, i;
            String laby;
            char car;
            tailleX = sc.nextInt();  // récupérer la taille en X du labyrinthe	                                
            tailleY = sc.nextInt();  // récupérer la taille en Y du labyrinthe
            posX = departX = sc.nextInt();  // récupérer le point de départ courant en X du labyrinthe
            posY = departY = sc.nextInt();  // récupérer le point de départ courant en Y du labyrinthe
            arriveeX = sc.nextInt();  // récupérer le point d’arrivée en X du labyrinthe
            arriveeY = sc.nextInt();  // récupérer le point d’arrivée en Y du labyrinthe
            System.out.println(tailleX + " " + tailleY);
            Case unecase = null; // initialiser une case
            grille = new ArrayList<>(); // instancier l’ArrayList de cases
            // Lire les cases du labyrinthe
            for (lig = 0; lig < tailleY; lig++) { // lignes
                laby = sc.next(); // une ligne du laby
                i = 0;

                for (col = 0; col < tailleX; col++) { // colonnes
                    car = laby.charAt(i++);//récupérer le caractère d’une case

                    // Détection des sommets d'entrée et de sortie
                    if (car == 'X') {
                        unecase = new CaseMur(lig, col);
                    } else if (car == '_') {
                        unecase = new CaseTrou(lig, col);
                    } else {
                        throw new FileFormatException();
                    }

                    // ajouter la case dans la grille
                    grille.add(unecase);
                } // fin du for col
                sc.nextLine();
            } // fin du for lig

            // ajouter les cases adjacentes aux trous
            for (lig = 0; lig < tailleY; lig++) { // lignes
                for (col = 0; col < tailleX; col++) { // colonnes
                    Case c = getCase(lig, col);

                    if (c instanceof CaseTrou) {
                        // dans l'ordre haut bas droite gauche
                        Case haut = getCase(lig - 1, col);
                        if (haut != null && haut instanceof CaseTrou) {
                            c.ajouterVoisin(haut);
                        }
                        Case bas = getCase(lig + 1, col);
                        if (bas != null && bas instanceof CaseTrou) {
                            c.ajouterVoisin(bas);
                        }
                        Case droite = getCase(lig, col + 1);
                        if (droite != null && droite instanceof CaseTrou) {
                            c.ajouterVoisin(droite);
                        }
                        Case gauche = getCase(lig, col - 1);
                        if (gauche != null && gauche instanceof CaseTrou) {
                            c.ajouterVoisin(gauche);
                        }

                    }
                }
            }

        }// fin du try
        catch (FileNotFoundException | FileFormatException e) {
            throw new FileFormatException();
        } // générer l’exception FileFormatException
    } // fin de initFromFile

    /**
     * Tente de se déplacer dans la case ligne, colonne et de la visiter
     *
     * @param lig : ligne de la case
     * @param col : colonne de la case
     * @throws ImpossibleMoveException :  déplacement impossible
     */
    public void move(int lig, int col) throws ImpossibleMoveException {
        Case macase;

        // teste si on peut visiter la case
        macase = getCase(lig, col);
        if (macase == null) {
            throw new ImpossibleMoveException();
        } else if (!macase.canMoveToCase()) {
            throw new ImpossibleMoveException();
        } else {
            setVisited(lig, col);
        }
    }

    /**
     * deplacement automatique sans deborder et si pas déjà visité
	 * @throws ImpossibleMoveException :  déplacement impossible
     */
	public void autoMove() throws ImpossibleMoveException {
        int dir;
        do {
            Random r = new Random();
            dir = r.nextInt(4);
            switch (dir) {
                case 0: // haut
                    posY--;
                    break;
                case 1: // bas
                    posY++;
                    break;
                case 2: // droite
                    posX++;
                    break;
                case 3: // gauche
                    posX--;
                    break;
            }
        } while (posX < 0 || posX >= tailleX || posY < 0 || posY >= tailleY);
        
        // si la case pas visitée, s'y déplacer
        if (!getCase(posY, posX).getVisited()) {
            move(posY, posX);
        }
    }

    /**
     * accesseur retourne la position courante en X
     * @return 
     */
    public int getCurrentPositionX() {
        return posX;
    }

    /**
     * accesseur retourne la position courante en Y
     * @return 
     */
    public int getCurrentPositionY() {
        return posY;
    }

    /**
     * accesseur retourne le depart en X
     * @return 
     */
    public int getDepartX() { // utile pour TestLaby
        return departX;
    }

    /**
     * accesseur retourne depart en Y
     * @return 
     */
    public int getDepartY() { // utile pour TestLaby
        return departY;
    }

    /**
     * accesseur retourne l'arrivee en X
     * @return 
     */
    public int getArriveeX() { // utile pour TestLaby
        return arriveeX;
    }

    /**
     * accesseur retourne l'arrivee en Y
     * @return 
     */
    public int getArriveeY() { // utile pour TestLaby
        return arriveeY;
    }

    /**
     * accesseur retourne la taille en X
     * @return 
     */
    public int getTailleX() { // utile pour TestLaby
        return tailleX;
    }

    /**
     * accesseur retourne la taille en Y
     * @return 
     */
    public int getTailleY() { // utile pour TestLaby
        return tailleY;
    }

    /**
     * retourne une case de la grille du labyrinthe
     *
     * @param lig : ligne de la case
     * @param col : colonne de la case
     * @return de la case
     */
    public final Case getCase(int lig, int col) {
        // Cas de débordement
        if (col < 0 || col >= tailleX || lig < 0 || lig >= tailleY) {
            return null;
        }
        int index = lig * tailleX + col; // calcul de l’index de la grille en fonction de lig et col
        return grille.get(index);
    }

    /**
     * setter pour visiter une case du labyrinthe
     *
     * @param lig : ligne de la case
     * @param col : colonne de la case
     */
    public void setVisited(int lig, int col) {
        posX = col;
        posY = lig;
        getCase(lig, col).setVisited();
    }
} // fin de Labyrinthe

