import java.awt.*;
import java.util.ArrayList;
public class Food extends Object {
    public static final ArrayList<Food> list = new ArrayList<>();
    private static final int size = 4;
    private static final int radius = size/2;
    public Food(float posX, float posY) {
        super(posX, posY);
        life = (float) (Math.random() * 12 + 8);
        list.add(this);
    }
    @Override
    public int sizeObject() {
        return size;
    }
    @Override
    public float radiusObject() {
        return radius;
    }
    @Override
    public Color colorObject() {
        return COLOR[0];
    }
    public static void Population() {
        for (int i = 0; i < list.size(); i++) {
            Food a = list.get(i);
            if (a.life <= 0f){
                list.remove(a);
                i--;
            }
        }
    }
    public static void Init(int count) {
        for (int i =0; i < count; i++){new Food((float)(Math.random() * (sSize.width - size)), (float)(Math.random() * (sSize.height - size)));}
    }
}