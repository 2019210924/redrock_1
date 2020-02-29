package com.example.mydemohttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydemohttp.Http.Callback;
import com.example.mydemohttp.Http.NetUtil;
import com.example.mydemohttp.Http.Request;


public class MainActivity extends AppCompatActivity {
        private TextView textView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String key[] = {"username", "password"};

        String value[] = {"***", "***"};
       textView = findViewById(R.id.tv);

        //异步
        Request request = new Request.Builder("http://bihu.jay86.com/login.php")
                .setMethod("POST").setKeyArray(key).setValueArray(value).build();

        NetUtil.getInstance().execute(request, new Callback() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

            }

            @Override
            public void onFailed(Throwable throwable) {
                throwable.printStackTrace();
                Log.i("postAsynHttp","postfailure");
            }
        });

        textView.setText(request.toString());

    }
}