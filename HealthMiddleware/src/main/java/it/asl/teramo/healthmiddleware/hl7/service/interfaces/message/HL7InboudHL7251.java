package it.asl.teramo.healthmiddleware.hl7.service.interfaces.message;

import ca.uhn.hl7v2.model.v231.message.MDM_T10;
import ca.uhn.hl7v2.model.v251.message.*;

 public interface HL7InboudHL7251 {
     RSP_K11 receiver(QBP_Q11 qbpQ11) throws Exception;
     RSP_K21 receiver(QBP_Q21 qbpQ11) throws Exception;
     ACK receiver(MDM_T02 mdmT02);
     ACK receiver(MDM_T10 mdmT10);
     ORL_O22 receiver(OML_O21 omlO21);
}
