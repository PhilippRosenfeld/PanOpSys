from flask import Flask, jsonify
from config import USE_MOCK_SENSORS
from sensors.factory import create_sensor
from services.sensor_service import SensorService

app = Flask(__name__)

sensor = create_sensor(USE_MOCK_SENSORS)
service = SensorService(sensor)

@app.route("/")
def index():
    return "Home Hub backend running"

@app.route("/favicon.ico")
def favicon():
    return "", 204

@app.route("/api/sensors")

def sensors():
    return jsonify(service.get_all())

if __name__ == "__main__":
    app.run(debug=True)