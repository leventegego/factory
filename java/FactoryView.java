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

        public FactoryView(FactoryModel model)
        {
            super(model, "Factory", 800);
            defaultFont = new Font("Calibri", Font.BOLD, 18);
            setVisible(true);
            repaint();
        }

        @Override
        public void draw(Graphics g, int x, int y, int object)
        {
            if (object == FactoryModel.PART)
            {
                super.drawObstacle(g, x, y);
                g.setColor(Color.white);
                drawString(g, x, y, defaultFont, "P");
            }
        }

        @Override
        public void drawAgent(Graphics g, int x, int y, Color c, int id)
        {
            String label = String.valueOf(id);
            Color bg = null;
            Color fg = null;
            if(true)
            {
                // label = "Alice";
                bg = Color.blue;
                fg = Color.white;
            }
            else
            {
                // label = "Bob";
                bg  = Color.red;
                fg = Color.black;
            }


            super.drawAgent(g, x, y, bg, -1);
            g.setColor(fg);
            super.drawString(g, x, y, defaultFont, label);
        }

    }