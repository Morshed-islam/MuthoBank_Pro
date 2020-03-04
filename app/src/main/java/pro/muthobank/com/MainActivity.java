package pro.muthobank.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pro.muthobank.com.app.Fonts;

public class MainActivity extends AppCompatActivity {

    private TextView title,dess;
    private Button join_now_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.front_title);
        dess = findViewById(R.id.front_des);

        Fonts.customFontBold(title,getApplicationContext());
        Fonts.customFontLight(dess,getApplicationContext());


        join_now_btn =findViewById(R.id.join_now_btn);

        join_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SubscribeActivity.class));
            }
        });
    }
}
