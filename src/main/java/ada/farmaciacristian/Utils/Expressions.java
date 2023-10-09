package ada.farmaciacristian.Utils;

public enum Expressions {
    NAME("^[A-Z][a-z']+(\\s[A-Z][a-z']+){0,5}$") ;
    private String pattern;

    private Expressions(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}
