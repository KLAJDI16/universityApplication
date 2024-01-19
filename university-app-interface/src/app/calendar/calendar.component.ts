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

  constructor(private apiService: ApiService) {}

  public eventData: EventSettingsModel = {
 
    dataSource: [{

        Id: 1,

        Subject: 'Board Meeting',

        StartTime: new Date(2018, 10, 30, 9, 0),

        EndTime: new Date(2018, 10, 30, 11, 0)

    },

        {

            Id: 2,

            Subject: 'Training session on JSP',

            StartTime: new Date(2018, 10, 30, 15, 0),

            EndTime: new Date(2018, 10, 30, 17, 0)

        },

        {

            Id: 3,

            Subject: 'Sprint Planning with Team members',

            StartTime: new Date(2018, 10, 30, 9, 30),

            EndTime: new Date(2018, 10, 30, 11, 0)

        }]

}
  ngOnInit() {
    this.retrieveUserCourses();
  }

  retrieveUserCourses() {
    const user= sessionStorage.getItem('user');
    this.apiService.userCourses(user).subscribe((courses: any[]) => {
      this.events = courses.map((course) => {
        const start = course.startDate;
        const end = course.endDate;

        return {
          start,
          end,
          title: course.courseName,
        } as CalendarEvent;
      });
    });
  }

  

  handleDayClick(day: any) {
    // Handle day click logic here
  }
}


