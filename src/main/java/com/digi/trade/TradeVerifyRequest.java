package com.digi.trade;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TradeVerifyRequest {
    private final long tradeRefNumber;
}
