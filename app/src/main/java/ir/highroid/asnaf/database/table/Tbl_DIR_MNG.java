package ir.highroid.asnaf.database.table;

/**
 * Created by Mahdizit on 12/07/2016.
 */
abstract public class Tbl_DIR_MNG {

    public static final String TABLE_NAME = "DIR_MNG";

    public static final String FIELD_ID = "_id";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_POST = "post";
    public static final String FIELD_DES = "des";
    public static final String FIELD_FK_DIR = "fk_dir";

    public static final String[] COLUMNS = {FIELD_ID,FIELD_TITLE,FIELD_IMAGE, FIELD_POST, FIELD_DES, FIELD_FK_DIR};

}
