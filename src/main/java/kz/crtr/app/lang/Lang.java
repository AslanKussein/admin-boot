package kz.crtr.app.lang;

public enum Lang {

    Kz("Kz"), Ru("Ru"), Ln("Ln");

    private String desc;

    Lang(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}