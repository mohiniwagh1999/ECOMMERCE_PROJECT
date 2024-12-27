import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  orders: any[] = [];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Retrieve query params and parse the orders array
    this.route.queryParams.subscribe(params => {
      if (params['orders']) {
        this.orders = JSON.parse(params['orders']); // Parse the stringified orders array
      }
    });
  }

}
