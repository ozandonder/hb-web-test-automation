package globals;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {
    private static final Product INSTANCE = new Product();

    private String productName;
    private String priceText;
    private String currencyText;
    private String brandName;
    private int quantity;

    public static Product getInstance() {
        return INSTANCE;
    }

    public void reset() {
        this.productName = null;
        this.priceText = null;
        this.currencyText = null;
        this.brandName = null;
        this.quantity = 0;
    }
}