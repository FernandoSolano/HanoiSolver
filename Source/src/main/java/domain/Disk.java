package domain;

import org.openqa.selenium.By;

public class Disk {
    private int weight;
    private By route;

    public Disk() {
    }

    public Disk(int weight, By route) {
        this.weight = weight;
        this.route = route;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public By getRoute() {
        return route;
    }

    public void setRoute(By route) {
        this.route = route;
    }
}
