<template>
  <v-app>
    <v-main>
      <v-container>
        <chat-component />
      </v-container>
    </v-main>
  </v-app>
</template>

<script lang="ts">
  import { defineComponent } from 'vue'
  import ChatComponent from './components/ChatComponent.vue'

  export default defineComponent({
    components: {
      ChatComponent,
    },
  })
</script>

<style>
/* Global styles */
</style>
