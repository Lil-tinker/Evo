import java.util.ArrayList;
public abstract class Bacterium extends Object {
    public int sleep;
    public float vision;
    public float speed;
    public Parasite parasite = null;
    public static final ArrayList<Bacterium> bactList = new ArrayList<>();
    public static final int size = 10;
    private static final int radius = size/2;
    public Bacterium(float posX, float posY) {
        super(posX, posY);
        bactList.add(this);
    }
    public abstract void Born();
    public abstract void Born(Parasite a);
    public abstract void clearList();
    public abstract float getReproduction();
    public abstract ArrayList<Object> listFood();
    @Override
    public int sizeObject() {
        return size;
    }
    @Override
    public float radiusObject() {
        return radius;
    }
    public static void Population() {
        for (int i = 0; i < bactList.size(); i++) {
            Bacterium a = bactList.get(i);
            if (a.life >= a.getReproduction()) {
                a.life-=25f;
                if (a.parasite != null) {
                    Parasite p = new Parasite(a.parasite.posX, a.parasite.posY);
                    p.bacterium = true;
                    a.Born(p);
                }
                else {
                    if (a.getClass() == Bacterium_1.class && Math.random() > 0.9) {
                        Bacterium_1.Evo(a);
                    }
                    else
                        a.Born();
                }
            }
            else if (a.life <= 0f) {
                if (a.parasite != null) {
                    a.parasite.centerX = a.parasite.posX + a.parasite.radiusObject();
                    a.parasite.centerY = a.parasite.posY + a.parasite.radiusObject();
                    a.parasite.bacterium = false;
                }
                bactList.remove(a);
                i--;
                a.clearList();
            }
        }
    }
    public static void cycleLife() {
        for (Bacterium a : bactList) {
            if (a.sleep > 0) {a.sleep--;}
            else if (a.life < a.getReproduction()) {
                buf.clear();
                ArrayList<Object> food = a.listFood();
                Object closestFood = a.closestFood(food);
                float dist = a.minDist(closestFood);
                if (dist <  a.areaControl(closestFood)) {a.eat(closestFood);}
                else {a.walk(closestFood, dist);}
                a.life--;
            }
            if (a.parasite != null) {
                a.parasite.Parasitism(a);
            }
        }
    }
    private void eat(Object closestFood){
        life+=closestFood.life;
        closestFood.life = 0f;
        sleep++;
    }
    private void walk(Object closestFood, float dist){
        double targetAngle;
        if ((closestFood != null) && (vision > dist)) {
            targetAngle = Math.atan2(closestFood.posY - posY, closestFood.posX - posX);
            if (targetAngle < 0) targetAngle = targetAngle + (float)(Math.PI * 2.0);
        }
        else{
            targetAngle = Math.random() * Math.PI * 2;
        }
        posX += (float)Math.cos(targetAngle) * speed;
        posY += (float)Math.sin(targetAngle) * speed;
        if (posX < 0)
            posX = 0;
        else if (posX+size > sSize.width)
            posX = sSize.width-size;
        if (posY < 0)
            posY = 0;
        else if (posY+size > sSize.height)
            posY = sSize.height-size;
        centerX = posX + radiusObject();
        centerY = posY + radiusObject();
    }
}