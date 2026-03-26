package panopsys.persistence;

import panopsys.DTO.AtmoDTO;

/**
 * Interface to provide interactions with DB
 */
public interface AtmoDao {

  /**
   * Gets the latest Atmo reading from the DB
   *
   * @return the {@link AtmoDTO} representing the latest entry
   */
  AtmoDTO getLastAtmo();


  /**
   * Adds the Atmo reading to the DB
   *
   * @return the {@link AtmoDTO} representing the saved entry
   */
  AtmoDTO saveAtmo(AtmoDTO atmoDTO);
}

