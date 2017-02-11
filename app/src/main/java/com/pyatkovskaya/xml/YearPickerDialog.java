package com.pyatkovskaya.xml;

import android.content.Context;
import android.os.Build;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by Natali-Pi on 07.02.2017.
 */

public class YearPickerDialog extends PickerDialog {

    private static final int NUMBER_OF_INTEGER_PICKERS = 4;
    private static final int NUMBER_OF_DECIMAL_PICKERS = 0;

    private static String innerResultUnits = ""; //will be used inside dialog only

    public YearPickerDialog(Context context, String dialogTitle, float startValue) {
        super(context, dialogTitle, startValue, NUMBER_OF_INTEGER_PICKERS, NUMBER_OF_DECIMAL_PICKERS); //3 integers and no decimal picker
    }

    @Override
    protected void setUpStyle(NumberPicker[] integerNumberPickers, NumberPicker[] decimalNumberPickers, TextView result) {
        //max value will be 359 degrees,
        integerNumberPickers[0].setMinValue(1);
        integerNumberPickers[0].setMaxValue(2);

        /*
        * If the start value for dialog is 059-099 or 159-199 or 259-299,
        * we put second pickers max value to 9
        * later in onNumberPickersValuesChange() it will be adjusted
        */
        integerNumberPickers[1].setMinValue(0);
        integerNumberPickers[1].setMaxValue(9);

        integerNumberPickers[2].setMinValue(0);
        integerNumberPickers[2].setMaxValue(9);

        integerNumberPickers[3].setMinValue(0);
        integerNumberPickers[3].setMaxValue(9);
        int color;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            color = getContext().getResources().getColor(R.color.text_color);
        } else {
            color = getContext().getColor(R.color.text_color);
        }
        result.setTextColor(color);
        for (int i = 0; i < integerNumberPickers.length; i++) {
            setNumberPickerTextColor(integerNumberPickers[i], color);
        }




    }

    /**
     * @param collectedValue normally value obtained by calling method collect
     * @return prepared and formatted value as text
     */
    @Override
    protected String getInnerResultString(float collectedValue) {
        return ""+(int)collectedValue;
    }

    @Override
    protected void onNumberPickersValuesChange(NumberPicker[] integerNumberPickers, NumberPicker[] decimalNumberPickers) {


        //get value from pickers
        float collectedValue = collect(integerNumberPickers, decimalNumberPickers);

        //we have 359 degrees so...
        if (collectedValue < 2000) {// we don't want give to user chance, to choose 390 degrees
            //we have to set 5 as maximum value of middle nuber picker to get 359 degrees max
            integerNumberPickers[1].setMinValue(9);
            integerNumberPickers[1].setMaxValue(9);


        } else {//in case it's less than 300 all is ok and max value is 299
            integerNumberPickers[1].setMinValue(0);
            integerNumberPickers[1].setMaxValue(0);

        }

        super.onNumberPickersValuesChange(integerNumberPickers, decimalNumberPickers);// refreshes result, after changes
    }

    /**
     * Sets the units of result value
     *
     * @param units units to be added to the end of result inside dialog
     * @return this
     */
    public static void setInnerResultUnits(String units) {
        innerResultUnits = units;

    }
}