<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const url = '/api/people'
const people = ref([])
const name = ref('')
const age = ref(0)
const editId = ref(null)
const searchId = ref('')
const selectedPerson = ref(null)
const error = ref('')

// GET ALL
async function load() {
  try {
    people.value = (await axios.get(url)).data
    error.value = ''
  } catch (e) {
    error.value = 'Could not load people. Check that Spring Boot and MongoDB are running.'
  }
}

// ADD OR UPDATE
async function save() {
  const person = { name: name.value, age: Number(age.value) }
  try {
    if (editId.value) {
      await axios.put(`${url}/${editId.value}`, person)
    } else {
      await axios.post(url, person)
    }
    name.value = ''
    age.value = 0
    editId.value = null
    await load()
  } catch (e) {
    error.value = 'Could not save person.'
  }
}

// FILL FORM FOR EDITING
function edit(p) {
  editId.value = p.id
  name.value = p.name
  age.value = p.age
}

// DELETE
async function remove(id) {
  try {
    await axios.delete(`${url}/${id}`)
    selectedPerson.value = null
    await load()
  } catch (e) {
    error.value = 'Could not delete person.'
  }
}

// GET BY ID
async function getById() {
  if (!searchId.value.trim()) return
  try {
    selectedPerson.value = (await axios.get(`${url}/${searchId.value.trim()}`)).data
    error.value = selectedPerson.value ? '' : 'No person found with that ID.'
  } catch (e) {
    selectedPerson.value = null
    error.value = 'Could not search for that ID.'
  }
}

onMounted(load)
</script>

<template>
  <h2>Person Management System</h2>
  <p v-if="error" class="error">{{ error }}</p>

  <!-- ADD / UPDATE FORM -->
  <form @submit.prevent="save">
    <input v-model="name" placeholder="Name" required />
    <input v-model.number="age" type="number" min="0" placeholder="Age" required />
    <button type="submit">{{ editId ? 'Update' : 'Add' }}</button>
  </form>

  <!-- GET BY ID -->
  <h3>Search Person by ID</h3>
  <input v-model="searchId" placeholder="Enter Person ID" />
  <button @click="getById">Search</button>
  <p v-if="selectedPerson">{{ selectedPerson.name }} - {{ selectedPerson.age }}</p>

  <!-- GET ALL -->
  <h3>All People</h3>
  <table>
    <thead>
      <tr><th>Name</th><th>Age</th><th>Actions</th></tr>
    </thead>
    <tbody>
      <tr v-for="p in people" :key="p.id">
        <td>{{ p.name }}</td>
        <td>{{ p.age }}</td>
        <td>
          <button @click="edit(p)">Edit</button>
          <button @click="remove(p.id)">Delete</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>
h2 { color: darkblue; }
input, button { padding: 8px; margin: 5px; }
table { width: 100%; border-collapse: collapse; margin-top: 15px; }
th, td { border: 1px solid gray; padding: 10px; text-align: left; }
th { background-color: #eeeeee; }
.error { color: darkred; }
</style>
