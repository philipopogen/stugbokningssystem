import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BokningRequest } from '../models/BokningRequest';
import { CommonService } from './common.service';
import { BekraftelseResponse } from '../models/BekraftelseResponse';

@Injectable({
  providedIn: 'root'
})
export class BokningService {

  private apiBasUrl ='/api/v1/bokning';
  constructor(private http: HttpClient) { }

  public bokaStuga(request: BokningRequest): Observable<BekraftelseResponse> {
    const url = `${this.apiBasUrl}/boka/`;
    return this.http.post<BekraftelseResponse>(url, request, CommonService.getHttpOptions);
  }

  public hamtaBokningar(userId: number, page: number, size: number): Observable<any> {
    const url = `${this.apiBasUrl}/bokningar/${userId}/${page}/${size}`;
    return this.http.get<any>(url);
  } 
    
}
