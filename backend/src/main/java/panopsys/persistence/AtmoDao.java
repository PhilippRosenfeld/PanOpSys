package panopsys.persistence;

import panopsys.DTO.AtmoCreateDTO;
import panopsys.DTO.AtmoDTO;

import java.util.List;

/**
 * Interface to provide interactions with DB
 */
public interface AtmoDao {

  /**
   * Adds the Atmo reading to the DB
   *
   * @return the {@link AtmoDTO} representing the saved entry
   */
  AtmoDTO saveAtmo(AtmoCreateDTO atmoDTO);

  /**
   * Returns a list of all atmo readings in the DB up to a maximum of {@code limit}
   * @param limit maximum number of entries to return
   * @return a list with {@link AtmoDTO} entries
   */
  List<AtmoDTO> getAtmos(int limit);
}

