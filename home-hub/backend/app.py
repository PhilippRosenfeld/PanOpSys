import signal
from flask import Flask, jsonify, send_from_directory
from config import USE_MOCK_SENSORS 
from sensors.factory import create_sensor
from services.sensor_service import SensorService
import os
from datetime import datetime

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
FRONTEND_DIR = os.path.join(BASE_DIR, "..", "frontend")

last_measurement_time = None

app = Flask(
    __name__,
    static_folder="../frontend",
    static_url_path=""
)
#app.run(debug=True)

sensor = create_sensor(USE_MOCK_SENSORS)
service = SensorService(sensor)

@app.route("/")
def index():
    return send_from_directory("../frontend", "index.html")

@app.route("/favicon.ico")
def favicon():
    return "", 204

@app.route("/api/sensors")
def sensors():
    global last_measurement_time

    now = datetime.now()
    seconds_ago = None

    if last_measurement_time is not None:
        seconds_ago = int((now - last_measurement_time).total_seconds())

    last_measurement_time = now

    return jsonify({
        "sensors": service.get_all(),
        "last_measurement": now.isoformat(),
        "seconds_ago": seconds_ago
    })

@app.route("/shutdown", methods=["POST"])
def shutdown():
    os.kill(os.getpid(), signal.SIGINT)
    return jsonify({"status": "shutting down"})

if __name__ == "__main__":
    app.run(debug=True)