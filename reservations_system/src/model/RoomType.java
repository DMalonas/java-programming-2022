package model;

/**
 * @author DMalonas
 */
public enum RoomType {
    SINGLE("SINGLE"), DOUBLE ("DOUBLE");

    public final String label;

    private RoomType(String label) {
        this.label = label;
    }
}
