package panopsys.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import panopsys.DTO.AtmoCreateDTO;
import panopsys.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class AtmoValidator {
  private static final Logger LOG = LoggerFactory.getLogger(AtmoValidator.class);

  public AtmoValidator() {
  }

  public void validateForPost(AtmoCreateDTO atmoDTO) throws ValidationException {
    LOG.info("validateForPost({})", atmoDTO);
    List<String> validationErrors = new ArrayList<>();

    if (atmoDTO == null) {
      throw new ValidationException("Reading must not be null.", validationErrors);
    }

    if (atmoDTO.temp() < -30 ||  atmoDTO.temp() > 100) {
      LOG.warn("Unreasonable temp reading: {}", atmoDTO.temp());
    }
    if (atmoDTO.pressure() < 800 ||  atmoDTO.pressure() > 1200) {
      LOG.warn("Unreasonable pressure reading: {}", atmoDTO.pressure());
    }
    if (atmoDTO.humidity() < 0 ||  atmoDTO.humidity() > 150) {
      LOG.warn("Unreasonable humidity reading: {}", atmoDTO.humidity());
    }


    if (validationErrors.size() > 0) {
      throw new ValidationException("Validation for atmo post failed.", validationErrors);
    }



  }
}
