import java.awt.*;
import java.util.ArrayList;
public class Bacterium_1 extends Bacterium {
    public static final ArrayList<Bacterium_1> list = new ArrayList<>();
    private static final float reproduction = 50;
    public Bacterium_1(float posX, float posY, int sleep) {
        super(posX, posY);
        life = 25f;
        this.sleep = sleep;
        speed = 11f;
        vision = 12500f;
        list.add(this);
    }
    public Bacterium_1(float posX, float posY, Parasite parasite) {
        super(posX, posY);
        life = 25f;
        sleep = 0;
        speed = 13f;
        vision = 15000f;
        list.add(this);
        this.parasite = parasite;
    }
    public static void Init(int count) {
        for (int i = 0; i < count; i++){new Bacterium_1((float)(Math.random() * (sSize.width - 50) + 50), (float)(Math.random() * (sSize.height - 50) + 50), 5);}
    }
    public static void Evo(Bacterium b) {
        switch ((int) (Math.random() * 5)){
            case 0:
                new Bacterium_1(b.posX, b.posY, 5);
                break;
            case 1:
                new Bacterium_2(b.posX, b.posY);
                break;
            case 2:
                new Bacterium_3(b.posX, b.posY);
                break;
            case 3:
                new Parasite(b.posX, b.posY);
                break;
            case 4:
                new Mushroom(b.posX, b.posY);
                break;
        }
    }
    @Override
    public void Born() {
        for (int i=0; i < 1 + Math.random()*4 ;i++)
            new Bacterium_1(posX, posY, i);
    }
    @Override
    public void Born(Parasite parasite) {
        for (int i=0; i < 1 + Math.random()*4 ;i++)
            new Bacterium_1(posX, posY, parasite);
    }
    @Override
    public void clearList() {
        list.remove(this);
    }
    @Override
    public float getReproduction() {
        return reproduction;
    }
    @Override
    public Color colorObject() {
        return COLOR[1];
    }
    @Override
    public ArrayList<Object> listFood() {
        buf.addAll(Food.list);
        return buf;
    }
}