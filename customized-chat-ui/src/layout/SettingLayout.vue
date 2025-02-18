<template>
  <div class="system-layout">
    <el-container>
      <el-header>
        <AppHeader />
      </el-header>
      <el-container>
        <el-aside>
          <AppSideBar />
        </el-aside>
        <el-main>
          <AppMain />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import AppHeader from './components/app-header/index.vue'
import AppMain from './components/app-main/index.vue'
import AppSideBar from './components/side-bar/index.vue'
</script>

<style lang="scss" scoped></style>
