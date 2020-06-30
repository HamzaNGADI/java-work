/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.event.EventListenerList;

/**
 * Modele de donnees (tres simple) manipulees par le systeme. Creee et mise a
 * jour par le controleur
 *
 * @author riviere
 */
public class Statistiques {

    private ArrayList<Double> notes;
    // Liste de vues a prevenir lors de changements dans les donnees
    private EventListenerList list;

    public Statistiques() {
        notes = new ArrayList<>();
        list = new EventListenerList();
    }

    /**
     * Appelee par le controleur pour ajouter des vues dans la liste du modele
     *
     * @param l - vue implementant l'interface StatsListener
     */
    public void addListener(StatsListener l) {
        list.add(StatsListener.class, l);
    }

    /**
     *
     * @return
     */
    private Double calculerMediane() {
        Double m;
        ArrayList<Double> notesTriees = (ArrayList<Double>) notes.clone();
        Collections.sort(notesTriees);
        int size = notesTriees.size();
        if (size % 2 == 0) {
            m = (notesTriees.get(size / 2) + notesTriees.get((size / 2) - 1)) / 2;
        } else {
            m = notesTriees.get((size - 1) / 2);
        }
        return m;
    }

    /**
     *
     * @return
     */
    private Double calculerMoyenne() {
        Double m = 0.;
        for (Double n : notes) {
            m = m + n;
        }
        return m / notes.size();
    }

    /**
     * Appelee par le controleur pour mettre a jour les notes et calculer les
     * statistiques
     *
     * @param notes : la nouvelle liste de notes
     */
    public void updateStats(ArrayList<Double> notes) {
        this.notes = notes;
        // On previent toutes les vues qui ecoutent des modifications qui ont eu lieu 
        for (final StatsListener listen : list.getListeners(StatsListener.class)) {
            listen.statsUpdated(this.calculerMoyenne(), this.calculerMediane());
        }
    }

}
