import java.util.Map;
import java.util.HashMap;

public class Warehouse {//керування запасами
    private Map<Product, Integer> productInWarehouse;

    public Warehouse() {
        this.productInWarehouse = new HashMap<>();
    }
    public Map<Product, Integer> getProductInWarehouse() {
        return productInWarehouse;
    }
    public void setProductInWarehouse(Map<Product, Integer> productsInStock) {
        this.productInWarehouse = productsInStock;
    }

    // Манипуляции со складом
    // 1. добавление на склад
    public void addProductsToWarehouse(Product product, int howManyProducts){
        if (productInWarehouse.containsKey(product))
        {
            int howManyProductsCurrently = productInWarehouse.get(product);
            productInWarehouse.put(product, howManyProductsCurrently + howManyProducts);
        }
        else
        { //текущего товара нет на складе
            productInWarehouse.put(product, howManyProducts);
        }
    }

    // Манипуляции со складом
    // 2. удаление со склада
    public void removeProductsFromWarehouse(Product product, int howManyProducts){
        if (productInWarehouse.containsKey(product)){//достаточно товаров чтобы удалить
            int howManyProductsCurrently = productInWarehouse.get(product);
            if (howManyProductsCurrently>howManyProducts)
            {
                productInWarehouse.put(product,howManyProductsCurrently-howManyProducts);
            }
            else if (howManyProductsCurrently==howManyProducts)
            {//== поровну товаров и у нас и на складе, можем удалить
                productInWarehouse.remove(product);
            }
            else
            {//
                throw new IndexOutOfBoundsException("Not enough products to remove.");
            }
        }
        else
        {
            throw new IndexOutOfBoundsException("There are no products to remove.");

        }
    }

    // Манипуляции со складом
    // 3. сколько товаров на складе вообще?
    public int getNumberOfProductsInWarehouse(){
        return productInWarehouse.size();
    }

    // Манипуляции со складом
    // 4. сколько конкретного товара на складе?
    public int getNumberOfProductInWarehouse(Product product){
        return productInWarehouse.getOrDefault(product, 0); //getOrDefault возвращает знач объекта по ключу
    }

}
