package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

//========================================================================================

    // TODO Capitalize the user username, firstname and lastname
    Mono<User> capitalizeOne(Mono<User> mono) {
        Mono<User> user = mono.map(element -> new User(element.getUsername().toUpperCase(),
                element.getFirstname().toUpperCase(), element.getLastname().toUpperCase()));
        return user;
    }

//========================================================================================

    // TODO Capitalize the users username, firstName and lastName
    Flux<User> capitalizeMany(Flux<User> flux) {

        Flux<User> upper = flux.map(element -> new User(element.getUsername().toUpperCase(),
                element.getFirstname().toUpperCase(),
                element.getLastname().toUpperCase()));
        return upper;
    }

//========================================================================================

    // TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        Flux<User> upper = flux.flatMap(element -> asyncCapitalizeUser(element));
        return upper;
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

}
