package de.wi2020sebgruppe4.KinoTicketRes.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgruppe4.KinoTicketRes.model.User;

public interface UserRepository extends CrudRepository<User, UUID>{

    Optional<User> findUserByEmail(String email);

    void createPasswordResetTokenForUser(Optional<User> user, String token);//sollte nicht funktionieren
}
