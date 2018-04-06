/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author segado
 */
public class CaseTrou extends CaseImplementee {
    // Constructeurs
    public CaseTrou(int lig, int col ) {
	super(lig, col) ; // appel du constructeur de la classe mère CaseImplementee
	moved=true ; // on peut visiter une CaseTrou
    }  
}
