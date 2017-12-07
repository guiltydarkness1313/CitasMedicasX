package com.shibuyaxpress.citasmedicasx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.shibuyaxpress.citasmedicasx.fragments.DoctorListFragment;
import com.shibuyaxpress.citasmedicasx.models.Citas;
import com.shibuyaxpress.citasmedicasx.models.Medicos;
import com.shibuyaxpress.citasmedicasx.models.Pacientes;
import com.shibuyaxpress.citasmedicasx.models.ResponseMessage;
import com.shibuyaxpress.citasmedicasx.services.ApiService;
import com.shibuyaxpress.citasmedicasx.services.ApiServiceGenerator;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalRegisterDateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    EditText description;
    EditText fecha;
    EditText time;
    Button btnRegister;
    String date,timer;
    private static final String TAG = FinalRegisterDateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_register_date);
        fecha=findViewById(R.id.input_date_register);
        time=findViewById(R.id.txt_time_register);
        description=findViewById(R.id.input_descripcion_register);
        btnRegister=findViewById(R.id.btn_register_date);
        Calendar now = Calendar.getInstance();


        final TimePickerDialog tpd=TimePickerDialog.newInstance(
                FinalRegisterDateActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),true
        );

        final DatePickerDialog dpd = DatePickerDialog.newInstance(
                FinalRegisterDateActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tpd.show(getFragmentManager(),"TimePicker");
            }
        });
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String desc=description.getText().toString();
                if(timer.isEmpty()||date.isEmpty()||desc.isEmpty()){
                    Toast.makeText(FinalRegisterDateActivity.this, "Nombre y Precio son campos requeridos", Toast.LENGTH_SHORT).show();
                    return;
                }
                String fecha=date+" "+timer;
                ApiService service= ApiServiceGenerator.createService(ApiService.class);
                Call<Citas>call=service.createDatewithDoctor(fecha,desc, Medicos.getInstance().getId(), Pacientes.getInstance().getId());
                Log.d(TAG,Pacientes.getInstance().getId()+" "+Medicos.getInstance().getId());
                call.enqueue(new Callback<Citas>() {
                    @Override
                    public void onResponse(Call<Citas> call, Response<Citas> response) {
                        try {

                            int statusCode = response.code();
                            Log.d(TAG, "HTTP status code: " + statusCode);

                            if (response.isSuccessful()) {

                                Citas responseMessage = response.body();
                                Log.d(TAG, "responseMessage: " + responseMessage);

                                Toast.makeText(FinalRegisterDateActivity.this, "se registro", Toast.LENGTH_LONG).show();
                                finish();

                            } else {
                                Log.e(TAG, "onError: " + response.errorBody().string());
                                throw new Exception("Error en el servicio");
                            }

                        } catch (Throwable t) {
                            try {
                                Log.e(TAG, "onThrowable: " + t.toString(), t);
                                Toast.makeText(FinalRegisterDateActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            } catch (Throwable x) {
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Citas> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String mon,day;
        if((monthOfYear+1)<10){
            mon="0"+(monthOfYear+1);
        }else{
            mon=String.valueOf((monthOfYear+1));
        }
        if(dayOfMonth<10){
            day="0"+dayOfMonth;
        }else{
            day=String.valueOf(dayOfMonth);
        }
        date = year+"-"+mon+"-"+day;
        fecha.setText(date);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String hour,min,sec;
        if(hourOfDay<10){
             hour="0"+hourOfDay;
        }else {
            hour=String.valueOf(hourOfDay);
        }
        if(minute<10){
             min="0"+minute;
        }else{
            min=String.valueOf(minute);
        }
        if(second<10){
             sec="0"+second;
        }else{
            sec=String.valueOf(second);
        }
        timer=hour+":"+min+":"+sec;
        time.setText(timer);
    }
}
