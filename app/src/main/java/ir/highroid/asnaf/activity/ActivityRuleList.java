package ir.highroid.asnaf.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.adapter.AdapterRuleRecycler;
import ir.highroid.asnaf.bundle.BundleRule;
import ir.highroid.asnaf.database.DatabaseInteract;

public class ActivityRuleList extends AppCompatActivity {

    private RecyclerView ruleRecycler;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initObjectAndView();
        initRuleRecycler();
    }

    private void initObjectAndView(){
        ruleRecycler = (RecyclerView) findViewById(R.id.ruleRecycler);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
    }

    private void initRuleRecycler(){

        DatabaseInteract db = new DatabaseInteract(getApplicationContext());
        ArrayList<BundleRule> ruleList = db.getRulesTitle(1);

//        for (int i = 1; i < 18; i++) {
//            BundleRule bundleRule = new BundleRule();
//            bundleRule.title = "قانون شماره ی " + i;
//            bundleRule.image = "b" + i;
//            ruleList.add(bundleRule);
//        }

        AdapterRuleRecycler adapterRuleRecycler = new AdapterRuleRecycler(ruleList, getApplicationContext());
        ruleRecycler.setLayoutManager(linearLayoutManager);
        ruleRecycler.setAdapter(adapterRuleRecycler);

    }

}
