import { Component } from "@angular/core";
import { Router } from "@angular/router";

interface Tab {
  name: string;
  route: string;
}
@Component({
  selector: "header",
  templateUrl: "./header.component.html"
})
export class HeaderComponent {
  tabs: Tab[] = [{ name: "Inicio", route: "" }];

  constructor(private router: Router) {}

  addTab(name: string, route: string) {
    this.tabs.push({ name: name, route: route });
  }

  routeTab(route:string) {
    this.router.navigate([route]);
  }
}