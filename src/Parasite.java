import java.awt.*;
import java.util.ArrayList;
public class Parasite extends Object {
    public static final ArrayList<Parasite> list = new ArrayList<>();
    private static final int size = 16;
    private static final int radius = size/2;
    private static final int maxLife = 10;
    private static final int shift = (size - Bacterium.size) / 2;
    public boolean bacterium = false;
    public Parasite(float posX, float posY) {
        super(posX, posY);
        life = maxLife;
        list.add(this);
    }
    @Override
    public int sizeObject() { return size; }
    @Override
    public float radiusObject() {
        return radius;
    }
    @Override
    public Color colorObject() {
        return COLOR[4];
    }
    public static void Population() {
        for (int i = 0; i < list.size(); i++) {
            Parasite a = list.get(i);
            if (a.life <= 0) {
                list.remove(a);
                i--;
            }
        }
    }
    public void Parasitism(Bacterium a) {
        posX = a.posX - shift;
        posY = a.posY - shift;
        a.life-=0.5f;
        if (life < maxLife)
            life++;
    }
    public static void cycleLife() {
        for (Parasite a : list) {
            if (!a.bacterium) {
                float dist;
                for (Object f : Bacterium.bactList) {
                    if (f.life == 0) continue;
                    if (((Bacterium) f).parasite != null) continue;
                    dist = a.minDist(f);
                    if (dist < a.areaControl(f)) {
                        a.bacterium = true;
                        ((Bacterium) f).parasite = a;
                        a.posX = f.posX - shift;
                        a.posY = f.posY - shift;
                    }
                }
                if (!a.bacterium)
                    a.life--;
            }
        }
    }
    public static void Init(int count) {
        for (int i = 0; i < count; i++){new Parasite((float)(Math.random() * (sSize.width - size)), (float)(Math.random() * (sSize.height - size)));}
    }
}