import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../service/usuario-service.service';
import { Usuario } from '../model/usuario';

@Component({
  selector: 'app-criar-usuario',
  templateUrl: './criar-usuario.component.html',
  styleUrls: ['./criar-usuario.component.css']
})
export class CriarUsuarioComponent implements OnInit{

  usuario: Usuario;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private usuarioService: UsuarioService) {
    this.usuario = new Usuario();
  }

    ngOnInit(): void {
    }

  onSubmit() {
    this.usuarioService.enviarCadastro(this.usuario).subscribe(data => {
      this.usuario = data;
      if (this.usuario != null){
        if (this.usuario.retorno != "Senhas diferentes!"){
          this.gotoUserList();
        }
        else{
          alert('Senhas diferentes');
        }
      }
      });
  }

  gotoUserList() {
    this.router.navigate(['/desbloquearUsuario']);
  }
}
