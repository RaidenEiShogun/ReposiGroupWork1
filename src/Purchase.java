import java.util.List;

public class Purchase {
        private List<Product> productsInCart;
        public Purchase(List<Product> productsInCart) {
            this.productsInCart = productsInCart;
        }

        public List<Product> getProductsInCart() {
            return productsInCart;
        }

        public void setProductsInCart(List<Product> productsInCart) {
            this.productsInCart = productsInCart;
        }

        public void addToCart(Product product) {
            this.productsInCart.add(product);
        }

        public void processPurchase() {
            // тут логика обработки процесса заказа
        }

        public void dismissPurchase() {
            // логике отмены заказа
        }

}
