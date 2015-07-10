package ir.highroid.asnaf.database.table;

/**
 * Created by Mahdizit on 12/07/2016.
 */
abstract public class Tbl_UN_CALL {

    public static final String TABLE_NAME = "UN_CALL";

    public static final String FIELD_ID = "_id";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_WEB_URL = "web_url";
    public static final String FIELD_TELEGRAM = "telegram";
    public static final String FIELD_FK_UNION = "fk_union";

    public static final String[] COLUMNS = {FIELD_ID,FIELD_PHONE,FIELD_EMAIL,FIELD_WEB_URL,FIELD_TELEGRAM,FIELD_FK_UNION};
}
