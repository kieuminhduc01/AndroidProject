package com.example.appexcerse.constant.listModel;

import com.example.appexcerse.constant.model.MucDichTap;
import com.example.appexcerse.constant.model.MuscleFocus;

import java.util.ArrayList;
import java.util.List;

public class ListMuscleFocus {
    public static List<String> getAll(){
        List<String> muscles=new ArrayList<>();
        muscles.add(MuscleFocus.Abs);
        muscles.add(MuscleFocus.Back);
        muscles.add(MuscleFocus.Biceps);
        muscles.add(MuscleFocus.Calves);
        muscles.add(MuscleFocus.Chest);
        muscles.add(MuscleFocus.Deltoid);
        muscles.add(MuscleFocus.ForceArms);
        muscles.add(MuscleFocus.Glutes);
        muscles.add(MuscleFocus.Lats);
        muscles.add(MuscleFocus.Quads);
        muscles.add(MuscleFocus.Triceps);
        muscles.add(MuscleFocus.Hams);

        return muscles;
    }
}
