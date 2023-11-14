public class Sugar extends CoffeeDecorator {

    public Sugar(Coffee coffee) { super(coffee); }

    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + " add sugar";
    }

    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = this.coffee;
    }

    @Override
    public double Cost() { return this.coffee.Cost() + 0.05; }

}
