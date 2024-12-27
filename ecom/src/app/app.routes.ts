import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { OrdersuccessComponent } from './ordersuccess/ordersuccess.component';
import { PaymentfailComponent } from './paymentfail/paymentfail.component';

export const routes: Routes = [
    {path: 'search/:keyword', component: ProductListComponent },
    {path: 'cart', component: CartComponent },
    {path: 'checkout', component: CheckoutComponent},
    {path: 'category/:id', component: ProductListComponent},
    {path: 'category', component: ProductListComponent},
    {path: 'products/:id', component: ProductDetailsComponent},
    {path: 'products', component: ProductListComponent},
    { path: 'order-success', component:  OrdersuccessComponent },
    { path: 'payment-failure', component: PaymentfailComponent },
    {path: '', redirectTo: '/products', pathMatch: 'full'},
    {path: '**', redirectTo: '/products', pathMatch: 'full'},


];
