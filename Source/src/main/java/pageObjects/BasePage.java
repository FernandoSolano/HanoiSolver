package pageObjects;

import domain.Disk;
import org.openqa.selenium.By;
import selenium.SeleniumUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.Stack;

public abstract class BasePage {
    SeleniumUtils seleniumUtils = new SeleniumUtils();
    public int disksQuantity;
    public LinkedList<Disk> disks;
    public Stack<Disk> tower1, tower2, tower3;
    By tower1_route, tower2_route, tower3_route;

    public BasePage() {
        disks = new LinkedList<>();
        tower1 = new Stack<>();
        tower2 = new Stack<>();
        tower3 = new Stack<>();
    }

    public abstract void goTo();

    public abstract void setDisksQuantity();

    public abstract void makeMove(Disk disk, By tower_route);

    public int play() {
        //Some variable declarations that will be used on this method
        boolean disk1Moved;
        Disk[] towerTops;
        //First move to the tower with the same parity as the disks quantity
        if (disksQuantity % 2 == 0) {
            makeMove(tower1.peek(), tower2_route);
            tower2.push(tower1.pop());
        } else {
            makeMove(tower1.peek(), tower3_route);
            tower3.push(tower1.pop());
        }
        int numberOfMoves = 1;
        disk1Moved = true;
        //Iterate while the tower 3 disks quantity is different than the specified quantity
        while (tower3.size() != disksQuantity) {
            //Update status of the current tops, IfElse validations are required to avoid EmptyStackException
            towerTops = new Disk[4];
            if (tower1.empty()) {
                towerTops[1] = null;
            } else {
                towerTops[1] = tower1.peek();
            }
            if (tower2.empty()) {
                towerTops[2] = null;
            } else {
                towerTops[2] = tower2.peek();
            }
            if (tower3.empty()) {
                towerTops[3] = null;
            } else {
                towerTops[3] = tower3.peek();
            }
            //If the last movement was the disk one then it should move another disk
            if (disk1Moved) {
                //If there is at least one disk on every tower
                if (towerTops[1] != null && towerTops[2] != null && towerTops[3] != null) {
                    //Defining the disk 1 location
                    if (towerTops[1].getWeight() == 1) {
                        //Determining which move between towers 2 or 3 can be done
                        if (towerTops[2].getWeight() < towerTops[3].getWeight() && towerTops[2].getWeight() % 2 != towerTops[3].getWeight() % 2) {
                            //Move tower 2 to tower 3
                            makeMove(tower2.peek(), tower3_route);
                            tower3.push(tower2.pop());
                        } else if (towerTops[3].getWeight() < towerTops[2].getWeight() && towerTops[3].getWeight() % 2 != towerTops[2].getWeight() % 2) {
                            //Move tower 3 to tower 2
                            makeMove(tower3.peek(), tower2_route);
                            tower2.push(tower3.pop());
                        }
                    } else if (towerTops[2].getWeight() == 1) {
                        //Determining which move between towers 1 or 3 can be done
                        if (towerTops[1].getWeight() < towerTops[3].getWeight() && towerTops[1].getWeight() % 2 != towerTops[3].getWeight() % 2) {
                            //Move tower 1 to tower 3
                            makeMove(tower1.peek(), tower3_route);
                            tower3.push(tower1.pop());
                        } else if (towerTops[3].getWeight() < towerTops[1].getWeight() && towerTops[3].getWeight() % 2 != towerTops[1].getWeight() % 2) {
                            //Move tower 3 to tower 1
                            makeMove(tower3.peek(), tower1_route);
                            tower1.push(tower3.pop());
                        }
                    } else if (towerTops[3].getWeight() == 1) {
                        //Determining which move between towers 1 or 2 can be done
                        if (towerTops[1].getWeight() < towerTops[2].getWeight() && towerTops[1].getWeight() % 2 != towerTops[2].getWeight() % 2) {
                            //Move tower 1 to tower 2
                            makeMove(tower1.peek(), tower2_route);
                            tower2.push(tower1.pop());
                        } else if (towerTops[2].getWeight() < towerTops[1].getWeight() && towerTops[2].getWeight() % 2 != towerTops[1].getWeight() % 2) {
                            //Move tower 2 to tower 1
                            makeMove(tower2.peek(), tower1_route);
                            tower1.push(tower2.pop());
                        }
                    }
                } else if (towerTops[1] != null && towerTops[2] != null) {//If towers one and two have disks but three
                    //Defining the disk 1 location
                    if (towerTops[2].getWeight() == 1) {
                        //Move tower 1 to tower 3
                        makeMove(tower1.peek(), tower3_route);
                        tower3.push(tower1.pop());
                    }
                } else if (towerTops[1] != null && towerTops[3] != null) {//If towers one and three have disks but two
                    //Defining the disk 1 location
                    if (towerTops[3].getWeight() == 1) {
                        //Move tower one to tower two
                        makeMove(tower1.peek(), tower2_route);
                        tower2.push(tower1.pop());
                    }
                } else if (towerTops[2] != null && towerTops[3] != null) {//If towers two and three have disks but one
                    //Defining the disk 1 location
                    if (towerTops[3].getWeight() == 1) {
                        //Move tower 2 to tower 1
                        makeMove(tower2.peek(), tower1_route);
                        tower1.push(tower2.pop());
                    }
                }
                disk1Moved = false;
            } else {//If other disk was moved then it should move the disk one
                //If there is at least one disk on every tower
                if (towerTops[1] != null && towerTops[2] != null && towerTops[3] != null) {
                    //Defining the disk 1 location
                    if (towerTops[1].getWeight() == 1) {
                        //Determining which move between towers 2 or 3 can be done
                        if (towerTops[2].getWeight() % 2 == 0) {
                            //Move tower 1 to tower 2
                            makeMove(tower1.peek(), tower2_route);
                            tower2.push(tower1.pop());
                        } else if (towerTops[3].getWeight() % 2 == 0) {
                            //Move tower 1 to tower 3
                            makeMove(tower1.peek(), tower3_route);
                            tower3.push(tower1.pop());
                        }
                    } else if (towerTops[2].getWeight() == 1) {
                        //Determining which move between towers 1 or 3 can be done
                        if (towerTops[1].getWeight() % 2 == 0) {
                            //Move tower 2 to tower 1
                            makeMove(tower2.peek(), tower1_route);
                            tower1.push(tower2.pop());
                        } else if (towerTops[3].getWeight() % 2 == 0) {
                            //Move tower 2 to tower 3
                            makeMove(tower2.peek(), tower3_route);
                            tower3.push(tower2.pop());
                        }
                    } else if (towerTops[3].getWeight() == 1) {
                        //Determining which move between towers 1 or 2 can be done
                        if (towerTops[1].getWeight() % 2 == 0) {
                            //Move tower 3 to tower 1
                            makeMove(tower3.peek(), tower1_route);
                            tower1.push(tower3.pop());
                        } else if (towerTops[2].getWeight() % 2 == 0) {
                            //Move tower 3 to tower 2
                            makeMove(tower3.peek(), tower2_route);
                            tower2.push(tower3.pop());
                        }
                    }
                } else if (towerTops[1] != null && towerTops[2] != null) {//If towers one and two have disks but three
                    //Defining the disk 1 location
                    if (towerTops[1].getWeight() == 1 && towerTops[2].getWeight() % 2 == 0) {
                        //Move tower one to tower two
                        makeMove(tower1.peek(), tower2_route);
                        tower2.push(tower1.pop());
                    } else if (towerTops[1].getWeight() == 1 && towerTops[2].getWeight() % 2 == 1) {
                        //Move tower one to tower three
                        makeMove(tower1.peek(), tower3_route);
                        tower3.push(tower1.pop());
                    }
                } else if (towerTops[1] != null && towerTops[3] != null) {//If towers one and three have disks but two
                    //Defining the disk 1 location
                    if (towerTops[1].getWeight() == 1 && towerTops[3].getWeight() % 2 == 0) {
                        //Move tower one to tower three
                        makeMove(tower1.peek(), tower3_route);
                        tower3.push(tower1.pop());
                    } else if (towerTops[1].getWeight() == 1 && towerTops[3].getWeight() % 2 == 1) {
                        //Move tower one to tower two
                        makeMove(tower1.peek(), tower2_route);
                        tower2.push(tower1.pop());
                    }
                } else if (towerTops[2] != null && towerTops[3] != null) {//If towers two and three have disks but one
                    //Defining the disk 1 location
                    if (towerTops[2].getWeight() == 1 && towerTops[3].getWeight() % 2 == 0) {
                        //Move tower 2 to tower 3
                        makeMove(tower2.peek(), tower3_route);
                        tower3.push(tower2.pop());
                    } else if (towerTops[2].getWeight() == 1 && towerTops[3].getWeight() % 2 == 1) {
                        //Move tower 2 to tower 1
                        makeMove(tower2.peek(), tower1_route);
                        tower1.push(tower2.pop());
                    } else if (towerTops[3].getWeight() == 1 && towerTops[3].getWeight() % 2 == 0) {
                        //Move tower 2 to tower 3
                        makeMove(tower2.peek(), tower3_route);
                        tower3.push(tower2.pop());
                    } else if (towerTops[3].getWeight() == 1 && towerTops[3].getWeight() % 2 == 1) {
                        //Move tower 2 to tower 1
                        makeMove(tower2.peek(), tower1_route);
                        tower1.push(tower2.pop());
                    }
                }
                disk1Moved = true;
            }
            numberOfMoves++;
        }
        return numberOfMoves;
    }

    public void close() {
        seleniumUtils.close();
    }

    public File takeScreenshot() {
        return seleniumUtils.capture();
    }

}
