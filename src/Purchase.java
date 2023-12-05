import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private final List<Product> productsInCart;
    private final Warehouse warehouse;

        public Purchase(Warehouse warehouse) {
            this.productsInCart = new ArrayList<>();
            this.warehouse = warehouse;
        }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }
    //доступ в корзину
    public void addToCart(Product product, int howMany){
            //товар на складе есть? если да, то все добавляем и забираем со склада
        if (warehouse.getNumberOfProductInWarehouse(product)>=howMany)

        {
            productsInCart.add(product);
            warehouse.removeProductsFromWarehouse(product,howMany);
        }
        else
        {//иначе кидаю ошибку (хоть как-то связана с числом)
            throw new IndexOutOfBoundsException("Not enough product: "+ product.getName() +"In a warehouse");

        }
    }

    //подумаю как обработать заказ, а пока удалить заказ
    public void dismissPurchase(){
        for (Product p:
             productsInCart) {
            int howManyInCart = p.getHowManyInCart();
            warehouse.addProductsToWarehouse(p,howManyInCart);
        }
        productsInCart.clear(); // после всего чистим корзину
    }

    public void WorkOnPurchase(){
        double howManyForAllPurchase = calcAll();

        //нужно получить инфо для заказа
        System.out.println("Processing purchase");
        System.out.println("Products in your current cart");

        for (Product p : productsInCart)
        {
            System.out.println("- " + p.getName() + " x" + p.getHowManyInCart()); //имя+сколько в корзине
        }
        System.out.println("Total Price: " + howManyForAllPurchase+
                "\nPurchase is offered. ");

        productsInCart.clear(); //после всего чистим корзину
    }

    private double calcAll() {
            double howManyForAllPurchase=0;
        for (Product p:
             productsInCart) {
            howManyForAllPurchase+=p.getPrice()*p.getHowManyInCart();
        }
        return howManyForAllPurchase;
    }
}
