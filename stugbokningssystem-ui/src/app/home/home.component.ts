import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { StugaService } from '../services/stuga.service';
import { StugaResponse } from '../models/StugaResponse';
import { CommonModule } from '@angular/common';
import { BokningService } from '../services/bokning.service';
import { BokningRequest } from '../models/BokningRequest';
import { ApplicationUrlService } from '../services/application-url.service';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { CommonService } from '../services/common.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatToolbarModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  stugor!: StugaResponse[];
  public visaBokningsForm: boolean = false;
  bokastugaForm!: FormGroup;
  valtStugaId!: number;
  submitted: boolean = false;
  bekraftelseResponse = "";

  constructor(
    private router: Router,
    private stugaService: StugaService,
    private bokninService: BokningService,
    public apllicationUrl: ApplicationUrlService,
    public dialog: MatDialog
  ) {
    this.hamtaStuga();
    this.initStugaBokningForm()
  }

  private hamtaStuga() {
    const page = 0;
    const size = 10;
    this.stugaService.hamtaStugor(page, size).subscribe(resp => {
      this.stugor = resp.content
    })
  }

  private initStugaBokningForm() {
    this.bokastugaForm = new FormGroup({
      incheckningsdatum: new FormControl('', [Validators.required]),
      incheckningstid: new FormControl('', [Validators.required]),
      utcheckningsdatum: new FormControl('', [Validators.required]),
      utcheckningstid: new FormControl('', [Validators.required]),
      turistNamn: new FormControl('', [Validators.required, Validators.minLength(3)]),
      turistEpost: new FormControl('', [Validators.required, Validators.email]),
      turistMobilnummer: new FormControl('', [Validators.required, Validators.pattern('^[0-9]*$')]),
      turistAdress: new FormControl('', [Validators.required])
    });
  }

  public openDialog(stugaId: number): void {
    this.valtStugaId = stugaId
    this.visaBokningsForm = true;
    this.initStugaBokningForm();
  }

  public submitBokaStuga() {
    this.submitted = true;
    if (!this.bokastugaForm.valid) {
      return;
    }

    this.bokninService.bokaStuga(this.byggBokningRequest()).subscribe((res) => {
      this.bekraftelseResponse = res.meddelande
    },
      (error) => {
        alert(error.error.message)
      }
    );
  }

  public visaBokningar() {
    this.bekraftelseResponse = "";
    this.visaBokningsForm = false;
  }

  public hamtaBild(bildNamn: string) {
    return CommonService.hamtaBild(bildNamn);
  }

  private byggBokningRequest() {
    const bokningRequest: BokningRequest = {
      stugaId: this.valtStugaId,
      incheckningsdatum: this.formatDateTime(this.bokastugaForm.get('incheckningsdatum')?.value, this.bokastugaForm.get('incheckningstid')?.value),
      utcheckningsdatum: this.formatDateTime(this.bokastugaForm.get('utcheckningsdatum')?.value, this.bokastugaForm.get('utcheckningstid')?.value),
      turistNamn: this.bokastugaForm.get('turistNamn')?.value,
      turistEpost: this.bokastugaForm.get('turistEpost')?.value,
      turistMobilnummer: this.bokastugaForm.get('turistMobilnummer')?.value,
      turistAdress: this.bokastugaForm.get('turistAdress')?.value
    }
    return bokningRequest;
  }

  formatDateTime(datum: string, tid: string): string {
    const date: Date = new Date(datum);
    if (date) {
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2);
      const day = ('0' + date.getDate()).slice(-2);
      return `${year}-${month}-${day} ${tid}`;
    }
    return '';
  }

  public getErrorMessage(formControlName: string): string {
    if (!this.submitted) {
      return '';
    }
    const controlError: ValidationErrors =
      this.bokastugaForm.get(formControlName)?.errors!;
    if (controlError) {
      const errors = Object.keys(controlError);
      return errors[0];
    }
    return '';
  }
}
