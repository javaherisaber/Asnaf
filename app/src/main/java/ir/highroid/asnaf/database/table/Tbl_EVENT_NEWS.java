package ir.highroid.asnaf.database.table;

/**
 * Created by Mahdizit on 12/07/2016.
 */
abstract public class Tbl_EVENT_NEWS {

    public static final String TABLE_NAME = "EVENT_NEWS";

    public static final String FIELD_ID = "_id";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_TIME = "time";
    public static final String FIELD_FK_UNION = "fk_union";

    public static final String[] COLUMNS = {FIELD_ID, FIELD_TITLE, FIELD_IMAGE,FIELD_DATE,FIELD_TIME,FIELD_FK_UNION};

}
