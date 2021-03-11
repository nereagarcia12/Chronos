import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profilesidebar',
  templateUrl: './profilesidebar.component.html',
  styleUrls: ['./profilesidebar.component.css']
})
export class ProfilesidebarComponent implements OnInit {

  isLoggedIn = false;
  user!: any;
  constructor(private tokenStorage: TokenStorageService,
    private userService: UserService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.user = this.tokenStorage.getUser();
    }
  }

  logout(): void {
    this.tokenStorage.signOut();
    window.location.reload();
  }

  deleteUser():void{
    this.userService.deleteUser(this.user.id).subscribe((data) => {
      alert("Se ha borrado tu cuenta. Lo sentimos")
      this.logout()
      
    })
  }

}
