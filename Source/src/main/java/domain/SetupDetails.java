package domain;

public class SetupDetails {

    private int disksQty;
    private String username, password, from, to;

    public SetupDetails() {
    }

    public SetupDetails(int disksQty, String username, String password, String from, String to) {
        this.disksQty = disksQty;
        this.username = username;
        this.password = password;
        this.from = from;
        this.to = to;
    }

    public int getDisksQty() {
        return disksQty;
    }

    public void setDisksQty(int disksQty) {
        this.disksQty = disksQty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
