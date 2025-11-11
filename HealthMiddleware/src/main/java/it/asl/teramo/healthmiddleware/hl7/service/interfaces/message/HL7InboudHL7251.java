package it.asl.teramo.healthmiddleware.hl7.service.interfaces.message;

import ca.uhn.hl7v2.model.v231.message.MDM_T10;
import ca.uhn.hl7v2.model.v251.message.*;

public interface HL7InboudHL7251 {
    public RSP_K11 receiver(QBP_Q11 qbpQ11) throws Exception;
    public RSP_K21 receiver(QBP_Q21 qbpQ11) throws Exception;
    public ACK receiver(MDM_T02 mdmT02);
    public ACK receiver(MDM_T10 mdmT10);
    public ORL_O22 receiver(OML_O21 omlO21);
}
