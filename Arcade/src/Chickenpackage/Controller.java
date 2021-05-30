package Chickenpackage;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Vector;

public class Controller {

    Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public void tickBullets(Vector<Bullet> b) {
        if (!b.isEmpty()) {
            for (Bullet tempBullet : b) {
                if (tempBullet.getY() < -50 && !b.isEmpty()) {
                    b.remove(tempBullet);
                    return;
                }
                tempBullet.tick();
            }
        }
    }

    public void renderBullets(Graphics g, Vector<Bullet> b) throws IOException {
        if (!b.isEmpty()) {
            for (int i = 0; i < b.size(); i++) {
                b.elementAt(i).render(g);
            }
        }
    }

    public void tickEggs(Vector<Egg> e, int h) {
        if (!e.isEmpty()) {
            for (Egg tempEgg : e) {
                if (tempEgg.getY() > h && !e.isEmpty()) {
                    e.remove(tempEgg);
                    return;
                }
                tempEgg.tick();
            }
        }
    }

    public void renderEggs(Graphics g, Vector<Egg> e) throws IOException {
        if (!e.isEmpty()) {
            for (int i = 0; i < e.size(); i++) {
                e.elementAt(i).render(g);
            }
        }
    }

    public void tickPresents(Vector<Present> p, int h) {
        if (!p.isEmpty()) {
            for (Present tempPres : p) {
                if (tempPres.getY() > h && !p.isEmpty()) {
                    p.remove(tempPres);
                    return;
                }
                tempPres.tick();
            }
        }
    }

    public void renderPresents(Graphics g, Vector<Present> p) throws IOException {
        if (!p.isEmpty()) {
            for (int i = 0; i < p.size(); i++) {
                p.elementAt(i).render(g);
            }
        }
    }

    public void renderHearts(Graphics g, Vector<Heart> h) {
        if (!h.isEmpty()) {
            for (int i = 0; i < h.size(); i++) {
                h.elementAt(i).render(g);
            }
        }
    }
}