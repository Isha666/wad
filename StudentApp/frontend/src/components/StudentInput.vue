<script setup>
import { ref } from 'vue'

// PROPS: Receive title from parent
defineProps(['title'])

// EMITS: Declare custom event
const emit = defineEmits(['addStudent'])

const name = ref('')

function submit() {
  if (name.value.trim() === '') return

  // Send name to parent
  emit('addStudent', name.value.trim())
  name.value = ''
}
</script>

<template>
  <div>
    <h2>{{ title }}</h2>
    <input v-model="name" placeholder="Enter student name">
    <button @click="submit">Add Student</button>
  </div>
</template>
