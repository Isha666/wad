<script setup>
import { ref,onMounted } from 'vue'
import axios from 'axios'
const leaves=ref([]),names=ref([]),message=ref('')
const leaveId=ref(''),employeeId=ref(''),startDate=ref(''),endDate=ref(''),leaveType=ref('sick')
async function loadLeaves(){leaves.value=(await axios.get('/api/leaves')).data}
async function fetchValid(){names.value=(await axios.get('/api/valid-leaves')).data}
async function applyLeave(){try{const res=await axios.post('/api/leaves',{leaveId:leaveId.value,employeeId:employeeId.value,startDate:startDate.value,endDate:endDate.value,leaveType:leaveType.value});if(res.data===null){message.value='Invalid employee or duplicate leave ID';return}await loadLeaves();await fetchValid();message.value='Leave applied'}catch(e){message.value='Failed: '+e.message}}
onMounted(async()=>{try{await loadLeaves();await fetchValid()}catch(e){message.value=e.message}})
</script>
<template>
 <h2>Apply Leave</h2><p>{{message}}</p>
 <form @submit.prevent="applyLeave"><input v-model="leaveId" placeholder="Leave ID" required><input v-model="employeeId" placeholder="Employee ID" required><input type="date" v-model="startDate" required><input type="date" v-model="endDate" required><select v-model="leaveType"><option value="sick">Sick</option><option value="vacation">Vacation</option><option value="casual">Casual</option></select><button>Apply Leave</button></form>
 <h2>Leave Applications</h2><table><tr><th>Leave ID</th><th>Employee ID</th><th>Start Date</th><th>End Date</th><th>Type</th></tr><tr v-for="l in leaves" :key="l.leaveId"><td>{{l.leaveId}}</td><td>{{l.employeeId}}</td><td>{{l.startDate}}</td><td>{{l.endDate}}</td><td>{{l.leaveType}}</td></tr></table>
 <h2>Eligible Employees (Aggregation)</h2><button @click="fetchValid">Refresh</button><ul><li v-for="(n,i) in names" :key="i">{{n}}</li></ul>
</template>
