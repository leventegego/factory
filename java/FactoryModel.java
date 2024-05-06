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
import java.util.Collections;

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
    public List<Boolean> marks;
    public List<Integer> nPart;
    public Location lPart;


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
        marks = new ArrayList<Boolean>(Collections.nCopies(nAgents, false));
        nPart = new ArrayList<Integer>(Collections.nCopies(nAgents, 0));

        int id = 0;
        for(int i = 0; i < nAssembler; ++i) registerAg(id++, "assembler", i + 1, la, i, 0);
        for(int i = 0; i < nCarrier; ++i)   registerAg(id++, "carrier",   i + 1, lc, i, size - 1);
        for(int i = 0; i < nWorker; ++i)    registerAg(id++, "worker",    i + 1, lw, size - 1, i);

        lPart = new Location(7, 7);
        add(PART, lPart.x, lPart.y);
    }

    void registerAg(int id, String type, int number, List<Integer> list, int x, int y)
    {
        String name = type + number;
        list.add(id);
        name2id.put(name, id);
        id2name.put(id, name);
        setAgPos(id, x, y);
    }

    void setParts(int id, int count)
    {
        nPart.set(id, count);
    }

    Integer getParts(int id)
    {
        return nPart.get(id);
    }

    void moveTowards(int ag, int x, int y) throws Exception
    {
        Location t = new Location(x, y);
        Location l = getAgPos(ag);
        Location[] nb = { (Location)l.clone(), (Location)l.clone(), (Location)l.clone(), (Location)l.clone() };
        nb[0].x += 1;
        nb[1].x -= 1;
        nb[2].y += 1;
        nb[3].y -= 1;

        int best = -1;
        for(int i = 0; i < 4; ++i)
            if(inGrid(nb[i]) && getAgAtPos(nb[i]) == -1 && (best == -1 ||
                nb[i].distanceEuclidean(t) < nb[best].distanceEuclidean(t)))
                best = i;

        if(best != -1)
            setAgPos(ag, nb[best]);
    }

    public String agName(int id)
    {
        return id2name.get(id);
    }

    public int agId(String name)
    {
        return name2id.get(name);
    }

}