package pro.muthobank.com;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

public class SubscribeActivity extends AppCompatActivity {

    private LinearLayout select_country;
    private TextView countrytxt, countrycodetxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        select_country = findViewById(R.id.select_country);
        countrycodetxt = findViewById(R.id.countrycodetxt);
//        countrytxt = findViewById(R.id.countrytxt);

        select_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opencountry();
            }
        });
    }


    String country_iso_code = "US";
    @SuppressLint("WrongConstant")
    public void Opencountry() {
        final CountryPicker picker = CountryPicker.newInstance("Select Country");
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                // Implement your code here
//                countrytxt.setText(name);
                countrycodetxt.setText(dialCode);
                picker.dismiss();
                country_iso_code = code;
            }
        });
//        picker.setStyle(R.style.countrypicker_style, R.style.countrypicker_style);
        picker.show(getSupportFragmentManager(), "Select Country");
    }
}
