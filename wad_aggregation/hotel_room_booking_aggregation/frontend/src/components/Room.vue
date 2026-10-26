<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
const rooms=ref([]),stats=ref([]),error=ref('')
const roomId=ref(''),roomType=ref('Single'),price=ref(0)
async function loadRooms(){rooms.value=(await axios.get('/api/rooms')).data}
async function loadStats(){stats.value=(await axios.get('/api/room-statistics')).data}
async function addRoom(){try{error.value='';await axios.post('/api/rooms',{roomId:roomId.value,roomType:roomType.value,price:price.value});await loadRooms();await loadStats()}catch(e){error.value='Could not add room: '+e.message}}
onMounted(async()=>{try{await loadRooms();await loadStats()}catch(e){error.value=e.message}})
</script>
<template>
 <h2>Add Room</h2><p class="error">{{error}}</p>
 <form @submit.prevent="addRoom"><input v-model="roomId" placeholder="Room ID" required><select v-model="roomType"><option>Single</option><option>Double</option><option>Suite</option></select><input type="number" min="0" v-model.number="price" placeholder="Price" required><button>Add Room</button></form>
 <h2>All Rooms</h2><table><tr><th>ID</th><th>Type</th><th>Price</th><th>Status</th></tr><tr v-for="r in rooms" :key="r.roomId"><td>{{r.roomId}}</td><td>{{r.roomType}}</td><td>{{r.price}}</td><td>{{r.status}}</td></tr></table>
 <h2>Room Statistics (Aggregation)</h2><button @click="loadStats">Refresh Statistics</button><table><tr><th>Room Type</th><th>Count</th><th>Average Price</th><th>Total Price</th><th>Minimum</th><th>Maximum</th></tr><tr v-for="s in stats" :key="s._id"><td>{{s._id}}</td><td>{{s.totalRooms}}</td><td>{{s.averagePrice}}</td><td>{{s.totalPrice}}</td><td>{{s.minimumPrice}}</td><td>{{s.maximumPrice}}</td></tr></table>
</template>
