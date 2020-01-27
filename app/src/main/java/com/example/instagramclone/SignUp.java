package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText edtKickBoxerName;
    private EditText edtKickBoxerPunchSpeed;
    private EditText edtKickBoxerPunchPower;
    private EditText edtKickBoxerKickSpeed;
    private EditText edtKickBoxerKickPower;

    private Button btnSubmitKickBoxer;
    private Button btnSubmitBoxer;

    private TextView txtGetData;

    private Button btnGetAllKickBoxerData;

    private String allKickBoxers;

    private Button btnGetAllBoxersData;
    private String allBoxers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning the editText fields
        edtKickBoxerName = (EditText) findViewById(R.id.edtKickBoxerName);
        edtKickBoxerPunchSpeed =(EditText) findViewById(R.id.edtKickBoxerPunchSpeed);
        edtKickBoxerPunchPower = (EditText)findViewById(R.id.edtKickBoxerPunchPower);
        edtKickBoxerKickSpeed = (EditText)findViewById(R.id.edtKickBoxerKickSpeed);
        edtKickBoxerKickPower = (EditText)findViewById(R.id.edtKickBoxerKickPower);

        //Submit KickBoxer's data
        btnSubmitKickBoxer = findViewById(R.id.btnSubmitKickBoxer);
        btnSubmitKickBoxer.setOnClickListener(SignUp.this);

        //Submit Boxer's data
        btnSubmitBoxer = findViewById(R.id.btnSubmitBoxer);
        btnSubmitBoxer.setOnClickListener(SignUp.this);

        btnGetAllKickBoxerData = findViewById(R.id.btnGetAllKickBoxersData);
        btnGetAllBoxersData = findViewById(R.id.btnGetAllBoxersData);

        txtGetData = findViewById(R.id.txtGetData);
        txtGetData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("3Z9BhuwgX7", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null){

                            txtGetData.setText(object.get("name") + " - "
                                    + "Punch Speed: " + object.get("punch_speed")
                                    + " Punch Power: " + object.get("punch_power")
                                    + " Kick Speed: " + object.get("kick_speed")
                                    + " Kick Power: " + object.get("kick_power"));
                        }

                    }
                });
            }
        });

        btnGetAllKickBoxerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null){

                            if (objects.size() > 0){

                                for (ParseObject kickBoxer : objects){
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";

                                }

                                FancyToast.makeText(SignUp.this, allKickBoxers, Toast.LENGTH_LONG, FancyToast.SUCCESS,true).show();

                            } else {
                                FancyToast.makeText(SignUp.this, "Wrong", Toast.LENGTH_SHORT, FancyToast.WARNING,true).show();

                            }
                        }
                    }
                });

            }
        });

        btnGetAllBoxersData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBoxers = "";
                final ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Boxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if(objects.size() > 0){

                                for (ParseObject boxer : objects){
                                    allBoxers += boxer.get("name") + " - PS: " + boxer.get("punch_speed") + " PP: " + boxer.get("punch_power") + "\n";
                                }
                                FancyToast.makeText(SignUp.this, allBoxers, Toast.LENGTH_LONG, FancyToast.SUCCESS,true).show();

                            } else {
                                FancyToast.makeText(SignUp.this, allKickBoxers, Toast.LENGTH_LONG, FancyToast.WARNING,true).show();

                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSubmitKickBoxer){
            try {
                final ParseObject kickBoxer = new ParseObject("KickBoxer");
                kickBoxer.put("name", edtKickBoxerName.getText() + "");
                kickBoxer.put("punch_speed", Integer.parseInt(edtKickBoxerPunchSpeed.getText() + ""));
                kickBoxer.put("punch_power", Integer.parseInt(edtKickBoxerPunchPower.getText() + ""));
                kickBoxer.put("kick_speed", Integer.parseInt(edtKickBoxerKickSpeed.getText() + ""));
                kickBoxer.put("kick_power", Integer.parseInt(edtKickBoxerKickPower.getText() + ""));

                kickBoxer.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " object has been succesfully saved!", Toast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();
                        } else {
                            FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT, FancyToast.WARNING, true).show();

                        }
                    }
                });
            } catch (Exception e){
                FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT, FancyToast.WARNING, true).show();
            }
        } else if  (v.getId() == R.id.btnSubmitBoxer){
            try {
                final ParseObject boxer  = new ParseObject("Boxer");
                boxer.put("name", edtKickBoxerName.getText() + "");
                boxer.put("punch_speed", Integer.parseInt(edtKickBoxerPunchSpeed.getText() + ""));
                boxer.put("punch_power", Integer.parseInt(edtKickBoxerPunchPower.getText() + ""));

                boxer.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUp.this, boxer.get("name") + " object has been succesfully saved!", Toast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();
                        } else {
                            FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT, FancyToast.WARNING, true).show();
                        }
                    }
                });
            } catch(Exception e){
                FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT, FancyToast.WARNING, true).show();
            }

        }

    }
}
