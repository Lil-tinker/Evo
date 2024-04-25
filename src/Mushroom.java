import java.awt.*;
import java.util.ArrayList;
public class Mushroom extends Object {
    private static final int s = 30;
    public static final ArrayList<Mushroom> list = new ArrayList<>();
    private int size = s;
    private static final float reproduction = 200;
    private int sleep = 5;
    public Mushroom(float posX, float posY) {
        super(posX, posY);
        life = reproduction;
        list.add(this);
    }
    @Override
    public int sizeObject() { return size; }
    @Override
    public float radiusObject() {
        if (size == 0)
            return (float)s/2;
        else
            return (float) size / 2;
    }
    @Override
    public Color colorObject() {
        return COLOR[5];
    }
    public static void Population() {
        for (int i = 0; i < list.size(); i++) {
            Mushroom a = list.get(i);
            if (a.life > reproduction*((float) a.size/s)) {
                a.life-= reproduction / 2;
                a.size += s;
                a.posX -= (float) s/2;
                a.posY -= (float) s/2;
                a.sleep = a.size / s;
            }
            else if (a.life <= 0) {
                if (a.size == s) {
                    list.remove(a);
                    i--;
                }
                else {
                    a.size-= s;
                    a.posX += (float) s/2;
                    a.posY += (float) s/2;
                    a.life = reproduction;
                    a.sleep = a.size / s;
                    if (Math.random() > 0.9) {
                        float x = a.posX+s*(int)(Math.random()*(a.size/s+2));
                        if (x == a.posX || x == a.posX+s*((float) a.size /s+2))
                            new Mushroom(x-s,a.posY-s+s*(int)(Math.random()*(a.size/s+2)));
                        else {
                            if (Math.random() > 0.5)
                                new Mushroom(x-s,a.posY-s);
                            else
                                new Mushroom(x-s,a.posY-s+s*((float) a.size /s+2));
                        }
                    }
                }
            }
        }
    }
    public static void cycleLife() {
        buf.clear();
        buf.addAll(Food.list);
        buf.addAll(Bacterium.bactList);
        for (Mushroom a : list) {
            if (a.sleep > 0) {a.sleep--;}
            else {
                float dist;
                for (Object f : buf) {
                    if (f.life == 0) continue;
                    dist = a.minDist(f);
                    if (dist < a.areaControl(f)) {
                        if (a.size > f.sizeObject()) {
                            a.life += f.life;
                            f.life = 0;
                        }
                    }
                }
                a.life-=(float)((a.size / s)*(a.size / s));
            }
        }
    }
    public static void Init(int count) {
        for (int i = 0; i < count; i++){new Mushroom((float)(Math.random() * (sSize.width - s)), (float)(Math.random() * (sSize.height - s)));}
    }
}