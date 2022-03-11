package listener;

import java.sql.Timestamp;

public interface DateListener {

    void onDateChanged(Timestamp timestamp);
}
