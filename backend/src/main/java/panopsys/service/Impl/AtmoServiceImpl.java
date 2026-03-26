package panopsys.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panopsys.exceptions.ValidationException;
import panopsys.persistence.AtmoDao;
import panopsys.DTO.AtmoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import panopsys.service.AtmoService;
import panopsys.validator.AtmoValidator;

@Service
public class AtmoServiceImpl implements AtmoService {
  private static final Logger LOG = LoggerFactory.getLogger(AtmoServiceImpl.class);
  private final AtmoDao dao;
  private final AtmoValidator validator;

  @Autowired
  public AtmoServiceImpl(AtmoDao dao, AtmoValidator validator) {
    this.dao = dao;
    this.validator = validator;
  }

  public AtmoDTO getLastAtmo() {
    LOG.info("getLastAtmo");

    return dao.getLastAtmo();
  }

  @Override
  public AtmoDTO saveAtmo(AtmoDTO atmoDTO) throws ValidationException {
    LOG.info("saveAtmo({})", atmoDTO);
    validator.validateForPost(atmoDTO);

    return dao.saveAtmo(atmoDTO);
  }
}
