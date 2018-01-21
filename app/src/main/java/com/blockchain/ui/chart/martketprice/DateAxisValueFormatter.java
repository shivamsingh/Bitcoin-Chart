package com.blockchain.ui.chart.martketprice;

import android.text.format.DateFormat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

class DateAxisValueFormatter implements IAxisValueFormatter {
    private static final long DAY = 24 * 60 * 60 * 1000;
    private LineChart chart;

    DateAxisValueFormatter(LineChart chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        String format = chart.getVisibleXRange() / DAY > 30 * 6 ? "MMM yy" : "dd MMM";
        return (String) DateFormat.format(format, (long) value);
    }
}
