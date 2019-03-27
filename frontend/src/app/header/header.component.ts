import { Component, TemplateRef, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "../login/login.service";
import { MatDialogRef, MatDialog } from "@angular/material";
import { HeaderService } from "./header.service";
import { Tab } from "./tab.model";

/**
 * Component for the header
 */
@Component({
  selector: "header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent {
  @ViewChild("loginDialog") loginDialog: TemplateRef<any>;
  dialogRef: MatDialogRef<any, any>;
  loginError: boolean = false;
  constructor(
    private router: Router,
    public headerService: HeaderService,
    public loginService: LoginService,
    public dialog: MatDialog
  ) {}
  //Tab methods
  addTab(name: string, route: string) {
    this.headerService.addTab(name, route);
  }

  //Param is router url
  closeTab(url: string) {
    // If the tab to remove is the current open tab go to home route
    if (url === this.router.url) this.router.navigate(["/"]);
    this.headerService.closeTab(url);
  }
  //Login/Logout methods
  openLoginDialog() {
    this.dialogRef = this.dialog.open(this.loginDialog, {
      width: "50%",
      height: "50%"
    });
  }

  logIn(event: any, user: string, pass: string) {
    event.preventDefault();

    this.loginService.logIn(user, pass).subscribe(
      u => {
        console.log(u);
        this.loginError = false;
        this.dialogRef.close();
      },
      error => (this.loginError = true)
    );
  }

  logOut() {
    this.loginService.logOut().subscribe(
      response => {
        this.router.navigate(["/"]);
      },
      error => console.log("Error when trying to log out: " + error)
    );
  }
}
