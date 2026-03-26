package panopsys.mapper;

import org.springframework.stereotype.Component;
import panopsys.DTO.AtmoCreateDTO;
import panopsys.DTO.AtmoDTO;
import panopsys.persistence.AtmoEntity;

import java.time.LocalDateTime;

/**
 * Class to map atmo objects
 */
@Component
public class AtmoMapper {


  public AtmoDTO entityToDTO(AtmoEntity entity) {
    return new AtmoDTO(entity.getTemp(), entity.getPressure(), entity.getHumidity(), entity.getTimestamp()
    );
  }

  public AtmoEntity dtoToEntity(AtmoDTO dto) {
    return new AtmoEntity(dto.temp(), dto.pressure(), dto.humidity(), dto.timestamp());
  }

  public AtmoEntity createDTOtoEntity(AtmoCreateDTO dto) {
    return new AtmoEntity(dto.temp(), dto.pressure(), dto.humidity(), LocalDateTime.now());
  }

}
