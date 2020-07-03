/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 * Initialise la vue (Ihm) et le modèle (Statistiques) et fait le lien entre les
 * deux. Vérifie les commandes de l'utilisateur si besoin.
 *
 * @author riviere
 */
public class Controler {

    private final JFrame frame;
    // Initialise la vue
    private final Ihm ihm;
    // Initialise le modèle
    private Statistiques stats;

    public Controler() {
        ihm = new Ihm(this);
        frame = new JFrame();
        stats = new Statistiques();
        stats.addListener(ihm);

        frame.add(ihm);
        frame.setName("ProfHelper");
        frame.setSize(700, 400);
        frame.setTitle("ProfHelper");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int res;
                res = JOptionPane.showConfirmDialog(ihm, "Voulez-vous sauvegarder les résultats avant de quitter ?", "Question", JOptionPane.INFORMATION_MESSAGE);

                if (res == JOptionPane.YES_OPTION) {

                } else if (res == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }

        });
        frame.setVisible(true);
    }

    /**
     *
     * @param notes
     */
    public void createStats(ArrayList<Double> notes) {
        stats.updateStats(notes);
    }

    /**
     *
     */
    public void editerNotes() {
        NotesEditor ne = new NotesEditor();
        ne.setSize(375, 300);
        ne.setResizable(false);
        ne.setVisible(true);
        if (ne.getNotes() != null) {
            ihm.setNotesInList(ne.getNotes().toArray());
            this.createStats(ne.getNotes());
        }
    }

    /**
     *
     */
    public void modifierNotes() {
        ListModel<Object> list = ihm.getListModel();
        ArrayList<Double> notes = new ArrayList<>();
        for (int i = 0; i < list.getSize(); i++) {
            notes.add((Double) list.getElementAt(i));
        }

        NotesEditor ne = new NotesEditor(notes);
        ne.setSize(375, 300);
        ne.setResizable(false);
        ne.setVisible(true);
        if (ne.getNotes() != null) {
            ihm.setNotesInList(ne.getNotes().toArray());
            this.createStats(ne.getNotes());
        }
    }

    public static void main(String[] args) {
        new Controler();
    }

}
