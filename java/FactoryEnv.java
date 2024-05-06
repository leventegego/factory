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

public class FactoryEnv extends Environment {

    static Logger logger = Logger.getLogger(FactoryEnv.class.getName());
    private FactoryModel model;
    private FactoryView  view;

    @Override
    public void init(String[] args)
    {
        int size = Integer.parseInt(args[0]);
        int nAssembler = Integer.parseInt(args[1]);
        int nCarrier = Integer.parseInt(args[2]);
        int nWorker = Integer.parseInt(args[3]);

        model = new FactoryModel(size, nAssembler, nCarrier, nWorker);
        view  = new FactoryView(model);
        model.setView(view);

        updatePercepts();

        view.repaint();
    }


    @Override
    public boolean executeAction(String name, Structure action)
    {
        try
        {
            int id = model.agId(name);
            Location l = model.getAgPos(id);

            logger.info(name + ": " + action);

            if (action.getFunctor().equals("mark"))
            {
                int x = (int)((NumberTerm)action.getTerm(0)).solve();
                model.marks.set(id, x == 1);

                view.update(l.x, l.y);
            }
            else if(action.getFunctor().equals("move_towards"))
            {
                int x = (int)((NumberTerm)action.getTerm(0)).solve();
                int y = (int)((NumberTerm)action.getTerm(1)).solve();

                model.moveTowards(id, x, y);
                updatePercept(id);
            }
            else if(action.getFunctor().equals("show"))
            {
                int count = (int)((NumberTerm)action.getTerm(0)).solve();
                model.setParts(id, count);
                view.update(l.x, l.y);
            }
            else
            {
                return false;
            }

            informAgsEnvironmentChanged();

            // Thread.sleep(300);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    void updatePercept(int id)
    {
        String name = model.agName(id);
        Location l = model.getAgPos(id);

        Literal pos = Literal.parseLiteral(
            String.format("pos(%d, %d)", l.x, l.y));
        Literal part = Literal.parseLiteral(
            String.format("pos(p, %d, %d)", model.lPart.x, model.lPart.y));

        clearPercepts(name);
        addPercept(name, pos);
        addPercept(name, part);
    }

    void updatePercepts()
    {
        // clearPercepts();
        for(int i = 0; i < model.nAgents; ++i)
            updatePercept(i);
    }




}
