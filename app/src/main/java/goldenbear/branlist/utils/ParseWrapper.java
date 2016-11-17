package goldenbear.branlist.utils;

/**
 * Created by metaphoenix on 11/16/16.
 */
public class ParseWrapper {
    private static ParseWrapper ourInstance = new ParseWrapper();

    private ParseWrapper() {

    }

    public static ParseWrapper getInstance() {
        return ourInstance;
    }
}
