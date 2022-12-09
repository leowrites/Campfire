package service.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/** Helper class for the database access objects.
 */
public class DaoHelper {

    /** Changes the date format and sets that date format in the ObjectMapper object.
     * @param m an ObjectMapper
     */
    public static void formatDate(ObjectMapper m) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        m.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        m.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
    }

}
