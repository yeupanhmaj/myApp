package com.example.vocalearn.fragment;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import com.example.vocalearn.MyApplication;
import com.example.vocalearn.R;
import com.example.vocalearn.SharedReference.MyRF;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

public class User extends Fragment {
    private final String listDay[] = {"Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"};
    private boolean[] checkedDay = new boolean[]{
            false, // Monday
            false, // Tuesday
            false, // Wednesday
            false, // Thursday
            false, // Friday
            false, // Saturday
            false // Sunday
    };
    private final String time[] = {"1",
            "2",
            "3",
            "4"};
    private boolean[] checkedTime = new boolean[]{
            false, // Monday
            false, // Tuesday
            false, // Wednesday
            false // Thursday
    };
    public static final String SHARED_PREFS = "sharedPrefs";
    private final String NAME = "name";
    private final String HOUR = "hour";
    private final String MINUTE = "minute";
    private RelativeLayout txtDay, txtTime, txtNotification;
    private TextView txtTimePractice;
    private int Hour, Minute;
    private SharedPreferences sp;
    private ImageButton btnEditTen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.schedule_activity, container, false);
        Hour = MyRF.LoadInt(sp, HOUR);
        Minute = MyRF.LoadInt(sp, MINUTE);
        addControl(root);
        txtTimePractice.setText(Hour + ":" + Minute);
        addEvent(root);
        return root;
    }

    private void addEvent(View view) {
        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setMultiChoiceItems(listDay, checkedDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.setTitle("Pick Days");
                builder.show();
            }
        });
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setSingleChoiceItems(time, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.setTitle("Pick How Many Time To Practice");
                builder.show();
            }
        });
        txtNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txtTimePractice.setText(selectedHour + ":" + selectedMinute);
                        MyRF.SaveInt(sp, HOUR, selectedHour);
                        MyRF.SaveInt(sp, MINUTE, selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        btnEditTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                final EditText edittext = new EditText(getActivity());
                alert.setMessage("Enter Your Name");
                alert.setTitle("Name");

                alert.setView(edittext);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.

                    }
                });
                alert.show();
            }
        });
    }

    private void addControl(View view) {
        txtDay = view.findViewById(R.id.txtDay);
        txtTime = view.findViewById(R.id.txtTime);
        txtNotification = view.findViewById(R.id.txtNotification);
        txtTimePractice = view.findViewById(R.id.txtTimePractice);
        btnEditTen = view.findViewById(R.id.btnEditTen);
    }

}
