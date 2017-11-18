package com.marcosdefreitas.maakereview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button btnEntrar; EditText txt_user, txt_pass;
    String url = "http://10.0.2.2:8080/RestJR/services/makeReview/validaUsuario";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = (Button)findViewById(R.id.btn_entrar_login);
        txt_user = (EditText)findViewById(R.id.txt_user_login);
        txt_pass = (EditText)findViewById(R.id.txt_senha_login);
        btnEntrar.setOnClickListener(login.this);
    }

    @Override
    public void onClick(View v) {

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostHandler handler = new PostHandler(txt_user.getText().toString(), txt_pass.getText().toString());
                String result = null;

                try
                {
                    result = handler.execute(url).get();

                    if(result.contains("false"))
                        Toast.makeText(getApplicationContext(), "Usuário/Senha inválido", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Bem vindo!", Toast.LENGTH_LONG).show();

                }
                catch (InterruptedException e) {e.printStackTrace();}
                catch (ExecutionException e) {e.printStackTrace();}
            }
        });

    }

    public class PostHandler extends AsyncTask<String, Void, String> {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String,String> par = new HashMap<String,String>();
        String user, password;

        public PostHandler(String user, String password) {
            this.user = user;
            this.password = password;
        }

        @Override
        protected String doInBackground(String... params) {
            par.put("usuario", user);
            par.put("password", password);
            JSONObject parameter = new JSONObject(par);
            Response response = null;

            final RequestBody body = RequestBody.create(JSON, parameter.toString());
            Request request = new Request.Builder()
                    .url(params[0])
                    .post(body)
                    .addHeader("content-type","application/json; charset=utf-8")
                    .build();

            try {
                response = client.newCall(request).execute();

                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response.toString());
                else
                    return response.body().string();

            } catch (Exception e){e.getMessage().toString();}

            return "";
        }
    }

}
