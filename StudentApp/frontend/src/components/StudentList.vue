<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const students = ref([])

async function loadStudents() {
  const res = await axios.get('/api/students')
  students.value = res.data
}

onMounted(loadStudents)
</script>

<template>
  <div>
    <h2>All Students</h2>
    <ul>
      <li v-for="student in students" :key="student.id">
        {{ student.name }}
      </li>
    </ul>
  </div>
</template>
