import { Injectable } from "@angular/core";
import { Tab } from "./tab.model";

@Injectable()
export class HeaderService {
  tabs: Map<string, Tab>; //key is route (sets were not working as intended)
  constructor() {
    this.resetTabs();
  }
  addTab(name: string, route: string) {
    this.tabs.set(route, { name: name, route: route, closable: true });
  }

  //Param is route url
  closeTab(url: string) {
    this.tabs.delete(url);
  }

  getTabs(): Map<string, Tab> {
    return this.tabs;
  }

  resetTabs(){
    this.tabs = new Map();
    this.tabs.set("/", { name: "Inicio", route: "/", closable: false });
  }
}
