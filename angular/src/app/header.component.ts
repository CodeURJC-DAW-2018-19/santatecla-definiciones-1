import { Component } from "@angular/core";
import { Router } from "@angular/router";

interface Tab {
  name: string,
  route: string,
  closable: boolean
}
@Component({
  selector: "header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent {
  tabs: Tab[] = [{ name: "Inicio", route: "", closable: false }];

  constructor(private router: Router) { }

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
}