import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { CalendarComponent } from './calendar/calendar.component';
import { AllCoursesComponent } from './all-courses/all-courses.component';
import { CoursePageComponent } from './course-page/course-page.component';
import { TopRatedComponent } from './top-rated/top-rated.component';

const routes: Routes = [
  { path: 'login', component: LogInComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'calendar', component: CalendarComponent },
  { path: 'all-courses', component: AllCoursesComponent },
  { path: 'course-page/:id', component: CoursePageComponent },
  { path: 'top-rated', component: TopRatedComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
