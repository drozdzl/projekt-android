package com.example.lukasz.aplikacja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private static final String Register_Url = "http://192.168.56.1//user_control.php";
    private EditText loginEditText ;
    private EditText passwordEditText ;
    private EditText emailEditText  ;
    private EditText passwordRepeatEditText ;
    private EditText firstNameEditText;
    private EditText cityEditText;
    private EditText birthDateEditText;
    private Button showHideButton ;
    private Button submitButton ;
    private View hideView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView rulesTextView = (TextView) findViewById(R.id.rulesTextView);
        loginEditText = (EditText) findViewById(R.id.loginEditTextView);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditTextView);
        passwordRepeatEditText = (EditText) findViewById(R.id.passwordRepeatEditText);
        firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
        cityEditText = (EditText) findViewById(R.id.cityName);
        birthDateEditText = (EditText)findViewById(R.id.birthDateEditText);
        showHideButton = (Button) findViewById(R.id.more);
        submitButton = (Button) findViewById(R.id.submitAccountButton);
        hideView = (View) findViewById(R.id.hide);
        hideView.setVisibility(View.GONE);
        String rulesFirstStatement = getString(R.string.rulesFirstStatement);
        String rulesNextStatement = getString(R.string.rulesSecondStatement);
        rulesTextView.setText(Html.fromHtml(rulesFirstStatement + rulesNextStatement));
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEveryThingTrue = true;


                if (loginEditText.getText().length() < 6) {
                    isEveryThingTrue = false;
                    loginEditText.setError(getString(R.string.loginErrorToShort));
                } if (passwordEditText.getText().length() < 6) {
                    isEveryThingTrue = false;
                    passwordEditText.setError(getString(R.string.passwordErrorToShort));
                } if (emailEditText.getText().length() < 1) {
                    isEveryThingTrue = false;
                    emailEditText.setError(getString(R.string.emailError));
                }if ( !(passwordEditText.getText().toString().equals(passwordRepeatEditText.getText().toString()))) {
                    passwordRepeatEditText.setError(getString(R.string.passwordsDontMatch));
                    isEveryThingTrue = false;
                } if(isEveryThingTrue == false){


                }
                else {
                    registerUser();
                    Intent moveToLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(moveToLoginActivity);
                }
            }
        });
        showHideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hideView.getVisibility() == View.GONE) {
                    hideView.setVisibility(View.VISIBLE);
                    showHideButton.setText(R.string.showHideButtonLess);
                } else {
                    hideView.setVisibility(View.GONE);
                    showHideButton.setText(R.string.showHideButtonMore);
                }
            }
        });
    }
    private void registerUser() {
        final String username = loginEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String firstname = firstNameEditText.getText().toString().trim();
        final String city = cityEditText.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Register_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username);
                params.put("email",email);
                params.put("password", password);
                params.put("first_name",firstname);
                params.put("city",city);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
