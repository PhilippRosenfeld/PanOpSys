package panopsys.persistence.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import panopsys.DTO.AtmoCreateDTO;
import panopsys.DTO.AtmoDTO;
import panopsys.mapper.AtmoMapper;
import panopsys.persistence.AtmoDao;
import panopsys.persistence.AtmoEntity;

import java.util.List;

@Repository
public class AtmoDaoImpl implements AtmoDao {
  private static final Logger LOG = LoggerFactory.getLogger(AtmoDaoImpl.class);
  private static final AtmoMapper MAPPER = new AtmoMapper();

  private final JdbcClient jdbcClient;

  private static final String SQL_SELECTLATEST = "SELECT temp, pressure, humidity, timestamp FROM atmo ORDER BY timestamp DESC LIMIT 1";
  private static final String SQL_INSERT = "INSERT INTO atmo (temp, pressure, humidity, timestamp) VALUES (:temp, :pressure, :humidity, :timestamp)";
  private static final String SQL_SELECTALL = "SELECT temp, pressure, humidity, timestamp FROM atmo ORDER BY timestamp";


  public AtmoDaoImpl(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }


  @Override
  public AtmoDTO getLastAtmo() {
    LOG.trace("getLastAtmo");

    return jdbcClient
        .sql(SQL_SELECTLATEST)
        .query(AtmoDTO.class)
        .optional()
        .orElse(null);
  }

  @Override
  public AtmoDTO saveAtmo(AtmoCreateDTO atmoDTO) {
    LOG.trace("saveAtmo({})", atmoDTO);

    AtmoEntity entity = MAPPER.createDTOtoEntity(atmoDTO);

    int created = jdbcClient
        .sql(SQL_INSERT)
        .param("temp", entity.getTemp())
        .param("pressure", entity.getPressure())
        .param("humidity", entity.getHumidity())
        .param("timestamp", entity.getTimestamp())
        .update();

    if (created != 1) {
      throw new DataIntegrityViolationException("%d atmo readings inserted, expected exactly 1".formatted(created));
    }

    return MAPPER.entityToDTO(entity);
  }

  @Override
  public List<AtmoDTO> getAllAtmos() {
    LOG.trace("getAllAtmos");

    return jdbcClient
        .sql(SQL_SELECTALL)
        .query(AtmoDTO.class)
        .stream().toList();
  }


}
