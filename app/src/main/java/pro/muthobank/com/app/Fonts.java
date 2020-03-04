package pro.muthobank.com.app;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class Fonts {

    public static void customFontBold(TextView tv, Context context){
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/montserrat_bold.otf");
        tv.setTypeface(custom_font);
    }
    public static void customFontLight(TextView tv, Context context){
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/montserrat_light.otf");
        tv.setTypeface(custom_font);
    }
}
