import { Routes } from '@angular/router';
import { ProductPage } from './pages/product/product-page/product-page';
import { Home } from './pages/home/home';
import { OutboundOrderPage } from './pages/outbound-order/outbound-order-page/outbound-order-page';
import { InboundDeliveryPage } from './pages/inbound-delivery/inbound-delivery-page/inbound-delivery-page';
import { InboundDeliveryHistory } from './pages/inbound-delivery/inbound-delivery-history/inbound-delivery-history';
import { OutboundOrderHistory } from './pages/outbound-order/outbound-order-history/outbound-order-history';

export const routes: Routes = [
    {
        path: '',
        component: Home
    },
    {
        path: 'products',
        component: ProductPage
    },
    {
        path: 'orders',
        component: OutboundOrderPage
    },
    {
        path: 'orders/history',
        component: OutboundOrderHistory
    },
    {
        path: 'deliveries',
        component: InboundDeliveryPage
    },
    {
        path: 'deliveries/history',
        component: InboundDeliveryHistory
    }
];
