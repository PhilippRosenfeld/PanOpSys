import random
import time
from .base import Sensor

class MockBME280(Sensor):
    def read(self) -> dict:
        if random.random() < 0.0:
            raise RuntimeError("Sensor Timeout")

        time.sleep(random.uniform(0.1, 0.5))
        
        return {
            "temperature": round(20 + random.random() * 5, 2),
            "humidity": round(40 + random.random() * 10, 2),
            "pressure": round(1013 + random.random() * 5, 2),
        }
