import { Component, TemplateRef, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "../login/login.service";
import { MatDialogRef, MatDialog } from "@angular/material";

/**
 * Interface for Tab information
 */
interface Tab {
  name: string;
  route: string;
  closable: boolean;
}

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
  //Now holds placeholder data to be removed
  //TODO: Remove tab Teacher
  tabs: Tab[] = [
    { name: "Inicio", route: "/", closable: false },
    { name: "Teacher", route: "teacher/15", closable: true },
    { name: "Student", route: "concept/15", closable: true }
  ];
  loginError: boolean = false;
  constructor(
    private router: Router,
    public loginService: LoginService,
    public dialog: MatDialog
  ) {}
  //Tab methods
  addTab(name: string, route: string) {
    this.tabs.push({ name: name, route: route, closable: true });
  }

  closeTab(tab: Tab) {
    let index = this.tabs.indexOf(tab);
    // If the tab to remove is the current open tab go to home route
    if (this.router.url === this.tabs[index].route)
      this.router.navigate([this.tabs[0].route]);
    this.tabs.splice(index, 1);
  }

  routeTab(route: string) {
    this.router.navigate([route]);
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
