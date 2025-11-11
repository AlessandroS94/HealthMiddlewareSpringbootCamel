package it.asl.teramo.healthmiddleware.hl7.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hl7Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String messageType; // e.g., MDM^T02

    @Lob
    @Column(nullable = false)
    private String payload; // full XML payload

    @Column(nullable = false)
    private OffsetDateTime receivedAt;

    public Hl7Message(String type, String xmlPayload, OffsetDateTime now) {
        this.messageType = type;
        this.payload = xmlPayload;
        this.receivedAt = now;
    }
}
