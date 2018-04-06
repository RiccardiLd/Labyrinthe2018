/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author segado
 */
public interface Case {

// Prototypes
    public int getPositionX(); // retourne la position en X (colonne) de la case

    public int getPositionY(); // retourne la position en Y (ligne) de la case

    public boolean canMoveToCase(); // retourne un booléen indiquant si la on peut se déplacer dans la case 

    public boolean getVisited(); // retourne un booléen indiquant si on peut visiter la case

    public Case getVoisin(int i); // retourne le voisin numero i de la case

    public int getNbVoisins(); // retourne le nombre de voisins de la case

    public void setVisited(); // modifie la case à visitée

    public void ajouterVoisin(Case c); // ajouter un voisin à la case

}
