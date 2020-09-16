package openjge;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    public String name;
    public Component[] components;

    public GameObject(String name, Component... components) {
        this.name = name;
        this.components = components;
    }

    //region Useful Methods
    public <T extends Component> T[] getComponents() {
        List<T> array = new ArrayList<T>(2);

        for (Component component : components)
            try {
                array.add((T) component);
            } catch (ClassCastException e) {}

        return (T[]) array.toArray();
    }

    public <T extends Component> boolean hasComponent() {
        boolean has = false;

        for (Component component : components)
            try {
                T temp = (T) component;
                has = true;
            } catch (ClassCastException e) {}

        return has;
    }

    public <T extends Component> T getComponent() {
        T out = null;

        for (Component component : components)
            try {
                out = (T) component;
            } catch (ClassCastException e) {}

        return out;
    }
    //endregion
}
