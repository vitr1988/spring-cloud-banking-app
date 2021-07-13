package com.luxoft.training.spring.cloud;

import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HistoryController implements ApplicationListener<AbstractFinancialEvent> {

    private Map<Date, BigDecimal> historyMap = Collections.synchronizedMap(new LinkedHashMap<>());

    @GetMapping
    public Map<Date, BigDecimal> getHistory() {
        return historyMap;
    }

    @Override
    public void onApplicationEvent(AbstractFinancialEvent event) {
        historyMap.put(new Date(event.getTimestamp()), event.getSum());
    }
}
