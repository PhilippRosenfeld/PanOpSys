package panopsys.service;

import panopsys.DTO.AtmoDTO;
import panopsys.exceptions.ValidationException;

/**
 * Interface to provide services for atmosphere sensor
 */
public interface AtmoService {

  /**
   * Gets the latest Atmo reading.
   * @return {@link AtmoDTO} representing latest reading
   */
  AtmoDTO getLastAtmo();

  AtmoDTO saveAtmo(AtmoDTO atmoDTO) throws ValidationException;
}
