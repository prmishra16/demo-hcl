package start.myprojectbe.controller;


import org.springframework.stereotype.Controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import start.myprojectbe.model.Transaction;

@Controller
public class AuditController {
  public @RequestBody Transaction getTransaction() {
    return null;
  }
}
