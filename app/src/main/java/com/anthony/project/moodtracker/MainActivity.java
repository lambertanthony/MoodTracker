package com.anthony.project.moodtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private List<Mood> moodList;
    private MoodAdapter moodAdapter;
    private Button historicButton;
    private Button addMoodButton;
    private TextView text_view;
    private ArrayList<Mood> moodsData;
    private MoodsPersistence moodsPersistence;
    SingletonMoodsData singletonMoodsData  = SingletonMoodsData.getInstance();
    RecyclerView recyclerView;


    public static final int[] tableauImg = new int[]{R.drawable.smiley_sad, R.drawable.smiley_disappointed, R.drawable.smiley_normal, R.drawable.smiley_happy, R.drawable.smiley_super_happy};

    public static final int[] tableauFnd = new int[]{R.color.faded_red, R.color.warm_grey, R.color.cornflower_blue_65, R.color.light_sage, R.color.banana_yellow};


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewPager2);
        historicButton = findViewById(R.id.btnHist);
        addMoodButton = findViewById(R.id.btnComm);
       moodsPersistence = MoodsPersistence.getInstance(this);
        //MoodsPersistence.removeAll();
        singletonMoodsData.setArray(moodsPersistence.getMoodsData());
        System.out.println("Test moods:" +moodsPersistence.getMoodsData().toString());
        singletonMoodsData.getWeeklyMood();
        System.out.println("Test2 moods:" +moodsPersistence.getMoodsData().toString());
        LocalDate currentDate = LocalDate.now();
        System.out.println("Date current :"+currentDate.toString());

        System.out.println("Dates list "+ DateManipulator.getDatesBetween().toString());
        singletonMoodsData.getWeeklyMood();
       System.out.println("Test2 moods:" +singletonMoodsData.getArray().toString());

        moodAdapter = new MoodAdapter(this, tableauImg);
        viewPager2.setAdapter(moodAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewPager2.setBackgroundColor(getResources().getColor(tableauFnd[position]));
                Log.i("test position", "onPageSelected: position" + position);
            }
        });

       // viewPager2.setCurrentItem(3,false);


        addMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder moodDialog = new AlertDialog.Builder(MainActivity.this);
                moodDialog.setTitle("Comment for Mood state");
                final EditText commentInput = new EditText(MainActivity.this);
                commentInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                moodDialog.setView(commentInput);

                moodDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String message ="Mood saved";
                        String   comment = commentInput.getText().toString().trim();
                        singletonMoodsData.addToArray(new Mood(viewPager2.getCurrentItem(),comment, LocalDate.now()));
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        singletonMoodsData.getWeeklyMood();
                        moodsPersistence.saveMoodsData(singletonMoodsData.getArray());
                    }
                });

                moodDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                moodDialog.show();
                commentInput.requestFocus();
            }
        });

        historicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(getApplicationContext(), HistoricActivity.class));




             }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share_button:
                singletonMoodsData.getWeeklyMood();
                moodsData = singletonMoodsData.getArray();
                final String subject = "Moods rapport";
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                String to = "sharingMood@example.com";
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT,  MailFormatter.mailMessage(moodsData));
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Email"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        super.onStop();
        singletonMoodsData.getWeeklyMood();
        moodsPersistence = MoodsPersistence.getInstance(this);
        moodsPersistence.saveMoodsData(singletonMoodsData.getArray());
        //moodsPersistence.saveMoodsData(Mood.getData());

    }




}