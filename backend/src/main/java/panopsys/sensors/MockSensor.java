package panopsys.sensors;

import org.springframework.scheduling.annotation.Scheduled;
import panopsys.DTO.AtmoDTO;
import panopsys.exceptions.ValidationException;
import panopsys.service.AtmoService;

import java.util.Random;

public class MockSensor {
  private final AtmoService atmoService;
  private final Random random = new Random();

  public MockSensor(AtmoService atmoService) {
    this.atmoService = atmoService;
  }

  @Scheduled(fixedRate = 3000)
  public void sendMockData() {
    AtmoDTO dto = new AtmoDTO(
        random.nextInt(15,35),
        random.nextInt(950, 1050),
        random.nextInt(40, 80),
        null
    );
    try {
      atmoService.saveAtmo(dto);
    } catch (ValidationException ignored){
    }
  }


}
