/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import java.util.EventListener;

/**
 * Interface implementant EventListener qui permet de faire le lien entre vue et
 * modele. Chaque vue doit implementer cette interface et ses methodes.
 *
 * @author riviere
 */
public interface StatsListener extends EventListener {

    public void statsUpdated(Double moyenne, Double mediane);
}
