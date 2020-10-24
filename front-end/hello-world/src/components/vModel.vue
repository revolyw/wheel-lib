<template>
  <div id="app">
      <p>{{ message }}</p>
      <input v-model="message">
  </div>
</template>
    
<script>

export default {
  name: 'vModel',
  el: '#app',
  data: function(){
    return { 
      message: 'Runoob!'
    }
  }
}
</script>