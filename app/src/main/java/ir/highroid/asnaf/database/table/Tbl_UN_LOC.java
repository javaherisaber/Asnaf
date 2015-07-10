package ir.highroid.asnaf.database.table;

/**
 * Created by Mahdizit on 12/07/2016.
 */
abstract public class Tbl_UN_LOC {

    public static final String TABLE_NAME = "UN_LOC";

    public static final String FIELD_ID = "";
    public static final String FIELD_ADDRESS = "address";
    public static final String FIELD_LATITUDE = "latitude";
    public static final String FIELD_LONGITUDE = "longitude";
    public static final String FIELD_MARKER = "marker";
    public static final String FIELD_FK_UNION = "fk_union";

    public static final String[] COLUMNS = {FIELD_ID, FIELD_ADDRESS, FIELD_LATITUDE,FIELD_LONGITUDE, FIELD_MARKER,FIELD_FK_UNION};

}
