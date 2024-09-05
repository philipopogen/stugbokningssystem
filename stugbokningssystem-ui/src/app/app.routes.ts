import { Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './home/home.component';
import { AccessControlComponent } from './access-control/access-control.component';

export const routes: Routes = [
    {
        path: '',
        children: [
            { path: '', component: HomeComponent },
          { path: 'bokningar/:id', component: AdminComponent },
          { path: 'access-control', component: AccessControlComponent },
          { path: '**', redirectTo: 'home', pathMatch: 'full' },
        ],
      },
];
