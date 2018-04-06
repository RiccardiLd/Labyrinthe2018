/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.*;

/**
 *
 * @author segado
 */
public class CaseImplementee implements Case {

    // Attributs protégés et accessibles seulement pour les classes héritées
    protected int positionX, positionY; // position courante dans la case
    protected boolean visited, moved; // booléens de visite et d'accés à la case
    protected ArrayList<Case> voisins; // Liste des cases voisines
    protected int nb_voisins; // nombre de cases voisines

    // Constructeurs : au moins celui indiqué dans l’énoncé
    public CaseImplementee(int lig, int col) {
        this.positionX = col;
        this.positionY = lig;
        visited = false;
        voisins = null;
        nb_voisins = 0;
    }

    // getters 
    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public boolean canMoveToCase() {
        return moved;
    }

    @Override
    public boolean getVisited() {
        return visited;
    }

    @Override
    public Case getVoisin(int i) {
        return voisins.get(i);
    }
    
    @Override
    public int getNbVoisins() {
        return nb_voisins;
    }

    // setters
    @Override
    public void setVisited() {
        visited = true;
    }

    @Override
    public void ajouterVoisin(Case c) {
        if (voisins == null) {
            voisins = new ArrayList<>();
        }
        voisins.add(c);
        nb_voisins = voisins.size();
    }
}
