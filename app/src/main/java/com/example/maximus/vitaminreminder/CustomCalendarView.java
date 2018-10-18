package com.example.maximus.vitaminreminder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;

public class CustomCalendarView implements DayViewDecorator {
    private final int color;
    private final CalendarDay day;
    static MaterialCalendarView calendarView;

    public CustomCalendarView(int color, CalendarDay day, MaterialCalendarView materialCalendarView) {
        this.color = color;
        this.day = day;
        this.calendarView = materialCalendarView;
    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        return calendarDay.equals(day);
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {
        dayViewFacade.addSpan(new DotSpan(5, color));
    }


    public static void addDotSpan(int color) {
        calendarView.addDecorator(new CustomCalendarView(color, CalendarDay.today(), calendarView));
    }
}
