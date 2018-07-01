package club.oobootcamp.refactoring;

import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private String customerName;
    private String customerAddress;
    private List<LineItem> lineItems;

    public Order(String customerName, String customerAddress, List<LineItem> lineItems) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.lineItems = lineItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public double getTotalSalesTax() {
        return getLineItems().stream().mapToDouble(LineItem::getSalesTax).sum();
    }

    public double getTotalAmountWithTax() {
        return getLineItems().stream().mapToDouble(LineItem::getTotalAmountWithTax).sum();
    }

    public String generateReceipt() {
        return new StringBuilder()
                .append(generateCustomerInfo())
                .append(generateItemsInfo())
                .append(generateTotalSalesTax())
                .append(generateTotalAmountWithTax())
                .toString();
    }

    private String generateCustomerInfo() {
        return getCustomerName() + getCustomerAddress();
    }

    private String generateItemsInfo() {
        return getLineItems().stream().map(LineItem::generateLineItemInfo).collect(Collectors.joining());
    }

    private String generateTotalAmountWithTax() {
        return "Total Amount" + '\t' + getTotalAmountWithTax();
    }

    private String generateTotalSalesTax() {
        return "Sales Tax" + '\t' + getTotalSalesTax();
    }

}
