import { createRouter, createWebHistory } from 'vue-router'
import Employee from '../components/Employee.vue'
import Leave from '../components/Leave.vue'
const router = createRouter({ history: createWebHistory(), routes: [
  { path: '/', redirect: '/employees' },
  { path: '/employees', component: Employee },
  { path: '/leaves', component: Leave }
] })
export default router
