package Controller;

import Article.*;
import Vue.Vue;
import Model.Model;

public abstract class Control {

    protected final Model model;
    protected final Vue vue;

    public Control(Model model, Vue vue) {
		this.model = model;
		this.vue = vue;
    }
}


