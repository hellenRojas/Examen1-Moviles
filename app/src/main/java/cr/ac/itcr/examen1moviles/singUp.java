package cr.ac.itcr.examen1moviles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cr.ac.itcr.examen1moviles.access_data.LoginDataBaseAdapter;

public class singUp extends Activity
{
    EditText editTextUserName,editTextPassword,editTextConfirmPassword;
    Button btnCreateAccount;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        // Se crea la instancia de la base de datos SQLite
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Se obtienen las referencias de los views
        editTextUserName=(EditText)findViewById(R.id.txtUsuarioNuevo);
        editTextPassword=(EditText)findViewById(R.id.txtPassNueva);
        editTextConfirmPassword=(EditText)findViewById(R.id.txtConfirmPass);

        btnCreateAccount=(Button)findViewById(R.id.btnNuevoUsuario);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();

                // Se verifica que ningun campo esté vacío
                if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Complete todos los campos", Toast.LENGTH_LONG).show();
                    return;
                }
                //Se chequea que las contraseñas coinsidan
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Las constraseñas no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    // Se guardan los datos en la base de datos
                    loginDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(), "Cuenta creada correctamente", Toast.LENGTH_LONG).show();
                    Intent intentSignIn=new Intent(getApplicationContext(),login.class);
                    startActivity(intentSignIn);
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}