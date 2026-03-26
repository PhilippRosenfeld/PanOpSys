package panopsys.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import panopsys.DTO.AtmoDTO;
import panopsys.exceptions.ValidationException;
import panopsys.service.Impl.AtmoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = AtmoEndpoint.BASE_PATH)
public class AtmoEndpoint {
  private static final Logger LOG = LoggerFactory.getLogger(AtmoEndpoint.class);
  static final String BASE_PATH = "/atmo";

  private final AtmoServiceImpl service;


  @Autowired
  public AtmoEndpoint(AtmoServiceImpl service) {
    this.service = service;
  }

@GetMapping
  public AtmoDTO getLastAtmo() {
    LOG.info("getLastAtmo");

    return service.getLastAtmo();
}

@PostMapping
public AtmoDTO saveAtmo(@RequestBody AtmoDTO atmoDTO) throws ValidationException {
    LOG.info("saveAtmo({})", atmoDTO);

    return service.saveAtmo(atmoDTO);
}

}
