package pl.mps.quotes.enums;

import lombok.Getter;

@Getter
public enum Action {

    ONE("label One") { @Override public void action() { System.out.println("Action from " + getLabel()); } },
    TWO("label Two") { @Override public void action() { System.out.println("Action from " + getLabel()); } },
    THREE("label Three") { @Override public void action() { System.out.println("Action from " + getLabel()); } };

    private final String label;

    Action(String label) {
        this.label = label;
    }

    public void option() {
        System.out.println("Option");
    }

    public abstract void action();

}

class EnumTest {

    public static void main(String[] args) {
        Action.ONE.action();
        Action.ONE.option();
        Action.TWO.action();
        System.out.println(Action.TWO.getLabel());
        Action.THREE.action();
    }
}
