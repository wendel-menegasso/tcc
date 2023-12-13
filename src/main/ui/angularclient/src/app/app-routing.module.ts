import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { MenuComponent } from './menu/menu.component';
import { FalhaDeLoginComponent} from './falha-de-login/falha-de-login.component';
import { AppComponent } from './app.component';
import { CriarUsuarioComponent } from './criar-usuario/criar-usuario.component';
import { DesbloquearUsuarioComponent } from  './desbloquear-usuario/desbloquear-usuario.component';
import { ContasBancariasComponent } from './contas-bancarias/contas-bancarias.component';
import { ContasComponent } from './contas/contas.component';
import { LoginComponent } from './login/login.component';
import { TelaInicialComponent } from './tela-inicial/tela-inicial.component';
import { CorpoComponent } from './corpo/corpo.component';
import { RendasComponent } from './rendas/rendas.component';
import { RendasUsuarioComponent } from './rendas-usuario/rendas-usuario.component';
import { GastosUsuarioComponent } from './gastos-usuario/gastos-usuario.component';
import { GastosComponent } from './gastos/gastos.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardUsuarioComponent } from './dashboard-usuario/dashboard-usuario.component';
import { VeiculosComponent } from './veiculos/veiculos.component';
import { VeiculosUsuarioComponent } from './veiculos-usuario/veiculos-usuario.component';
import { ImoveisComponent } from './imoveis/imoveis.component';
import { ImoveisUsuarioComponent } from './imoveis-usuario/imoveis-usuario.component';

const routes: Routes = [
    { path: 'login', component: AppComponent },
    { path: 'users', component: UserListComponent },
    { path: 'menu', component: MenuComponent},
    { path: 'contasBancarias', component: ContasComponent},
    { path: 'contas', component: ContasBancariasComponent},
    { path: 'logout', component: UserFormComponent},
    { path: 'home', component: TelaInicialComponent},
    { path: 'corpo', component: CorpoComponent},
    { path: '', component: UserFormComponent },
    { path: 'telaLogin', component: LoginComponent},
    { path: 'falhaDeLogin', component: FalhaDeLoginComponent},
    { path: 'cadastrarUsuario', component: CriarUsuarioComponent},
    { path: 'desbloquearUsuario', component: DesbloquearUsuarioComponent},
    { path: 'ganhos', component: RendasUsuarioComponent},    
    { path: 'ganhosObtidos', component: RendasComponent},
    { path: 'gastos', component: GastosUsuarioComponent},
    { path: 'gastosObtidos', component: GastosComponent},
    { path: 'dashboard', component: DashboardComponent},
    { path: 'dashboardObtidos', component: DashboardUsuarioComponent},
    { path: 'veiculos', component: VeiculosComponent},
    { path: 'veiculosObtidos', component: VeiculosUsuarioComponent},
    { path: 'imoveis', component: ImoveisComponent},
    { path: 'imoveisObtidos', component: ImoveisUsuarioComponent},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
