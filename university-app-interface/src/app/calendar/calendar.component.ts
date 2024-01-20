import { Component } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { ApiService } from '../api.service';
import { EventSettingsModel} from '@syncfusion/ej2-angular-schedule';
import { ScheduleModule } from '@syncfusion/ej2-angular-schedule';
import { NavBarComponent } from '../nav-bar/nav-bar.component';

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [ScheduleModule, NavBarComponent],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent {
  view: CalendarView = CalendarView.Month;
  viewDate: Date = new Date();
  events: CalendarEvent[] = [];
  activeDayIsOpen = true;
  public eventSettings!: EventSettingsModel;
  public courses: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.retrieveUserCourses();
    this.eventSettings = {
      dataSource: this.generateWeeklyEvents(),
    };
  }

  retrieveUserCourses() {
    const user = sessionStorage.getItem('user');
    this.apiService.userCourses(user).subscribe((courses: any[]) => {
      this.courses = courses;
      this.events = this.generateWeeklyEvents(); 
    });
  }

  generateWeeklyEvents() {
    const today = new Date();
    const currentDayOfWeek = today.getDay();

    const startOfWeek = new Date(today.getFullYear(), today.getMonth(), today.getDate() - currentDayOfWeek); 

    const endOfWeek = new Date(today.getFullYear(), today.getMonth(), today.getDate() + (6 - currentDayOfWeek));

    const courses = this.courses; 

    const events = [];
    let currentDay = new Date(startOfWeek);

    while (currentDay <= endOfWeek) {
      for (const course of courses) {
        if (currentDay.getDay() === course.dayOfWeek - 1) {
          const startTime = new Date(currentDay);
          startTime.setHours(9, 0);

          const endTime = new Date(startTime);
          endTime.setHours(startTime.getHours() + 3);

          events.push({
            start: startTime,
            end: endTime,
            title: course.name,
          } as CalendarEvent);
        }
      }
      currentDay.setDate(currentDay.getDate() + 1);
    }

    return events;
  }

  
}