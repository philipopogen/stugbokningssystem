import { PermissionService } from './../services/permission.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { BokningService } from '../services/bokning.service';
import { ApplicationUrlService } from '../services/application-url.service';
import { BokningResponse } from '../models/BokningResponse';
import { CommonService } from '../services/common.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, RouterLink,],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {

  public bokningResponse!: BokningResponse[]

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private permissionService: PermissionService,
    private bokninService: BokningService,
    public apllicationUrl: ApplicationUrlService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      const param = params['id']
      this.permissionService.requestAccess({ "userId": param }).subscribe(
        (res) => {
          this.hamtaBokningar(param);
        },
        (error) => {
          this.router.navigate([this.apllicationUrl.access_control]);
        }
      );
    });
  }

  public hamtaBokningar(userId: number) {
    const page = 0;
    const size = 10;
    this.bokninService.hamtaBokningar(userId, page, size).subscribe((res) => {
      this.bokningResponse = res.content;
    },
      (error) => {
        alert(error.error.message)
      }
    );
  }

  public hamtaBild(bildNamn: string) {
    return CommonService.hamtaBild(bildNamn);
  }


}
