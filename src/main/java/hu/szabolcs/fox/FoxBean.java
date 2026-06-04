package hu.szabolcs.fox;

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

    public void save() {
        foxService.save(fox);
        fox = new Fox();
    }

    public void delete(Long id) {
        foxService.delete(id);
    }

    public List<Fox> getFoxes() {
        return foxService.findAll();
    }

    public Fox getFox() {
        return fox;
    }

    public void setFox(Fox fox) {
        this.fox = fox;
    }
}