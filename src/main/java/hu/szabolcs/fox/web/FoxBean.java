package hu.szabolcs.fox.web;

import hu.szabolcs.fox.domain.Fox;
import hu.szabolcs.fox.service.FoxService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FoxBean implements Serializable {

    @EJB
    private FoxService foxService;

    private Fox fox = new Fox();

    private List<Fox> foxes;

    @PostConstruct
    public void init() {
        loadFoxes();
    }

    public void save() {
        foxService.save(fox);
        fox = new Fox();
        loadFoxes();
    }

    public String delete(Long id) {
        foxService.delete(id);
        loadFoxes();
        return null;
    }

    private void loadFoxes() {
        foxes = foxService.findAll();
    }

    public List<Fox> getFoxes() {
        return foxes;
    }

    public Fox getFox() {
        return fox;
    }

    public void setFox(Fox fox) {
        this.fox = fox;
    }
}