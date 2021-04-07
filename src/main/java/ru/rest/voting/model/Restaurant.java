package ru.rest.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @NotBlank
    @Size(min = 5, max = 200)
    @Column(name = "address", nullable = false)
    private String address;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "menus", joinColumns = @JoinColumn(name = "rest_id"))
    private List<Menu> menus;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "votes", joinColumns = @JoinColumn(name = "rest_id"))
    private List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address, List<Menu> menus) {
        super(id, name);
        this.address = address;
        this.menus = menus;
    }

    public Restaurant(Integer id, String name, String address, List<Menu> menus, List<Vote> votes) {
        super(id, name);
        this.address = address;
        this.menus = menus;
        this.votes = votes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
