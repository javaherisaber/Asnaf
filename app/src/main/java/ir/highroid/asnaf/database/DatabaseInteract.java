package ir.highroid.asnaf.database;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.ArrayList;

import ir.highroid.asnaf.bundle.BundleAsnafMember;
import ir.highroid.asnaf.bundle.BundleCall;
import ir.highroid.asnaf.bundle.BundleNews;
import ir.highroid.asnaf.bundle.BundleRule;
import ir.highroid.asnaf.bundle.BundleUnion;

/**
 * Created by Mahdizit on 20/06/2016.
 */
public class DatabaseInteract {

    Context context;
    AsnafDB db;

    public DatabaseInteract(Context ctx){
        this.context = ctx;
        db  = new AsnafDB(context);
    }

    public ArrayList<BundleUnion> getUnions(){
        openDB();
        ArrayList<BundleUnion> unions = new ArrayList<>();
        Cursor cursor = db.getAllUnions();
        cursor.moveToFirst();
        if(cursor.getCount() != 0) {
            do {

                BundleUnion union = new BundleUnion();
                union.id = cursor.getInt(0);
                union.color = cursor.getString(1);
                union.type = cursor.getInt(2);
                union.title = cursor.getString(3);
                union.image = cursor.getString(4);
                union.des = cursor.getString(5);
                unions.add(union);

            }while (cursor.moveToNext());
        }
        db.close();
        return unions;
    }

    public String getUnionColor(int id){
        openDB();
        String result = db.getUnionColor(id);
        db.close();
        return result;
    }

    public String getUnionColor(String title){
        openDB();
        String result = db.getUnionColor(title);
        db.close();
        return result;
    }

    public String getDirectorateTitle(int unionId){
        openDB();
        String result = db.getDirectorateTitle(unionId);
        db.close();
        return result;
    }

    public ArrayList<BundleAsnafMember> getDirManagers(int unionId){
        openDB();
        ArrayList<BundleAsnafMember> dirManagers = new ArrayList<>();
        Cursor cursor = db.getDirManagers(unionId);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            do {

                BundleAsnafMember dirMng = new BundleAsnafMember();
                dirMng.title = cursor.getString(0);
                dirMng.image = cursor.getString(1);
                dirMng.post = cursor.getString(2);
                dirManagers.add(dirMng);

            }while (cursor.moveToNext());
        }
        db.close();
        return dirManagers;
    }

    public ArrayList<BundleRule> getRulesTitle(int unionId){
        openDB();
        ArrayList<BundleRule> rules = new ArrayList<>();
        Cursor cursor = db.getRulesTitle(unionId);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            do {
                BundleRule rule = new BundleRule();
                rule.title = cursor.getString(0);
                rule.id = cursor.getInt(1);
                rules.add(rule);
            }while (cursor.moveToNext());
        }
        db.close();
        return rules;
    }

    public String getRuleDes(int ruleId){
        openDB();
        String result = db.getRuleDes(ruleId);
        db.close();
        return result;
    }

    public ArrayList<BundleNews> getEventNews(int unionId){
        openDB();
        ArrayList<BundleNews> eventNews = new ArrayList<>();
        Cursor cursor = db.getEventNews(unionId);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            do {
                BundleNews eventN = new BundleNews();
                eventN.id = cursor.getInt(0);
                eventN.title = cursor.getString(1);
                eventN.image = cursor.getString(2);
                eventN.date = cursor.getString(3);
                eventN.time = cursor.getString(4);
                eventNews.add(eventN);
            }while (cursor.moveToNext());
        }
        db.close();
        return eventNews;
    }

    public String getEventNewsDes(int eventNewsId){
        openDB();
        String result = db.getEventNewsDes(eventNewsId);
        db.close();
        return result;
    }

    public BundleCall getCallInfo(int unionId){
        openDB();
        Cursor cursor = db.getCallInfo(unionId);
        cursor.moveToFirst();
        BundleCall call = new BundleCall();
        if(cursor.getCount() > 0){

                call.phone = cursor.getString(0);
                call.email = cursor.getString(1);
                call.webUrl = cursor.getString(2);
                call.telegram = cursor.getString(3);
                call.address = cursor.getString(4);
                call.latitude = cursor.getString(5);
                call.longitude = cursor.getString(6);
                call.marker = cursor.getString(7);
        }
        db.close();
        return call;
    }

    public String getUnionDes(int unionId){
        openDB();
        String result = db.getUnionDes(unionId);
        db.close();
        return result;
    }

    private void openDB(){
        try{
            db.open();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
