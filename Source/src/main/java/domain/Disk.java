package domain;

public class Disk {
    private int weight;
    private String route;

    public Disk() {
    }

    public Disk(int weight, String route) {
        this.weight = weight;
        this.route = route;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
