package com.pyatkovskaya.xml;

/**
 * Created by Natali-Pi on 07.02.2017.
 */

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * Created by MisterY on 28.12.2015.
 */
public abstract class PickerDialog extends Dialog {


    protected OnDoneListener onDoneListener = new OnDoneListener() {
        @Override
        public void onDone(float result) {
            Log.e("PickerDialog", "listener is not set");
        }
    };

    private static PickerDialog lastInstance = null;

    public PickerDialog(Context context, String dialogTitle, float startValue, int integerNumberPickersCount, int decimalNumberPickersCount) {
        super(context);

        //Prevents dialog to be shown multiple times, as we need only one instance of dialog
        if (lastInstance != null ) {
            lastInstance.dismiss();
            lastInstance = this;
        } else {
            lastInstance = this;
        }

        //simple layout for this dialog
        setContentView(R.layout.data_dialog);


        //prepare layout for our dialog
        LinearLayout pickersLayout = (LinearLayout) findViewById(R.id.pickerslayout);
        //creating number pickers for our amount of digits
        final NumberPicker[] integerNumberPickers = new NumberPicker[integerNumberPickersCount];
        final NumberPicker[] decimalNumberPickers = new NumberPicker[decimalNumberPickersCount];
        //initialisation of number pickers and inserting them into the layout
        initializeNumberPickers(context, pickersLayout, integerNumberPickers, decimalNumberPickers);
        //prepare result view for styling
        TextView result = (TextView) findViewById(R.id.result);
        //getting minimum and maximum numbers of pickers if overrided by subclass
        setUpStyle(integerNumberPickers, decimalNumberPickers, result);

        //preparing start value for number pickers, value must be separated by digits and stored to an array
        //we use separate arrays for integer part and decimal part of value
        int[] integerDigits = prepareValuesForPickers(startValue, integerNumberPickersCount, false);
        int[] decialDigits = prepareValuesForPickers(startValue, decimalNumberPickersCount, true);

        //change value shown by number pickers to match the start value
        setValueForNumberPickers(integerNumberPickers, decimalNumberPickers, integerDigits, decialDigits);

        //refresh the result text view to show correct value
        onNumberPickersValuesChange(integerNumberPickers, decimalNumberPickers);

        // preparing listener to the done button
        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoneListener.onDone(collect(integerNumberPickers, decimalNumberPickers));

                //close dialog
                dismiss();
            }
        });

    }


    /**
     * Owerride in subclass to set starting minimum and maximum values to integer and decimal number pickers
     * Note: called before start value is set. And right after value is set, method onNumberPickersValuesChange() is called,
     * to check if any additional adjustment required.
     *
     * @param integerNumberPickers array of NumberPikers used to choose integer part of value
     * @param decimalNumberPickers array of NumberPikers used to choose decimal part of value
     * @see #onNumberPickersValuesChange(NumberPicker[], NumberPicker[])
     * @see #setValueForNumberPickers(NumberPicker[], NumberPicker[], int[], int[])
     */
    abstract protected void setUpStyle(@NonNull NumberPicker[] integerNumberPickers, @NonNull NumberPicker[] decimalNumberPickers, TextView result);

    /**
     * Preparing layout for number pickers, adds decimal point if required, setting listeners and putting all together
     *
     * @param context              context of an App
     * @param integerNumberPickers array of NumberPikers used to choose integer part of value
     * @param decimalNumberPickers array of NumberPikers used to choose decimal part of value
     * @param pickersLayout        LinearLayout in which NumberPickers will be inserted
     */
    private void initializeNumberPickers(@NonNull Context context, @NonNull LinearLayout pickersLayout, @NonNull final NumberPicker[] integerNumberPickers, @NonNull final NumberPicker[] decimalNumberPickers) {
        //preparing listener for pickers
        NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //when any picker changes it's value, it calls general method for all listeners in this dialog
                onNumberPickersValuesChange(integerNumberPickers, decimalNumberPickers);
            }
        };
        //preparing all integer number pickers
        for (int i = 0; i < integerNumberPickers.length; i++) {

            integerNumberPickers[i] = new NumberPicker(context);
            integerNumberPickers[i].setOnValueChangedListener(onValueChangeListener);
            //Hides keyboard when number picker is clicked
            integerNumberPickers[i].setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            pickersLayout.addView(integerNumberPickers[i]);
        }
        //if user needs to choose decimal value
        if (decimalNumberPickers.length > 0) {

            //adding point between number pickers to make it more look like float
            pickersLayout.addView(getDecimalView(context));
            //preparing all decimal number pickers
            for (int i = 0; i < decimalNumberPickers.length; i++) {
                decimalNumberPickers[i] = new NumberPicker(context);
                decimalNumberPickers[i].setOnValueChangedListener(onValueChangeListener);
                //Hides keyboard when number picker is clicked
                decimalNumberPickers[i].setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
                pickersLayout.addView(decimalNumberPickers[i]);
            }
        }
    }

    /**
     * Sets values to number pickers.
     * Note: called after Min-max value is set and just before method onNumberPickersValuesChange() is called
     *
     * @param integerNumberPickers array of NumberPikers used to choose integer part of value
     * @param decimalNumberPickers array of NumberPikers used to choose decimal part of value
     * @param integerValue         array of digits from integer part of value
     * @param decimalValue         array of digits from decimal part of value
     * @see #setUpStyle(NumberPicker[], NumberPicker[], TextView)
     * @see #onNumberPickersValuesChange(NumberPicker[], NumberPicker[])
     */
    private void setValueForNumberPickers(@NonNull NumberPicker[] integerNumberPickers, @NonNull NumberPicker[] decimalNumberPickers, @NonNull int[] integerValue, @NonNull int[] decimalValue) {

        //for every number picker set digit from array
        for (int i = 0; i < integerNumberPickers.length; i++) {
            integerNumberPickers[i].setValue(integerValue[i]);
        }

        //if we have decimal value
        if (decimalNumberPickers.length > 0) {
            //for every number picker set digit from array
            for (int i = 0; i < decimalNumberPickers.length; i++) {

                decimalNumberPickers[i].setValue(decimalValue[i]);
            }
        }
    }

    /**
     * Collects values from number pickers and assemble it to float
     *
     * @param integerNumberPickers array of NumberPikers used to choose integer part of value
     * @param decimalNumberPickers array of NumberPikers used to choose part of value
     * @return assembled value, collected from all integer and decimal NumberPickers
     */
    protected float collect(@NonNull NumberPicker[] integerNumberPickers, @NonNull NumberPicker[] decimalNumberPickers) {
        String result = "";
        //gathering values from all number pickers to string
        for (int i = 0; i < integerNumberPickers.length; i++) {
            result += integerNumberPickers[i].getValue();
        }
        // if we have decimal number pickers
        if (decimalNumberPickers.length > 0) {
            //to make value in string look like decimal
            result += ".";
            //collecting decimal part from pickers
            for (int i = 0; i < decimalNumberPickers.length; i++) {
                result += decimalNumberPickers[i].getValue();
            }
        }
        //convert string to float
        return Float.parseFloat(result);
    }

    /**
     * Normally hould be used to set value for number pickers calling method setValueForNumberPickers()
     *
     * @param value              float to be converted to digits
     * @param numberPickersCount amount of number pickers for which this value is converted, returned array will have same length
     * @param isForDecimalPart   true if this method called to get only decimal part of value false if only integer part
     * @return array of digits based on value
     */
    private int[] prepareValuesForPickers(float value, int numberPickersCount, boolean isForDecimalPart) {

        int[] values = new int[numberPickersCount];
        String digits = "";
        if (!isForDecimalPart) {
            digits = new String("" + value).split(Pattern.quote("."))[0];
        } else {
            digits = new String("" + value).split(Pattern.quote("."))[1];
        }

        for (int numPickIndex = numberPickersCount - 1, digitIndex = 1; numPickIndex >= 0 && digitIndex <= digits.length(); numPickIndex--, digitIndex++) {
            values[numPickIndex] = Integer.parseInt("" + digits.charAt(digits.length() - digitIndex));
        }
        return values;
    }

    /**
     * Use to get TextView with point which should be inserted inside LinearLayout between number pickers to separate integer value from decimal
     *
     * @param context this apps context
     * @return TextView representing decimal point between number pickers
     */
    private TextView getDecimalView(@NonNull Context context) {
        //creating blank Text View
        TextView decimal = new TextView(context);
        //text is point
        decimal.setText(".");
        //size 64 to make shure user will see it
        decimal.setTextSize(64);
        // android:layout_width="wrap_content" android:layout_height="match_parent"
        decimal.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // places the "." in the middle so it will look properly
        decimal.setGravity(Gravity.CENTER_VERTICAL);
        return decimal;
    }

    /**
     * By default only refreshes TextView with result, override in subclass if you need to add refreshing of something else
     *
     * @param integerNumberPickers array of NumberPikers used to choose integer part of value
     * @param decimalNumberPickers array of NumberPikers used to choose decimal part of value
     */
    @CallSuper
    protected void refreshResultView(@NonNull NumberPicker[] integerNumberPickers, @NonNull NumberPicker[] decimalNumberPickers) {
        //collecting value from number pickers to float
        float collectedValue = collect(integerNumberPickers, decimalNumberPickers);

        //converts collected value from string to float
        String resultString = getInnerResultString(collectedValue);

        //obtaining TextView of result from layout
        TextView result = (TextView) findViewById(R.id.result);

        //sets result to TextView
        result.setText(resultString);
    }

    /**
     * By default only calls refreshResultView, override in subclass if you need to do anything else when number pickers touched
     * Note: called after min-max value is set and after starting value is set, all additional adjustment must go here
     *
     * @param integerNumberPickers array of NumberPikers used to choose integer part of value
     * @param decimalNumberPickers array of NumberPikers used to choose decimal part of value
     * @see #setUpStyle(NumberPicker[], NumberPicker[], TextView)
     * @see #setValueForNumberPickers(NumberPicker[], NumberPicker[], int[], int[])
     */
    @CallSuper
    protected void onNumberPickersValuesChange(@NonNull NumberPicker[] integerNumberPickers, @NonNull NumberPicker[] decimalNumberPickers) {
        refreshResultView(integerNumberPickers, decimalNumberPickers);
    }

    /**
     * Override in subclass to change result formatting inside dialog
     *
     * @param collectedValue normally value obtained by calling method collect
     * @return value converted to String
     * @see #collect(NumberPicker[], NumberPicker[])
     */
    protected String getInnerResultString(float collectedValue) {
        //By default simply returns collected value converted to String
        return "" + collectedValue;
    }

    /**
     * Sets listener for this dialog which will be invoked when Done button will be pressed
     *
     * @param onDoneListener implementation of interface OnDoneListener from this class
     * @return this object for easier usage in the client code
     * @see #onDoneListener
     */
    @CallSuper
    public PickerDialog setOnDoneListener(OnDoneListener onDoneListener) {
        this.onDoneListener = onDoneListener;
        return this;
    }

    /**
     * Sets font for the dialog, by default will be used standard android typeface
     *
     * @param typeface typeface to use
     * @return this object for easier usage in the client code
     */
    @CallSuper
    public PickerDialog setFont(Typeface typeface) {
        //obtaining result TextView from layout
        TextView result = (TextView) findViewById(R.id.result);
        //sets chosen font
        result.setTypeface(typeface);

        //obtaining done Button from layout
        Button done = (Button) findViewById(R.id.done);
        //sets chosen font
        done.setTypeface(typeface);

        return this;
    }


    public interface OnDoneListener {
        public void onDone(float result);
    }

    protected void setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();

                } catch (NoSuchFieldException e) {
                    Log.w("setNumberPickerTextClr", e);
                } catch (IllegalAccessException e) {
                    Log.w("setNumberPickerTextClr", e);
                } catch (IllegalArgumentException e) {
                    Log.w("setNumberPickerTextClr", e);
                }
            }
        }

    }
}