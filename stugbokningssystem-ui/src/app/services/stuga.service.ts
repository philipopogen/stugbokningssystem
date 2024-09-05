import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StugaService {
  constructor(private http: HttpClient) {}

  public hamtaStugor(page: number, size: number): Observable<any> {
    const url = `/api/v1/stuga/stugor/${page}/${size}`;
    return this.http.get<any>(url);
  }

}
