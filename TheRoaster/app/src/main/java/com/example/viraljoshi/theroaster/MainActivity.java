package com.example.viraljoshi.theroaster;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //Declaration of Seek bar Variables
    private static SeekBar seekBar;
    private static SeekBar seekBar1;
    private static SeekBar seekBar2;
    private static TextView textView;
    private static TextView textView1;
    private static TextView textView2;
    EditText editText;
    Button b;
    DatePicker dp;
    RadioGroup rg;
    RadioButton r1,r2,r3,r4,r5,r6;
    CheckBox ck;
    final Boolean ckbl[]={false};
    Date dt = new Date(System.currentTimeMillis());
    Spinner sp;
    int progress_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar();
        seekbar1();
        seekbar2();
        //SharedPreferences concept Started.
        //Here we are Declaring the Shared Preference variable and then it will be going to call all the widgets as well it will store and retrieve the State.
        final SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        //Edit Text widget is called
        editText = (EditText) findViewById(R.id.editText);
        //The editor variable will store the value of the Edit text entered by the user and then getString() method will Read the values of the Edittext entered by the user previously
        editor.putString("Username",editText.getText().toString());
        String name=sharedPreferences.getString("Username","");
        editText.setText(name);
        //Date Picker widget is called.
        dp = (DatePicker) findViewById(R.id.simpleDatePicker);
        //Radio Button widget is called
        r1=(RadioButton)findViewById(R.id.rd);
        r2=(RadioButton)findViewById(R.id.rd1);
        r3=(RadioButton)findViewById(R.id.rd2);
        r4=(RadioButton)findViewById(R.id.rd3);
        r5=(RadioButton)findViewById(R.id.rd4);
        r6=(RadioButton)findViewById(R.id.rd5);
        sp=(Spinner)findViewById(R.id.spinner);
        ck=(CheckBox)findViewById(R.id.checkBox);
        //Button widget is called
        b = (Button) findViewById(R.id.bt);
        //All the values of the widgets will be stored in the Shared Preference using the Onclick Listener of the Button
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Edit text value will be stored in the Shared preference
                 String a = editText.getText().toString();
                editor.putString("Name",a);
                //radio Button values will be stored as if the Condition will be true that is it is checked or not
                editor.putBoolean("XS",r1.isChecked());
                editor.putBoolean("S",r2.isChecked());
                editor.putBoolean("M",r3.isChecked());
                editor.putBoolean("L",r4.isChecked());
                editor.putBoolean("Xl",r5.isChecked());
                editor.putBoolean("XXL",r6.isChecked());
                editor.putBoolean("Name",ckbl[0]);
                editor.apply();
                editor.commit();
                //After Saving all the values of the Widgets the Toast message will be Printed which will inform us that all the values are stored using Shared Preference
                Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_LONG).show();
            }
        });
        //Checkbox method is called as well it will check the condition whether it is true then and then it will stored the value in Shared Preference
        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    ckbl[0]=true;
                }
                else
                {
                    ckbl[0]=false;
                }
                Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_LONG).show();
            }
        });
        //Date Picker values in the formate of Day, Month and Year will be stored in Shared Preference
        int day = dp.getDayOfMonth();
        int month = dp.getMonth() + 1;
        int year = dp.getYear();
        editor.putString("day", String.valueOf(day));
        editor.putInt("month", month);
        editor.putInt("day", year);
        editor.commit();
            }
            //Pant Size Seekbar
            // Seek Bar slider will be called using its ID
    public void seekbar() {
        seekBar = (SeekBar) findViewById(R.id.skbar);
        textView = (TextView) findViewById(R.id.txtskbar);
        textView.setText("Covered:" + seekBar.getProgress() + " / " + seekBar.getMax());
//After making some changes on the Seek bar method the progress will be shown
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                textView.setText("Covered:" + progress + " / " + seekBar.getMax());
            }
//3 Auto Generated Methodd of onProgressChanged is declared
            //This method will start the progress of the Seek Bar
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }
//This method will display that seek bar has been stoped
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Covered:" + progress_value + " / " + seekBar.getMax());

            }
        });
    }
    //Shirt size Seekbar
    int pv1;
    public void seekbar1()
    {
        seekBar1 = (SeekBar) findViewById(R.id.skbar1);
        textView1 = (TextView) findViewById(R.id.txtskbar1);
        textView1.setText("Covered:" + seekBar1.getProgress() + " / " + seekBar1.getMax());
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar1, int progress, boolean fromUser) {
                pv1 = progress;
                textView1.setText("Covered:" + progress + " / " + seekBar1.getMax());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar1)
            {
                textView1.setText("Covered:" + pv1 + " / " + seekBar1.getMax());
            }
        });
    }
    //Shoe Size Seek bar
    public void seekbar2()
    {
        final int pv = 4;
        seekBar2 = (SeekBar) findViewById(R.id.skbar2);
        textView2 = (TextView) findViewById(R.id.txtskbar2);
        textView2.setText("Covered:0" + " / " + seekBar2.getMax());
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar1, int progress, boolean fromUser) {
                if (progress >= pv) {
                    textView2.setText("Covered:" + progress + " / " + seekBar1.getMax());
                } else {
                    textView2.setText("Range Start from :" + pv);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {
            }
        });
    }
}