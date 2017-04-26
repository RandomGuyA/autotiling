package Model;

import Model.MapGen.Map;

public class Model {

    private Map map;

    public Model (){
        map = new Map();
    }

    public Map getMap() {
        return map;
    }
}
