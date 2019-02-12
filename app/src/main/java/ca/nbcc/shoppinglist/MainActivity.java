package ca.nbcc.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ca.nbcc.shoppinglist.models.ShoppingListItem;

public class MainActivity extends AppCompatActivity {
    public final static int TEXT_REQUEST = 1;
    private ShoppingListItem[] items;
    private List<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Programmatically creating layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

        textViews = new ArrayList<>();
        items = new ShoppingListItem[10];

        // Programmatically creating all 10 TextViews
        for (int i = 1; i < 11; i++) {
            TextView view = new TextView(this);
            view.setId(i);
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViews.add(view);
            layout.addView(view);
        }

        // Retrieving saved state
        if (savedInstanceState != null) {
            for (int i = 1; i < 11; i++) {
                if (savedInstanceState.getString("name" + i) == null) {
                    break;
                }
                items[i] = new ShoppingListItem(savedInstanceState.getInt("amount" + i), savedInstanceState.getString("name" + i));
                String output = items[i].getAmount() + " " + items[i].getName();
                textViews.get(i).setText(output);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("ShoppingList", items);
        super.onSaveInstanceState(outState);
        for(int i = 1; i < 11; i++){
            if(items[i] == null){
                break;
            }
            outState.putInt("amount" + i, items[i].getAmount());
            outState.putString("name" + i, items[i].getName());
        }
    }


    // Might finish this later, iterator is weird
//    @Override
//    public void onRestoreInstanceState(Bundle inState) {
//        super.onRestoreInstanceState(inState);
//        items = (ShoppingListItem[]) inState.getSerializable("ShoppingList");
//        for (ShoppingListItem item : items) {
//            Iterator<TextView> tvIter = textViews.iterator();
//            if (!tvIter.hasNext()){
//                return;
//            }
//
//            while (tvIter.hasNext()){
//                item = new ShoppingListItem(inState.getInt("amount"), inState.getString("name"));
//                String output = item.getAmount() + " " + item.getName();
//                textViews.get(1).setText(output);
//                tvIter.remove();
//            }
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = intent.getStringExtra(SecondActivity.EXTRA_REPLY);
                for(int i = 1; i < 11; i++){
                    if(items[i] == null){
                        items[i] = new ShoppingListItem(1, reply);
                    } else if(items[i].getName().equals(reply)){
                        items[i].setAmount(items[i].getAmount() + 1);
                    }

                    if(items[i].getName().equals(reply)){
                        String output = items[i].getAmount() + " " + items[i].getName();
                        textViews.get(i).setText(output);
                        break;
                    }
                }
            }
        }
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}
