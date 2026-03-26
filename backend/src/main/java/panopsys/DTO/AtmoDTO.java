package panopsys.DTO;

import java.time.LocalDateTime;

/**
 * Data Transfer Object to transfer Atmosphere Sensor readings
 *
 * @param temp measured temperate
 * @param pressure measured pressure
 * @param humidity measured humidity
 */
public record AtmoDTO(
    long temp,
    long pressure,
    long humidity,
    LocalDateTime timestamp
) {
}
