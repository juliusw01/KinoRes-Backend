package de.wi2020sebgruppe4.KinoTicketRes.repositories;

import Token.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, UUID> {



}
