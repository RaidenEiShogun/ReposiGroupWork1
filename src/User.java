import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Purchase> purchaseHistory;

    public User() {
        this.purchaseHistory = new ArrayList<>();
    }

    //два метода: 1) добавить заказ в историю покупок 2)получить текущую историю
    //текущая:
    public List<Purchase> getPurchaseHistory() {
        return purchaseHistory;
    }
    //2)получить текущую историю
    public void addPurchase(Purchase purchase) {
        this.purchaseHistory.add(purchase);
    }
}