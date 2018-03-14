package com.example.vaibhav.booksearch;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    LinearLayout setting_language,setting_reminder,setting_ads,setting_share,setting_rate,
            setting_notifications,setting_faq,setting_contact,setting_policy,setting_term_of_use;
    Switch notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notification=(Switch)findViewById(R.id.switch_btn);
        setting_language=(LinearLayout) findViewById(R.id.setting_id_language);
        setting_reminder=(LinearLayout) findViewById(R.id.setting_id_reminder);
        setting_ads=(LinearLayout) findViewById(R.id.setting_id_remove_ad);
        setting_share=(LinearLayout) findViewById(R.id.setting_id_share_app);
        setting_rate=(LinearLayout) findViewById(R.id.setting_id_rate_app);
        setting_notifications=(LinearLayout) findViewById(R.id.setting_id_notification);
        setting_faq=(LinearLayout) findViewById(R.id.setting_id_faq);
        setting_contact=(LinearLayout) findViewById(R.id.setting_id_contact_us);
        setting_policy=(LinearLayout) findViewById(R.id.setting_id_policy);
        setting_term_of_use=(LinearLayout) findViewById(R.id.setting_id_term_of_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });


        setting_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingsActivity.this,Setting_Language.class);
                startActivity(i);
            }
        });

        setting_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingsActivity.this,Setting_Reminder.class);
                startActivity(i);
            }
        });

        setting_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingsActivity.this,Settings_Faq.class);
                startActivity(i);
            }
        });

        setting_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingsActivity.this,Setting_Policy.class);
                startActivity(i);
            }
        });

        setting_term_of_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingsActivity.this,Setting_Term_of_use.class);
            }
        });

        setting_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingsActivity.this,Setting_Rate.class);
                startActivity(i);
            }
        });
        setting_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,"BOOK SHARE APP");
                startActivity(Intent.createChooser(shareIntent,"Share Using.."));
            }
        });

        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked){
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(SettingsActivity.this);
                    mBuilder.setSmallIcon(R.drawable.book_read);
                    mBuilder.setContentTitle("BookSearch Notification!");
                    mBuilder.setContentText("Hi, This is Android Book Notification Detail!");

                    NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                    inboxStyle.setBigContentTitle("Big Title Details:");

                    // Moves events into the big view
                    for (int i=0; i < 6; i++) {
                        inboxStyle.addLine("Details No."+ i);
                    }

                    mBuilder.setStyle(inboxStyle);

                    Intent resultIntent = new Intent(SettingsActivity.this, MainActivity.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(SettingsActivity.this);
                    stackBuilder.addParentStack(MainActivity.class);

// Adds the Intent that starts the Activity to the top of the stack
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(resultPendingIntent);

                    Notification note = mBuilder.build();
                    note.defaults |= Notification.DEFAULT_VIBRATE;
                    note.defaults |= Notification.DEFAULT_SOUND;
                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
                    mNotificationManager.notify(0, mBuilder.build());

                }

            }
        });



    }
}
