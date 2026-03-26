package panopsys.DTO;

/**
 * Data Transfer Object to transfer Atmosphere Sensor readings
 *
 * @param temp measured temperate
 * @param pressure measured pressure
 * @param humidity measured humidity
 */
public record AtmoCreateDTO(
    long temp,
    long pressure,
    long humidity
) {
}
