<template>
  <v-layout ml-16 mr-16 mt-8>
    <v-flex>
      <panel title="Register">
        <form name="fincontrol-form" autocomplete="off">
          <br />
          <v-text-field label="E-mail" v-model="email" required></v-text-field>
          <br />
          <v-text-field label="Password" type="password" v-model="password" required></v-text-field>
          <br />
          <div class="error" v-html="error" />
          <br />
          <v-btn class="cyan" dark @click="register">Register</v-btn>
        </form>
      </panel>
    </v-flex>
  </v-layout>
</template>

<script>
import AuthenticationService from '@/services/AuthenticationService'
import Panel from '@/components/Panel'
export default {
  data () {
    return {
      email: '',
      password: '',
      error: null
    }
  },
  watch: {
    email (value) {
      console.log('email has changed: ', value)
    }
  },
  methods: {
    async register () {
      try {
        const response = await AuthenticationService.register({
          email: this.email,
          password: this.password
        })
        this.$store.dispatch('setToken', response.data.token)
        this.$store.dispatch('setUser', response.data.user)
        if (response.data.token) {
          this.$store.dispatch('setIsUserLoggedIn', true)
        } else {
          this.$store.dispatch('setIsUserLoggedIn', false)
        }
        this.$router.push('root')
      } catch (error) {
        this.error = error.response.data.error
      }
    }
  },
  components: {
    Panel
  }
  // mounted () {
  //   setTimeout(() => {
  //     this.email = 'hello world'
  //   }, 2000)
  // }
}
</script>

<style scoped>
.error {
  background-color: red ;
  color: white;
}
</style>
