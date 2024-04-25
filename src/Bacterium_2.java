import java.awt.*;
import java.util.ArrayList;
public class Bacterium_2 extends Bacterium {
    public static final ArrayList<Bacterium_2> list = new ArrayList<>();
    private static final float reproduction = 100;
    public Bacterium_2(float posX, float posY) {
        super(posX, posY);
        life = 25f;
        sleep = 10;
        speed = 13f;
        vision = 17500f;
        list.add(this);
    }
    public Bacterium_2(float posX, float posY, Parasite parasite) {
        super(posX, posY);
        life = 26f;
        sleep = 5;
        speed = 15f;
        vision = 20000f;
        list.add(this);
        this.parasite = parasite;
    }
    @Override
    public float getReproduction() {
        return reproduction;
    }
    public static void Init(int count) {
        for (int i = 0; i < count; i++){new Bacterium_2((float)(Math.random() * (sSize.width - 50) + 50), (float)(Math.random() * (sSize.height - 50) + 50));}
    }
    @Override
    public void Born() {
        new Bacterium_2(posX, posY);
    }
    @Override
    public void Born(Parasite parasite) {
        new Bacterium_2(posX, posY, parasite);
    }
    @Override
    public ArrayList<Object> listFood() {
        buf.addAll(Bacterium_1.list);
        return buf;
    }
    @Override
    public void clearList() {
        list.remove(this);
    }
    @Override
    public Color colorObject() {
        return COLOR[2];
    }
}