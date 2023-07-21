import {Component, Input } from '@angular/core';
import { User } from '../model/user';

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.css']
})
export class UserHeaderComponent{
    @Input() username : User;
}

