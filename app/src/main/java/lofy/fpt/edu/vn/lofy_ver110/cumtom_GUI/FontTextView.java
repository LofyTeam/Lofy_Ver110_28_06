package lofy.fpt.edu.vn.lofy_ver110.cumtom_GUI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FontTextView extends android.support.v7.widget.AppCompatTextView {

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        String fontPath = "GOTHICB.TTF";
        Typeface fontsStyle = Typeface.createFromAsset(context.getAssets(), fontPath);
        this.setTypeface(fontsStyle,Typeface.BOLD);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        String fontPath = "GOTHICB.TTF";
        Typeface fontsStyle = Typeface.createFromAsset(context.getAssets(), fontPath);
        this.setTypeface(fontsStyle,Typeface.BOLD);
    }

    public FontTextView(Context context) {
        super(context);

        String fontPath = "GOTHICB.TTF";
        Typeface fontsStyle = Typeface.createFromAsset(context.getAssets(), fontPath);
        this.setTypeface(fontsStyle,Typeface.BOLD);
    }
}
