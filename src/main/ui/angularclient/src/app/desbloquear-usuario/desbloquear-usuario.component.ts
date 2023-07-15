import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../service/usuario-service.service';
import { Usuario } from '../model/usuario';

@Component({
  selector: 'app-desbloquear-usuario',
  templateUrl: './desbloquear-usuario.component.html',
  styleUrls: ['./desbloquear-usuario.component.css']
})
export class DesbloquearUsuarioComponent implements OnInit {

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
    this.usuarioService.habilitarUsuario(this.usuario).subscribe(data => {
      this.usuario = data;
      if (this.usuario != null){
          this.gotoUserList();
      }
      });
  }

  gotoUserList() {
    this.router.navigate(['/home']);
  }
}
