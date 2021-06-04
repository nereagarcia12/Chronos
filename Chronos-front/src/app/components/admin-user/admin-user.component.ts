import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user-interface';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent implements OnInit {

  users: User[] = []
  pageOfUsers!: Array<User>;
  isLoggedIn = false;
  user!: any;

  constructor(private userService: UserService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.user = this.tokenStorage.getUser();
  }
  this.getUsers();
  }

  getUsers():void{
    this.userService.getAllUsers().subscribe((data) =>
    this.users = data)
  }

  deleteUser(id: number){
    this.userService.deleteUser(id).subscribe(()=> {
      this.reloadPage();
    })
  }
  reloadPage(): void {
    window.location.reload();
  }
  onChangePage(pageOfAds: Array<User>) {
    this.pageOfUsers = pageOfAds;
  }

}
