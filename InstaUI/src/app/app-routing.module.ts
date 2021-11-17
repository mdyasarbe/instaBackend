import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './components/profileComponent/profile-component.component';
import { ImageUploadComponent } from './components/image-upload/image-upload.component';
import { MostDownloadedComponent } from './components/most-downloaded/most-downloaded.component'
import { AuthGuardService } from './service/authguard';
import { SearchComponent } from './components/search/search.component';

import { AddFollowersComponent } from './components/add-followers/add-followers.component';

const routes: Routes = [
  {
    path: '', loadChildren: () => import('./components/account/account.module').then((m) => m.AccountModule),
  },
  {
    path: 'profile', component: ProfileComponent, canActivate: [AuthGuardService]
  },
  {
    path: 'imageupload', component: ImageUploadComponent, canActivate: [AuthGuardService]
  },
  {
    path: 'mostdownload', component: MostDownloadedComponent, canActivate: [AuthGuardService]
  },
  {
    path: 'search', component: SearchComponent, canActivate: [AuthGuardService]
  },
  {
    path: 'addfollowers', component: AddFollowersComponent, canActivate: [AuthGuardService]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
