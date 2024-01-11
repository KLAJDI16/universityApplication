import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'myurl';

  constructor(private http: HttpClient) {}


  register(user: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/user/register`, user);
  }

  login(user: any): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/user/login`, user);
  }

  joinCourse(requestModel: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/user/join`, requestModel);
  }
  dropCourse(requestModel: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/user/drop`, requestModel);
  }

  retrieveCourses(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/user/retrieve`);
  }

  leaveFeedback(requestModel: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/user/leaveFeedback`, requestModel);
  }

  viewFeedbacks(courseName: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/user/view/${courseName}`);
  }

  topCourses(rate: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/user/courses/${rate}`);
  }

  listAllCourses(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/user/courses`);
  }

}


