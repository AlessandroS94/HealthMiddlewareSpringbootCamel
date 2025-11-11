package it.asl.teramo.healthmiddleware.hl7.repository;

import it.asl.teramo.healthmiddleware.hl7.model.Hl7Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Hl7MessageRepository extends JpaRepository<Hl7Message, Long> {
}
