package com.netsahiwot.netsa_hiwot;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TimePicker;

import java.util.Calendar;
/**
 * Created by Amanuel on 25/8/2016.
 */
public class UserSetting extends PreferenceActivity {
    private SharedPreferences sharedPreferences;
    private Preference timeset;
    static final int DIALOG_ID = 10;
    public static int hour;
    public static int minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        ActionBar bar = getActionBar();
        //bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Settings");

        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(UserSetting.this);
        timeset = (Preference) findPreference("time_set");

        timeset.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
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
                int hour, minute;
                final Calendar c = Calendar.getInstance();
               /* if (summary != 0) {
                    c.setTimeInMillis(sharedPrefs.getLong("userTime",
                            System.currentTimeMillis()));
                }*/
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog tpk = new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);
                return new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);

        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int pickerHour,
                              int pickerMinute) {
            Calendar Cal = Calendar.getInstance();
            hour = pickerHour;
            minute = pickerMinute;

        /*  Cal.setTimeInMillis(System.currentTimeMillis());
            Cal.set(Calendar.HOUR_OF_DAY, hour);
            Cal.set(Calendar.MINUTE, minute);
            Cal.set(Calendar.SECOND, 1);


            Intent intent = new Intent(getApplicationContext(), CustomNotification.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,Cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
            //Editor e = sharedPrefs.edit();
            //  e.putLong("userTime", Cal.getTimeInMillis());
            // e.commit();

            timeset.setSummary(DateFormat.getTimeFormat(getBaseContext())
                    .format(new Date(Cal.getTimeInMillis())));
*/
            //new AlarmTask(UserSettingActivity.this, Cal).run();
        }
    };
}
