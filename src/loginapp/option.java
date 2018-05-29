package loginapp;

public enum option {
    admin , student;

    private option() {};

    public String value() {
        return name();
    };

    public static option fromvalue(String v) {
        return valueOf(v);
    }
}
