import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApplicationUrlService {
  access_control: string = '/access-control';
  bokningar: string = '/bokningar/';
}
