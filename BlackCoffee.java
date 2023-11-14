public class BlackCoffee extends CoffeeDecorator {

    public BlackCoffee(Coffee coffee){ super(coffee); }

    @Override
    public String printCoffee() { return this.coffee.printCoffee(); }

    @Override
    public void addTopping(Coffee coffee) {
        coffee.addTopping(this.coffee);
        this.coffee = coffee;
        instructions();
    }

    public void instructions() { System.out.println("Pour coffee from pot into cup"); }

    @Override
    public double Cost() { return this.coffee.Cost(); }

}
