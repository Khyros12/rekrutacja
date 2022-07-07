package com.wojciech.olszewski.cargo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WeightUnit {
    @SerializedName("kg")
    KG("kg"),
    @SerializedName("lb")
    LB("lb");

    final String unit;
}
