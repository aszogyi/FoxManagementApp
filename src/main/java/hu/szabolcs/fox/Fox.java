package hu.szabolcs.fox;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FOX")
public class Fox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Species must not be empty")
    private String species;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender must not be null")
    private Gender gender;

    public Fox() {
    }

    public Fox(String name, String species, Gender gender, String imageUrl) {
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
