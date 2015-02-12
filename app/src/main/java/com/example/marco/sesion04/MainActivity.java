package com.example.marco.sesion04;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckedTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actv = (AutoCompleteTextView)findViewById(R.id.autoLibro);
        String[] books = getResources().getStringArray(R.array.libros);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
        actv.setAdapter(aa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Nombre: " + ((TextView)findViewById(R.id.editName)).getText().toString() + "\n");
            sb.append("Telefono: " + ((TextView)findViewById(R.id.editPhone)).getText().toString() + "\n");
            sb.append("Escolaridad: " + ((Spinner)findViewById(R.id.spEscolaridad)).getSelectedItem().toString() + "\n");
            RadioGroup rg = (RadioGroup)findViewById(R.id.radioGenero);
            RadioButton rb = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
            sb.append("Genero: " + rb.getText().toString() + "\n");
            sb.append("Libro Favorito: " + ((AutoCompleteTextView)findViewById(R.id.autoLibro)).getText().toString() + "\n");
            sb.append("Practica Deporte: " + ((CheckedTextView)findViewById(R.id.textDeporte)).isChecked() + "\n");
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkMarkOnClick(View view)
    {
        boolean checked = ((CheckedTextView)view).isChecked();
        ((CheckedTextView)view).setChecked(!checked);
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.buttonLimpiar:
                showDialog();
                break;
        }
    }

    public void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Desea realmente limpiar?").setTitle("Guit Test");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cleanForm();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void cleanForm()
    {
        ((TextView)findViewById(R.id.editName)).setText("");
        ((TextView)findViewById(R.id.editPhone)).setText("");
        ((Spinner)findViewById(R.id.spEscolaridad)).setSelection(0);
        ((RadioButton)findViewById(R.id.rbMasculino)).setChecked(true);
        ((AutoCompleteTextView)findViewById(R.id.autoLibro)).setText("");
        ((CheckedTextView)findViewById(R.id.textDeporte)).setChecked(false);
    }
}
