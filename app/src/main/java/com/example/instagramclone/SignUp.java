package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText edtKickBoxerName;
    private EditText edtKickBoxerPunchSpeed;
    private EditText edtKickBoxerPunchPower;
    private EditText edtKickBoxerKickSpeed;
    private EditText edtKickBoxerKickPower;

    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtKickBoxerName = (EditText) findViewById(R.id.edtKickBoxerName);
        edtKickBoxerPunchSpeed =(EditText) findViewById(R.id.edtKickBoxerPunchSpeed);
        edtKickBoxerPunchPower = (EditText)findViewById(R.id.edtKickBoxerPunchPower);
        edtKickBoxerKickSpeed = (EditText)findViewById(R.id.edtKickBoxerKickSpeed);
        edtKickBoxerKickPower = (EditText)findViewById(R.id.edtKickBoxerKickPower);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(SignUp.this);
    }

    public void btnSubmitClicked(View view){

//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if (e == null){
//                    Toast.makeText(SignUp.this, "boxer object is saved succesfully", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });



    }

    @Override
    public void onClick(View v) {

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




    }
}
