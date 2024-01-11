import { Component } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { ApiService } from '../api.service';


@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent {
  view: CalendarView = CalendarView.Month;
  viewDate: Date = new Date();
  events: CalendarEvent[] = [];
  activeDayIsOpen = true;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.retrieveCourses();
  }

  retrieveCourses() {
    this.apiService.retrieveCourses().subscribe((courses: any[]) => {
      this.events = courses.map((course) => {
        const start = this.calculateStartDate(course.courseDate, course.startTime);
        const end = this.calculateEndDate(course.courseDate, course.endTime);

        return {
          start,
          end,
          title: course.courseName,
        } as CalendarEvent;
      });
    });
  }

  calculateStartDate(courseDate: string, startTime: string): Date {
    const startDateTime = new Date(courseDate);
    const [hours, minutes] = startTime.split(':');

    startDateTime.setHours(parseInt(hours, 10));
    startDateTime.setMinutes(parseInt(minutes, 10));

    return startDateTime;
  }

  calculateEndDate(courseDate: string, endTime: string): Date {
    const startDateTime = this.calculateStartDate(courseDate, endTime);
    const endDateTime = new Date(startDateTime);
    const [hours, minutes] = endTime.split(':');

    endDateTime.setHours(parseInt(hours, 10));
    endDateTime.setMinutes(parseInt(minutes, 10));

    return endDateTime;
  }

  handleDayClick(day: any) {
    // Handle day click logic here
  }
}


