import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { UserService } from './service/user.service';
import { MenuComponent } from './menu/menu.component';
import { FalhaDeLoginComponent } from './falha-de-login/falha-de-login.component';
import { MasterComponent } from './master/master.component';
import { NgbdAccordionConfig } from './accordion-config/accordion-config.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CriarUsuarioComponent } from './criar-usuario/criar-usuario.component';
import { UsuarioService } from './service/usuario-service.service';
import { DesbloquearUsuarioComponent } from './desbloquear-usuario/desbloquear-usuario.component';
import { ContasBancariasComponent } from './contas-bancarias/contas-bancarias.component';
import { ContasBancariasService } from './service/contas-bancarias.service';
import { ContasComponent } from './contas/contas.component';
import { InserirContasModalComponent } from './inserir-contas-modal/inserir-contas-modal.component';
import { UserHeaderComponent } from './user-header/user-header.component';
import { LoginComponent } from './login/login.component';
import { TelaInicialComponent } from './tela-inicial/tela-inicial.component';
import { CorpoComponent } from './corpo/corpo.component';
import { AlterarContasModalComponent } from './alterar-contas-modal/alterar-contas-modal.component';
import { AlterarRendasModalComponent } from './alterar-rendas-modal/alterar-rendas-modal.component';
import { InserirRendasModalComponent } from './inserir-rendas-modal/inserir-rendas-modal.component';
import { RendasComponent } from './rendas/rendas.component';
import { RendasUsuarioComponent } from './rendas-usuario/rendas-usuario.component';
import { RendasService } from './service/rendas-service';
import { GastosComponent } from './gastos/gastos.component';
import { GastosUsuarioComponent } from './gastos-usuario/gastos-usuario.component';
import { InserirGastosModalComponent } from './inserir-gastos-modal/inserir-gastos-modal.component';
import { AlterarGastosModalComponent } from './alterar-gastos-modal/alterar-gastos-modal.component';
import { OrigemComponent } from './origem/origem.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardUsuarioComponent } from './dashboard-usuario/dashboard-usuario.component';
import { VeiculosComponent } from './veiculos/veiculos.component';
import { InserirVeiculosComponent } from './inserir-veiculos/inserir-veiculos.component';
import { AlterarVeiculosComponent } from './alterar-veiculos/alterar-veiculos.component';
import { VeiculosUsuarioComponent } from './veiculos-usuario/veiculos-usuario.component';
import { VeiculosService } from './service/veiculos.service';
import { AlterarImoveisComponent } from './alterar-imoveis/alterar-imoveis.component';
import { InserirImoveisComponent } from './inserir-imoveis/inserir-imoveis.component';
import { ImoveisComponent } from './imoveis/imoveis.component';
import { ImoveisUsuarioComponent } from './imoveis-usuario/imoveis-usuario.component';
import { ImoveisService } from './service/imoveis.service';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserFormComponent,
    MenuComponent,
    FalhaDeLoginComponent,
    MasterComponent,
    NgbdAccordionConfig,
    CriarUsuarioComponent,
    DesbloquearUsuarioComponent,
    ContasBancariasComponent,
    ContasComponent,
    InserirContasModalComponent,
    UserHeaderComponent,
    LoginComponent,
    TelaInicialComponent,
    CorpoComponent,
    AlterarContasModalComponent,
    AlterarRendasModalComponent,
    InserirRendasModalComponent,
    RendasComponent,
    RendasUsuarioComponent,
    GastosComponent,
    GastosUsuarioComponent,
    InserirGastosModalComponent,
    AlterarGastosModalComponent,
    OrigemComponent,
    DashboardComponent,
    DashboardUsuarioComponent,
    VeiculosComponent,
    InserirVeiculosComponent,
    AlterarVeiculosComponent,
    VeiculosUsuarioComponent,
    AlterarImoveisComponent,
    InserirImoveisComponent,
    ImoveisComponent,
    ImoveisUsuarioComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
  ],
  providers: [UserService,UsuarioService,ContasBancariasService,RendasService,VeiculosService,ImoveisService],
  bootstrap: [AppComponent]
})
export class AppModule { }
