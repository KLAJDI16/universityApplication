import { Injectable } from '@angular/core';
import { HttpClient, HttpParams  } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'http://localhost:8989';

  constructor(private http: HttpClient) {}


  register(user: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, user);
  }

  login(user: any): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/login`, user);
  }

  joinCourse(user: string, courseName: string): Observable<any> {
    const requestModel = { user, request: courseName };
    return this.http.post<any>(`${this.baseUrl}/join`, requestModel);
  }
  
  dropCourse(user: string, courseName: string): Observable<any> {
    const requestModel = { user, request: courseName };
    return this.http.post<any>(`${this.baseUrl}/drop`, requestModel);
  }

  retrieveCourses(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/retrieve`);
  }

  leaveFeedback(requestModel: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/leaveFeedback`, requestModel);
  }

  viewFeedbacks(courseName: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/view/${courseName}`);
  }

  topCourses(rate: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}courses/${rate}`);
  }

  userCourses(username: any): Observable<any[]> {
    const params = new HttpParams().set('username', username);
    return this.http.get<any[]>(`${this.baseUrl}/userCourses`, { params });
  }

  getUser(): string | null {
    const user = sessionStorage.getItem('username');
    return user;
  }
  
  
}


