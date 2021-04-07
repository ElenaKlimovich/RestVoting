package ru.rest.voting.to;

import ru.rest.voting.model.Restaurant;

import java.util.Objects;

public class RestaurantTo {

    private Integer id;
    private String name;
    private String address;
    private Integer votes;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, String address, Integer votes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "CafeTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", votes=" + votes +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantTo)) return false;
        RestaurantTo restaurantTo = (RestaurantTo) o;
        return Objects.equals(id, restaurantTo.id) &&
                Objects.equals(name, restaurantTo.name) &&
                Objects.equals(address, restaurantTo.address) &&
                votes == restaurantTo.votes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, votes);
    }
}