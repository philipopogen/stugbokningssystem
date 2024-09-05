import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class PermissionService {
  private apiBasUrl ='/api/v1/access-control';
  constructor(private http: HttpClient) { }

  public requestAccess(request: any) {
    const url = `${this.apiBasUrl}/request-access/`;
    return this.http.post(url, request, CommonService.getHttpOptions);
  }
}
