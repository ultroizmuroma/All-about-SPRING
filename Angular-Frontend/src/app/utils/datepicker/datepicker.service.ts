import { Injectable } from '@angular/core';
import { Subject } from "rxjs/Subject";
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DatepickerService{
  private date = new Subject<Date>();
  constructor(){}

  getDate(): Observable<Date> {
    return this.date.asObservable();
  }

  pushData(date: Date){
    this.date.next(date);
  }
}
