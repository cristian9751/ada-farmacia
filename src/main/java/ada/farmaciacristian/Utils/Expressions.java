package ada.farmaciacristian.Utils;

public enum Expressions {
    NAME("^[A-Z][a-z']+(\\s[A-Z][a-z']+){0,5}$") ,
    STREET("^(\\d{5}|\\d{5}-\\d{4})\\s[a-zA-Z\\s\\d]+$");
    private String pattern;

    private Expressions(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}
