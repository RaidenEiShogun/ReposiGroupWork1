public class Product {

    private String name;
    private Category category;
    private int howManyInCart; //сколько в корзине
    private double price;

    public Product(String name, Category category, double price) {
        this.name = name;
        this.category = category;
        this.howManyInCart = 0;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getHowManyInCart() {
        return howManyInCart;
    }

    public double getPrice() {
        return price;
    }

    //setter не всем нужен, ток для кол-ва в корзине
    public void setHowManyInCart(int howManyInCart){
        this.howManyInCart = howManyInCart;}

    //добавляем товар в корзину
    public void addToCurrentCart(int howMany){
        this.howManyInCart +=howMany;}

    //Ищем инфо на складе. Там имя и сколько на складе
    public void viewWarehouse(Warehouse warehouse){
        int howManyInWarehouse = warehouse.getNumberOfProductInWarehouse(this);
        System.out.println("Product: "+ name+"\n"+"In warehouse: "+howManyInWarehouse);
    }
}
