import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  name: FormControl;
  email: FormControl;
  phone: FormControl;
  city: FormControl;
  password: FormControl;
  errorMessage: string = '';

  constructor(private authService: AuthService,
    private route : Router) { 
    this.name = new FormControl('', [Validators.required]);
    this.email = new FormControl('', [Validators.required,Validators.email]);
    this.phone = new FormControl('', [Validators.required, Validators.pattern(/^-?(0|[1-9]\d*)?$/), Validators.maxLength(9)]);
    this.city = new FormControl('', [Validators.required,Validators.pattern(/^[A-Za-zñÑáéíóúÁÉÍÓÚ ]+$/)]);
    this.password = new FormControl('', [Validators.required,Validators.minLength(6),Validators.maxLength(12)]);
    this.form = new FormGroup({
      name: this.name,
      email: this.email,
      phone: this.phone,
      city: this.city,
      password: this.password
    });
  }

  ngOnInit(): void {
  }

  onSubmit():void{
    let user = {name: this.name.value, email: this.email.value, phone: this.phone.value, city: this.city.value, password: this.password.value } 
    this.authService.register(user).subscribe( (data) => {
      this.route.navigate(['login']);
    },
    err => {
      this.errorMessage = err.error.error;
    })
  }
}
