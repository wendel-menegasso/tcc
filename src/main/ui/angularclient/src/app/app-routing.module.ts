import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { MenuComponent } from './menu/menu.component';
import { MasterComponent } from './master/master.component';
import { FalhaDeLoginComponent} from './falha-de-login/falha-de-login.component';
import { AppComponent } from './app.component';
import { CriarUsuarioComponent } from './criar-usuario/criar-usuario.component';
import { DesbloquearUsuarioComponent } from  './desbloquear-usuario/desbloquear-usuario.component';
import { ContasBancariasComponent } from './contas-bancarias/contas-bancarias.component';
import { ContasComponent } from './contas/contas.component';

const routes: Routes = [
    { path: 'login', component: AppComponent },
    {
        path: '',
        component: MasterComponent,
        children: [
        { path: '', component: UserFormComponent },
        { path: 'users', component: UserListComponent },
        { path: 'home', component: MenuComponent},
        { path: 'home/:name/:token/:id', component: MenuComponent},
        { path: 'falhaDeLogin', component: FalhaDeLoginComponent},
        { path: 'cadastrarUsuario', component: CriarUsuarioComponent},
        { path: 'desbloquearUsuario', component: DesbloquearUsuarioComponent},
        { path: 'contasBancarias', component: ContasComponent},
        { path: 'contas', component: ContasBancariasComponent},
        { path: 'logout', component: UserFormComponent}
        ],
    },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
