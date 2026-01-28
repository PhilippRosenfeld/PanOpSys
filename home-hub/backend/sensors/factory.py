from .mock import MockBME280
# later
# from .bme280 import Sensor BME280

def create_sensor(use_mock: bool):
    if use_mock:
        return MockBME280()
    else:
        #return BME280()
        raise NotImplementedError("Real sensor not implemented yet.")