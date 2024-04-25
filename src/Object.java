import java.awt.*;
import java.util.ArrayList;
public abstract class Object {
    public static final Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    public float posX;
    public float posY;
    public float centerX;
    public float centerY;
    public float life;
    public static final ArrayList<Object> buf = new ArrayList<>();
    public static Color[] COLOR = {
            new Color(150, 50, 150, 255),
            new Color(50, 150, 50, 255),
            new Color(255, 100, 120, 255),
            new Color(200, 200, 100, 255),
            new Color(0, 0, 0, 255),
            new Color(100, 150, 200, 255)
    };
    public Object(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        centerX = posX + radiusObject();
        centerY = posY + radiusObject();
    }
    public abstract int sizeObject();
    public abstract float radiusObject();
    public abstract Color colorObject();
    public Object closestFood(ArrayList<Object> objects) {
        Object food = null;
        float minFoodDist = 1000000;
        for (Object f : objects) {
            if (f.life <= 0f) continue;
            float dist = minDist(f);
            if (dist < minFoodDist) {
                minFoodDist = dist;
                food = f;
            }
        }
        return food;
    }
    public float minDist(Object f) {
        if (f == null)
            return 4000000;
        return (centerX-f.centerX)*(centerX-f.centerX) + (centerY-f.centerY)*(centerY-f.centerY);
    }
    public float areaControl(Object f) {
        if (f == null)
            return 4000000;
        return ((float) sizeObject() /2 + (float) f.sizeObject()/2)*((float) sizeObject()/2 + (float) f.sizeObject()/2);
    }
    public static ArrayList<Object> getObjects() {
        buf.clear();
        buf.addAll(Mushroom.list);
        buf.addAll(Parasite.list);
        buf.addAll(Bacterium.bactList);
        buf.addAll(Food.list);
        return buf;
    }
    public static void cycle(int count) {
        Mushroom.cycleLife();
        Food.Init(count);
        Bacterium.cycleLife();
        Parasite.cycleLife();
        Bacterium.Population();
        Parasite.Population();
        Mushroom.Population();
        Food.Population();
    }
}