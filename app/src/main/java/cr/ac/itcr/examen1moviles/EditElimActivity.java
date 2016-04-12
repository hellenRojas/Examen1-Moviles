package cr.ac.itcr.examen1moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cr.ac.itcr.examen1moviles.access_data.FlorRepository;
import cr.ac.itcr.examen1moviles.access_data.IRepository;
import cr.ac.itcr.examen1moviles.entity.Flor;

public class EditElimActivity extends AppCompatActivity {
    EditText etNombre,etNombreC,etColor;
    Button btnAgregarEE, btnEliminarEE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_elim);
        //Se obtiene el nombre enviado por el EditarElimFragment
        String nombre = getIntent().getExtras().getString("nombre");
        // Se hace una instancia de Irepository para acceder a las funciones de base de datos
        final IRepository repository = new FlorRepository(this);
        //Se obtiene el objeto Flor con la funcion GetBy mandando el nombre como parametro
        Flor f = repository.GetBy(nombre);
        String nombreC = f.getNamec();
        String color = f.getColor();
        // Se setean los campos con los atributos del objeto
        etNombre = (EditText) findViewById(R.id.etNombreEE);
        etNombreC = (EditText) findViewById(R.id.etNombreCEE);
        etColor = (EditText) findViewById(R.id.etColorEE);
        etNombre.setText(nombre);
        etNombreC.setText(nombreC);
        etColor.setText(color);
        btnAgregarEE = (Button)findViewById(R.id.btnAgregarFlorEE);
        btnEliminarEE = (Button)findViewById(R.id.btnEliminarEE);
        // Se le da funcionalidad al boton de Editar
        btnAgregarEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNombre.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Complete los espacios", Toast.LENGTH_LONG).show();
                } else {
                    // una vez que el usuario cambie algo y de click en el boton  se crea un objeto Flor
                    Flor flor = new Flor();
                    flor.setName(etNombre.getText().toString());
                    flor.setNameC(etNombreC.getText().toString());
                    flor.setColor(etColor.getText().toString());
                    //Y luego se modifican los campos por medio de la función Update
                    repository.Update(flor);

                    Toast.makeText(getApplicationContext(), "Editado correctamente", Toast.LENGTH_SHORT).show();
                }
            }

        });
        //Se le da funcionalidada al boton eliminar
        btnEliminarEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNombre.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Complete los espacios", Toast.LENGTH_LONG).show();
                } else {
                    // Se crea un objeto con los atributos  que estan en los campos
                    Flor flor = new Flor();
                    flor.setName(etNombre.getText().toString());
                    flor.setNameC(etNombreC.getText().toString());
                    flor.setColor(etColor.getText().toString());
                    //Se limbian los editText
                    etNombre.setText("");
                    etNombreC.setText("");
                    etColor.setText("");
                    // Se elimina de base de datos
                    repository.Delete(flor);

                    Toast.makeText(getApplicationContext(), "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}
