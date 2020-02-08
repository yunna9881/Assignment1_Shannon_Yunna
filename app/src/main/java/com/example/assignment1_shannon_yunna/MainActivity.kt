package com.example.assignment1_shannon_yunna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast.*
import kotlinx.android.synthetic.main.activity_main.*

//class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        /****************************Amount**********************************/
        var choiceAmount: Double = 0.0
        //Edit Text Amount User Input Resources
        var myETAmount : EditText = findViewById(R.id.ETAmount)




        /*****************************Tips***********************************/

        var myETTip : EditText = findViewById(R.id.ETTip)
        myETTip.isEnabled=false

        // Array Items Resource by Id for Tip Spinner Selection
        val myArrayTips = resources.getStringArray(R.array.ArrayTips)

        //Spinner Resources
        val mySpinnerTip: Spinner = findViewById(R.id.SpinnerTip)

        //Instance Variable holds choice converted to double for calculations

        var choiceTip: Double=0.0;

        //Load the Spinner and Listener Set User's selection from Tip Spinner
        if (mySpinnerTip != null) {
            val myAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, myArrayTips
            )
            mySpinnerTip.adapter = myAdapter

            mySpinnerTip.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    when(myArrayTips[position]){
                        "10%"-> {myETTip.clearComposingText(); myETTip.isEnabled=false; choiceTip=0.10; }
                        "15%"-> {myETTip.clearComposingText(); myETTip.isEnabled=false; choiceTip=0.15; }
                        "20%"-> {myETTip.clearComposingText(); myETTip.isEnabled=false; choiceTip=0.20; }
                        else-> {myETTip.isEnabled=true; }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }


        }

        /*******************Spinner for Number of People**********************/
        var choicePeople=0;
        // Array Items for Number of People Spinner Selection
        val myArrayPeople = resources.getStringArray(R.array.ArrayPeople)

        //Spinner People Resource By Id
        val mySpinnerPeople: Spinner = findViewById(R.id.SpinnerNumPeople)
        //Spinner Listener and Set User's selection
        if (mySpinnerPeople != null) {
            val myAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, myArrayPeople
            )
            mySpinnerPeople.adapter = myAdapter

            mySpinnerPeople.onItemSelectedListener = object :

                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                   choicePeople= myArrayPeople[position].toInt();

                    }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }

        /*****************************Results Text Views*********************/

        var myTipResult : TextView = findViewById(R.id.TVTipResult)
        var myTotalResult : TextView = findViewById(R.id.TVTotalResult)
        var myPPResult : TextView = findViewById(R.id.TVPPResult)

        var myPerPersonTVLabel :TextView= findViewById(R.id.TVPP)
        myPerPersonTVLabel.visibility=View.INVISIBLE

        /******************************Buttons*****************************************/

        ///////////Calculate Button
        val myBtnCalculate = findViewById<Button>(R.id.btnCalculate) as Button
        //Button Calculate Listener
        myBtnCalculate.setOnClickListener() {

            if(myETAmount.text.isEmpty() || myETTip.isEnabled && myETTip.text.isEmpty()){
                val myToast = Toast.makeText(getApplicationContext(),"Missing Amount and/or Tip", Toast.LENGTH_SHORT)
                myToast.show();
            }else{
                //****need to check first if its a number.... crashes

                //get the input amount
                choiceAmount = myETAmount.text.toString().toDouble()

                //get the input tip
                if(myETTip.isEnabled){
                    choiceTip = myETTip.text.toString().toDouble()
                    choiceTip/=100.0
                }
                //calculate and display the result of tip and total

                myTipResult.text=(choiceAmount*choiceTip).toString()

                myTotalResult.text=(choiceAmount*(1+choiceTip)).toString()

                //calculate and display per person
                if(choicePeople>1){
                     myPerPersonTVLabel.visibility=View.VISIBLE
                     myPPResult.text=((choiceAmount*(1+choiceTip))/choicePeople).toString()
                }

            }


        }

        ////////////Clear Button
        val myBtnClear = findViewById<Button>(R.id.btnClear) as Button
        //Button Calculate Listener
        myBtnClear.setOnClickListener() {


        }

//        fun required(inputtx)
//        {
//            if (inputtx.value.length == 0)
//            {
//                alert("message");
//                return false;
//            }
//            return true;
//        }



    }


}
