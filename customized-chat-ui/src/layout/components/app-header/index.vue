<template>
  <div class="app-header w-full">
    <TopBar />
  </div>
</template>

<script setup lang="ts">
import TopBar from '../top-bar/index.vue'
</script>

<style lang="scss" scoped>
.app-header {
  background-color: var(--app-header-bg-color);
  position: fixed;
  top: 0;
  left: 0;
  z-index: 100;
}
</style>
