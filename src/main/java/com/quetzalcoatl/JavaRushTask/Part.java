package com.quetzalcoatl.JavaRushTask;

import javax.persistence.*;

@Entity
@Table(name = "part")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String type;

    private boolean isNecessary;

    private int quantity;

    public Part() {
    }

    public Part(String type, boolean isNecessary, int quantity) {
        this.type = type;
        this.isNecessary = isNecessary;
        this.quantity = quantity;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNecessary() {
        return isNecessary;
    }

    public void setNecessary(boolean necessary) {
        isNecessary = necessary;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", isNecessary=" + isNecessary +
                ", quantity=" + quantity +
                '}';
    }
}
