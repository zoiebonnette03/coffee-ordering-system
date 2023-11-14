public class HotWater extends CoffeeDecorator {

    public HotWater(Coffee coffee) { super(coffee); }

    @Override
    public String printCoffee() { return this.coffee.printCoffee() + " add hot water"; }

    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = this.coffee;
    }

    @Override
    public double Cost() { return this.coffee.Cost() + 0.0; }
}
