package com.digi.trade;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ForTradeSettlementResponse {

    private final GrantStatus status;
    public enum GrantStatus {
        APPROVED
    }

}
