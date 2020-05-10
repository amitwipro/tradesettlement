package com.digi.trade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForTradeSettlementRequest {

    private  long tradeRefNumber;
    private BrokerageType brokerageType;

    public enum BrokerageType{
        PLATINUM
    }

}
