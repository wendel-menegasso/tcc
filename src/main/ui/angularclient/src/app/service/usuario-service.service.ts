import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../model/usuario';
import { Observable, of } from 'rxjs'

@Injectable()
export class UsuarioService {

  private enviarCadastroUrl: string;
  private habilitarUsuarioUrl: string;

  constructor(private http: HttpClient) {
    //this.enviarCadastroUrl = 'http://localhost:9090/enviarCadastro';
    //this.habilitarUsuarioUrl = 'http://localhost:9090/habilitarUsuario';
    this.enviarCadastroUrl = 'http://20.124.3.145:9090/enviarCadastro';
    this.habilitarUsuarioUrl = 'http://20.124.3.145:9090/habilitarUsuario';
  }

  public enviarCadastro(user: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.enviarCadastroUrl, user);
  }

  public habilitarUsuario(user: Usuario) : Observable<Usuario> {
    return this.http.post<Usuario>(this.habilitarUsuarioUrl, user);
  }
}
