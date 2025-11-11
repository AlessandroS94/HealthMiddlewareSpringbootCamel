package it.asl.teramo.healthmiddleware.hl7.service.impl.message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v231.message.MDM_T10;
import ca.uhn.hl7v2.model.v251.message.*;
import ca.uhn.hl7v2.model.v251.segment.PID;
import it.asl.teramo.healthmiddleware.hl7.service.interfaces.message.HL7InboudHL7251;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HL7InboundHL7251 implements HL7InboudHL7251 {

    @Override
    public RSP_K11 receiver(QBP_Q11 qbpQ11) throws Exception {
        RSP_K11 rspK11 = new RSP_K11();
        try {
            String messageControlId = qbpQ11.getMSH().getMessageControlID().getValue();
            rspK11.getMSA().getAcknowledgmentCode().setValue("AA");
            rspK11.getMSH().getMessageControlID().setValue(messageControlId);
            // QPD - Query Parameter Definition
            rspK11.getQPD().getQpd1_MessageQueryName().getIdentifier().setValue("Q22");
            rspK11.getQPD().getQpd1_MessageQueryName().getCe2_Text().setValue("Find Candidates");
            rspK11.getQPD().getQpd1_MessageQueryName().getNameOfCodingSystem().setValue("HL7");
            rspK11.getQPD().getQueryTag().setValue("Q001");

            // QAK - Query Acknowledgment
            rspK11.getQAK().getQueryTag().setValue("Q001");
            rspK11.getQAK().getQueryResponseStatus().setValue("OK");

            // PID - Patient Identification (esempio di dati paziente fittizi)
            // Usa il metodo get() generico per accedere al segmento PID
            PID pid = (PID) rspK11.get("PID");
            pid.getPatientID().getIDNumber().setValue("123456");
            pid.getPatientName(0).getFamilyName().getSurname().setValue("Rossi");
            pid.getDateTimeOfBirth().getTime().setValue("19800115");
            pid.getAdministrativeSex().setValue("M");

        } catch (DataTypeException e) {
            log.warn("Errore nell'inserimento dei dati HL7 DataTypeException: " + e.getMessage());
            throw new Exception("Errore nella generazione del contenuto");
        } catch (HL7Exception e) {
            log.error("Errore nell'inserimento dei dati HL7 HL7Exception: " + e.getMessage());
            throw new Exception("Errore nella generazione del messaggio");
        }
        return rspK11;
    }

    @Override
    public ACK receiver(MDM_T02 mdmT02) {
        return null;
    }

    @Override
    public ACK receiver(MDM_T10 mdmT10) {
        return null;
    }

    @Override
    public ORL_O22 receiver(OML_O21 omlO21) {
        ORL_O22 orlO22 = new ORL_O22();
        String controlIDMessage = omlO21.getMSH().getMessageControlID().getValue();
        String applicationSending = omlO21.getMSH().getSendingApplication().getNamespaceID().getValue();
        String applicationReceiving = omlO21.getMSH().getReceivingApplication().getNamespaceID().getValue();
        try {
            orlO22.getMSA().getAcknowledgmentCode().setValue("AA");
            orlO22.getMSH().getMessageControlID().setValue(controlIDMessage);
            orlO22.getMSH().getSendingApplication().getNamespaceID().setValue(applicationSending);
            orlO22.getMSH().getReceivingApplication().getNamespaceID().setValue(applicationReceiving);
            /*
             * STUB RETURN MESSAGE
             */

        } catch (HL7Exception e) {
            log.error("Errore nell'inserimento dei dati HL7 ORL_O22 HL7Exception: " + e.getMessage());
            throw new RuntimeException("Errore nella generazione del messaggio");
        }

        return orlO22;

    }

    @Override
    public RSP_K21 receiver(QBP_Q21 qbpQ21) {
        RSP_K21 rspK21 = new RSP_K21();
        try {
            String messageControlId = qbpQ21.getMSH().getMessageControlID().getValue();
            rspK21.getMSA().getAcknowledgmentCode().setValue("AA");
            rspK21.getMSH().getMessageControlID().setValue(messageControlId);
        } catch (HL7Exception e) {
            log.error("Errore nell'inserimento dei dati HL7 RSP_K21 HL7Exception: " + e.getMessage());
            throw new RuntimeException("Errore nella generazione del messaggio");
        }
        return rspK21;
    }
}
