package goldenbear.branlist; /**
 * Created by metaphoenix on 11/14/16.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskEntryData {
    private String title, description, submitter, date;
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public TaskEntryData(String title, String description, String submitter) {
        this.title = title;
        this.description = description;
        this.submitter = submitter;
        Date date = new Date();
        this.date = dateFormat.format(date);
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getSubmitter() {
        return submitter;
    }

    public String getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
