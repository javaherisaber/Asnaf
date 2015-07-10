package ir.highroid.asnaf.database.table;

/**
 * Created by Mahdizit on 12/07/2016.
 */
abstract public class Tbl_EV_DETAIL {

    public static final String TABLE_NAME = "EV_DETAIL";

    public static final String FIELD_ID = "_id";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_DES = "des";
    public static final String FIELD_FK_EVENT_NEWS = "fk_event_news";

    public static final String[] COLUMNS = {FIELD_ID, FIELD_IMAGE, FIELD_DES,FIELD_FK_EVENT_NEWS};

}
