const API_SENSORS = "/api/sensors";
const API_SHUTDOWN = "/shutdown";

const POLL_INTERVAL_MS = 5000;

// Fetch sensor data from the backend API
function updateSensors() {
    fetch(API_SENSORS)
        .then(response => {
            if (!response.ok) {
                throw new Error("API error");
            }
            return response.json();
        })
        .then(data => {
            renderSensors(data);
        })
        .catch(error => {
            console.error(error);
            showBackendError();
        });
}

// Render sensor data to the page
function renderSensors(data) {
    const sensors = data.sensors ?? {};

    setText("temperature", formatValue(sensors.temperature, "°C"));
    setText("humidity", formatValue(sensors.humidity, "%"));
    setText("pressure", formatValue(sensors.pressure, "hPa"));

    if (data.seconds_ago !== null && data.seconds_ago !== undefined) {
        setText(
            "timeInfo",
            `Last measurement ${formatSeconds(data.seconds_ago)} ago`
        );
    } else {
        setText("timeInfo", "First measurement just taken");
    }
}

//shutdown server
function shutdownServer() {
    if (!confirm("Really shut down the server?")) return;

    fetch(API_SHUTDOWN, { method: "POST" })
        .then(() => {
            alert("Server is shutting down");
        })
        .catch(() => {
            alert("Server already offline");
        });
}

/* ------------------ helpers ------------------ */

function setText(id, text) {
    const el = document.getElementById(id);
    if (el) el.innerText = text;
}

function formatValue(value, unit) {
    if (value === null || value === undefined) return "N/A";
    return `${value} ${unit}`;
}

function formatSeconds(seconds) {
    if (seconds < 60) return `${seconds} seconds`;
    if (seconds < 3600) return `${Math.floor(seconds / 60)} minutes`;
    return `${Math.floor(seconds / 3600)} hours`;
}

function showBackendError() {
    setText("timeInfo", "Backend not reachable");
}

/* ------------------ init ------------------ */

document.addEventListener("DOMContentLoaded", () => {
    updateSensors();
    setInterval(updateSensors, POLL_INTERVAL_MS);
});
