<template>
  <v-toolbar fixed dark class="cyan">
    <v-toolbar-title class="mr-4">
      <span class="home" @click="navigateTo({name: 'root'})">Sistema de Eventos</span>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn v-if="$store.state.isUserLoggedIn" @click="navigateTo({name: 'perfil'})">Perfil</v-btn>
    <v-btn v-if="$store.state.isUserLoggedIn" @click="navigateTo({name: 'eventos'})">Eventos</v-btn>
    <v-btn v-if="$store.state.isUserLoggedIn" @click="navigateTo({name: 'meuseventos'})">Meus Eventos</v-btn>
    <v-btn v-if="$store.state.isUserLoggedIn" @click="navigateTo({name: 'minhasinscricoes'})">Minhas Inscrições</v-btn>
    <v-btn v-if="$store.state.isUserLoggedIn" @click="navigateTo({name: 'validarchave'})">Validar Chave</v-btn>
    <v-btn v-if="!$store.state.isUserLoggedIn" @click="navigateTo({name: 'register'})">Registrar-se</v-btn>
    <v-btn v-if="!$store.state.isUserLoggedIn" @click="navigateTo({name: 'login'})">Entrar</v-btn>
    <v-btn v-if="$store.state.isUserLoggedIn" @click="logout()">Sair</v-btn>
  </v-toolbar>
</template>

<script>
export default {
  methods: {
    navigateTo (route) {
      this.$router.push(route)
    },
    logout () {
      this.$store.dispatch('setToken', null)
      this.$store.dispatch('setUser', null)
      this.$store.dispatch('setIsUserLoggedIn', false)
      this.$router.push('root')
    }
  }
}
</script>
<style scoped>
.home{
  cursor: pointer;
}
.home:hover{
  color: rgb(93, 190, 228);
}
</style>
