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

class FactoryView extends GridWorldView
{
    FactoryModel model;

    public FactoryView(FactoryModel model)
    {
        super(model, "Factory", 800);
        this.model = model;
        defaultFont = new Font("Calibri", Font.BOLD, 15);
        setVisible(true);
        repaint();
    }

    @Override
    public void draw(Graphics g, int x, int y, int obj)
    {
        if(obj == model.PART)
        {
            super.drawObstacle(g, x, y);
            g.setColor(Color.white);
            drawString(g, x, y, defaultFont, "P");
        }
    }

    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id)
    {
        // String label = String.valueOf(id) + " " + model.getParts(id);
        String label = String.valueOf(model.getParts(id));
        String name = model.agName(id);

        Color bg = null;
        Color fg = null;
        char t = name.charAt(0);
        switch(name.charAt(0))
        {
            case 'a':
            bg = Color.gray;
            fg = Color.white;
            break;
            case 'c':
            bg = Color.orange;
            fg = Color.black;
            break;
            case 'w':
            bg = Color.blue;
            fg = Color.white;
            break;
        }

        super.drawAgent(g, x, y, bg, -1);
        g.setColor(fg);
        super.drawString(g, x, y, defaultFont, label);
        g.setColor(Color.red);
    }

}