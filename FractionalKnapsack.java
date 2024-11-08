import java.util.Arrays;

class Item {
    double value, weight;

    // Constructor
    public Item(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    // Function to get maximum value in the knapsack
    public static double fractionalKnapsack(Item[] items, double capacity) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.value / b.weight, a.value / a.weight));

        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity >= item.weight) {
                // If the item can be added fully
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // Add the fractional part of the item
                totalValue += (item.value / item.weight) * capacity;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(70, 10),
            new Item(120, 20),
            new Item(140, 30)
        };

        double capacity = 50;
        double maxValue = fractionalKnapsack(items, capacity);
        System.out.println("Maximum Value in Knapsack: " + maxValue);
    }
}
