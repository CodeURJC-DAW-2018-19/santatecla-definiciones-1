import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { TestComponent } from "./test.component";
import { StudentComponent } from "./student.component";

const routes: Routes = [{ path: "", component: TestComponent },
                        { path: "concept/:id", component: StudentComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
