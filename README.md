# HealthMiddlewareSpringbootCamel

[![Java](https://img.shields.io/badge/Java-25-orange?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Apache Camel](https://img.shields.io/badge/Apache%20Camel-4.x-blue?style=flat-square&logo=apache)](https://camel.apache.org/)
[![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-10.x-purple?style=flat-square&logo=eclipse)](https://jakarta.ee/)
[![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)](LICENSE)

> **Feasibility study of an interoperable middleware in the healthcare system**

Studio di fattibilità di un sistema interoperabile tra cartelle cliniche specialistiche diverse, realizzato con Spring Boot e Apache Camel per l'integrazione e il routing di messaggi HL7 in ambito healthcare.

---

## 🎯 Obiettivi

- Implementare routing e trasformazione di messaggi HL7
- Garantire interoperabilità tra diverse piattaforme healthcare
- Fornire una soluzione scalabile e affidabile

---

## 🛠️ Tecnologie

| Componente | Descrizione |
|-----------|------------|
| **Framework** | Spring Boot 3.x, Spring MVC, Spring Data JPA |
| **Message Routing** | Apache Camel |
| **Standard** | Jakarta EE, HL7 v2 |
| **ORM** | Hibernate (Spring Data JPA) |
| **Code Generation** | Lombok |
| **Build Tool** | Maven |
| **Java** | JDK 25 |
---

## 🚀 Quick Start

### Prerequisiti
- **JDK 25+**
- **Maven 3.8+**
- **Docker** (opzionale, per database)

### Installazione

1. **Clone del repository**
   ```bash
   git clone https://github.com/yourusername/HealthMiddlewareSpringbootCamel.git
   cd HealthMiddlewareSpringbootCamel/HealthMiddleware
   ```

2. **Build del progetto**
   ```bash
   mvn clean package
   ```

3. **Avvio dell'applicazione**
   ```bash
   mvn spring-boot:run
   ```

L'applicazione sarà disponibile a `http://localhost:8080`

---

## 📚 Funzionalità Principali

### HL7 Processing
- ✅ Parsing e validazione messaggi HL7
- ✅ Trasformazione messaggi tra diverse versioni
- ✅ Routing intelligente basato su Camel

### Integration
- ✅ API REST per integrazione esterna
- ✅ Support per multiple healthcare endpoints
- ✅ Gestione persistenza con Spring Data JPA

### Healthcare Standards
- ✅ Conformità HL7 v2.5.1
- ✅ Jakarta EE compatibility
- ✅ HIPAA-ready architecture
