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
        String nombre = getIntent().getExtras().getString("nombre");
        Toast.makeText(this, nombre, Toast.LENGTH_LONG).show();
        final IRepository repository = new FlorRepository(this);
        Flor f = repository.GetBy(nombre);
        String nombreC = f.getNamec();
        String color = f.getColor();
        etNombre = (EditText) findViewById(R.id.etNombreEE);
        etNombreC = (EditText) findViewById(R.id.etNombreCEE);
        etColor = (EditText) findViewById(R.id.etColorEE);
        etNombre.setText(nombre);
        etNombreC.setText(nombreC);
        etColor.setText(color);
        btnAgregarEE = (Button)findViewById(R.id.btnAgregarFlorEE);
        btnEliminarEE = (Button)findViewById(R.id.btnEliminarEE);
        btnAgregarEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNombre.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Complete los espacios", Toast.LENGTH_LONG).show();
                } else {

                    Flor flor = new Flor();
                    flor.setName(etNombre.getText().toString());
                    flor.setNameC(etNombreC.getText().toString());
                    flor.setColor(etColor.getText().toString());
                    repository.Update(flor);

                    Toast.makeText(getApplicationContext(), "Editado correctamente", Toast.LENGTH_SHORT).show();
                }
            }

        });
        btnEliminarEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNombre.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Complete los espacios", Toast.LENGTH_LONG).show();
                } else {

                    Flor flor = new Flor();
                    flor.setName(etNombre.getText().toString());
                    flor.setNameC(etNombreC.getText().toString());
                    flor.setColor(etColor.getText().toString());
                    etNombre.setText("");
                    etNombreC.setText("");
                    etColor.setText("");
                    repository.Delete(flor);

                    Toast.makeText(getApplicationContext(), "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}
