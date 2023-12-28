import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private httpClient: HttpClient) {}

  authenticate(data: any): Observable<any> {
    // Assuming your authentication endpoint is 'your-authentication-endpoint'
    return this.httpClient.post('your-authentication-endpoint', data);
  }
}

