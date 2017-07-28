package Spring;

public class Error {

    private final long id;
    private final String content = "Error";

    public Error(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}