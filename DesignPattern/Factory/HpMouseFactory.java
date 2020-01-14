package Factory;

public class HpMouseFactory extends MouseFactory {

    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }
}
