package com.example.appexcerse.area.admin.logicUtil;

import android.content.Context;

import com.example.appexcerse.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Util {
    public static String conectListString(List<String> list, String character) {
        StringBuilder result = new StringBuilder();
        if (list.size() == 0) return "";
        result.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            result.append(character);
            result.append(list.get(i));
        }
        return result.toString();
    }
    public static List<String> selectedChips(ChipGroup chipGroup) {
        List<String> selectedItem = new ArrayList<>();
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip currentChip = (Chip) chipGroup.getChildAt(i);
            if (currentChip.isChecked()) {
                selectedItem.add(currentChip.getText().toString());
            }
        }
        return selectedItem;
    }
    public static List<String> reverseStringToList(String base, String character) {
        String[] string = base.split(character);
        return Arrays.asList(string);
    }
    public static void loadChip(ChipGroup chipGroup, List<String> list, Context context , List<String> selectedList) {
        if (selectedList == null)
            selectedList = new ArrayList<>();
        for (String element : list) {
            Chip chip = new Chip(context);
            ChipDrawable drawable = ChipDrawable.createFromAttributes(context, null, 0, R.style.Widget_MaterialComponents_Chip_Choice);
            chip.setChipDrawable(drawable);
            chip.setPadding(10, 10, 10, 10);
            chip.setText(element);
            chip.setChecked(selectedList.indexOf(element) >= 0);
            chipGroup.addView(chip);
        }
    }
    public static String getNow(){
        SimpleDateFormat ISO_8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");
        String now = ISO_8601_FORMAT.format(new Date());
        return now;
    } public static String dateToHumanReadableString(Date date){
        SimpleDateFormat vnFormat = new SimpleDateFormat("dd-MM-yyyy");
        String now = vnFormat.format(date);
        return now;
    }
    public static Date ParseISO_8601_FORMATToDate(String string){
        SimpleDateFormat ISO_8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");
        try {
            return ISO_8601_FORMAT.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }




}
