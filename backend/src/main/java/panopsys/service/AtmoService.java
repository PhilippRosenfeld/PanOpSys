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
   * Gets the latest atmo reading.
   * @return {@link AtmoDTO} representing latest reading
   */
  AtmoDTO getLastAtmo();

  /**
   * Saves the atmo reading
   * @param atmoDTO reading that is to be saved
   * @return the saved reading
   * @throws ValidationException if values could not be validated
   */
  AtmoDTO saveAtmo(AtmoCreateDTO atmoDTO) throws ValidationException;

  /**
   * Gets all atmo readings.
   * @return a list of {@link AtmoDTO}
   */
  List<AtmoDTO> getAllAtmos();
}
