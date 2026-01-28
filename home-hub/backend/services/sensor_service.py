class SensorService:
    def __init__(self, sensor):
        self.sensor = sensor

    def get_all(self) -> dict:
        return self.sensor.read()
