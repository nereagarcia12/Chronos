import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateUser, User } from 'src/app/model/user-interface';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  form: FormGroup;
  name: FormControl;
  phone: FormControl;
  city: FormControl;
  email: FormControl;
  isLoggedIn = false;
  user!: any;
  userLoggedIn!: any;


  constructor(private userService: UserService,
    private tokenStorage: TokenStorageService,
    private activatedRoute: ActivatedRoute, private route : Router) {
      this.name = new FormControl('', [Validators.required]);
      this.phone = new FormControl('', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.maxLength(9)]);
      this.city = new FormControl('', [Validators.required,Validators.pattern(/^[A-Za-zñÑáéíóúÁÉÍÓÚ ]+$/)]);
      this.email = new FormControl('', [Validators.required,Validators.email]);
      this.form = new FormGroup({
        name: this.name,
        phone: this.phone,
        city: this.city,
        email: this.email
      });
     
     }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.userLoggedIn = this.tokenStorage.getUser();
  }
  this.getUser();
  }

  getUser():void{
    this.userService.getUserByid(this.userLoggedIn.id).subscribe((data) =>{
      this.user = data;
      this.form.patchValue({
        name: this.user.name,
        phone: this.user.phone,
        city: this.user.city,
        email: this.user.email
      })
    })
  }

  onSubmit():void{
    let user ={name: this.name.value, phone: this.phone.value, city: this.city.value, email: this.email.value, password: 'notRequired'} as CreateUser
    this.userService.editUser(this.user.id, user).subscribe(()=>{
      this.userLoggedIn.name = this.name.value;
      this.tokenStorage.saveUser(this.userLoggedIn);
    })
  }



}
