package ca.nbcc.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "ca.nbcc.shoppinglist.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void addItem(View view) {
        String item = (String)((Button)view).getText();
        Intent reply = new Intent();
        reply.putExtra(EXTRA_REPLY, item);
        setResult(RESULT_OK, reply);
        finish();
    }
}
