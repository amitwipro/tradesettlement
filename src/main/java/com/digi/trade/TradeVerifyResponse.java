package com.digi.trade;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TradeVerifyResponse {

    private TradeVolumeType tradeVolumeType;

    public enum TradeVolumeType{
        HIGH
    }
}
