package start.myprojectbe.Prashant;

public class OrderRequestCo {

    private int qty;
    private String fundName;
    private String tranType;

    public int getQty() {
        return qty;
    }

    public OrderRequestCo setQty(int qty) {
        this.qty = qty;
        return this;
    }

    public String getFundName() {
        return fundName;
    }

    public OrderRequestCo setFundName(String fundName) {
        this.fundName = fundName;
        return this;
    }

    public String getTranType() {
        return tranType;
    }

    public OrderRequestCo setTranType(String tranType) {
        this.tranType = tranType;
        return this;
    }
}
