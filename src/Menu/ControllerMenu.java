package Menu;

import Controller.Control;
import Vue.Vue;

import java.awt.event.*;

public class ControllerMenu extends Control implements MouseListener {

    /*
     * Il n'y a pas besoin de model pour ce controller, il s'agit juste de changer la page qui s'affiche en fonction du menu choisi
     */
    public ControllerMenu(Vue vue) {
        super(null, vue);
        vue.setMenuController(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source == vue.menuArticle) {
            vue.afficherVueArticles();
        }
        if (source == vue.menuClients) {
            vue.afficherVueClients();
        }
        if (source == vue.menuCommandes) {
            vue.afficherVueCommandes();
        }
        if (source == vue.menuFournisseurs) {
            vue.afficherVueFournisseurs();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


