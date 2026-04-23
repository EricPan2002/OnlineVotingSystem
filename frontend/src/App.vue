
<template>
  <div id="app">
    <h1>線上投票系統</h1>
    <nav class="navbar">
      <div class="nav-links">
        <router-link to="/">投票首頁</router-link> |
        <router-link to="/admin">後台管理</router-link>
      </div>
    </nav>

    <main class="content">
      <router-view />
    </main>
  </div>
</template>

<style>
#app { font-family: sans-serif; padding: 20px; }
.navbar { border-bottom: 2px solid #eee; margin-bottom: 20px; padding-bottom: 10px; }
.nav-links a { margin: 0 10px; color: #42b983; text-decoration: none; }
.nav-links a.router-link-active { font-weight: bold; text-decoration: underline; }
</style>