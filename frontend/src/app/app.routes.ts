import { Routes } from '@angular/router';
import {AtmoComponent} from './component/atmo-component/atmo-component';

export const routes: Routes = [
  {path: 'atmo', children: [
    {path: '', component: AtmoComponent},
    ]},
];
