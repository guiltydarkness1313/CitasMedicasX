package com.shibuyaxpress.citasmedicasx.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.shibuyaxpress.citasmedicasx.R;
import com.shibuyaxpress.citasmedicasx.models.Pacientes;
import com.shibuyaxpress.citasmedicasx.models.Usuarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterUserActivity extends AppCompatActivity {

    CircleImageView profileImage;

    EditText inputName;
    EditText inputLastNameP;
    EditText inputLastNameM;
    EditText inputDate;

    Spinner spinnerTypeDoc;

    EditText inputNumberDoc;
    EditText inputNationality;
    EditText inputDirection;
    EditText inputPostalCode;
    EditText inputCity;
    EditText inputProvince;
    EditText inputTelephone;
    EditText inputCell;
    EditText inputEmail;

    Spinner spinnerSex;
    Calendar myCalendar = Calendar.getInstance();
    Button btnSiguiente;

    String fecha_nacimiento;


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

        String formatoriginal="yyyy-MM-dd";
        SimpleDateFormat newSdf=new SimpleDateFormat(formatoriginal,Locale.getDefault());

        inputDate.setText(sdf.format(myCalendar.getTime()));

        fecha_nacimiento=newSdf.format(myCalendar.getTime());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        inputDate=findViewById(R.id.inputBirthDate);
        inputName=findViewById(R.id.input_name);
        inputCell=findViewById(R.id.input_cell);
        inputCity=findViewById(R.id.input_city);
        inputDirection=findViewById(R.id.input_dir);
        inputEmail=findViewById(R.id.input_email);
        inputNationality=findViewById(R.id.input_nation);
        inputNumberDoc=findViewById(R.id.input_number_doc);
        inputPostalCode=findViewById(R.id.input_postal_code);
        inputProvince=findViewById(R.id.input_province);
        inputTelephone=findViewById(R.id.input_telephone);
        inputLastNameP=findViewById(R.id.input_last_name_p);
        inputLastNameM=findViewById(R.id.input_last_name_m);

        spinnerSex=findViewById(R.id.spinner_sex);
        spinnerTypeDoc=findViewById(R.id.spinner_type_doc);

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterUserActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void getDatafromInput(){

        Pacientes.getInstance().setNombre(inputName.getText().toString());
        Pacientes.getInstance().setApellido_paterno(inputLastNameP.getText().toString());
        Pacientes.getInstance().setApellido_materno(inputLastNameM.getText().toString());
        Pacientes.getInstance().setCelular(Integer.parseInt(inputCell.getText().toString()));
        Pacientes.getInstance().setCiudad(inputCity.getText().toString());
        Pacientes.getInstance().setProvincia(inputProvince.getText().toString());
        Pacientes.getInstance().setCod_postal(inputPostalCode.getText().toString());
        Pacientes.getInstance().setCorreo(inputEmail.getText().toString());
        Pacientes.getInstance().setDireccion(inputDirection.getText().toString());
        Pacientes.getInstance().setTelefono(Integer.parseInt(inputTelephone.getText().toString()));
        Pacientes.getInstance().setFecha_nacimiento(fecha_nacimiento);
        Pacientes.getInstance().setGenero(spinnerSex.getSelectedItem().toString());
        Pacientes.getInstance().setTipo_doc(spinnerTypeDoc.getSelectedItem().toString());
        Pacientes.getInstance().setNum_doc(Integer.parseInt(inputNumberDoc.getText().toString()));
        Pacientes.getInstance().setNacionalidad(inputNationality.getText().toString());
        //ingresar el usuario_id
        //Pacientes.getInstance().setUsuario_id(1);
        //Pacientes.getInstance().setImagen_perfil("");
        Usuarios.getInstance().setNombre(String.valueOf(Pacientes.getInstance().getNum_doc()));

    }

    public void siguiente(View view){
        getDatafromInput();
        Intent launcher= new Intent(RegisterUserActivity.this, UserPasswordActivity.class);
        startActivity(launcher);
    }
}
