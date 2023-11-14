public class Espresso extends CoffeeDecorator {

    public Espresso(Coffee coffee) { super(coffee); }

    @Override
    public String printCoffee() { return this.coffee.printCoffee() + " add espresso"; }

    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = this.coffee;
    }

    @Override
    public double Cost() { return this.coffee.Cost() + 0.35; }
}
