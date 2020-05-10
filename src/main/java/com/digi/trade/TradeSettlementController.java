package com.digi.trade;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class TradeSettlementController {

    private final RestTemplate restTemplate;

    @PostMapping("/trade-settlement")
    public ForTradeSettlementResponse applyForTradeSettlement(@RequestBody  final ForTradeSettlementRequest forTradeSettlementRequest){
         final long tradeRefNumber = forTradeSettlementRequest.getTradeRefNumber();
         final TradeVerifyResponse tradeVerifyResponse = restTemplate.postForObject("http://localhost:9001/trade-verify",
                 new TradeVerifyRequest(tradeRefNumber),TradeVerifyResponse.class);
         if(tradeVerifyResponse.getTradeVolumeType()== TradeVerifyResponse.TradeVolumeType.HIGH
         && forTradeSettlementRequest.getBrokerageType() == ForTradeSettlementRequest.BrokerageType.PLATINUM){
            return  new ForTradeSettlementResponse(ForTradeSettlementResponse.GrantStatus.APPROVED);
         }
         throw new RuntimeException("Trade is not ready for settlement");
    }
}
