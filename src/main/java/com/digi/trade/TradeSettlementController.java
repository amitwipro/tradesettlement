package com.digi.trade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class TradeSettlementController {

    private final RestTemplate restTemplate;

    @Value("${tradeverifier.baseurl}")
    private  String tradeVerifierBaseUrl;

    @PostMapping("/trade-settlement")
    public ForTradeSettlementResponse applyForTradeSettlement(@RequestBody  final ForTradeSettlementRequest forTradeSettlementRequest){

        final String uri = UriComponentsBuilder.fromHttpUrl(tradeVerifierBaseUrl)
                .path("trade-verify")
                .toUriString();

         final long tradeRefNumber = forTradeSettlementRequest.getTradeRefNumber();
         final TradeVerifyResponse tradeVerifyResponse = restTemplate.postForObject(uri,
                 new TradeVerifyRequest(tradeRefNumber),TradeVerifyResponse.class);
         if(tradeVerifyResponse.getTradeVolumeType()== TradeVerifyResponse.TradeVolumeType.HIGH
         && forTradeSettlementRequest.getBrokerageType() == ForTradeSettlementRequest.BrokerageType.PLATINUM){
            return  new ForTradeSettlementResponse(ForTradeSettlementResponse.GrantStatus.APPROVED);
         }
         throw new RuntimeException("Trade is not ready for settlement");
    }
}
