package ir.highroid.asnaf.database.table;

/**
 * Created by Mahdizit on 12/07/2016.
 */
abstract public class Tbl_DIR_BANNER {

    public static final String TABLE_NAME = "DIR_BANNER";

    public static final String FIELD_ID = "_id";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_FK_DIR = "fk_dir";

    public static final String[] COLUMNS = {FIELD_ID, FIELD_IMAGE, FIELD_FK_DIR};

}
