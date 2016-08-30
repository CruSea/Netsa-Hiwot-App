package com.netsahiwot.netsa_hiwot;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Amanuel on 25/8/2016.
 */
public class UserSetting extends PreferenceActivity {

    private SharedPreferences sharedPrefs;
    private Preference mytime;
    private long summary;
    static final int DIALOG_ID = 10;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        // Allow super to try and create a view first
        final View result = super.onCreateView(name, context, attrs);
        if (result != null) {
            return result;
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //getWindow().setStatusBarColor(getResources().getColor(R.color.header));
        }
        addPreferencesFromResource(R.xml.settings);

        sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(UserSetting.this);
        mytime = findPreference("mytime");
        summary = sharedPrefs.getLong("userTime", 0);
        if (summary != 0) {
            mytime.setSummary(DateFormat.getTimeFormat(getBaseContext())
                    .format(summary));
        } else {
            mytime.setSummary("");
        }

        mytime.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                showDialog(DIALOG_ID);

                return false;
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ID:
                int hour,
                        minute;
                final Calendar c = Calendar.getInstance();
                if (summary != 0) {
                    c.setTimeInMillis(sharedPrefs.getLong("userTime",
                            System.currentTimeMillis()));
                }
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog tpk = new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);
                return tpk;

        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int pickerHour,
                              int pickerMinute) {
            Calendar Cal = Calendar.getInstance();
            int hour = pickerHour;
            int minute = pickerMinute;

            Cal.setTimeInMillis(System.currentTimeMillis());
            Cal.set(Calendar.HOUR_OF_DAY, hour);
            Cal.set(Calendar.MINUTE, minute);
            Cal.set(Calendar.SECOND, 0);

            SharedPreferences.Editor e = sharedPrefs.edit();
            e.putLong("userTime", Cal.getTimeInMillis());
            e.commit();
            mytime.setSummary(DateFormat.getTimeFormat(getBaseContext())
                    .format(new Date(Cal.getTimeInMillis())));
            Log.d("Hi sammie!!!", "UserSetting onTimeSet()..." + Cal.toString());
            new AlarmTask(UserSetting.this, Cal).run();
        }
    };
}