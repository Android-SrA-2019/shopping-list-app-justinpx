package ca.nbcc.shoppinglist.models;

import java.io.Serializable;

public class ShoppingListItem implements Serializable {
    // Properties
    private int amount;
    private String name;

    // Getters and Setters
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Constructors
    public ShoppingListItem(){}

    public ShoppingListItem(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }
}
