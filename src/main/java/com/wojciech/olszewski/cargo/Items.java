package com.wojciech.olszewski.cargo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Items {
    private final static double LB_TO_KG_RATIO = 0.4536;
    private final static double KG_TO_LB_RATIO = 2.205;

    private long id;
    private int weight;
    @SerializedName("weightUnit")
    private WeightUnit weightUnit;
    private int pieces;

    public int getWeightKg() {
        return weightUnit.equals(WeightUnit.KG) ? weight : (int) (weight * LB_TO_KG_RATIO);
    }

    public int getWeightLb() {
        return weightUnit.equals(WeightUnit.LB) ? weight : (int) (weight * KG_TO_LB_RATIO);
    }

}