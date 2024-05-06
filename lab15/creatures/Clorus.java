package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {

    private int r;
    private int g;
    private int b;

    private final double moveEnergyCost = 0.03;
    private final double stayEnergyCost = 0.01;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     *
     */
    @Override
    public void move() {
        energy -= moveEnergyCost;
    }

    /**
     * @param c
     */
    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * @return
     */
    @Override
    public Clorus replicate() {
        Clorus baby = new Clorus(energy / 2);
        energy /= 2;
        return baby;
    }

    /**
     *
     */
    @Override
    public void stay() {
        energy -= stayEnergyCost;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        List<Direction> pilps = getNeighborsOfType(neighbors, "pilp");
        if (pilps.size() > 0) {
            Direction d = HugLifeUtils.randomEntry(pilps);
            return new Action(Action.ActionType.ATTACK, d);
        }
        if (energy >= 1) {
            Direction d = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, d);
        }
        Direction d = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, d);
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }
}
