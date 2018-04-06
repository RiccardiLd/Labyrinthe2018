/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author segado
 */
public class CaseMur extends CaseImplementee {

    // Constructeurs
    public CaseMur(int lig, int col) {
        super(lig, col); // appel du constructeur de la classe mère CaseImplementee
        moved = false; // on ne peut pas visiter une CaseMur
    }

}
