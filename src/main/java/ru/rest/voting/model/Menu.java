package ru.rest.voting.model;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class Menu {

    private String name;
    private Double price;
    private LocalDateTime dateTime;

    public Menu() {
    }

    public Menu(String name, Double price, LocalDateTime dateTime) {
        this.name = name;
        this.price = price;
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return name.equals(menu.name) && price.equals(menu.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, dateTime);
    }
}
