import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {

  user: User;
    users: User;
    name: string;
    token: string;
    id: string;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private userService: UserService) {
    this.user = new User();
  }

  onSubmit() {
    this.userService.authenticate(this.user).subscribe(data => {
      this.users = data;
      if (this.users != null){
          console.log(this.users);
          this.gotoUserList();
      }
      else{
        this.router.navigate(['falhaDeLogin']);
      }
      });
  }

  gotoUserList() {
      this.name = this.users.name;
      this.token = this.users.token;
      this.id = this.users.id;
      this.router.navigate(['/home'], { queryParams: { name: this.name, 'token': this.token, 'id': this.id  } });
  }
}
