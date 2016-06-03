package d.dao.easylife.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dao on 5/31/16.
 * 时间格式化
 */
public class DateUtils {

    public static String formatDate(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }
}
