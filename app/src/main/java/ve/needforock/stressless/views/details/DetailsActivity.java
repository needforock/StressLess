package ve.needforock.stressless.views.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import ve.needforock.stressless.R;
import ve.needforock.stressless.models.Pending;
import ve.needforock.stressless.views.main.PendingsFragment;

public class DetailsActivity extends AppCompatActivity {

    private EditText editText;
    private Pending pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        long id = getIntent().getLongExtra(PendingsFragment.PENDING_ID,0);

        pending = Pending.findById(Pending.class, id);
        getSupportActionBar().setTitle(pending.getName());

        editText = (EditText) findViewById(R.id.descriptionEt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String description = pending.getDescription();
        if(description!=null){
            editText.setText(description);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        String description = editText.getText().toString();
        pending.setDescription(description);
        pending.save();
    }
}
