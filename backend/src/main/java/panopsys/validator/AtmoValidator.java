package panopsys.validator;

import org.springframework.stereotype.Component;
import panopsys.DTO.AtmoDTO;
import panopsys.exceptions.ValidationException;

@Component
public class AtmoValidator {
  public AtmoValidator() {
  }

  public void validateForPost(AtmoDTO atmoDTO) throws ValidationException {
    //TODO
  }
}
