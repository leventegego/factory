import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class FactoryModel extends GridWorldModel {

    public static final int PART = 16;
    Random random = new Random(System.currentTimeMillis());

    public int size;
    public int nAgents;
    public List<Integer> la;
    public List<Integer> lc;
    public List<Integer> lw;
    public Map<Integer, String> id2name;
    public Map<String, Integer> name2id;


    public FactoryModel(int size, int nAssembler, int nCarrier, int nWorker)
    {
        super(size, size, nAssembler + nCarrier + nWorker);
        this.size = size;
        this.nAgents = nAssembler + nCarrier + nWorker;

        la = new ArrayList<Integer>();
        lc = new ArrayList<Integer>();
        lw = new ArrayList<Integer>();
        id2name = new HashMap<Integer, String>();
        name2id = new HashMap<String, Integer>();

        int id = 0;
        for(int i = 0; i < nAssembler; ++i) registerAg(id++, "assembler", i + 1);
        for(int i = 0; i < nCarrier; ++i)   registerAg(id++, "carrier", i + 1);
        for(int i = 0; i < nWorker; ++i)    registerAg(id++, "worker", i + 1);


        for(int i = 0; i < nAgents; ++i)
        {
            setAgPos(i, i, 0);
        }

        // add(PART, 3, 0);
        // add(PART, GSize-1, 0);
        // add(PART, 1, 2);
        // add(PART, 0, GSize-2);
        // add(PART, GSize-1, GSize-1);
    }

    void registerAg(int id, String type, int number)
    {
        if(type.equals("assembler")) la.add(id);
        if(type.equals("carrier"))   lc.add(id);
        if(type.equals("worker"))    lw.add(id);

        String name = type + number;
        name2id.put(name, id);
        id2name.put(id, name);
    }

    void moveTowards(int ag, int x, int y) throws Exception
    {

        Location l = getAgPos(ag);
        Location l_ = l;

        if (l.x < x) l_.x++;
        if (l.x > x) l_.x--;
        if (l.y < y) l_.y++;
        if (l.y > y) l_.y--;

        if(getAgAtPos(l_) != -1)
            return;

        setAgPos(ag, l_);
    }

    String agName(int id)
    {
        return id2name.get(id);
    }

    int agId(String name)
    {
        return name2id.get(name);
    }

}