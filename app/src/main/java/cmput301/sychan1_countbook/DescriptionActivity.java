package cmput301.sychan1_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.media.CamcorderProfile.get;
import static cmput301.sychan1_countbook.R.id.initialValue;
import static cmput301.sychan1_countbook.R.id.saveButton;
import static cmput301.sychan1_countbook.R.id.start;

public class DescriptionActivity extends MainActivity {

    InputAuthenticator inputAuthenticator;

    EditText name;
    EditText comment;
    EditText initialValue;
    EditText currentValue;
    TextView date;

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        int position = this.getIntent().getIntExtra("position", 0);

        name.setText(counterList.get(position).getName());
        comment.setText(counterList.get(position).getComment());
        initialValue.setText(String.valueOf(counterList.get(position).getInitialValue()));
        currentValue.setText(String.valueOf(counterList.get(position).getCurrentValue()));
        date.setText(counterList.get(position).getDate().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        name = (EditText) findViewById(R.id.nameValue);
        comment = (EditText) findViewById(R.id.commentValue);
        initialValue = (EditText) findViewById(R.id.initialValueValue);
        currentValue = (EditText) findViewById(R.id.currentValueValue);
        date = (TextView) findViewById(R.id.dateValue);

        final int position = this.getIntent().getIntExtra("position", 0);

        inputAuthenticator = new InputAuthenticator();

        Button subtractButton = (Button) findViewById(R.id.subtractButton);

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentVal = Integer.parseInt(currentValue.getText().toString());
                currentValue.setText(String.valueOf(currentVal - 1));
            }
        });

        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentVal = Integer.parseInt(currentValue.getText().toString());
                currentValue.setText(String.valueOf(currentVal + 1));
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Authenticates the input fields to ensure they are not empty.
                if (inputAuthenticator.isEmpty(name) || inputAuthenticator.isEmpty(initialValue)) {
                    if (inputAuthenticator.isEmpty(name)) {
                        name.setError("Name field cannot be empty!");
                    }
                    if (inputAuthenticator.isEmpty(initialValue)) {
                        initialValue.setError("Initial Value field cannot be empty!");
                    }
                } else if (inputAuthenticator.isLessThanZero(currentValue)) {
                    currentValue.setError("Current Value cannot be lower than zero!");
                } else {
                    counterList.get(position).setName(name.getText().toString());
                    counterList.get(position).setComment(comment.getText().toString());
                    counterList.get(position).setInitialValue(Integer.parseInt(initialValue.getText().toString()));
                    counterList.get(position).setCurrentValue(Integer.parseInt(currentValue.getText().toString()));
                    counterList.get(position).setDate();
                    saveInFile();
                    Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);

                    startActivity(intent);
                }
            }
        });

        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterList.remove(position);
                counterListView.setAdapter(counterAdapter);
                saveInFile();
                Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button resetButton = (Button) findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int originalInitialValue = Integer.parseInt(initialValue.getText().toString());

                counterList.get(position).setCurrentValue(originalInitialValue);
                saveInFile();
                Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
