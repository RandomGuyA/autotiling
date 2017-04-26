package Model.MapGen;

import Model.Coordinates;

public class Cell {

    private Coordinates position;
    private Coordinates parent;
    private int value;

    public Cell(Coordinates position, Coordinates parent, int value){
        this.position=position;
        this.parent=parent;
        this.value=value;

    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public Coordinates getParent() {
        return parent;
    }

    public void setParent(Coordinates parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
