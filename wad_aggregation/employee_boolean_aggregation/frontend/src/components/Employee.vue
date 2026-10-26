<script setup>
import { ref,onMounted } from 'vue'
import axios from 'axios'
const employees=ref([]),error=ref('')
const employeeId=ref(''),name=ref('')
const sickLeave=ref(false),vacationLeave=ref(false),casualLeave=ref(false)
async function loadEmployees(){employees.value=(await axios.get('/api/employees')).data}
async function addEmployee(){try{error.value='';await axios.post('/api/employees',{employeeId:employeeId.value,name:name.value,sickLeave:sickLeave.value,vacationLeave:vacationLeave.value,casualLeave:casualLeave.value});await loadEmployees()}catch(e){error.value='Failed: '+e.message}}
onMounted(async()=>{try{await loadEmployees()}catch(e){error.value=e.message}})
</script>
<template>
 <h2>Add Employee</h2><p class="error">{{error}}</p>
 <form @submit.prevent="addEmployee"><input v-model="employeeId" placeholder="Employee ID" required><input v-model="name" placeholder="Employee Name" required><label><input type="checkbox" v-model="sickLeave">Sick Leave</label><label><input type="checkbox" v-model="vacationLeave">Vacation Leave</label><label><input type="checkbox" v-model="casualLeave">Casual Leave</label><button>Add Employee</button></form>
 <h2>Employees</h2><table><tr><th>ID</th><th>Name</th><th>Sick</th><th>Vacation</th><th>Casual</th></tr><tr v-for="e in employees" :key="e.employeeId"><td>{{e.employeeId}}</td><td>{{e.name}}</td><td>{{e.sickLeave}}</td><td>{{e.vacationLeave}}</td><td>{{e.casualLeave}}</td></tr></table>
</template>
