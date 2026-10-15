<script setup>
import { ref } from 'vue'
import axios from 'axios'
import StudentInput from './StudentInput.vue'

const message = ref('')

async function addStudent(name) {
  try {
    await axios.post('/api/students', {
      name: name
    })
    message.value = name + ' added successfully!'
  } catch (error) {
    message.value = 'Error adding student'
  }
}
</script>

<template>
  <div>
    <!-- PROPS + EMITS -->
    <StudentInput
      title="Add New Student"
      @addStudent="addStudent"
    />
    <p>{{ message }}</p>
  </div>
</template>
