package syr.js.org.syrnative;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Syr Project
 * https://syr.js.org
 * Created by Derek Anderson on 1/8/18.
 */
public class SyrView implements SyrBaseModule {

    @Override
    public View render(JSONObject component, Context context, View instance) {

        RelativeLayout layout;
        if(instance != null) {
            layout = (RelativeLayout) instance;
        } else {
            layout = new RelativeLayout(context);
        }



        JSONObject style = null;
        try {
            JSONObject componentInstance = component.getJSONObject("instance");
            if(componentInstance.has("style")){
                style = componentInstance.getJSONObject("style");
                layout.setLayoutParams(SyrStyler.styleLayout(style));

                if(style.has("left")) {
                    layout.setX(style.getInt("left"));
                }

                if(style.has("top")) {
                    layout.setY(style.getInt("top"));
                }

                if(style.has("opacity")) {
                    layout.setAlpha(style.getInt("opacity"));
                }

                SyrStyler.styleView(layout, style);

                if(style.has("overflow")) {
                    String overflow = style.getString("overflow");
                    if(overflow.contains("hidden")) {
                        layout.setClipChildren(true);
                    } else {
                        layout.setClipChildren(false);
                    }
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return layout;
    }

    @Override
    public String getName() {
        return "View";
    }

    @SyrMethod
    // java.lang.String , int

    // {NativeModules} from 'syr'
    // NativeModules.SyrView.testExportMethod('foo', 100);
    public void testExportMethod(String message, int duration) {
        message = message + "  " + this.getClass().getName();
        Log.i(message, message);
    }
}
