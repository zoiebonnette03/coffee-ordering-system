import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;
import static java.lang.Integer.parseInt;

public class Main {

    public static int[] inventory = inventoryReader();
    public static ArrayList<String> item = new ArrayList<>();
    public static ArrayList<Double> price = new ArrayList<>();
    public static ArrayList<Coffee> Temp2 = new ArrayList<>(6);

    public static void main(String[] args) {

        Scanner CafeApplication = new Scanner(System.in);
        System.out.println("Cafe Application Running...");
        int input = 0;

        Stack<String> stack = new Stack<>();

        while(input != 1) {
            System.out.printf("Press 1 : Read Inventory\nPress 2 : Create Order\nPress 3 : Update Inventory\nPress 4 : Update Log File\nPress 5 : Exit the Application\n");
            switch(CafeApplication.nextLine()) {
                //prints inventory
                case "1":
                    System.out.println("Current items in the inventory: ");
                    inventory = inventoryReader();
                    System.out.print("Black Coffee = " + inventory[0]);
                    System.out.print("\nMilk = " + inventory[1]);
                    System.out.print("\nHotWater = " + inventory[2]);
                    System.out.print("\nEspresso = " + inventory[3]);
                    System.out.print("\nSugar = " + inventory[4]);
                    System.out.print("\nWhippedCream = " + inventory[5]);
                    System.out.print("\nPlainBagel = " + inventory[6]);
                    System.out.print("\nEverythingBagel = " + inventory[7]);
                    System.out.print("\nSesameSeedBagel = " + inventory[8]);
                    System.out.print("\nBlueberryMuffin = " + inventory[9]);
                    System.out.print("\nChocolateChipMuffin = " + inventory[10]);
                    System.out.print("\nCinnamonMuffin = " + inventory[11]);
                    System.out.print("\nGlazedDonut = " + inventory[12]);
                    System.out.print("\nSprinkleDonut = " + inventory[13]);
                    System.out.print("\nChocolateDonut = " + inventory[14]);
                    break;
                //creating coffee or food order
                case "2":
                    System.out.println("Press 1: Create Coffee Order\nPress 2: Create Food Order");
                    switch(CafeApplication.nextLine()) {
                        //create order for coffee and calls createOrder()
                        case "1":
                            Scanner userOrders = new Scanner(System.in);
                            System.out.println("Coffee order created");
                            do {
                                inventoryReader();
                                Temp2 = createOrder();

                                if(inventory[0] > 1) {
                                    //decreasing inventory of coffee by one
                                    inventory[0] = inventory[0] - 1;

                                    for (int i = 0; i < Temp2.size(); i++) {
                                        item.add(Temp2.get(i).printCoffee());
                                        price.add(Temp2.get(i).Cost());
                                    }
                                    System.out.println("Do you want to add another coffee to this order? - yes or no");
                                }
                                else {
                                    System.out.println("Out of Coffee. Visit us later.");
                                    inventory[0] = inventory[0] - 1;
                                    break;
                                }
                            } while(!userOrders.nextLine().equals("no"));

                            System.out.println("Coffee order completed");
                            stack.push(printOrder(item, price));
                            break;
                        //create order for food and calls createFood()
                            case "2":
                            userOrders = new Scanner(System.in);
                            Stack<String> food = new Stack<>();
                            System.out.println("Food order created.");
                            do {
                                inventoryReader();
                                food = createFood();
                                System.out.println("Do you want to add another food item to this order? - yes or no");
                            } while(!(userOrders.nextLine()).equals("no"));
                            System.out.println("Bagel order completed");

                            stack.push(String.valueOf(food));
                            break;
                    }
                //update inventory
                case "3":
                    inventoryWriter(inventoryReader());
                    break;
                //update log writer
                case "4":
                    try {
                        logWriter(stack);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                //exit cafe application
                case "5":
                    inventoryReader();
                    try {
                        logWriter(stack);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    input = 1;
                    break;
                default:
                    System.out.println("Invalid Selection. Please Try Again");
            }
        }

        System.out.print(printOrder(item, price));

    }

    /** createFood() creates bagel, muffin, or donut food order
     * @return Stack<String></String> stack carries all the food orders
     */
    public static Stack<String> createFood() {
        Scanner userFeedback = new Scanner(System.in);

        Stack<String> foodOrder = new Stack<>();

        double priceB = 1.15;
        double priceM = 0.75;
        double priceD = 1.00;

        int in = 0;

        while(in != 1) {
            System.out.println("Enter the following values to select your item.");
            System.out.print("1) Bagel\n2) Muffin\n3) Donut\n'e' to quit\n");
            String response = userFeedback.nextLine();

            if(response.equals("1")) {

                System.out.println("Choose bagel type:");
                System.out.print("1. Plain Bagel\n2. Everything Bagel\n3. Sesame Seed Bagel\n");

                switch (userFeedback.nextLine()) {
                    case "1":
                        item.add("Plain bagel");
                        price.add(priceB);
                        inventory[6] = inventory[6] - 1;
                        foodOrder.push(printOrder(item, price));
                        break;
                    case "2":
                        item.add("Everything bagel");
                        price.add(priceB);
                        inventory[7] = inventory[7] - 1;
                        foodOrder.push(printOrder(item, price));
                        break;
                    case "3":
                        item.add("Sesame seed bagel");
                        price.add(priceB);
                        inventory[8] = inventory[8] - 1;
                        foodOrder.push(printOrder(item, price));
                        break;
                    default:
                        System.out.println("Invalid Selection. Please Try Again");
                }
            }

            else if(response.equals("2")) {

                System.out.println("Choose muffin type:");
                System.out.print("1. Blueberry muffin\n2. Chocolate Chip Muffin\n3. Cinnamon Muffin\n");

                switch (userFeedback.nextLine()) {
                    case "1":
                        item.add("Blueberry muffin");
                        price.add(priceM);
                        inventory[9] = inventory[9] - 1;
                        foodOrder.push(printOrder(item, price));
                        break;
                    case "2":
                        item.add("Chocolate chip muffin");
                        price.add(priceM);
                        inventory[10] = inventory[10] - 1;
                        foodOrder.push(printOrder(item, price));
                        break;
                    case "3":
                        item.add("Cinnamon Muffin");
                        price.add(priceM);
                        inventory[11] = inventory[11] - 1;
                        foodOrder.push(printOrder(item, price));
                        break;
                    default:
                        System.out.println("Invalid Selection. Please Try Again");
                }
            }

            else if(response.equals("3")) {

                System.out.println("Choose donut type:");
                System.out.print("1. Glazed Donut\n2. Sprinkled Donut\n3. Chocolate Donut\n");

                switch (userFeedback.nextLine()) {
                    case "1":
                        item.add("Glazed donut");
                        price.add(priceD);
                        inventory[12] = inventory[12] - 1;
                        foodOrder.push(printOrder(item, price));
                        break;
                    case "2":
                        item.add("Sprinkled donut");
                        price.add(priceD);
                        inventory[13] = inventory[13] - 1;
                        foodOrder.push(printOrder(item, price));
                        break;
                    case "3":
                        item.add("Chocolate donut");
                        price.add(priceD);
                        inventory[14] = inventory[14] - 1;
                        foodOrder.push(printOrder(item, price));
                        break;
                    default:
                        System.out.println("Invalid Selection. Please Try Again");
                }
            }

            else if(response.equals("e")) {
                in = 1;
                break;
            }
            else {
                System.out.println("invalid input");
            }

        }
        return foodOrder;
    }

    /** Method inventoryReader() returns array of amount of items available
     * @return int[] array of number of items left in the inventory
     */
    public static int[] inventoryReader() {
        int[] avail = new int[15];
        int i = 0;

        try {
            File inventory = new File("Inventory.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inventory));
            String inv;

            while((inv = reader.readLine()) != null) {
                int equals = inv.indexOf("=");
                avail[i] = (parseInt(inv.substring(equals + 2)));
                i++;
            }

        } catch (IOException e) {
            System.out.println("No such inventory item exists.");
            throw new RuntimeException(e);
        }

        return avail;

    }

    /** Method inventoryWriter() prints the inventory and amount of each item
     * @param invent equal to the inventory array returned from inventoryReader()
     */
    public static void inventoryWriter(int[] invent) {
        invent = inventory;
        try {
            FileWriter writer = new FileWriter("Inventory.txt", false);
            writer.write("Black Coffee = " + invent[0]);
            writer.write("\nMilk = " + invent[1]);
            writer.write("\nHotWater = " + invent[2]);
            writer.write("\nEspresso = " + invent[3]);
            writer.write("\nSugar = " + invent[4]);
            writer.write("\nWhippedCream = " + invent[5]);
            writer.write("\nPlainBagel = " + invent[6]);
            writer.write("\nEverythingBagel = " + invent[7]);
            writer.write("\nSesameSeedBagel = " + invent[8]);
            writer.write("\nBlueberryMuffin = " + invent[9]);
            writer.write("\nChocolateChipMuffin = " + invent[10]);
            writer.write("\nCinnamonMuffin = " + invent[11]);
            writer.write("\nGlazedDonut = " + invent[12]);
            writer.write("\nSprinkleDonut = " + invent[13]);
            writer.write("\nChocolateDonut = " + invent[14]);
            writer.close();
            System.out.println("Succesfully update the inventory.");
        } catch (IOException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }
    }

    /** Method printOrder() prints the receipt of the order at the end of an order
     * @param item array list that holds all the items in the order
     * @param price array list that holds all the prices of the items in the order
     * @return String that is the string of the receipt from the string builder
     */
    public static String printOrder(ArrayList<String> item, ArrayList<Double> price) {
        int i;
        double totalCost = 0;
        StringBuilder cofOrder = new StringBuilder();
        cofOrder.append("RECEIPT: \n");

        for(i = 1; i < item.size(); i++) {

            cofOrder.append("Item " + (i) + ": " + item.get(i-1));
            cofOrder.append(String.format(" | Cost: %.2f\n", price.get(i-1)));
            totalCost = totalCost + price.get(i-1);
        }
        cofOrder.append(String.format("TOTAL COST OF ORDER: %.2f\n", totalCost));

        return cofOrder.toString();

    }

    /** Method logWriter() appends stack to the LogFile.txt
     * @param stack contains all the prices and items in an order together
     * @throws IOException
     */
    public static void logWriter(Stack<String> stack) throws IOException {
        FileWriter myWriter = new FileWriter("LogFile.txt", true);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        myWriter.write("\nWriting orders from stack " + formatter.format(date));
        myWriter.write("\n");
        if(stack.empty())
            System.out.println("Nothing to log. Stack is empty.");
        else {
            while (!(stack.empty())) {
                myWriter.append(stack.pop());
            }
        }
        myWriter.flush();
        myWriter.close();
    }

    /** Method createOrder() creates a coffee order and returns an array of coffees in the order
     * @return ArrayList<Coffee></Coffee> returns coffee arraylist of all the coffees and toppings in the order
     */
    public static ArrayList<Coffee> createOrder(){

        Scanner userFeedback = new Scanner(System.in);
        ArrayList<Coffee> coffeeOrder = new ArrayList<Coffee>();


        Coffee basicCoffee = new BasicCoffee();

        int in = 0;

        while (in != 1) {

            System.out.println("Enter the following values to add toppings: ");
            System.out.print("1.) milk\n2.) hot water\n3.) espresso\n4.) sugar\n5) whipped cream\ne - to complete order\n");

            switch (userFeedback.nextLine()) {

                case "1":

                    if(inventory[1] != 0) {
                        Coffee milk = new Milk(basicCoffee);
                        coffeeOrder.add(milk);
                        System.out.println("Milk added");
                        inventory[1] = inventory[1] - 1;
                    }
                    else
                        System.out.println("Out of milk. Try a different topping.");

                    break;

                case "2":
                    if(inventory[2] != 0) {
                        Coffee hotwater = new HotWater(basicCoffee);
                        coffeeOrder.add(hotwater);
                        System.out.println("Hot water added");
                        inventory[2] = inventory[2] - 1;
                    }
                    else
                        System.out.println("Out of hot water. Try a different topping.");

                    break;

                case "3":
                    if (inventory[3] != 0) {
                        Coffee espresso = new Espresso(basicCoffee);
                        coffeeOrder.add(espresso);
                        System.out.println("Espresso added");
                        inventory[3] = inventory[3] - 1;
                    }
                    else
                        System.out.println("Out of espresso. Try a different topping.");

                    break;

                case "4":
                    if(inventory[4] != 0) {
                        Coffee sugar = new Sugar(basicCoffee);
                        coffeeOrder.add(sugar);
                        System.out.println("Sugar added");
                        inventory[4] = inventory[4] - 1;
                    }
                   else
                       System.out.println("Out of sugar. Try a different topping.");

                    break;

                case "5":
                    if(inventory[5] != 0) {
                        Coffee whippedcream = new WhippedCream(basicCoffee);
                        coffeeOrder.add(whippedcream);
                        System.out.println("Whipped cream added");
                        inventory[5] = inventory[5] - 1;
                    }
                    else
                        System.out.println("Out of whipped cream. Try a different topping.");

                    break;

                case "e":
                    in = 1;
                    break;

                default:
                    System.out.println("Invalid input.");
            }

        }
        return coffeeOrder;
    }


}
