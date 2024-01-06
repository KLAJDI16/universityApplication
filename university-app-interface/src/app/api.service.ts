import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}


  authenticate(data: any): Observable<any> {
    const url = `${this.baseUrl}/login`;
    return this.http.post(url, data);
  }

  register(data: any): Observable<any> {
    const url = `${this.baseUrl}/signup`;
    return this.http.post(url, data);
  }


}


