package ir.highroid.asnaf.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.sax.StartElementListener;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import ir.highroid.asnaf.database.table.Tbl_DIRECTORATE;
import ir.highroid.asnaf.database.table.Tbl_DIR_MNG;
import ir.highroid.asnaf.database.table.Tbl_EVENT_NEWS;
import ir.highroid.asnaf.database.table.Tbl_EV_DETAIL;
import ir.highroid.asnaf.database.table.Tbl_RULE;
import ir.highroid.asnaf.database.table.Tbl_UN_CALL;
import ir.highroid.asnaf.database.table.Tbl_UN_LOC;
import ir.highroid.asnaf.database.table.Tbl_Union_A;

/**
 * Created by Mahdizit on 30/06/2016.
 */
public class AsnafDB {

    private static final String DB_PATH = "/data/data/ir.highroid.asnaf/databases/";
    private static final String DATABASE_NAME = "asnaf.db";
    private static final int    DATABASE_VERSION = 1;
    private static final String TAG = "asnaf_Database";

    private final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public AsnafDB(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
        try {
            DBHelper.createDataBase();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        private Context context;

        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
        }

        private boolean checkDataBase() {
            try {
                final String DbPath = DB_PATH + DATABASE_NAME;
                final File file = new File(DbPath);//file for checking our database existence
                if (file.exists())
                {
                    Log.d(TAG, "Database already exist");
                    return true;
                }
                else
                {
                    return false;
                }
            } catch (SQLiteException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void createDataBase() throws IOException {
            boolean mDataBaseExist = checkDataBase();
            if (!mDataBaseExist) {
                this.getReadableDatabase();//makes our database readable
                try {
                    copyDataBase();//copy database from assets folder
                } catch (IOException mIOException) {
                    mIOException.printStackTrace();
                    throw new Error("Error copying database");
                } finally {
                    this.close();//closing this IO operation after accomplishing it
                }
            }
        }

        private void copyDataBase() throws IOException {
            try {
                //using an inputstram to open database from assets
                InputStream mInputStream = context.getAssets().open(DATABASE_NAME);
                String outFileName = DB_PATH + DATABASE_NAME;//output file dir
                OutputStream mOutputStream = new FileOutputStream(outFileName);//outputstream to make new database
                byte[] buffer = new byte[1024];//buffer size for write operation
                int length;
                while ((length = mInputStream.read(buffer)) > 0) {
                    mOutputStream.write(buffer, 0, length);//writing output byte by byte
                }
                mOutputStream.flush();//flushes the stream
                mOutputStream.close();
                mInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //---opens the database---
    public AsnafDB open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public synchronized void close()
    {
        DBHelper.close();
    }

    public Cursor getAllRecords(String table, String[] columns)
    {
        return db.query(table, columns, null, null, null, null, null);
    }

    public Cursor getAllUnions(){
        String sql = "select * from " + Tbl_Union_A.TABLE_NAME+ " where " + Tbl_Union_A.FIELD_TYPE + " = 0";
        return db.rawQuery(sql,null);
    }

    public String getUnionColor(int id){
        String sql = "select "+ Tbl_Union_A.FIELD_COLOR +" from " + Tbl_Union_A.TABLE_NAME + " where " + Tbl_Union_A.FIELD_ID + " = " + id;
        return executeRawQueryForString(sql);
    }

    public String getUnionColor(String title){
        String sql = "select "+ Tbl_Union_A.FIELD_COLOR +" from " + Tbl_Union_A.TABLE_NAME + " where " + Tbl_Union_A.FIELD_TITLE + " = " + title;
        return executeRawQueryForString(sql);
    }

    public String getDirectorateTitle(int unionId){
        String sql = "select "+ Tbl_DIRECTORATE.FIELD_TITLE + " from " + Tbl_DIRECTORATE.TABLE_NAME + " where " + Tbl_DIRECTORATE.FIELD_FK_UNION + " = " + unionId;
        return executeRawQueryForString(sql);
    }

    public Cursor getDirManagers(int unionId){
        String sql = "SELECT "+Tbl_DIR_MNG.FIELD_TITLE +","+Tbl_DIR_MNG.FIELD_IMAGE + "," + Tbl_DIR_MNG.FIELD_POST +
        " FROM "+ Tbl_DIR_MNG.TABLE_NAME +
        " WHERE " + Tbl_DIR_MNG.FIELD_FK_DIR +" IN ("+
                "SELECT " + Tbl_DIRECTORATE.FIELD_ID +
                " FROM " + Tbl_DIRECTORATE.TABLE_NAME +
                " WHERE " + Tbl_DIRECTORATE.FIELD_FK_UNION + " = " + unionId +")";
        return db.rawQuery(sql,null);
    }

    public Cursor getRulesTitle(int unionId){
        String sql = "select "+ Tbl_RULE.FIELD_TITLE + "," + Tbl_RULE.FIELD_ID + " from " + Tbl_RULE.TABLE_NAME + " where " + Tbl_RULE.FIELD_FK_UNION + " = " + unionId;
        return db.rawQuery(sql,null);
    }

    public String getRuleDes(int ruleId){
        String sql = "select " + Tbl_RULE.FIELD_DES + " from " + Tbl_RULE.TABLE_NAME + " where " + Tbl_RULE.FIELD_ID + " = " + ruleId;
        return executeRawQueryForString(sql);
    }

    public Cursor getEventNews(int unionId){
        String sql = "select " + Tbl_EVENT_NEWS.FIELD_ID + "," +
                Tbl_EVENT_NEWS.FIELD_TITLE + "," + Tbl_EVENT_NEWS.FIELD_IMAGE + "," +Tbl_EVENT_NEWS.FIELD_DATE +
                "," + Tbl_EVENT_NEWS.FIELD_TIME +
                " from " +Tbl_EVENT_NEWS.TABLE_NAME +
                " where " + Tbl_EVENT_NEWS.FIELD_FK_UNION + " = " + unionId;
        return db.rawQuery(sql,null);
    }

    public String getEventNewsDes(int eventId){
        String sql = "select " + Tbl_EV_DETAIL.FIELD_DES + " from " + Tbl_EV_DETAIL.TABLE_NAME +
                " where " + Tbl_EV_DETAIL.FIELD_FK_EVENT_NEWS + " = " + eventId;
        return executeRawQueryForString(sql);
    }

    public Cursor getCallInfo(int unionId){
        String sql = "SELECT "+ Tbl_UN_CALL.FIELD_PHONE + "," + Tbl_UN_CALL.FIELD_EMAIL + "," + Tbl_UN_CALL.FIELD_WEB_URL +
        "," + Tbl_UN_CALL.FIELD_TELEGRAM + "," + Tbl_UN_LOC.FIELD_ADDRESS + "," + Tbl_UN_LOC.FIELD_LATITUDE +
        "," + Tbl_UN_LOC.FIELD_LONGITUDE + "," + Tbl_UN_LOC.FIELD_MARKER +
                " FROM " + Tbl_UN_CALL.TABLE_NAME +  " JOIN " +Tbl_UN_LOC.TABLE_NAME +
                " ON " + Tbl_UN_CALL.TABLE_NAME + "." + Tbl_UN_CALL.FIELD_FK_UNION + " = " + Tbl_UN_LOC.TABLE_NAME + "." +
                Tbl_UN_LOC.FIELD_FK_UNION +
                " WHERE " +Tbl_UN_CALL.TABLE_NAME + "." + Tbl_UN_CALL.FIELD_FK_UNION + " = " + unionId;
        return db.rawQuery(sql,null);
    }

    public String getUnionDes(int unionId){
        String sql = "select "+ Tbl_Union_A.FIELD_DES + " from " + Tbl_Union_A.TABLE_NAME + " where " + Tbl_Union_A.FIELD_ID + " = " + unionId;
        return executeRawQueryForString(sql);
    }

    private String executeRawQueryForString(String sql){
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        String result = cursor.getString(0);
        cursor.close();
        return result;
    }
}