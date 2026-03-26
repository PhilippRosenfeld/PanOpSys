package panopsys.sensors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import panopsys.DTO.AtmoCreateDTO;
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
    AtmoCreateDTO dto = new AtmoCreateDTO(
        random.nextInt(15,35),
        random.nextInt(950, 1050),
        random.nextInt(40, 80)
    );
    try {
      atmoService.saveAtmo(dto);
    } catch (ValidationException ignored){
    }
  }


}
