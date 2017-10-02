package cmput301.sychan1_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCounterActivity extends MainActivity {

    InputAuthenticator inputAuthenticator;

    EditText name;
    EditText initialValue;
    EditText currentValue;
    EditText comment;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);

        inputAuthenticator = new InputAuthenticator();

        name = (EditText) findViewById(R.id.name);
        initialValue = (EditText) findViewById(R.id.initialValue);
        currentValue = (EditText) findViewById(R.id.currentValue);
        comment = (EditText) findViewById(R.id.comment);

        // disabled on creation
        currentValue.setKeyListener(null);

        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Authenticates the input fields to ensure they are not empty.
                if(inputAuthenticator.isEmpty(name) || inputAuthenticator.isEmpty(initialValue)) {
                    if (inputAuthenticator.isEmpty(name)) {
                        name.setError("Name field cannot be empty!");
                    }
                    if (inputAuthenticator.isEmpty(initialValue)) {
                        initialValue.setError("Initial Value field cannot be empty!");
                    }
                }else{
                    counterList.add(new Counter(name.getText().toString(),
                            Integer.parseInt(initialValue.getText().toString()),
                            comment.getText().toString()));
                    counterAdapter.notifyDataSetChanged();

                    saveInFile();

                    Intent intent = new Intent(AddCounterActivity.this, MainActivity.class);

                    startActivity(intent);
                }
            }
        });
    }
}
