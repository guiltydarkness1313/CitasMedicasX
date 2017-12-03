package com.shibuyaxpress.citasmedicasx.activities;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.shibuyaxpress.citasmedicasx.R;
import com.shibuyaxpress.citasmedicasx.UserPasswordActivity;
import com.shibuyaxpress.citasmedicasx.fragments.DatePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterUserActivity extends AppCompatActivity {

    EditText inputDate;
    Calendar myCalendar = Calendar.getInstance();
    Button btnSiguiente;

    DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        inputDate.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        inputDate=findViewById(R.id.inputBirthDate);

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterUserActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void siguiente(View view){
        Intent launcher= new Intent(RegisterUserActivity.this, UserPasswordActivity.class);
        startActivity(launcher);
    }
}
