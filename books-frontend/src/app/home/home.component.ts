import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  links : string[] = ["Books", "Authors", "Genres", "Publishers"];
  activeLink : string = this.links[0];
}
