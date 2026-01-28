import random
from .base import Sensor

class MockBME280(Sensor):
    def read(self) -> dict:
        return {
            "temperature": round(20 + random.random() * 5, 2),
            "humidity": round(40 + random.random() * 10, 2),
            "pressure": round(1013 + random.random() * 5, 2),
        }
