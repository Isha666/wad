<script setup>
import { ref,onMounted } from 'vue'
import axios from 'axios'
const bookings=ref([]),details=ref([]),message=ref('')
const bookingID=ref(''),customerName=ref(''),phone=ref(''),roomId=ref('')
async function loadBookings(){bookings.value=(await axios.get('/api/bookings')).data}
async function loadDetails(){details.value=(await axios.get('/api/booking-details')).data}
async function bookRoom(){try{const res=await axios.post('/api/bookings',{bookingID:bookingID.value,customerName:customerName.value,phone:phone.value,roomId:roomId.value});if(res.data===null){message.value='Room unavailable, invalid room or duplicate booking ID';return}message.value='Booking CONFIRMED';await loadBookings();await loadDetails()}catch(e){message.value='Booking failed: '+e.message}}
onMounted(async()=>{try{await loadBookings();await loadDetails()}catch(e){message.value=e.message}})
</script>
<template>
 <h2>Book Room</h2><p>{{message}}</p><form @submit.prevent="bookRoom"><input v-model="bookingID" placeholder="Booking ID" required><input v-model="customerName" placeholder="Customer Name" required><input v-model="phone" placeholder="Phone" required><input v-model="roomId" placeholder="Room ID" required><button>Book Room</button></form>
 <h2>All Bookings</h2><table><tr><th>Booking ID</th><th>Customer</th><th>Phone</th><th>Room ID</th><th>Status</th></tr><tr v-for="b in bookings" :key="b.bookingID"><td>{{b.bookingID}}</td><td>{{b.customerName}}</td><td>{{b.phone}}</td><td>{{b.roomId}}</td><td>{{b.bookingStatus}}</td></tr></table>
 <h2>Booking Details (Aggregation)</h2><button @click="loadDetails">Refresh Details</button><table><tr><th>Customer</th><th>Room Type</th><th>Price</th><th>Room Status</th></tr><tr v-for="d in details" :key="d._id"><td>{{d.customerName}}</td><td>{{d.room.roomType}}</td><td>{{d.room.price}}</td><td>{{d.room.status}}</td></tr></table>
</template>
