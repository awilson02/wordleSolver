package com.wordlesolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Probabilty {


    private static int numChars = 64715;
    private static int numWords = 12943;





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





    public static void updateProb(ArrayList<Character> usedChars)
    {
        if(MainActivity.words.size() == 0)
        {
            return;
        }
        for(int i =0; i <positionScore.size(); i++)
        {
            positionScore.set(i, new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0,0.0,0.0)));
        }


        updateScore(usedChars);
        updatePositionScore(usedChars);


    }


    private static void updateScore(ArrayList<Character> usedChars)
    {
        ArrayList<Integer> letterIndex = new ArrayList<>();

        for(int i = 0; i < usedChars.size(); i++)
        {
            letterIndex.add(letters.indexOf(usedChars));
        }

         numWords = MainActivity.words.size();

        //updating all other letter probs

        for ( int i = 0; i < score.size(); i++) {
            if (!letterIndex.contains(i)) {

                int count = 0;
                for (int x = 0; x < numWords; x++) {

                    if (MainActivity.words.get(x).contains(letters.get(i).toString())) {
                        count++;
                    }
                    for(int y = 0; y < MainActivity.words.get(x).length(); y++)
                    {

                        if(letters.get(i) == MainActivity.words.get(x).charAt(y))
                        {
                            positionScore.get(i).set(y ,  positionScore.get(i).get(y) + 1.0);
                        }

                    }

                }

                score.set(i, -((double) count / (double) numWords));
            }
        }

    }


    private static void updatePositionScore(ArrayList<Character> usedChars)
    {

        for ( int i = 0; i < positionScore.size(); i++)
        {
            double total = positionScore.get(i).get(0) + positionScore.get(i).get(1) + positionScore.get(i).get(2)  + positionScore.get(i).get(3) + positionScore.get(i).get(4);
            for (int y = 0; y < positionScore.get(i).size(); y++)
            {

                positionScore.get(i).set(y, positionScore.get(i).get(y) / total);
            }
        }

    }
}
