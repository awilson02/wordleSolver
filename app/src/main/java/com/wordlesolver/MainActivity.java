package com.wordlesolver;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.wordlesolver.R;
import com.wordlesolver.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    private ListView listView;

    private long mLastClickTime = 0;


    public static ArrayList<String> words = new ArrayList<>();
    public static ArrayList<String> common = new ArrayList<>();
    public static int guess = 0;
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Character> usedChar = new ArrayList<>();
    public static ArrayList<Character> containedChar = new ArrayList<>();
    public static ArrayList<Character> word = new ArrayList<>(Arrays.asList('-', '-', '-', '-', '-'));

    public String userInTest = "";

    public static ArrayList<Character> yellowWord = new ArrayList<>();
    public boolean userSubmit = false;


    //Letter is their number of occurrences
    public static ArrayList<Character> letters = new ArrayList<Character>(Arrays.asList(
            'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'));
    public static ArrayList<Double> score = new ArrayList<Double>((Collection<? extends Double>) Arrays.asList(
            0.09242295512473932, 0.025086892716459412, 0.031219587549239206, 0.037815710203135865, 0.10277284312968255,
            0.017177724569398315, 0.02531860662701784, 0.027095079941299144, 0.05795937282768209, 0.004495249864833552,
            0.023094153085656908, 0.05199660152931181, 0.030508998223526686, 0.045539507221750215, 0.06840194639684868,
            0.031096006796941376, 0.0017301305321696147, 0.06416930563064803, 0.10271105275353364, 0.050853479570556887,
            0.038742565845369586, 0.01070518266779949, 0.016019155016606165, 0.004448907082721866, 0.031914729280914496,
            0.0067042558121572565));


    public static ArrayList<ArrayList<Double>> positionScore = new ArrayList<ArrayList<Double>>
            ((Collection<? extends ArrayList<Double>>) Arrays.asList(
                    new ArrayList<Double>(Arrays.asList(0.12303577398863257, 0.37780006686726847, 0.2062855232363758, 0.17937144767636243, 0.11350718823136074)), //A
                    new ArrayList<Double>(Arrays.asList(0.558841651263093, 0.04990757855822551, 0.20579174368453482, 0.14910659272951324, 0.036352433764633395)), //B
                    new ArrayList<Double>(Arrays.asList(0.45544554455445546, 0.08712871287128712, 0.19405940594059407, 0.2004950495049505, 0.06287128712871287)), //C
                    new ArrayList<Double>(Arrays.asList(0.27818627450980393, 0.03431372549019608, 0.15931372549019607, 0.19240196078431374, 0.33578431372549017)), //D
                    new ArrayList<Double>(Arrays.asList(0.04542036396450594, 0.24454805233869756, 0.13235072943299744, 0.3493758459918785, 0.2283050082719206)), //E

                    new ArrayList<Double>(Arrays.asList(0.5355535553555355, 0.021602160216021602, 0.15931593159315932, 0.20972097209720972, 0.0738073807380738)), //F
                    new ArrayList<Double>(Arrays.asList(0.3888888888888889, 0.045787545787545784, 0.221001221001221, 0.2576312576312576, 0.08669108669108669)), //G
                    new ArrayList<Double>(Arrays.asList(0.27837992013690815, 0.3103251568739304, 0.06845407872219053, 0.13405590416428978, 0.20878494010268112)), //H
                    new ArrayList<Double>(Arrays.asList(0.04398826979472141, 0.3676352972540656, 0.27912556651559584, 0.23460410557184752, 0.07464676086376966)), //I
                    new ArrayList<Double>(Arrays.asList(0.6941580756013745, 0.037800687285223365, 0.15807560137457044, 0.09965635738831616, 0.010309278350515464)),// J

                    new ArrayList<Double>(Arrays.asList(0.2508361204013378, 0.06354515050167224, 0.17926421404682275, 0.33444816053511706, 0.17190635451505018)), //K
                    new ArrayList<Double>(Arrays.asList(0.17087667161961367, 0.20683506686478453, 0.25200594353640415, 0.22912332838038632, 0.1411589895988113)), //L
                    new ArrayList<Double>(Arrays.asList(0.35106382978723405, 0.09473150962512665, 0.25835866261398177, 0.20364741641337386, 0.09219858156028368)), // M
                    new ArrayList<Double>(Arrays.asList(0.11024423337856173, 0.11702849389416553, 0.32632293080054275, 0.2666214382632293, 0.17978290366350066)), //N
                    new ArrayList<Double>(Arrays.asList(0.05919566199728875, 0.4726615454134659, 0.22345232715770447, 0.1570266606416629, 0.087663804789878)), //O

                    new ArrayList<Double>(Arrays.asList(0.425732737208147, 0.11326378539493294, 0.18032786885245902, 0.20765027322404372, 0.07302533532041729)), //P
                    new ArrayList<Double>(Arrays.asList(0.6964285714285714, 0.13392857142857142, 0.11607142857142858, 0.017857142857142856, 0.03571428571428571)), //Q
                    new ArrayList<Double>(Arrays.asList(0.1510115606936416, 0.22639691714836224, 0.28829479768786126, 0.17220616570327554, 0.16209055876685935)),//R
                    new ArrayList<Double>(Arrays.asList(0.23465703971119134, 0.013989169675090252, 0.07987364620938628, 0.07746690734055355, 0.5940132370637786)), //S
                    new ArrayList<Double>(Arrays.asList(0.2474164133738602, 0.07264437689969605, 0.18693009118541035, 0.2723404255319149, 0.22066869300911854)), //T

                    new ArrayList<Double>(Arrays.asList(0.07535885167464115, 0.47248803827751196, 0.26555023923444976, 0.1598883572567783, 0.02671451355661882)), //U
                    new ArrayList<Double>(Arrays.asList(0.3492063492063492, 0.07503607503607504, 0.3463203463203463, 0.22366522366522368, 0.005772005772005772)), //V
                    new ArrayList<Double>(Arrays.asList(0.39633558341369335, 0.15718418514946964, 0.26133076181292186, 0.12343297974927676, 0.06171648987463838)), //W
                    new ArrayList<Double>(Arrays.asList(0.05555555555555555, 0.19791666666666666, 0.4618055555555556, 0.041666666666666664, 0.24305555555555555)), //X
                    new ArrayList<Double>(Arrays.asList(0.08760890609874153, 0.1292352371732817, 0.10309777347531461, 0.05227492739593417, 0.627783155856728)), //Y

                    new ArrayList<Double>(Arrays.asList(0.24193548387096775, 0.06682027649769585, 0.3271889400921659, 0.2903225806451613, 0.07373271889400922)) //Z

            )

            );


    public static String wordGuess = "roate";
    public static boolean yellows = false;


    //changes to char if there is 2 or more of that char
    public static char containsTwo = '-';


    ArrayList<ArrayList<Button>> tileButtons = new ArrayList<>();
    ArrayList<LinearLayout> rows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //result page
        //Linear Layouts
        rows.add(findViewById(R.id.row1));
        rows.add(findViewById(R.id.row2));
        rows.add(findViewById(R.id.row3));
        rows.add(findViewById(R.id.row4));
        rows.add(findViewById(R.id.row5));


        listView = findViewById(R.id.list);
        setAdapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                popUpShow(i);

                return;
            }


        });

        //buttons

        Button enter = findViewById(R.id.SubmitButton);
        enter.setOnClickListener(this::onClick);

        Button reset = findViewById(R.id.resetButton);
        reset.setOnClickListener(this::onClick);

        Button userGuess = findViewById(R.id.submitGuess);
        userGuess.setOnClickListener(this::onClick);

        Button drawerButton = findViewById(R.id.drawerButton);
        drawerButton.setOnClickListener(this::onClick);


        ArrayList<Button> buttonRow1 = new ArrayList<>();
        buttonRow1.add(findViewById(R.id.button11));
        buttonRow1.add(findViewById(R.id.button12));
        buttonRow1.add(findViewById(R.id.button13));
        buttonRow1.add(findViewById(R.id.button14));
        buttonRow1.add(findViewById(R.id.button15));


        ArrayList<Button> buttonRow2 = new ArrayList<>();
        buttonRow2.add(findViewById(R.id.button21));
        buttonRow2.add(findViewById(R.id.button22));
        buttonRow2.add(findViewById(R.id.button23));
        buttonRow2.add(findViewById(R.id.button24));
        buttonRow2.add(findViewById(R.id.button25));


        ArrayList<Button> buttonRow3 = new ArrayList<>();
        buttonRow3.add(findViewById(R.id.button31));
        buttonRow3.add(findViewById(R.id.button32));
        buttonRow3.add(findViewById(R.id.button33));
        buttonRow3.add(findViewById(R.id.button34));
        buttonRow3.add(findViewById(R.id.button35));
        ;


        ArrayList<Button> buttonRow4 = new ArrayList<>();
        buttonRow4.add(findViewById(R.id.button41));
        buttonRow4.add(findViewById(R.id.button42));
        buttonRow4.add(findViewById(R.id.button43));
        buttonRow4.add(findViewById(R.id.button44));
        buttonRow4.add(findViewById(R.id.button45));
        ;


        ArrayList<Button> buttonRow5 = new ArrayList<>();
        buttonRow5.add(findViewById(R.id.button51));
        buttonRow5.add(findViewById(R.id.button52));
        buttonRow5.add(findViewById(R.id.button53));
        buttonRow5.add(findViewById(R.id.button54));
        buttonRow5.add(findViewById(R.id.button55));


        ArrayList<Button> buttonRow6 = new ArrayList<>();
        buttonRow6.add(findViewById(R.id.button61));
        buttonRow6.add(findViewById(R.id.button62));
        buttonRow6.add(findViewById(R.id.button63));
        buttonRow6.add(findViewById(R.id.button64));
        buttonRow6.add(findViewById(R.id.button65));


        tileButtons.add(buttonRow1);
        tileButtons.add(buttonRow2);
        tileButtons.add(buttonRow3);
        tileButtons.add(buttonRow4);
        tileButtons.add(buttonRow5);
        tileButtons.add(buttonRow6);


        for (int y = 0; y < tileButtons.size(); y++) {

            for (int x = 0; x < 5; x++) {

                tileButtons.get(y).get(x).setOnClickListener(this::onClick);

            }

        }


        //wordle solver


        BufferedReader reader = null;
        String line = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("words.txt"), "UTF-8"));

            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();

        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        //reading in list of 1000 most common words
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("common.txt"), "UTF-8"));


            while ((line = reader.readLine()) != null) {
                common.add(line);
            }


        } catch (IOException e) {
            e.printStackTrace();

        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        setButtonsText();


    }

    @Override
    public void onStart() {

        super.onStart();
        final View anyView = findViewById(R.id.drawer_layout);
        anyView.postDelayed(new Runnable() {
            @Override
            public void run() {
               showInstructs();
            }
        }, 1);

    }



    public void setButtonsText() {
        tileButtons.get(guess).get(0).setText(String.valueOf(wordGuess.charAt(0)));
        tileButtons.get(guess).get(1).setText(String.valueOf(wordGuess.charAt(1)));
        tileButtons.get(guess).get(2).setText(String.valueOf(wordGuess.charAt(2)));
        tileButtons.get(guess).get(3).setText(String.valueOf(wordGuess.charAt(3)));
        ;
        tileButtons.get(guess).get(4).setText(String.valueOf(wordGuess.charAt(4)));

    }


    //search through list to find best next guess
    public static void wordFinder() {

        boolean changed = false;
        listCleaner();

        for (int i = 0; i < words.size(); i++) {
            if (!changed) {
                wordGuess = words.get(i);
                changed = true;
            }
            if (guess != 4) {
                if (containsTwo != '-') {


                } else {

                    if (((common.contains(words.get(i)) || common.contains(wordGuess) && guess > 2)) && (guess > 2 || !containsMoreThanOne(words.get(i)))) {
                        if ((wordValue(words.get(i)) > wordValue(wordGuess))) {
                            wordGuess = words.get(i);
                            changed = true;
                        }
                    } else if (!common.contains(words.get(i)) && !common.contains(wordGuess) && guess >= 2) {

                        if ((wordValue(words.get(i)) > wordValue(wordGuess))) {
                            wordGuess = words.get(i);
                            changed = true;
                        }
                    }

                }


            } else {

                if ((common.contains(words.get(i)) && !common.contains(wordGuess))) {
                    wordGuess = words.get(i);
                    changed = true;
                } else if (!common.contains(words.get(i)) && common.contains(wordGuess)) {
                } else if (wordValue(words.get(i)) > wordValue(wordGuess)) {
                    wordGuess = words.get(i);
                    changed = true;
                }
            }


        }
        containsTwo = '-';
        yellows = false;




    }


    //removes all no longer valid words from list
    public static void listCleaner() {

        //remove guess
        words.remove(wordGuess);

        if (containsTwo != '-') {
            containsTwoClean();
        }
        //used chars
        for (int i = 0; i < words.size(); i++) {
            for (int x = 0; x < usedChar.size(); x++) {

                if (words.get(i).contains(usedChar.get(x).toString())) {
                    words.remove(i);

                    i--;
                    break;
                }
            }
        }

        //chars in green position
        for (int x = 0; x < 5; x++) {
            if (word.get(x) != '-') {
                for (int i = 0; i < words.size(); i++) {
                    if (words.get(i).charAt(x) != word.get(x)) {
                        words.remove(i);

                        i--;
                    }
                }
            }
        }

        //yellow chars
        for (int i = 0; i < words.size(); i++) {

            for (int x = 0; x < containedChar.size(); x++) {

                if (!words.get(i).contains(containedChar.get(x).toString())) {
                    words.remove(i);

                    i--;
                    break;
                }
            }
        }
    }


    //if no more than 1 of a specific char will clear all words with that char
    public static void duplicateClean(int index, char character) {
        for (int i = 0; i < words.size(); i++) {
            for (int x = 0; x < words.get(i).length(); x++) {
                if (words.get(i).charAt(x) == character && x != index) {
                    words.remove(i);
                    i--;
                    break;
                }
            }

        }
    }

    //removes all words where that char is yellow in that postion
    public static void yellowCleaner(int index, char character) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).charAt(index) == character) {
                words.remove(i);
                i--;
            }
        }
    }


    //gets the value based on the occurrences of the characters in the word and position
    public static double wordValue(String string) {
        double value = 0;

        //letter value
        for (int i = 0; i < string.length(); i++) {
            value += score.get(letters.indexOf(string.charAt(i)));
        }

        //letter position value
        if (yellows) {
            for (int i = 0; i < string.length(); i++) {

                if (yellowWord.contains(string.charAt(i))) {
                    value += positionScore.get(letters.indexOf(string.charAt(i))).get(i);
                }
            }
        }


        return value;
    }


    //checks if a word contains more than one if not need to optimize guess
    public static boolean containsMoreThanOne(String string) {
        for (int x = 0; x < string.length(); x++) {
            int counter = 0;
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(x) == string.charAt(i)) {
                    counter++;
                    if (counter > 1) {
                        return true;
                    }
                }

            }

        }
        return false;
    }

    public static void containsTwoClean() {
        for (int i = 0; i < words.size(); i++) {
            int twos = 0;
            for (int x = 0; x < words.get(i).length(); x++) {

                if (words.get(i).charAt(x) == containsTwo) {
                    twos++;
                }
            }
            if (twos < 2) {
                words.remove(i);
                i--;
            }

        }
    }


    public static boolean checker(String userIn) {
        boolean g = false;
        ArrayList<Character> waiting = new ArrayList<>();


        if (userIn.length() != 5) {
            System.out.println("Input must be 5 letters with no spaces");
            return false;
        } else if (userIn.equals("GGGGG")) {
            System.out.println("Completed");
            return false;
        } else {
            for (int i = 0; i < userIn.length(); i++) {
                if (userIn.charAt(i) == 'N') {
                    if (!containedChar.contains(wordGuess.charAt(i)) && !word.contains(wordGuess.charAt(i))) {
                        usedChar.add(wordGuess.charAt(i));
                    } else {
                        if (g && waiting.contains(wordGuess.charAt(i))) {
                            containedChar.remove(word.get(i));
                            duplicateClean(word.indexOf(wordGuess.charAt(i)), wordGuess.charAt(i));
                            wordGuess = wordGuess.substring(0, i) + "." + wordGuess.substring(i + 1);
                        } else if (word.indexOf(wordGuess.charAt(i)) != -1) {
                            duplicateClean(word.indexOf(wordGuess.charAt(i)), wordGuess.charAt(i));
                        } else {
                            g = true;
                            waiting.add(wordGuess.charAt(i));
                            containedChar.add(wordGuess.charAt(i));

                        }

                    }

                } else if (userIn.charAt(i) == 'Y') {

                    yellows = true;
                    if (!containedChar.contains(wordGuess.charAt(i)) && !word.contains(wordGuess.charAt(i))) {

                        containedChar.add(wordGuess.charAt(i));
                        yellowWord.add(wordGuess.charAt(i));

                    } else if (wordGuess.indexOf((wordGuess.charAt(i))) != i) {

                        containsTwo = wordGuess.charAt(i);
                        containedChar.add(wordGuess.charAt(i));
                        yellowWord.add(wordGuess.charAt(i));
                    }


                    yellowCleaner(i, wordGuess.charAt(i));
                } else if (userIn.charAt(i) == 'G') {

                    word.set(i, wordGuess.charAt(i));
                    if (containedChar.contains(wordGuess.charAt(i))) {

                        if (wordGuess.indexOf((wordGuess.charAt(i))) != i) {

                            containsTwo = wordGuess.charAt(i);
                        } else {
                            containedChar.remove(word.get(i));
                        }

                    }

                    if (waiting.contains(wordGuess.charAt(i))) {
                        duplicateClean(i, wordGuess.charAt(i));
                    }
                    g = true;


                }


            }
        }

        return true;
    }


    private void onClick(View view) {
        switch (view.getId()) {

            case R.id.SubmitButton:

                if (SystemClock.elapsedRealtime() - mLastClickTime < 2000){
                    break;
                }

                mLastClickTime = SystemClock.elapsedRealtime();
                if (userSubmit) {
                    for (int x = 0; x < 5; x++) {
                        tileButtons.get(guess).get(x).setEnabled(true);
                    }
                    LinearLayout text = findViewById(R.id.userGuess);
                    text.setVisibility(View.GONE);
                    ((EditText) findViewById(R.id.userText1)).setText("");
                    ((EditText) findViewById(R.id.userText2)).setText("");
                    ((EditText) findViewById(R.id.userText3)).setText("");
                    ((EditText) findViewById(R.id.userText4)).setText("");
                    ((EditText) findViewById(R.id.userText5)).setText("");
                    ((Button) findViewById(R.id.SubmitButton)).setText("Submit Results");
                    ((Button) findViewById(R.id.submitGuess)).setText("make a guess");
                    ((Button) findViewById(R.id.SubmitButton)).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_corner_green, null));
                    ((Button) findViewById(R.id.submitGuess)).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_corner_enter_grey, null));
                    userSubmit = false;
                } else {
                    guess++;
                    LinearLayout rowPrev = findViewById(R.id.row1);
                    LinearLayout row = findViewById(R.id.row1);
                    if (guess == 1) {
                        row = findViewById(R.id.row2);


                        findViewById(R.id.button11).setEnabled(false);
                        findViewById(R.id.button12).setEnabled(false);
                        findViewById(R.id.button13).setEnabled(false);
                        findViewById(R.id.button14).setEnabled(false);
                        findViewById(R.id.button15).setEnabled(false);

                        findViewById(R.id.button21).setEnabled(true);
                        findViewById(R.id.button22).setEnabled(true);
                        findViewById(R.id.button23).setEnabled(true);
                        findViewById(R.id.button24).setEnabled(true);
                        findViewById(R.id.button25).setEnabled(true);

                    } else if (guess == 2) {
                        rowPrev = findViewById(R.id.row2);
                        row = findViewById(R.id.row3);

                        findViewById(R.id.button21).setEnabled(false);
                        findViewById(R.id.button22).setEnabled(false);
                        findViewById(R.id.button23).setEnabled(false);
                        findViewById(R.id.button24).setEnabled(false);
                        findViewById(R.id.button25).setEnabled(false);

                        findViewById(R.id.button31).setEnabled(true);
                        findViewById(R.id.button32).setEnabled(true);
                        findViewById(R.id.button33).setEnabled(true);
                        findViewById(R.id.button34).setEnabled(true);
                        findViewById(R.id.button35).setEnabled(true);
                        ;
                    } else if (guess == 3) {
                        rowPrev = findViewById(R.id.row3);
                        row = findViewById(R.id.row4);

                        findViewById(R.id.button31).setEnabled(false);
                        findViewById(R.id.button32).setEnabled(false);
                        findViewById(R.id.button33).setEnabled(false);
                        findViewById(R.id.button34).setEnabled(false);
                        findViewById(R.id.button35).setEnabled(false);

                        findViewById(R.id.button41).setEnabled(true);
                        findViewById(R.id.button42).setEnabled(true);
                        findViewById(R.id.button43).setEnabled(true);
                        findViewById(R.id.button44).setEnabled(true);
                        findViewById(R.id.button45).setEnabled(true);
                        ;
                    } else if (guess == 4) {
                        rowPrev = findViewById(R.id.row4);
                        row = findViewById(R.id.row5);

                        findViewById(R.id.button41).setEnabled(false);
                        findViewById(R.id.button42).setEnabled(false);
                        findViewById(R.id.button43).setEnabled(false);
                        findViewById(R.id.button44).setEnabled(false);
                        findViewById(R.id.button45).setEnabled(false);


                        findViewById(R.id.button51).setEnabled(true);
                        findViewById(R.id.button52).setEnabled(true);
                        findViewById(R.id.button53).setEnabled(true);
                        findViewById(R.id.button54).setEnabled(true);
                        findViewById(R.id.button55).setEnabled(true);
                    } else if (guess == 5) {
                        rowPrev = findViewById(R.id.row5);
                        row = findViewById(R.id.row6);

                        findViewById(R.id.button51).setEnabled(false);
                        findViewById(R.id.button52).setEnabled(false);
                        findViewById(R.id.button53).setEnabled(false);
                        findViewById(R.id.button54).setEnabled(false);
                        findViewById(R.id.button55).setEnabled(false);


                        findViewById(R.id.button61).setEnabled(true);
                        findViewById(R.id.button62).setEnabled(true);
                        findViewById(R.id.button63).setEnabled(true);
                        findViewById(R.id.button64).setEnabled(true);
                        findViewById(R.id.button65).setEnabled(true);
                    } else if (guess == 6) {
                        rowPrev = findViewById(R.id.row6);
                        row = findViewById(R.id.row6);

                        findViewById(R.id.button61).setEnabled(false);
                        findViewById(R.id.button62).setEnabled(false);
                        findViewById(R.id.button63).setEnabled(false);
                        findViewById(R.id.button64).setEnabled(false);
                        findViewById(R.id.button65).setEnabled(false);


                    }


                    for (int x = 0; x < tileButtons.get(guess - 1).get(x).length(); x++) {
                        tileButtons.get(guess - 1).get(x).setEnabled(false);
                        if (guess < 6) {
                            tileButtons.get(guess).get(x).setEnabled(true);
                        }
                    }

                    // wordle solver


                    String answer = "";
                    if (guess == 1) {


                        if (((ColorDrawable) findViewById(R.id.button11).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button11).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button12).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button12).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button13).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button13).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button14).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button14).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button15).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button15).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }


                    } else if (guess == 2) {


                        if (((ColorDrawable) findViewById(R.id.button21).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button21).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button22).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button22).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button23).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button23).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button24).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button24).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button25).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button25).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }

                    } else if (guess == 3) {

                        if (((ColorDrawable) findViewById(R.id.button31).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button31).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button32).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button32).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button33).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button33).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button34).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button34).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button35).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button35).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }


                    } else if (guess == 4) {


                        if (((ColorDrawable) findViewById(R.id.button41).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button41).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button42).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button42).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button43).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button43).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button44).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button44).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button45).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button45).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }


                    } else if (guess == 5) {


                        if (((ColorDrawable) findViewById(R.id.button51).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button51).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button52).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button52).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button53).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button53).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button54).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button54).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button55).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button55).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                    } else if (guess == 6) {


                        if (((ColorDrawable) findViewById(R.id.button61).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button61).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button62).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button62).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button63).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button63).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button64).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button64).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                        if (((ColorDrawable) findViewById(R.id.button65).getBackground()).getColor() == Color.parseColor("#6D6E81")) {
                            answer = answer + "N";
                        } else if (((ColorDrawable) findViewById(R.id.button65).getBackground()).getColor() == Color.parseColor("#FAD002")) {
                            answer = answer + "Y";
                        } else {
                            answer = answer + "G";
                        }
                    }


                    if (guess < 6) {
                        System.out.println(guess);
                        if (!checker(answer)) {
                            rowPrev.setBackground(Drawable.createFromPath("@null"));
                            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                            findViewById(R.id.SubmitButton).setVisibility(View.INVISIBLE);
                            findViewById(R.id.submitGuess).setVisibility(View.INVISIBLE);
                            for (int x = 0; x < word.size(); x++) {
                                word.set(x, wordGuess.charAt(x));
                            }
                            listCleaner();
                            words.add(wordGuess);
                            setAdapter();

                        } else {
                            wordFinder();
                            if(words.size() == 0)
                            {
                                rowPrev.setBackground(Drawable.createFromPath("@null"));
                                Toast.makeText(this, "Sorry, I do not have any more words", Toast.LENGTH_LONG).show();
                                findViewById(R.id.SubmitButton).setVisibility(View.INVISIBLE);
                                findViewById(R.id.submitGuess).setVisibility(View.INVISIBLE);
                                setAdapter();
                            }
                            else
                                {
                                    setAdapter();
                                    setButtonsText();
                                    row.setVisibility(View.VISIBLE);
                                    rowPrev.setBackground(Drawable.createFromPath("@null"));
                                }
                        }
                    } else {
                        rowPrev.setBackground(Drawable.createFromPath("@null"));
                        Toast.makeText(this, "Out of Guesses", Toast.LENGTH_LONG).show();
                        findViewById(R.id.SubmitButton).setVisibility(View.INVISIBLE);
                        findViewById(R.id.submitGuess).setVisibility(View.INVISIBLE);
                    }


                }
                break;
            case R.id.resetButton:

                words = new ArrayList<>();
                common = new ArrayList<>();
                guess = 0;
                input = new Scanner(System.in);
                usedChar = new ArrayList<>();
                containedChar = new ArrayList<>();
                word = new ArrayList<>(Arrays.asList('-', '-', '-', '-', '-'));
                yellowWord = new ArrayList<>();
                wordGuess = "roate";
                yellows = false;
                containsTwo = '-';
                Intent refresh = new Intent(this, MainActivity.class);
                startActivity(refresh);
                this.finish(); //

                break;

            case R.id.submitGuess:



                    mLastClickTime = SystemClock.elapsedRealtime();
                    if (!userSubmit) {

                        for (int x = 0; x < 5; x++) {
                            tileButtons.get(guess).get(x).setEnabled(false);
                        }
                        LinearLayout text = findViewById(R.id.userGuess);
                        text.setVisibility(View.VISIBLE);

                        ((Button) findViewById(R.id.submitGuess)).setText("Guess");
                        ((Button) findViewById(R.id.SubmitButton)).setText("Cancel");
                        ((Button) findViewById(R.id.SubmitButton)).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_corner_red, null));
                        ((Button) findViewById(R.id.submitGuess)).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_corner_green, null));


                        if (findViewById(R.id.userText1).requestFocus()) {
                            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(findViewById(R.id.userText1), InputMethodManager.SHOW_IMPLICIT);
                        }

                        userSubmit = true;
                    } else {
                        for (int x = 0; x < 5; x++) {
                            tileButtons.get(guess).get(x).setEnabled(true);
                        }
                        getUserText();
                        setButtonsText();

                        LinearLayout text = findViewById(R.id.userGuess);
                        text.setVisibility(View.GONE);

                        ((EditText) findViewById(R.id.userText1)).setText("");
                        ((EditText) findViewById(R.id.userText2)).setText("");
                        ((EditText) findViewById(R.id.userText3)).setText("");
                        ((EditText) findViewById(R.id.userText4)).setText("");
                        ((EditText) findViewById(R.id.userText5)).setText("");


                        ((Button) findViewById(R.id.SubmitButton)).setText("Submit Results");
                        ((Button) findViewById(R.id.submitGuess)).setText("make a guess");
                        ((Button) findViewById(R.id.SubmitButton)).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_corner_green, null));
                        ((Button) findViewById(R.id.submitGuess)).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_corner_enter_grey, null));
                        userSubmit = false;
                    }

                break;
            case R.id.drawerButton:
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.open();
                break;
            default:

                ColorDrawable buttonColor = (ColorDrawable) view.getBackground();

                if (buttonColor.getColor() == Color.parseColor("#FAD002")) {
                    view.setBackgroundColor(Color.parseColor("#458802"));
                } else if (buttonColor.getColor() == Color.parseColor("#458802")) {
                    view.setBackgroundColor(Color.parseColor("#6D6E81"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#FAD002"));
                }

        }
    }


    public void setAdapter() {
        Typeface mTypeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, words) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.WHITE);

                item.setTypeface(mTypeface);

                return item;
            }
        };

        listView.setAdapter(arrayAdapter);
    }


    public void getUserText() {
        if(validInput()) {
            wordGuess = userInTest;
            userInTest = "";
        }
    }


    public boolean validInput()
    {
        userInTest = userInTest + ((EditText) findViewById(R.id.userText1)).getText().toString();
        userInTest = userInTest + ((EditText) findViewById(R.id.userText2)).getText().toString();
        userInTest = userInTest + ((EditText) findViewById(R.id.userText3)).getText().toString();
        userInTest = userInTest + ((EditText) findViewById(R.id.userText4)).getText().toString();
        userInTest = userInTest + ((EditText) findViewById(R.id.userText5)).getText().toString();
        userInTest = userInTest.toLowerCase();

        if(userInTest.length() ==5)
        {
            for(int x = 0 ; x < userInTest.length(); x++)
            {

                if(usedChar.contains(userInTest.charAt(x)))
                {
                    Toast.makeText(this, "Some of the characters in your guess are not in the answer", Toast.LENGTH_LONG).show();
                    return false;

                }
            }
           return true;
        }
        else
            {
                Toast.makeText(this, "Please fill all spaces in guess", Toast.LENGTH_LONG).show();
                return false;
            }
    }


    public void popUpShow(int index)
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popup = inflater.inflate(R.layout.popup_list, findViewById(R.id.drawer_layout), false);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        PopupWindow popupWindow = new PopupWindow(popup, width, height, true);

        popupWindow.showAtLocation(findViewById(R.id.drawer_layout), Gravity.CENTER, 0, 0);

        Button guessPop = (Button) popup.findViewById(R.id.guessPop);
        Button cancelPop = (Button)  popup.findViewById(R.id.cancelPop);


        guessPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wordGuess = words.get(index);
                setButtonsText();
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.close();
                popupWindow.dismiss();

            }
        });


        cancelPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                return;
            }
        });
        // dismiss the popup window when touched
        popup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });




    }


    public  void showInstructs()
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popup = inflater.inflate(R.layout.popup_instructions, findViewById(R.id.drawer_layout), false);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        PopupWindow popupWindow = new PopupWindow(popup, width, height, true);

        popupWindow.showAtLocation(findViewById(R.id.drawer_layout), Gravity.CENTER, 0, 0);


        // dismiss the popup window when touched
        popup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                popupWindow.dismiss();
                return true;
            }
        });

    }
}








