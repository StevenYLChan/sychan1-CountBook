package cmput301.sychan1_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "data.sav";

    public ArrayList<Counter> counterList = new ArrayList<Counter>();
    public ArrayAdapter<Counter> counterAdapter;

    public TextView numberOfCountersTextView;
    public ListView counterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Floating button listener that starts the AddCounterActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingAddCounterButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCounterActivity.class);
                startActivity(intent);
            }
        });

        counterListView = (ListView) findViewById(R.id.counterListView);
        numberOfCountersTextView = (TextView) findViewById(R.id.numberOfCountersTextView);

        // Click listener to view the specifics of a particular counter
        counterListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent descriptionIntent = new Intent(MainActivity.this, DescriptionActivity.class);
                descriptionIntent.putExtra("position",position);
                startActivity(descriptionIntent);
            }
        });
    }

    /**
     * Called on application start
     */

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        loadFromFile();
        counterAdapter = new ArrayAdapter<Counter>(this,
                R.layout.list_item, counterList);
        counterListView.setAdapter(counterAdapter);

        // Displays the total number of counters
        numberOfCountersTextView.setText("Total counters: " + String.valueOf(counterListView.getAdapter().getCount()));

    }

    /**
     * Load existing save data from file
     *
     * @throws RuntimeException in IOException cases
     */

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            counterList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counterList = new ArrayList<Counter>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    /**
     * Saves data to file
     *
     * @throws RuntimeException in FileNotFoundException cases and IOException cases
     */

    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            OutputStreamWriter out = new OutputStreamWriter(fos);

            Gson gson = new Gson();
            gson.toJson(counterList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
