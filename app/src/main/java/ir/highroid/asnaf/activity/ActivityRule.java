package ir.highroid.asnaf.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.database.DatabaseInteract;

public class ActivityRule extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    Typeface tfSans;
    TextView txtRuleContent, txtNextRule;
    FrameLayout btnNextRule;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initToolbar();
        initObjectAndView();
        initContents();
        initListenerAndEvent();
    }

    private void initObjectAndView(){
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        txtRuleContent =(TextView) findViewById(R.id.txtContent);
        tfSans = Typeface.createFromAsset(getAssets(), "IRAN Sans.ttf");
//        txtNextRule = (TextView) findViewById(R.id.txtNextRule);
//        txtNextRule.setTypeface(tfSans);

        collapsingToolbarLayout.setCollapsedTitleTypeface(tfSans);
        collapsingToolbarLayout.setExpandedTitleTypeface(tfSans);
        txtRuleContent.setTypeface(tfSans);

    }

    private void initContents(){

//        txtNextRule.setText("قانون بعدی");
        int ruleId = getIntent().getIntExtra("ruleId",0);
        String ruleTitle = getIntent().getStringExtra("ruleTitle");
        DatabaseInteract db = new DatabaseInteract(getApplicationContext());
        String ruleDes = db.getRuleDes(ruleId);
        txtRuleContent.setText(ruleDes);
        collapsingToolbarLayout.setTitle(ruleTitle);
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initListenerAndEvent(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityRule.this.finish();
            }
        });
    }
}
