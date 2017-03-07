package horusgeo.br158;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

//    private String login = "cappe";
//    private String passwd = "br158";
    private String login = "a";
    private String passwd = "a";
    EditText loginText;
    EditText passwdText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginText = (EditText) findViewById(R.id.loginText);
        passwdText = (EditText) findViewById(R.id.passwdText);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: return check login
                doLogin();
//                checkIfLoginValid();
            }
        });

    }

    private void checkIfLoginValid(){


        String lText = loginText.getText().toString();
        if(!lText.isEmpty()){
            loginText.setError(null);
            checkIfPasswdValid();
        }else{
            loginText.setError("Insira um login!");
            loginText.requestFocus();
        }

    }

    private void checkIfPasswdValid(){

        String pText = passwdText.getText().toString();
        if(!pText.isEmpty()){
            passwdText.setError(null);
            doLogin();
        }else{
            passwdText.setError("Insira uma senha!");
            passwdText.requestFocus();
        }
    }

    private void doLogin(){
//        TODO: Return intent to MapsActivity

//        if(loginText.getText().toString().equals(login)){
//            if(passwdText.getText().toString().equals(passwd)){
                Intent intent = new Intent(LoginActivity.this, infoPropActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
//            }else{
//                Toast.makeText(LoginActivity.this, "Senha incorreta!", Toast.LENGTH_LONG).show();
//                passwdText.setError("Verifique a senha");
//            }
//        }else{
//            Toast.makeText(LoginActivity.this, "Usuário incorreto!", Toast.LENGTH_LONG).show();
//            loginText.setError("Verifique o login");
//        }

    }
}

