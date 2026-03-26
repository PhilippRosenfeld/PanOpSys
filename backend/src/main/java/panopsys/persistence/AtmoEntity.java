package panopsys.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "atmo")
public class AtmoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private long temp;
  private long pressure;
  private long humidity;
  private LocalDateTime timestamp;


  public AtmoEntity() {}

  public AtmoEntity(long temp, long pressure, long humidity, LocalDateTime timestamp) {
    this.temp = temp;
    this.pressure = pressure;
    this.humidity = humidity;
    this.timestamp = timestamp;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public long getHumidity() {
    return humidity;
  }

  public long getPressure() {
    return pressure;
  }

  public long getTemp() {
    return temp;
  }
}