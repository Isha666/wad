import { createRouter, createWebHistory } from 'vue-router'
import Room from '../components/Room.vue'
import Booking from '../components/Booking.vue'
const router = createRouter({ history: createWebHistory(), routes: [
  { path: '/', redirect: '/rooms' },
  { path: '/rooms', component: Room },
  { path: '/bookings', component: Booking }
] })
export default router
