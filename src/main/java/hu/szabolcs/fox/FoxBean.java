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
    // Holds id selected from the UI for operations like delete where EL method parameters
    // might not be supported across all JSF/EL versions. We use a no-arg delete() that
    // reads this property.
    private Long selectedId;

    public void save() {
        foxService.save(fox);
        fox = new Fox();
    }

    // No-arg delete used by the UI with f:setPropertyActionListener
    public String delete() {
        if (selectedId != null) {
            foxService.delete(selectedId);
            selectedId = null;
        }
        return null;
    }

    public String delete(Long id) {
        System.out.println("FoxBean delete called with id: " + id);
        foxService.delete(id);
        return null;
    }

    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
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