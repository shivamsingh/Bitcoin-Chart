package com.blockchain.ui.chart.martketprice;

import com.blockchain.data.chart.marketprice.MarketPrice;
import com.blockchain.domain.chart.marketprice.RetrieveMarketPriceList;
import com.blockchain.testcommon.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static polanski.option.Option.none;

public class MarketPriceViewModelTest extends BaseTest {

    @Mock
    private RetrieveMarketPriceList interactor;

    @Mock
    private MarketPriceEntityMapper mapper;

    private MarketPriceViewModel viewModel;

    private ArrangeBuilder arrangeBuilder;

    @Before
    public void setup() {
        arrangeBuilder = new ArrangeBuilder();
        viewModel = new MarketPriceViewModel(interactor, mapper);
    }

    @Test
    public void marketPriceFromInteractorAreMapped() throws Exception {
        List<MarketPriceEntity> marketPriceEntities = Collections.singletonList(Mockito.mock(MarketPriceEntity.class));
        List<MarketPrice> marketPrices = Collections.singletonList(Mockito.mock(MarketPrice.class));

        arrangeBuilder
                .withMappedMarketPriceEntities(marketPriceEntities)
                .interactorEmitsMarketPrice(marketPrices);

        Mockito.verify(mapper).apply(marketPrices);
    }

    private class ArrangeBuilder {

        BehaviorSubject<List<MarketPrice>> interactorSubject = BehaviorSubject.create();

        private ArrangeBuilder() {
            Mockito.when(interactor.getBehaviourStream(none())).thenReturn(interactorSubject);
        }

        private ArrangeBuilder withMappedMarketPriceEntities(List<MarketPriceEntity> marketPriceEntities) throws Exception {
            Mockito.when(mapper.apply(Mockito.anyList())).thenReturn(marketPriceEntities);
            return this;
        }

        private ArrangeBuilder interactorEmitsMarketPrice(List<MarketPrice> marketPrices) {
            interactorSubject.onNext(marketPrices);
            return this;
        }
    }
}