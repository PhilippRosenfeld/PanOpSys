# Architecture – Home Hub

## Goal

This project implements a modular home automation hub based on a Raspberry Pi. The hub collects sensor data, exposes it via a web API, and later allows controlling actuators.

**Design goals:**

* clear separation of hardware, logic, and web layer
* interchangeable sensors (mock ↔ real hardware)
* easy extensibility

---

## High-Level Overview

```
+------------------+        HTTP / JSON        +------------------+
|                  |  <-------------------->  |                  |
|     Frontend     |                          |     Backend      |
|   (HTML / JS)    |                          |   (Flask API)    |
|                  |                          |                  |
+------------------+                          +------------------+
                                                     |
                                                     |
                                              Sensor abstraction
                                                     |
                                          +-----------------------+
                                          |   Concrete sensors    |
                                          | (Mock, BME280, …)    |
                                          +-----------------------+
```

---

## Components in Detail

### 1. Frontend

**Location:** `frontend/`

**Responsibility:**

* display sensor data
* periodically poll the backend API
* later: user interaction (buttons, controls)

**Technologies:**

* HTML
* JavaScript (Fetch API)
* CSS

---

### 2. Backend

**Location:** `backend/`

The backend is the central control unit of the system.

**Responsibilities:**

* provide a REST API
* coordinate sensor access
* abstract hardware-specific logic

**Technologies:**

* Python
* Flask

---

### 3. Sensor Abstraction Layer

**Location:** `backend/sensors/`

The sensor layer defines a common interface for all sensors.

```python
class Sensor:
    def read(self) -> dict:
        pass
```

**Purpose:**

* decouple hardware from application logic
* allow development and testing without physical hardware
* enable easy replacement or addition of sensors

Examples:

* `MockBME280` – generates fake data for development
* `BME280Sensor` – real hardware implementation

---

### 4. Service Layer

**Location:** `backend/services/`

The service layer acts as an intermediary between API and sensors.

**Responsibilities:**

* encapsulate business logic
* prepare sensor data for the API
* simplify future extensions (e.g. caching, filtering)

---

## Data Flow

1. The frontend sends an HTTP request to the backend
2. The Flask API calls the sensor service
3. The service queries the active sensor implementation
4. Sensor data is returned as JSON
5. The frontend renders the data

---

## Why This Architecture?

* **Maintainability:** clean separation of concerns
* **Testability:** mock sensors allow testing without hardware
* **Scalability:** new sensors and actuators can be added easily
* **Professional structure:** aligns with common backend design patterns

---

## Future Extensions

* actuator layer (relays, LEDs, motors)
* authentication and authorization
* MQTT integration
* database for time-series sensor data
* WebSocket-based live updates
