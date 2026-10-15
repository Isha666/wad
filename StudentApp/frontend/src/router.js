import { createRouter, createWebHistory } from 'vue-router'
import StudentForm from './components/StudentForm.vue'
import StudentList from './components/StudentList.vue'

const routes = [
  { path: '/', redirect: '/add' },
  { path: '/add', component: StudentForm },
  { path: '/students', component: StudentList }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
