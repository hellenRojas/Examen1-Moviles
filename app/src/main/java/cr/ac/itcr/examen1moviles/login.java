package cr.ac.itcr.examen1moviles;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.itcr.examen1moviles.access_data.LoginDataBaseAdapter;

public class login extends Activity
{
    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

// create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();


        TextView btnSignUp=(TextView)findViewById(R.id.textRegistro);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentSignUP=new Intent(getApplicationContext(),singUp.class);
                startActivity(intentSignUP);
            }
        });


        final EditText editTextUserName=(EditText)findViewById(R.id.txtEmail);
        final EditText editTextPassword=(EditText)findViewById(R.id.txtPass);

        Button btnSignIn=(Button)findViewById(R.id.btnLogin);

// Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

// fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

// check if the Stored password matches with Password entered by user
                if(password.equals(storedPassword))
                {
                    Toast.makeText(login.this, "Se logeo correctamente", Toast.LENGTH_LONG).show();
                    Intent intentDash=new Intent(getApplicationContext(),DashboardActivity.class);
                    startActivity(intentDash);

                }
                else
                {
                    Toast.makeText(login.this, "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    // Methos to handleClick Event of Sign In Button
    public void signIn(View V)
    {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database
        loginDataBaseAdapter.close();
    }
}
