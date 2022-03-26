package start.myprojectbe.Prashant;

import org.springframework.beans.factory.annotation.Autowired;

import start.myprojectbe.entity.Asset;
import start.myprojectbe.entity.Order;
import start.myprojectbe.repository.AssetRepository;
import start.myprojectbe.repository.OrderRepository;

import java.util.Objects;

@Service
public class OrderEntryServiceImpl implements OrderEntryService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AssetRepository assetRepository;

    int PROTFOLIO_ID = 123;

    @Override
    public String createOrder(OrderRequestCo orderRequestCo) throws Exception {

        orderEntryParamValidation(orderRequestCo);

        Asset asset = assetRepository.findBySecurityName(orderRequestCo.getFundName());
        int stockPrice = asset.getStockPrice() * orderRequestCo.getQty();

        Order order = new Order();
        order.setOrderQty(orderRequestCo.getQty());
        order.setOrderStatus("Submitted");
        order.setOrderPrice(stockPrice);
        order.setPortfolioId(PROTFOLIO_ID);
        order.setAssetId((int) asset.getPortfolioId());

        Order order1 ;
        try{
             order1 =  orderRepository.save(order);
        }catch (Exception ex){

            throw new Exception("Error occured while creating order");
        }


        return String.valueOf(order1.getOrderRefNo());
    }

    private void orderEntryParamValidation(OrderRequestCo orderRequestCo) throws Exception {

        if(Objects.isNull(orderRequestCo) || orderRequestCo.getFundName().isEmpty() || orderRequestCo.getTranType().isEmpty()){
            throw new Exception("Invalid Request !! Please try again!!");
        }
        //validating order type
                if(!"BUY".equalsIgnoreCase(orderRequestCo.getTranType()) &&
                !"SELL".equalsIgnoreCase(orderRequestCo.getTranType())){
            throw new Exception("Invalid Transaction Type !!");
        }
        //validating order qty
        if(orderRequestCo.getQty() < 0 ){
            throw new Exception("Please enter valid quantity");
        }

        String fundName = "HCL";  //db or cache call

        //validating order qty
        if(fundName.equalsIgnoreCase(orderRequestCo.getFundName()) ){
            throw new Exception("Please enter valid fund name");
        }
    }
}
