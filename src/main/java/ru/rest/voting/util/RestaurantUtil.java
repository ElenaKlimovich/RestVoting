package ru.rest.voting.util;

import ru.rest.voting.model.Restaurant;
import ru.rest.voting.to.RestaurantTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public RestaurantUtil() {
    }

    public static List<RestaurantTo> getAllRestaurantTosWithVotes(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(r->createRestaurantTo(r))
                .collect(Collectors.toList());
    }

    public static RestaurantTo createRestaurantTo(Restaurant r) {
        return new RestaurantTo(r.id(), r.getName(), r.getAddress(), r.getVotes().size());
    }
}
