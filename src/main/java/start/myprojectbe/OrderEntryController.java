package start.myprojectbe.Prashant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderEntryController {

    @Autowired
    private OrderEntryService orderEntryService;

    @PostMapping(value = "/order/entry")
    public String orderEntry(@RequestBody OrderRequestCo orderRequestCo) throws Exception {

        String resp = orderEntryService.createOrder(orderRequestCo);

        return resp;
      }
}
