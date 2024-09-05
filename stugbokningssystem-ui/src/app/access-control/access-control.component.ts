import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PermissionService } from '../services/permission.service';
import { CommonModule } from '@angular/common';
import { ApplicationUrlService } from '../services/application-url.service';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-access-control',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule, ReactiveFormsModule, MatButtonModule],
  templateUrl: './access-control.component.html',
  styleUrl: './access-control.component.css'
})
export class AccessControlComponent {
  public accessRequestForm!: FormGroup;
  public submitted: boolean = false;
  public showAccessRequestForm: boolean = false
  public serverErrorMessge!: string
  private currentUserId!: number;

  constructor(private router: Router, private permissionService: PermissionService, public apllicationUrl: ApplicationUrlService) { }

  ngOnInit(): void {
    this.initAccessRequestForm()
  }

  private initAccessRequestForm() {
    this.accessRequestForm = new FormGroup({
      userId: new FormControl('',
        [
          Validators.required,
          Validators.minLength(3), Validators.maxLength(50),
        ]
      )
    }
    )
  }

  public submitAccessRequestForm() {
    this.submitted = true;
    this.serverErrorMessge = "";
    if (!this.accessRequestForm.valid) {
      return;
    }
    this.currentUserId = this.accessRequestForm.get('userId')?.value;
    const accessRequest = { "userId": this.accessRequestForm.get('userId')?.value }
    this.permissionService.requestAccess(accessRequest).subscribe(
      (res) => {
        this.submitted = false;
        this.showAccessRequestForm = false;
        this.router.navigate([this.apllicationUrl.bokningar, this.currentUserId]);
      },
      (error) => {
        this.submitted = false;
        this.serverErrorMessge = error.error.message;
      }
    );
  }

  public clearServerError() {
    this.serverErrorMessge = '';
  }

  public getErrorMessage(formControlName: string): string {
    if (!this.submitted) {
      return '';
    }
    const controlError: ValidationErrors =
      this.accessRequestForm.get(formControlName)?.errors!;
    if (controlError) {
      const errors = Object.keys(controlError);
      return errors[0];
    }
    return '';
  }
}
