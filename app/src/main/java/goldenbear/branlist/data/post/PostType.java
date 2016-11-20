package goldenbear.branlist.data.post;

/**
 * Created by metaphoenix on 11/19/16.
 */
public enum PostType {
    SERVICES("Services"),
    SALE("Sales"),
    JOBS("Jobs"),
    HOUSING("Housing"),
    ACTIVITIES("Activities"),
    OTHERS("Others");

    private String type;

    PostType(String type) {
        this.type = type;
    }

    public static PostType getTypeFromPosition(int position) {
        return values()[position - 1];
    }

    @Override
    public String toString() {
        return type;
    }
}
