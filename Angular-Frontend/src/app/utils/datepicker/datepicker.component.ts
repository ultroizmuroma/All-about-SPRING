import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { NativeDateAdapter, DateAdapter, MAT_DATE_FORMATS, MatDatepickerInputEvent } from "@angular/material";
import { DatepickerService } from './datepicker.service';

export class AppDateAdapter extends NativeDateAdapter {

  format(date: Date, displayFormat: Object): string {

    if (displayFormat === 'input') {
      const day = date.getDate().toString().length < 2 ? "0" + date.getDate() : date.getDate();
      const month = (date.getMonth() + 1).toString().length < 2 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      const year = date.getFullYear();
      return `${day}.${month}.${year}`;
    } else {
      return date.toDateString();
    }
  }
}

export const APP_DATE_FORMATS =
  {
    parse: {
      dateInput: { month: 'short', year: 'numeric', day: 'numeric' },
    },
    display: {
      dateInput: 'input',
      monthYearLabel: { year: 'numeric', month: 'numeric' },
      dateA11yLabel: { year: 'numeric', month: 'long', day: 'numeric' },
      monthYearA11yLabel: { year: 'numeric', month: 'long' },
    }
  };

/** @title Basic datepicker */
@Component({
  selector: 'datepicker',
  templateUrl: 'datepicker.component.html',
  styleUrls: ['datepicker.component.css'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})
export class DatepickerComponent implements OnInit {
  constructor(private service: DatepickerService){}

  public date: Date;
  @Output() onChangeDate = new EventEmitter<Date>();

  ngOnInit(){
    this.date = new Date();
    this.service.pushData(this.date);
  }

  changeDate(event: MatDatepickerInputEvent<Date>) {
    this.service.pushData(event.value);
  }
}
