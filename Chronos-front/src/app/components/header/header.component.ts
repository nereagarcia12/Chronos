import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/services/token-storage.service';

declare function menu() : any;


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn = false;
  user!: any;
  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.user = this.tokenStorage.getUser();
    }
    this.tokenStorage.currentUser.subscribe(user => this.user = user)
    setTimeout(() => {
      menu()
  }, 100/60);
  }

  logout(): void {
    this.tokenStorage.signOut();
    window.location.reload();
  }

  empty(): void{

  }
}
