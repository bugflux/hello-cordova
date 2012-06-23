/**
 * 
 */
package pt.it.av.hellocordova.plugins;

import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;

import com.phonegap.api.Plugin;

public class BrightnessPlugin extends Plugin {
	public static final String ACTION_SET = "set";
	public static final String ACTION_GET = "get";

	@Override
	public PluginResult execute(String action, JSONArray data, String callbackId) {
		final Context _ctx = this.ctx.getContext();
		PluginResult result = null;

		try {
			// GET BRIGHTNESS
			if (ACTION_GET.equals(action)) {
				int brightness = Settings.System.getInt(
						_ctx.getContentResolver(),
						Settings.System.SCREEN_BRIGHTNESS);

				Log.d("CORDOVA EXAMPLE", "Brightness get " + brightness);

				JSONObject response = new JSONObject();
				response.put("value", brightness);

				result = new PluginResult(Status.OK, response);
			}

			// SET BRIGHTNESS
			else if (ACTION_SET.equals(action)) {
				int brightness = data.getInt(0);
				Settings.System.putInt(_ctx.getContentResolver(),
						Settings.System.SCREEN_BRIGHTNESS, brightness);

				Log.d("CORDOVA EXAMPLE", "Brightness set " + brightness);

				result = new PluginResult(Status.OK);
			}

			// UNKNOWN ACTION
			else {
				result = new PluginResult(Status.INVALID_ACTION);
			}
		} catch (JSONException e) {
			result = new PluginResult(Status.JSON_EXCEPTION);
			e.printStackTrace();
		} catch (SettingNotFoundException e) {
			result = new PluginResult(Status.ERROR);
			e.printStackTrace();
		}

		return result;
	}
}
