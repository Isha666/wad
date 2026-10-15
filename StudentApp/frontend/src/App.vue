<template>
  <div>
    <h1>Student Management</h1>
    <nav>
      <router-link to="/add">Add Student</router-link>
      |
      <router-link to="/students">View Students</router-link>
    </nav>
    <hr>
    <router-view />
  </div>
</template>
