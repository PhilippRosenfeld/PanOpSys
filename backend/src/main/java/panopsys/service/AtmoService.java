package panopsys.service;

import panopsys.DTO.AtmoCreateDTO;
import panopsys.DTO.AtmoDTO;
import panopsys.exceptions.ValidationException;

import java.util.List;

/**
 * Interface to provide services for atmosphere readings
 */
public interface AtmoService {

  /**
   * Saves the atmo reading
   * @param atmoDTO reading that is to be saved
   * @return the saved reading
   * @throws ValidationException if values could not be validated
   */
  AtmoDTO saveAtmo(AtmoCreateDTO atmoDTO) throws ValidationException;

  /**
   * Gets all atmo readings up to limit
   * @param limit maximum number of entries to return
   * @return a list of {@link AtmoDTO}
   */
  List<AtmoDTO> getAtmos(int limit);
}
