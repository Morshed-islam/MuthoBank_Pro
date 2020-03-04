package pro.muthobank.com;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import org.w3c.dom.Text;

import pro.muthobank.com.app.Fonts;

public class SubscribeActivity extends AppCompatActivity {

    private EditText phonetxt;
    private LinearLayout select_country;
    private TextView countrycodetxt;
    private TextView _tvCountry,_tvFName,_tvLName,_tvEmail,_tvEmployment,_tvCurrentSalary,_tvAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        select_country = findViewById(R.id.select_country);
        countrycodetxt = findViewById(R.id.countrycodetxt);
        phonetxt = findViewById(R.id.phonetxt);

        _tvCountry = findViewById(R.id.tv_country);
        _tvFName = findViewById(R.id.tv_f_name);
        _tvLName = findViewById(R.id.tv_l_name);
        _tvEmail = findViewById(R.id.tv_email);
        _tvEmployment = findViewById(R.id.tv_employment);
        _tvCurrentSalary = findViewById(R.id.tv_current_salary);
        _tvAge = findViewById(R.id.tv_age);


        Fonts.customFontLight(phonetxt,getApplicationContext());
        Fonts.customFontLight(_tvCountry,getApplicationContext());
        Fonts.customFontBold(_tvFName,getApplicationContext());
        Fonts.customFontBold(_tvLName,getApplicationContext());
        Fonts.customFontBold(_tvEmail,getApplicationContext());
        Fonts.customFontBold(_tvEmployment,getApplicationContext());
        Fonts.customFontBold(_tvCurrentSalary,getApplicationContext());
        Fonts.customFontBold(_tvAge,getApplicationContext());

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
                countrycodetxt.setText(dialCode);
                picker.dismiss();
                country_iso_code = code;
            }
        });
        picker.show(getSupportFragmentManager(), "Select Country");
    }
}
