import { Component } from "@angular/core";
import { Router } from "@angular/router";

/**
 * Interface for Tab information
 */
interface Tab {
  name: string,
  route: string,
  closable: boolean
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
  //Now holds placeholder data to be removed
  //TODO: Remove tab Teacher
  tabs: Tab[] = [{ name: "Inicio", route: "/", closable: false }, { name: "Teacher", route: "teacher/15", closable: true }, { name: "Student", route: "concept/15", closable: true }];

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