package Factory;

public class DellMouseFactory extends MouseFactory {

    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
