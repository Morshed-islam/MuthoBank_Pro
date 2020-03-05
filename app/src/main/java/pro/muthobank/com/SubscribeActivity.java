package pro.muthobank.com;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import pro.muthobank.com.api.ApiInterface;
import pro.muthobank.com.api.ApiUtils;
import pro.muthobank.com.app.Fonts;
import pro.muthobank.com.model.SubscriberPostModel;
import pro.muthobank.com.model.SubscriberResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribeActivity extends AppCompatActivity {

    private Button join_now_btn;
    private EditText phonetxt,first_name,last_name,salary,status,age,email;
    private LinearLayout select_country;
    private TextView countrycodetxt;
    private TextView _tvCountry,_tvFName,_tvLName,_tvEmail,_tvEmployment,_tvCurrentSalary,_tvAge;
    private ProgressDialog mRegProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);


        initViews();

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


        join_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postDataToServer();

            }
        });

    }

    private void initViews(){
        select_country = findViewById(R.id.select_country);
        countrycodetxt = findViewById(R.id.countrycodetxt);

        //textViews
        _tvCountry = findViewById(R.id.tv_country);
        _tvFName = findViewById(R.id.tv_f_name);
        _tvLName = findViewById(R.id.tv_l_name);
        _tvEmail = findViewById(R.id.tv_email);
        _tvEmployment = findViewById(R.id.tv_employment);
        _tvCurrentSalary = findViewById(R.id.tv_current_salary);
        _tvAge = findViewById(R.id.tv_age);

        //edittext
        phonetxt = findViewById(R.id.phonetxt);
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.ed_last_name);
        email = findViewById(R.id.ed_email);
        status = findViewById(R.id.ed_status);
        salary = findViewById(R.id.ed_salary);
        age = findViewById(R.id.ed_age);

        //button
        join_now_btn = findViewById(R.id.join_now_btn);
        mRegProgress = new ProgressDialog(this);
    }

    private void postDataToServer() {


        String mPhone = phonetxt.getText().toString().trim();
        String mFname = first_name.getText().toString().trim();
        String mLname = last_name.getText().toString().trim();
        String mEmail = email.getText().toString().trim();
        String mStatus = status.getText().toString().trim();
        String mSalary = salary.getText().toString().trim();
        String mAge = age.getText().toString().trim();

        if (TextUtils.isEmpty(mPhone) || TextUtils.isEmpty(mFname) || TextUtils.isEmpty(mLname) || TextUtils.isEmpty(mStatus)
                || TextUtils.isEmpty(mSalary) || TextUtils.isEmpty(mAge) || TextUtils.isEmpty(mEmail)) {

            Toast.makeText(this, "All Fields Required!", Toast.LENGTH_SHORT).show();
            return;
        }


        mRegProgress.setTitle("Logging.....");
        mRegProgress.setMessage("Please wait while we login your account !");
        mRegProgress.setCanceledOnTouchOutside(false);
        mRegProgress.show();

//        SubscriberPostModel subscriberPostModel = new SubscriberPostModel("01247324","morshed","islam","morsh777@gmail.com","dev","234000","24");
        SubscriberPostModel subscriberPostModel = new SubscriberPostModel(mPhone,mFname,mLname,mEmail,mStatus,mSalary,mAge);
        ApiInterface apiInterface = ApiUtils.getApiInterface();
        Call<ResponseBody> call = apiInterface.postSubscribe(subscriberPostModel);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        mRegProgress.dismiss();
                        ViewDialog viewDialog = new ViewDialog();
                        viewDialog.showDialog(SubscribeActivity.this);
                        Log.i("DATA", "onResponse: success --"+response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else {
                    Log.i("DATA", "onResponse: Below --"+response.body());
                    mRegProgress.dismiss();
                    Toast.makeText(SubscribeActivity.this, "Duplicate number! Please use another number.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                mRegProgress.dismiss();
                Toast.makeText(SubscribeActivity.this, "Error!", Toast.LENGTH_SHORT).show();

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



    public class ViewDialog {

        public void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.success_dialog);

            TextView congratz = dialog.findViewById(R.id.dialog_congrats);
            TextView congratz2 = dialog.findViewById(R.id.dialog_congrats_2);

            Fonts.customFontBold(congratz,getApplicationContext());
            Fonts.customFontBold(congratz2,getApplicationContext());

            Button dialogButton = dialog.findViewById(R.id.okay_dialog_btn);
            Fonts.customFontLight(dialogButton,getApplicationContext());

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Intent goToHome = new Intent(getApplicationContext(), MainActivity.class);
                    goToHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToHome);
                    finish();
                }
            });

            dialog.show();

        }
    }
}
